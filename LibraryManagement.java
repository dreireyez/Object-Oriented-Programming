import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManagement {
    public static void main(String[] args) {
        Library library = new Library();
        library.initializeSampleBooks();
        library.run();
    }
}

class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    // Manual Input of Books
    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
    }

    // Getters
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getIsbn() {
        return isbn;
    }
    public boolean getIsAvailable() {
        return isAvailable;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}

class Library {
    private ArrayList<Book> books;
    public Library() {
        books = new ArrayList<>();
    }
    public void addBook(Book book) {
        books.add(book);
    }
    private Book getBookByISBN (String ISBN) {
        for (Book b : books) {
            if (b.getIsbn().equals(ISBN)) {
                return b;
            }
        }
        return null;
    }
    public void borrowBook(String isbn) {
        Book book = getBookByISBN(isbn);
        if (book == null) {
            System.out.println("Book not found.");
        }
        else if (!book.getIsAvailable()) {
            System.out.println("Book is not available.");
        }
        else {
            book.setIsAvailable(false);
            System.out.println("You have borrowed book: " + book.getTitle());
        }

    }
    public void returnBook(String isbn) {
        Book book = getBookByISBN(isbn);
        if (book == null) {
            System.out.println("Book not found.");
        }
        else if (book.getIsAvailable()) {
            System.out.println("Book is already in library.");
        }
        else {
            book.setIsAvailable(true);
            System.out.println("You have returned book: " + book.getTitle());
        }

    }
    public void displayBooks() {
        System.out.println();
        for (Book b : books) {
            System.out.println("- Title: "+b.getTitle()+" | Author: "+b.getAuthor()+" | ISBN: "+b.getIsbn()+" | Available: "+b.getIsAvailable());
        }
        System.out.println();
    }
    public void run() {
        boolean run = true;
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the Library Management System!");
        System.out.println("1. Display Books");
        System.out.println("2. Borrow a Book");
        System.out.println("3. Return a Book");
        System.out.println("4. Exit");
        while (run) {
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    displayBooks();
                    break;
                case 2:
                    System.out.print("Enter ISBN to borrow: ");
                    String ISBN1 = input.nextLine();
                    borrowBook(ISBN1);
                    break;
                case 3:
                    System.out.print("Enter ISBN to return: ");
                    String ISBN2 = input.nextLine();
                    returnBook(ISBN2);
                    break;
                case 4:
                    System.out.println("\nGoodbye!");
                    run = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
        input.close();
    }
    public void initializeSampleBooks() {
        books.add(new Book("Clean Code", "Robert C. Martin", "978-0132350884"));
        books.add(new Book("Java Concurrency in Practice", "Brian Goetz", "978-0321349606"));
        books.add(new Book("Effective Java", "Joshua Bloch", "978-0134685991"));
    }
}
