package com.zeller.fastlibrary.searchhistory;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Zellerpooh on 17/1/18.
 */

public class SearchManager {

    //模糊搜索
    private static final int MSG_SEARCH = 1;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //搜索请求
            //search(String.valueOf(mEditTextSearch.getText()));
        }
    };
    private Context mContext;
    private BaseHistoryStorage historyStorage;

    public void SearchManager() {

    }
}
