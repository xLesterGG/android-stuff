package com.example.lesgo.a5t1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayContact extends AppCompatActivity {
    Bundle bundle;
    DbHandler db;
    EditText txtName,txtContact,txtEmail;
    Boolean b = false;
    Button bCall,bUpdate;
    Contact  c ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_contact);

        txtName = (EditText)findViewById(R.id.eName);
        txtContact = (EditText)findViewById(R.id.eNumber);
        txtEmail = (EditText)findViewById(R.id.eEmail);
        bCall = (Button)findViewById(R.id.call);
        bUpdate = (Button)findViewById(R.id.update);

        txtName.setFocusableInTouchMode(false);
        txtContact.setFocusableInTouchMode(false);
        txtEmail.setFocusableInTouchMode(false);


        bundle = getIntent().getExtras();
        String idnum = bundle.getString("id");


        db = new DbHandler(this);
        c = db.getContact(idnum);
        txtName.setText(c.getName());
        txtContact.setText(c.getContact());
        txtEmail.setText(c.getEmail());


        bCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + Integer.parseInt( c.getContact())));
                startActivity(intent);
            }
        });

        bUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.updateContact(new Contact(c.getId(),txtName.getText().toString(),txtContact.getText().toString(),txtEmail.getText().toString()));
                Toast t =  Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT);
                t.show();

                setResult(RESULT_OK);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if(id == R.id.edit){
            if(b==false)
            {
                Toast t =  Toast.makeText(getApplicationContext(), "Edit Mode on", Toast.LENGTH_SHORT);
                t.show();

                bCall.setVisibility(View.INVISIBLE);
                bUpdate.setVisibility(View.VISIBLE);

                b=true;
                txtName.setFocusableInTouchMode(true);
                txtContact.setFocusableInTouchMode(true);
                txtEmail.setFocusableInTouchMode(true);
            }
            else
            {
                Toast t =  Toast.makeText(getApplicationContext(), "Edit Mode off", Toast.LENGTH_SHORT);
                t.show();

                bCall.setVisibility(View.VISIBLE);
                bUpdate.setVisibility(View.INVISIBLE);

                b=false;
                txtName.setFocusableInTouchMode(false);
                txtContact.setFocusableInTouchMode(false);
                txtEmail.setFocusableInTouchMode(false);

                txtName.clearFocus();
                txtContact.clearFocus();
                txtEmail.clearFocus();

            }

        }


        return super.onOptionsItemSelected(item);
    }

}
