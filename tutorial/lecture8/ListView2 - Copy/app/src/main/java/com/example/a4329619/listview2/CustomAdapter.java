package com.example.a4329619.listview2;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 4329619 on 20/10/2016.
 */
public class CustomAdapter extends ArrayAdapter<Movie>{

    private List<Movie> movie_list;
   private final Context context;

    public CustomAdapter(Context context,List<Movie> list){
        super(context,R.layout.row_view,list);
        movie_list = list;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listView = inflater.inflate(R.layout.row_view,null,true);
        TextView txtTitle = (TextView)listView.findViewById(R.id.text_title);
        TextView txtRating = (TextView)listView.findViewById(R.id.text_rating);
        TextView txtGenre = (TextView)listView.findViewById(R.id.text_genre);

        ImageView imgView = (ImageView) listView.findViewById(R.id.imageView);

        imgView.setImageResource(movie_list.get(position).getImage());
        txtTitle.setText(movie_list.get(position).getTitle());
        txtRating.setText("Rating:" + Double.toString(movie_list.get(position).getRating()));
        txtGenre.setText(movie_list.get(position).getGenre());

        return listView;

    }
}
