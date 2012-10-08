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

    static final String WELCOME =  "----------------------------------------------------\n" +
            "|  Welcome To The Bangalore Public Library System   |\n"+
            "----------------------------------------------------";
    private static final String MENU_OPTIONS [] = {"Login","List Book Catalog","List Movie Catalog","Check Out Book","Check My Details","Exit"};
    private static final String GOODBYE_MESSAGE = "Thank you for using Biblioteca. See you next time";



    public Biblioteca(PrintStream outputStream, Scanner scanner) {
        this.outputStream = outputStream;
        this.scanner = scanner;
        this.library = new Library();
        prepareMenu();
    }

    public void printMessage(String message){
        outputStream.println(message);
    }

    public String getUserInput(String message){
        printMessage(message);
        return scanner.next();
    }

    public String reserveBook() {
        if (library.hasLoggedInUser()) {
            String userInput = getUserInput("Enter the book number: ");
            int bookNumber = Integer.parseInt(userInput) - 1;
            return library.reserveBook(bookNumber);
        }
        else {
            return "You must first login to check out a book";
        }
    }

    public Library getLibrary() {
        return library;
    }

    private void prepareMenu() {
        populateMovieCatalogue();
        populateBookCatalogue();

        this.menu = new Menu();
        menu.add(new LoginItem(MENU_OPTIONS[0], this));
        menu.add(new Item(MENU_OPTIONS[1], library.bookCatalogue()));
        menu.add(new Item(MENU_OPTIONS[2], library.movieCatalogue()));
        menu.add(new ReservationItem(MENU_OPTIONS[3], this));
        menu.add(new UserItem(MENU_OPTIONS[4], this));
        menu.add(new Item(MENU_OPTIONS[5], GOODBYE_MESSAGE));
    }

    private void populateMovieCatalogue() {
        String fileSeparator = System.getProperty("file.separator");
        String userDirectory = System.getProperty("user.dir");
        final String MOVIE_FILENAME = userDirectory + fileSeparator +
                                      "files" + fileSeparator + "movies.txt";
        String movieLine;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(MOVIE_FILENAME));
            while ((movieLine = reader.readLine()) != null){
                String movieFields [] = movieLine.split(";");
                String title = movieFields[0];
                int year = Integer.parseInt(movieFields[1]);
                String director = movieFields[2];
                library.addMovie(new Movie(title, year, director));
            }
            reader.close();
        }
        catch (IOException exception){
            //ignore for now
        }

    }

    private void populateBookCatalogue() {
        library.addBook(new Book("Test Driven Development By Example", "Kent Beck"));
        library.addBook(new Book("Floyd Electronic", "Floyd"));
        library.addBook(new Book("How To Dance 101", "Anonymous Famous"));
        library.addBook(new Book("Lessons of Here", "Anonymous Famous"));
    }

    private void processMenuSelection(String userInput) {
        int selection = Integer.parseInt(userInput);
        String message = menu.processItem(selection - 1);
        printMessage(message);
    }

    public String menuContent(){
        String content = WELCOME + "\nMenu\n================\n";
        for(int number = 1; number <= MENU_OPTIONS.length; number++){
            content += number + ". " + MENU_OPTIONS[number - 1] + "\n";
        }
        return content;
    }

    public void run() {
        String content = menuContent();
        while(true){
            printMessage(content);
            String userInput = getUserInput("Select an option: ");
            processMenuSelection(userInput);

            if (userInput.equals("6")){
                break;
            }
        }
    }

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca(System.out, new Scanner(System.in));
        biblioteca.run();
    }
}
