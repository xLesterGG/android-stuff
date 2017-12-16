package com.example.a4329619.test2;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_view);

        Bundle bundle = getIntent().getExtras();
        String foodName = bundle.getString("food_item");
        Drawable img = getResources().getDrawable(bundle.getInt("food_image"));

        TextView txtView = (TextView)findViewById(R.id.textview1);
        ImageView imgView = (ImageView)findViewById(R.id.imgview1);

        txtView.setText(foodName);
        imgView.setImageDrawable(img);
    }
}
