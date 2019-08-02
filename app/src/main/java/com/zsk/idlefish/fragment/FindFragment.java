package com.zsk.idlefish.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zsk.idlefish.R;
import com.zsk.idlefish.base.BaseFragment;


public class FindFragment extends BaseFragment {

    public static FindFragment newInstance() {
        FindFragment fragment = new FindFragment();
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_find;
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
