package com.example.tonight401.tonightbarowner;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class CommentList extends ActionBarActivity {
    private ListView listview;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);
        Intent intent =getIntent();
        String barId=intent.getExtras().getString("barId");

        String[] barIds = new String[] {
                barId,
                barId,
                barId,
                barId,
                barId,
                barId,
                barId,
                barId,
                barId,
                barId,
                barId,
                barId,
                barId,
                barId,
                barId

        };


        listview = (ListView) findViewById(R.id.OwnersComments);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,barIds);
        listview.setAdapter(adapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_comment_list, menu);
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
}
