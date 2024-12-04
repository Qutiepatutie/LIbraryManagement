package libraryproject;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin {
    
    static Scanner input = new Scanner(System.in);
    private static File file;
    public static String book_title, book_author, book_isbn;
    
    public static void welcome() throws IOException {
        
        System.out.println("[1]Add Books");
        System.out.println("[2]View Books");
        System.out.print("Choose: ");
        int choice = input.nextInt();
        input.nextLine();
        
        switch(choice){
            case 1 -> {
                addBooks();
            }
            
            case 2 ->{
                viewBooks();
            }
            
        }
    }
    
    public static void fileMaker(){
        // Create the file
        file = new File("Books.txt");

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
    
    public static void fileWriter(String book_title, String book_author, String book_isbn) throws IOException {

        // Use fileWriter to write to the file
        try (FileWriter fileWrite = new FileWriter(file, true)) {
            // Use fileWriter to write to the file
            fileWrite.append(book_author + " | " + book_title + " | " + book_isbn+" | \n");
        }

    }
    
    public static void addBooks() throws IOException{ 
        fileMaker();
        System.out.print("Enter Book Title: ");
        book_title = input.nextLine();

        System.out.print("Enter Book Author: ");
        book_author = input.nextLine();

        System.out.print("Enter ISBN: ");
        book_isbn = input.nextLine();

        fileWriter(book_title, book_author, book_isbn);
    }
    
    public static void viewBooks(){
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
