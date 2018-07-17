package com.example.amrit.myapplication2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button cal=(Button) findViewById(R.id.buttonCal);
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
        String wvalue = height.getText().toString();
        Double weightVal = Double.parseDouble(wvalue);
        System.out.println("here is the height "+weightVal);

        Double calc = (weightVal/(heightVal*heightVal));
        EditText result = (EditText) findViewById(R.id.result);

        result.setText(Double.toString(calc));
    }

    /*public void onClickEnter(View view){
        Intent intent = new Intent( this,Main2Activity.class);
        startActivity(intent);

    }*/
}
