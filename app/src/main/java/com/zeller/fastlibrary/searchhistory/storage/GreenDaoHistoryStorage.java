package com.zeller.fastlibrary.searchhistory.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.zeller.fastlibrary.App;
import com.zeller.fastlibrary.greendaoTest.DaoMaster;
import com.zeller.fastlibrary.greendaoTest.DaoSession;
import com.zeller.fastlibrary.searchhistory.bean.SearchHistoryModel;
import com.zeller.fastlibrary.searchhistory.storage.bean.SearchRecord;
import com.zeller.fastlibrary.searchhistory.storage.bean.SearchRecordDao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Zellerpooh on 17/3/3.
 */

public class GreenDaoHistoryStorage extends BaseHistoryStorage {
    private static final String db_name = "search_history";
    private static GreenDaoHistoryStorage mInstance;
    private Context context;
    private DaoSession daoSession;
    private SearchRecordDao searchRecordDao;
    private int default_size = 5;

    private GreenDaoHistoryStorage(Context context, int size) {
        this.context = context;
        this.default_size = size;
        daoSession = App.getDaoSession();
        searchRecordDao = daoSession.getSearchRecordDao();
    }

    /**
     * 获取单例
     *
     * @param context
     * @return
     */
    public static synchronized GreenDaoHistoryStorage getInstance(Context context, int size) {
        if (mInstance == null) {
            synchronized (GreenDaoHistoryStorage.class) {
                if (mInstance == null) {
                    mInstance = new GreenDaoHistoryStorage(context.getApplicationContext(), size);
                }
            }
        }
        return mInstance;
    }

    public void insert(SearchRecord searchRecord) {
        searchRecordDao.insert(searchRecord);
    }

    public void delete(SearchRecord searchRecord) {
        searchRecordDao.delete(searchRecord);
    }

    public List<SearchRecord> queryAll() {
        return searchRecordDao.queryBuilder().orderDesc(SearchRecordDao.Properties.Date).build().list();
    }

    public List<SearchRecord> query(String value) {
        return searchRecordDao.queryBuilder().where(SearchRecordDao.Properties.Text.eq(value)).orderDesc(SearchRecordDao.Properties.Date).build().list();
    }

    private List<SearchRecord> queryAllLimit() {
        return searchRecordDao.queryBuilder().orderDesc(SearchRecordDao.Properties.Date).limit(default_size).build().list();
    }

    @Override
    public void save(String value) {
        List<SearchRecord> records = query(value);
        if (!records.isEmpty()) {
            for (SearchRecord sd : records) {
                delete(sd);
            }
        }
        SearchRecord searchRecord = new SearchRecord(value, new Date());
        insert(searchRecord);
    }

    @Override
    public void remove(String key) {
        List<SearchRecord> records = query(key);
        if (!records.isEmpty()) {
            for (SearchRecord sd : records) {
                delete(sd);
            }
        }
    }

    @Override
    public void clear() {
        List<SearchRecord> records = queryAll();
        if (!records.isEmpty()) {
            for (SearchRecord sd : records) {
                delete(sd);
            }
        }
    }

    @Override
    public String generateKey() {
        return null;
    }

    @Override
    public ArrayList<SearchHistoryModel> sortHistory() {
        List<SearchRecord> queries = queryAllLimit();
        ArrayList<SearchHistoryModel> models = new ArrayList<>();
        if (!queries.isEmpty()) {
            for (int i = 0; i < queries.size(); i++) {
                models.add(new SearchHistoryModel(queries.get(i).getText(), queries.get(i).getText()));
            }
        }
        return models;
    }


}
