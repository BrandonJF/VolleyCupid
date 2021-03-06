package com.brandonjf.volleycupid;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * Created by brandon on 9/30/15.
 */
public class OkJsonRequest extends JsonObjectRequest {

    private Priority mPriority;

    public OkJsonRequest(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener){
        super(method, url, jsonRequest, listener, errorListener);
    }

    public void  setPriority(Priority priority){
        mPriority = priority;
    }

    @Override
    public Priority getPriority() {
        return mPriority == null ? Priority.NORMAL : mPriority;
    }

    @Override
    public String getBodyContentType() {
        return "application/x-www-form-urlencoded; charset=UTF-8";
    }
}
