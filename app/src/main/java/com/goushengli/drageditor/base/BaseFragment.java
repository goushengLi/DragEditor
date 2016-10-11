package com.goushengli.drageditor.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by goushengLi on 2016/10/10.
 */

public abstract class BaseFragment extends Fragment {
    private Context mContext = getActivity();

    public Context getContext() {
        if (mContext != null) {
            return mContext;
        }
        return getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return createView(inflater, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    public abstract View createView(LayoutInflater inflater, @Nullable Bundle savedInstanceState);

    public abstract void initData();
}
