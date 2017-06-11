package com.example.animalsloader;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.animalsloader.database.AnimalsContract;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static int LOADER_ID = 1;
    private ListView mListView;
    private AnimalStorage mAnimalStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAnimalStorage = ((ProviderStorage) getApplication()).getAnimalStorage();
        mListView = (ListView) findViewById(R.id.listView);

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Animal animal = (Animal) parent.getItemAtPosition(position);
                final Intent replaceAnimalIntent = new Intent(parent.getContext(), ReplaceActivity.class);

                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());

                builder.setNegativeButton(R.string.long_click_dialog_negative_button_text, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        mAnimalStorage.removeAnimal(animal);
                    }
                });
                builder.setPositiveButton(R.string.long_click_dialog_positive_button_text, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        replaceAnimalIntent.putExtra(AnimalsContract.Animals._ID, animal.getID());
                        replaceAnimalIntent.putExtra(AnimalsContract.Animals.NAME, animal.getName());
                        replaceAnimalIntent.putExtra(AnimalsContract.Animals.SPECIES, animal.getSpecie());
                        replaceAnimalIntent.putExtra(AnimalsContract.Animals.AGE, String.valueOf(animal.getAge()));

                        startActivity(replaceAnimalIntent);
                    }
                });
                builder.setTitle(R.string.long_click_dialog_title);
                builder.setCancelable(true);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return false;
            }
        });
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
        switch (item.getItemId()) {
            case R.id.addAnimalMenu:
                Intent intentAdd = new Intent(this, AddAnimalActivity.class);
                startActivity(intentAdd);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    class AnimalListLoadCallbacks implements LoaderManager.LoaderCallbacks<List<Animal>> {

        @Override
        public Loader<List<Animal>> onCreateLoader(int id, Bundle args) {
            return new AnimalLoader(MainActivity.this);
        }

        @Override
        public void onLoadFinished(Loader<List<Animal>> loader, List<Animal> data) {

            Log.d("AnimalLoader", "loadFinished");
            mListView.setAdapter(new AnimalListAdapter(data));
        }

        @Override
        public void onLoaderReset(Loader<List<Animal>> loader) {
        }
    }
}
