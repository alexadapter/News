package cn.dream.alextailor.bean.youku.quality.response;

import java.util.ArrayList;

/**
 * Created by alexadapter on 2015/9/30.
 */
public class QualityVideosBean {
    private int page;
    private int count;
    private int total;
    private ArrayList<QualityVideoBean> videos;

    public int getPage() {
        return page;
    }

    public int getCount() {
        return count;
    }

    public int getTotal() {
        return total;
    }

    public ArrayList<QualityVideoBean> getVideos() {
        return videos;
    }
}
