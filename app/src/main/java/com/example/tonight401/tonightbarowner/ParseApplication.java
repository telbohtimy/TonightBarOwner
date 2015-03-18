package com.example.tonight401.tonightbarowner;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseCrashReporting;
import com.parse.ParseObject;
import com.parse.ParseUser;
public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize Crash Reporting.
        ParseCrashReporting.enable(this);
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "WmiRuMFBYBvbiSfYhngCJl4dqy2zs1FkwFC5CnRh", "Ezo6Lc63m5XNo6eRA3ZmQsiaV6cJPJMrkfMXb7Fz");

        ParseUser.enableAutomaticUser();

     /*   ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "ANother");
        testObject.saveInBackground();*/

    }
}