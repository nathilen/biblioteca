package com.twu29.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    static final String INVALID_MENU_OPTION = "Select a valid option!!!";
    private List<Item> items;

    public Menu() {
        items = new ArrayList<Item>();
    }

    public void add(Item item) {
        items.add(item);
    }

    public String processItem(int itemNumber) {

        if (itemNumber < 0 || itemNumber >= items.size()){
            return INVALID_MENU_OPTION;
        }
        Item item = items.get(itemNumber);
        return item.deliver();
    }

}
