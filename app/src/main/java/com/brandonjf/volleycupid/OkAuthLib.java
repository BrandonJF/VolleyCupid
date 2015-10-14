package com.brandonjf.volleycupid;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by brandon on 10/1/15.
 */
public class OkAuthLib {

    final static String AUTH_ENDPOINT = "https://api.okcupid.com/oauth2/authorize?";
    final static String TOKEN_ENDPOINT = "https://api.okcupid.com/oauth2/token?";
//    final static String REDIRECT_URI = "brandonjf://redirect";
    private String mRedirectUri;
    private String mAuthEndpoint;
    private String mTokenEndpoint;
    private String mClientId;
    private String mClientSecret;
    private String mAuthorizationCode;
    private String mAccessToken;
    private String mRefreshToken;
    private Context mContext;
    private WebView webView;
    private ProgressDialog progressDialog;

    public static OkResponseInterface okResponseInterface;
    private static OkAuthLib mInstance;

    private Response.Listener responseListener = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            try {
//               parseQuickmatchQueueJson(response);
            } catch (Exception e) {
                Log.d("QuickmatchLib", e.toString());
            }
        }
    };

    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.d("VolleyCupid", error.toString());
        }
    };

    private OkAuthLib(OkResponseInterface okResponseInterface) {
        OkAuthLib.okResponseInterface = okResponseInterface;
    }

    public static synchronized OkAuthLib getInstance(OkResponseInterface okResponseInterface) {
        if (mInstance == null) {
            mInstance = new OkAuthLib(okResponseInterface);
        }
        return mInstance;
    }

    public static synchronized OkAuthLib getInstance() {
        return mInstance;
    }

    public OkAuthLib init(Context context){
        mAuthEndpoint = context.getString(R.string.oauth_endpoint_auth);
        mTokenEndpoint = context.getString(R.string.oauth_endpoint_token);
        mRedirectUri = context.getString(R.string.oauth_redirect_uri);
        mClientId = context.getString(R.string.oauth_client_id);
        mClientSecret = context.getString(R.string.oauth_client_secret);
        mContext = context.getApplicationContext();
        return this;
    }

    public void startAuthentication(){
        getAuthorizationCode();
    }

    public void getAuthorizationCode(){
        if (mClientId.isEmpty()){
            return;
        }
        Uri authUri = Uri.parse(mAuthEndpoint)
                .buildUpon()
                .appendQueryParameter("scope", "0_1_2_3_4_5_6")
                .appendQueryParameter("redirect_uri", mRedirectUri)
                .appendQueryParameter("response_type", "code")
                .appendQueryParameter("client_id", mClientId)
                .build();
        String authUrl = authUri.toString();
        Log.d("VolleyCupid", authUrl);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, authUri);
        browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(browserIntent);
    }

    public void setAuthorizationCode(String authorizationCode){
        this.mAuthorizationCode = authorizationCode;
        convertAuthCodeToAccessToken(authorizationCode);
        Log.d("VolleyCupid", "Auth Code: " + authorizationCode);
    }

    public void setAccessToken(String accessToken){
        this.mAccessToken = accessToken;
    }

    public String getAccessToken(){
        return mAccessToken;
    }
    public void setRefreshToken(String refreshToken){
        this.mRefreshToken = refreshToken;
    }
    public String getRefreshToken(){
        return mRefreshToken;
    }

    public void convertAuthCodeToAccessToken(String authCode){
        try {
            Map<String, String> jsonBody = new HashMap<String, String>();
            jsonBody.put("client_id", mClientId);
            jsonBody.put("client_secret", mClientSecret);
            jsonBody.put("redirect_uri", mRedirectUri);
            jsonBody.put("code", authCode);
            jsonBody.put("grant_type", "authorization_code");
            CustomRequest accessTokenRequest = new CustomRequest(Request.Method.POST, mTokenEndpoint, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        setAccessToken(response.getString("access_token"));
                        setRefreshToken(response.getString("refresh_token"));
                        okResponseInterface.onAccessTokenReceivedListener(getAccessToken());
                    } catch (Exception e) {
                        Log.d("OkAuthLib", e.toString());
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("VolleyCupid", error.toString());
                }
            });

            ApplicationController.getInstance().addToRequestQueue(accessTokenRequest);
        } catch (Exception e) {
            Log.e("VolleyCupid", "unexpected JSON exception", e);
        }



    }
}
