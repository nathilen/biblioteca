package com.twu29.biblioteca;

/**
 * Understands a Library user
 */
public class User {
    private String username;
    private String password;
    private boolean isLoggedIn;

    private Authentication authentication;

    public User(String username, String password){
        this.username = username;
        this.password = password;

        authentication = new Authentication();
    }

    public void login() throws LibraryException {
        if (isLoggedIn){
            throw new LibraryException("User has already logged in");
        }
        if (!authentication.isAuthenticated(this)){
            throw new LibraryException("Username or password is incorrect");
        }
        isLoggedIn = true;
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
}
