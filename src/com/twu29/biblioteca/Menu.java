package com.twu29.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private List<Item> items;

    public Menu(Library library) {
        items = new ArrayList<Item>();
        items.add(new Item("List Book Catalog", library.bookCatalogue()));
        items.add(new Item("List Movie Catalog", library.movieCatalogue()));
        items.add(new Item("Check My Details", library.checkUser()));
    }

    public String processItem(int itemNumber) {

        if (itemNumber < 0 || itemNumber >= items.size()){
            return Biblioteca.INVALID_MENU_OPTION;
        }
        Item item = items.get(itemNumber);
        return item.deliver();
    }
}
