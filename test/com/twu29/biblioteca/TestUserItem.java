package com.twu29.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class TestUserItem {
    UserItem userItem;
    private Biblioteca biblioteca;

    @Before
    public void setUp() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ByteArrayInputStream inputStream = new ByteArrayInputStream("".getBytes());
        biblioteca = new Biblioteca(new PrintStream(outputStream), new Scanner(inputStream));
        userItem = new UserItem("User Item", biblioteca);
    }

    @Test
    public void shouldUnderstandDetailsOfGenericUser() throws Exception {
        assertThat(userItem.deliver(), is("Please talk to Librarian. Thank you."));
    }

    @Test
    public void shouldUnderstandDetailsOfLoggedInUser() throws Exception {
        biblioteca.getLibrary().doLogin("111-1112", "lola2");
        assertThat(userItem.deliver(), is("Hi 111-1112!"));
    }
}
