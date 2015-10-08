package com.dream.common.request;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dream.common.api.JsonRequest;

import java.util.HashMap;
import java.util.Map;


/**
 * Editor: lee
 * Email: shuhejiang@163.com
 * Date: 2014/11/12
 */
public abstract class AbstractEncryJsonRequest<Result> extends AbstractEncryRequest<Result> {

    IRequestCallBack<Result> callBack;
    Class clazz;

    public AbstractEncryJsonRequest(Class clazz, IRequestCallBack<Result> callBack,String key) {
        super(key);
        this.clazz = clazz;
        this.callBack = callBack;
    }

    @Override
    protected Request<Result> getRequest() {

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onFailed(error);
            }
        };

        Response.Listener<Result> listener = new Response.Listener<Result>() {
            @Override
            public void onResponse(Result response) {
                callBack.onSuccess(response);
            }
        };

        JsonRequest<Result> request = new JsonRequest<Result>(clazz, getMethod(), getFullUrl(),
                listener, errorListener) {
            @Override
            public HashMap<String, String> getHeaders() throws AuthFailureError {
                return AbstractEncryJsonRequest.this.getHead();
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                return AbstractEncryJsonRequest.this.getPost();
            }
        };
        request.setParams(getParams());
        return request;
    }
}
