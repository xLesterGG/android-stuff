package com.example.lesgo.myprofile;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn;
    Profile profile = new Profile();
    Boolean created = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button)findViewById(R.id.button);


        restoreState(savedInstanceState);

        if(created)
        {
            TextView tV1 = new TextView(this);
            TextView tV2 = new TextView(this);
            TextView tV3 = new TextView(this);


            btn.setVisibility(View.INVISIBLE);

            LinearLayout linearLayout = (LinearLayout)findViewById(R.id.mainlayout);
            ImageView iV = new ImageView(this);
            if(profile.getBat())
            {
                iV.setImageResource(R.drawable.batman);
            }
            else
            {
                iV.setImageResource(R.drawable.superman);
            }


            tV1.setText(profile.getName());
            tV1.setTextColor(Color.BLUE);


            tV2.setText(profile.getProfession());


            tV3.setText("Tap me to call");
            tV3.setTextColor(Color.RED);


            tV1.setGravity(Gravity.CENTER);
            tV2.setGravity(Gravity.CENTER);
            tV3.setGravity(Gravity.CENTER);


            int orientation=this.getResources().getConfiguration().orientation;
            if(orientation== Configuration.ORIENTATION_PORTRAIT){


                tV1.setTextSize(TypedValue.COMPLEX_UNIT_PX,110);
                tV2.setTextSize(TypedValue.COMPLEX_UNIT_PX,50);
                tV3.setTextSize(TypedValue.COMPLEX_UNIT_PX,70);

                tV1.setPadding(0,0,0,25);
                tV2.setPadding(0,0,0,25);

            }
            else{
                //code for landscape mode


                tV1.setTextSize(TypedValue.COMPLEX_UNIT_PX,20);
                tV2.setTextSize(TypedValue.COMPLEX_UNIT_PX,20);
                tV3.setTextSize(TypedValue.COMPLEX_UNIT_PX,40);
            }


            linearLayout.addView(iV);
            linearLayout.addView(tV1);
            linearLayout.addView(tV2);



            tV3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + Integer.parseInt( profile.getContact())));
                    startActivity(intent);
                }
            });

            linearLayout.addView(tV3);

        }

        setTitle("My Profile");


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),GetProfile.class);
                startActivityForResult(intent,0);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(data==null)
            Log.d("NO RESULT", " no result");
        else
        {
            created=true;
            ArrayList<Profile> profileData = data.getParcelableArrayListExtra("profile");
            profile = profileData.get(0);

            TextView tV1 = new TextView(this);
            TextView tV2 = new TextView(this);
            TextView tV3 = new TextView(this);


            btn.setVisibility(View.INVISIBLE);

            LinearLayout linearLayout = (LinearLayout)findViewById(R.id.mainlayout);
            ImageView iV = new ImageView(this);
            if(profile.getBat())
            {
                iV.setImageResource(R.drawable.batman);
            }
            else
            {
                iV.setImageResource(R.drawable.superman);
            }


            tV1.setText(profile.getName());
            tV1.setTextColor(Color.BLUE);


            tV2.setText(profile.getProfession());


            tV3.setText("Tap me to call");
            tV3.setTextColor(Color.RED);


            tV1.setGravity(Gravity.CENTER);
            tV2.setGravity(Gravity.CENTER);
            tV3.setGravity(Gravity.CENTER);


            int orientation=this.getResources().getConfiguration().orientation;
            if(orientation== Configuration.ORIENTATION_PORTRAIT){


                tV1.setTextSize(TypedValue.COMPLEX_UNIT_PX,110);
                tV2.setTextSize(TypedValue.COMPLEX_UNIT_PX,50);
                tV3.setTextSize(TypedValue.COMPLEX_UNIT_PX,70);

                tV1.setPadding(0,0,0,25);
                tV2.setPadding(0,0,0,25);

            }
            else{
                //code for landscape mode


                tV1.setTextSize(TypedValue.COMPLEX_UNIT_PX,20);
                tV2.setTextSize(TypedValue.COMPLEX_UNIT_PX,20);
                tV3.setTextSize(TypedValue.COMPLEX_UNIT_PX,40);
            }


            linearLayout.addView(iV);
            linearLayout.addView(tV1);
            linearLayout.addView(tV2);



            tV3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + Integer.parseInt( profile.getContact())));
                    startActivity(intent);
                }
            });

            linearLayout.addView(tV3);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {

        if(created)
        {
            outState.putBoolean("bat",profile.getBat());
            outState.putString("contact",profile.getContact());
            outState.putString("profession",profile.getProfession());
            outState.putString("name",profile.getName());
            outState.putBoolean("created",created);
        }

        super.onSaveInstanceState(outState);
    }

    public void restoreState(Bundle bundle){
        if(bundle== null)
            return ;

        profile.setBat(bundle.getBoolean("bat"));
        profile.setContact(bundle.getString("contact"));
        profile.setProfession(bundle.getString("profession"));
        profile.setName(bundle.getString("name"));

        created  = bundle.getBoolean("created") ;

    }
}
