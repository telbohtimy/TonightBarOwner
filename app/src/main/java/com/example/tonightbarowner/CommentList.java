package com.example.tonightbarowner;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;

public class CommentList extends ActionBarActivity {
    private static ListView commentList;
    private static CommentAdapter commentAdapter;
    private String barId;
    private SwipeRefreshLayout swipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);
        Intent intent = getIntent();
        barId = intent.getStringExtra("barId");

        commentList = (ListView) findViewById(R.id.comment_list);
        commentAdapter = new CommentAdapter(this, barId);
        commentAdapter.notifyDataSetChanged();
        commentAdapter.setObjectsPerPage(10);
        commentList.setAdapter(commentAdapter);

        //Allows for refresh on swipe up
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.comments_swipe);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeLayout.setRefreshing(true);
                commentList.setAdapter(commentAdapter);
                ( new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });
        swipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
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

    public static void deleteComment(ParseObject object) {
        try {
            object.delete();
            commentList.setAdapter(commentAdapter);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
