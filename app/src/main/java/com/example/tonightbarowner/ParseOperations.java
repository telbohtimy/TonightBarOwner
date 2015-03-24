package com.example.tonightbarowner;

import android.util.Log;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;


public class ParseOperations extends ParseObject {

    public static boolean validate=false;
    public static String barId;

    public static boolean isValidate() {
        return validate;
    }
    public static String isBarId() {
        return barId;
    }

    public static void validateLogin(String username, final String password){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("BarOwners");
        query.whereEqualTo("username", username);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (object == null) {
                    Log.d("TEST", "The getFirst request failed.");
                } else {
                    Log.d("TEST", "Passed "+object.getString("password")+" "+object.getString("bar"));
                    if (password.equals(object.getString("password"))){
                        validate=true;
                    }
                    barId=object.getString("bar");
                }
            }
        });
    }
}

