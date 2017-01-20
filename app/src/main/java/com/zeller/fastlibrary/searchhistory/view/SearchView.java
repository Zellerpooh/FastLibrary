package com.zeller.fastlibrary.searchhistory.view;

import com.zeller.fastlibrary.searchhistory.bean.SearchHistoryModel;

import java.util.ArrayList;

/**
 * Created by Zellerpooh on 17/1/18.
 */

public interface SearchView {
    void showHistories(ArrayList<SearchHistoryModel> results);

    void searchSuccess(String value);
}
