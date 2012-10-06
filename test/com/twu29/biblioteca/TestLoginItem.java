package com.twu29.biblioteca;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class TestLoginItem {

    private OutputStream outStream;
    private ByteArrayInputStream inputStream;

    @Test
    public void shouldUnderstandUserLogin() throws Exception {
        String loginDetails = "111-1112 lola2";
        outStream = new ByteArrayOutputStream();
        inputStream = new ByteArrayInputStream(loginDetails.getBytes());
//        LoginItem loginItem = new LoginItem("Login", new PrintStream(outStream), new Scanner(inputStream));
//        loginItem.deliver();
    }
}
