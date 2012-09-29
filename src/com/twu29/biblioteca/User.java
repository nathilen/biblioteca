package com.twu29.biblioteca;

/**
 * Understands a Library user
 */
public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        if (!isUsernameValid(username)){
            throw (new LibraryException("Username is not in valid format"));
        }
        this.username = username;
        this.password = password;
    }

    public void login() {
        //To change body of created methods use File | Settings | File Templates.
    }

    private boolean isUsernameValid(String username){
        final int USERNAME_LENGTH = 8;
        final int DASH_POSITION = 3;
        String prefix = username.substring(0,DASH_POSITION);
        String suffix = username.substring(DASH_POSITION + 1);
        return  username.length() == USERNAME_LENGTH && isDigit(prefix) &&
                username.indexOf("-") == DASH_POSITION && isDigit(suffix);

    }

    private boolean isDigit(String content) {
        for (int index = 0; index < content.length(); index++){
            Character character = content.charAt(index);
            if (!Character.isDigit(character)){
                return false;
            }
        }
        return true;
    }
}
