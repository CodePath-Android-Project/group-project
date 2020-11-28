package com.example.jobfinder;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {

    // Initializes Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("qWB0LlSoPhTwV50fStn0ULVL7HTc3vwoVjzosSgu")
                .clientKey("ejwD0huYSqb9xUhMsY8XXwUT6sHEJUmnEjyGsof9")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
