package com.twu29.biblioteca;

public class Item {
    private String text;
    private String message;

    public Item(String text, String message){
        this.text = text;
        this.message = message;
    }

    public String deliver() {
        return message;
    }
}
