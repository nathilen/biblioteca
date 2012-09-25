package com.twu29.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


public class TestLibrary {

    private OutputStream outStream;
    private Library library;
    private String lineSeparator;

    private Book [] books;

    @Before
    public void setUp() throws Exception {
        setUp("");
    }
    public void setUp(String input) throws Exception {
        outStream = new ByteArrayOutputStream();
        library = new Library(new PrintStream(outStream),new ByteArrayInputStream(input.getBytes()));
        lineSeparator = System.getProperty("line.separator");

        books = new Book[4];
        books[0] = new Book("Test Driven Development By Example", "Kent Beck");
        books[1] = new Book("Floyd Electronic", "Floyd");
        books[2] = new Book("How To Dance 101", "Anonymous Famous");
        books[3] = new Book("Lessons of Here", "Anonymous Famous");

        for (Book book: books){
            library.addBook(book);
        }
    }

    @Test
    public void testShouldPrintWelcome() throws Exception {
        library.printWelcome();
        assertThat(outStream.toString(), is(formattedOutput(Library.WELCOME)));
    }

    @Test
    public void testShouldPrintMenu() throws Exception {
        library.printMenu();
        assertThat(outStream.toString(), is(formattedOutput(Library.MENU)));
    }

    @Test
    public void testShouldAllowUserToSelectMenuOption() throws Exception {
        int input = 2;
        setUp("2");
        assertEquals(input, library.getUserSelection());
    }

    @Test
    public void testShouldUserSelectionBeNumeric () throws Exception {
        library.setInputStream(new ByteArrayInputStream("two".getBytes()));
        assertNotSame(2, library.getUserSelection());
    }

    @Test
    public void testShouldBeNotifiedOfInvalidMenuOption() throws Exception {
        library.setInputStream(new ByteArrayInputStream("6".getBytes()));
        library.processMenuSelection();
        assertThat(outStream.toString(), is(formattedOutput(Library.INVALID_MENU_OPTION)) );
    }

    @Test
    public void testShouldSeeAllBooks() throws Exception {
        String bookMenu = "Our Books\n------------\n";
        int bookNumber = 1;
        for (Book book: books){
            bookMenu += bookNumber++ + ". " + book.toString() + "\n";
        }
        library.printBookMenu();
        assertThat(outStream.toString(), is(formattedOutput(bookMenu)));
    }

    @Test
    public void testShouldReserveAvailableBook() throws Exception {
        String firstBookOnMenu = "1";
        library.setInputStream(new ByteArrayInputStream(firstBookOnMenu.getBytes()));
        library.reserveBook();
        assertThat(outStream.toString(),is(formattedOutput(Library.RESERVED_AVAILABLE_BOOK)));
    }

    @Test
    public void testShouldNotReserveUnavailableBook() throws Exception {
        String bookNotInMenu = "5";
        library.setInputStream(new ByteArrayInputStream(bookNotInMenu.getBytes()));
        library.reserveBook();
        assertThat(outStream.toString(),is(formattedOutput(Library.RESERVED_UNAVAILABLE_BOOK)));
    }

    @Test
    public void testShouldBeAbleToCheckUserDetails() throws Exception {
        library.checkUser();
        assertThat(outStream.toString(),is(formattedOutput(Library.USER_DETAILS_MESSAGE)));

    }

    private String formattedOutput(String output) {
        return output + lineSeparator;
    }

}
