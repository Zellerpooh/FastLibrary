package com.zeller.fastlibrary.searchhistory;

/**
 * Created by Zellerpooh on 17/1/18.
 */

public interface OnSearchHistoryListener {
    void onDelete(String key);

    void onSelect(String content);
}
