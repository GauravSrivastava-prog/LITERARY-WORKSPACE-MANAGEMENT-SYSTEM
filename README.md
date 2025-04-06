# Literary Workspace Management System

A Java console application that provides a collaborative environment for writers to create and manage content, and for readers to discover and access books.

## Features

### Writer Features
- Create new books with custom titles and content
- Edit existing books by appending new content
- Delete books from the system
- Automatic tracking of book authorship and editing history

### Reader Features
- Search for books by title and read their content
- List all books written by a specific author
- Browse the complete catalog of available books

### System Features
- Simple console-based user interface
- Persistent storage of books and metadata
- Role-based access control (Writer/Reader)

## Project Structure

- `Main.java` - Entry point and main menu controller
- `Person.java` - Abstract base class for user roles
- `WriterPerson.java` - Implementation of writer-specific features
- `ReaderPerson.java` - Implementation of reader-specific features

## Data Storage

The system uses two types of files:
- `BookList.csv` - Stores metadata about all books (title, author, last editor)
- Individual `.txt` files - Each book's content stored in a separate file named after its title

## How to Use

### Getting Started
1. Compile all Java files:
   ```
   javac Main.java Person.java WriterPerson.java ReaderPerson.java
   ```
2. Run the application:
   ```
   java Main
   ```

### Main Menu Options
1. View List of Available Books
2. Enter Writer Mode
3. Enter Reader Mode
4. Exit

### Writer Mode
When entering Writer Mode, you'll be prompted for your name and then presented with these options:
1. Write a new book
2. Edit an existing book
3. Delete a book
4. Return to Main Menu

### Reader Mode
When entering Reader Mode, you'll be prompted for your name and then presented with these options:
1. Search a book via Book Title
2. List all books written by an Author
3. Exit to Main Menu

## Implementation Details

The system is built using object-oriented programming principles:
- **Abstraction**: Uses an abstract `Person` class to define common behavior
- **Inheritance**: `ReaderPerson` and `WriterPerson` extend the `Person` class
- **Encapsulation**: Access modifiers protect internal implementation details
- **File I/O**: Java's standard file operations for persistent storage

## Requirements

- Java Runtime Environment (JRE) 8 or higher
- No external libraries required

## Future Enhancements

Potential improvements for future versions:
- User authentication system
- Full edit capabilities (not just append)
- Book categorization and tagging
- Enhanced search functionality
- Improved user interface

## UML Diagram of the Project

![UML Diagram](uml_diagram.png)


