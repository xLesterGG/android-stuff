package com.example.lesgo.a4t222;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    Button btn;
    Button btn1;
    EditText nameTxt;
    EditText naTxt;
    EditText eEmail;
    Button sBtn;
    TextView msgView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        btn = (Button)findViewById(R.id.button);
        nameTxt = (EditText)findViewById(R.id.eName) ;
        naTxt = (EditText)findViewById(R.id.eNationality);
        eEmail = (EditText)findViewById(R.id.eEmail);
        btn1 = (Button)findViewById(R.id.button1);
        msgView = (TextView)findViewById(R.id.message);
        sBtn = (Button)findViewById(R.id.sButton);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),GetProfile.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent,1);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);



            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),GetNationality.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent,2);
                overridePendingTransition(R.anim.slidein, R.anim.slideout);

            }
        });

        sBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameTxt.getText().toString();
                String nationality = naTxt.getText().toString();
                String email = eEmail.getText().toString();
                Toast t =  Toast.makeText(getApplicationContext(), "Please fill in all your details", Toast.LENGTH_SHORT);
                String message= "";

                if(name.matches("")||nationality.matches("")|| email.matches(""))
                {
                    t.show();
                }
                else
                {
                    ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                    if (networkInfo != null && networkInfo.isConnected()) {
                        try
                        {
                            message = postToDB(name,nationality,email);
                        } catch(Exception e){
                            e.printStackTrace();
                        }
                        InputMethodManager inputManager = (InputMethodManager)
                                getSystemService(Context.INPUT_METHOD_SERVICE);

                        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);

                    } else {
                        // display error
                        msgView.setText("Either you have bad connection or you have no internet");
                    }
                    msgView.setText(message);

                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data==null){
            Log.d("NO RESULT", " no result");
        }else
        {
            if(requestCode == 1)
            {
                String abc = data.getStringExtra("name");
                nameTxt.setText(abc);
            }
            else if(requestCode == 2)
            {
                String abc = data.getStringExtra("nationality");
                Log.d("name is", abc);
                naTxt.setText(abc);
            }
        }

    }



    private String postToDB(String name,String nationality, String email) throws IOException {
        InputStream is = null;

        String name1 = name.replace(" ","+");

        nationality = URLEncoder.encode(nationality);
        email = URLEncoder.encode(email);

        String link = "https://cos30017-server1-tootsie.c9users.io/upload/?nm=" + name1 + "&national=" +nationality + "&email=" + email + "&author=" + "NotLester";
        try{
            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d("RESPONSE", "The response is: " + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is, 24);
            Log.d("Aaa", contentAsString);
            return contentAsString;

        } finally {
            if (is != null) {
                is.close();
            }
        }

    }

    public String readIt(InputStream stream, int len) throws IOException{
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }
}
