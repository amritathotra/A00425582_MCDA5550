package com.example.amrit.myapplication2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

class InClassDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "inclass"; // name of the DB
    private static final int DB_VERSION = 1; // version of the DB
    public static final String TABLE_NAME = "PERSON"; // name of the Table
    public static final String TABLE_NAME2 = "BMI"; // name of the Table

    public InClassDatabaseHelper(Context context){
        super(context,DB_NAME,null, DB_VERSION); // null is for cursors
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "PASSWORD TEXT, " // Never store passwords in clear text in real apps
                + "HEALTH_CARD_NUMB TEXT, "
                + "DATE TEXT);");

        db.execSQL("CREATE TABLE "+TABLE_NAME2+" ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "HEIGHT DOUBLE, "
                + "WEIGHT DOUBLE, " // Never store passwords in clear text in real apps
                + "BMI DOUBLE);");
    }

    public void createPersonData(String name, String date, String password, String healthCardNumber){
        SQLiteDatabase db = getWritableDatabase();
        Date today = new Date(); // we want to start with some initial data
        ContentValues personValues = new ContentValues();
        personValues.put("NAME", name);
        personValues.put("PASSWORD", password);
        personValues.put("HEALTH_CARD_NUMB", healthCardNumber );
        personValues.put("DATE", date);
        db.insert(TABLE_NAME,null, personValues);

    }

    public void createBmiData(double height, double weight, double bmi){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues bmiValues = new ContentValues();
        bmiValues.put("HEIGHT", height);
        bmiValues.put("WEIGHT", weight);
        bmiValues.put("BMI", bmi);

        db.insert(TABLE_NAME2,null, bmiValues);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}