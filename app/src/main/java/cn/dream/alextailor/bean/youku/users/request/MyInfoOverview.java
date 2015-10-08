package cn.dream.alextailor.bean.youku.users.request;

import com.android.volley.Request;
import com.dream.common.request.ExtRequestImpl;

import java.util.HashMap;

import cn.dream.alextailor.network.youku.UsersCenter;

import cn.dream.alextailor.network.youku.YouKuHelper;

/**
 * Created by alexadapter on 2015/9/30.
 */
public class MyInfoOverview implements ExtRequestImpl {
    @Override
    public String getUrl() {
        return UsersCenter.MY_INFO_API;
    }

    @Override
    public int getMethod() {
        return Request.Method.POST;
    }

    @Override
    public HashMap<String, String> getParams() {
        HashMap map = new HashMap();
        map.put("client_id","" + YouKuHelper.YK_CENTER_ID);
        map.put("access_token","token");
        return map;
    }

    @Override
    public HashMap<String, String> getHead() {
        return new HashMap<>();
    }
}
