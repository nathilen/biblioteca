package com.twu29.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestMenu {

    private Menu menu;

    @Before
    public void setUp() throws Exception {
        menu = new Menu();
    }

    @Test
    public void shouldAddMenuItem() throws Exception {
        String text = "My Name";
        String message = "Charlene";
        menu.add(new Item(text, message));
        assertThat(menu.processItem(0), is (message));
    }

    @Test
    public void shouldBeNotifiedOfInvalidMenuOption() throws Exception {
        assertThat(menu.processItem(-1), is(Menu.INVALID_MENU_OPTION));
    }
}
