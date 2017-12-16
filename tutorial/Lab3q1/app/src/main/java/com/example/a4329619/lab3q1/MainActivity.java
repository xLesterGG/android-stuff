package com.example.a4329619.lab3q1;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    boolean bulb = false;
    int count =0;
    Camera mCam;

   // Camera mCam = Camera.open();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final ImageView bulbImageView = (ImageView)findViewById(R.id.bulbview);
        restoreState(savedInstanceState);

       /* if(bulb== true)
        Log.d("boolean","true");
        else
            Log.d("boolean","false");*/



        if(bulb==false) {
            bulbImageView.setImageResource(R.mipmap.ic_launcher);
        }
        else {
            bulbImageView.setImageResource(R.mipmap.on);

                mCam = Camera.open();
                Camera.Parameters p = mCam.getParameters();
                p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                mCam.setParameters(p);
                mCam.startPreview();


        }

        bulbImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_UP:
                        if(bulb==false)
                        {
                            bulbImageView.setImageResource(R.mipmap.on);
                            bulb = true;

                            /*if(count >0)
                            {*/
                                mCam = Camera.open();
                                Camera.Parameters p = mCam.getParameters();
                                p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                                mCam.setParameters(p);
                                mCam.startPreview();
                            //}
                           /* else{
                                Camera.Parameters p = mCam.getParameters();
                                p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                                mCam.setParameters(p);
                                mCam.startPreview();
                            }*/



                        }
                        else
                        {
                            bulbImageView.setImageResource(R.mipmap.ic_launcher);
                            bulb = false;
                            count = count +1;
                            mCam.stopPreview();
                            mCam.release();
                        }
                }
                return true;
            }
        });





    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putBoolean("BULB",bulb);
        super.onSaveInstanceState(outState);
    }

    private void restoreState(Bundle bundle){
        if(bundle==null)
            return;

        bulb = bundle.getBoolean("BULB");
    }
}
