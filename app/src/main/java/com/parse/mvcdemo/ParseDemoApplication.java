package com.parse.mvcdemo;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Ashwani on 25-07-2015.
 */
public class ParseDemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize the singletons so their instances
        // are bound to the application process.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "UxabqWJzwN5ZBnV69ri7sRwtOnN3GWQGRIoNTEq7", "0nI6hQyhEORgeNvLhFejYvaCOYp9C5vxwdW6zgZp");

    }


}
