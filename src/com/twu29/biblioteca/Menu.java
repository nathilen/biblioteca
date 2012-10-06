package com.twu29.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    static final String INVALID_MENU_OPTION = "Select a valid option!!!";
    private List<Item> items;
    private final String [] MENU_OPTIONS = {"List Book Catalog", "List Movie Catalog", "Check My Details"};

    public Menu(Library library) {
        items = new ArrayList<Item>();
        items.add(new Item(MENU_OPTIONS[0], library.bookCatalogue()));
        items.add(new Item(MENU_OPTIONS[1], library.movieCatalogue()));
        items.add(new Item(MENU_OPTIONS[2], library.checkUser()));
    }

    public String processItem(int itemNumber) {

        if (itemNumber < 0 || itemNumber >= items.size()){
            return INVALID_MENU_OPTION;
        }
        Item item = items.get(itemNumber);
        return item.deliver();
    }

    public String [] menuOptions() {
        return MENU_OPTIONS;
    }
}
