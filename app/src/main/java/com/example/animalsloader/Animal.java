package com.example.animalsloader;

/**
 * Created by Андрей on 21.05.2017.
 */

public class Animal{
    private final String mName, mSpecie;
    private final int mAge;

    public Animal(String mName, String mSpecie, int mAge) {
        this.mName = mName;
        this.mSpecie = mSpecie;
        this.mAge = mAge;
    }

    public String getmName() {
        return mName;
    }

    public String getmSpecie() {
        return mSpecie;
    }

    public int getmAge() {
        return mAge;
    }
}
