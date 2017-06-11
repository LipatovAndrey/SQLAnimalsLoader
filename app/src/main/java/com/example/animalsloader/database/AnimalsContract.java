package com.example.animalsloader.database;

import android.provider.BaseColumns;

/**
 * Created by Андрей on 09.06.2017.
 */
public class AnimalsContract {

    public static class Animals implements BaseColumns {

        public static final String SPECIES = "species";
        public static final String AGE = "age";
        public static final String NAME = "name";

    }
}