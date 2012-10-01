package com.twu29.biblioteca;

import java.util.HashMap;

public class Authentication {
    private HashMap<Integer,User> validUsers;

    private void createValidUsers(){
        validUsers = new HashMap<Integer, User>();
        validUsers.put(1, new User("111-1111","lola1"));
        validUsers.put(2, new User("111-1112","lola2"));
        validUsers.put(3, new User("111-1113","lola3"));
        validUsers.put(4, new User("111-1114","lola4"));
        validUsers.put(5, new User("111-1115","lola5"));
    }
    public boolean isAuthenticated(User user) {
        createValidUsers();
        return validUsers.containsValue(user);
    }
}
