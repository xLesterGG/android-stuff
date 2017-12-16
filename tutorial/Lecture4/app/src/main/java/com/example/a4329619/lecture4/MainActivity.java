package com.example.a4329619.lecture4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),Get_Details.class);
                startActivityForResult(intent,0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data==null)
            Log.d("NO RESULT","no result");
        else
        {
            ArrayList<Person> personData = data.getParcelableArrayListExtra("person");
            Person person = personData.get(0);

            LinearLayout linearLayout = (LinearLayout)findViewById(R.id.inner_layout);
            TextView tv = new TextView(this);
            tv.setText(person.toString());
            linearLayout.addView(tv);
        }
    }



}
