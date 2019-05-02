package com.multibrandinfotech.sql_join;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabaseObj;
    EditText editTextName, editTextPhoneNumber;
    String NameHolder, NumberHolder, SQLiteDataBaseQueryHolder;
    Button EnterData, ButtonDisplayData;
    Boolean EditTextEmptyHold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EnterData = (Button)findViewById(R.id.button);
        ButtonDisplayData = (Button)findViewById(R.id.button2);
        editTextName = (EditText)findViewById(R.id.editText);
        editTextPhoneNumber = (EditText)findViewById(R.id.editText2);

        EnterData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SQLiteDataBaseBuild();

                SQLiteTableBuild();


                CheckEditTextStatus();

                InsertDataIntoSQLiteDatabase();

                EmptyEditTextAfterDataInsert();


            }
        });

        ButtonDisplayData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, DisplaySQLiteDataActivity.class);
                startActivity(intent);
            }
        });


    }

    public void SQLiteDataBaseBuild(){

        sqLiteDatabaseObj = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }

    public void SQLiteTableBuild(){

        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS "+SQLiteHelper.TABLE_NAME+"("+SQLiteHelper.Table_Column_ID+" PRIMARY KEY AUTOINCREMENT NOT NULL, "+SQLiteHelper.Table_Column_1_Name+" VARCHAR, "+SQLiteHelper.Table_Column_2_PhoneNumber+" VARCHAR);");

    }
    public void SQLiteTableBuild2(){

        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS "+SQLiteHelper.TABLE_NAME2+"("+SQLiteHelper.Table_Column_ID2+" PRIMARY KEY AUTOINCREMENT NOT NULL, "+SQLiteHelper.Table_Column_1_Name2+" VARCHAR, "+SQLiteHelper.Table_Column_2_PhoneNumber2+" VARCHAR);");

    }



    public void CheckEditTextStatus(){

        NameHolder = editTextName.getText().toString() ;
        NumberHolder = editTextPhoneNumber.getText().toString();

        if(TextUtils.isEmpty(NameHolder) || TextUtils.isEmpty(NumberHolder)){

            EditTextEmptyHold = false ;

        }
        else {

            EditTextEmptyHold = true ;
        }
    }

    public void InsertDataIntoSQLiteDatabase(){

        if(EditTextEmptyHold == true)
        {

            SQLiteDataBaseQueryHolder = "INSERT INTO "+SQLiteHelper.TABLE_NAME+" (name,phone_number) VALUES('"+NameHolder+"', '"+NumberHolder+"');";

            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);


            String name="pi";
            String num="20";

            SQLiteDataBaseQueryHolder = "INSERT INTO "+SQLiteHelper.TABLE_NAME2+" (name,phone_number) VALUES('"+name+"', '"+num+"');";

            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);

            Toast.makeText(MainActivity.this,"Data Inserted Successfully", Toast.LENGTH_LONG).show();

        }
        else {

            Toast.makeText(MainActivity.this,"Please Fill All The Required Fields.", Toast.LENGTH_LONG).show();

        }

    }

    public void EmptyEditTextAfterDataInsert(){

        editTextName.getText().clear();

        editTextPhoneNumber.getText().clear();

    }

}