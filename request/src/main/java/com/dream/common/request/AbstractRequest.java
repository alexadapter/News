package com.dream.common.request;

import com.android.volley.Request;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import adapter.leelibs.utils.URLUtils;

/**
 * Editor: lee
 * Email: shuhejiang@163.com
 * Date: 2014/11/12
 */
public abstract class AbstractRequest<T> implements IRequest {

    private String TAG = AbstractRequest.class.getName();

    public AbstractRequest(){
    }

    protected byte[] getPost() {
        if (getParams() != null) {
            return URLUtils.encodeParameters(getParams());
        }
        return null;
    }

    protected abstract String getUrl();
    protected abstract int getMethod();
    protected abstract HashMap<String,String> getHead();
    protected java.util.HashMap<String,String> getParams(){return null;}
    protected abstract Request<T> getRequest();

    protected String getFullUrl() {
        if (getMethod() == Request.Method.GET && getParams() != null)
            return URLUtils.constructURL(getUrl(), getParams());
        else
            return getUrl();
    }
}
