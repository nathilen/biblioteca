package com.twu29.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


public class TestLibrary {

    private OutputStream outStream;
    private Library library;
    private String lineSeparator;
    private ByteArrayInputStream inputStream;

    @Before
    public void setUp() throws Exception {
        setUp("");
    }

    public void setUp(String input) throws Exception {
        outStream = new ByteArrayOutputStream();
        inputStream = new ByteArrayInputStream(input.getBytes());

        library = new Library(new PrintStream(outStream), new Scanner(inputStream));
        lineSeparator = System.getProperty("line.separator");

        library.addBook(1, new Book("Test Driven Development By Example","Kent Beck",false));
        library.addBook(2, new Book("How To Dance 101","Anonymous Famous",false));

        library.addMovie(1, new Movie("Scholay", 1975, "Ramesh Sippy"));
        library.addMovie(2, new Movie("La Maison", 2011, "Krystel Elembe", 3));
    }

    @Test
    public void shouldPrintWelcome() throws Exception {
        library.printWelcome();
        assertThat(outStream.toString(), is(formattedOutput(Library.WELCOME + "\n")));
    }

    @Test
    public void shouldPrintMenu() throws Exception {
        library.printMenu();
        assertThat(outStream.toString(), is(formattedOutput(Library.MENU)));
    }

    @Test
    public void shouldAllowUserToSelectMenuOption() throws Exception {
        int input = 2;
        setUp("2");
        assertEquals(input, library.getUserSelection());
    }

    @Test
    public void shouldUserSelectionBeNumeric () throws Exception {
        setUp("two");
        assertNotSame(2, library.getUserSelection());
    }

    @Test
    public void shouldBeNotifiedOfInvalidMenuOption() throws Exception {
        setUp("6");
        library.processMenuSelection(6);
        assertThat(outStream.toString(), is(formattedOutput(Library.INVALID_MENU_OPTION)) );
    }

    @Test
    public void shouldSeeAllBooks() throws Exception {
        String bookMenu = "\n\nOur Books\n------------\n" +
                          "1. Test Driven Development By Example by Kent Beck\n" +
                          "2. How To Dance 101 by Anonymous Famous\n";
        library.printBooks();
        assertThat(outStream.toString(), is(formattedOutput(bookMenu)));
    }

    @Test
    public void shouldReserveAvailableBook() throws Exception {
        String firstBookOnMenu = "1";
        setUp(firstBookOnMenu);
        library.reserveBook();
        assertThat(outStream.toString(),is(formattedOutput(Library.RESERVED_AVAILABLE_BOOK)));
    }

    @Test
    public void shouldNotReserveUnavailableBook() throws Exception {
        String bookInMenu = "2 2";
        String bookAvailableText = formattedOutput(Library.RESERVED_AVAILABLE_BOOK);
        String bookUnavailableText = formattedOutput(Library.RESERVED_UNAVAILABLE_BOOK);

        setUp(bookInMenu);

        library.reserveBook();
        library.reserveBook();
        assertThat(outStream.toString(),is(bookAvailableText + bookUnavailableText));
    }

    @Test
    public void shouldNotReserveBookNotInMenu() throws Exception {
        String bookNotInMenu = "5";
        setUp(bookNotInMenu);
        library.reserveBook();
        assertThat(outStream.toString(),is(formattedOutput(Library.RESERVED_UNAVAILABLE_BOOK)));
    }

    @Test
    public void shouldBeAbleToCheckUserDetails() throws Exception {
        library.checkUser();
        assertThat(outStream.toString(),is(formattedOutput(Library.USER_DETAILS_MESSAGE)));

    }

    @Test
    public void shouldSeeAllMovies() throws Exception {
        String movieMenu = "\n\nOur Movies\n------------\n" +
                           "Movie\tYear\tDirector\tRating\n" +
                           "Scholay\t1975\tRamesh Sippy\tN/A\n" +
                           "La Maison\t2011\tKrystel Elembe\t3\n";

        library.printMovies();
        assertThat(outStream.toString(), is(formattedOutput(movieMenu)));
    }

    private String formattedOutput(String output) {
        return output + lineSeparator;
    }
}
