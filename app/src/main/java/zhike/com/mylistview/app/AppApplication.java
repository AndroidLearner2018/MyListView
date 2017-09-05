package zhike.com.mylistview.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;

import android.util.Log;
import android.widget.Toast;



import org.xutils.x;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

//import com.alibaba.sdk.android.BaseAlibabaSDK;
//import com.alibaba.sdk.android.callback.InitResultCallback;
//import com.facebook.drawee.backends.pipeline.Fresco;
//import com.alipay.sdk.app.EnvUtils;

public class AppApplication extends Application {
    public int count = 0;
    private static Context sContext;

    public static AppApplication mAppApplication = null;
    public static boolean isAppOnForeground;

    public static Context getContext() {

        return sContext;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();

        sContext = this;
        mAppApplication = this;

        x.Ext.init(this);



    }

}

