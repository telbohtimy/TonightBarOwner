package com.example.tonightbarowner;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "WmiRuMFBYBvbiSfYhngCJl4dqy2zs1FkwFC5CnRh", "Ezo6Lc63m5XNo6eRA3ZmQsiaV6cJPJMrkfMXb7Fz");
    }

    public void login(View v){
        EditText mUsername= (EditText)findViewById(R.id.Username);
        EditText mPassword= (EditText)findViewById(R.id.Password);
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();

        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (parseUser != null) {
                    //Passing the bar Id through an intent in case you need it
                    String barId = parseUser.getString("barId");
                    Intent intent = new Intent(getApplicationContext(), CommentList.class);
                    intent.putExtra("barId", barId);
                    startActivity(intent);
                    finish();
                } else {
                    TextView error= (TextView) findViewById(R.id.Error);
                    error.setVisibility(View.VISIBLE);
                }
            }
        });

        /*
        ParseOperations.validateLogin(username,password);
        boolean login = ParseOperations.isValidate();
        if (login==true){
            //Passing the bar Id through an intent in case you need it
            String barId=ParseOperations.isBarId();
            Intent intent = new Intent(this,CommentList.class);
            intent.putExtra("barId", barId);
            startActivity(intent);
        }
        else{
            TextView error= (TextView) findViewById(R.id.Error);
            error.setVisibility(View.VISIBLE);
            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
        }
        */
    }
}
