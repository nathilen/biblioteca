package com.twu29.biblioteca;

public class Book {
    private String title;
    private String author;
    private boolean reserved;

    public Book(String title, String author){
        this.title = title;
        this.author = author;
    }

    public boolean reserve() {
        if (!reserved){
            reserved = true;
            return reserved;
        }
        return false;
    }


    @Override
    public String toString(){
        return title + " by " + author;
    }
}
