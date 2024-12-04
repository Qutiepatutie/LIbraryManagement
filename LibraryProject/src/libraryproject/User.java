package libraryproject;

import java.io.*;
import static libraryproject.LibraryProject.input;

public class User {
    
    public static void register() throws IOException{
         while(true){
            System.out.print("Enter new Username: ");
            String newUserId = input.nextLine();

            System.out.print("Enter New Password: ");
            String newPassword = input.nextLine();

            System.out.print("Enter name: ");
            String newName = input.nextLine();

            System.out.print("Enter ID Number: ");
            int newIdNum = input.nextInt();
            input.nextLine();
            
            System.out.print("Enter Sex(M/F): ");
            char newSex = input.nextLine().charAt(0);
            
            System.out.print("Enter Age: ");
            int newAge = input.nextInt();
            input.nextLine();
            
            System.out.println("(mm/dd/yy)");
            System.out.print("Enter Birthdate: ");
            String newBirthdate = input.nextLine();
            
            try(FileWriter writer = new FileWriter("UserAccounts.txt", true)){
                writer.append("Name: " + newName + " | ID Num: " + newIdNum + "\n");
                writer.append("Sex: " + newSex + " | Age : " + newAge + " | Birthdate: " + newBirthdate + "\n");
                writer.append("Username: " + newUserId + " | Password: " + newPassword + "\n");
                writer.append("Role: Student \n\n");
            }

            System.out.println("User Registered Succesfully!");
            break;
        }
    }
    
    public static void viewBooks() throws IOException {
        FileReader fileReader = new FileReader("Books.txt");

            // Read and print the file content
            int character;
            while ((character = fileReader.read()) != -1) {
                System.out.print((char) character);
            }
    }
    
    public static void privileges() throws IOException{
        
        System.out.println("[1]View Books");
        System.out.println("[2]Borrow Books");
        System.out.print("Choose: ");
        int choice = input.nextInt();
        input.nextLine();
        
        switch(choice){
            case 1 -> {
                viewBooks();
            }
            
            case 2 ->{
                
            }
            
        }
    }
    
    
}
