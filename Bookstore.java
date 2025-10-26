import java.util.Scanner;
import java.util.ArrayList; 
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


// method call
//String cleanTitle = TextValidator.normalizeText(userInputTitle);
//boolean validISBN = TextValidator.validateISBN(userInputISBN);


//Part 2. Inheritance and Part 6 Recursive – Expanding the Book Model
//Name: Jeffrey Ortegon
// The Superclass that shows the book information
class Book {
    protected String title;
    protected String author;
    protected String genre;
    protected int numOfPages;
    
    public Book(String title, String author, String genre, int numOfPages){
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.numOfPages = numOfPages;
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

    public double getNumOfPages() {
        return numOfPages;
    }
    
    public void displayInfo(){
        System.out.println("The book title is: " + title + ", it's written by: " + author + ". The genre is: " + genre);
        System.out.println("The book has: " + numOfPages + " pages! Better get to reading to feed that brain!");
    }
}

// subclass that orders books and shows how much it is
class OrderBook extends Book {
    protected double donation;
    protected double shipping = 2.99;
    protected double total;
    
    public OrderBook(String title, String author, String genre, int numOfPages, double donation){
        super(title, author, genre, numOfPages);
        this.donation = donation;
        this.total = donation + shipping;
    }
    
    @Override
    public void displayInfo(){
        super.displayInfo();
        System.out.println("The price is: $" + String.format("%.2f", donation) + " with shipping costing: $" + String.format("%.2f", shipping) + ". That's a total of: $" + String.format("%.2f", total));
        
    }
    
}

// subclass that shows EBook information on the size and information
class EBook extends Book {
    private double fileSize;
    private String format;
    
    public EBook(String title, String author, String genre, int numOfPages, double fileSize, String format){
        super(title, author, genre, numOfPages);
        this.fileSize = fileSize;
        this.format = format;
    }
    
    @Override
    public void displayInfo(){
        super.displayInfo();
        System.out.println("The file size for this eBook is: " + fileSize + " MB. The format is: " + format + ".");
    }
}

public class Bookstore {
    static Book[] books = new Book[5];
    static int bookCount = 0;

    public static void addBook() {
        Scanner input = new Scanner(System.in);
        for(int i = 0; i < books.length; i++) {
            System.out.print("Enter the book title: ");
            String title = input.nextLine();

            System.out.print("Enter the authors name: ");
            String author = input.nextLine();

            String suggested = suggestGenre(title);
            System.out.println("Suggested Genre: " + suggested);
            
            System.out.print("Enter the book genre (Press enter to use suggested genre): ");
            String genreInput = input.nextLine();
            String genre = genreInput.isEmpty() ? suggested : genreInput;

            System.out.print("Enter the number of pages: ");
            int pages = Integer.parseInt(input.nextLine());

            books[i] = new Book(title, author, genre, pages);
            System.out.println("Book: " + title + " has been added to the store, the genre is: " + genre);
        }
    }
    
    // buy book method
    public static void buyBook(){
        Scanner input = new Scanner(System.in);
        
        // to show the available books to buy
        System.out.println("Here are the available books:");
        for(int j = 0; j < books.length; j++){
            if(books[j] != null){
                System.out.println((j+1) + ": " + books[j].title + " by " + books[j].author);
            }
        }
        
        // selecting the book 
        System.out.print("Enter the number of the book you want to order: ");
        int bookNum = Integer.parseInt(input.nextLine())-1;
        
        if(bookNum < 0 || bookNum >= books.length || books[bookNum] == null){
            System.out.println("Please enter a valid selection");
            return;
        }
        
        Book selected = books[bookNum];
        
        
        System.out.println("What type of book would you like to order?");
        System.out.println("1 = Physical copy, 2 = Electronic copy?");
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(input.nextLine());
        
        if(choice == 1){
            System.out.println("You're ordering a random physical book");
            System.out.print("How much would you like to donate?: ");
            double donate = Double.parseDouble(input.nextLine());
            OrderBook purchase = new OrderBook(selected.title, selected.author, selected.genre, selected.numOfPages, donate);
            purchase.displayInfo();
        }
        else if(choice == 2){
            System.out.println("You're ordering a random electronic book");
            System.out.print("How much would you like to donate?: ");
            double donate = Double.parseDouble(input.nextLine());
            System.out.print("Enter your desired format (PDF or TXT): ");
            String format = input.nextLine();
            System.out.print("Enter your desired file size in MB: ");
            double fileSize = Double.parseDouble(input.nextLine());
            EBook purchase = new EBook(selected.title, selected.author, selected.genre, selected.numOfPages, fileSize, format);
            System.out.println("Thank your for your donation of $" + donate + "!");
        }
        else{
            System.out.println("Please enter a valid number");
        }
    }

    public static void displayBooks() {
        System.out.println("Books in the store:");
        for (int i = 0; i < books.length; i++) {
            if (books[i] != null) {
                books[i].displayInfo();
            }
        }
    }

