package com.bnsantos.test.support.library;

import android.app.Application;

/**
 * Created by bruno on 08/01/16.
 */
public class App extends Application {
    public static String API_KEY = "YOUR_API_KEY_HERE";

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
