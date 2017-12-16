package com.example.lesgo.a6t1;

import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView lat,longt,add;
    //String path = "/sdcard/sample_image.jpg";
    String path = "/storage/extSdCard/sample_image.jpg";

    ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        btn = (Button)findViewById(R.id.get);
        lat = (TextView)findViewById(R.id.lat);
        longt = (TextView)findViewById(R.id.longt);
        add = (TextView)findViewById(R.id.add);
        img = (ImageView)findViewById(R.id.img);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File file = new File(path);
                if(file.exists())
                {
                    img.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
                }

                try{
                    ExifInterface ex = new ExifInterface(path);
                    float[] latLong = new float[2];
                    ex.getLatLong(latLong);

                    lat.setText(String.valueOf(latLong[0]));
                    longt.setText(String.valueOf(latLong[1]));

                    JSONObject aaa;

                    aaa = callApi(String.valueOf(latLong[0]),String.valueOf(latLong[1]));

                    //Log.d("return",aaa.toString());


                    JSONArray result = aaa.getJSONArray("results");
                    JSONObject first = result.getJSONObject(0);
                    String address = first.getString("formatted_address");


                    add.setText(address);
                    Log.d("return",address);



                }catch(Exception e)
                {
                    e.printStackTrace();
                }

            }
        });
    }

    private JSONObject callApi(String lat,String longt) throws IOException {
        InputStream is = null;
        JSONObject obj = new JSONObject();


        String link = "https://maps.googleapis.com/maps/api/geocode/json?latlng="+ lat +","+ longt + "&key=AIzaSyDc-o9R0yB-P3Exd3wOcNSrMCeWcUR4pt4";
        try{
            StringBuilder stringBuilder = new StringBuilder();

            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 );
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);


            conn.connect();
            int response = conn.getResponseCode();
            Log.d("RESPONSE", "The response is: " + response);
            is = conn.getInputStream();

            int b;
            while((b = is.read())!= -1){
                stringBuilder.append((char)b);
            }

            try{
                obj = new JSONObject(stringBuilder.toString());
            } catch (JSONException e)
            {
                e.printStackTrace();
            }

        } finally {
            if (is != null) {
                is.close();
            }
        }
        return obj;

    }
}
