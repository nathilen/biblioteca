package com.twu29.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.internal.matchers.StringContains.containsString;


public class TestBiblioteca {

    private OutputStream outStream;
    private ByteArrayInputStream inputStream;
    private Biblioteca biblioteca;
    private String lineSeparator;

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
    public void shouldUnderstandMessagePrinting() throws Exception {
        String message = "Hello World/n, How is life?";
        biblioteca.printMessage(message);
        assertThat(outStream.toString(), is(formattedOutput(message)));
    }

    @Test
    public void shouldUnderstandUserInput() throws Exception {
        String userInput = "Charlene";
        setUp(userInput);
        assertEquals(userInput, biblioteca.getUserInput("Enter your name:"));
    }

    @Test
    public void shouldHaveMenuContent() throws Exception {
        String content = Biblioteca.WELCOME + "\nMenu\n================\n1. List Book Catalog" +
                        "\n2. List Movie Catalog\n3. Check My Details\n";
        assertThat(biblioteca.menuContent(), is(content));
    }

    @Test
    public void shouldBeNotifiedOfInvalidMenuOption() throws Exception {
        String userInput = "6";
        setUp(userInput);
        biblioteca.processMenuSelection(userInput);
//        assertThat(outStream.toString(), is(formattedOutput(Menu.INVALID_MENU_OPTION)) );
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
