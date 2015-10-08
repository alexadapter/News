package adapter.leelibs.utils;

import android.support.annotation.NonNull;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Editor: lee
 * Email: shuhejiang@163.com
 * Date: 2014/10/17
 */
public class URLUtils {
    public static String ENCODING = "utf-8";

    public static byte[] encodeParameters(Map<String, String> params) {
        return encodeURL(params).getBytes();
    }

    public static String encodeURL(Map<String, String> params) {
        StringBuilder stb = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String value = (entry.getKey() == null || entry.getKey().isEmpty()) ? "" : entry.getKey();
                stb.append(URLEncoder.encode(value, ENCODING));
                stb.append('=');

                value = (entry.getValue() == null || entry.getValue().isEmpty()) ? "" : entry.getValue();
                stb.append(URLEncoder.encode(value, ENCODING));
                stb.append('&');
            }

            return stb.length() > 1 ? stb.substring(0,stb.length()-1) : stb.toString();
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Encoding not supported: " + ENCODING, uee);
        }

    }

    public static String connectUnencodeURL(Map<String, String> params) {
        StringBuilder stb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String value = (entry.getKey() == null || entry.getKey().isEmpty()) ? "" : entry.getKey();
            stb.append(value);
            stb.append('=');

            value = (entry.getValue() == null || entry.getValue().isEmpty()) ? "" : entry.getValue();
            stb.append(value);
            stb.append('&');
        }

        return stb.length() > 1 ? stb.substring(0,stb.length()-1) : stb.toString();

    }

    /**
     * Encode url
     * @param url
     * @param params
     * @return
     */
    public static String constructURL(String url,Map<String, String> params) {
        StringBuilder stb = new StringBuilder(url);
        try {
            stb.append('?');
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if(entry.getValue() == null || entry.getValue().isEmpty()){
                    continue;
                }
                stb.append(URLEncoder.encode(entry.getKey(),ENCODING));
                stb.append('=');
                stb.append(URLEncoder.encode(entry.getValue(),ENCODING));
                stb.append('&');
            }
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Encoding not supported: " + ENCODING, uee);
        }
        return stb.substring(0,stb.length()-1);
    }
}
