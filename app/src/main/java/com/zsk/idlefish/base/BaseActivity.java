package com.zsk.idlefish.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;
import com.zsk.idlefish.R;
import com.zsk.idlefish.tools.AppManager;
import com.zsk.idlefish.tools.NetChangeObserver;
import com.zsk.idlefish.tools.NetStateReceiver;
import com.zsk.idlefish.utils.NetStatusUtil;
import com.zsk.idlefish.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends RxAppCompatActivity {
    protected Activity mContext;
    private Unbinder mUnbind;
    public boolean mIsFirstShow = true;
    private TextView mTitle;
    private LinearLayout submit;
    private TextView mSubmitTv;
    private View mBg;
     //网络观察者
    protected NetChangeObserver mNetChangeObserver = null;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        AppManager.getInstance().addActivity(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getLayoutId());
        mUnbind = ButterKnife.bind(this);
        initView(savedInstanceState);
        initData();
        EventBus.getDefault().register(this);
        mNetChangeObserver = new NetChangeObserver() {
            @Override
            public void onNetConnected(NetStatusUtil.NetState type) {
                onNetworkConnected(type);
            }

            @Override
            public void onNetDisConnect() {
                onNetworkDisConnected();
            }
        };
        //开启广播去监听 网络 改变事件
        NetStateReceiver.registerObserver(mNetChangeObserver);
    }
    /**
     * 网络连接状态
     * @param type 网络状态
     */
    protected void onNetworkConnected(NetStatusUtil.NetState type) {

    }
    /**
     * 网络断开的时候调用
     */
    protected void onNetworkDisConnected() {
        ToastUtils.showNormal("当前未连接到网络");
    }

    //初始化标题栏的信息
    public void initActionBar(CharSequence message) {
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            // 处理返回键
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // 标题
        mTitle =  findViewById(R.id.title_text);
        mTitle.setText(message);
        submit =   findViewById(R.id.submitButton);
        mSubmitTv = findViewById(R.id.submitText);
        mBg =  findViewById(R.id.main_header_1);

    }
    //传入导航栏的背景颜色
    public void initBackgroundColor(int RGB){
        mBg.setBackgroundColor(RGB);
    }

    //初始化submit的文字信息
    public void initSubmitButton(CharSequence message){
        submit.setVisibility(View.VISIBLE);
        mSubmitTv.setText(message);
    }
    //得到actionbar的提交按钮
    public LinearLayout setSubmitListener() {
        return submit;
    }
    protected void T(String msg) {
        ToastUtils.showNormal(msg);
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
        AppManager.getInstance().remove(this);
        mUnbind.unbind();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String event) {

    }
}

