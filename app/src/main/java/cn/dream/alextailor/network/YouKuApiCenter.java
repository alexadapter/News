package cn.dream.alextailor.network;

import android.content.Context;
import com.dream.common.api.SimpleJsonApi;
import com.dream.common.request.IRequestCallBack;
import cn.dream.alextailor.bean.youku.quality.request.QualityVideosOverview;
import cn.dream.alextailor.bean.youku.quality.response.QualityVideosBean;

/**
 * Created by alexadapter on 2015/9/30.
 */
public class YouKuApiCenter {

    public static final String QUALITY_VIDEOS_URL = "https://api.youku.com/quality/video/by/category.json";

    static {

    }

    /**
     * 获取优质视频信息
     * */
    public static final void getQualityVideos(Context context,
                                              IRequestCallBack<QualityVideosBean> callBack){
        QualityVideosOverview bean = new QualityVideosOverview();
        new SimpleJsonApi<>(QualityVideosBean.class, callBack, bean).execute(context, context);
    }
}
