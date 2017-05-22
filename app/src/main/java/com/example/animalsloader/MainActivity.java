package com.example.animalsloader;

import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private AnimalStorage mAnimalStorage;
    private static int LOADER_ID=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAnimalStorage =((ProviderStorage)getApplication()).getAnimalStorage();
        mListView = (ListView) findViewById(R.id.listView);
    }

    @Override
    protected void onResume() {
        super.onResume();
       getSupportLoaderManager().initLoader(LOADER_ID, null, new AnimalListLoadCallbacks());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.addAnimalMenu:
                Intent intent= new Intent(this, AddAnimalActivity.class);
                startActivity(intent);
                break;
            case R.id.removeAnimalMenu:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    class AnimalListLoadCallbacks implements LoaderManager.LoaderCallbacks<List<Animal>>{

        @Override
        public Loader<List<Animal>> onCreateLoader(int id, Bundle args) {
            return new AnimalLoader(MainActivity.this);
        }

        @Override
        public void onLoadFinished(Loader<List<Animal>> loader, List<Animal> data) {
            mListView.setAdapter(new AnimalListAdapter(data));
        }

        @Override
        public void onLoaderReset(Loader<List<Animal>> loader) {
        }
    }
}
