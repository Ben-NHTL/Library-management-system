/**
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * Student Name: Hoang Thien Loc Nguyen
 * Student Number: 041165148
 * Section #: 301
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD.
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Scanner;

/**
 * This class contains unit tests for borrowing and returning books in a Library system.
 * It uses the JUnit 5 framework to test various scenarios such as successful borrow/return,
 * exceeding borrow limits, and returning more than borrowed.
 */
public class BorrowReturnTests {

    /**
     * Tests the borrowing functionality of the Library class.
     * Adds a fiction book, borrows 2 copies, and verifies that the
     * remaining quantity is updated correctly.
     */
    @Test
    void testBorrowBook() {
        Library library = new Library();
        Scanner sc = new Scanner("f\n" + "123\n" + "The Great Gatsby\n" + "F. Scott Fitzgerald\n" + "10\n");

        // Add a fiction book
        library.addBook(sc);

        // Borrow 2 copies of the book
        sc = new Scanner("123\n2\n");
        boolean result = library.borrowBook(sc);

        assertTrue(result);
        assertEquals("Library Catalog:\n" + "Book: 123 The Great Gatsby 8 Author: F. Scott Fitzgerald Genre: Fiction",
                library.toString());
    }

    /**
     * Tests the return functionality of the Library class.
     * Adds a fiction book, borrows and then returns 2 copies,
     * verifying that the original quantity is restored.
     */
    @Test
    void testReturnBook() {
        Library library = new Library();
        Scanner sc = new Scanner("f\n" + "123\n" + "The Great Gatsby\n" + "F. Scott Fitzgerald\n" + "10\n");

        // Add a fiction book
        library.addBook(sc);

        // Borrow 2 copies of the book
        sc = new Scanner("123\n" + "2\n");
        library.borrowBook(sc);

        // Return 2 copies of the book
        sc = new Scanner("123\n" + "2\n");
        boolean result = library.returnBook(sc);

        assertTrue(result);
        assertEquals("Library Catalog:\n" + "Book: 123 The Great Gatsby 10 Author: F. Scott Fitzgerald Genre: Fiction",
                library.toString());
    }

    /**
     * Tests borrowing more copies than available in the Library.
     * Ensures that the borrow operation fails when the requested quantity
     * exceeds the stock, and that the inventory remains unchanged.
     */
    @Test
    void testBorrowInvalidQuantity() {
        Library library = new Library();
        Scanner sc = new Scanner("f\n" + "123\n" + "The Great Gatsby\n" + "F. Scott Fitzgerald\n" + "5\n");

        // Add a fiction book
        library.addBook(sc);

        // Attempt to borrow more than available
        sc = new Scanner("123\n" + "6\n");
        boolean result = library.borrowBook(sc);

        assertFalse(result);
        assertEquals("Library Catalog:\n" + "Book: 123 The Great Gatsby 5 Author: F. Scott Fitzgerald Genre: Fiction",
                library.toString());
    }

    /**
     * Tests returning more copies than were borrowed from the Library.
     * Ensures that the return operation fails and that the total stock is updated
     * correctly according to the borrow quantity.
     */
    @Test
    void testReturnExcessQuantity() {
        Library library = new Library();
        Scanner sc = new Scanner("f\n" + "123\n" + "The Great Gatsby\n" + "F. Scott Fitzgerald\n" + "5\n");

        // Add a fiction book
        library.addBook(sc);

        // Borrow 2 copies of the book
        sc = new Scanner("123\n" + "2\n");
        library.borrowBook(sc);

        // Attempt to return more than borrowed
        sc = new Scanner("123\n3\n");
        boolean result = library.returnBook(sc);

        assertFalse(result);
        assertEquals("Library Catalog:\n" + "Book: 123 The Great Gatsby 3 Author: F. Scott Fitzgerald Genre: Fiction",
                library.toString());
    }
}
