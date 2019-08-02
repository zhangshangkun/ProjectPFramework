package com.zsk.idlefish.fragment;
import android.os.Bundle;
import android.view.View;
import com.zsk.idlefish.R;
import com.zsk.idlefish.base.BaseFragment;

public class MessageFragment extends BaseFragment {

    public static MessageFragment newInstance() {
        MessageFragment fragment = new MessageFragment();
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
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
