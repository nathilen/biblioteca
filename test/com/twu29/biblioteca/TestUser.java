package com.twu29.biblioteca;

import org.junit.Test;

import static org.junit.Assert.fail;

public class TestUser {
    @Test(expected = LibraryException.class)
    public void shouldValidateUsername() throws Exception {
        new User("chacha","here101");
        fail("Should not create user");
    }

    @Test
    public void shouldCreateValidUser() throws Exception {
        new User("111-1113","lola2");
    }
}
