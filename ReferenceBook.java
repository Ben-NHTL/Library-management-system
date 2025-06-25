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
 * Represents a ReferenceBook, which is a type of Book with the genre set to
 * "Reference" and includes an edition field.
 */
public class ReferenceBook extends Book {
    // Additional attribute specific to reference books
    private String edition;

    /**
     * Constructor that sets the genre of the book to "Reference".
     */
    public ReferenceBook() {
        super(); // Calls the superclass (Book) constructor
        setGenre("Reference"); // Sets the genre to "Reference"
    }

    /**
     * Gets the edition of the reference book.
     * 
     * @return edition string
     */
    public String getEdition() {
        return edition;
    }

    /**
     * Sets the edition of the reference book.
     * 
     * @param edition the edition to set
     */
    public void setEdition(String edition) {
        this.edition = edition;
    }

    /**
     * Adds a ReferenceBook by gathering base book details and the edition.
     * 
     * @param sc Scanner object for user input
     * @return true if book details were successfully entered, false otherwise
     */
    @Override
    public boolean addBook(Scanner sc) {
        if (!super.addBook(sc)) { // Calls the superclass's addBook method
            return false; // If superclass addBook fails, return false
        }

        sc.nextLine(); // Consume newline left from previous input
        System.out.print("Enter the edition of the book: ");
        setEdition(sc.nextLine()); // Read edition input

        setGenre("Reference"); // Reconfirm genre
        return true; // Return true if all input successful
    }

    /**
     * Returns a string representation of the ReferenceBook.
     * 
     * @return formatted string containing book details and edition
     */
    @Override
    public String toString() {
        return super.toString() + " Edition: " + edition; // Include edition in string
    }

    /**
     * Outputs ReferenceBook data to a file using the provided PrintWriter.
     * 
     * @param pw PrintWriter object for writing to a file
     */
    @Override
    public void outputBook(PrintWriter pw) {
        pw.println("r"); // Identifier for ReferenceBook type
        pw.println(getBookCode()); // Outputs the book code
        pw.println(getTitle()); // Outputs the title
        pw.println(getQuantity()); // Outputs the quantity
        pw.println(getAuthor()); // Outputs the author
        pw.println(edition); // Outputs the edition
    }
}
