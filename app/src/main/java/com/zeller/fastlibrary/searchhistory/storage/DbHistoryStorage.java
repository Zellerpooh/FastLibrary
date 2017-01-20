package com.zeller.fastlibrary.searchhistory.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zeller.fastlibrary.searchhistory.bean.SearchHistoryModel;
import com.zeller.fastlibrary.searchhistory.db.HistoryDb;
import com.zeller.fastlibrary.searchhistory.model.SearchModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zellerpooh on 17/1/19.
 */

public class DbHistoryStorage extends BaseHistoryStorage {
    public static final int DEFAULT_INT = 5;
    private static DbHistoryStorage sInstance = null;
    private int maxItems = 5;
    private static SimpleDateFormat mFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private HistoryDb mHistoryDb = null;

    private DbHistoryStorage(Context context, int maxItems) {
        mHistoryDb = HistoryDb.getsInstance(context.getApplicationContext());
        this.maxItems = maxItems;
    }

    public static synchronized DbHistoryStorage getInstance(final Context context, int maxItems) {
        if (sInstance == null) {
            synchronized (DbHistoryStorage.class) {
                if (sInstance == null) {
                    sInstance = new DbHistoryStorage(context.getApplicationContext(), maxItems);
                }
            }
        }
        return sInstance;
    }

    public static synchronized DbHistoryStorage getInstance(final Context context) {
        if (sInstance == null) {
            synchronized (DbHistoryStorage.class) {
                if (sInstance == null) {
                    sInstance = new DbHistoryStorage(context.getApplicationContext(), DEFAULT_INT);
                }
            }
        }
        return sInstance;
    }

    public void onCreate(final SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + SearchHistoryColumns.NAME + " ("
                + SearchHistoryColumns.SEARCHSTRING + " TEXT NOT NULL,"
                + SearchHistoryColumns.TIMESEARCHED + " LONG NOT NULL);");
    }

    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SearchHistoryColumns.NAME);
        onCreate(db);
    }


    @Override
    public void save(String searchString) {
        if (searchString == null) {
            return;
        }

        String trimmedString = searchString.trim();

        if (trimmedString.isEmpty()) {
            return;
        }

        final SQLiteDatabase database = mHistoryDb.getWritableDatabase();
        database.beginTransaction();

        try {

            database.delete(SearchHistoryColumns.NAME,
                    SearchHistoryColumns.SEARCHSTRING + " = ? COLLATE NOCASE",
                    new String[]{trimmedString});

            final ContentValues values = new ContentValues(2);
            values.put(SearchHistoryColumns.SEARCHSTRING, trimmedString);
            values.put(SearchHistoryColumns.TIMESEARCHED, generateKey());
            database.insert(SearchHistoryColumns.NAME, null, values);

            Cursor oldest = null;
            try {
                database.query(SearchHistoryColumns.NAME,
                        new String[]{SearchHistoryColumns.TIMESEARCHED}, null, null, null, null,
                        SearchHistoryColumns.TIMESEARCHED + " ASC");

                if (oldest != null && oldest.getCount() > maxItems) {
                    oldest.moveToPosition(oldest.getCount() - maxItems);
                    long timeOfRecordToKeep = oldest.getLong(0);

                    database.delete(SearchHistoryColumns.NAME,
                            SearchHistoryColumns.TIMESEARCHED + " < ?",
                            new String[]{String.valueOf(timeOfRecordToKeep)});

                }
            } finally {
                if (oldest != null) {
                    oldest.close();
                    oldest = null;
                }
            }
        } finally {
            database.setTransactionSuccessful();
            database.endTransaction();
        }
    }

    @Override
    public void remove(String key) {
        final SQLiteDatabase database = mHistoryDb.getReadableDatabase();
        database.delete(SearchHistoryColumns.NAME, SearchHistoryColumns.TIMESEARCHED + " = ?", new String[]{key});
    }

    @Override
    public void clear() {
        final SQLiteDatabase database = mHistoryDb.getReadableDatabase();
        database.execSQL("DELETE FROM " + SearchHistoryColumns.NAME);
    }

    @Override
    public String generateKey() {
        return mFormat.format(new Date());
    }

    @Override
    public ArrayList<SearchHistoryModel> sortHistory() {
        Cursor searches = queryRecentSearches(String.valueOf(maxItems));

        ArrayList<SearchHistoryModel> results = new ArrayList<>(maxItems);

        try {
            if (searches != null && searches.moveToFirst()) {
                int colIdx = searches.getColumnIndex(SearchHistoryColumns.SEARCHSTRING);
                int timeIdx = searches.getColumnIndex(SearchHistoryColumns.TIMESEARCHED);
                do {
                    SearchHistoryModel searchHistoryModel = new SearchHistoryModel();
                    searchHistoryModel.setContent(searches.getString(colIdx));
                    searchHistoryModel.setTime(searches.getString(timeIdx));
                    results.add(searchHistoryModel);
                } while (searches.moveToNext());
            }
        } finally {
            if (searches != null) {
                searches.close();
                searches = null;
            }
        }

        return results;
    }

    public Cursor queryRecentSearches(final String limit) {
        final SQLiteDatabase database = mHistoryDb.getReadableDatabase();
        return database.query(SearchHistoryColumns.NAME,
                new String[]{SearchHistoryColumns.SEARCHSTRING, SearchHistoryColumns.TIMESEARCHED}, null, null, null, null,
                SearchHistoryColumns.TIMESEARCHED + " DESC", limit);
    }

    public interface SearchHistoryColumns {
        /* Table name */
        String NAME = "searchhistory";

        /* What was searched */
        String SEARCHSTRING = "searchstring";

        /* Time of search */
        String TIMESEARCHED = "timesearched";
    }
}
