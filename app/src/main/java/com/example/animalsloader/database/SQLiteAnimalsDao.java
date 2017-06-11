package com.example.animalsloader.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.animalsloader.Animal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Андрей on 09.06.2017.
 */

public class SQLiteAnimalsDao extends SQLiteOpenHelper implements AnimalsDao {

    private final static String DB_NAME = "animals_dataBase.db";
    private final static int DB_VERSION = 1;
    private static final String TABLE_NAME = "animals";

    public SQLiteAnimalsDao(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
    }

    private static Animal createAnimal(Cursor cursor) {
        Animal animal = new Animal();
        animal.setID(getLong(cursor, AnimalsContract.Animals._ID));
        animal.setSpecie(getString(cursor, AnimalsContract.Animals.SPECIES));
        animal.setAge(getInt(cursor, AnimalsContract.Animals.AGE));
        animal.setName(getString(cursor, AnimalsContract.Animals.NAME));
        return animal;
    }

    private static ContentValues createValuesFromAnimal(Animal animal) {
        ContentValues values = new ContentValues();
        values.put(AnimalsContract.Animals.SPECIES, animal.getSpecie());
        values.put(AnimalsContract.Animals.AGE, animal.getAge());
        values.put(AnimalsContract.Animals.NAME, animal.getName());
        return values;
    }

    private static long getLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }

    private static String getString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    private static int getInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Create", "DataBase");
        String sql = "CREATE TABLE " + TABLE_NAME + "(" +
                AnimalsContract.Animals._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                AnimalsContract.Animals.SPECIES + " TEXT NOT NULL, " +
                AnimalsContract.Animals.AGE + " INTEGER NOT NULL, " +
                AnimalsContract.Animals.NAME + " TEXT NOT NULL" +
                ");";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        throw new UnsupportedOperationException(
                "Not implemented yet"
        );
    }

    @Override
    public long insertAnimal(Animal animal) {

        long ID = -1;
        SQLiteDatabase db = getWritableDatabase();

        try {
            db.beginTransaction();
            ID = db.insert(TABLE_NAME, null, createValuesFromAnimal(animal));
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
        return ID;
    }

    @Override
    public List<Animal> getAnimals() {

        List<Animal> animals = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;

        try {
            db.beginTransaction();
            cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
            animals = new ArrayList<>();
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Animal animal = createAnimal(cursor);
                animals.add(animal);
                cursor.moveToNext();
            }

            db.setTransactionSuccessful();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.endTransaction();
            db.close();
        }
        return animals;
    }

    @Override
    public Animal getAnimalById(long id) {

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        Animal animal = null;
        try {
            db.beginTransaction();
            cursor = db.query(TABLE_NAME, null, AnimalsContract.Animals._ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);
            cursor.moveToFirst();
            animal = createAnimal(cursor);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return animal;
    }

    @Override
    public int updateAnimal(Animal animal) {

        SQLiteDatabase db = getWritableDatabase();
        int numberOfUpdateRow = 0;
        try {
            db.beginTransaction();
            numberOfUpdateRow = db.update(TABLE_NAME, createValuesFromAnimal(animal), AnimalsContract.Animals._ID + " = ?",
                    new String[]{String.valueOf(animal.getID())});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }

        return numberOfUpdateRow;
    }

    @Override
    public int deleteAnimal(Animal animal) {

        SQLiteDatabase db = getWritableDatabase();
        int numberOfDeletedRow = 0;
        try {
            db.beginTransaction();
            numberOfDeletedRow = db.delete(TABLE_NAME, AnimalsContract.Animals._ID + "=" + animal.getID(), null);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }

        return numberOfDeletedRow;
    }

}
