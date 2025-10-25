# Bookstore 

## AI workflows
The book store database is a system built in Java that uses ArrayLists, arrays, exceptions, file handling, search and sorting that allows it to showcase its rule-based, validated and algorithmic data management. 

## AI Pipeline
How the system extends the previous assignment is that it incorporates AI-inspired rule-based validation and algorithmic data processing that mimics stages of an AI data pipeline as well as classification, data validation and preparation.

## AI theme
The book store database project extends from the previous assignment’s AI theme from using dynamic data storage to now using rule-based algorithmic management. That is able to validate input data cleaning in the AI workflows, search feature that is able to search genre classification to mimic data labeling for supervised learning and lastly, having complexity analysis that is able to connect for performance optimization in the purpose of training larger models.

When datasets get larger, being able to search and sort efficiently affects: memory usage, model performance and training time. Making the algorithmic efficiency more than just for academic purposes it’s important in real life AI applications as well.


## Main Features
The features that the bookstore database has includes: searching and sorting, array 

Validation Logic from chapter 9 means to have an InvalidTitleException that is able to throw an exception if a user enters an empty book title. As well as having InvalidIsbnException that makes sure to have proper ISBN that is used for formatting and length checks. This type of validation ensures that the dataset maintains its structural integrity before being stored, which is similar to a data validation in AI preprocessing.

### Example Inputs Screenshots:



### Class Hierarchy and Inheritance Summary Chapter 10

Class Hierachy and Inheritance Summary from chapter 10 means the current implementation of a single class that is Bookstore, the design does support extension into a class hierarchy that includes: Bookstore as the main class that is in charge of managing the collection logic, file operations and searches.

As well as InvalidIsbnException and InvalidTitleException extend to Exception, that represents the custom rule-based validation errors.

In the future some more inheritance will include the Book superclass extending to ebook and PrintedBook subclasses and InventoryItem superclass that is used for connecting the data handling across the store types. 

### Exception Handling Chapter 11
Exception Handling from chapter 11 means that having exceptions implemented in the code helps greatly improve error clarity and maintain a smoother flow of the program. This showcases a pattern that mimics 

Code Example:

Output:

### File I/O Implementation
File I/O Implementation means file handling that gives the users the ability to save and reload book data between sessions using plain text files. 

The methods used to save all current book titles to a text file is by using saveBooksToFile(ArrayList<String> bookList) that saves to a text file named books.txt.


### Recursive method Chapter 15
Recursive method chapter 15 means to 

Sorting and searching algorithms from chapter 16 are used to locate a book in the ArrayList. Big-O Complexity will be that the Best Case: O(1) means that the item is found and the Worth Case: O(n) means to scan the entire list


### Sorting and Searching Algorithms Chapter 16
Sorting and searching algorithms from chapter 16 are used to locate a book in the ArrayList. Big-O Complexity will be that the Best Case: O(1) means that the item is found and the Worth Case: O(n) means to scan the entire list.

## Screenshots

Sample Code from books.txt

## Authors
Jayla Craddock, Jeffrey Ortegon, Sahima Durrani, Ryan Kostka, Bahdan Mikhailau 

## Built with
*     Java
