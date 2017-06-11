package com.example.animalsloader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.animalsloader.database.AnimalsContract;

public class ReplaceActivity extends AppCompatActivity {
    private EditText mAnimalNameEditText, mAnimalSpecieEditText, mAnimalAgeEditText, mAnimalIDEditText;
    private String mFieldName, mFieldAge, mFieldSpecie;
    private long mID;
    private AnimalStorage mAnimalStorage;
    private Button mButtonChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replace);

        mAnimalStorage = ((MyApplication) getApplication()).getAnimalStorage();
        mAnimalAgeEditText = (EditText) findViewById(R.id.ReplaceAnimalAge);
        mAnimalNameEditText = (EditText) findViewById(R.id.ReplaceAnimalname);
        mAnimalSpecieEditText = (EditText) findViewById(R.id.ReplaceAnimalSpecie);
        mAnimalIDEditText = (EditText) findViewById(R.id.ReplaceAnimALiD);
        mButtonChange = (Button) findViewById(R.id.btnReplace);

        Intent intent = getIntent();
        mFieldAge = intent.getStringExtra(AnimalsContract.Animals.AGE);
        mFieldSpecie = intent.getStringExtra(AnimalsContract.Animals.SPECIES);
        mFieldName = intent.getStringExtra(AnimalsContract.Animals.NAME);

        mAnimalAgeEditText.setText(mFieldAge);
        mAnimalNameEditText.setText(mFieldName);
        mAnimalSpecieEditText.setText(mFieldSpecie);

        mID = intent.getLongExtra(AnimalsContract.Animals._ID, -1);
        mAnimalIDEditText.setText(String.valueOf(mID));
        mButtonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animal animal = new Animal(mAnimalNameEditText.getText().toString(), mAnimalSpecieEditText.getText().toString(), Integer.parseInt(mAnimalAgeEditText.getText().toString()));
                animal.setID(mID);
                mAnimalStorage.replaceAnimal(animal);
                finish();
            }
        });


    }
}
