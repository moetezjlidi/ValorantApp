package com.example.myapplication;

import android.app.Activity;
import android.app.Application;

public class MyApp extends Application {
    private Activity mCurrentActivity = null;
    @Override
    public void onCreate() {
        super.onCreate();
        //Parse SDK stuff goes here
    }
    public Activity getCurrentActivity () {
        return mCurrentActivity ;
    }
    public void setCurrentActivity (Activity mCurrentActivity) {
        this . mCurrentActivity = mCurrentActivity ;
    }
}
