package com.twu29.biblioteca;

/**
 * Understands a Library user
 */
public class User {
    private String username;
    private String password;
    private boolean isLoggedIn;

    private Authentication authentication;

    public User(String username, String password) {
        if (!isUsernameValid(username)){
            throw (new LibraryException("Username is not in valid format"));
        }
        this.username = username;
        this.password = password;

        authentication = new Authentication();
    }

    public void login() {
        if (isLoggedIn){
            throw new LibraryException("User has already logged in");
        }
        if (!authentication.isAuthenticated(this)){
            throw new LibraryException("Username or password is incorrect");
        }
        isLoggedIn = true;
    }

    public void logout(){
        isLoggedIn = false;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isLoggedIn(){
        return isLoggedIn;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof User)) return false;
        User thatUser = (User) other;
        return this.username.equals(thatUser.username) && this.password.equals(thatUser.password);
    }

    @Override
    public int hashCode() {
        return username.hashCode() + password.hashCode();
    }

    private boolean isUsernameValid(String username){
        final int USERNAME_LENGTH = 8;
        final int DASH_POSITION = 3;

        if (username.length() < USERNAME_LENGTH){
            return false;
        }
        String prefix = username.substring(0,DASH_POSITION);
        String suffix = username.substring(DASH_POSITION + 1);
        return hasDigitsOnly(prefix) && username.indexOf("-") == DASH_POSITION && hasDigitsOnly(suffix);

    }

    private boolean hasDigitsOnly(String content) {
        for (int index = 0; index < content.length(); index++){
            Character character = content.charAt(index);
            if (!Character.isDigit(character)){
                return false;
            }
        }
        return true;
    }
}
