package com.mzwy.pinhu;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.karumi.dexter.Dexter;
import com.tencent.smtt.sdk.QbSdk;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhang on 2016/10/11.
 * 创建app
 */
public class MyApplication extends Application {

    private static MyApplication instance;

    public static MyApplication getInstance() {

        if (instance == null) {
            instance = new MyApplication();
            return instance;
        }
        return instance;
    }


    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
        Context context = getApplicationContext();
        Dexter.initialize(context);

        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(),  cb);
    }


    private static List<Activity> listActivity = new ArrayList<>();

    public static void setListActivity(Activity activity) {
        listActivity.add(activity);
    }

    public static void finishActivity() {

        for (Activity activity : listActivity
                ) {
            activity.finish();
        }
    }

    public static void finishLastActivity() {
        if (listActivity.size() > 0) {
            listActivity.get(listActivity.size() - 1).finish();
        }
    }


}
