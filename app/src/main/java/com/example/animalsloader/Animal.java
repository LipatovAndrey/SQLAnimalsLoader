package com.example.animalsloader;

import java.io.Serializable;

/**
 * Created by Андрей on 21.05.2017.
 */

public class Animal implements Serializable {
    private String mName, mSpecie;
    private int mAge;
    private long mID;

    public Animal(String mName, String mSpecie, int mAge) {
        this.mName = mName;
        this.mSpecie = mSpecie;
        this.mAge = mAge;
    }

    public Animal() {

    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSpecie() {
        return mSpecie;
    }

    public void setSpecie(String specie) {
        mSpecie = specie;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int age) {
        mAge = age;
    }

    public long getID() {
        return mID;
    }

    public void setID(long ID) {
        mID = ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Animal animal = (Animal) o;

        if (mAge != animal.mAge) return false;
        if (mID != animal.mID) return false;
        if (mName != null ? !mName.equals(animal.mName) : animal.mName != null) return false;
        return mSpecie != null ? mSpecie.equals(animal.mSpecie) : animal.mSpecie == null;

    }

    @Override
    public int hashCode() {
        int result = mName != null ? mName.hashCode() : 0;
        result = 31 * result + (mSpecie != null ? mSpecie.hashCode() : 0);
        result = 31 * result + mAge;
        result = 31 * result + (int) (mID ^ (mID >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "mName='" + mName + '\'' +
                ", mSpecie='" + mSpecie + '\'' +
                ", mAge=" + mAge +
                ", mID=" + mID +
                '}';
    }
}
