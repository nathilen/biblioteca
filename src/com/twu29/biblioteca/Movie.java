package com.twu29.biblioteca;

public class Movie {
    private String title;
    private int year;
    private String director;
    private int rating;

    public Movie(String title, int year, String director) {
        this.title = title;
        this.year = year;
        this.director = director;
    }

    public Movie(String title, int year, String director, int rating) {
        this(title, year, director);
        this.rating = rating;
    }

    @Override
    public String toString() {
        String movieLine = title + "\t" + year + "\t" + director;

        if (rating > 0){
            return movieLine + "\t" + rating;
        }
        return movieLine + "\tN/A";
    }
}
