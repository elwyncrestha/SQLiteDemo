package com.github.elwyncrestha.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * @author Elvin Shrestha on 19/12/19
 */
public class MyHelper extends SQLiteOpenHelper {

    private static final String dbName = "dictionaryDB";
    private static final int dbVersion = 1;

    private static final String TABLE_NAME = "tblWord";
    private static final String COLUMN_WORD_ID = "wordId";
    private static final String COLUMN_WORD = "word";
    private static final String COLUMN_MEANING = "meaning";

    public MyHelper(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = String.format(
                "CREATE TABLE %s" +
                        "(" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT," +
                        "%s TEXT" +
                        ")", TABLE_NAME, COLUMN_WORD_ID, COLUMN_WORD, COLUMN_MEANING);
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertData(String word, String meaning, SQLiteDatabase sqLiteDatabase) {
        try {
            String query = String.format(
                    "INSERT INTO %s(%s, %s) VALUES('%s', '%s')",
                    TABLE_NAME, COLUMN_WORD, COLUMN_MEANING, word, meaning
            );
            sqLiteDatabase.execSQL(query);
            return true;
        } catch (Exception e) {
            Log.d("Error: ", e.toString());
            return false;
        }
    }

    public long insert(String word, String meaning, SQLiteDatabase sqLiteDatabase) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_WORD, word);
        contentValues.put(COLUMN_MEANING, meaning);
        return sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }
}
