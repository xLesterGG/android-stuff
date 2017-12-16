package com.example.lesgo.a5t2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView web = new WebView(this);
        web = (WebView)findViewById(R.id.webview);
        web.loadUrl("file:///android_asset/index.html");

    }
}
