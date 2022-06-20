/*
 * *
 *  * Created by prady on 6/8/22, 11:14 PM
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 6/8/22, 11:14 PM
 *
 */

package com.prady.mvvm.template.ui;

import android.app.Application;

import com.prady.mvvm.template.helpers.PreferenceManager;


public class MyApplication extends Application {

    private static MyApplication mContext;

    public static MyApplication getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        PreferenceManager.init(this);
    }
}