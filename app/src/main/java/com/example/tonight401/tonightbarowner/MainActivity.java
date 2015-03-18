package com.example.tonight401.tonightbarowner;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "WmiRuMFBYBvbiSfYhngCJl4dqy2zs1FkwFC5CnRh", "Ezo6Lc63m5XNo6eRA3ZmQsiaV6cJPJMrkfMXb7Fz");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void login(View v){
        EditText mUsername= (EditText)findViewById(R.id.Username);
        EditText mPassword= (EditText)findViewById(R.id.Password);
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();
        ParseOperations.validateLogin(username,password);
        boolean login=ParseOperations.isValidate();
        if (login==true){
            //Passing the bar Id through an intent incase you need it
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
    }
}
