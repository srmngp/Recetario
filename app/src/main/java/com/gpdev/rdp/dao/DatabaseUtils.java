package com.gpdev.rdp.dao;

import java.io.IOException;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.gpdev.rdp.AppUtils;
import com.gpdev.rdp.R;

import de.greenrobot.common.io.FileUtils;

public class DatabaseUtils {

    /** Create new database if not present */
    public static void copyExistingDatabase(String name) {
       // if (databaseExists(name)) {
       //     return;
       // }

        copyDataBase(name);
    }

    private static boolean databaseExists(String name) {
        SQLiteDatabase sqliteDatabase = null;
        try {
            sqliteDatabase = SQLiteDatabase.openDatabase(AppUtils.extractString(R.string.DB_DESTINY_PATH) + name, null,
                    SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }

        if (sqliteDatabase != null) {
            sqliteDatabase.close();
        }

        return sqliteDatabase != null;
    }

    /**
     * Copy existing database file in system
     */
    private static void copyDataBase(String name) {

        String destinyPath = AppUtils.extractString(R.string.DB_DESTINY_PATH);
        String resourcePath = AppUtils.extractString(R.string.DB_RESOURCE_PATH) + AppUtils.extractString(R.string.DB_RESOURCE_NAME);

        try {
            FileUtils.copyFile(resourcePath, destinyPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}