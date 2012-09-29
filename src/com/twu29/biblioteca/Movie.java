/**
 * Understands a movie with a pre-set rating
 */
package com.twu29.biblioteca;

import java.util.Calendar;
import java.util.Random;

public class Movie {
    private static final int MINIMUM_RATING = 1;
    private static final int MAXIMUM_RATING = 10;
    private static final int MINIMUM_YEAR = 2;
    private String title;
    private int year;
    private String director;
    private String rating;

    public Movie(String title, int year, String director) {
        this.title = title;
        this.year = year;
        this.director = director;

        rate();
    }

    private void rate() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if (Math.abs(currentYear - year) < MINIMUM_YEAR){
            rating = "N/A";
        }
        else{
            Random randomGenerator = new Random();
            int randomRating = MINIMUM_RATING + randomGenerator.nextInt(MAXIMUM_RATING);
            rating = String.valueOf(randomRating);
        }
    }

    public String movieLine(String format){
        return String.format(format,title,year,director,rating);
    }
}
