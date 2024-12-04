package com.mycompany.java_library.users;

public class Students{

    public static class Student extends UserData{
        private String studentID;

        public void register_Student(){
            try {
                register_prompt();
                System.out.print("Enter Librarian ID: ");
                studentID = scan.next();
                
                System.out.println("\nLibrarian Information: " + first_name +" "+ last_name +"\n" +
                                "Library Code: " + studentID);
                
            } catch (Exception err) {
                err.printStackTrace();
            }
        }
    }


}
