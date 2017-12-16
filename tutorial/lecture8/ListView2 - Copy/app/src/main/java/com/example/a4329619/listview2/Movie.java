package com.example.a4329619.listview2;

/**
 * Created by 4329619 on 20/10/2016.
 */
public class Movie {
    String title;
    double rating;
    String genre;
    int image;

    public Movie(String title, double rating, String genre, int image) {
        this.title = title;
        this.rating = rating;
        this.genre = genre;
        this.image = image;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
