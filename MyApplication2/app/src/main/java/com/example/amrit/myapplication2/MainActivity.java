package com.example.amrit.myapplication2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

        EditText nameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        InClassDatabaseHelper helper = new InClassDatabaseHelper(this);

        nameField = (EditText) findViewById(R.id.editText);


        SQLiteDatabase db = helper.getWritableDatabase();
        // run a query
        Cursor cursor = db.query(InClassDatabaseHelper.TABLE_NAME,new String[]
                        {"NAME","PASSWORD","DATE"},
                null,null,null,null,null); //
        if (cursor.moveToFirst()){
            String name = cursor.getString(0);
            Log.d("Name", name);
            nameField.setText(name);
        }
        cursor.close(); // cleanup
        db.close(); // cleanup


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickEnter(View view){
        Intent intent = new Intent( this,Main2Activity.class);
        startActivity(intent);

    }
}
