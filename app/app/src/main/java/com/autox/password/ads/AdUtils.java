package com.autox.password.ads;

import com.autox.password.BuildConfig;

public class AdUtils {
    public static class MI_AD {
        public static String getAppId() {
            String app_id = "2882303761518335559";
            if (BuildConfig.DEBUG) {
                app_id = "2882303761517518052";
            }
            return app_id;
        }
    }
}
