package com.twu29.biblioteca;

public class Book {
    private String title;
    private String author;
    private boolean reserved;

    public Book(String title, String author, boolean reserved){
        this.title = title;
        this.author = author;
        this.reserved = reserved;
    }

    public String toString(){
        return title + " by " + author;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
}
