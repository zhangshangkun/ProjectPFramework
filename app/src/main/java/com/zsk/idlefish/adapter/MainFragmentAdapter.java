package com.zsk.idlefish.adapter;



import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.zsk.idlefish.base.BaseFragment;

import java.util.List;

/**
 * Created by Administrator on 2017/9/11.
 */

public class MainFragmentAdapter extends FragmentStatePagerAdapter {
    private List<BaseFragment> list;
    public MainFragmentAdapter(FragmentManager fm, List<BaseFragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

}
