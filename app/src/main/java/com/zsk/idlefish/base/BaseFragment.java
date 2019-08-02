package com.zsk.idlefish.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import butterknife.ButterKnife;
import butterknife.Unbinder;
/**
 *@作者 zsk
 *创建时间 2019/8/2 15:44
 *描述
*/
public abstract class BaseFragment extends Fragment {
    public View convertView;
    private boolean isVisible = false;
    private boolean isInitView = false;
    private boolean isFirstLoad = true;
    public Activity mContext;
    private Unbinder mUnbind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (convertView == null) {
            convertView = inflater.inflate(getLayoutId(), container, false);
            mUnbind = ButterKnife.bind(this, convertView);
            mContext=getActivity();
            initView(convertView, savedInstanceState);
            isInitView = true;
            initData();
            lazyLoadData();
            EventBus.getDefault().register(this);
        }
        ViewGroup parent = (ViewGroup) convertView.getParent();
        if (parent != null) {
            parent.removeView(convertView);
        }
        return convertView;
    }
    /**
     * 加载页面布局ID
     */
    protected  abstract int getLayoutId();
    /**
     * 初始化控件
     */
    protected abstract void initView(View convertView, Bundle savedInstanceState);
    /**
     * 初始化数据
     */
    protected abstract void initData();
    /**
     * 获取网络数据
     */
    protected abstract void getData();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            isVisible = true;
            lazyLoadData();
        } else {
            isVisible = false;
        }
        super.setUserVisibleHint(isVisibleToUser);
    }
    private void lazyLoadData() {
        if (!isFirstLoad || !isVisible || !isInitView) {
            return;
        }
        getData();
        isFirstLoad = false;
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            isVisible = true;
            lazyLoadData();
        } else {
            isVisible = false;
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbind.unbind();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String event) {

    }
}
