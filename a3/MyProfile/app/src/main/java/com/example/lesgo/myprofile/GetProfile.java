package com.example.lesgo.myprofile;

import android.content.Intent;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class GetProfile extends AppCompatActivity {

    EditText eText1;
    EditText eText2;
    EditText eText3;
    RadioGroup rg;
    Boolean bat=false;
    Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_profile);
        setTitle("Get Profile");

        eText1 = (EditText)findViewById(R.id.editText1);
        eText2 = (EditText)findViewById(R.id.editText2);
        eText3 = (EditText)findViewById(R.id.editText3);
        rg = (RadioGroup)findViewById(R.id.radiogroup);
        create = (Button)findViewById(R.id.createButton);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View radioBtn = rg.findViewById(checkedId);
                int index = rg.indexOfChild(radioBtn);

                switch(index){
                    case 0:
                        bat = false;
                        break;
                    case 1:
                        bat = true;
                        break;
                }

            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Toast t =  Toast.makeText(getApplicationContext(), "Please fill in all your details", Toast.LENGTH_SHORT);
            String name = eText1.getText().toString();
            String profession = eText2.getText().toString();
            String contact = eText3.getText().toString();

            if(name.matches("") || profession.matches("")|| contact.matches(""))
            {
                t.cancel();
                t =  Toast.makeText(getApplicationContext(), "Please fill in all your details", Toast.LENGTH_SHORT);
                t.show();
            }
            else
            {
                t.cancel();

                t = Toast.makeText(getApplicationContext(), "Profile created", Toast.LENGTH_SHORT);
                t.show();

                Profile profile = new Profile(name,profession,contact,bat);

                Log.d("LOG",profile.getBat().toString());

                Intent intent = new Intent();
                ArrayList<Profile> profileList = new ArrayList<Profile>();
                profileList.add(profile);

                intent.putParcelableArrayListExtra("profile",profileList); // needs extend parcelrable
                setResult(RESULT_OK,intent);
            }

            }
        });

    }

}
