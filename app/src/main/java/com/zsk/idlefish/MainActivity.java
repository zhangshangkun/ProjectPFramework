package com.zsk.idlefish;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.zsk.idlefish.adapter.MainFragmentAdapter;
import com.zsk.idlefish.base.BaseActivity;
import com.zsk.idlefish.base.BaseFragment;
import com.zsk.idlefish.fragment.FindFragment;
import com.zsk.idlefish.fragment.HomeFragment;
import com.zsk.idlefish.fragment.MessageFragment;
import com.zsk.idlefish.fragment.MineFragment;
import com.zsk.idlefish.fragment.ReleaseFragment;
import com.zsk.idlefish.view.NoScrollViewPager;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.home)
    RadioButton home;
    @BindView(R.id.find)
    RadioButton find;
    @BindView(R.id.release)
    RadioButton release;
    @BindView(R.id.message)
    RadioButton message;
    @BindView(R.id.mine)
    RadioButton mine;
    @BindView(R.id.vp_main_page)
    NoScrollViewPager viewPager;
    private List<BaseFragment> mFragments = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        home.setOnClickListener(this);
        find.setOnClickListener(this);
        release.setOnClickListener(this);
        message.setOnClickListener(this);
        mine.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(FindFragment.newInstance());
        mFragments.add(ReleaseFragment.newInstance());
        mFragments.add(MessageFragment.newInstance());
        mFragments.add(MineFragment.newInstance());
        viewPager.setNoScroll(true);
        viewPager.setOffscreenPageLimit(mFragments.size());
        MainFragmentAdapter adapter = new MainFragmentAdapter(getSupportFragmentManager(), mFragments);
        viewPager.setAdapter(adapter);
    }
    @Override
    public void loadData() {

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.home:
                viewPager.setCurrentItem(0);
                break;
            case R.id.find:
                viewPager.setCurrentItem(1);
                break;
            case R.id.release:
                viewPager.setCurrentItem(2);
                break;
            case R.id.message:
                viewPager.setCurrentItem(3);
                break;
            case R.id.mine:
                viewPager.setCurrentItem(4);
                break;
        }
    }
}
