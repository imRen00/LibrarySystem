/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.librarysystem;
import java.util.ArrayList;
import java.util.Scanner; 

/**
 *
 * @author reno.
 */

public class LibrarySystem {

    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);

        library.addItem(new Book("B1", "Java Basics", "James Gosling"));
        library.addItem(new Book("B2", "OOP in Java", "Herbert Schildt"));

        while (true) {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Show all books");
            System.out.println("2. Borrow book");
            System.out.println("3. Return book");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> library.showAllItems();
                case 2 -> {
                    System.out.print("Enter book ID: ");
                    library.borrowItem(sc.nextLine());
                }
                case 3 -> {
                    System.out.print("Enter book ID: ");
                    library.returnItem(sc.nextLine());
                }
                case 4 -> {
                    System.out.println("Goodbye!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
    

 abstract class LibraryItem {
     
    private String id;
    private String title;
    private boolean isAvailable;

    public LibraryItem(String id, String title) {
        this.id = id;
        this.title = title;
        this.isAvailable = true;
    }

    public String getId() { 
        return id; 
    }
    
    public String getTitle() { 
        return title; 
    }
    public boolean isAvailable() { 
        return isAvailable; 
    }

    public void borrowItem() { 
        isAvailable = false; 
    }
    
    public void returnItem() { 
        isAvailable = true; 
    }

    public abstract String getDetails(); // Polymorphism
}

 class Book extends LibraryItem {
    private String author;

    public Book(String id, String title, String author) {
        super(id, title);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String getDetails() {
        return "Book ID: " + getId() +
               ", Title: " + getTitle() +
               ", Author: " + author +
               ", Available: " + isAvailable();
    }
}

  class Member {
      
    private String memberId;
    private String name;

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    
    public String getMemberId() { 
        return memberId; 
    }
    
    public String getName() { 
        return name; 
    }
}

 class Library {
     
    private ArrayList<LibraryItem> items = new ArrayList<>();

    public void addItem(LibraryItem item) {
        items.add(item);
        System.out.println("Item added successfully.");
    }

    public void showAllItems() {
        for (LibraryItem item : items) {
            System.out.println(item.getDetails());
        }
    }

    public LibraryItem findItemById(String id) {
        for (LibraryItem item : items) {
            if (item.getId().equalsIgnoreCase(id)) {
                return item;
            }
        }
        return null;
    }

    public void borrowItem(String id) {
        LibraryItem item = findItemById(id);
        if (item != null && item.isAvailable()) {
            item.borrowItem();
            System.out.println("Item borrowed successfully.");
        } else {
            System.out.println("Item not available or not found.");
        }
    }

    public void returnItem(String id) {
        LibraryItem item = findItemById(id);
        if (item != null && !item.isAvailable()) {
            item.returnItem();
            System.out.println("Item returned successfully.");
        } else {
            System.out.println("Invalid return attempt.");
        }
    }
    
}