    // Jayla Craddock Part 3
// Save all books to a text file with duplicates removed
public static void saveBooksToFile(ArrayList<String> bookList) {
    try (PrintWriter writer = new PrintWriter(new File("books.txt"))) {

        ArrayList<String> totalBooks = new ArrayList<>();
        
        
        // Add array books (if they exist)
        for (int i = 0; i < books.length; i++) {
            if (books[i] != null && !books[i].trim().isEmpty()) {
                totalBooks.add(books[i] + " (" + genres[i] + ")");
            }
        }

        // Add books from ArrayList
        for (String book : bookList) {
            totalBooks.add(book);
        }

        // Manually remove duplicates
        ArrayList<String> uniqueBooks = new ArrayList<>();
        for (String book : totalBooks) {
            boolean duplicateFound = false;
            for (String unique : uniqueBooks) {
                if (unique.equalsIgnoreCase(book)) {
                    duplicateFound = true;
                    break;
                }
            }
            if (!duplicateFound) {
                uniqueBooks.add(book);
            }
        }

        // Write unique titles to file
        for (String book : uniqueBooks) {
            writer.println(book);
        }

        System.out.println(" Books successfully saved to books.txt");
    } catch (IOException e) {
        System.out.println("Error saving books: " + e.getMessage());
    } finally {
        System.out.println("Save operation complete.");
    }
}


// Jayla Craddock Part 3
// Load all books from text file
public static ArrayList<String> loadBooksFromFile() {
    ArrayList<String> loadedBooks = new ArrayList<>();
    try (Scanner fileScanner = new Scanner(new File("books.txt"))) {
        int arrayIndex = 0;
        
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            loadedBooks.add(line);
            
              // Also refill the arrays (first 5 entries only)
            if (arrayIndex < books.length) {
                books[arrayIndex] = line;
                genres[arrayIndex] = "Unknown"; // Default if not listed
                arrayIndex++;
            }
        }
        System.out.println("Books successfully loaded from books.txt");
    } catch (FileNotFoundException e) {
        System.out.println("Sorry, books.txt not found. Please save books first.");
    } finally {
        System.out.println("Load operation complete.");
    }
    return loadedBooks;
}
// End of Part 3
    
    // recursive method to count genres that match
    public static int matchingGenres(String keyword, int count){
        if(count >= books.length){
            return 0;
        }
        if(books[count] != null && books[count].genre.toLowerCase().contains(keyword.toLowerCase())){
            return 1 + matchingGenres(keyword, count + 1);
        }
        else{
            return matchingGenres(keyword, count +1 );
        }
    }
    
    // wrapper for the results
    public static void searchGenres(){
        Scanner input = new Scanner(System.in);
        System.out.print("What genre would you like to search for?: ");
        String keyword = input.nextLine();
        
        int keywordCountMatch = matchingGenres(keyword, 0);
        System.out.println("We found: " + keywordCountMatch + " title(s) that have " + keyword + " as the genre!");
    }
    
    // Ryan Kostka Part 4
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

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        addBook();
        displayBooks();
        searchGenres();
        buyBook();
        
        System.out.println("Do you want to sort the books using Merge Sort? (yes or no): ");
        if (keyboard.nextLine().equalsIgnoreCase("yes"))
        {
            mergeSort(books);
            System.out.println("The books are now sorted!");
        }
        
        System.out.println("Do you want to sort the books using Selection Sort? (yes or no): ");
        if (keyboard.nextLine().equalsIgnoreCase("yes"))
        {
            selectionSort(books);
            System.out.println("The books are now sorted!");
        }
        
        System.out.println("Do you want to find specific book? (yes or no): ");
        if (keyboard.nextLine().equalsIgnoreCase("yes"))
        {
            System.out.println("What is the title of the book you want to find?: ");
            String bookToFind = keyboard.nextLine();
            binarySearch(books, bookToFind);
        }
        

                

        //Jayla Craddock Part 3
        saveBooksToFile(dynamicBooks);

        System.out.println("Would you like to load all your books from file? (yes or no): ");
        if (keyboard.nextLine().equalsIgnoreCase("yes")) {
            ArrayList<String> loadedBooks = loadBooksFromFile();
            System.out.println("Here are your loaded Books:");
            for (String b : loadedBooks) {
                System.out.println(b);
            }
        } //End of Part 3
    }
    
    // Part 5: Selection Sort, Merge Sort, Binary Seach, to sort and find the books by title.
    //Name: Bahdan Mikhailau
    
    /*
    Selection Sort Algorithm
    
    Case insensetively sorts an array of Book objects by title (A->Z).
    
    Algorithm steps:
    1. Iterate through the array, selecting the smallest title.
    2. Swap it with the first element of the usorted part of the array.
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
    
    Case insensetively sorts an array of Book objects by title (A->Z).
    
    Algorithm steps:
    1. Dividing the array into two halves recursively until each subarray has
    one element.
    2.  Merge the subarrays back together in sorted order by comparing the titles.
    
    Big-O Analysis:
    -Best case: O(n log(n))
    -Average case: O(n log(n))
    -Worst case: O(n log(n))
    
    Efficient for large datasets.
    */
    private static void mergeSort(Book[] books)
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
    
 
    //Helper method to merge two sorted subarrays into a single sorted array. 
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
    
    Case insensetively searches for the title of the book in a sorted array of 
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
}
    
  










