package adapter.leelibs.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Created by adapter on 2014/6/26.
 */
public class LibUtils {
    public static final String TAG= "Utils";

    public static final String MD5_BAD = "md5bad";

    public static String FileToMD5(String filename) {
        return FileToMD5(new File(filename));
    }

    public static String FileToMD5(File file) {
        final int BUFFER_SIZE = 8192;
        byte[] buf = new byte[BUFFER_SIZE];
        int length;
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            MessageDigest md = MessageDigest.getInstance("MD5");
            while ((length = bis.read(buf)) != -1) {
                md.update(buf, 0, length);
            }


            byte[] array = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (Exception e) {
//			e.printStackTrace();
            Log.e(TAG, e.getMessage());
        } finally {
            try {
                if(!isNull(bis)) {
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return MD5_BAD;
    }

    public static String stringToMD5(String input) {
        String ret = MD5_BAD;
        try {
            // 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
            MessageDigest messageDigest =MessageDigest.getInstance("MD5");
            // 输入的字符串转换成字节数组
            byte[] inputByteArray = input.getBytes();
            // inputByteArray是输入字符串转换得到的字节数组
            messageDigest.update(inputByteArray);
            // 转换并返回结果，也是字节数组，包含16个元素
            byte[] resultByteArray = messageDigest.digest();
            StringBuffer sb = new StringBuffer();
            for (byte array : resultByteArray) {
                sb.append(Integer.toHexString((array & 0xFF) | 0x100).substring(1, 3));
            }
            // 字符数组转换成字符串返回
//            return byteArrayToHex(resultByteArray);
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, e.getMessage());
        }
        return ret;
    }


    public static boolean isNull(Object object){
        return object == null;
    }

    public static boolean isNullValue(String str){
        return str == null || str.equals("")
                ||  str.toLowerCase().equals("null");
    }

    /**
     * 通过下载地址确定下载的文件名
     * */
    public static String getFileNameFromUrl(String url,String fileName){
        String name = fileName;
        if(isNullValue(name)){
            name = getFileNameFromUrl(url);
        }
        return name;
    }

    private static String getFileNameFromUrl(String url){
        int index = url.indexOf('?');
        String name = "";
        if(index > 0){
            url = url.substring(0,index);
        }
        int lastIndex = url.lastIndexOf("/");
        if(lastIndex < 0){
            lastIndex = 0;
        }else{
            lastIndex += 1;
        }
        name = url.substring(lastIndex);
//        Log.e(TAG,"name=" + name);
        return name;
    }

    public static String SHA1(String val) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(val.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static long getAvailaleSize(String path){
        StatFs stat = new StatFs(path);
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return availableBlocks * blockSize;
        //(availableBlocks * blockSize)/1024      KIB 单位
        //(availableBlocks * blockSize)/1024 /1024  MIB单位
    }

    /*private static String deviceId(Context context){
        String uuid = null;
        if (uuid == null) {
            final SharedPreferences prefs = context.getSharedPreferences(PREFS_FILE, 0);
            final String id = prefs.getString(PREFS_DEVICE_ID, null);
            if (id != null) {
                // Use the ids previously computed and stored in the prefs file
                uuid = UUID.fromString(id);
            } else {
                final String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
                // Use the Android ID unless it's broken, in which case fallback on deviceId,
                // unless it's not available, then fallback on a random number which we store
                // to a prefs file
                try {
                    if (!"9774d56d682e549c".equals(androidId)) {
                        uuid = UUID.nameUUIDFromBytes(androidId.getBytes("utf8"));
                    } else {
                        final String deviceId = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
                        uuid = deviceId != null ? UUID.nameUUIDFromBytes(deviceId.getBytes("utf8")) : UUID.randomUUID();
                    }
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
                // Write the value out to the prefs file
//                prefs.edit().putString(PREFS_DEVICE_ID, uuid.toString()).commit();
            }
        }
        return uuid;
    }*/

}
