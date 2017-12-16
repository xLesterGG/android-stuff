package com.example.a4329619.listview2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Movie> movie_list = populate_list();

        ListView lv = (ListView)findViewById(R.id.listView);
        CustomAdapter adapter = new CustomAdapter(this,movie_list);
        lv.setAdapter(adapter);
    }

    private List<Movie> populate_list(){
        List<Movie> list = new ArrayList<Movie>();
        populate_list().add(new Movie("Inferno",6.5, "Thriller,drama",R.drawable.inferno));
        populate_list().add(new Movie("Jungle Book",7.5, "Drama,drama",R.drawable.jungle_book));
        populate_list().add(new Movie("Star Wars",4.5, "Sci-fi,drama",R.drawable.star_wars));


        return list;
    }
}
