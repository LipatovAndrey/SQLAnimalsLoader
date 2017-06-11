package com.example.animalsloader;

import com.example.animalsloader.database.SQLiteAnimalsDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Андрей on 21.05.2017.
 */

public class AnimalStorage {
    private List<AnimalListChanged> mAnimalListChangedList;
    private SQLiteAnimalsDao mSQLiteAnimalsDao;

    public AnimalStorage(SQLiteAnimalsDao SQLiteAnimalsDao) {
        mAnimalListChangedList = new ArrayList<>();
        mSQLiteAnimalsDao = SQLiteAnimalsDao;
    }

    public void addToList(Animal animal) {
        mSQLiteAnimalsDao.insertAnimal(animal);
        for (AnimalListChanged listListeners : mAnimalListChangedList) {
            listListeners.changingAnimalList();
        }
    }

    public void removeAnimal(Animal animal) {
        mSQLiteAnimalsDao.deleteAnimal(animal);
        for (AnimalListChanged listListeners : mAnimalListChangedList) {
            listListeners.changingAnimalList();
        }
    }

    public void replaceAnimal(Animal animal) {
        mSQLiteAnimalsDao.updateAnimal(animal);
        for (AnimalListChanged listListeners : mAnimalListChangedList) {
            listListeners.changingAnimalList();
        }

    }

    public void AddChangeListListener(AnimalListChanged animalListChanged) {
        mAnimalListChangedList.add(animalListChanged);
    }

    public List<Animal> getAnimalList() {
        return mSQLiteAnimalsDao.getAnimals();
    }

    interface AnimalListChanged {
        void changingAnimalList();
    }
}
