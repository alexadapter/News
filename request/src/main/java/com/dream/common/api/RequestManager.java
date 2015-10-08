package com.dream.common.api;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;

import java.io.File;


/**
 * @author Issac
 * @date 7/18/13
 *
 * 1. bean(method,url,params,heads)
 * 2. requestManager(post)
 * 3. deliveryCenter(bean+post)
 */
public class RequestManager {

    private static RequestManager instance;
    private Context context;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private DiskBasedCache mDiskCache;

    private int MEM_CACHE_SIZE;

    public static synchronized RequestManager getInstance(Context context) {
        if (instance == null) {
            instance = new RequestManager(context,false);
        }
        return instance;
    }

    public RequestManager(Context context, boolean isHttps) {
        this.context = context.getApplicationContext();
        MEM_CACHE_SIZE = 1024 * 1024
                * ((ActivityManager) this.context.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass() / 6;

        mRequestQueue = new RequestQueue(
                new DiskBasedCache(this.context.getCacheDir(), 1024 * 1024 * 10),
                new BasicNetwork(new HurlStack()));

        mRequestQueue.start();
        mDiskCache = ((DiskBasedCache) mRequestQueue.getCache());
        BitmapLruCache mLruCache = new BitmapLruCache(MEM_CACHE_SIZE);

        mImageLoader = new ImageLoader(mRequestQueue, mLruCache);
    }


    public void executeRequest(Request request, Object tag) {
        if (tag != null) {
            request.setTag(tag);
        }
        mRequestQueue.add(request);
    }

    public void cancelAll(Object tag) {
        mRequestQueue.cancelAll(tag);
    }

    public File getCachedImageFile(String url) {
        return mDiskCache.getFileForKey(url);
    }

    public Bitmap getCachedBitmap(String url) {
        return mImageLoader.getCacheBitmap(url,0,0);
    }


    public ImageLoader.ImageContainer loadImage(String requestUrl,
                                                ImageLoader.ImageListener imageListener) {
        return loadImage(requestUrl, imageListener, 0, 0);
    }

    public ImageLoader.ImageContainer loadImage(String requestUrl,
                                                ImageLoader.ImageListener imageListener,
                                                int maxWidth, int maxHeight) {
        return mImageLoader.get(requestUrl, imageListener, maxWidth, maxHeight);
    }

    public ImageLoader.ImageListener getImageListener(final ImageView view,
                                                      final Drawable defaultImageDrawable,
                                                      final Drawable errorImageDrawable) {
        return new ImageLoader.ImageListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (errorImageDrawable != null) {
                    view.setImageDrawable(errorImageDrawable);
                }
            }

            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                if (response.getBitmap() != null) {
                    if (!isImmediate && defaultImageDrawable != null) {
                        TransitionDrawable transitionDrawable = new TransitionDrawable(
                                new Drawable[]{
                                        defaultImageDrawable,
                                        new BitmapDrawable(context.getResources(),
                                                response.getBitmap())
                                }
                        );
                        transitionDrawable.setCrossFadeEnabled(true);
                        view.setImageDrawable(transitionDrawable);
                        transitionDrawable.startTransition(100);
                    } else {
                        view.setImageBitmap(response.getBitmap());
                    }
                } else if (defaultImageDrawable != null) {
                    view.setImageDrawable(defaultImageDrawable);
                }
            }
        };
    }
}
