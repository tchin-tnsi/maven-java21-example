package com.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the application.
 */
@DisplayName("Application Tests")
class AppTest {

    @Test
    @DisplayName("Should create a valid Person record")
    void testPersonCreation() {
        Person person = new Person("John", 30);
        assertEquals("John", person.name());
        assertEquals(30, person.age());
    }

    @Test
    @DisplayName("Should throw exception for negative age")
    void testPersonWithNegativeAge() {
        assertThrows(IllegalArgumentException.class, () -> new Person("Jane", -5));
    }

    @Test
    @DisplayName("Should throw exception for blank name")
    void testPersonWithBlankName() {
        assertThrows(IllegalArgumentException.class, () -> new Person("   ", 25));
    }

    @Test
    @DisplayName("Should demonstrate pattern matching")
    void testPatternMatching() {
        Object person = new Person("Alice", 28);
        
        if (person instanceof Person(String name, int age)) {
            assertEquals("Alice", name);
            assertEquals(28, age);
        } else {
            fail("Pattern matching failed");
        }
    }

    @Test
    @DisplayName("Should demonstrate switch expression with pattern matching")
    void testSwitchPatternMatching() {
        Object obj = new Person("Bob", 35);
        
        String result = switch (obj) {
            case Person(String name, int age) -> String.format("%s is %d", name, age);
            default -> "Unknown";
        };
        
        assertEquals("Bob is 35", result);
    }
}
