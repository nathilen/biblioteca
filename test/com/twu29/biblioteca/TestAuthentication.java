package com.twu29.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestAuthentication {
    @Test
    public void shouldUnderstandAuthenticationOfValidUser() throws Exception {
        User user = new User("111-1112","lola2");
        assertEquals(true, new Authentication().isAuthenticated(user));
    }
}
