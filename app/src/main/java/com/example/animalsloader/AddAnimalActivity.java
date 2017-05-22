package com.example.animalsloader;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Андрей on 21.05.2017.
 */

public class AddAnimalActivity extends AppCompatActivity {
    private EditText mNameView, mAgeView, mSpesieView;
    private Button mBtnAddView;
    private AnimalStorage mAnimalStorage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_animal);
        mAnimalStorage = ((ProviderStorage)getApplication()).getAnimalStorage();
        mNameView = (EditText) findViewById(R.id.newAnimalname);
        mAgeView = (EditText) findViewById(R.id.newAnimalAge);
        mSpesieView = (EditText) findViewById(R.id.newAnimalSprcie);

        mBtnAddView = (Button) findViewById(R.id.btnadd);
        mBtnAddView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mNameView.getText().toString().length()==0||mSpesieView.getText().toString().length()==0||mAgeView.getText().toString().length()==0||!Number.class.isInstance(Integer.parseInt(mAgeView.getText().toString()))){
                    Toast.makeText(AddAnimalActivity.this, "one of editText's is empty, or age not a number", Toast.LENGTH_SHORT).show();
                }else{
                    mAnimalStorage.addToList(new Animal(mNameView.getText().toString(), mSpesieView.getText().toString(), Integer.parseInt(mAgeView.getText().toString())));
                    finish();
                }
            }
        });
    }
}
