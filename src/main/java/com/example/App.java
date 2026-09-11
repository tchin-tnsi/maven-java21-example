package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main application class demonstrating Java 21 features.
 */
public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Starting Maven Java 21 Example Application");

        // Record Pattern Matching (Java 21 feature)
        Person person = new Person("Alice", 30);
        printPersonInfo(person);

        // Virtual Threads (Java 21 feature)
        demonstrateVirtualThreads();

        // Pattern Matching for Switch (Java 21 feature)
        demonstratePatternMatching();

        logger.info("Application completed successfully");
    }

    /**
     * Demonstrate pattern matching with records.
     */
    static void printPersonInfo(Object obj) {
        if (obj instanceof Person(String name, int age)) {
            logger.info("Person: {} is {} years old", name, age);
        }
    }

    /**
     * Demonstrate virtual threads (Project Loom).
     */
    static void demonstrateVirtualThreads() {
        logger.info("Creating virtual threads");
        
        try (var executor = java.util.concurrent.Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 5; i++) {
                final int taskId = i;
                executor.submit(() -> {
                    logger.info("Virtual thread {} executing", taskId);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        logger.error("Virtual thread interrupted", e);
                    }
                });
            }
        }
    }

    /**
     * Demonstrate pattern matching in switch expressions.
     */
    static void demonstratePatternMatching() {
        Object[] objects = {
            new Person("Bob", 25),
            "Hello String",
            42,
            3.14
        };

        logger.info("Pattern matching in switch:");
        for (Object obj : objects) {
            String result = switch (obj) {
                case Person(String name, int age) -> String.format("Person named %s, age %d", name, age);
                case String s -> String.format("String: %s", s);
                case Integer i -> String.format("Integer: %d", i);
                case Double d -> String.format("Double: %.2f", d);
                default -> "Unknown type";
            };
            logger.info("  {}", result);
        }
    }
}

/**
 * A record demonstrating Java 16+ records feature.
 * Compact and immutable data carrier.
 */
record Person(String name, int age) {
    /**
     * Compact constructor with validation.
     */
    public Person {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank");
        }
    }
}
