package com.example.animalsloader.database;

import com.example.animalsloader.Animal;

import java.util.List;

/**
 * Created by Андрей on 10.06.2017.
 */

public interface AnimalsDao {
    long insertAnimal(Animal animal);

    List<Animal> getAnimals();

    Animal getAnimalById(long id);

    int updateAnimal(Animal animal);

    int deleteAnimal(Animal animal);
}
