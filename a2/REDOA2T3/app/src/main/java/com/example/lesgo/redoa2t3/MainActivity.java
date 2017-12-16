package com.example.lesgo.redoa2t3;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    EditText cup ;
    EditText tables;
    EditText teas;
    TextView ml;
    Double answer=0.0;
    Double total =0.0;
    String a1="";
    String a2="";
    CheckBox cb;
    TextView l;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        restoreState(savedInstanceState);


        Button convert = (Button)findViewById(R.id.convert);
        cup = (EditText)findViewById(R.id.cups);
        tables = (EditText)findViewById(R.id.tablespoon);
        teas = (EditText)findViewById(R.id.teaspoon);
        ml = (TextView)findViewById(R.id.milli);
        cb = (CheckBox)findViewById(R.id.convert1);
        l = (TextView)findViewById(R.id.litres);


        ml.setText(a1);
        l.setText(a2);

        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true)
                {
                      l.setVisibility(View.VISIBLE);
                }
                else{
                      l.setVisibility(View.INVISIBLE);
                }
            }
        });


        cup.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    tables.setText("");
                    teas.setText("");
                }
            }
        });

        tables.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    cup.setText("");
                    teas.setText("");
                }
            }
        });


        teas.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    cup.setText("");
                    tables.setText("");
                }
            }
        });



        convert.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                String cupinput = cup.getText().toString();
                String tableinput = tables.getText().toString();
                String teainput = teas.getText().toString();

                if(!cupinput.matches(""))
                {
                    total = Double.parseDouble(cupinput);
                    answer = total *240;
                    a1 = total+ " cup(s) of water = "+ answer + " millilitres";
                    a2 = total+ " cup(s) of water = "+ answer/1000 + " litres";

                    ml.setText(a1);
                    l.setText(a2 );

                    cup.setText("");
                    answer =0.0;

                }

                if(!tableinput.matches(""))
                {
                    total = Double.parseDouble(tableinput);
                    answer = total *15;
                    a1 = total+ " tablespoon(s) of water = "+ answer + " millilitres" ;
                    a2 = total+ " tablespoon(s) of water = "+ answer/1000 + " litres"  ;

                    ml.setText(a1);
                    l.setText(a2);

                    tables.setText("");
                    answer =0.0;
                }

                if(!teainput.matches(""))
                {
                    total = Double.parseDouble(teainput);
                    answer = total *5;
                    a1 = total+ " teaspoon(s) of water = "+ answer + " millilitres" ;
                    a2 = total+ " teaspoon(s) of water = "+ answer/1000 + " litres" ;

                    ml.setText(a1);
                    l.setText(a2);

                    teas.setText("");
                    answer =0.0;
                }
                return true;
            }
        });
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putString("a1", a1);
        outState.putString("a2", a2);

        super.onSaveInstanceState(outState);
    }


    private void restoreState(Bundle bundle){
        if(bundle== null)
            return ;

        if(bundle.getInt("field")==1)
        {
            String x = String.valueOf(bundle.getDouble("input"));
            cup.setText("abc");
        }

        a1 =(bundle.getString("a1"));
        a2 =(bundle.getString("a2"));

    }
}