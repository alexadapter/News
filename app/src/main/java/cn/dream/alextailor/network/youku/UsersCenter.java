package cn.dream.alextailor.network.youku;

import android.content.Context;

import com.dream.common.api.SimpleJsonApi;
import com.dream.common.request.IRequestCallBack;

import cn.dream.alextailor.bean.youku.users.request.MyInfoOverview;
import cn.dream.alextailor.bean.youku.users.response.MyInfoBean;

/**
 * Created by alexadapter on 2015/9/30.
 */
public class UsersCenter {
    public static final String MY_INFO_API = "https://openapi.youku.com/v2/users/myinfo.json";
    /**
     * 获取优质视频信息
     * */
    public static final void getMyInfo(Context context,
                                              IRequestCallBack<MyInfoBean> callBack){
        MyInfoOverview bean = new MyInfoOverview();
        new SimpleJsonApi<>(MyInfoBean.class, callBack, bean).execute(context, context);
    }
}
