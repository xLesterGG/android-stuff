package com.example.lesgo.a4t222;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class GetProfile extends AppCompatActivity {

    //ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_profile);

       // lv = (ListView)findViewById(R.id.ListView);
        List<ProfileName> profile_list = populate_list();

        ProfileAdapter adapter = new ProfileAdapter(profile_list);
        RecyclerView recycle_view = (RecyclerView)findViewById(R.id.recycle_view1);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recycle_view.setLayoutManager(layoutManager);
        recycle_view.setItemAnimator(new DefaultItemAnimator());

        recycle_view.setAdapter(adapter);


    }

    private List<ProfileName> populate_list(){
        List<ProfileName> list = new ArrayList<ProfileName>();
        list.add(new ProfileName("Marlene", "Malaysian"));
        list.add(new ProfileName("Emily", "Malaysian"));
        list.add(new ProfileName("Ambrose", "Bangladesh"));
        list.add(new ProfileName("Marlene", "Malaysian"));
        list.add(new ProfileName("Emily", "Malaysian"));
        list.add(new ProfileName("Ambrose", "Bangladesh"));
        list.add(new ProfileName("Marlene", "Malaysian"));
        list.add(new ProfileName("Emily", "Malaysian"));
        list.add(new ProfileName("Ambrose", "Bangladesh"));
        list.add(new ProfileName("Marlene", "Malaysian"));
        list.add(new ProfileName("Emily", "Malaysian"));
        list.add(new ProfileName("Ambrose", "Bangladesh"));



        return list;
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }
}
