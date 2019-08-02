package com.zsk.idlefish.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends RxAppCompatActivity {
    protected Activity mContext;
    private Unbinder mUnbind;
    public boolean mIsFirstShow = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getLayoutId());
        mUnbind = ButterKnife.bind(this);
        initView(savedInstanceState);
        initData();
        EventBus.getDefault().register(this);
    }

    /**
     * 加载页面布局ID
     */
    public abstract int getLayoutId();

    /**
     * 初始化控件
     */
    public abstract void initView(Bundle savedInstanceState);

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 数据在onResume上显示
     */
    public abstract void loadData();

    @Override
    protected void onResume() {
        if (mIsFirstShow) {
            mIsFirstShow = false;
            loadData();
        }
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        mUnbind.unbind();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String event) {

    }
}

