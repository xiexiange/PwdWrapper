package com.autox.password;

import android.app.Application;
import android.content.Context;

import com.autox.base.BaseUtil;
import com.autox.base.PrefUtil;
import com.autox.password.ads.AdUtils;
import com.miui.zeus.mimo.sdk.MimoSdk;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.stat.StatService;

import java.util.Properties;

public class EApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        PrefUtil.getInstance().init(this);
        CrashReport.initCrashReport(getApplicationContext(), "b57fe81771", BuildConfig.DEBUG);
        if (!BuildConfig.DEBUG) {
            StatService.registerActivityLifecycleCallbacks(this);
        }
        BaseUtil.getInstance().setImpl(new BaseUtil.IImpl() {
            @Override
            public Context getContext() {
                return EApplication.this;
            }

            @Override
            public String getApplicationID() {
                return BuildConfig.APPLICATION_ID;
            }

            @Override
            public void recordUsage(String key, String value) {
                Properties prop = new Properties();
                prop.setProperty("value", value);
                StatService.trackCustomKVEvent(EApplication.this, key, prop);
            }
        });
        // 小米广告
        MimoSdk.init(this, AdUtils.MI_AD.getAppId()); // false
        // end 小米广告
    }

    public static Context getContext() {
        return mContext;
    }
}
