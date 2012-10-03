package com.twu29.biblioteca;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Biblioteca {
    private PrintStream outputStream;
    private Scanner scanner;

    private Menu menu;
    private Library library;

    static final String INVALID_MENU_OPTION = "Select a valid option!!!";
    static final String WELCOME =  "----------------------------------------------------\n" +
                                   "|  Welcome To The Bangalore Public Library System   |\n"+
                                   "----------------------------------------------------";
    static final String MENU_OPTIONS [] = {"Login","List Book Catalog","List Movie Catalog","Check Out Book","Check My Details","Exit"};
    static final String MENU = menu();
    static final String RESERVED_AVAILABLE_BOOK = "Thank You! Enjoy the book.";
    static final String RESERVED_UNAVAILABLE_BOOK = "Sorry we don't have that book yet.";
    static final String GENERIC_USER_MESSAGE = "Please talk to Librarian. Thank you.";


    public Biblioteca(PrintStream outputStream, Scanner scanner) {
        this.outputStream = outputStream;
        this.scanner = scanner;
        this.library = new Library();
        prepareMenu();
    }

    public void printWelcome() {
        outputStream.println(WELCOME);
        outputStream.println();
    }

    public void processMenuSelection(String userInput) {
        int selection = Integer.parseInt(userInput);
        String message = menu.processItem(selection - 1);
        printMessage(message);

        switch(selection){
            case 1:
                doLogin();
                break;
            case 4:
                library.reserveBook(0);
                break;
            default:
        }
    }

    public void doLogin() {
        outputStream.print("Enter username: ");
        String username = scanner.next();
        outputStream.print("Enter password: ");
        String password = scanner.next();
        String message = library.doLogin(username, password);
        outputStream.println(message);
    }

    public void printMessage(String message){
        outputStream.println(message);
    }

    public void printMenu() {
        outputStream.println(menu());
    }

    public String getUserInput(String message){
        printMessage(message);
        return scanner.next();
    }

    public void run() {

        while(true){
            printWelcome();
            printMenu();
            String userInput = getUserInput("Select an option: ");
            processMenuSelection(userInput);
        }
    }

    private void prepareMenu() {
        populateMovieCatalogue();
        populateBookCatalogue();
        this.menu = new Menu(library);
    }

    private void populateMovieCatalogue() {
        String fileSeparator = System.getProperty("file.separator");
        String userDirectory = System.getProperty("user.dir");
        final String MOVIE_FILENAME = userDirectory + fileSeparator +
                                      "files" + fileSeparator + "movies.txt";
        String movieLine;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(MOVIE_FILENAME));
            int movieNumber = 0;
            while ((movieLine = reader.readLine()) != null){
                String movieFields [] = movieLine.split(";");
                String title = movieFields[0];
                int year = Integer.parseInt(movieFields[1]);
                String director = movieFields[2];
                library.addMovie(++movieNumber, new Movie(title, year, director));
            }
            reader.close();
        }
        catch (IOException exception){
            //ignore for now
        }

    }

    private void populateBookCatalogue() {
        library.addBook(1, new Book("Test Driven Development By Example", "Kent Beck", false));
        library.addBook(2, new Book("Floyd Electronic", "Floyd", false));
        library.addBook(3, new Book("How To Dance 101", "Anonymous Famous", false));
        library.addBook(4, new Book("Lessons of Here", "Anonymous Famous", false));
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
        new Biblioteca(System.out, inputScanner).run();
    }
}
