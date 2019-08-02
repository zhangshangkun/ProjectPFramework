package com.zsk.idlefish.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zsk.idlefish.R;
import com.zsk.idlefish.base.BaseFragment;

public class HomeFragment extends BaseFragment {
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View convertView, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getData() {

    }
}
