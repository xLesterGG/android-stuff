package com.example.lesgo.a5t1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DbHandler db;
    List<Contact> contacts;
    RecyclerViewAdapter adapter ;
    RecyclerView recycle_view;
    AlertDialog.Builder builder1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        builder1 = new AlertDialog.Builder(this);
        db = new DbHandler(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),AddContact.class);
                startActivityForResult(intent,0);
            }
        });

        contacts= db.getAllContacts();

        adapter = new RecyclerViewAdapter(contacts);
        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String name, String contact,String id) {
                Log.d("ON ITEM CLICK", name + contact);

                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), DisplayContact.class);
                intent.putExtra("id",id);

                startActivityForResult(intent,2);
            }
        });


        adapter.setOnLongClickListener(new RecyclerViewAdapter.OnLongClickListener() {
            @Override
            public void OnLongItemClick(String id) {
                Log.d("ON LONG PRESS" , id);

                final Contact c = new Contact(Integer.parseInt(id));

                builder1.setTitle("Confirm deletion");
                builder1.setMessage("Are you sure you want to delete this contact");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int ida) {
                                db.deleteContact(c);

                                finish();
                                startActivity(getIntent());

                                dialog.cancel();

                                Toast t =  Toast.makeText(getApplicationContext(), "Contact Deleted", Toast.LENGTH_SHORT);
                                t.show();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        });

        recycle_view = (RecyclerView)findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recycle_view.setLayoutManager(layoutManager);
        recycle_view.setItemAnimator(new DefaultItemAnimator());
        recycle_view.setAdapter(adapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data == null)
        {
            Log.d("onactivityresult","is null");
            if(requestCode == 2)
            {
                finish();
                startActivity(getIntent());

            }
        }
        else
        {
            if(requestCode == 0)
            {
                ArrayList<Contact> contactdata = data.getParcelableArrayListExtra("contact");
                Contact d;
                d = contactdata.get(0);

                Log.d("here", d.getName() +","+ d.getContact() +","+ d.getEmail());
                db.addContact(d);

                finish();
                startActivity(getIntent());

            }
        }
    }

}
