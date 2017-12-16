package com.example.a4329619.bottomsheet;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private BottomSheetBehaviour bottomBehaviour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String images[] = new String[] {"Monster 1", "Monster 2", "Monster 3", "Monster 4", "Monster 5"};

        final ListView lv = (ListView)findViewById(R.id.listView);
        final ImageView imgView =(ImageView)findViewById(R.id.imageView);
        View bottomView = findViewById(R.id.bottom_sheet_layout);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        lv.setAdapter(adapter);

        bottomBehaviour= BottomSheetBehaviour.from(bottomView);
        bottomBehaviour.setState(BottomSheetBehaviour.STATE_HIDDEN);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String clicked = (String)lv.getItemAtPosition(i);
                clicked = clicked.replaceAll("\\s","");
                clicked = clicked.toLowerCase();
                bottomBehaviour.setState(BottomSheetBehaviour.STATE_EXPANDED);

                Resources res = getResources();
                int resID = res.getIdentifier(clicked,"drawable",getPackageName());
                Drawable drawable = res.getDrawable(resID);
                imgView.setImageDrawable(drawable);
            }
        });

        bottomBehaviour.setBottomSheetCallback(new BottomSheetBehaviour.BottomSheetCallBack(){


        });
    }
}
