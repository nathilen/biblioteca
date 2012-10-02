package com.twu29.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private List<Item> items;

    public Menu(Library library) {
        items = new ArrayList<Item>();
        items.add(new Item("List Book Catalog", library.bookCatalogue()));
        items.add(new Item("List Movie Catalog", library.movieCatalogue()));
    }

    public String processItem(int itemNumber) {
        Item item = items.get(itemNumber - 1);
        String output = item.deliver();
        return output;
    }
}
