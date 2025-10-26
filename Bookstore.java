import java.util.Scanner;
import java.util.ArrayList; 


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
        addBook();
        displayBooks();
        searchGenres();
        buyBook();
    }
}






