package com.example.animalsloader;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Андрей on 21.05.2017.
 */

public class AnimalStorage {
    private List<Animal> mAnimalList;
    private List<AnimalListChanged> mAnimalListChangedList;

    public AnimalStorage(){
        mAnimalListChangedList = new ArrayList<>();
        mAnimalList = new ArrayList<>();
        mAnimalList.add(new Animal("Мурка", "Cat", 3));
        mAnimalList.add(new Animal("Матроскин", "Cat", 13));
        mAnimalList.add(new Animal("Мишель", "Dog", 6));
        mAnimalList.add(new Animal("Молния", "Horse", 8));
        mAnimalList.add(new Animal("Калоша", "Duck", 1));
        mAnimalList.add(new Animal("Ветерок", "Dove", 6));
        mAnimalList.add(new Animal("Маруся", "Dog", 5));
        mAnimalList.add(new Animal("Шип", "Horse", 7));
        mAnimalList.add(new Animal("Коготь", "Duck", 4));
        mAnimalList.add(new Animal("Горбатый", "Cat", 3));
    }
    public void addToList(Animal animal){
        Log.e("AnimalStorage","addToList");
        mAnimalList.add(animal);
        for(AnimalListChanged listListeners: mAnimalListChangedList){
            listListeners.isChanged();
            Log.e("ListListeners","isChanged()");
        }
    }
    public void AddChangeListListener(AnimalListChanged animalListChanged){
        mAnimalListChangedList.add(animalListChanged);
    }

    public List<Animal> getAnimalList() {
        return mAnimalList;
    }

    interface AnimalListChanged{
        void isChanged();
    }
}
