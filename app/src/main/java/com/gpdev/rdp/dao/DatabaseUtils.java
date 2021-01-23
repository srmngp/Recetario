package com.gpdev.rdp.dao;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.gpdev.rdp.App;
import com.gpdev.rdp.AppUtils;
import com.gpdev.rdp.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public class DatabaseUtils {

    private static final String TAG = "DatabaseUtils";

    private static String destinyPath = AppUtils.extractString(R.string.DB_DESTINY_PATH);

    public static void copyExistingDatabase(String name) {
        if (databaseExists(name)) {
            Log.i(TAG, "Database " + name + "already exists");
            return;
        }
        copyDataBase(name);
    }

    private static boolean databaseExists(String name) {
        SQLiteDatabase sqliteDatabase = null;
        try {
            sqliteDatabase = SQLiteDatabase.openDatabase(destinyPath + name, null,
                    SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            return false;
        }

        if (sqliteDatabase != null) {
            sqliteDatabase.close();
        }

        return sqliteDatabase != null;
    }

    private static void copyDataBase(String databaseName) {
        String destinyPath = AppUtils.extractString(R.string.DB_DESTINY_PATH);
        File destinationFile = new File(destinyPath + databaseName);

        String resourcePath = AppUtils.extractString(R.string.DB_RESOURCE_PATH) + AppUtils.extractString(R.string.DB_RESOURCE_NAME);

        try {
            InputStream databaseInputStream = App.getContext().getAssets().open(resourcePath);
            copyInputStreamToFile(databaseInputStream, destinationFile);
        } catch (IOException e) {
            Log.e(TAG, "Error while copying the database", e);
        }
    }

}