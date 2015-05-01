package com.herokuapp.ezhao.desserts;

import android.app.Application;
import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;

public class DessertApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Configuration.Builder configurationBuilder = new Configuration.Builder(this);
        configurationBuilder.addModelClass(Dessert.class);
        ActiveAndroid.initialize(configurationBuilder.create());
    }
}
