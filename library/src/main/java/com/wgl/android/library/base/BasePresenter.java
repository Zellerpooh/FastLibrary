package com.wgl.android.library.base;

import android.content.Context;

import com.wgl.android.library.baserx.RxManager;


public abstract class BasePresenter<T, E> {
    private Context mContext;
    private E mModel;
    private T mView;
    private RxManager mRxManage = new RxManager();


    public void inject(Context context, T view, E model) {
        mContext = context;
        mView = view;
        mModel = model;
        onStart();
    }

    public void inject(T view, E model) {
        mView = view;
        mModel = model;
        onStart();
    }

    public Context getContext() {
        return mContext;
    }

    public E getModel() {
        return mModel;
    }

    public T getView() {
        return mView;
    }

    public RxManager getRxManage() {
        return mRxManage;
    }

    public void onStart() {
    }

    public void onDestroy() {
        mRxManage.clear();
    }
}
