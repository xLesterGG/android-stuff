package com.example.a4329619.lecture4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.widget.EditText;

import java.util.ArrayList;

public class Get_Details extends AppCompatActivity {

    EditText editxt1;
    EditText editxt2;
    EditText editxt3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get__details);

        editxt1 = (EditText)findViewById(R.id.editText);
        editxt2 = (EditText)findViewById(R.id.editText2);
        editxt3 = (EditText)findViewById(R.id.editText3);

    }

    @Override
    public void onBackPressed() {
        String firstName = editxt1.getText().toString();
        String surName = editxt2.getText().toString();
        String state = editxt3.getText().toString();

       // Log.d("asdadasdas", firstName + surName + state);

        Person person = new Person(firstName, surName,state);

        Intent intent = new Intent();

        ArrayList<Person> personList = new ArrayList<Person>();
        personList.add(person);
        intent.putParcelableArrayListExtra("person",personList);
        setResult(RESULT_OK,intent);

        super.onBackPressed();
    }
}
