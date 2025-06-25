/**
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * Student Name: Hoang Thien Loc Nguyen
 * Student Number: 041165148
 * Section #: 301
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD.
 */

import java.util.Scanner; // Imports Scanner for user input
import java.io.PrintWriter; // Imports PrintWriter for file output

/**
 * Represents a FictionBook, which is a type of Book with the genre set to
 * "Fiction".
 */
public class FictionBook extends Book {

	/**
	 * Constructor that sets the genre of the book to "Fiction".
	 */
	public FictionBook() {
		super(); // Calls the superclass (Book) constructor
		setGenre("Fiction"); // Sets the genre to "Fiction"
	}

	/**
	 * Adds a FictionBook using the base Book's addBook method.
	 * 
	 * @param sc Scanner object for user input
	 * @return true if book details were successfully entered, false otherwise
	 */
	@Override
	public boolean addBook(Scanner sc) {
		if (!super.addBook(sc)) { // Calls the superclass's addBook method
			return false; // If superclass addBook fails, return false
		}
		return true; // Otherwise, return true
	}

	/**
	 * Returns a string representation of the FictionBook.
	 * 
	 * @return formatted string containing book details
	 */
	@Override
	public String toString() {
		return super.toString(); // Calls the base class toString method
	}
	
	/**
	 * Outputs FictionBook data to a file using the provided PrintWriter.
	 * 
	 * @param pw PrintWriter object for writing to a file
	 */
	@Override
	public void outputBook(PrintWriter pw) {
		pw.println("f"); // Identifier for FictionBook type
		pw.println(getBookCode()); // Outputs the book code
		pw.println(getTitle()); // Outputs the title
		pw.println(getQuantity()); // Outputs the quantity
		pw.println(getAuthor()); // Outputs the author
	}
}
