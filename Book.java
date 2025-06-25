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
import java.io.PrintWriter;

/**
 * The Book class represents a general book with basic details such as code,
 * title, author, genre, quantity in stock, and quantity borrowed. It supports
 * operations like input, update, and comparison.
 */
public class Book implements Comparable<Book> {
	// Unique identifier for the book
	private int bookCode;

	// Current number of copies available
	private int quantityInStock;

	// Number of copies currently borrowed
	private int quantityBorrowed;

	// Title of the book
	private String title;

	// Author of the book
	private String author;

	// Genre/category of the book
	private String genre;

	/**
	 * Default constructor for Book.
	 */
	public Book() {
	}

	/**
	 * Sets the number of copies currently borrowed.
	 * 
	 * @param quantityBorrowed the borrowed quantity to set
	 */
	public void setQuantityBorrowed(int quantityBorrowed) {
		this.quantityBorrowed = quantityBorrowed;
	}

	/**
	 * Gets the number of copies currently borrowed.
	 * 
	 * @return quantity borrowed
	 */
	public int getQuantityBorrowed() {
		return quantityBorrowed;
	}

	/**
	 * Sets the quantity in stock.
	 * 
	 * @param quantityInStock the quantity to set
	 */
	public void setQuantity(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	/**
	 * Gets the quantity in stock.
	 * 
	 * @return quantity in stock
	 */
	public int getQuantity() {
		return quantityInStock;
	}

	/**
	 * Sets the book code.
	 * 
	 * @param bookCode the book code to set
	 */
	public void setBookCode(int bookCode) {
		this.bookCode = bookCode;
	}

	/**
	 * Gets the book code.
	 * 
	 * @return book code
	 */
	public int getBookCode() {
		return bookCode;
	}

	/**
	 * Sets the genre of the book.
	 * 
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * Gets the genre of the book.
	 * 
	 * @return genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * Sets the title of the book.
	 * 
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the title of the book.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the author of the book.
	 * 
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Gets the author of the book.
	 * 
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Updates the quantity in stock by a given amount.
	 * 
	 * @param amount the number to add (positive) or subtract (negative)
	 * @return true if update is valid; false if it would result in negative stock
	 */
	public boolean updateQuantity(int amount) {
		if (quantityInStock + amount < 0) {
			return false; // prevent stock from going below zero
		}
		quantityInStock += amount;
		return true;
	}

	/**
	 * Compares this book to another based on title and author (case-insensitive).
	 * 
	 * @param book the book to compare with
	 * @return true if title and author match; false otherwise
	 */
	public boolean isEqual(Book book) {
		return this.title.equalsIgnoreCase(book.title) && this.author.equalsIgnoreCase(book.author);
	}

	/**
	 * Prompts the user to enter a valid book code. Loops until a valid integer is
	 * entered.
	 * 
	 * @param sc Scanner object for input
	 * @return true when a valid book code has been entered
	 */
	public boolean inputCode(Scanner sc) {
		while (true) {
			try {
				System.out.print("Enter the code for the book: ");
				bookCode = sc.nextInt(); // attempt to read integer
				return true; // valid input
			} catch (InputMismatchException e) {
				System.out.println("Invalid code...");
				sc.nextLine(); // clear invalid input from scanner buffer
			}
		}
	}

	/**
	 * Prompts the user to enter book details (code, title, author, quantity).
	 * Includes input validation for book code and quantity.
	 * 
	 * @param sc Scanner object for input
	 * @return true if input is successful; false if any part fails
	 */
	public boolean addBook(Scanner sc) {
		// Prompt and validate book code
		inputCode(sc);

		sc.nextLine(); // consume leftover newline after nextInt()

		// Prompt for title
		System.out.print("Enter the title of the book: ");
		title = sc.nextLine();

		// Prompt for author
		System.out.print("Enter the author of the book: ");
		author = sc.nextLine();

		// Prompt and validate quantity input
		while (true) {
			System.out.print("Enter the quantity of the book: ");
			try {
				quantityInStock = sc.nextInt();
				if (quantityInStock < 0) {
					System.out.println("Invalid quantity...");
				} else {
					break; // valid input
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid quantity...");
				sc.nextLine(); // clear invalid input
			}
		}
		return true;
	}

	/**
	 * Allows sorting of Book objects by book code using the Comparable interface.
	 * 
	 * @param other the book to compare with
	 * @return a negative integer, zero, or a positive integer as this bookCode is less than, equal to, or greater than the other
	 */
	@Override
	public int compareTo(Book other) {
		return Integer.compare(this.bookCode, other.bookCode);
	}

	/**
	 * Writes the book details to the provided PrintWriter.
	 * Subclasses may override this to include additional fields.
	 * 
	 * @param pw the PrintWriter to output to
	 */
	public void outputBook(PrintWriter pw) {
		// This default version will only be called if subclasses don't override
		pw.println(getBookCode());
		pw.println(getTitle());
		pw.println(getQuantity());
		pw.println(getAuthor());
	}

	/**
	 * Returns a string representation of the book's key information.
	 * 
	 * @return formatted string with book details
	 */
	@Override
	public String toString() {
		return "Book: " + bookCode + " " + title + " " + quantityInStock + " Author: " + author + " Genre: " + genre;
	}
}
