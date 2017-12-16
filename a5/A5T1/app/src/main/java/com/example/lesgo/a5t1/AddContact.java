package com.example.lesgo.a5t1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddContact extends AppCompatActivity {

    EditText txtName;
    EditText txtNum;
    EditText txtEmail;
    ImageView btnSubmit;

    DbHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        db = new DbHandler(this);

        List<Contact> contacts= db.getAllContacts();
        if (contacts!=null){
            for(Contact c:contacts){
                Log.d("record in add",Integer.toString(c.getId())+ "," + c.getName()+ "," + c.getContact()+"," + c.getEmail() );
            }
        }
        else{
            Log.d("null","null");
        }


        txtName = (EditText)findViewById(R.id.nAme);
        txtNum = (EditText)findViewById(R.id.nUmber);
        txtEmail = (EditText)findViewById(R.id.eMail);

        btnSubmit = (ImageView)findViewById(R.id.addBtn);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t =  Toast.makeText(getApplicationContext(), "Please fill in all your details", Toast.LENGTH_SHORT);

                String name = txtName.getText().toString();
                String number = txtNum.getText().toString();
                String email = txtEmail.getText().toString();

                if(name.matches("")||number.matches("")||email.matches(""))
                {
                    t.cancel();
                    t =  Toast.makeText(getApplicationContext(), "Please fill in all your details", Toast.LENGTH_SHORT);
                    t.show();
                }
                else
                {
                    t.cancel();

                    t = Toast.makeText(getApplicationContext(), "Contact created", Toast.LENGTH_SHORT);
                    t.show();

                    Contact c = new Contact(name,number,email);

                    Intent intent = new Intent();
                    ArrayList<Contact> contactList = new ArrayList<>();
                    contactList.add(c);

                    intent.putParcelableArrayListExtra("contact",contactList);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });

    }
}
