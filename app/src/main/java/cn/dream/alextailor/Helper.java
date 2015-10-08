package cn.dream.alextailor;

import android.os.Build;
import java.util.HashMap;

/**
 * Created by alexadapter on 2015/9/30.
 */
public class Helper {
    public static HashMap<String, String> getHeader() {
        HashMap<String, String> header = new HashMap<String, String>();
        header.put("User-Agent", generateUserAgent());
        header.put("Content-Type", "text/html; charset=utf-8; text/xml");
//        header.put("uid", UserPersonalInfo.newInstance().getUid());
//        String userName = UserPersonalInfo.newInstance().getUid();
//        if (!TextUtils.isEmpty(userName)) {
//            String encodedUserName = Base64.encodeToString(userName.getBytes(), Base64.DEFAULT);
//            header.put("userName", encodedUserName);
//        }

        if (BuildConfig.DEBUG)
            header.put("debug", String.valueOf(true));
        return header;
    }

    public static final int VERSION = 1;

    private static String generateUserAgent() {
        StringBuilder sb = new StringBuilder("api-client/");
        sb.append(VERSION);
        sb.append(" ");
        sb.append(BuildConfig.APPLICATION_ID);
        sb.append("/");
        sb.append(BuildConfig.VERSION_NAME);
        sb.append("(");
        sb.append(BuildConfig.VERSION_CODE);
        sb.append(")");
        sb.append("[");
        sb.append(BuildConfig.FLAVOR);
        sb.append("Android/");
        sb.append(Build.VERSION.SDK_INT);
        return sb.toString();
    }
}
