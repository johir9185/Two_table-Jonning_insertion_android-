package com.multibrandinfotech.sql_join;

/**
 * Created by Md. Rejaul Karim on 5/2/2019.
 * Copyright (c) 2019 MULTIBRAND INFOTECH LTD
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    static String DATABASE_NAME="AndroidJSonDataBaseQ";

    public static final String TABLE_NAME="AndroidJSonTable";

    public static final String TABLE_NAME2="AndroidJSonTable2";

    public static final String Table_Column_ID="id";
    public static final String Table_Column_1_Name="name";
    public static final String Table_Column_2_PhoneNumber="phone_number";

    public static final String Table_Column_ID2="id";
    public static final String Table_Column_1_Name2="name";
    public static final String Table_Column_2_PhoneNumber2="phone_number";




    public SQLiteHelper(Context context) {

        super(context, DATABASE_NAME, null, 2);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+Table_Column_ID+" INTEGER PRIMARY KEY, "+Table_Column_1_Name+" VARCHAR, "+Table_Column_2_PhoneNumber+" VARCHAR)";
        String CREATE_TABLE2="CREATE TABLE IF NOT EXISTS "+TABLE_NAME2+" ("+Table_Column_ID2+" INTEGER PRIMARY KEY, "+Table_Column_1_Name2+" VARCHAR, "+Table_Column_2_PhoneNumber2+" VARCHAR)";
        database.execSQL(CREATE_TABLE);
        database.execSQL(CREATE_TABLE2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME2);
        onCreate(db);

    }

}