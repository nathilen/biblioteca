package com.twu29.biblioteca;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {
    private PrintStream outputStream;
    private InputStream inputStream;

    static final String INVALID_MENU_OPTION = "Select a valid option!!\n\n\n";
    static final String WELCOME =  "\n----------------------------------------------------\n" +
                                   "|  Welcome To The Bangalore Public Library System   |\n"+
                                   "----------------------------------------------------\n";
    static final String MENU_OPTIONS [] = {"List Book Catalog","Check Out Book","Check My Details"};
    static final String MENU = menu();
    static final String RESERVED_AVAILABLE_BOOK = "Thank You! Enjoy the book.\n\n\n";
    static final String RESERVED_UNAVAILABLE_BOOK = "Sorry we don't have that book yet.\n\n\n";
    static final String USER_DETAILS_MESSAGE = "Please talk to Librarian. Thank you.\n\n\n";

    private List<Book> books;

    public Library(PrintStream outputStream, InputStream inputStream) {
        this.outputStream = outputStream;
        this.inputStream = inputStream;
        books = new ArrayList<Book>();
    }

    public void printWelcome() {
        outputStream.println(WELCOME);
    }

    public void processMenuSelection() {
        int selection = getUserSelection();

        switch(selection){
            case 1:
                printBookMenu();
                break;
            case 2:
                reserveBook();
                break;
            case 3:
                checkUser();
                break;
            case 4:
                System.exit(0);
            default:
                outputStream.println(INVALID_MENU_OPTION);
        }
    }

    public void setInputStream(InputStream inputStream){
        this.inputStream = inputStream;
    }

    public void printMenu() {
        outputStream.println(menu());
    }

    public int getUserSelection(){
        try{
            Scanner scanner = new Scanner(inputStream);
            return scanner.nextInt();
        }
        catch(Exception exception){
            outputStream.println("Only numbers are allowed");
        }
        return -1;
    }

    public void addBook(Book book){
        books.add(book);
    }

    public void printBookMenu(){
        String bookMenu = "Our Books\n------------\n";
        int bookNumber = 1;
        for (Book book: books){
            bookMenu += bookNumber++ + ". " + book.toString()+ "\n";
        }
        outputStream.println(bookMenu);
    }

    public void reserveBook() {
        try{
            books.get(getUserSelection());
            outputStream.println(RESERVED_AVAILABLE_BOOK);
        } catch (Exception exception){
            outputStream.println(RESERVED_UNAVAILABLE_BOOK);
        }
    }

    public void checkUser() {
        outputStream.println(USER_DETAILS_MESSAGE);
    }

    public void run(){
        setInputStream(System.in);
        populateBookCatalogue();

        while(true){
            printWelcome();
            printMenu();
            processMenuSelection();
        }
    }

    private void populateBookCatalogue() {
        addBook(new Book("Test Driven Development By Example", "Kent Beck"));
        addBook(new Book("Floyd Electronic", "Floyd"));
        addBook(new Book("How To Dance 101", "Anonymous Famous"));
        addBook(new Book("Lessons of Here", "Anonymous Famous"));
    }

    private static String menu(){
        String menu = "Menu\n================\n";
        int optionNumber;
        for(optionNumber = 1; optionNumber <= MENU_OPTIONS.length; optionNumber++){
            menu += optionNumber + ". " + MENU_OPTIONS[optionNumber - 1] + "\n";
        }
        menu += optionNumber + ". Exit\nSelect an option: ";
        return menu;
    }

    public static void main(String[] args) {
        new Library(System.out, System.in).run();
    }
}
