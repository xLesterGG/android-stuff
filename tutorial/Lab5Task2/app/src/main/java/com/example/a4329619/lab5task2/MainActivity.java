package com.example.a4329619.lab5task2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    InputStream inputStream;
   // ArrayAdapter<String> adapter;

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv = (ListView)findViewById(R.id.listview);
        tv = (TextView)findViewById(R.id.txtview);


        inputStream = getResources().openRawResource(R.raw.android_dev_agreement);
        Set<String> words = getWords(inputStream);

        ArrayList<String> newA = new ArrayList<String>();

        for (String s : words) {
            newA.add(s);
        }


        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,newA);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String input = (String)adapterView.getItemAtPosition(i);
                tv.setText(input);

                if(input.length()>5)
                {
                    tv.setTextColor(Color.parseColor("#FF0000"));
                }
                else
                {
                    tv.setTextColor(Color.parseColor("#00FF00"));

                }

            }
        });

    }

    private Set<String> getWords(InputStream res)
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(res));
        String line;
        Set<String> words = new HashSet<String>(2000); // to store unique words
        try
        {
            while ((line = br.readLine()) != null)
            {
                String[] wordsOnLine = line.split("[\\p{Punct}\\s}]");
                for (String w : wordsOnLine)
                {
                    if (w.trim().length() < 2)
                        continue; // ignore empty and single char
                    words.add(w.toLowerCase());
                }
            }
        } catch (IOException iox)
        {
            words = null; // this is evil
        }
        return words;
    }

}
