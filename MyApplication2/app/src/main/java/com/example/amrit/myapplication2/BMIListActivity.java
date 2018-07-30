package com.example.amrit.myapplication2;


import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BMIListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_bmilist); //List activity does this for you!
        ListView listBMIResults = getListView();
        ArrayAdapter<bmiResult> listAdapter = new ArrayAdapter<bmiResult>(
                this,
                android.R.layout.simple_expandable_list_item_1, //<- Layout param for ListActivity
                results);
        listBMIResults.setAdapter(listAdapter);

    }

    public void onListItemClick(ListView listView,
                                View itemView,
                                int position,
                                long id){
        System.out.println("Clicked on " + results[position].toString());
    }

    bmiResult[] results = { new bmiResult(5.5,100), new bmiResult(4.3,156)};


}