package com.sport.sport.controllers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainControllerTest {
    private final MainController mainController = new MainController();

    @Test
    public void testOk() {
        assertEquals("index", mainController.welcome());
        assertEquals("teams", mainController.teams());
        assertEquals("members", mainController.members());
    }
}
