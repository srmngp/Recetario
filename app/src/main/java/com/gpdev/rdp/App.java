package com.gpdev.rdp;

import android.app.Application;
import android.content.Context;

import com.gpdev.rdp.dao.DatabaseHelper;
import com.gpdev.rdp.dao.DaoMaster;
import com.gpdev.rdp.dao.DaoSession;
import com.gpdev.rdp.dao.DatabaseUtils;

import org.greenrobot.greendao.database.Database;

public class App extends Application {

    private DaoSession daoSession;
    private static Context staticContext;

    @Override
    public void onCreate() {
        super.onCreate();
        staticContext = this;
        daoSession = extractDaoSession();
    }

    private DaoSession extractDaoSession() {
        String databaseName = this.getString(R.string.DB_NAME);

        DatabaseUtils.copyExistingDatabase(databaseName);

        DatabaseHelper helper = new DatabaseHelper(this, databaseName);
        Database db = helper.getWritableDb();

        return new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public static Context getContext() {
        return staticContext;
    }

}