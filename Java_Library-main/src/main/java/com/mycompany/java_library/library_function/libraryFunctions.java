package com.mycompany.java_library.library_function;
import java.util.*;
import java.io.*;


public class libraryFunctions {
    
    static Scanner scan = new Scanner(System.in);
    private static File file;

    String book_title = "",
           book_author = "", 
           book_isbn = "";
        
    public void Book_Data(String book_title,
                          String book_author,
                          String book_isbn  
                        ){
            this.book_title = book_title;
            this.book_author = book_author;
            this.book_isbn = book_isbn;
    }

    public void fileMaker(String name){
        // Create the file
        file = new File(name + ".txt");

        try {
            if (file.createNewFile()){
                System.out.println("File created: "+file.getName()+" has been successfully created!");
            } 
            else {
                System.out.println("File already exists. Writing to the existing file...");
            }
        } catch (IOException | InputMismatchException err) {
            err.printStackTrace();
        }   
    }

    public void fileWriter_Books(String book_title, String book_author, String book_isbn) throws IOException {

        FileWriter fileWrite = new FileWriter(file, true); 
        // Use fileWriter to write to the file
        fileWrite.append(this.book_author+" | "+this.book_title+" | "+this.book_isbn+" | \n");
        fileWrite.close();
    }

    public void writer()throws IOException{
        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.close();
    }


    public void addBooks() throws Exception{ 
        fileMaker("Books");
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
            file = new File("Books.txt");
            Scanner fileReader = new Scanner(file);
            System.out.println("Books:");
            
            while(fileReader.hasNextLine()){
                String data = fileReader.nextLine();
                System.out.println(data);
            }
            fileReader.close();
        
        }catch (FileNotFoundException e){
            System.out.println("File Not Found");
            e.printStackTrace();
        }
    }
    



}


