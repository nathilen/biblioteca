package com.twu29.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestMenu {

    private Menu menu;

    @Before
    public void setUp() throws Exception {
        menu = new Menu(new Library());
    }

    @Test
    public void shouldBeNotifiedOfInvalidMenuOption() throws Exception {
        assertThat(menu.processItem(10), is(Menu.INVALID_MENU_OPTION));
    }
}
