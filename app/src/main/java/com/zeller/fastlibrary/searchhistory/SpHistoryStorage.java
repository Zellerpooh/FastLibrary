package com.zeller.fastlibrary.searchhistory;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * Created by Zellerpooh on 17/1/18.
 */

public class SpHistoryStorage extends BaseHistoryStorage {

    private Context context;
    public static final String SEARCH_HISTORY = "search_history";
    private static SimpleDateFormat mFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private int HISTORY_MAX = 5;

    public SpHistoryStorage(Context context, int historyMax) {
        this.context = context;
        this.HISTORY_MAX = historyMax;
    }

    @Override
    public void put(String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(SEARCH_HISTORY,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    @Override
    public boolean contains(String key) {
        SharedPreferences sp = context.getSharedPreferences(SEARCH_HISTORY,
                Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    @Override
    public Map<String, ?> getAll() {
        SharedPreferences sp = context.getSharedPreferences(SEARCH_HISTORY,
                Context.MODE_PRIVATE);
        return sp.getAll();
    }

    @Override
    public void remove(String key) {
        SharedPreferences sp = context.getSharedPreferences(SEARCH_HISTORY,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.commit();
    }

    @Override
    public void clear() {
        SharedPreferences sp = context.getSharedPreferences(SEARCH_HISTORY,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

    @Override
    public String generateKey() {
        return mFormat.format(new Date());
    }

    @Override
    public ArrayList<SearchHistoryModel> sortHistory(Map<String, ?> allHistory) {
        ArrayList<SearchHistoryModel> mResults = new ArrayList<>();
        Map<String, String> hisAll = (Map<String, String>) getAll();
        //将key排序升序
        Object[] keys = hisAll.keySet().toArray();
        Arrays.sort(keys);
        int keyLeng = keys.length;
        //这里计算 如果历史记录条数是大于 可以显示的最大条数，则用最大条数做循环条件，防止历史记录条数-最大条数为负值，数组越界
        int hisLeng = keyLeng > HISTORY_MAX ? HISTORY_MAX : keyLeng;
        for (int i = 1; i <= hisLeng; i++) {
            mResults.add(new SearchHistoryModel((String) keys[keyLeng - i], hisAll.get(keys[keyLeng - i])));
        }
        return mResults;
    }

}
