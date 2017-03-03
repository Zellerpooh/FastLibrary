package com.zeller.fastlibrary.searchhistory.presenter;

import android.content.Context;

import com.zeller.fastlibrary.searchhistory.bean.SearchHistoryModel;
import com.zeller.fastlibrary.searchhistory.model.OnSearchListener;
import com.zeller.fastlibrary.searchhistory.model.SearchModel;
import com.zeller.fastlibrary.searchhistory.model.SearchModelImpl;
import com.zeller.fastlibrary.searchhistory.view.SearchView;

import java.util.ArrayList;

/**
 * Created by Zellerpooh on 17/1/18.
 */

public class SearchPresenterImpl implements SearchPresenter, OnSearchListener {
    private static final int historyMax = 5;
    private SearchView searchView;
    private SearchModel searchModel;

    public SearchPresenterImpl(SearchView searchView, Context context) {
        this.searchView = searchView;
        this.searchModel = new SearchModelImpl(context, historyMax);
    }

    //移除历史记录
    @Override
    public void remove(String key) {
        searchModel.remove(key);
        searchModel.sortHistory(this);
    }

    @Override
    public void clear() {
        searchModel.clear();
        searchModel.sortHistory(this);
    }

    //获取所有的历史记录
    @Override
    public void sortHistory() {
        searchModel.sortHistory(this);
    }

    @Override
    public void search(String value) {
        searchModel.save(value);
        searchModel.search(value, this);
    }

    @Override
    public void onSortSuccess(ArrayList<SearchHistoryModel> results) {
        searchView.showHistories(results);
    }

    @Override
    public void searchSuccess(String value) {
        searchView.searchSuccess(value);
    }
}
