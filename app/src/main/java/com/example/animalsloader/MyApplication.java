package com.example.animalsloader;

import android.app.Application;

import com.example.animalsloader.database.SQLiteAnimalsDao;

/**
 * Created by Андрей on 21.05.2017.
 */

public class MyApplication extends Application implements ProviderStorage {
    private AnimalStorage mAnimalStorage;
    private SQLiteAnimalsDao mSQLiteAnimalsDao;

    @Override
    public void onCreate() {
        super.onCreate();
        mSQLiteAnimalsDao = new SQLiteAnimalsDao(this);
        mAnimalStorage = new AnimalStorage(mSQLiteAnimalsDao);

    }

    @Override
    public AnimalStorage getAnimalStorage() {
        return mAnimalStorage;
    }
}
