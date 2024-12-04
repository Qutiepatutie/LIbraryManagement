package com.mycompany.java_library.users;

public class Admin{
    public static class Librarian extends UserData {
        private String librarianCode;

        public void register_Librarian(){
          try {
            register_prompt();
            System.out.print("Enter Librarian ID: ");
            librarianCode = scan.next();
            
            System.out.println("\nLibrarian Information: "+ first_name +" "+ last_name +"\n" +
                            "Library Code: " + librarianCode);
            
          } 
          catch (Exception err) {
            err.printStackTrace();
            }
        }
    }
}


