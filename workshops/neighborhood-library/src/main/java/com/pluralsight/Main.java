package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        // Book[}library = getPopulatedLibrary();
        // creates the variable 'library' that holds the value of Book[}library


        ShowHomeScreen();
    }

    private static Console console = new Console();

    private static Book[] library = getPopulatedBooks();


   // private static Book[] getPopulatedLibrary(){

        //Book[] library = new Book[20]; //create a new list of arrays from the Book

   //     library[0] = new Book(0, "ISBN 978-1-78862-355-1", "Nginx HTTP Server");
   //     library[1] = new Book(1, "ISBN 978-0-13-468599-1", "Effective Java");
   //     library[2] = new Book(2, "ISBN 978-1-4919-1889-0", "Fluent Python");
   //     library[3] = new Book(3, "ISBN 978-0-596-52068-7", "Learning Python");
   //     library[4] = new Book(4, "ISBN 978-0-321-35668-0", "Introduction to Algorithms");
   //     library[5] = new Book(5, "ISBN 978-1-59327-584-6", "Automate the Boring Stuff with Python");
   //     library[6] = new Book(6, "ISBN 978-1-4919-1881-4", "Python for Data Analysis");
   //     library[7] = new Book(7, "ISBN 978-0-321-93444-5", "Clean Code");
   //     library[8] = new Book(8, "ISBN 978-0-13-235088-4", "The Clean Coder");
   //     library[9] = new Book(9, "ISBN 978-0-262-03384-8", "Structure and Interpretation of Computer Programs");
   //     library[10] = new Book(10, "ISBN 978-1-4919-1883-8", "Data Science from Scratch");
   //     library[11] = new Book(11, "ISBN 978-1-59327-599-0", "Eloquent JavaScript");
   //     library[12] = new Book(12, "ISBN 978-1-4919-4545-2", "You Don’t Know JS");
   //     library[13] = new Book(13, "ISBN 978-0-13-110362-7", "The C Programming Language");
   //     library[14] = new Book(14, "ISBN 978-1-118-80048-3", "Head First Design Patterns");
   //     library[15] = new Book(15, "ISBN 978-1-4919-6026-4", "Designing Data-Intensive Applications");
   //     library[16] = new Book(16, "ISBN 978-1-118-30924-3", "JavaScript: The Good Parts");library[17] = new Book(17, "ISBN 978-0-596-00482-8", "Programming Perl");
   //     library[18] = new Book(18, "ISBN 978-1-4919-1889-0", "High Performance Browser Networking");
   //     library[19] = new Book(19, "ISBN 978-1-4919-1888-3", "Hands-On Machine Learning with Scikit-Learn and TensorFlow");
   //    library[20] = new Book(20, "ISBN 978-1-4919-4721-0", "Grokking Algorithms");

   //       return library;
   //   }

    private static void ShowHomeScreen() {

        String homeScreenPrompt = "Welcome to the library!\n" +
                "Please select an option from the following:\n" +
                "    1 - Show Available Books\n" +
                "    2 - Show Checked Out Books\n" +
                "    0 - Exit library\n" +
                "(1,2,0): ";

        int option;
        do {

            option = console.promptForInt(homeScreenPrompt);
            switch(option){
                case 1: ShowAvailableBooksScreen();
                    break;
                case 2: ShowCheckedOutBooksScreen();
                    break;
                case 0: System.out.println("Exiting the library! Have a nice day!");
                    break;
                default:
                    System.out.println("Not a valid entry. Please try again!");
            }
        } while (option != 0);
    }


    private static void displayAvailableBooks(){
        for(Book book : library){
            if (book != null && !book.isCheckedOut()){
                System.out.println(book);
            }
        }
    }

    private static void ShowAvailableBooksScreen() {

        displayAvailableBooks();

        int choice;
        do {
            //prompt the user to check out a book or exit to the home screen
            String availableBookPrompt = "[1] Would you like to check out a book? \n"
                    + "[2] or exit to home screen?";

            choice = console.promptForInt(availableBookPrompt);
            switch (choice) {
                case 1:
                    System.out.println("Here is a list of available books: \n");
                    displayAvailableBooks();

                    //prompt the user to enter book IDb to select
                    int bookID = console.promptForInt("Please enter the book ID to select it: ");

                    Book selectedBook = getBooksById(library, bookID);

                    //prompt the user for their name to checkout
                    String name;
                    name = console.promptForString("Please enter your name for check out: ");

                    System.out.printf("Thank you %s, you have checked out: %s\n", name, selectedBook);

                    //The selected book will now be unavailable in the list of books

                    selectedBook.checkOut(name);

                case 2: ShowHomeScreen();
                    break;
                default:
                    System.out.println("Invalid entry. Please enter a numerical value shown.");
            }
        } while (choice != 2);
        System.out.println("Sorry, invalid input taken! please try again");
    }

    private static void ShowCheckedOutBooksScreen(){
        System.out.println("Books currently checked out: \n");
        int bookCheckedOut = 0; //adding loop

        for(Book book : library){

            if(book.isCheckedOut()){
                System.out.println(book + " is checked out by" + book.getCheckedOutTo());
                bookCheckedOut ++;
            }
        }

        if(bookCheckedOut > 0){

            String checkOutPrompt = "[C] Check in a book" +
                    " [X] Go back to home screen";

            String choice = console.promptForString(checkOutPrompt).trim().toUpperCase();


            switch(choice){
                case "C":

                    int bookID = console.promptForInt("Please enter the ID number of the book you'd like to return");

                    if(bookID > 0 && bookID <= library.length){
                        Book checkedOutBook = library[bookID - 1];
                        if(checkedOutBook.isCheckedOut()){
                            System.out.printf("Thank you %s, %d %s has been checked in \n\n"
                                    , checkedOutBook.getCheckedOutTo()
                                    , checkedOutBook.getId()
                                    , checkedOutBook.getTitle());
                            checkedOutBook.checkIn();
                        }
                    }
                case "X":
                    return;

                default:
                    System.out.println("Invalid entry, Please try again!");
            }
        } else{System.out.println("There are currently no books checked out");}
    }

    private static Book getBooksById(Book[] library, int id){
        for(Book book : library){
            if(book.getId() == id)
                return book;
        }
        return null;
    }
    private static Book[] getPopulatedBooks(){

        try{
            FileReader fr = new FileReader("books.txt");
            BufferedReader reader = new BufferedReader(fr);
            Book[] booksTemp = new Book[1000];

            int size = 0;
            String dataString;

            while((dataString = reader.readLine()) != null){

                booksTemp[size] = getBookFromEncodedString(dataString);

                size++;

            }
            Book[] booksFinal = Arrays.copyOf(booksTemp, size);

            return booksFinal;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Book getBookFromEncodedString(String encodedBook){

        String[] temp = encodedBook.split(Pattern.quote("|"));

        int id = Integer.parseInt(temp[0]);
        String isbn = temp[1];
        String title = temp[2];

        Book result = new Book(id, isbn, title);
        return result;
    }
}
























