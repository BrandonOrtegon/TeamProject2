import java.util.*;
import java.io.*;



//Jayla Craddock Part 3
class InvalidTitleException extends Exception {
	public InvalidTitleException(String message) {
		super(message);
	}
}

class InvalidIsbnException extends Exception {
	public InvalidIsbnException(String message) {
		super(message);
	}
}
// End of Part 3


//Part 2. Inheritance and Part 6 Recursive by Expanding the Book Model
//Name: Jeffrey Ortegon
// The Superclass that shows the book information
class Book {
	protected String title;
	protected String author;
	protected String isbn;
	protected String genre;
	protected double basePrice;


	public Book(String title, String author, String isbn, String genre, double basePrice) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.genre = genre;
		this.basePrice = basePrice;

	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getGenre() {
		return genre;
	}

	public String getIsbn() {
		return isbn;
	}

	public void displayInfo() {
		System.out.println("The book title is: " + title + ", it's written by: " + author + ". The genre is: " + genre);
		System.out.println("The isbn is " + isbn + " and the base price is: $" + basePrice);

	}
}


// subclass that orders books and shows how much it is
class PrintBook extends Book {
	// protected double donation;
	private double shipping = 2.99;
	private int numOfPages;
	// protected double total;

	public PrintBook(String title, String author, String isbn, String genre, double basePrice, int numOfPages, double shipping) {
		super(title, author, isbn, genre, basePrice);
		this.numOfPages = numOfPages;
		this.shipping = shipping;
	}

	@Override
	public void displayInfo() {
		super.displayInfo();
		System.out.println("The book has: " + numOfPages + " pages! Better get to reading to feed that brain!");
		System.out.println("The shipping cost is: $" + shipping);

	}

}




// subclass that shows EBook information on the size and information
class EBook extends Book {
	private double fileSize;
	private String format;

	public EBook(String title, String author, String isbn, String genre, double basePrice, double fileSize, String format) {
		super(title, author, isbn, genre, basePrice);
		this.fileSize = fileSize;
		this.format = format;
	}

	@Override
	public void displayInfo() {
		super.displayInfo();
		System.out.println("The file size for this eBook is: " + fileSize + " MB. The format is: " + format + ".");
	}
}


// Ryan Kostka Part 4
class GenrePredictor {
	public static String suggestGenre(String title) {
		// checks to make sure the string isnt some invalid format
		if(title == null || title.isEmpty()) return "Invalid title";
		String lower = title.toLowerCase();

		// different cases for genre recommendation
		if(lower.contains("dragon") || lower.contains("Sword"))
			return "Fantasy";
		else if(lower.contains("space") || lower.contains("planet") || lower.contains("alien"))
			return "Sci-fi";
		else if(lower.contains("detective") || lower.contains("murder") || lower.contains("mystery"))
			return "Mystery";
		else if(lower.contains("love"))
			return "Romance";
		else if(lower.contains("war") || lower.contains("battle"))
			return "Historical";
		else if(lower.contains("ghost") || lower.contains("killer"))
			return "Horror";
		else if(lower.contains("biography") || lower.contains("memoir") || lower.contains("life"))
			return "Non-fiction";
		else
			return "General";
	}
}



public class Bookstore {
	static Book[] books = new Book[5];
	static int bookCount = 0;

