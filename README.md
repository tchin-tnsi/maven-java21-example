# Maven Java 21 Example Project

A modern Maven Java 21 example project demonstrating the latest Java features and best practices.

## Features

This project showcases several Java 21 features and modern development practices:

### Java 21 Features Demonstrated

- **Records** (Java 16+): Immutable data carriers with compact syntax
- **Pattern Matching**: Switch expressions with pattern matching
- **Virtual Threads**: Project Loom - lightweight, efficient threading model
- **Text Blocks**: Multi-line string literals (Java 13+)

### Project Structure

```
maven-java21-example/
├── pom.xml                          # Maven configuration
├── src/
│   ├── main/
│   │   ├── java/com/example/
│   │   │   └── App.java            # Main application with Java 21 examples
│   │   └── resources/
│   │       └── logback.xml         # Logging configuration
│   └── test/
│       └── java/com/example/
│           └── AppTest.java        # Unit tests
├── .gitignore                       # Git ignore rules
└── README.md                        # This file
```

## Prerequisites

- **Java 21** or later
- **Maven 3.8.1** or later

Check your versions:
```bash
java --version
mvn --version
```

## Building the Project

### Compile the Project
```bash
mvn clean compile
```

This compiles all source files in `src/main/java` and `src/test/java`.

### Run Tests
```bash
mvn test
```

Executes all unit tests in `src/test/java` using JUnit 5.

### Build JAR (Package)
```bash
mvn package
```

This creates two JAR files in the `target/` directory:
- **maven-java21-example-1.0.0.jar** - Standard JAR with manifest
- **maven-java21-example-1.0.0-shaded.jar** - Fat JAR with all dependencies included

### Run the Application

#### Option 1: Using Maven exec plugin
```bash
mvn exec:java -Dexec.mainClass="com.example.App"
```

#### Option 2: Run packaged JAR
First, build the package:
```bash
mvn clean package
```

Then run the JAR:
```bash
java -jar target/maven-java21-example-1.0.0-shaded.jar
```

#### Option 3: Run with preview features enabled (if needed)
```bash
java --enable-preview -jar target/maven-java21-example-1.0.0-shaded.jar
```

## Expected Output

When you run the application, you should see output similar to:

```
16:10:51.234 [main] INFO com.example.App - Starting Maven Java 21 Example Application
16:10:51.245 [main] INFO com.example.App - Person: Alice is 30 years old
16:10:51.256 [main] INFO com.example.App - Creating virtual threads
16:10:51.267 [main] INFO com.example.App - Virtual thread 0 executing
16:10:51.268 [main] INFO com.example.App - Virtual thread 1 executing
16:10:51.268 [main] INFO com.example.App - Virtual thread 2 executing
16:10:51.269 [main] INFO com.example.App - Virtual thread 3 executing
16:10:51.269 [main] INFO com.example.App - Virtual thread 4 executing
16:10:51.380 [main] INFO com.example.App - Pattern matching in switch:
16:10:51.381 [main] INFO com.example.App -   Person named Bob, age 25
16:10:51.382 [main] INFO com.example.App -   String: Hello String
16:10:51.382 [main] INFO com.example.App -   Integer: 42
16:10:51.383 [main] INFO com.example.App -   Double: 3.14
16:10:51.384 [main] INFO com.example.App - Application completed successfully
```

## Code Examples

### Records
```java
record Person(String name, int age) {
    public Person {
        if (age < 0) throw new IllegalArgumentException("Age cannot be negative");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be blank");
    }
}
```

### Pattern Matching
```java
if (obj instanceof Person(String name, int age)) {
    System.out.println("Person: " + name + ", age: " + age);
}
```

### Switch Pattern Matching
```java
String result = switch (obj) {
    case Person(String name, int age) -> String.format("%s is %d", name, age);
    case String s -> "String: " + s;
    case Integer i -> "Integer: " + i;
    default -> "Unknown type";
};
```

### Virtual Threads
```java
try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
    for (int i = 0; i < 5; i++) {
        executor.submit(() -> {
            // Task executed in a virtual thread
            doWork();
        });
    }
}
```

## Maven Commands Quick Reference

| Command | Description |
|---------|-------------|
| `mvn clean` | Remove the target directory |
| `mvn compile` | Compile source code |
| `mvn test` | Run unit tests |
| `mvn package` | Create JAR files |
| `mvn clean package` | Clean and package (recommended) |
| `mvn exec:java -Dexec.mainClass="com.example.App"` | Run the application directly |
| `mvn help:describe -Dplugin=compiler` | Show compiler plugin details |

## Dependencies

### Runtime
- **SLF4J** (2.0.9): Simple Logging Facade for Java
- **Logback** (1.4.14): Flexible logging framework

### Test
- **JUnit 5** (5.10.1): Modern Java testing framework

## Maven Plugins Used

- **maven-compiler-plugin** (3.11.0): Compiles Java 21 code with preview features
- **maven-jar-plugin** (3.3.0): Creates executable JAR with manifest
- **maven-shade-plugin** (3.5.0): Creates fat JAR with all dependencies
- **maven-surefire-plugin** (3.1.2): Runs unit tests
- **maven-failsafe-plugin** (3.1.2): Runs integration tests

## Java 21 Resources

- [Java 21 Documentation](https://docs.oracle.com/en/java/javase/21/)
- [Java 21 Release Notes](https://www.oracle.com/java/technologies/javase/21-relnotes-intro.html)
- [Project Loom - Virtual Threads](https://openjdk.org/projects/loom/)
- [Pattern Matching](https://openjdk.org/projects/amber/design-notes/patterns/intro)
- [Records](https://openjdk.org/projects/amber/records/)

## Troubleshooting

### "Java version not supported"
Ensure you have Java 21 or later installed:
```bash
java --version
```

### "Module not found"
Run Maven clean and rebuild:
```bash
mvn clean install
```

### Preview feature warnings
These are expected in Java 21. The pom.xml is configured to enable preview features.

## Contributing

Feel free to fork this project and experiment with Java 21 features!

## License

This project is provided as-is for educational and reference purposes.

---

Happy coding with Java 21! 🚀
