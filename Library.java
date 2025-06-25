/**
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * Student Name: Hoang Thien Loc Nguyen
 * Student Number: 041165148
 * Section #: 301
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD.
 */

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The Library class manages a catalog of books, including operations to add,
 * borrow, return, search, load from file, and save to file using an ArrayList.
 */
public class Library {
	private ArrayList<Book> catalog; // List to store books in the library

	/**
	 * Constructs a Library with an empty catalog.
	 */
	public Library() {
		catalog = new ArrayList<>(); // Initialize the catalog as an empty ArrayList
	}

	/**
	 * Adds a new book to the catalog by prompting the user for input.
	 * The user chooses the type of book, and the method validates uniqueness.
	 *
	 * @param sc Scanner for input
	 * @return true if the book was added successfully; false otherwise
	 */
	public boolean addBook(Scanner sc) {
		if (catalog.size() >= 20) {  // Limit catalog size to 20 books max
			System.out.println("Catalog is full.");
			return false;
		}

		Book newBook = null;

		// Prompt user repeatedly until a valid book type is chosen
		while (newBook == null) {
			System.out.print("Do you wish to add a Fiction (f), Non-Fiction (n), or Reference (r) book? ");
			String input = sc.next().toLowerCase();
			sc.nextLine(); // Consume leftover newline character

			// Create the corresponding subclass instance based on user input
			switch (input) {
				case "f":
					newBook = new FictionBook();
					break;
				case "n":
					newBook = new NonFictionBook();
					break;
				case "r":
					newBook = new ReferenceBook();
					break;
				default:
					System.out.println("Invalid input... Please enter a valid entry.");
			}
		}

		// Check if the new book code already exists using binary search
		if (binarySearchBookCode(newBook.getBookCode()) != -1) {
			System.out.println("Book code already exists.");
			return false;
		}

		// If addBook (which prompts for book details) succeeds, add and sort catalog
		if (newBook.addBook(sc)) {
			catalog.add(newBook);
			catalog.sort(null);  // Sort catalog by book code (assumes Book implements Comparable)
			System.out.println("Book added and catalog sorted.");
			return true;
		} else {
			System.out.println("Failed to add book.");
			return false;
		}
	}

