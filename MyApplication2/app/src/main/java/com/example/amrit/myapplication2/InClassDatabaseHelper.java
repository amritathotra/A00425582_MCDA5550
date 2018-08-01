package com.example.amrit.myapplication2;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import java.util.Date;
import android.content.ContentValues;
import android.util.Log;

class InClassDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "inclass";
    private static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "PERSON";
    public static final String BMI = "BMI";

    public InClassDatabaseHelper(Context context){
        super(context,DB_NAME,null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // person table
        db.execSQL("CREATE TABLE "+TABLE_NAME+" ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "PASSWORD TEXT, "
                + "HEALTH_CARD TEXT, "
                + "DATEBIRTH TEXT);");

        // bmi table
        db.execSQL("CREATE TABLE "+ BMI +" ("
                + "_id INTEGER, "
                + "DATE TEXT, "
                + "HEIGHT DOUBLE,"
                + "WEIGHT DOUBLE,"
                + "BMI DOUBLE);");


        db.execSQL("CREATE UNIQUE INDEX PK ON "+ BMI + "(_id, DATE);");

        
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}