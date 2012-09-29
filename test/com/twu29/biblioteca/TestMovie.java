package com.twu29.biblioteca;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class TestMovie {

    @Test
    public void shouldUnderstandMovieLineFormat() throws Exception {
        Movie movie = new Movie("Coaching 101", 2012, "Aman King");
        assertThat(movie.movieLine("%s, %d, %s, %s"), is("Coaching 101, 2012, Aman King, N/A"));
    }

    @Test
    public void shouldNotRateMovieOfLessThanTwoYears() throws Exception {
        Movie movie = new Movie("La Maison", 2011, "Krystel Elembe");
        assertThat(movie.movieLine("%s\t%d\t%s\t%s"), is("La Maison\t2011\tKrystel Elembe\tN/A"));
    }

    @Test
    public void shouldRateMovieWithAtLeastTwoYears() throws Exception {
        Movie movie = new Movie("Scholay", 1975, "Ramesh Sippy");
        assertThat(movie.movieLine("%s\t%d\t%s\t%s"), is(not("Scholay\t1975\tRamesh Sippy\tN/A")));
    }
}
