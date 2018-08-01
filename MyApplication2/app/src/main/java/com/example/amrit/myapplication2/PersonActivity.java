package com.example.amrit.myapplication2;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;


public class PersonActivity extends AppCompatActivity {
    private Person person = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void onClickRegister(View view){

        InClassDatabaseHelper helper = new InClassDatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        // get the fields values and insert into the database
        String userName = ((EditText)findViewById(R.id.userName)).getText().toString();
        String userPass = ((EditText)findViewById(R.id.Password)).getText().toString();
        String birthdate = ((EditText)findViewById(R.id.birthdate)).getText().toString();
        String healthcard = ((EditText)findViewById(R.id.healthcard)).getText().toString();

        ContentValues personValues = new ContentValues();
        personValues.put("NAME", userName);
        personValues.put("PASSWORD", userPass);
        personValues.put("HEALTH_CARD", healthcard);
        personValues.put("DATEBIRTH", birthdate);



        db.insert("PERSON", null, personValues);

            // intent for going to the BMI calculate Activity
            Intent intent = new Intent(this, EnterBMIActivity.class);

            try (Cursor cursor = db.rawQuery("SELECT _ID FROM PERSON WHERE NAME = ?", new String[] {userName}, null)) {
                cursor.moveToNext();
                person = new Person(cursor.getInt(0), userName);
            }
            db.close();
            // existing user passed to the BMI calculate Activity
            intent.putExtra("Person", person);
            startActivity(intent);

        db.close();
    }
}
