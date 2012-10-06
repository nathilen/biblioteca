package com.twu29.biblioteca;

import java.util.Hashtable;

public class Library {

    static final String GENERIC_USER_MESSAGE = "Please talk to Librarian. Thank you.";
    private User loggedInUser;
    private Hashtable<Integer, Movie> movies;
    private static Hashtable<Integer,Book> books;

    public Library() {
        books = new Hashtable<Integer, Book>();
        movies = new Hashtable<Integer, Movie>();
    }

    public void addBook(int bookNumber, Book book) {
        books.put(bookNumber, book);
    }

    public void addMovie(int movieNumber, Movie movie) {
        movies.put(movieNumber, movie);
    }

    public String bookCatalogue(){
        String bookMenu = "\n\nOur Books\n------------\n";
        for (int bookNumber = 0; bookNumber < books.size(); bookNumber++){
            bookMenu += (bookNumber + 1) + ". " + books.get(bookNumber)+ "\n";
        }
        return bookMenu;
    }

    public String movieCatalogue() {
        String lineFormat = "%-20s%-10d%-20s%-3s\n";
        String headerFormat = "\n\n%s\n%s\n%-20s%-10s%-20s%-3s\n\n";
        String movieMenu = String.format(headerFormat,"Our Movies",
                                    "------------","Movie","Year","Director","Rating");

        for(int movieNumber = 1; movieNumber <= movies.size(); movieNumber++){
            movieMenu += movies.get(movieNumber).movieLine(lineFormat);
        }
        return movieMenu;
    }

    public String reserveBook(int selection) {
        try{
            Book book = books.get(selection);
            if (!book.isReserved()){
                book.setReserved(true);
                return Biblioteca.RESERVED_AVAILABLE_BOOK;
            }
            else{
                return Biblioteca.RESERVED_UNAVAILABLE_BOOK;
            }

        } catch (Exception exception){
            return Biblioteca.RESERVED_UNAVAILABLE_BOOK;
        }
    }

    public String doLogin(String username, String password) {
        User user = new User(username,password);
        user.login();
        loggedInUser = user;
        return "User successfully logged in";
    }

    public String checkUser() {
        if (loggedInUser != null){
            return "Hi " + loggedInUser.getUsername() + "!";
        }
        else{
            return GENERIC_USER_MESSAGE;
        }
    }
}
