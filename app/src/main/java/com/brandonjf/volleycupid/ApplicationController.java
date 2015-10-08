package com.brandonjf.volleycupid;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by brandon on 9/30/15.
 */
public class ApplicationController extends Application {
    public static final String TAG = ApplicationController.class.getName();
    private static ApplicationController mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private ImageLoader.ImageCache mImageCache;

    public static synchronized ApplicationController getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mImageCache = new ImageLoader.ImageCache() {
            private LruCache<String, Bitmap> cache = new LruCache<>((int) (Runtime.getRuntime().maxMemory() / 1024) / 8);
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        };
        mInstance = this;

    }

    public RequestQueue getRequestQueue(){
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
            mImageLoader = new ImageLoader(mRequestQueue, mImageCache);
        }
        return mRequestQueue;
    }

    public ImageLoader getmImageLoader(){
        return mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancel(){
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(TAG);
        }
    }

}
