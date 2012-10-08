package com.twu29.biblioteca;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class TestLoginItem {
    private LoginItem loginItem;

    public void setUp(String input) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Biblioteca biblioteca = new Biblioteca(new PrintStream(outputStream), new Scanner(inputStream));

        loginItem = new LoginItem("Login", biblioteca);
    }

    @Test
    public void shouldUnderstandValidUserLogin() throws Exception {
        setUp("111-1112 lola2");
        assertThat(loginItem.deliver(), is("User successfully logged in"));
    }

    @Test
    public void shouldUnderstandInvalidUserLogin() throws Exception {
        setUp("111-1113 lola2");
        assertThat(loginItem.deliver(), is(not("User successfully logged in")));
    }
}
