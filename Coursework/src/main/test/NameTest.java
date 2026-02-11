package main.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import main.Name;

public class NameTest {

    @Test
    void testGetInitials() {
        Name name = new Name("Babul", "Bati");
        assertEquals("BB", name.getInitials());
    }

    @Test
    void testToString() {
        Name name = new Name("Bimala", "Tandukar");
        assertEquals("Bimala Tandukar", name.toString());
    }
}

