package com.example.a4329619.a4t3;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn = (Button)findViewById(R.id.btn);
        ed = (EditText)findViewById(R.id.ed);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = ed.getText().toString();
                query = URLEncoder.encode(query);
                String url = "http://www.google.com/search?q="+ query;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

    }
}
