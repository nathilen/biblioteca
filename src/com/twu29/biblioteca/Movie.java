package com.twu29.biblioteca;

public class Movie {
    private String title;
    private int year;
    private String director;
    private String rating;

    public Movie(String title, int year, String director) {
        this.title = title;
        this.year = year;
        this.director = director;
        this.rating = "N/A";
    }

    public Movie(String title, int year, String director, String rating) {
        this(title, year, director);
        this.rating = rating;
    }

    public String movieLine(String format){
        return String.format(format,title,year,director,rating);
    }
    @Override
    public String toString() {
        return String.format("%-20s%-10d%-20s%-3s",title,year,director,rating);
    }
}
