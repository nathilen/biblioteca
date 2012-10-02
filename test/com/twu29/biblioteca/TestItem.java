package com.twu29.biblioteca;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TestItem {
    @Test
    public void shouldDeliverCorrectMessage() throws Exception {
        String message = "Charlene Tshitoka";
        assertThat(new Item("Display Name", message).deliver(), is (message));
    }


}
