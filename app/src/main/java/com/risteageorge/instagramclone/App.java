package com.risteageorge.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("vm74Zw3MZcLhENLhFu9Z3lq9WX9c33zExcZpckh1")
                // if defined
                .clientKey("3g4a2QyTO53LP0T0JqATEDTl9o1Y9Z8VOzKZ6Mf2")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }
}
