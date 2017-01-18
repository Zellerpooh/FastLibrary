package com.zeller.fastlibrary.searchhistory.model;

import android.content.Context;

import com.zeller.fastlibrary.searchhistory.SearchHistoryModel;

import java.util.ArrayList;

/**
 * Created by Zellerpooh on 17/1/18.
 */

public interface SearchModel {


    void save(String value);

    void search(String value,OnSearchListener onSearchListener);

    void remove(String key);

    void clear();

    void sortHistory(OnSearchListener onSearchListener);
}
