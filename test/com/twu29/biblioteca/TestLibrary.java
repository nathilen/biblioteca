package com.twu29.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.internal.matchers.StringContains.containsString;

public class TestLibrary {
    private Library library;

    @Before
    public void setUp() throws Exception {
        library = new Library();
        library.addBook(1, new Book("Test Driven Development By Example", "Kent Beck", false));
        library.addBook(2, new Book("How To Dance 101", "Anonymous Famous", false));
    }

    @Test
    public void shouldSeeAllBooks() throws Exception {
        String bookMenu = "\n\nOur Books\n------------\n" +
                "1. Test Driven Development By Example by Kent Beck\n" +
                "2. How To Dance 101 by Anonymous Famous";
        assertThat(library.bookCatalogue(), is(bookMenu));
    }

    @Test
    public void shouldSeeAllMovies() throws Exception {

        library.addMovie(1, new Movie("Scholay", 2012, "Ramesh Sippy"));
        library.addMovie(2, new Movie("La Maison", 2011, "Krystel Elembe"));

        String lineFormat = "%-20s%-10d%-20s%-3s\n";
        String headerFormat = "\n\n%s\n%s\n%-20s%-10s%-20s%-3s\n\n";
        String movieMenu = String.format(headerFormat,"Our Movies",
                "------------","Movie","Year","Director","Rating") +
                String.format(lineFormat, "Scholay", 2012, "Ramesh Sippy", "N/A") +
                String.format(lineFormat, "La Maison", 2011, "Krystel Elembe", "N/A");

        assertThat(library.movieCatalogue(), is(movieMenu));
    }

    @Test
    public void shouldReserveAvailableBook() throws Exception {
        assertThat(library.reserveBook(1),is(Biblioteca.RESERVED_AVAILABLE_BOOK));
    }

    @Test
    public void shouldNotReserveUnavailableBook() throws Exception {
        String bookAvailableText = Biblioteca.RESERVED_AVAILABLE_BOOK;
        String bookUnavailableText = Biblioteca.RESERVED_UNAVAILABLE_BOOK;

        String message = library.reserveBook(2);
        message += library.reserveBook(2);
        assertThat(message,is(bookAvailableText + bookUnavailableText));
    }

    @Test
    public void shouldNotReserveBookNotInMenu() throws Exception {
        assertThat(library.reserveBook(8), is(Biblioteca.RESERVED_UNAVAILABLE_BOOK));
    }

    @Test
    public void shouldBeAbleToCheckUserThatHasNotLoggedIn() throws Exception {
        assertThat(library.checkUser(),is(Biblioteca.GENERIC_USER_MESSAGE));
    }

    @Test
    public void shouldBeAbleToCheckUserThatHasLoggedIn() throws Exception {
        library.verifyUser("111-1112","lola2");
        assertThat(library.checkUser(), is("Hi 111-1112!"));
    }

    @Test
    public void shouldLoggedInUserNotSeeGenericMessage() throws Exception {
        library.verifyUser("111-1113","lola3");
        assertThat(library.checkUser(), is(not(containsString(Biblioteca.GENERIC_USER_MESSAGE))));
    }
}
