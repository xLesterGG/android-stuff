package com.example.a4329619.task2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    int num =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = (Button)findViewById(R.id.button);
        Button btn2 = (Button)findViewById(R.id.button2);



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Button 1 pressed",Toast.LENGTH_LONG).show();
            }
        });



        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView a = (TextView)findViewById(R.id.textView);
                a.setText("I was clicked " + num +" number of times");

                if(num%2 ==0)
                {
                    a.setTextColor(Color.RED);
                }
                else{
                    a.setTextColor(Color.BLUE);

                }
                num = num + 1;

                Log.d("Tag", " I am here"+ num);
            }
        });



    }
}
