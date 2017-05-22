package com.example.animalsloader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by Андрей on 21.05.2017.
 */

public class AnimalLoader extends android.support.v4.content.AsyncTaskLoader<List<Animal>> implements AnimalStorage.AnimalListChanged{
    private AnimalStorage mAnimalStorage;
    private List<Animal> mCachList;
    public AnimalLoader(Context context) {
        super(context);
        this.mAnimalStorage = ((ProviderStorage)context.getApplicationContext()).getAnimalStorage();
        this.mAnimalStorage.AddChangeListListener(this);
    }

    @Override
    public List<Animal> loadInBackground() {
        return mAnimalStorage.getAnimalList();
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (mCachList ==null||takeContentChanged()){
            forceLoad();
        }
    }

    @Override
    public void deliverResult(List<Animal> data) {
        super.deliverResult(data);
        mCachList = data;
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
    }

    @Override
    public void isChanged() {
        onContentChanged();
    }
}
