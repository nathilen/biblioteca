package com.twu29.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;
import static org.junit.internal.matchers.StringContains.containsString;


public class TestBiblioteca {

    private OutputStream outStream;
    private Biblioteca biblioteca;
    private String lineSeparator;
    private ByteArrayInputStream inputStream;

    @Before
    public void setUp() throws Exception {
        setUp("");
    }

    public void setUp(String input) throws Exception {
        outStream = new ByteArrayOutputStream();
        inputStream = new ByteArrayInputStream(input.getBytes());

        biblioteca = new Biblioteca(new PrintStream(outStream), new Scanner(inputStream));
        lineSeparator = System.getProperty("line.separator");
    }

    @Test
    public void shouldPrintWelcome() throws Exception {
        biblioteca.printWelcome();
        assertThat(outStream.toString(), is(formattedOutput(Biblioteca.WELCOME + "\n")));
    }

    @Test
    public void shouldPrintMenu() throws Exception {
        biblioteca.printMenu();
        assertThat(outStream.toString(), is(formattedOutput(Biblioteca.MENU)));
    }

    @Test
    public void shouldAllowUserToSelectMenuOption() throws Exception {
        int input = 2;
        setUp("2");
        assertEquals(input, biblioteca.getUserSelection());
    }

    @Test
    public void shouldUserSelectionBeNumeric () throws Exception {
        setUp("two");
        assertNotSame(2, biblioteca.getUserSelection());
    }

    @Test
    public void shouldBeNotifiedOfInvalidMenuOption() throws Exception {
        setUp("6");
        biblioteca.processMenuSelection(6);
        assertThat(outStream.toString(), is(formattedOutput(Biblioteca.INVALID_MENU_OPTION)) );
    }

    @Test
    public void shouldPerformLogin() throws Exception {
        setUp("111-1111 lola1");
        biblioteca.doLogin();
        assertThat(outStream.toString(), containsString("User successfully logged in"));
    }

    @Test(expected = LibraryException.class)
    public void shouldNotAllowInvalidUserToLogin() throws Exception {
        setUp("111-1111 lola2");
        biblioteca.doLogin();
    }

    private String formattedOutput(String output) {
        return output + lineSeparator;
    }
}
