# draw-app

A basic desktop drawing app built with Kotlin and Swing.

## Features

- Draw line, rectangle, and circle shapes on a canvas with mouse drag.
- Toolbar shape selector to switch between different drawing tools.
- Stroke color picker button to change shape colors.
- Stroke width selector to adjust line thickness.
- Undo last action to remove the most recent shape.
- Clear canvas to start fresh.

## Run

```bash
./gradlew run
```

## Test

```bash
./gradlew test
```

# Overview

As a software engineer, I am exploring Kotlin to understand its modern syntax improvements over Java and its suitability for desktop GUI applications. This project served as a hands-on exercise to practice core Kotlin concepts while building a functional utility.

I wrote this Basic Drawing App to demonstrate Kotlin's ability to interface with standard Java libraries (AWT/Swing) while utilizing modern language features like data classes, sealed interfaces, and safe null handling. The application allows users to interactively draw geometric shapes on a canvas using mouse input.

My purpose for writing this software was to gain proficiency in Kotlin's object-oriented principles, collection management, and event-driven programming.

[Software Demo Video](https://www.loom.com/share/1ec5edec41904451ab383558dfc006c6https://www.loom.com/share/1ec5edec41904451ab383558dfc006c6)

# Development Environment

To develop this software, I used:

- **IDE**: Android Studio / IntelliJ IDEA
- **Build System**: Gradle (Kotlin DSL)
- **JDK**: OpenJDK 17

The software is written in **Kotlin 1.9** and uses the following libraries:

- **Java Swing**: For the GUI components (JFrame, JPanel, JToolBar).
- **Java AWT**: For graphics rendering, colors, and event handling.

# Useful Websites

- [Kotlin Official Documentation](https://kotlinlang.org/docs/home.html)
- [Java Swing Tutorial (Oracle)](https://docs.oracle.com/javase/tutorial/uiswing/)
- [Kotlin for Java Developers](https://kotlinlang.org/docs/java-to-kotlin-idioms-look-up.html)

# Future Work

- **Persistence**: Add the ability to save and load drawings to/from files.
- **Advanced Tools**: Implement a "Select/Move" tool to reposition existing shapes.
- **Fill Colors**: Allow shapes to have separate stroke and fill color properties.
