package com.twu29.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Library {

    static final String GENERIC_USER_MESSAGE = "Please talk to Librarian. Thank you.";
    static final String RESERVED_UNAVAILABLE_BOOK = "Sorry we don't have that book yet.";
    static final String RESERVED_AVAILABLE_BOOK = "Thank You! Enjoy the book.";
    private User loggedInUser;
    private List <Movie> movies;
    private List<Book> books;

    public Library() {
        books = new ArrayList<Book>();
        movies = new ArrayList<Movie>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
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

        for(int movieNumber = 0; movieNumber < movies.size(); movieNumber++){
            movieMenu += movies.get(movieNumber).movieLine(lineFormat);
        }
        return movieMenu;
    }

    public String reserveBook(int selection) {
        try{
            Book book = books.get(selection);
            if (book.reserve()){
                return RESERVED_AVAILABLE_BOOK;
            }
            else{
                return RESERVED_UNAVAILABLE_BOOK;
            }

        } catch (Exception exception){
            return RESERVED_UNAVAILABLE_BOOK;
        }

    }

    public String doLogin(String username, String password) {
        User user = new User(username, password);
        try{
            user.login();
            loggedInUser = user;
        }
        catch(LibraryException exception){
            return exception.getMessage();
        }
        return "User successfully logged in";
    }

    public String userDetails() {
        if (loggedInUser != null){
            return "Hi " + loggedInUser.getUsername() + "!";
        }
        else{
            return GENERIC_USER_MESSAGE;
        }
    }

    public boolean hasLoggedInUser(){
        return loggedInUser != null;
    }
}
