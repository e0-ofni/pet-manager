package com.example.petmanager;

import android.provider.BaseColumns;

public final class DatabaseDiary {
    public static final class Diary implements BaseColumns {
        public static final String DIARYNAME = "diaryname";
        public static final String DIARYYEAR = "diaryyear";
        public static final String DIARYMONTH = "diarymonth";
        public static final String DIARYDAY = "diaryday";
        public static final String DIARYTITLE = "diarytitle";
        public static final String DIARYIMAGE = "diaryimage";
        public static final String DIARYCONTENTS = "diarycontents";
        public static final String _TABLENAME0 = "diarytable";
        public static final String _CREATE0 = "create table if not exists "+_TABLENAME0+"("
                +_ID+" integer primary key autoincrement, "
                +DIARYNAME+" integer not null , "
                +DIARYYEAR+" integer not null , "
                +DIARYMONTH+" integer not null , "
                +DIARYDAY+" integer not null, "
                +DIARYTITLE+" text not null, "
                +DIARYIMAGE+" text not null, "
                +DIARYCONTENTS+" text not null);";
        public static final String _DELETE0 =
                "DROP TABLE IF EXISTS " + _TABLENAME0;
    }
}