	public static void addBook() {
		Scanner input = new Scanner(System.in);

		try {
			System.out.print("Enter the book title: ");
			String title = TextValidator.normalizeText(input.nextLine());
			if (!TextValidator.validateTitleOrAuthor(title)) throw new InvalidTitleException("Invalid title");

			System.out.print("Enter the authors name: ");
			String author = TextValidator.normalizeText(input.nextLine());
			if (!TextValidator.validateTitleOrAuthor(author)) throw new InvalidIsbnException("Invalid author");

			System.out.print("Enter the ISBN: ");
			String isbn = input.nextLine();
			if (!TextValidator.validateISBN(isbn)) throw new InvalidTitleException("Invalid ");

			String suggested = GenrePredictor.suggestGenre(title);
			System.out.println("Suggested Genre: " + suggested);

			System.out.print("Enter the book genre (Press enter to use suggested genre): ");
			String genreInput = input.nextLine();
			String genre = genreInput.isEmpty() ? suggested : genreInput;

			System.out.print("Enter the base price: ");
			double basePrice = Double.parseDouble(input.nextLine());

			System.out.println("What type of book would you like to order?");
			System.out.println("1 = Physical copy, 2 = Electronic copy?");
			System.out.print("Enter your choice: ");
			int choice = Integer.parseInt(input.nextLine());

			if(choice == 1) {
				System.out.println("You're ordering a physical book");
				System.out.println("How many pages does the book have?: ");
				int pagesNum = Integer.parseInt(input.nextLine());
				System.out.println("How much does this physical book weigh?: ");
				double shipping = Double.parseDouble(input.nextLine());
				books[bookCount++] = new PrintBook(title, author, isbn, genre,
				                                   basePrice,pagesNum, shipping);
			}
			else if(choice == 2) {
				System.out.println("You're ordering an electronic book");
				System.out.print("Enter your desired format (PDF or TXT): ");
				String format = input.nextLine();
				System.out.print("Enter your desired file size in MB: ");
				double fileSize = Double.parseDouble(input.nextLine());
				books[bookCount++] = new EBook(title, author, isbn, genre, basePrice, fileSize, format);
			}

			System.out.println("Yay! Book has been added successfully.");


		} catch (InvalidTitleException | InvalidIsbnException e) {
			System.out.println("Validation Error: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

	//Display Books
	public static void displayBooks() {
		System.out.println("Books in store:");
		for (Book b : books) if (b != null) b.displayInfo();
	

}

// Jayla Craddock Part 3
public static void saveBooksToFile() {
	try (PrintWriter writer = new PrintWriter( new FileWriter("books.txt", true))) {

		for (int i = 0; i < books.length; i++) {
			if (books[i] != null) {
				writer.println("Title: " + books[i].getTitle());
                writer.println("Author: " + books[i].getAuthor());
                writer.println("ISBN: " + books[i].getIsbn());
                writer.println("Genre: " + books[i].getGenre());
                writer.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			}
		}

		System.out.println("Books saved successfully.");

	} catch (IOException e) {
		System.out.println("Error saving file: " + e.getMessage());
	} finally {
		System.out.println("Save operation complete.");
	}


}


// Jayla Craddock Part 3
// Load all books from text file
public static void loadBooksFromFile() {
	try(Scanner fileScanner = new Scanner(new File("books.txt"))) {
		while (fileScanner.hasNextLine()) {
			System.out.println(fileScanner.nextLine());
		}

		fileScanner.close();

		System.out.println("Books successfully loaded from books.txt");
	} catch (FileNotFoundException e) {
		System.out.println("Sorry, books.txt not found. Please save books first.");
	}
}
// End of Part 3


// recursive method to count genres that match
public static int matchingGenres(String keyword, int count) {
	if(count >= books.length) {
		return 0;
	}
	if(books[count] != null && books[count].genre.toLowerCase().contains(keyword.toLowerCase())) {
		return 1 + matchingGenres(keyword, count + 1);
	}
	else {
		return matchingGenres(keyword, count +1 );
	}
}


// Part 5: Selection Sort, Merge Sort, Binary Search, to sort and find the books by title.
//Name: Bahdan Mikhailau

/*
Selection Sort Algorithm

Case insensitively sorts an array of Book objects by title (A->Z).

Algorithm steps:
1. Iterate through the array, selecting the smallest title.
2. Swap it with the first element of the unsorted part of the array.
3. Repeat until the array is sorted.

Big-O Analysis:
-Best case: O(n^2)
-Average case: O(n^2)
-Worst case: O(n^2)

Inefficient for large datasets, however, Selection Sort is simple
and works well with small datasets.
*/
private static void selectionSort(Book[] books)
{
	for (int i = 0; i < books.length - 1; i++)
	{
		int minIndex = i;
		// Find the smallest title alphabetically in the remaining unsorted array
		for (int j = i + 1; j < books.length; j++)
		{
			if (books[j].getTitle().compareToIgnoreCase(books[minIndex].getTitle()) < 0)
			{
				minIndex = j;
			}
		}
		// Swap the found smallest book with the first book of the unsorted section
		Book temp = books[i];
		books[i] = books[minIndex];
		books[minIndex] = temp;
	}
}

/*
Merge Sort Algorithm

Case insensitively sorts an array of Book objects by title (A->Z).

Algorithm steps:
1. Dividing the array into two halves recursively until each sub array has
one element.
2.  Merge the sub arrays back together in sorted order by comparing the titles.

Big-O Analysis:
-Best case: O(n log(n))
-Average case: O(n log(n))
-Worst case: O(n log(n))

Efficient for large datasets.
*/
public static void mergeSort(Book[] books)
{
	int inputLength = books.length;
	// Base case: if array has 1 or fewer elements, it is already sorted
	if (inputLength < 2)
	{
		return;
	}

	int midIndex = inputLength / 2;
	Book[] leftHalf = new Book[midIndex];
	Book[] rightHalf = new Book[inputLength - midIndex];

	// Split the array into two halves
	for (int i = 0; i < midIndex; i++)
	{
		leftHalf[i] = books[i];
	}
	for (int i = midIndex; i < inputLength; i++)
	{
		rightHalf[i - midIndex] = books[i];
	}

	// Recursively sort both halves
	mergeSort(leftHalf);
	mergeSort(rightHalf);

	// Merge the sorted halves
	merge(books, leftHalf, rightHalf);
}


//Helper method to merge two sorted sub arrays into a single sorted array.
private static void merge(Book[] books, Book[] leftHalf, Book[] rightHalf)
{
	int leftSize = leftHalf.length;
	int rightSize = rightHalf.length;

	int i = 0, j = 0, k = 0;

	// Compare titles from both halves and insert the smaller one first
	while (i < leftSize && j < rightSize)
	{
		if (leftHalf[i].getTitle().compareToIgnoreCase(rightHalf[j].getTitle()) <= 0)
		{
			books[k] = leftHalf[i];
			i++;
		}
		else
		{
			books[k] = rightHalf[j];
			j++;
		}
		k++;
	}

	// Copy remaining elements from left half (if any)
	while (i < leftSize)
	{
		books[k] = leftHalf[i];
		i++;
		k++;
	}

	// Copy remaining elements from right half (if any)
	while (j < rightSize)
	{
		books[k] = rightHalf[j];
		j++;
		k++;
	}
}

/*
Binary Search Algorithm

Case insensitively searches for the title of the book in a sorted array of
Book objects.

Algorithm steps:
1. Compare the title of the book in the middle of the array with the target
title.
2. If they match -> the book is found.
3. If the target title is less than middle title -> search in the left half
of the array.
4. Otherwise -> search in the right half.
5. Repeat until found or search space is empty.

Big-O Analysis:
-Best case: O(1)
-Average case: O(log(n))
-Worst case: O(log(n))

Binary Search is much more efficient than linear search for large, sorted datasets.
*/
private static String binarySearch(Book[] books, String titleToFind)
{
	int left = 0;
	int right = books.length - 1;

	while (left <= right)
	{
		int middle = (left + right) / 2;
		String middleTitle = books[middle].getTitle();

		// If titles match, return success message
		if (titleToFind.compareToIgnoreCase(middleTitle) == 0)
		{
			return ("The book that you trying to find with the "
			        + "title " + titleToFind + " is located at " + middle + "'s" +
			        " position in the Book array.");
		}

		// If titleToFind comes before middleTitle, search left half
		if (titleToFind.compareToIgnoreCase(middleTitle) < 0)
		{
			right = middle - 1;
		}
		// Otherwise search right half
		else
		{
			left = middle + 1;
		}
	}

	// If not found
	return ("The book that you trying to find with the "
	        + "title " + titleToFind + " wasn't found in the Book array.");
}

public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	boolean programRunning = true;

	System.out.println("Welcome to the Collin College Bookstore System!"
	                  );
	while (programRunning) {
		System.out.println("Choose an option: ");
		System.out.println("1. Add a new book");
		System.out.println("2. Display all books");
		System.out.println("3. Save books to file");
		System.out.println("4. Load books from file");
		System.out.println("5. Search for genre keyword");
		System.out.println("6. Exit");
		System.out.print("Enter your choice: ");

		int choice = input.nextInt();
		input.nextLine(); // consume newline

		switch (choice) {
		case 1:
			addBook();
		case 2:
			displayBooks();
		case 3:
			saveBooksToFile();
		case 4:
			loadBooksFromFile();
		case 5: {
			System.out.print("Enter keyword to search for in genres: ");
			String keyword = input.nextLine();
			System.out.println("Found: " + matchingGenres(keyword, 0) + " matches.");
		}
		case 6: {
			System.out.println("Exiting the program. Goodbye!");
			programRunning = false;
		}

		}

	}


	System.out.println("Searching recursively for keyword 'thriller: ");
	System.out.println("Found: " + matchingGenres("thriller", 0) + " matches.");

}
}

