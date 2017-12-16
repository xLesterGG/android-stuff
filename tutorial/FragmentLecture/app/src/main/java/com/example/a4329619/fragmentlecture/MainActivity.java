package com.example.a4329619.fragmentlecture;


import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;

public class MainActivity extends FragmentActivity implements FragmentOne.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentOne frag1 = new FragmentOne();
        FragmentManager fragManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragTrans = fragManager.beginTransaction();
        fragTrans.add(R.id.linear_layout,frag1);
        fragTrans.commit();
    }

    @Override
    public void onFragmentInteraction(Integer val) {
        Log.d("Value",Integer.toString(val));
    }
}
