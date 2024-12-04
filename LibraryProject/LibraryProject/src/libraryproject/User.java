package libraryproject;

import java.io.*;
import static libraryproject.LibraryProject.*;


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
    
    public static void viewUserInfo() throws IOException{
        FileReader fileReader = new FileReader("UserAccounts.txt");
        StringBuilder fileContent = new StringBuilder();
        int character;

        // Read the file content into a StringBuilder
        while ((character = fileReader.read()) != -1) {
            fileContent.append((char) character);
        }

        fileReader.close();

        // Split the file content into entries based on double newlines
        String[] entries = fileContent.toString().split("\n\n");
        
        for (String entry : entries) {

            String[] lines = entry.split("\n");
            for (String line : lines) {
                if(line.startsWith("Username: " + userId)){
                    if(line.startsWith("Name:")){
                        String[] infos = line.split("\\|");
                        nameFromFile = infos[0].split(":")[1].trim(); // Extract Name of User
                        idFromFile = infos[1].split(":")[1].trim(); // Extract Id Num of User

                    }if (line.startsWith("Sex")){
                        String[] infos2 = line.split("\\|");
                        sexFromFile = infos2[0].split(":")[1].trim(); // Extract Sex of User
                        ageFromFile = infos2[1].split(":")[1].trim();   //Extract Age of User
                        birthdateFromFile = infos2[2].split(":")[1].trim(); // Extract birthdate of User
                    }
                }
            }
        }
        System.out.printf("Name: %s | Id Number : %s \n", nameFromFile, idFromFile);
        System.out.printf("Sex: %s | Age : %s | Birthdate: %s\n\n", sexFromFile, ageFromFile, birthdateFromFile);
    }
    
    public static void privileges() throws IOException{
        
        System.out.println("[1]View Books");
        System.out.println("[2]Borrow Books");
        System.out.println("[3]View Info");
        System.out.print("Choose: ");
        int choice = input.nextInt();
        input.nextLine();
        
        switch(choice){
            case 1 -> {
                viewBooks();
            }
            
            case 2 ->{
                
            }
            
            case 3 -> {
                viewUserInfo();
            }
            
            default -> {
                System.out.println("Invalid input, please choose from 1-3");
            }
            
        }
    }
    
    
}
