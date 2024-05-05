package com.collectionpoints.api;

import org.junit.jupiter.api.Test;

import com.collectionpoints.api.ApiRest;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApiRestTest {

    ApiRest apiRest = new ApiRest();

    @Test
    void apiRestTest() {
        var response = apiRest.commandName();
        assertEquals("Hello World", response);
    }
}
