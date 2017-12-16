package com.example.a4329619.listview2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Movie> movie_list = populate_list();

        RecyclerView recycle_view = (RecyclerView)findViewById(R.id.recycle_view);
        CustomAdapter adapter = new CustomAdapter(movie_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recycle_view.setLayoutManager(layoutManager);
        recycle_view.setItemAnimator(new DefaultItemAnimator());

        recycle_view.setAdapter(adapter);
    }

    private List<Movie> populate_list(){
        List<Movie> list = new ArrayList<Movie>();
        list.add(new Movie("Inferno",6.5, "Thriller,drama",R.drawable.inferno));
        list.add(new Movie("Jungle Book",7.5, "Drama,drama",R.drawable.jungle_book));
        list.add(new Movie("Star Wars",4.5, "Sci-fi,drama",R.drawable.star_wars));
        list.add(new Movie("Lessy",10.0,"Comedy, Parody",R.drawable.star_wars));
        list.add(new Movie("Jungle Book",7.5, "Drama,drama",R.drawable.jungle_book));
        list.add(new Movie("Star Wars",4.5, "Sci-fi,drama",R.drawable.star_wars));
        list.add(new Movie("Lessy",10.0,"Comedy, Parody",R.drawable.star_wars));
        list.add(new Movie("Jungle Book",7.5, "Drama,drama",R.drawable.jungle_book));
        list.add(new Movie("Star Wars",4.5, "Sci-fi,drama",R.drawable.star_wars));
        list.add(new Movie("Lessy",10.0,"Comedy, Parody",R.drawable.star_wars));


        return list;
    }
}
