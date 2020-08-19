package com.gpdev.rdp.dao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.gpdev.rdp.App;
import com.gpdev.rdp.AppUtils;
import com.gpdev.rdp.R;

import de.greenrobot.common.io.FileUtils;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public class DatabaseUtils {

    /** Create new database if not present */
    public static void copyExistingDatabase(String name) {
       if (databaseExists(name)) {
            return;
       }

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
    private static void copyDataBase(String databaseName) {

        String destinyPath = AppUtils.extractString(R.string.DB_DESTINY_PATH);
        File destinationFile = new File(destinyPath + databaseName);

        String resourcePath = AppUtils.extractString(R.string.DB_RESOURCE_NAME);

        try {
            InputStream databaseInputStream = App.getContext().getAssets().open(resourcePath);
            copyInputStreamToFile(databaseInputStream, destinationFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}