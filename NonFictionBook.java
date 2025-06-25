/**
 * CET - CS Academic Level 3  
 * Declaration: I declare that this is my own original work and is free from Plagiarism  
 * Student Name: Hoang Thien Loc Nguyen  
 * Student Number: 041165148  
 * Section #: 301  
 * Course: CST8130 - Data Structures  
 * Professor: James Mwangi PhD.
 */

import java.util.Scanner;
import java.io.PrintWriter;

/**
 * Represents a NonFictionBook, which is a type of Book with an additional
 * field: fieldOfStudy. This class allows storing and retrieving academic
 * subject areas for non-fiction titles.
 */
public class NonFictionBook extends Book {
	/**
	 * Field of study or subject area of the book.
	 */
	private String fieldOfStudy;

	/**
	 * Default constructor that sets the genre to "Non-Fiction".
	 */
	public NonFictionBook() {
		super();
		setGenre("Non-Fiction");
	}

	/**
	 * Gets the field of study for the non-fiction book.
	 *
	 * @return the field of study
	 */
	public String getFieldOfStudy() {
		return fieldOfStudy;
	}

	/**
	 * Sets the field of study for the non-fiction book.
	 *
	 * @param fieldOfStudy the subject area to set
	 */
	public void setFieldOfStudy(String fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}

	/**
	 * Prompts the user to input information for the non-fiction book, including
	 * inherited attributes and the specific field of study.
	 *
	 * @param sc Scanner object for user input
	 * @return true if book data was successfully gathered; false otherwise
	 */
	@Override
	public boolean addBook(Scanner sc) {
		if (!super.addBook(sc)) {
			return false;
		}

		sc.nextLine(); // consume newline left over from previous input
		System.out.print("Enter the field of study: ");
		setFieldOfStudy(sc.nextLine());

		setGenre("Non-Fiction"); // ensure genre is set correctly
		return true;
	}

	/**
	 * Returns a string representation of the NonFictionBook, including the field
	 * of study.
	 *
	 * @return formatted string of book details
	 */
	@Override
	public String toString() {
		return super.toString() + " Field of Study: " + fieldOfStudy;
	}

	/**
	 * Outputs book information to a file, in the format required for re-import.
	 *
	 * @param pw PrintWriter object used to write to file
	 */
	@Override
	public void outputBook(PrintWriter pw) {
		pw.println("n");
		pw.println(getBookCode());
		pw.println(getTitle());
		pw.println(getQuantity());
		pw.println(getAuthor());
		pw.println(fieldOfStudy);
	}
}
