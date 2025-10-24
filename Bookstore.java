//Name: Jayla Craddock, Jeffrey Ortegon, Sahima Durrani, Ryan Kostka, Bahdan Mikhailau
//Date 9/20/2025
import java.util.Scanner;
import java.util.ArrayList; 


//Part 1: Array Implementation
//Name: Jeffrey Ortegon, Sahima Durrani
public class Bookstore {
    static String[] books = new String[5];
    static String[] genres = new String[5];
    static int bookCount = 0;

    // Method to add books
    public static void addBook() {
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < books.length; i++) {
            System.out.print("Enter a book into the store library: ");
            books[i] = input.nextLine();
            System.out.print("Now enter the genre of the book: ");
            genres[i] = input.nextLine();
            System.out.println("The book " + books[i] + " with the genre " + genres[i] + " has been entered in the library.");
        }
    }

    // Method to display books
    public static void displayBook() {
        System.out.println("Here are your books you've entered into the bookstore:");
        for (int i = 0; i < books.length; i++) {
            System.out.println("Book " + (i + 1) + ": " + books[i] + " (" + genres[i] + ")");
        }
    }
    
    //Part 2: ArrayList Implementation
    //Name: Jayla Craddock
    public static void main(String[] args) {
        addBook();
        displayBook();
        bookSearch();

        //ArrayList section
        ArrayList<String> dynamicBooks = new ArrayList<>();
        Scanner keyboard = new Scanner(System.in);
        String input;

        System.out.println("Welcome to the Book Database! Enter book titles. Type 'stop' to exit.");

        // Add books 
        while (true) {
            System.out.print("Book title: ");
            input = keyboard.nextLine();
            if (input.equalsIgnoreCase("stop")) break;
            dynamicBooks.add(input);
        }

        // Remove books
        while (true) {
            System.out.print("Remove a book (or type 'stop' to exit): ");
            input = keyboard.nextLine();
            if (input.equalsIgnoreCase("stop")) break;
            dynamicBooks.remove(input);
        }

        // Display books
        System.out.println("Your list of book titles:");
        for (String book : dynamicBooks) {
            System.out.println(book);
        }

        // Sequential search in ArrayList
        while (true) {
            System.out.println("Search a title in your database (or type 'stop' to exit): ");
            String searchInput = keyboard.nextLine();
            if (searchInput.equalsIgnoreCase("stop")) break;

            int index = dynamicBooks.indexOf(searchInput);
            if (index != -1) {
                System.out.println("Book title: " + searchInput + " found at index: " + index);
            } else {
                System.out.println("Sorry, " + searchInput + " is not in the database.");
            }
        }
    }

    // Part 3: Method to search for books by either name or genre.
    //Name: Ryan Kostka
    public static void bookSearch() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Do you want to search by 'name' or 'genre'? ");
            String searchChoice = input.nextLine();

            if (searchChoice.equalsIgnoreCase("exit")) break;

            // Name searching
            if (searchChoice.equalsIgnoreCase("name")) {
                System.out.print("Enter the title of the book (or type 'exit' to quit):");
                String searchTitle = input.nextLine();

                boolean found = false;
                for (int i = 0; i < books.length; i++) {
                    if (books[i] != null && books[i].equalsIgnoreCase(searchTitle)) {
                        System.out.println("Book found: " + books[i] + " (" + genres[i] + ")");
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Book not found.");
                }
            }

            // Genre searching
            else if (searchChoice.equalsIgnoreCase("genre")) {
                System.out.print("Enter the genre of the book: ");
                String searchGenre = input.nextLine();

                boolean found = false;
                for (int i = 0; i < genres.length; i++) {
                    if (genres[i] != null && genres[i].equalsIgnoreCase(searchGenre)) {
                        System.out.println("Book found: " + books[i] + " (" + genres[i] + ")");
                        found = true;
                    }
                }
                if (!found) {
                    System.out.println("No books found for this genre.");
                }
            }
        }
    }
}



