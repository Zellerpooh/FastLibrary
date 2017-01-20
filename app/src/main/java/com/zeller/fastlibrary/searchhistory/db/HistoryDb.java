package com.zeller.fastlibrary.searchhistory.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zeller.fastlibrary.searchhistory.storage.DbHistoryStorage;

/**
 * Created by Zellerpooh on 17/1/19.
 */

public class HistoryDb extends SQLiteOpenHelper {

    public static final String DATABASENAME = "historydb.db";
    private static final int VERSION = 1;
    private static HistoryDb sInstance = null;

    private final Context mContext;

    private HistoryDb(final Context context) {
        super(context, DATABASENAME, null, VERSION);

        mContext = context;
    }

    public static synchronized HistoryDb getsInstance(final Context context) {
        if (sInstance == null) {
            synchronized (HistoryDb.class) {
                if (sInstance == null) {
                    sInstance = new HistoryDb(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        DbHistoryStorage.getInstance(mContext).onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        DbHistoryStorage.getInstance(mContext).onUpgrade(db, oldVersion, newVersion);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        DbHistoryStorage.getInstance(mContext).onDowngrade(db, oldVersion, newVersion);
    }
}
