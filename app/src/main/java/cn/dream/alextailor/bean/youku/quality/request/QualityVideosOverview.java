package cn.dream.alextailor.bean.youku.quality.request;

import com.android.volley.Request;
import com.dream.common.request.ExtRequestImpl;

import java.util.HashMap;

import cn.dream.alextailor.network.YouKuApiCenter;
import cn.dream.alextailor.network.youku.YouKuHelper;

/**
 * Created by alexadapter on 2015/9/30.
 */
public class QualityVideosOverview implements ExtRequestImpl {
    private int cate ;
    private int page ;
    private int count ;

    private static final int DEFAULT_CATE = 1;
    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_COUNT = 20;

    public QualityVideosOverview(){
        this(DEFAULT_CATE,DEFAULT_PAGE,DEFAULT_COUNT);
    }

    public QualityVideosOverview(int cate, int page, int count){
        this.cate = cate == 0 ? DEFAULT_CATE : cate;
        this.page = page == 0 ? DEFAULT_PAGE : page;
        this.count = count == 0 ? DEFAULT_COUNT : count;
    }

    @Override
    public String getUrl() {
        return YouKuApiCenter.QUALITY_VIDEOS_URL;
    }

    @Override
    public int getMethod() {
        return Request.Method.GET;
    }

    @Override
    public HashMap<String, String> getParams() {
        HashMap map = new HashMap();
        map.put("client_id","" + YouKuHelper.YK_CENTER_ID);
        map.put("cate","" + this.cate);
        map.put("page","" + this.page);
        map.put("count","" + this.count);
        return map ;
    }

    @Override
    public HashMap<String, String> getHead() {
        return new HashMap();
    }
}
