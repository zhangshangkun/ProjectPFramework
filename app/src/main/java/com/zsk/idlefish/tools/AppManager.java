package com.zsk.idlefish.tools;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *@作者 zsk
 *创建时间 2019/8/5 10:47
 *描述  activity 管理类
*/
public class AppManager {

    /**
     * Actvity存储栈
     */
    private static Stack<Activity> mActivityStack;

    /**
     * AppManager单例对象
     */
    private static volatile AppManager mAppManager;

    private AppManager() {
        if (mActivityStack == null) {
            mActivityStack = new Stack<Activity>();
        }
    }
    /**
     * 单一实例
     */
    public static AppManager getInstance() {
        if (mAppManager == null) {
            synchronized (AppManager.class) {
                if (mAppManager == null)
                    mAppManager = new AppManager();
            }

        }
        return mAppManager;
    }

    /**
     * 获取栈顶Activity（堆栈中最后一个压入的）
     */
    public AppCompatActivity currentActivity() {
        AppCompatActivity activity = (AppCompatActivity) mActivityStack.lastElement();
        return activity;
    }


    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (mActivityStack == null) {
            mActivityStack = new Stack<Activity>();
        }
        mActivityStack.add(activity);
    }

    /**
     * 获取栈顶Activity（堆栈中最后一个压入的）
     */
    public Activity getTopActivity() {
        Activity activity = mActivityStack.lastElement();
        return activity;
    }

    /**
     * 结束栈顶Activity（堆栈中最后一个压入的）
     */
    public void killTopActivity() {
        Activity activity = mActivityStack.lastElement();
        killActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void killActivity(Activity activity) {
        if (activity != null) {
            mActivityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public synchronized void killActivity(Class<?>... calsses) {

        if (mActivityStack == null || mActivityStack.isEmpty())
            return;

        List<Activity> activities = new ArrayList<>();

        for (Class cls : calsses) {
            for (Activity activity : mActivityStack) {
                if (activity.getClass().equals(cls)) {
                    activities.add(activity);
                }
            }
        }

        for (Activity activity : activities) {
            killActivity(activity);
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public synchronized void killOtherExitThisActivity(Class calsses) {

        if (mActivityStack == null || mActivityStack.isEmpty())
            return;

        for (int i = 0, size = mActivityStack.size(); i < size; i++) {
            if (null != mActivityStack.get(i)) {
                if (!mActivityStack.get(i).getClass().equals(calsses))
                    mActivityStack.get(i).finish();
            }
        }

    }

    /**
     * 结束所有Activity
     */
    public void killAllActivity() {
        if (mActivityStack == null || mActivityStack.isEmpty()) {
            return;
        }
        for (int i = 0, size = mActivityStack.size(); i < size; i++) {
            if (null != mActivityStack.get(i)) {
                mActivityStack.get(i).finish();
            }
        }
        mActivityStack.clear();
    }

    /**
     * 结束除了当前的其他所有Activity
     * @param activity
     */
    public void killOthersActivity(Activity activity) {
        if (activity == null) {
            return;
        }
        for (int i = 0, size = mActivityStack.size(); i < size; i++) {
            if (null != mActivityStack.get(i) && activity != mActivityStack.get(i)) {
                mActivityStack.get(i).finish();
            }
        }
        mActivityStack.clear();
        mActivityStack.add(activity);
    }

    /**
     * 判断Activity是否存在
     * @param className
     * @return
     */
    public boolean existActivity(String className) {
        Activity activity = getActivityByName(className);
        if (activity != null && !activity.isFinishing()) {
            return true;
        }
        return false;
    }
    /**
     * 根据名字查找Activity
     * @param className
     * @return
     */
    public Activity getActivityByName(String className) {
        Activity activity = null;
        for (int i = 0, size = mActivityStack.size(); i < size; i++) {
            if (null != mActivityStack.get(i)) {
                if (mActivityStack.get(i).getClass().getName().equals(className)) {
                    activity = mActivityStack.get(i);
                }
            }
        }
        return activity;
    }

    /**
     * 删除并结束掉Activity
     * @param activity
     */
    public void finishActivity(Activity activity) {

        int pos = -1;
        if (activity != null && mActivityStack != null) {
            for (int i = 0, size = mActivityStack.size(); i < size; i++) {
                if (null != mActivityStack.get(i)) {
                    if (activity == mActivityStack.get(i)) {
                        pos = i;
                        activity.finish();
                    }
                }
            }
            if (pos != -1) {
                mActivityStack.remove(pos);
            }
        }
    }

    /**
     * 从栈里删除activity
     * @param activity
     */
    public void remove(Activity activity) {
        if (activity != null && mActivityStack != null) {
            mActivityStack.remove(activity);
        }
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            killAllActivity();
            ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {

        }
    }
    public static boolean dynamicAuthority(Activity abActivity, String permission, int returnValue) {
        if (ContextCompat.checkSelfPermission(abActivity, permission)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(abActivity,
                    new String[]{permission}, returnValue);
            return false;
        } else {
            return true;
        }
    }
    public static Bitmap getSmallBitmap(String filePaht) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePaht, options);
        options.inSampleSize = calculateInSampleSize(options, 480, 800);
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePaht, options);
    }
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }
    public static void compressBmpToFile(Bitmap bmp, File file) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options = 80;//个人喜欢从80开始,
        bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
        while (baos.toByteArray().length / 1024 > 100) {
            baos.reset();
            options -= 10;
            bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
