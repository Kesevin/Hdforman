package com.dgg.hdforeman.mvp.ui.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.components.support.RxFragment;

import org.simple.eventbus.EventBus;

import butterknife.ButterKnife;


public abstract class BaseFragment extends RxFragment{

    protected Context mContext;
    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        mContext = getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (needBindEvent()) {
            EventBus.getDefault().register(this);
        }

    }

    protected boolean needBindEvent() {
        return false;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (view != null) {
            ButterKnife.bind(this, view);
        }
        initView();
    }
    protected abstract void initView();

    protected void readyGo(Class<?> pClass) {
        startActivity(new Intent(getActivity(), pClass));
    }



    protected void readyGo(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(getActivity(), pClass);
        if (null != pBundle) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }
    protected void readyGo(Class<?> pClass, int requestCode) {
        Intent intent = new Intent(getActivity(), pClass);
        startActivityForResult(intent, requestCode);
    }
    protected void readyGo(Class<?> pClass, Bundle pBundle, int requestCode) {
        Intent intent = new Intent(getActivity(), pClass);
        if (null != pBundle) {
            intent.putExtras(pBundle);
        }
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void onDestroyView() {
        if (needBindEvent()) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroyView();
    }

}
