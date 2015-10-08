package com.dream.common.api;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by lee on 22/4/15.
 */
public class CharsetUTF8Request<T> extends JsonRequest<T> {


    public CharsetUTF8Request(Class responseType, int method, String url, Response.Listener listener, Response.ErrorListener errorListener) {
        super(responseType, method, url, listener, errorListener);
    }

    @Override
    protected String getResponseString(NetworkResponse response) throws UnsupportedEncodingException {
        String responseString = null;
        String charset = HttpHeaderParser.parseCharset(response.headers);

        if ( isGzipped(response)) {
            try {
                byte[] data = decompressResponse(response.data);
                responseString = new String(data, "utf-8");
            } catch (IOException e) {
                // it seems that result is not GZIP
            }
        }

        if (responseString == null) {
            responseString = new String(response.data, "utf-8");
        }

        return responseString;
    }
}
