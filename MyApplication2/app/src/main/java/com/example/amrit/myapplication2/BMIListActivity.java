package com.example.amrit.myapplication2;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

public class BMIListActivity extends ListActivity {
    private java.util.List<BMIResult> results = new ArrayList<BMIResult>();
    private Person person = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // gets the person from the login page.
        person = (Person)getIntent().getSerializableExtra("Person");

        InClassDatabaseHelper helper = new InClassDatabaseHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        // gets the bmi history for this person
        try (Cursor cursor = db.rawQuery("SELECT DATE, HEIGHT, WEIGHT, BMI FROM BMI WHERE _ID = ?", new String[] {String.valueOf(person.getId())}, null)) {

            if(cursor.getCount() > 0) {

                while (cursor.moveToNext()) {

                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    results.add(new BMIResult(Double.parseDouble(cursor.getString(1)),
                            Double.parseDouble(cursor.getString(2)),
                            cursor.getString(0)));

                }
            }
        }

        ListView listBMIResults = getListView();

        // send it to a normal array (don't ask me...)
        BMIResult[] results2 = new BMIResult[results.size()];
        for(int i = 0; i < results.size(); i++){
            results2[i] = results.get(i);
        }

        ArrayAdapter<BMIResult> listAdapter = new ArrayAdapter<BMIResult>(
                this,
                android.R.layout.simple_list_item_1,
                results2
        );

        listBMIResults.setAdapter(listAdapter);
    }


}
