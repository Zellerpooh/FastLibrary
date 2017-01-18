package com.zeller.fastlibrary.searchhistory;

import android.content.Context;

import java.security.Key;
import java.util.ArrayList;
import java.util.Map;

/**
 * 历史信息存储基类
 * Created by Zellerpooh on 17/1/18.
 */

public abstract class BaseHistoryStorage {
    /**
     * 保存历史记录时调用
     *
     * @param value
     */
    public void save(String value) {
        Map<String, String> historys = (Map<String, String>) getAll();
        for (Map.Entry<String, String> entry : historys.entrySet()) {
            if (value.equals(entry.getValue())) {
                remove(entry.getKey());
            }
        }
        put(generateKey(), value);
    }

    public abstract void put(String key, String value);

    public abstract boolean contains(String key);

    public abstract Map<String, ?> getAll();

    public abstract void remove(String key);

    public abstract void clear();

    /**
     * 生成key
     *
     * @return
     */
    public abstract String generateKey();

    /**
     * 返回排序好的历史记录
     *
     * @param allHistory
     * @return
     */
    public abstract ArrayList<SearchHistoryModel> sortHistory(Map<String, ?> allHistory);
}
