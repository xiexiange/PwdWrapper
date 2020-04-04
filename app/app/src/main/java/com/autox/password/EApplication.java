package com.autox.password;

import android.app.Application;
import android.content.Context;

import com.autox.base.BaseUtil;
import com.autox.base.PrefUtil;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.stat.StatConfig;
import com.tencent.stat.StatService;

public class EApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        PrefUtil.getInstance().init(this);
        CrashReport.initCrashReport(getApplicationContext(), "b57fe81771", BuildConfig.DEBUG);
        StatConfig.setDebugEnable(BuildConfig.DEBUG);
        StatService.registerActivityLifecycleCallbacks(this);
        BaseUtil.getInstance().setImpl(new BaseUtil.IImpl() {
            @Override
            public Context getContext() {
                return EApplication.this;
            }

            @Override
            public String getApplicationID() {
                return BuildConfig.APPLICATION_ID;
            }
        });
    }

    public static Context getContext() {
        return mContext;
    }
}
