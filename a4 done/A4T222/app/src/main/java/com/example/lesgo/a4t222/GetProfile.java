package com.example.lesgo.a4t222;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class GetProfile extends AppCompatActivity {

    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_profile);

        lv = (ListView)findViewById(R.id.ListView);

        String [] names = {
                "Lester",
                "Marlene",
                "Emily",
                "Ambrose",
                "Marlene",
                "Steven",
                "Emily",
                "Marlene",
                "Emily",
                "Ambrose",
                "Marlene",
                "Steven",
                "Emily",
                "Ambrose",
                "Marlene",
                "Steven",
                "Emily",
                "Marlene",
                "Emily"
        };

        ArrayAdapter<String>adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,names);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String input = (String)adapterView.getItemAtPosition(i);

                if(input == null)
                {
                 Log.d("aBCACSAD", "IS NULL")   ;
                }
                else{
                    Intent intent = new Intent();
                    intent.putExtra("name",input);
                    setResult(RESULT_OK,intent);
                    finish();
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);


                }


            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }
}
