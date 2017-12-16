package com.example.a4329619.listview2;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
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
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>{

    private List<Movie> movie_list;

    public CustomAdapter(List<Movie> list){
        movie_list = list;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{

        public TextView txtTitle,txtRating,txtGenre;
        public ImageView imgView;
        public CustomViewHolder(View itemView) {
            super(itemView);


            txtTitle = (TextView)itemView.findViewById(R.id.text_title);
            txtRating = (TextView)itemView.findViewById(R.id.text_rating);
            txtGenre = (TextView)itemView.findViewById(R.id.text_genre);

            imgView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    @Override
    public CustomAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflator = LayoutInflater.from(context);
        View view = inflator.inflate(R.layout.row_view,parent,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomAdapter.CustomViewHolder holder, int position) {
        Movie movie = movie_list.get(position);
        holder.txtTitle.setText(movie.getTitle());
        holder.txtGenre.setText(movie.getGenre());
        holder.txtRating.setText(Double.toString(movie.getRating()));
        holder.imgView.setImageResource(movie.getImage());
    }

    @Override
    public int getItemCount() {
        return movie_list.size();
    }
}
