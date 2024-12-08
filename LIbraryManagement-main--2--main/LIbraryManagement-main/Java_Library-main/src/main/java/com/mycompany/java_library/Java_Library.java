package com.mycompany.java_library;
//@author gelo

import java.util.*;
import com.mycompany.java_library.screen_functions.*;
import com.mycompany.java_library.users.UserData;


public class Java_Library{
    static Scanner scan = new Scanner(System.in);
    static UserData user = new UserData();

    
    public static void main(String[] args) throws Exception{
        menu();
        
               while(true){ 
                    System.out.println("""
                                       Select Action
                                       1. Log in
                                       2. Register
                                       3. Exit""");
                    System.out.print("Enter Selected Action: ");
                
                    try {
                        int role  = scan.nextInt();
        
                        switch(role){
                            case 1 -> {
                                Functions.clear_screen(3000);
                                user.login_prompt();
                            }
                            case 2 -> {
                                Functions.clear_screen(3000);
                                user.register_prompt();
                            }
                            case 3 -> {
                                return;
                            }
        
                            default -> {        
                                System.out.println("\nPlease try again!, Input is not in the option");
                                Functions.clear_screen(3000);
                            }
                        }
        
                    } catch (NumberFormatException | InputMismatchException e) {
                        System.out.println("E: Input is not in the option, Try Again.");
                        scan.nextLine();
                        Functions.clear_screen(3000);
         
                    }
               }
            }
        
        
        public static void menu() throws InterruptedException{
        Functions.clear_screen(3000);
        System.out.println("*********************************************");
        System.out.println("*                                           *");
        System.out.println("*    WELCOME TO THE LIBRARY APPLICATION!    *");
        System.out.println("*                                           *");
        System.out.println("*********************************************");
        System.out.println();
        System.out.println("              █▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█             ");
        System.out.println("              █    WELCOME!    █             ");
        System.out.println("              █▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄█             ");
        System.out.println("               ▀██████████████▀              ");
        System.out.println("                   ████████                  ");
        System.out.println("                     ████                    ");
        System.out.println("                  ▄▄▄████▄▄▄                 ");
        System.out.println();
        Thread.sleep(3000);
        System.out.println("*********************************************");
        System.out.println("*     Manage books, borrow, and explore!    *");
        System.out.println("*       Bringing the library to you.        *");
        System.out.println("*********************************************");

    }
}


 