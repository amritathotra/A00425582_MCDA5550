package com.example.amrit.myapplication2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.view.View;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;


public class EnterBMIActivity extends AppCompatActivity {
    private Person person = null;
    private static final String TAG = "EnterBMIActivity_DOA_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // gets the person from the login page.
        if(person == null) person = (Person)getIntent().getSerializableExtra("Person");
        setContentView(R.layout.activity_bmi);
    }

    public void calculate(View view){
        InClassDatabaseHelper helper = new InClassDatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();


        EditText height = (EditText)findViewById(R.id.textHeight);
        String value = height.getText().toString();
        Double heightVal = Double.parseDouble(value);

        EditText weight = (EditText)findViewById(R.id.textWeight);
        String valueW = weight.getText().toString();
        Double weightVal = Double.parseDouble(valueW);

        Double calc = (weightVal / (heightVal * heightVal));

        EditText result = (EditText) findViewById(R.id.textResult);
        result.setText(calc.toString());

        ContentValues BMI = new ContentValues();
        BMI.put("_ID", person.getId());
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        BMI.put("DATE", dateFormat.format(new Date()));
        BMI.put("HEIGHT", heightVal);
        BMI.put("WEIGHT", weightVal);
        BMI.put("BMI", calc);

        db.insert("BMI", null, BMI);
    }

    public void onClickViewHistory(View view){
        Intent intent = new Intent(this, BMIListActivity.class);
        // sends the person to the next page
        intent.putExtra("Person", person);
        startActivity(intent);
    }
}
