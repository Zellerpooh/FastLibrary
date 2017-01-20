package com.zeller.fastlibrary.searchhistory.storage;

import com.zeller.fastlibrary.searchhistory.bean.SearchHistoryModel;

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
    public abstract void save(String value);

    /**
     * 移除单条历史记录
     *
     * @param key
     */
    public abstract void remove(String key);

    /**
     * 清空历史记录
     */
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
     * @return
     */
    public abstract ArrayList<SearchHistoryModel> sortHistory();
}
