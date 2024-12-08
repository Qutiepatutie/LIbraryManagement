package com.mycompany.java_library.library_function;
import java.util.*;
import java.io.*;

import com.mycompany.java_library.screen_functions.Functions;
import com.mycompany.java_library.users.*;

    public class libraryFunctions extends UserData{
    
    static Scanner scan = new Scanner(System.in);
    public static File book_file, borrowedBooksFile;

    protected String student_name = "",
                     student_ID = "",
                     book_title = "",
                     book_author = "", 
                     book_isbn = "";
    
    ///////////////
    ///  BOOKS  ///
    ///////////////
    
    public void writeHeader_Books() throws IOException {
        try (FileWriter fileWriter = new FileWriter(book_file, true)) {
            fileWriter.write(String.format("%-35s | %-35s | %-15s%n", 
                            "Book Author", "Book Title", "Book Number"));
        }
    }
    public void fileWriter_Books(String book_title, String book_author, String book_isbn) throws IOException {
        if (book_file.length() == 0) {
            writeHeader_Books(); // Make sure to call the correct header method
        }
        
        try (FileWriter fileWrite = new FileWriter(book_file, true)) { 
            fileWrite.append(String.format("%-35s | %-35s | %-15s%n", 
                        book_author, book_title, book_isbn));
        }
    }
    
    // Library Functions
    public void addBooks() throws IOException, InterruptedException { 
        Functions.clear_screen(2000);
        
        book_file = new File("Books.txt");
        System.out.print("Enter Book Title: ");
        this.book_title = scan.nextLine();

        System.out.print("Enter Book Author: ");
        this.book_author = scan.nextLine();

        System.out.print("Enter ISBN: ");
        this.book_isbn = scan.nextLine();

        fileWriter_Books(book_title, book_author, book_isbn);
    }

    public void viewBooks(){

        try{
            Functions.clear_screen(2000);

            book_file = new File("Books.txt");
            Scanner fileReader = new Scanner(book_file);
            System.out.println("Books Stored");

            System.out.println("==============================================================================================");
            while(fileReader.hasNextLine()){
                String data = fileReader.nextLine();
                System.out.println(data);
            }
            fileReader.close();
            System.out.println("==============================================================================================");

        
        }catch (FileNotFoundException | InterruptedException e){
            System.out.println("No Books are currently stored");
            //e.printStackTrace();
        }
    }
    




    //////////////////////
    ///  BORROW BOOKS  ///
    //////////////////////

    public void writeHeader_Borrowers() throws IOException {
        try (FileWriter fileWriter = new FileWriter(borrowedBooksFile, true)) {
            fileWriter.write(String.format("%-35s | %-15s | %-35s | %-35s | %-15s%n", 
                             "Student Name", "Student ID", "Book Title", "Book Author", "Book Number"));
        }
    }
    public void fileWriter_Borrowers(String student_name, String student_ID, String book_title, String book_author, String book_isbn) throws IOException {
        // Check if the file is empty, if so, write the header
        if (borrowedBooksFile.length() == 0) {
            writeHeader_Borrowers();
        }
        try (FileWriter fileWriter = new FileWriter(borrowedBooksFile, true)) {
            fileWriter.append(String.format("%-35s | %-15s | %-35s | %-35s | %-15s%n", 
                            student_name, student_ID, book_title, book_author, book_isbn));
        }
    }

    //Library Functions
    public void borrowBooks() throws IOException, InterruptedException{
        Functions.clear_screen(2000);
        
        borrowedBooksFile = new File("Borrowed Books.txt");
        
        if(!borrowedBooksFile.exists()){
            borrowedBooksFile.createNewFile();
        }
        
        System.out.print("Enter Student Name: ");
        student_name = scan.nextLine();

        System.out.print("Enter Student ID: ");
        student_ID = scan.nextLine();

        System.out.print("Enter ISBN or Book Title: ");
        String searchBook = scan.nextLine();

        /***System.out.print("Enter Book Title: ");
        //book_title = scan.nextLine();

        // System.out.print("Enter Book Author: ");
        // book_author = scan.nextLine();

        //System.out.print("Enter ISBN: ");
        //book_isbn = scan.nextLine();}
        */

        boolean bookFound = false;
        boolean is_borrowed = false;
        try (BufferedReader br = new BufferedReader(new FileReader("Books.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");

                //extracted info from splits, creates new fields
                String  foundAuthor = parts[0].trim(),
                        foundTitle = parts[1].trim(),
                        foundISBN = parts[2].trim();

                if ((searchBook.equalsIgnoreCase(foundTitle) || searchBook.equalsIgnoreCase(foundISBN)) && (!line.startsWith("Borrowed by"))) {
                    
                    try(BufferedReader reader = new BufferedReader(new FileReader(borrowedBooksFile))){
                        String borrowedLine;

                        while((borrowedLine = reader.readLine()) != null){
                            if(borrowedLine.contains(foundISBN) || borrowedLine.contains(foundTitle)){
                                is_borrowed = true;
                                break;
                            }
                        }
                        if(is_borrowed){
                            System.out.println("This Book has already been borrowed");
                            return;
                        }
                    }
                    bookFound = true;
                    
                    Functions.clear_screen(3000);
                    System.out.println("Book found!");
                    // return info to rightful fields
                    book_author = foundAuthor;
                    book_title = foundTitle;
                    book_isbn = foundISBN;

                    fileWriter_Borrowers(student_name, student_ID, book_title, book_author, book_isbn);
                    
                }
                
            }
            if(!bookFound){
                Functions.clear_screen(3000);
                System.out.println("Book Not Found or Already Borrowed");
            }
        }

    }

    public void viewBorrowedBooks() throws InterruptedException {
        try {
            Functions.clear_screen(2000);
            borrowedBooksFile = new File("Borrowed Books.txt");
            Scanner fileReader = new Scanner(borrowedBooksFile);
            if(borrowedBooksFile.exists()){
                System.out.println("Borrowed Books:");

                while(fileReader.hasNextLine()){
                    String dataBorrowBooks = fileReader.nextLine();
                    System.out.println(dataBorrowBooks);
                }
                fileReader.close();
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("No Books are borrowed");
            //e.printStackTrace(); is only usefull for debugging, don't put it in production, take it out
        }
    }
}