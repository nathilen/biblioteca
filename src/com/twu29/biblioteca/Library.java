package com.twu29.biblioteca;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

public class Library {
    private PrintStream outputStream;
    private Scanner scanner;

    static final String INVALID_MENU_OPTION = "Select a valid option!!!";
    static final String WELCOME =  "----------------------------------------------------\n" +
                                   "|  Welcome To The Bangalore Public Library System   |\n"+
                                   "----------------------------------------------------";
    static final String MENU_OPTIONS [] = {"List Movie Catalog","List Book Catalog","Check Out Book","Check My Details","Exit"};
    static final String MENU = menu();
    static final String RESERVED_AVAILABLE_BOOK = "Thank You! Enjoy the book.";
    static final String RESERVED_UNAVAILABLE_BOOK = "Sorry we don't have that book yet.";
    static final String USER_DETAILS_MESSAGE = "Please talk to Librarian. Thank you.";

    private Hashtable<Integer,Book> books;
    private Hashtable<Integer, Movie> movies;
    private ArrayList<Menu> menus;

    public Library(PrintStream outputStream, Scanner scanner) {
        this.outputStream = outputStream;
        this.scanner = scanner;

        books = new Hashtable<Integer, Book>();
        movies = new Hashtable<Integer, Movie>();
        menus = new ArrayList<Menu>();
    }

    public void printWelcome() {
        outputStream.println(WELCOME);
        outputStream.println();
    }

    public void processMenuSelection(int selection) {
        switch(selection){
            case 1:
                printMovies();
                break;
            case 2:
                printBooks();
                break;
            case 3:
                reserveBook();
                break;
            case 4:
                checkUser();
                break;
            default:
                printInvalidOption();
        }
    }

    public void printInvalidOption() {
        outputStream.println(INVALID_MENU_OPTION);
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

    public void addBook(int bookNumber, Book book){
        books.put(bookNumber, book);
    }

    public void addMovie(int movieNumber, Movie movie) {
        movies.put(movieNumber, movie);
    }

    public void printBooks(){
        String bookMenu = "\n\nOur Books\n------------\n";
        int bookNumber = 0;
        for (Book book: books.values()){
            bookMenu += ++bookNumber + ". " + books.get(bookNumber)+ "\n";
        }
        outputStream.println(bookMenu);
    }

    public void printMovies() {
        String lineFormat = "%-20s%-10d%-20s%-3s\n";
        String headerFormat = "\n\n%s\n%s\n%-20s%-10s%-20s%-3s\n";
        String movieMenu = String.format(headerFormat,"Our Movies",
                                    "------------","Movie","Year","Director","Rating");

        for(int movieNumber = 1; movieNumber <= movies.size(); movieNumber++){
            movieMenu += movies.get(movieNumber).movieLine(lineFormat);
        }
        outputStream.println(movieMenu);
    }

    public void reserveBook() {
        try{
            Book book = books.get(getUserSelection());
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
        populateMovieCatalogue();

        while(true){
            printWelcome();
            printMenu();
            int selection = getUserSelection();
            processMenuSelection(selection);
        }
    }

    private void populateMovieCatalogue() {
        final String MOVIE_FILENAME = System.getProperty("user.dir") + "/files/movies.txt";
        String movieLine;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(MOVIE_FILENAME));
            int movieNumber = 0;
            while ((movieLine = reader.readLine()) != null){
                String movieFields [] = movieLine.split(";");
                String title = movieFields[0];
                int year = Integer.parseInt(movieFields[1]);
                String director = movieFields[2];
                String rating = movieFields[3];
                addMovie(++movieNumber,new Movie(title, year, director, rating));
            }
            reader.close();
        }
        catch (IOException exception){
            //ignore for now
        }

    }

    private void populateBookCatalogue() {
        addBook(1, new Book("Test Driven Development By Example","Kent Beck", false));
        addBook(2, new Book("Floyd Electronic","Floyd", false));
        addBook(3, new Book("How To Dance 101","Anonymous Famous", false));
        addBook(4, new Book("Lessons of Here","Anonymous Famous", false));
    }

    private static String menu(){
        String menu = "Menu\n================\n";
        int optionNumber;
        for(optionNumber = 1; optionNumber <= MENU_OPTIONS.length; optionNumber++){
            menu += optionNumber + ". " + MENU_OPTIONS[optionNumber - 1] + "\n";
        }
        menu += "Select an option: ";
        return menu;
    }

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        new Library(System.out, inputScanner).run();
    }

}
