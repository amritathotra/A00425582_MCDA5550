package com.example.amrit.myapplication2;

//import android.app.Person;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.database.Cursor;
import android.widget.EditText;
import android.util.Log;
import android.app.AlertDialog;
import android.os.Build;
import android.content.DialogInterface;


public class LoginActivity extends AppCompatActivity {
    private Person person = null;
    private static final String TAG = "LoginActivity_DOA_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    // If auth OK go to the next page
    public void onClickLogin(View view){
        Log.i(TAG, "onClickLogin");
        String userName = ((EditText)findViewById(R.id.userName)).getText().toString();
        String userPass = ((EditText)findViewById(R.id.userPass)).getText().toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Authentication failed");
        builder.setMessage("Invalid user/password.");
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(TAG, "DialogInterface");
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert);

        if(! validateLogin(userName, userPass)){
            Log.i(TAG, "onClickLogin - user/pass empty");
            builder.show();
            return;
        }

        if(authenticate(userName, userPass)) {
            Log.i(TAG, "onClickLogin - auth OK");
            Intent intent = new Intent(this, EnterBMIActivity.class);
            // sends the person to the next page
            intent.putExtra("Person", person);
            startActivity(intent);
        }
        else {
            Log.i(TAG, "onClickLogin - authentication FAILED");
            builder.show();
            return;
        }
    }

    // Just checks if something was entered...
    private boolean validateLogin(String userName, String password){
        return ((userName.trim().length() > 0) && (password.trim().length() > 0));
    }

    // Login authenticate, if user exist
    public boolean authenticate(String userName, String password){
        Log.i(TAG, "authenticating...");
        InClassDatabaseHelper helper = new InClassDatabaseHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Log.i(TAG, "authenticating: " + userName + " " + password);

       try (Cursor cursor = db.rawQuery("SELECT _ID, NAME, PASSWORD FROM PERSON WHERE NAME = ? AND PASSWORD = ?", new String[]{userName, password}, null)) {
            if(cursor.getCount() > 0) {
                cursor.moveToNext();
                person = new Person(cursor.getInt(0), cursor.getString(1));
                Log.i(TAG, "person: " + person);
                while (cursor.moveToNext()) {
                    Log.i(TAG, cursor.getString(0));
                    Log.i(TAG, cursor.getString(1));
                }
                Log.i(TAG, "!person == null: " + !(person == null));
            }
            return !(person == null);
        }
    }

    // registering new user
    public void onClickRegister(View view){
        Log.i(TAG, "onClickAddNew");
        Intent intent = new Intent(this, PersonActivity.class);
        startActivity(intent);
    }
}
