package com.twu29.biblioteca;

public class Item {
    private char number;
    private String message;

    public Item(char number, String message){
        this.number = number;
        this.message = message;
    }

    public String execute() {
       if (number != ' '){
           return number + ". " + message;
       }
       return message;
    }

    public String toString(){
        return number + ". " + message;
    }
}
