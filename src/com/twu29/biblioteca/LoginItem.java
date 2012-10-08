package com.twu29.biblioteca;

public class LoginItem extends Item {
    protected Biblioteca biblioteca;

    public LoginItem(String text, Biblioteca biblioteca) {
        super(text, "");
        this.biblioteca = biblioteca;
    }

    @Override
    public String deliver() {
        String username = biblioteca.getUserInput("Enter username: ");
        String password = biblioteca.getUserInput("Enter password: ");
        return biblioteca.getLibrary().doLogin(username, password);
    }
}
