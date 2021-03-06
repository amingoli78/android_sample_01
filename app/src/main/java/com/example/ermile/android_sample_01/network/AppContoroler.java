package com.example.ermile.android_sample_01.network;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class AppContoroler extends Application {

    private static final String testAppKey = "aniakfmprbqbbsnbnpiecdqtrdfshojhtlancfqblppccptbtrbisfsqjjtokclcroleck";

    public static final String videoZoneId = "5b19f12d0399ee0001b0a4aa";



    public static final String TAG = AppContoroler.class
            .getSimpleName();

    private RequestQueue mRequestQueue;

    private static AppContoroler mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized AppContoroler getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }


    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}