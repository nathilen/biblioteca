package com.twu29.biblioteca;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {
    private PrintStream outputStream;
    private Scanner scanner;

    static final String INVALID_MENU_OPTION = "Select a valid option!!!";
    static final String WELCOME =  "----------------------------------------------------\n" +
                                   "|  Welcome To The Bangalore Public Library System   |\n"+
                                   "----------------------------------------------------";
    static final String MENU_OPTIONS [] = {"List Book Catalog","Check Out Book","Check My Details"};
    static final String MENU = menu();
    static final String RESERVED_AVAILABLE_BOOK = "Thank You! Enjoy the book.";
    static final String RESERVED_UNAVAILABLE_BOOK = "Sorry we don't have that book yet.";
    static final String USER_DETAILS_MESSAGE = "Please talk to Librarian. Thank you.";

    private List<Book> books;

    public Library(PrintStream outputStream, Scanner scanner) {
        this.outputStream = outputStream;
        this.scanner = scanner;
        books = new ArrayList<Book>();
    }

    public void printWelcome() {
        outputStream.println(WELCOME);
        outputStream.println();
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

    public void printMenu() {
        outputStream.println(menu());
    }

    public int getUserSelection(){
        try{
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
        String bookMenu = "\n\nOur Books\n------------\n";
        int bookNumber = 1;
        for (Book book: books){
            bookMenu += bookNumber++ + ". " + book.toString()+ "\n";
        }
        outputStream.println(bookMenu);
    }

    public void reserveBook() {
        try{
            Book book = books.get(getUserSelection() - 1);
            if (!book.isReserved()){
                book.setReserved(true);
                outputStream.println(RESERVED_AVAILABLE_BOOK);
            }
            else{
                outputStream.println(RESERVED_UNAVAILABLE_BOOK);
            }

        } catch (Exception exception){
            outputStream.println(RESERVED_UNAVAILABLE_BOOK);
        }
    }

    public void checkUser() {
        outputStream.println(USER_DETAILS_MESSAGE);
    }

    public void run() {
        populateBookCatalogue();

        while(true){
            printWelcome();
            printMenu();
            processMenuSelection();
        }
    }

    private void populateBookCatalogue() {
        addBook(new Book("Test Driven Development By Example","Kent Beck", false));
        addBook(new Book("Floyd Electronic","Floyd", false));
        addBook(new Book("How To Dance 101","Anonymous Famous", false));
        addBook(new Book("Lessons of Here","Anonymous Famous", false));
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
        Scanner inputScanner = new Scanner(System.in);
        new Library(System.out, inputScanner).run();
    }
}
