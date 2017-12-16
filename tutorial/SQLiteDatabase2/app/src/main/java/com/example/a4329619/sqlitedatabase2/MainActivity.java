package com.example.a4329619.sqlitedatabase2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addBtn = (Button)findViewById(R.id.button);
        Button getBtn = (Button)findViewById(R.id.button2);
        Button delBtn = (Button)findViewById(R.id.button3);

        db = new DatabaseHandler(this);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addContact(new Contact("AAA","111111"));
                db.addContact(new Contact("BBB","222222"));
                db.addContact(new Contact("CCC","333333"));
                db.addContact(new Contact("DDD","444444"));
            }
        });

        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Contact> contacts = db.getAllContacts();

                for(Contact c:contacts ){
                    Log.d("Record",Integer.toString(c.getId())+ ", " + c.getName() + " , " + c.getContact());

                }
            }
        });

        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = db.deleteAllContacts();
                Log.d("Delete records",msg);

                List<Contact> contactList = db.getAllContacts();
                if(contactList==null)
                    Log.d("Search record","Record not found");
            }
        });
    }
}
