package com.twu29.biblioteca;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

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

    @Test
    public void shouldUnderstandUserEquality() throws Exception {
        String username = "111-1114";
        String password = "lola4";
        assertTrue(new User(username,password).equals(new User(username,password)));
    }

    @Test
    public void shouldUnderstandUsersWithSameUsernameMayNotBeEqual() throws Exception {
        String username = "111-1113";
        assertThat(new User(username,"lola1"), is(not(new User(username,"lola2"))));
    }

    @Test
    public void shouldUnderstandUsersWithSamePasswordMayNotBeEqual() throws Exception {
        String password = "lola1";
        assertThat(new User("111-1112", password), is(not(new User("111-1114",password))));
    }

    @Test
    public void shouldUnderstandUserIsNotEqualToNull() throws Exception {
        assertFalse(new User("111-1112", "lola3").equals(null));
    }

    @Test
    public void shouldUnderstandUserIsNotEqualToObjectOfDifferentType() throws Exception {
        assertFalse(new User("111-1114", "lola5").equals(new String("111-1114, lola5")));
    }

    @Test
    public void shouldUnderstandHashcodeOfTwoEqualUsers() throws Exception {
        String username = "111-1113";
        String password = "lola2";
        assertEquals(new User(username,password).hashCode(), new User(username,password).hashCode());
    }

    @Test
    public void shouldLoginValidUser() throws Exception {
        try{
            new User("111-1111","lola1").login();
        }
        catch (LibraryException exception){
            fail("Should have logged in");
        }
    }

    @Test
    public void shouldNotLoginInvalidUser() throws Exception {
        try{
            new User("111-1113","lola2").login();
            fail("Should not login");
        }
        catch (LibraryException exception){
            assertEquals("Username or password is incorrect", exception.getMessage());
        }
    }

    @Test
    public void shouldNotLoginUserAlreadyLoggedIn() throws Exception {
        try{
            User user = new User("111-1111","lola1");
            user.login();
            user.login();
            fail("Should not login again");
        }
        catch (LibraryException exception){
            assertEquals("User has already logged in", exception.getMessage());
        }
    }
}
