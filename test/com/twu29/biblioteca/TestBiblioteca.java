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
        String content = Biblioteca.WELCOME + "\nMenu\n================\n1. Login\n2. List Book Catalog\n" +
                        "3. List Movie Catalog\n4. Check Out Book\n5. Check My Details\n6. Exit\n";
        assertThat(biblioteca.menuContent(), is(content));
    }


    private String formattedOutput(String output) {
        return output + lineSeparator;
    }
}
