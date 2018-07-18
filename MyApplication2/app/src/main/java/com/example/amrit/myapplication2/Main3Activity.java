package com.example.amrit.myapplication2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class Main3Activity extends Activity {
    EditText nameField;
    EditText dateField;
    EditText passwordField;
    EditText healthCardField;
    InClassDatabaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        helper = new InClassDatabaseHelper(this);

        nameField = (EditText) findViewById(R.id.editTextName);
        dateField = (EditText) findViewById(R.id.editTextDate);
        passwordField = (EditText) findViewById(R.id.editTextPassword);
        healthCardField = (EditText) findViewById(R.id.editTextCard);



        SQLiteDatabase db = helper.getWritableDatabase();
        // run a query
        Cursor cursor = db.query(InClassDatabaseHelper.TABLE_NAME,new String[]
                        {"NAME","PASSWORD","DATE","HEALTH_CARD_NUMB"},
                null,null,null,null,null); //
        if (cursor.moveToFirst()){
            String name = cursor.getString(0);
            String password = cursor.getString(1);
            String date = cursor.getString(2);
            String health_card_num = cursor.getString(3);
            Log.d("Name", name);
            nameField.setText(name);
            passwordField.setText(password);
            dateField.setText(date);
            healthCardField.setText(health_card_num);
        }
        cursor.close(); // cleanup
        db.close(); // cleanup
    }


    public void onClickEnter(View view){
        Intent intent = new Intent( this,Main2Activity.class);
        helper.createPersonData(nameField.getText().toString(),dateField.getText().toString(),healthCardField.getText().toString(),passwordField.getText().toString());

        startActivity(intent);

    }

}
