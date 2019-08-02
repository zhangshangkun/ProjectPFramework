package com.zsk.idlefish.fragment;

import android.os.Bundle;
import android.view.View;

import com.zsk.idlefish.R;
import com.zsk.idlefish.base.BaseFragment;


public class MineFragment extends BaseFragment {
    public static MineFragment newInstance(){
        MineFragment mineFragment=new MineFragment();
        return mineFragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
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
