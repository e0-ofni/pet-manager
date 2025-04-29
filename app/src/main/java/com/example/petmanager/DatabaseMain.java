package com.example.petmanager;

import android.provider.BaseColumns;

public final class DatabaseMain {
    public static final class PetList implements BaseColumns {
        public static final String PETIMAGE = "petimage";
        public static final String PETNAME = "petname";
        public static final String PETAGE = "petage";
        public static final String PETSPECIES = "petspecies";
        public static final String PETILLNESS = "petillness";
        public static final String PETSURGERY = "petsurgery";
        public static final String PETINFO = "petinfo";
        public static final String _TABLENAME0 = "pettable";
        public static final String _CREATE0 = "create table if not exists "+_TABLENAME0+"("
                +_ID+" integer primary key autoincrement, "
                +PETIMAGE+" text not null , "
                +PETNAME+" text not null , "
                +PETAGE+" integer not null,"
                +PETSPECIES+" text not null , "
                +PETILLNESS+" text not null , "
                +PETSURGERY+" text not null , "
                +PETINFO+" text not null);";
        public static final String _DELETE0 =
                "DROP TABLE IF EXISTS " + _TABLENAME0;
    }

}
