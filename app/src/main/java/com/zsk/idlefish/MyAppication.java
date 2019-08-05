package com.zsk.idlefish;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

import com.zsk.idlefish.tools.NetStateReceiver;
import com.zsk.idlefish.utils.Utils;

public class MyAppication extends Application {
    public static Context mContext;
    private static MyAppication context;
    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
        context = this;
        mContext = getApplicationContext();
        Utils.init(this);
        /*开启网络广播监听*/
        NetStateReceiver.registerNetworkStateReceiver(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        NetStateReceiver.unRegisterNetworkStateReceiver(this);
    }
    //全局获取ApplicationContext
    public static MyAppication getApp() {
        return context;
    }

}
