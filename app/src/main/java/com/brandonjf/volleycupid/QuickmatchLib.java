package com.brandonjf.volleycupid;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.brandonjf.volleycupid.okclasses.QuickmatchMatch;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by brandon on 10/1/15.
 */
public class QuickmatchLib {
//    private final static String API_KEY = "23ed6c48a8904099ee2e6df28cbb5f42";
final static String API_ENDPOINT = "https://api.okcupid.com/1/quickmatch";
    public static OkResponseInterface okResponseInterface;
    private static QuickmatchLib mInstance;
    private Response.Listener responseListener = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            try {
               parseQuickmatchQueueJson(response);
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

    private QuickmatchLib(OkResponseInterface okResponseInterface) {
        QuickmatchLib.okResponseInterface = okResponseInterface;
    }

    public static synchronized QuickmatchLib getInstance(OkResponseInterface okResponseInterface) {
        if (mInstance == null) {
            mInstance = new QuickmatchLib(okResponseInterface);
        }
        return mInstance;
    }

    public void parseQuickmatchQueueJson(JSONObject jsonResponse){
        try {
            Gson gson = new Gson();
            ArrayList<QuickmatchMatch> quickmatchMatches = new ArrayList<QuickmatchMatch>();

            JSONArray matches = jsonResponse.getJSONArray("matches");
            for (int i = 0; i < matches.length(); i++) {
                JSONObject match = matches.getJSONObject(i);
                QuickmatchMatch tempMatch = gson.fromJson(match.toString(), QuickmatchMatch.class);
                quickmatchMatches.add(tempMatch);
            }
            Log.d("QuickmatchLib", "Done loading quickmatch queue.");
            okResponseInterface.onQuickmatchQueueListener(quickmatchMatches);
        } catch (Exception e) {
            Log.d("QuickmatchLib", e.toString());
        }


//        okResponseInterface.onQuickmatchQueueListener();
    }
    public void getQueue(Context context) {
        String url = API_ENDPOINT + "?access_token=" + context.getString(R.string.ok_token);
        OkJsonRequest quickmatchQueueRequest = new OkJsonRequest(Request.Method.GET, url, null, responseListener, errorListener);
        quickmatchQueueRequest.setPriority(Request.Priority.LOW);
        ApplicationController.getInstance().addToRequestQueue(quickmatchQueueRequest);

    }
}
