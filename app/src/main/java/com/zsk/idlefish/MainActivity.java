package com.zsk.idlefish;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import com.zsk.idlefish.adapter.MainFragmentAdapter;
import com.zsk.idlefish.base.BaseActivity;
import com.zsk.idlefish.fragment.HomeFragment;
import com.zsk.idlefish.view.NoScrollViewPager;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.home)
    RadioButton home;
    @BindView(R.id.find)
    RadioButton find;
    @BindView(R.id.plus)
    RadioButton plus;
    @BindView(R.id.message)
    RadioButton message;
    @BindView(R.id.mine)
    RadioButton mine;
    @BindView(R.id.vp_main_page)
    NoScrollViewPager viewPager;
    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        home.setOnClickListener(this);
        find.setOnClickListener(this);
        plus.setOnClickListener(this);
        message.setOnClickListener(this);
        mine.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(HomeFragment.newInstance());
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
            case R.id.plus:
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
