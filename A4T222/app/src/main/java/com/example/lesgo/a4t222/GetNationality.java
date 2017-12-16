package com.example.lesgo.a4t222;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class GetNationality extends AppCompatActivity {
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_nationality);

        lv = (ListView)findViewById(R.id.ListView);

        String [] nation = {
                "Malaysia",
                "Bangladesh",
                "Malaysia",
                "Bangladesh",
                "Malaysia",
                "Bangladesh",
                "Malaysia",
                "Bangladesh",
                "Malaysia",
                "Bangladesh",
                "Malaysia",
                "Bangladesh",
                "Malaysia",
                "Bangladesh",
                "Malaysia",
                "Bangladesh"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,nation);
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
                    intent.putExtra("nationality",input);
                    setResult(RESULT_OK,intent);
                    finish();
                    onBackPressed();


                }


            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter, R.anim.leave);
    }
}
