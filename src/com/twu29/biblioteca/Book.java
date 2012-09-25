package com.twu29.biblioteca;

public class Book {
    private String author;
    private String title;

    public Book(String title, String author){
        this.title = title;
        this.author = author;
    }

    public String toString(){
        return title + " by " + author;
    }

}
