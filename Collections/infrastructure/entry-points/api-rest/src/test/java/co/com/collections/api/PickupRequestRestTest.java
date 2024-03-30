package co.com.collections.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PickupRequestRestTest {

    PickupRequestRest pickupRequestRest = new PickupRequestRest();

    @Test
    void apiRestTest() {
        var response = pickupRequestRest.commandName();
        assertEquals("Hello World", response);
    }
}
