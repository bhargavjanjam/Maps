package com.example.vizzu.maps;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.app.Application;

/**
 * Created by vizzu on 11/1/2016.
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //Initializing Active Android
        ActiveAndroid.initialize(this);
    }
}
