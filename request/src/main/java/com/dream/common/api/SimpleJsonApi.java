package com.dream.common.api;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;

import com.android.volley.Request;
import com.dream.common.request.AbstractJsonRequest;
import com.dream.common.request.ExtRequestImpl;
import com.dream.common.request.IRequestCallBack;
import java.util.HashMap;

/**
 * Editor: lee
 * Email: shuhejiang@163.com
 * Date: 2014/11/13
 */
public class SimpleJsonApi<Result> extends AbstractJsonRequest<Result> {

    private ExtRequestImpl requestParams;

    public SimpleJsonApi(Class<Result> clazz, IRequestCallBack<Result> callBack, ExtRequestImpl object) {
        super(clazz, callBack);
        this.requestParams = object;
        if(requestParams == null){
            throw new NullPointerException("request is null");
        }
    }

    public SimpleJsonApi(Class<Result> clazz, IRequestCallBack<Result> callBack) {
        super(clazz, callBack);
    }

    @Override
    protected String getUrl() {
        return requestParams != null ? requestParams.getUrl() : null;
    }

    @Override
    protected int getMethod() {
        return requestParams != null ? requestParams.getMethod() : Request.Method.GET;
    }

    @Override
    protected HashMap<String,String> getHead() {
        if(requestParams != null && requestParams.getHead() != null){
            return requestParams.getHead();
        }else {
            return null;
        }
    }

    @Override
    protected HashMap<String, String> getParams() {
        if(requestParams != null){
            return requestParams.getParams();
        }
        return super.getParams();
    }

    @Override
    public void execute(Context context,Object tag) {
        execute(context,tag,false);
    }

    public void execute(Context context,Object tag,boolean trustHttps){
        RequestManager.getInstance(context).executeRequest(getRequest(), tag);
    }


}
