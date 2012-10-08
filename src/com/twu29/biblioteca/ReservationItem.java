package com.twu29.biblioteca;

public class ReservationItem extends LoginItem {
    public ReservationItem(String text, Biblioteca biblioteca) {
        super(text, biblioteca);
    }

    @Override
    public String deliver() {
        return biblioteca.reserveBook();
    }
}
