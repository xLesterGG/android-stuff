package com.example.a4329619.lab5task2;

import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements FragmentOne.OnFragmentInteractionListener,FragmentTwo.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentOne frag1 = new FragmentOne();
        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction fragTrans = fragManager.beginTransaction();
        fragTrans.add(R.id.linearlayout,frag1);
        fragTrans.commit();
    }


    @Override
    public void onFragmentClick(String clicked) {
       // Log.d("value",clicked);

        FragmentTwo frag2 = new FragmentTwo();
        Bundle bundle = new Bundle();
        bundle.putString("cValue",clicked);
        frag2.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.linearlayout,frag2); // replace frag container with new frag
        transaction.addToBackStack(null); //allow back

        transaction.commit();

    }

    @Override
    public void OnLoad(String msg) {
        Log.d("MESSAGE", msg);
    }
}
