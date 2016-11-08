package com.example.nayan.newmybdreceipetest.activity.support;

import android.app.Application;
import android.content.Context;

import com.example.nayan.newmybdreceipetest.model.MCategory;
import com.example.nayan.newmybdreceipetest.model.MReceipe;
import com.jewel.dbmanager.DBManager;

/**
 * Created by NAYAN on 11/6/2016.
 */
public class MyApp extends Application {
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        DBManager.init(context);
        DBManager.createTable(MCategory.class);
        DBManager.createTable(MReceipe.class);
    }
}
