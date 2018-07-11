package com.example.amrit.myapplication2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void calculate(){
        EditText height = (EditText) findViewById(R.id.textHeight);
        String value = height.getText().toString();
        Double heightVal = Double.parseDouble(value);
        System.out.println("here is the height "+heightVal);

        //repeat for weight
        Double calc = (weightVal/(heightVal*heightVal));
        EditText result = (EditText) findViewById(R.id.result);

        result.setText(calc);
    }
    public void onClickEnter(View view){
        Intent intent = new Intent( this,Main2Activity.class);
        startActivity(intent);
    }
}
