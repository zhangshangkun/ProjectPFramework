package com.zsk.idlefish.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zsk.idlefish.R;


public class MineFragment extends Fragment {
    public static MineFragment newInstance(){
        MineFragment mineFragment=new MineFragment();
        Bundle args = new Bundle();
        mineFragment.setArguments(args);
        return mineFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }
}
