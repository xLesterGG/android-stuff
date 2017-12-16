package com.example.a4329619.asynctask2;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    ImageView imgView;
    Button btn;
    private ProgressDialog progressDialog;
    public static final int BAR_TYPE = 1;
    public static final int SPINNER = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgView=(ImageView)findViewById(R.id.imageview);
        btn = (Button)findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DownloadImage().execute("http://api.androidhive.info/progressdialog/hive.jpg");
            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Downloading in progress ...");

        switch(id){
            case BAR_TYPE:
                progressDialog.setMax(100);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setIndeterminate(false);
                progressDialog.show();
                break;
            case SPINNER:
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();
                break;
            default: return null;
        }

        return progressDialog;
    }

    class DownloadImage extends AsyncTask<String,String,String>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(SPINNER);
        }

        @Override
        protected String doInBackground(String... strings) {
            int readBytes;

            try{
                URL url = new URL(strings[0]);
                URLConnection connection = url.openConnection();
                long fileLength = connection.getContentLength();

                InputStream input = new BufferedInputStream(url.openStream(),10*1024);
                OutputStream output = new FileOutputStream(Environment.getExternalStorageDirectory().getPath()+ "/photo.jpg");
                byte data[] = new byte[1024];
                long totalBytes = 0;

                while((readBytes=input.read(data))!=-1)
                {
                    totalBytes = totalBytes+readBytes;
                    Long percentage = (totalBytes*100)/fileLength;
                    publishProgress(String.valueOf(percentage));
                    output.write(data,0,readBytes);
                }

                output.flush();
                output.close();
                input.close();

                String path = Environment.getExternalStorageDirectory().toString()+"/photo.jpg";
                return path;

            }catch(Exception e){
                Log.d("Error", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            progressDialog.setProgress(Integer.valueOf(values[0]));
        }

        @Override
        protected void onPostExecute(String path) {
            super.onPostExecute(path);
            dismissDialog(SPINNER);
            imgView.setImageDrawable(Drawable.createFromPath(path));
        }
    }
}
