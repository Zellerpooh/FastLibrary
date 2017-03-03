package com.zeller.fastlibrary.dagger2;

import javax.inject.Inject;

/**
 * Created by Zellerpooh on 17/2/3.
 */

public class Poetry {
    private String mPemo;

    // 用Inject标记构造函数,表示用它来注入到目标对象中去
    @Inject
    public Poetry() {
        mPemo = "生活就像海洋";
    }

    public Poetry(String mPemo) {
        this.mPemo = mPemo;
    }

    public String getmPemo() {
        return mPemo;
    }
}
