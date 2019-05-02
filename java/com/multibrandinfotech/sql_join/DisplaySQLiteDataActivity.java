package com.multibrandinfotech.sql_join;

import android.app.Activity;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

public class DisplaySQLiteDataActivity extends Activity {

    SQLiteHelper sqLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    ListAdapter listAdapter ;
    ListView LISTVIEW;

    ArrayList<String> ID_Array;
    ArrayList<String> NAME_Array;
    ArrayList<String> PHONE_NUMBER_Array;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_sqlite_data);

        LISTVIEW = (ListView) findViewById(R.id.listView1);

        ID_Array = new ArrayList<String>();

        NAME_Array = new ArrayList<String>();

        PHONE_NUMBER_Array = new ArrayList<String>();

        sqLiteHelper = new SQLiteHelper(this);

    }

    @Override
    protected void onResume() {

        ShowSQLiteDBdata() ;

        super.onResume();
    }

    private void ShowSQLiteDBdata() {

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        String query="SELECT * FROM  '"+sqLiteHelper.TABLE_NAME+"' INNER JOIN '"+sqLiteHelper.TABLE_NAME2+"' ON '"+sqLiteHelper.Table_Column_2_PhoneNumber+"' = '"+sqLiteHelper.Table_Column_2_PhoneNumber2+"'";

        String query2="SELECT * FROM AndroidJSonTable INNER JOIN AndroidJSonTable2 ON AndroidJSonTable.phone_number = AndroidJSonTable2.phone_number";


        cursor =sqLiteDatabase.rawQuery(query, null);

       // cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+SQLiteHelper.TABLE_NAME2+"", null);

        ID_Array.clear();
        NAME_Array.clear();
        PHONE_NUMBER_Array.clear();

        if (cursor.moveToFirst()) {
            do {

                ID_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_ID)));

                NAME_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_1_Name)));

                PHONE_NUMBER_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_2_PhoneNumber)));


            } while (cursor.moveToNext());
        }

        listAdapter = new ListAdapter(DisplaySQLiteDataActivity.this,

                ID_Array,
                NAME_Array,
                PHONE_NUMBER_Array
        );

        LISTVIEW.setAdapter(listAdapter);

        cursor.close();
    }
}