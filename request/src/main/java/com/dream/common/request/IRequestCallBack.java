package com.dream.common.request;

import com.android.volley.VolleyError;


/**
 * Editor: lee
 * Email: shuhejiang@163.com
 * Date: 2014/11/12
 */
public interface IRequestCallBack<Result> {

    void onSuccess(Result callBack);
    void onFailed(VolleyError volley);
}
