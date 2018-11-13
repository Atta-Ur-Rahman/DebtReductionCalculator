package com.techease.debtreductioncalculator.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreditorInformationDataBase extends SQLiteOpenHelper {

    private static String DB_NAME = "DB_CREDITOR";
    private static int DB_VERSION = 1;


    public CreditorInformationDataBase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE CREDITOR_TABLE (ID INTEGER PRIMARY KEY AUTOINCREMENT,DATE,CREDITOR_NAME,BALANCE,RATE,PAYMENT,CUSTOM)";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
