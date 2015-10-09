package com.brandonjf.volleycupid;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by brandon on 10/1/15.
 */
public class OkAuthLib {

final static String AUTH_ENDPOINT = "https://api.okcupid.com/oauth2/authorize";
final static String TOKEN_ENDPOINT = "https://api.okcupid.com/oauth2/token";
private String mAuthEndpoint;
private String mTokenEndpoint;
private String mClientId;
private String mClientSecret;

    public static OkResponseInterface okResponseInterface;
    private static OkAuthLib mInstance;
    private Response.Listener responseListener = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {o
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

    public void init(Context context){
        String authEndpoint = context.getString(R.string.oauth_endpoint_auth);
        String tokenEndpoint = context.getString(R.string.oauth_endpoint_token);
        String clientId = context.getString(R.string.oauth_client_id);
        String clientSecret =context.getString(R.string.oauth_client_secret);

    }

    public void getQueue(Context context) {
        String url = API_ENDPOINT + "?access_token=" + context.getString(R.string.ok_token);
        OkJsonRequest quickmatchQueueRequest = new OkJsonRequest(Request.Method.GET, url, null, responseListener, errorListener);
        quickmatchQueueRequest.setPriority(Request.Priority.LOW);
        ApplicationController.getInstance().addToRequestQueue(quickmatchQueueRequest);

    }
}
