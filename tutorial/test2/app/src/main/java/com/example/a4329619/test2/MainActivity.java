package com.example.a4329619.test2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void fruitBtnHandler(View view){
      showFoodView("Fruit",R.drawable.fruit);
    }


    public void vegeBtnHandler(View view){
        showFoodView("Vegetable",R.drawable.veg);

    }

    public void drinkBtnHandler(View view){
        showFoodView("Drink",R.drawable.drink);
    }

    public void showFoodView(String item, int image){
        Bundle bundle = new Bundle();
        bundle.putString("food_item", item);
        bundle.putInt("food_image",image);

        Intent intent = new Intent();
        intent.setClass(getApplicationContext(),FoodView.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
