package com.zeller.fastlibrary.searchhistory.model;

import com.zeller.fastlibrary.searchhistory.SearchHistoryModel;

import java.util.ArrayList;

/**
 * Created by Zellerpooh on 17/1/18.
 */

public interface OnSearchListener {
    void onSortSuccess(ArrayList<SearchHistoryModel> results);

    void searchSuccess(String value);
}
