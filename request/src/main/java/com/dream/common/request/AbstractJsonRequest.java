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
public abstract class AbstractJsonRequest<Result> extends AbstractRequest<Result> {

    protected IRequestCallBack<Result> callBack;
    protected Class<Result> clazz;

    public AbstractJsonRequest(Class<Result> clazz,IRequestCallBack<Result> callBack) {
        super();
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
                return AbstractJsonRequest.this.getHead();
            }

            @Override
            public byte[] getBody() throws AuthFailureError {

                return getPost();
            }
        };
        request.setParams(getParams());
        return request;
    }
}
