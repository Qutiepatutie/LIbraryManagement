package com.mycompany.java_library.users;


import com.mycompany.java_library.library_function.libraryFunctions;
import java.io.IOException;
import java.util.Scanner;

public class Admin extends libraryFunctions{
      static libraryFunctions libFunc = new libraryFunctions();
      static Scanner scan = new Scanner(System.in);


      public void admin() throws IOException, InterruptedException{
        //Select option: viewbooks, viewBorredBooks, borrowBooks, exit
        while(true){
            System.out.println("""
                            Select Option
                            1. Add Books
                            2. View Books
                            3. Exit""");
            System.out.print("Enter Selected Option: ");
            int option = scan.nextInt();
            scan.nextLine();

            switch (option) {
                case 1 ->{
                  libFunc.addBooks();
                }
                case 2 ->{
                  libFunc.viewBooks();
                }
                case 3 ->{
                  return;
                }
                default -> {
                    System.out.println("Invalid Input, Please choose from 1-3");

                }
            }
            
        }
    }
}


