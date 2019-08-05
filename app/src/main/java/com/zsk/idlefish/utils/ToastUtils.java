package com.zsk.idlefish.utils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;
import com.zsk.idlefish.tools.AppManager;

public class ToastUtils {
    private static Toast toast;
    private static Context mContext;
    static {
        mContext = Utils.getContext();
    }
    /**
     * 短时间显示Toast
     * @param info 显示的内容
     */
    public static void showNormal(String info) {
        if (TextUtils.isEmpty(info)) {
            return;
        }
        Activity current = AppManager.getInstance().currentActivity();
        if (toast == null) {
            if (current == null)
                toast = Toast.makeText(current, info, Toast.LENGTH_SHORT);
            else {
                if (mContext == null) {
                    throw new NullPointerException("You have to call static method init() first in Applicaiton");
                } else
                    toast = Toast.makeText(mContext, info, Toast.LENGTH_SHORT);
            }
        } else {
            toast.setText(info);
        }
        toast.setGravity(Gravity.CENTER, 20, 20);
        toast.show();
    }

    /**
     * 长时间显示Toast
     * @param info 显示的内容
     */
    public static void showLongToast(String info) {
        if (TextUtils.isEmpty(info)) {
            return;
        }
        if (toast == null) {
            Activity current = AppManager.getInstance().currentActivity();
            if (current == null)
                toast = Toast.makeText(current, info, Toast.LENGTH_LONG);
            else {
                if (mContext == null) {
                    throw new NullPointerException("You have to call static method init() first in Applicaiton");
                } else
                    toast = Toast.makeText(mContext, info, Toast.LENGTH_LONG);
            }

        } else {
            toast.setText(info);
        }
        toast.setGravity(Gravity.CENTER, 20, 20);
        toast.show();
    }
}
