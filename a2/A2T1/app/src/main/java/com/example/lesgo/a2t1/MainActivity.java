package com.example.lesgo.a2t1;

import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    boolean bulb = false;
    Camera mCam ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView bulbImageView = (ImageView)findViewById(R.id.bulbview);
        restoreState(savedInstanceState);


        mCam = Camera.open();


        if(bulb == false)
        {
            bulbImageView.setImageResource(R.drawable.off);
        }
        else
        {
            bulbImageView.setImageResource(R.drawable.on);

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
                            bulbImageView.setImageResource(R.drawable.on);
                            bulb = true;

                            Camera.Parameters p = mCam.getParameters();
                            p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                            mCam.setParameters(p);
                            mCam.startPreview();

                        }
                        else
                        {
                            bulbImageView.setImageResource(R.drawable.off);
                            bulb = false;

                            Camera.Parameters p = mCam.getParameters();
                            p.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                            mCam.setParameters(p);


                        }
                }
                return true;
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putBoolean("BULB",bulb);

            mCam.stopPreview();
            mCam.release();

        super.onSaveInstanceState(outState);
    }

    private void restoreState(Bundle bundle){
        if(bundle==null)
            return;

        bulb = bundle.getBoolean("BULB");
    }
}
