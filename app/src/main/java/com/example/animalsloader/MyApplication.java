package com.example.animalsloader;

import android.app.Application;

/**
 * Created by Андрей on 21.05.2017.
 */

public class MyApplication extends Application implements ProviderStorage {
    private AnimalStorage mAnimalStorage;

    @Override
    public void onCreate() {
        super.onCreate();
        mAnimalStorage = new AnimalStorage();
    }

    @Override
    public AnimalStorage getAnimalStorage() {
        return mAnimalStorage;
    }
}
