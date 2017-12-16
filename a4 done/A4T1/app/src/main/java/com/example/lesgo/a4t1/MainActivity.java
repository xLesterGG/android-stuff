package com.example.lesgo.a4t1;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    EditText nameTxt;
    EditText naTxt ;
    EditText eTxt ;
    TextView msgView;
    Button sBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        nameTxt = (EditText)findViewById(R.id.eName);
        naTxt = (EditText)findViewById(R.id.eNationality);
        eTxt = (EditText)findViewById(R.id.eEmail);
        msgView = (TextView)findViewById(R.id.message);
        sBtn = (Button)findViewById(R.id.sButton);


        sBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameTxt.getText().toString();
                String nationality = naTxt.getText().toString();
                String email = eTxt.getText().toString();
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
