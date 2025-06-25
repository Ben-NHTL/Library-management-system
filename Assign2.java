/**
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * Student Name: Hoang Thien Loc Nguyen
 * Student Number: 041165148
 * Section #: 301
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD.
 */

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main class to run the Library Management System. Handles the main menu loop
 * and delegates functionality to the Library class.
 */
public class Assign2 {

	/**
	 * Main entry point for the Library Management System program.
	 * 
	 * @param args command-line arguments (not used)
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); // Scanner for user input
		Library library = new Library(); // Create a new Library instance
		int choice; // User's menu selection

		System.out.println("=== LIBRARY MANAGEMENT SYSTEM ===");

		// Main menu loop
		while (true) {
			try {
				displayMenu(); // Show the menu
				choice = sc.nextInt(); // Read user choice

				switch (choice) {
				case 1:
					library.addBook(sc); // Add a book
					break;

				case 2:
					System.out.println(library); // Display library catalog
					break;

				case 3:
					library.borrowBook(sc); // Borrow a book
					break;

				case 4:
					library.returnBook(sc); // Return a book
					break;

				case 5:
					library.searchForBook(sc); // Search for a book by code
					break;

				case 6:
					library.saveToFile(sc); // Save catalog data to file
					break;

				case 7:
					library.readFromFile(sc); // Load catalog data from file
					break;

				case 8:
					System.out.println("Exiting... <Program by Hoang Thien Loc Nguyen>");
					return; // Exit the program

				default:
					// Handle out-of-range menu choices
					System.out.println("...Invalid input... Please try again....");
				}
			} catch (InputMismatchException e) {
				// Handle non-integer input
				System.out.println("...Invalid input. Please enter a valid number...");
				sc.nextLine(); // Clear invalid input
			}
		}
	}

	/**
	 * Displays the menu options for the user.
	 */
	public static void displayMenu() {
		System.out.println("\nPlease select one of the following:");
		System.out.println("1: Add Book to Library");            // Option to add a new book
		System.out.println("2: Display Current Library Catalog"); // Option to list all books
		System.out.println("3: Borrow Book(s)");                  // Option to borrow a book
		System.out.println("4: Return Book(s)");                  // Option to return a borrowed book
		System.out.println("5: Search for Book");                 // Option to search a book by code
		System.out.println("6: Save Library Catalog to File");    // Option to write catalog to a file
		System.out.println("7: Read Library Catalog from File");  // Option to read catalog from a file
		System.out.println("8: To Exit");                         // Exit the program
		System.out.print("> ");                                   // Prompt for user input
	}
}
