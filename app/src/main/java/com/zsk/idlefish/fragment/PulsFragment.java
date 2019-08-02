package com.zsk.idlefish.fragment;

import android.os.Bundle;
import android.view.View;
import com.zsk.idlefish.R;
import com.zsk.idlefish.base.BaseFragment;

/**
 *@作者 zsk
 *创建时间 2019/8/2 15:44
 *描述
*/
public class PulsFragment extends BaseFragment {

    public static PulsFragment newInstance() {
        PulsFragment fragment = new PulsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_puls;
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
