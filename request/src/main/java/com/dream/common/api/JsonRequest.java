package com.dream.common.api;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class JsonRequest<T> extends ExtendedRequest<T> {
    private Class<T> mResponseType;
    private HashMap<String,String> heads;

    public JsonRequest(Class<T> responseType, int method, String url, Response.Listener<T> listener,
                       Response.ErrorListener errorListener) {
        this(responseType, method, url, listener, errorListener, null);
    }

    public JsonRequest(Class<T> responseType, int method, String url, Response.Listener<T> listener,
                       Response.ErrorListener errorListener,HashMap<String,String> heads) {
        super(method, url, listener, errorListener);
        mResponseType = responseType;
        this.heads = heads;
    }

    @Override
    public HashMap<String, String> getHeaders() throws AuthFailureError {
        HashMap<String,String> map = super.getHeaders();
        if(heads != null){
            map.putAll(heads);
        }
        return map;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        String jsonString;
        try {
            jsonString = getResponseString(response);
        } catch (UnsupportedEncodingException e) {
            return Response.error(new VolleyError(e));
        }

        Gson gson = new Gson();
        T result = null;
        try {
            result = gson.fromJson(jsonString, mResponseType);
        }catch (JsonSyntaxException e){
            Log.e(JsonRequest.class.getName(),jsonString + "is not T object");
        }
        return Response.success(result, null);
    }
}
