package com.mycompany.java_library.users;

import java.io.IOException;
import java.util.*;
import com.mycompany.java_library.library_function.libraryFunctions;
import com.mycompany.java_library.screen_functions.Functions;

public class Students extends libraryFunctions{
    static Scanner scan = new Scanner(System.in);

    static libraryFunctions libFunc = new libraryFunctions();


    public void student() throws IOException, InterruptedException{
        //Select option: viewbooks, viewBorredBooks, borrowBooks, exit
        while(true){
            System.out.println("""
                            Select Option
                            1. View Books
                            2. Borrow Books
                            3. View Borrowed Books
                            4. Exit""");
            System.out.print("Enter Selected Option: ");
            int option = scan.nextInt();
            scan.nextLine();

            switch (option) {
                case 1 ->{
                    libFunc.viewBooks();
                }

                case 2 ->{
                    libFunc.viewBooks();
                    
                    if(libFunc.book_file.exists()){
                        libFunc.borrowBooks();
                        
                    }else {
                        continue;
                    }
                }

                case 3 ->{
                    libFunc.viewBorrowedBooks();
                }

                case 4 ->{
                    Functions.clear_screen(3000);
                    return;
                }
                default -> {
                    System.out.println("Invalid Input, Please choose from 1-4");

                }
            }
            
        }
    }


}
