package com.zeller.fastlibrary;

import android.app.Application;

import com.zeller.fastlibrary.greendaoTest.DaoMaster;
import com.zeller.fastlibrary.greendaoTest.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Zellerpooh on 17/3/3.
 */

public class App extends Application {
    private static final String db_name = "search_history";
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        createDaoSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }

    public void createDaoSession() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, db_name);
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }
}
