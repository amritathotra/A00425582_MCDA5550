package com.example.amrit.myapplication2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Main2Activity extends AppCompatActivity {


    InClassDatabaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button cal=(Button) findViewById(R.id.buttonCal);

        helper = new InClassDatabaseHelper(this);


        SQLiteDatabase db = helper.getWritableDatabase();
        // run a query
        Cursor cursor = db.query(InClassDatabaseHelper.TABLE_NAME2,new String[]
                        {"HEIGHT"},
                null,null,null,null,null); //
        while (cursor.moveToNext()){
            Double height = cursor.getDouble(0);
            Log.d("Height", height.toString());
        }
        cursor.close(); // cleanup
        db.close(); // cleanup
        cal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                calculate();
            }
        });
    }

    public void calculate(){
        EditText height = (EditText) findViewById(R.id.textHeight);
        String value = height.getText().toString();
        Double heightVal = Double.parseDouble(value);
        System.out.println("here is the height "+heightVal);

        //repeat for weight
        EditText weight = (EditText) findViewById(R.id.textWeight);
        String wvalue = weight.getText().toString();
        Double weightVal = Double.parseDouble(wvalue);
        System.out.println("here is the Weight "+weightVal);

        Double calc = (weightVal/(heightVal*heightVal));
        EditText result = (EditText) findViewById(R.id.result);

        result.setText(Double.toString(calc));

        helper.createBmiData(heightVal,weightVal,calc);
    }

    /*public void onClickEnter(View view){
        Intent intent = new Intent( this,Main2Activity.class);
        startActivity(intent);

    }*/
}
