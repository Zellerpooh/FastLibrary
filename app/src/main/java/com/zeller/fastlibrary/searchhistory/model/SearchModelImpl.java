package com.zeller.fastlibrary.searchhistory.model;

import android.content.Context;

import com.zeller.fastlibrary.searchhistory.storage.BaseHistoryStorage;
import com.zeller.fastlibrary.searchhistory.storage.DbHistoryStorage;
import com.zeller.fastlibrary.searchhistory.storage.SpHistoryStorage;

/**
 * Created by Zellerpooh on 17/1/18.
 */

public class SearchModelImpl implements SearchModel {

    private BaseHistoryStorage historyStorage;

    public SearchModelImpl(Context context, int historyMax) {
//        historyStorage = SpHistoryStorage.getInstance(context, historyMax);
        historyStorage = DbHistoryStorage.getInstance(context, historyMax);
    }

    @Override
    public void save(String value) {
        historyStorage.save(value);
    }

    @Override
    public void search(String value, OnSearchListener onSearchListener) {
        onSearchListener.searchSuccess(value);
    }

    @Override
    public void remove(String key) {
        historyStorage.remove(key);
    }

    @Override
    public void clear() {
        historyStorage.clear();
    }

    @Override
    public void sortHistory(OnSearchListener onSearchListener) {
        onSearchListener.onSortSuccess(historyStorage.sortHistory());
    }
}
