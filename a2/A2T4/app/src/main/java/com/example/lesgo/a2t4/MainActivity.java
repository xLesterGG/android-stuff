package com.example.lesgo.a2t4;

import android.content.res.Configuration;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    RadioGroup radiog;
    ImageView imgv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radiog = (RadioGroup)findViewById(R.id.radiogroup);
        imgv = (ImageView)findViewById(R.id.imgview);


        radiog.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View radioButton = radiog.findViewById(checkedId);
                int index = radiog.indexOfChild(radioButton);

                switch(index){
                    case 0:
                        imgv.setImageResource(R.drawable.gabe);
                        break;

                    case 1:
                        imgv.setImageResource(R.drawable.rtz);
                        break;

                    case 2:
                        imgv.setImageResource(R.drawable.ee);
                        break;
                }
            }
        });


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        RadioGroup radiog = (RadioGroup)findViewById(R.id.radiogroup);

        switch(getResources().getConfiguration().orientation)
        {

            case Configuration.ORIENTATION_PORTRAIT:
                radiog.setOrientation(LinearLayout.VERTICAL);
                break;
            case Configuration.ORIENTATION_LANDSCAPE:
                radiog.setOrientation(LinearLayout.HORIZONTAL);
                break;

        }


    }
}
