package com.dream.common.api;

import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * Extend request.</p>
 * Gzip support.
 *
 * Created by cindy on 4/23/14.
 */
public abstract class ExtendedRequest<T> extends Request<T> {
    private static final String HEADER_ENCODING = "Content-Encoding";
    private static final String HEADER_USER_AGENT = "User-Agent";
    private static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";
    private static final String ENCODING_GZIP = "gzip";
    protected final Response.Listener<T> mSuccessListener;
    protected HashMap<String, String> mParams;
    private String mUserAgent;
    private boolean mGzipEnabled = true;

    public ExtendedRequest(int method, String url, Response.Listener<T> successListener,
                           Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        mSuccessListener = successListener;
    }

    @Override
    protected void deliverResponse(T response) {
        if (mSuccessListener != null) {
            mSuccessListener.onResponse(response);
        }
    }

    protected String getResponseString(NetworkResponse response) throws UnsupportedEncodingException {
        String responseString = null;
        String charset = HttpHeaderParser.parseCharset(response.headers);

        if (mGzipEnabled && isGzipped(response)) {
            try {
                byte[] data = decompressResponse(response.data);
                responseString = new String(data, charset);
            } catch (IOException e) {
                // it seems that result is not GZIP
            }
        }

        if (responseString == null) {
            responseString = new String(response.data, charset);
        }

        return responseString;
    }

    protected boolean isGzipped(NetworkResponse response) {
        Map<String, String> headers = response.headers;
        return headers != null && !headers.isEmpty() && headers.containsKey(HEADER_ENCODING) &&
                headers.get(HEADER_ENCODING).equalsIgnoreCase(ENCODING_GZIP);
    }

    protected byte[] decompressResponse(byte[] compressed) throws IOException {
        ByteArrayOutputStream baos = null;
        try {
            int size;
            ByteArrayInputStream memStream = new ByteArrayInputStream(compressed);
            GZIPInputStream gzip = new GZIPInputStream(memStream);
            final int buffSize = 8192;
            byte[] tempBuffer = new byte[buffSize];
            baos = new ByteArrayOutputStream();
            while ((size = gzip.read(tempBuffer, 0, buffSize)) != -1) {
                baos.write(tempBuffer, 0, size);
            }
            return baos.toByteArray();
        } finally {
            if (baos != null) {
                baos.close();
            }
        }
    }

    /**
     * Adds POST parameter
     *
     * @param key   Parameter name
     * @param value Parameter value
     */
    public void addParam(String key, Object value) {
        if (mParams == null) {
            mParams = new HashMap<String, String>();
        }
        mParams.put(key, String.valueOf(value));
    }

    @Override
    protected HashMap<String, String> getParams() throws AuthFailureError {
        return mParams;
    }

    /**
     * Sets parameters map
     *
     * @param params Parameters map
     */
    public void setParams(HashMap<String, String> params) {
        mParams = params;
    }

    @Override
    public HashMap<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<String, String>();

        // add user agent header
        if (TextUtils.isEmpty(mUserAgent)) {
            headers.put(HEADER_USER_AGENT, mUserAgent);
        }

        // add gzip header
        if (mGzipEnabled) {
            headers.put(HEADER_ACCEPT_ENCODING, ENCODING_GZIP);
        }
        return headers;
    }

    /**
     * Sets user agent to specify in request header
     *
     * @param userAgent User agent string
     */
    public void setUserAgent(String userAgent) {
        mUserAgent = userAgent;
    }

    /**
     * Disables GZIP compressing (enabled by default)
     */
    public void disableGzip() {
        mGzipEnabled = false;
    }

    /**
     * Sets request timeout
     *
     * @param timeoutSec Timeout in seconds
     */
    public void setTimeout(int timeoutSec) {
        setRetryPolicy(new DefaultRetryPolicy(timeoutSec * 1000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }
}