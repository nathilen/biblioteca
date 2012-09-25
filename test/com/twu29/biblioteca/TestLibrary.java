package com.twu29.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


public class TestLibrary {

    private OutputStream outStream;
    private Library library;
    private String lineSeparator;

    private List<Book> books;

    @Before
    public void setUp() throws Exception {
        setUp("");
    }

    public void setUp(String input) throws Exception {
        outStream = new ByteArrayOutputStream();
        library = new Library(new PrintStream(outStream),new ByteArrayInputStream(input.getBytes()));
        lineSeparator = System.getProperty("line.separator");

        library.addBook(new Book("Test Driven Development By Example","Kent Beck"));
        library.addBook(new Book("How To Dance 101","Anonymous Famous"));
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
        library.processMenuSelection();
        assertThat(outStream.toString(), is(formattedOutput(Library.INVALID_MENU_OPTION)) );
    }

    @Test
    public void shouldSeeAllBooks() throws Exception {
        String bookMenu = "\n\nOur Books\n------------\n" +
                          "1. Test Driven Development By Example by Kent Beck\n" +
                          "2. How To Dance 101 by Anonymous Famous\n";
        library.printBookMenu();
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

    private String formattedOutput(String output) {
        return output + lineSeparator;
    }

}
