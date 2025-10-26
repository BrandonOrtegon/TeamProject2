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

### Validation Logic Chapter 9

Validation Logic from chapter 9 means to have an InvalidTitleException that is able to throw an exception if a user enters an empty book title. As well as having InvalidIsbnException that makes sure to have proper ISBN that is used for formatting and length checks. This type of validation ensures that the dataset maintains its structural integrity before being stored, which is similar to a data validation in AI preprocessing.

### Example Inputs Screenshots:

## Valid input/output
<img width="336" height="381" alt="Screen Shot 2025-10-26 at 6 12 53 PM" src="https://github.com/user-attachments/assets/f5ed20ce-bf06-47b5-a8ca-948957d932b6" />

## Invalid input/output
<img width="473" height="219" alt="Screen Shot 2025-10-26 at 6 20 54 PM" src="https://github.com/user-attachments/assets/776e4257-b895-4372-aad0-bdceba1f1d51" />



### Class Hierarchy and Inheritance Summary Chapter 10

Class Hierachy and Inheritance Summary from chapter 10 means the current implementation of a single class that is Bookstore, the design does support extension into a class hierarchy that includes: Bookstore as the main class that is in charge of managing the collection logic, file operations and searches.

As well as having Book the superclass contain attributes that are shared amogst eachother that includes: author, title and price. Plus, for Ebooks and printed books that are the subclasses, they extend the Book class in their purpose to adding specific attributes such as file size and shipping weight.


### Exception Handling Chapter 11
Exception Handling from chapter 11 means that having exceptions implemented in the code helps greatly improve error clarity and maintain a smoother flow of the program. In the bookstore program, we used try-catch to handle any unexpected events that can include: Invalid user input, conversion errors, unreadable files and missing files.

Code Example:
<img width="640" height="421" alt="Screen Shot 2025-10-26 at 6 38 15 PM" src="https://github.com/user-attachments/assets/c98b78b1-dcc4-4b0c-8f52-5bcd2c0215cb" />



Output:

### File I/O Implementation
File I/O Implementation means file handling that gives the users the ability to save and reload book data between sessions using plain text files. 

The methods used to save all current book titles to a text file is by using saveBooksToFile(ArrayList<String> bookList) that saves to a text file named books.txt.

## Example of I/O
<img width="335" height="365" alt="Screen Shot 2025-10-26 at 6 21 33 PM" src="https://github.com/user-attachments/assets/2a0c6930-7f4e-4e9b-81f8-dd9618780a52" />



### Recursive method Chapter 15
Recursive method chapter 15 means to 

Sorting and searching algorithms from chapter 16 are used to locate a book in the ArrayList. Big-O Complexity will be that the Best Case: O(1) means that the item is found and the Worth Case: O(n) means to scan the entire list


### Sorting and Searching Algorithms Chapter 16
Sorting and searching algorithms from chapter 16 are used to locate a book in the ArrayList. Big-O Complexity will be that the Best Case: O(1) means that the item is found and the Worth Case: O(n) means to scan the entire list.

## Sorting and Searching Implementation (Part 5 – Bahdan Mikhailau)

### Selection Sort
- Algorithm that repeatedly finds the smallest element and places it in sorted order.  
- **Big-O Analysis:**  
  - Best case: O(n²)  
  - Average case: O(n²)  
  - Worst case: O(n²)  
- Implemented on the array of `Book` objects, showing basic comparison-based sorting using `String.compareToIgnoreCase()`.

### Merge Sort
- Efficient sorting algorithm that recursively splits and merges arrays.  
- **Big-O Analysis:**  
  - Best case: O(n log n)  
  - Average case: O(n log n)  
  - Worst case: O(n log n)  
- Preferred for larger datasets because of its logarithmic growth behavior.

### Binary Search
- Efficient searching algorithm that works only on sorted arrays.  
- Reduces the search space by half each iteration.  
- **Big-O Analysis:**  
  - Best case: O(1)  
  - Average case: O(log n)  
  - Worst case: O(log n)  
- Demonstrates how algorithmic efficiency scales for large datasets.

### AI Insight
Sorting and searching algorithms are essential for efficiently organizing and finding data.  
These algorithms are similar to how AI systems process large datasets.  
For instance, **Merge Sort** resembles AI breaking complex problems into smaller parts,  
and **Binary Search** mirrors the decision-making steps in machine learning models.  
This demonstrates how algorithmic design principles contribute to scalable AI efficiency.

## Screenshots
## Displays Adding and Auto-Genre Prediction in Console
<img width="668" height="566" alt="Screen Shot 2025-10-26 at 6 08 40 PM" src="https://github.com/user-attachments/assets/3f9ea7ec-5960-4d3c-b642-600ac382ad6a" />


## Displays Recursive output and Sorting and Searching
<img width="341" height="300" alt="Screen Shot 2025-10-26 at 6 11 35 PM" src="https://github.com/user-attachments/assets/2630617f-90f2-4efb-ba6f-42e370202765" />


## Displays displaying all books
<img width="336" height="381" alt="Screen Shot 2025-10-26 at 6 12 53 PM" src="https://github.com/user-attachments/assets/bff95a65-9a0c-49b2-9ef4-355d93892a67" />




## Sample Code from books.txt
## First Screenshot demostrates Save
<img width="340" height="372" alt="Screen Shot 2025-10-26 at 6 01 33 PM" src="https://github.com/user-attachments/assets/51e49447-dac0-4856-a4f7-6766430dcc71" />

## Second Screenshot demonstrates Load
<img width="343" height="338" alt="Screen Shot 2025-10-26 at 6 02 00 PM" src="https://github.com/user-attachments/assets/bd71225b-dfcb-49ce-9629-a7d05a060e4e" />





## Authors
Jayla Craddock, Jeffrey Ortegon, Sahima Durrani, Ryan Kostka, Bahdan Mikhailau 

## Built with
*     Java
