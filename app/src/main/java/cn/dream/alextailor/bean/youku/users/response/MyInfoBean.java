package cn.dream.alextailor.bean.youku.users.response;

/**
 * Created by alexadapter on 2015/9/30.
 */
public class MyInfoBean {
    /**
     * 用户ID
     * */
    private int id;
    /**
     * 用户名
     * */
    private String name;
    /**
     * 用户地址
     * */
    private String link;
    /**
     * 头像
     * */
    private String avatar;
    /**
     * 大头像
     * */
    private String avatar_large;
    /**
     * 性别
     * */
    private String gender;
    /**
     * 性别
     * */
    private String description;
    /**
     * 总视频数
     * */
    private String videos_count;
    /**
     * 总专辑数
     * */
    private String playlists_count;
    /**
     * 总收藏视频数
     * */
    private String favorites_count;
    /**
     * 粉丝数
     * */
    private String followers_count;
    /**
     * 关注数
     * */
    private String following_count;
    /**
     * 动态数
     * */
    private String statuses_count;
    /**
     * 被订阅数
     * */
    private String subscribe_count;
    /**
     * 总视频播放数
     * */
    private String vv_count;
    /**
     * 注册时间
     * */
    private String regist_time;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getAvatar_large() {
        return avatar_large;
    }

    public String getGender() {
        return gender;
    }

    public String getDescription() {
        return description;
    }

    public String getVideos_count() {
        return videos_count;
    }

    public String getPlaylists_count() {
        return playlists_count;
    }

    public String getFavorites_count() {
        return favorites_count;
    }

    public String getFollowers_count() {
        return followers_count;
    }

    public String getFollowing_count() {
        return following_count;
    }

    public String getStatuses_count() {
        return statuses_count;
    }

    public String getSubscribe_count() {
        return subscribe_count;
    }

    public String getVv_count() {
        return vv_count;
    }

    public String getRegist_time() {
        return regist_time;
    }
}
