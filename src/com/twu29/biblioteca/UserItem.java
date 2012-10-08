package com.twu29.biblioteca;

public class UserItem extends LoginItem{
    public UserItem(String text, Biblioteca biblioteca) {
        super(text, biblioteca);
    }

    @Override
    public String deliver() {
        return biblioteca.getLibrary().userDetails();
    }
}
