package cn.dream.alextailor.activitys;

import android.support.v7.app.AppCompatActivity;

import com.dream.common.api.RequestManager;

/**
 * Created by alexadapter on 2015/9/30.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RequestManager.getInstance(this).cancelAll(this);
    }
}
