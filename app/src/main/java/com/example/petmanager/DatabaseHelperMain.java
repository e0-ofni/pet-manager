package com.example.petmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DatabaseHelperMain extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "databasemain";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelperMain(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DatabaseMain.PetList._CREATE0); // 테이블 생성
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // 단순히 데이터를 삭제하고 다시 시작하는 정책이 적용될 경우
        sqLiteDatabase.execSQL(DatabaseMain.PetList._DELETE0);
        onCreate(sqLiteDatabase);
    }

    void insertRecord(String image, String name, int age, String species, String illness,String surgery, String info) {

        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseMain.PetList.PETIMAGE, image);
        values.put(DatabaseMain.PetList.PETNAME, name);
        values.put(DatabaseMain.PetList.PETAGE, age);
        values.put(DatabaseMain.PetList.PETSPECIES, species);
        values.put(DatabaseMain.PetList.PETILLNESS, illness);
        values.put(DatabaseMain.PetList.PETSURGERY, surgery);
        values.put(DatabaseMain.PetList.PETINFO, info);

        db.insert(DatabaseMain.PetList._TABLENAME0, null, values);
    }
    void updateRecord(int id, String image, String name, int age, String species, String illness,String surgery, String info) {
        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseMain.PetList.PETIMAGE, image);
        values.put(DatabaseMain.PetList.PETNAME, name);
        values.put(DatabaseMain.PetList.PETAGE, age);
        values.put(DatabaseMain.PetList.PETSPECIES, species);
        values.put(DatabaseMain.PetList.PETILLNESS, illness);
        values.put(DatabaseMain.PetList.PETSURGERY, surgery);
        values.put(DatabaseMain.PetList.PETINFO, info);

        db.update(DatabaseMain.PetList._TABLENAME0,  values, DatabaseMain.PetList._ID + "=" + id,null);
    }

    void deleteRecord(int id) {
        SQLiteDatabase db = getReadableDatabase();

        db.delete(DatabaseMain.PetList._TABLENAME0, DatabaseMain.PetList._ID + "=" + id, null);
    }


    public Cursor readRecordOrderByID() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                DatabaseMain.PetList.PETIMAGE,
                DatabaseMain.PetList.PETNAME,
                DatabaseMain.PetList.PETAGE,
                DatabaseMain.PetList.PETSPECIES,
                DatabaseMain.PetList.PETILLNESS,
                DatabaseMain.PetList.PETSURGERY,
                DatabaseMain.PetList.PETINFO
        };

        String sortOrder = DatabaseMain.PetList._ID + " ASC";

        Cursor cursor = db.query(
                DatabaseMain.PetList._TABLENAME0,   // The table to query
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