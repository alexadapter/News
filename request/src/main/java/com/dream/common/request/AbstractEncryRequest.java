package com.dream.common.request;

import com.android.volley.Request;

import adapter.leelibs.utils.URLUtils;

/**
 * Editor: lee
 * Email: shuhejiang@163.com
 * Date: 2014/11/21
 */
public abstract class AbstractEncryRequest<T> extends AbstractRequest<T> {
    private String key;

    public AbstractEncryRequest(String key){
        super();
        this.key = key;
    }

    @Override
    protected byte[] getPost() {
        super.getPost();
        if (getParams() != null) {
            return URLUtils.encodeParameters(getParams());
        }
        return null;
    }

    @Override
    protected String getFullUrl() {
        if (getMethod() == Request.Method.GET && getParams() != null)
            return URLUtils.constructURL(getUrl(), getParams());
        else
            return getUrl();
    }

    /**
     * Converts <code>params</code> into an application/x-www-form-urlencoded encoded string.
     */
    /*private byte[] encodeParameters(TreeMap<String, String> params, String encode) {
        StringBuilder stb = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                stb.append(URLEncoder.encode(entry.getKey(), encode));
                stb.append('=');
                stb.append(URLEncoder.encode(entry.getValue(), encode));
                stb.append('&');
            }
            String str = stb.toString();
            if(str.endsWith("&")){
                str = str.substring(0, stb.length() - 1);
            }
            stb.append(Utils.MD5Hex(str + "&key=" + key).toUpperCase());

            return stb.toString().getBytes(encode);
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Encoding not supported: " + encode, uee);
        }
    }

    private String constructURL(String url,Map<String, String> params) {
        StringBuilder stb = new StringBuilder(url);
        try {
            stb.append('?');
            for (Map.Entry<String, String> entry : params.entrySet()) {
                stb.append(URLEncoder.encode(entry.getKey(),encoding));
                stb.append('=');
                stb.append(URLEncoder.encode(entry.getValue(),encoding));
                stb.append('&');
            }

            String str = stb.toString();
            if(str.endsWith("&")){
                str = str.substring(0,stb.length() - 1);
            }
            stb.append(Utils.MD5Hex(str + "&key=" + key).toUpperCase());
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Encoding not supported: " + encoding, uee);
        }
        return stb.substring(0,stb.length()-1);
    }*/
}