	/**
	 * Allows the user to borrow a book by book code and quantity.
	 * Prevents borrowing reference books or more copies than are available.
	 *
	 * @param sc Scanner for input
	 * @return true if borrowing was successful; false otherwise
	 */
	public boolean borrowBook(Scanner sc) {
		if (catalog.isEmpty()) {
			System.out.println("Error... no books to borrow.");
			return false;
		}

		int code;
		// Loop until a valid integer book code is entered
		while (true) {
			try {
				System.out.print("Enter valid book code: ");
				code = sc.nextInt();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Invalid code...");
				sc.nextLine(); // Clear invalid input
			}
		}

		// Find book index using binary search
		int idx = binarySearchBookCode(code);
		if (idx == -1) {
			System.out.println("Error... book not in the catalog.");
			return false;
		}

		Book b = catalog.get(idx);

		// Reference books are not borrowable
		if (b instanceof ReferenceBook) {
			System.out.println("Reference books cannot be borrowed.");
			return false;
		}

		int qty;
		// Loop until a valid quantity is entered
		while (true) {
			System.out.print("Enter valid quantity to borrow: ");
			try {
				qty = sc.nextInt();
				if (qty < 1) {
					System.out.println("Invalid quantity...");
					return false;
				} else if (qty > b.getQuantity()) {
					System.out.println("Error... borrowing too many books.");
					return false;
				} else {
					break;  // Valid quantity entered
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid quantity...");
				return false;
			}
		}

		// Deduct borrowed quantity from available quantity
		b.updateQuantity(-qty);
		b.setQuantityBorrowed(qty); // Set how many copies have been borrowed
		return true;
	}

	/**
	 * Allows the user to return a borrowed book by entering the book code and
	 * quantity. Validates that the return quantity does not exceed the quantity
	 * borrowed.
	 *
	 * @param sc Scanner for input
	 * @return true if return was successful; false otherwise
	 */
	public boolean returnBook(Scanner sc) {
		if (catalog.isEmpty()) {
			System.out.println("Error... no books to return");
			return false;
		}

		int code;
		// Input loop for book code
		while (true) {
			try {
				System.out.print("Enter valid book code: ");
				code = sc.nextInt();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Invalid code...");
				sc.nextLine();
			}
		}

		// Find book index
		int idx = binarySearchBookCode(code);
		if (idx == -1) {
			System.out.println("Error... book not in the catalog.");
			return false;
		}

		Book b = catalog.get(idx);

		int qty;
		// Input loop for quantity to return
		while (true) {
			System.out.print("Enter valid quantity to return: ");
			try {
				qty = sc.nextInt();
				if (qty < 1) {
					System.out.println("Invalid quantity...");
					return false;
				} else if (qty > b.getQuantityBorrowed()) {
					System.out.println("Error... Trying to return more than checkout quantity");
					return false;
				} else {
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid quantity...");
				return false;
			}
		}

		// Increase available quantity and decrease borrowed count
		b.updateQuantity(qty);
		b.setQuantityBorrowed(b.getQuantityBorrowed() - qty);
		return true;
	}

	/**
	 * Searches for a book using binary search by book code.
	 *
	 * @param code the book code to search
	 * @return index of book if found; -1 otherwise
	 */
	public int binarySearchBookCode(int code) {
		int low = 0, high = catalog.size() - 1;
		// Classic binary search algorithm on sorted catalog list
		while (low <= high) {
			int mid = (low + high) / 2;
			int midCode = catalog.get(mid).getBookCode();
			if (midCode == code) {
				return mid;  // Found the book
			} else if (midCode < code) {
				low = mid + 1;  // Search right half
			} else {
				high = mid - 1; // Search left half
			}
		}
		return -1; // Book not found
	}

	/**
	 * Prompts the user for a book code and searches the catalog.
	 *
	 * @param sc Scanner for user input
	 * @return true if the book was found; false otherwise
	 */
	public boolean searchForBook(Scanner sc) {
		if (catalog.isEmpty()) {
			System.out.println("The library catalog is empty.");
			return false;
		}

		int bookCode;
		// Input validation loop for book code
		while (true) {
			try {
				System.out.print("Enter the book code to search for: ");
				bookCode = sc.nextInt();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a valid book code.");
				sc.nextLine();  // Clear invalid input
			}
		}

		int idx = binarySearchBookCode(bookCode);
		if (idx == -1) {
			System.out.println("No book found with the code " + bookCode + ".");
			return false;
		}

		// Print the book details if found
		System.out.println(catalog.get(idx));
		return true;
	}

	/**
	 * Reads book data from a file and adds valid books to the catalog.
	 * Skips duplicate codes and invalid entries.
	 *
	 * @param sc Scanner for filename input
	 */
	public void readFromFile(Scanner sc) {
		System.out.print("Enter filename to read from: ");
		String filename = sc.next();

		try (Scanner fileScanner = new Scanner(new File(filename))) {
			// Read until file ends
			while (fileScanner.hasNextLine()) {
				String type = fileScanner.nextLine().trim();

				if (!fileScanner.hasNextLine()) break;
				int code = Integer.parseInt(fileScanner.nextLine().trim());

				// Skip book if code already exists
				if (binarySearchBookCode(code) != -1) {
					System.out.println("Book code already exists: " + code);

					// Skip lines of book details based on book type to continue to next record
					if (type.equalsIgnoreCase("f")) {
						for (int i = 0; i < 3 && fileScanner.hasNextLine(); i++) fileScanner.nextLine();
					} else if (type.equalsIgnoreCase("n") || type.equalsIgnoreCase("r")) {
						for (int i = 0; i < 4 && fileScanner.hasNextLine(); i++) fileScanner.nextLine();
					}
					continue;
				}

				// Read common book fields
				String title = fileScanner.nextLine().trim();
				int qty = Integer.parseInt(fileScanner.nextLine().trim());
				String author = fileScanner.nextLine().trim();

				Book book = null;

				// Instantiate correct subclass and assign extra fields for non-fiction and reference
				switch (type.toLowerCase()) {
					case "f":
						FictionBook fBook = new FictionBook();
						fBook.setBookCode(code);
						fBook.setTitle(title);
						fBook.setQuantity(qty);
						fBook.setAuthor(author);
						book = fBook;
						break;
					case "n":
						String fieldOfStudy = fileScanner.nextLine().trim();
						NonFictionBook nfBook = new NonFictionBook();
						nfBook.setBookCode(code);
						nfBook.setTitle(title);
						nfBook.setQuantity(qty);
						nfBook.setAuthor(author);
						nfBook.setFieldOfStudy(fieldOfStudy);
						book = nfBook;
						break;
					case "r":
						String edition = fileScanner.nextLine().trim();
						ReferenceBook rBook = new ReferenceBook();
						rBook.setBookCode(code);
						rBook.setTitle(title);
						rBook.setQuantity(qty);
						rBook.setAuthor(author);
						rBook.setEdition(edition);
						book = rBook;
						break;
				}

				if (book != null) {
					catalog.add(book);
				}
			}

			catalog.sort(null); // Sort after loading all books
			System.out.println("Books loaded and catalog sorted.");
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + filename);
		} catch (NumberFormatException e) {
			System.out.println("Error parsing number from file.");
		}
	}

	/**
	 * Saves all books from the catalog to a specified file.
	 *
	 * @param sc Scanner for filename input
	 */
	public void saveToFile(Scanner sc) {
		System.out.print("Enter filename to save to: ");
		String filename = sc.next();

		try (PrintWriter pw = new PrintWriter(filename)) {
			for (Book book : catalog) {
				// Write book type indicator
				if (book instanceof FictionBook) {
					pw.println("f");
				} else if (book instanceof NonFictionBook) {
					pw.println("n");
				} else if (book instanceof ReferenceBook) {
					pw.println("r");
				}

				// Write common book info
				pw.println(book.getBookCode());
				pw.println(book.getTitle());
				pw.println(book.getQuantity());
				pw.println(book.getAuthor());

				// Write subclass-specific info
				if (book instanceof NonFictionBook) {
					pw.println(((NonFictionBook) book).getFieldOfStudy());
				} else if (book instanceof ReferenceBook) {
					pw.println(((ReferenceBook) book).getEdition());
				}
			}
			System.out.println("Library saved to file.");
		} catch (FileNotFoundException e) {
			System.out.println("Cannot save to file: " + filename);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Library Catalog:\n");

		for (int i = 0; i < catalog.size(); i++) {
			sb.append(catalog.get(i).toString());
			if (i < catalog.size() - 1) {
				sb.append("\n");
			}
		}

		return sb.toString();
	}
}
