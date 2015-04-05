package com.example.tonightbarowner;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.parse.DeleteCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommentAdapter extends ParseQueryAdapter<ParseObject>{
    public CommentAdapter(Context context, final String venueIds, final int sort) {
        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            @Override
            public ParseQuery create() {
                ParseQuery query = new ParseQuery("Comments");
                query.whereEqualTo("barId", venueIds);
                if (sort == 0) {
                    query.orderByDescending("createdAt");
                } else if (sort == 1) {
                    query.orderByDescending("likes");
                } else if (sort == 2) {
                    query.orderByDescending("dislikes");
                }
                return query;
            }
        });
    }

    @Override
    public View getItemView(final ParseObject object, View v, ViewGroup parent) {
        if (v == null) {
            v = View.inflate(getContext(), R.layout.comment_item, null);
        }

        super.getItemView(object, v, parent);

        ParseImageView commentImage = (ParseImageView) v.findViewById(R.id.comment_image);
        ParseFile imageFile = object.getParseFile("photo");
        if (imageFile != null) {
            commentImage.setParseFile(imageFile);
            commentImage.loadInBackground();
        }
        else{
            commentImage.setImageResource(android.R.color.transparent);
        }


        TextView userTextView = (TextView) v.findViewById(R.id.user);
        userTextView.setText(object.getString("user"));

        TextView postTimeTextView = (TextView) v.findViewById(R.id.post_time);
        Date postTime = object.getCreatedAt();
        Date currentTime = new Date();
        long diff = currentTime.getTime() - postTime.getTime();
        long diffSeconds = diff/1000;
        long diffMinutes = diff/(1000 * 60);
        long diffHours = diff/(1000 * 60 * 60);
        long diffDays = diff/(1000 * 60 * 60 * 24);
        long diffWeeks = diff/(1000 * 60 * 60 * 24 * 7);

        String diffString = "";
        if (diffWeeks > 0) {
            diffString = diffWeeks + " week";
            if (diffWeeks > 1) {
                diffString += "s";
            }
            postTimeTextView.setText(diffString + " ago");

        } else if (diffDays > 0) {
            diffString = diffDays + " day";
            if (diffDays > 1) {
                diffString += "s";
            }
            postTimeTextView.setText(diffString + " ago");

        } else if (diffHours > 0) {
            diffString = diffHours + " hour";
            if (diffHours > 1) {
                diffString += "s";
            }
            postTimeTextView.setText(diffString + " ago");

        } else if (diffMinutes > 0) {
            diffString = diffMinutes + " minute";
            if (diffMinutes > 1) {
                diffString += "s";
            }
            postTimeTextView.setText(diffString + " ago");

        } else if (diffSeconds >= 0) {
            diffString = diffSeconds + " second";
            if (diffSeconds > 1) {
                diffString += "s";
            }
            postTimeTextView.setText(diffString + " ago");

        } else {
            SimpleDateFormat df = new SimpleDateFormat("MMM d");
            String postDate = df.format(postTime);
            postTimeTextView.setText(postDate);
        }

        TextView commentTextView = (TextView) v.findViewById(R.id.comment_text);
        commentTextView.setText(object.getString("commentText"));

        Integer likes = object.getInt("likes");
        Integer dislikes = object.getInt("dislikes");
        final TextView likesTextView = (TextView) v.findViewById(R.id.likes);
        final TextView dislikesTextView = (TextView) v.findViewById(R.id.dislikes);
        likesTextView.setText(likes.toString());
        dislikesTextView.setText(dislikes.toString());

        ImageButton deleteButton = (ImageButton) v.findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommentList.deleteComment(object);
            }
        });

        return v;
    }
}
