package com.example.petmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;


public class DatabaseHelperDiary extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "databasediary";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelperDiary(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DatabaseDiary.Diary._CREATE0); // 테이블 생성
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // 단순히 데이터를 삭제하고 다시 시작하는 정책이 적용될 경우
        sqLiteDatabase.execSQL(DatabaseDiary.Diary._DELETE0);
        onCreate(sqLiteDatabase);
    }

    void insertRecord(String name, int year, int month, int day,
                      String title, String image, String contents) {
        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseDiary.Diary.DIARYNAME, name);
        values.put(DatabaseDiary.Diary.DIARYYEAR, year);
        values.put(DatabaseDiary.Diary.DIARYMONTH, month);
        values.put(DatabaseDiary.Diary.DIARYDAY, day);
        values.put(DatabaseDiary.Diary.DIARYTITLE, title);
        values.put(DatabaseDiary.Diary.DIARYIMAGE, image);
        values.put(DatabaseDiary.Diary.DIARYCONTENTS, contents);


        db.insert(DatabaseDiary.Diary._TABLENAME0, null, values);
    }

    void updateRecord(int id, String name, int year, int month, int day,
                      String title, String image, String contents) {
        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseDiary.Diary.DIARYNAME, name);
        values.put(DatabaseDiary.Diary.DIARYYEAR, year);
        values.put(DatabaseDiary.Diary.DIARYMONTH, month);
        values.put(DatabaseDiary.Diary.DIARYDAY, day);
        values.put(DatabaseDiary.Diary.DIARYTITLE, title);
        values.put(DatabaseDiary.Diary.DIARYIMAGE, image);
        values.put(DatabaseDiary.Diary.DIARYCONTENTS, contents);

        db.update(DatabaseDiary.Diary._TABLENAME0, values, DatabaseDiary.Diary._ID + "=" + id, null);
    }

    void deleteRecord(int id) {
        SQLiteDatabase db = getReadableDatabase();

        db.delete(DatabaseDiary.Diary._TABLENAME0, DatabaseDiary.Diary._ID + "=" + id, null);
    }


    public Cursor readRecordOrderByID() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                DatabaseDiary.Diary.DIARYNAME,
                DatabaseDiary.Diary.DIARYYEAR,
                DatabaseDiary.Diary.DIARYMONTH,
                DatabaseDiary.Diary.DIARYDAY,
                DatabaseDiary.Diary.DIARYTITLE,
                DatabaseDiary.Diary.DIARYIMAGE,
                DatabaseDiary.Diary.DIARYCONTENTS
        };

        String sortOrder = DatabaseDiary.Diary._ID + " ASC";

        Cursor cursor = db.query(
                DatabaseDiary.Diary._TABLENAME0,   // The table to query
                projection,   // The array of columns to return (pass null to get all)
                null,   // where 문에 필요한 column
                null,   // where 문에 필요한 value
                null,   // group by를 적용할 column
                null,   // having 절
                sortOrder   // 정렬 방식
        );

        return cursor;
    }
}