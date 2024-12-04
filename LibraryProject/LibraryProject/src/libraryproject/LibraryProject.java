package libraryproject;

import java.util.*;
import java.io.*;

public class LibraryProject {

    static String userId;
    static String password;
    static String nameFromFile;
    static String idFromFile;
    static String sexFromFile;
    static String ageFromFile;
    static String birthdateFromFile;

    static Scanner input = new Scanner(System.in);
    
    //static HashMap<String, User> userDatabase = new HashMap<>();
    
    public static void main(String[] args) throws IOException {
        //userDatabase.put("Admin", new User("admin123", "Admin"));
        //userDatabase.put("Student1", new User("student123", "Student"));
        
        int choice;
        
        while(true){
            try{
                System.out.println("[1] Login");
                System.out.println("[2] Register");
                System.out.println("[3] Exit");
                System.out.print("Choose: ");
                choice = input.nextInt();
                input.nextLine();
            }catch(Exception e){
                System.out.println("Invalid input, Please choose from 1-3\n");
                input.nextLine();
                continue;
            }
            
            
            switch(choice){

                case 1 -> {
                    System.out.print("Enter Username: ");
                    userId = input.nextLine();

                    System.out.print("Enter Password: ");
                    password = input.nextLine();
                    
                    String role = validateLogin(userId,password);
                    
                    if("Admin".equals(role)){
                        System.out.println("Welcome Admin!");
                        System.out.printf("Name: %s | ID: %s\n\n", nameFromFile, idFromFile);
                        Admin.welcome();
                        return;
                    }else {
                        System.out.println("Welcome Student!");
                        System.out.printf("Name: %s | ID: %s\n\n", nameFromFile, idFromFile);
                        User.privileges();
                        return;
                    }
                    //add choice to add books or exit program
                    //add user class
                    
                    //on user log in, check for name and infos (sex/age/bday)
                    //if name is registered, display on log in
                    //user can edit personal infos
                }

                case 2 -> {
                    User.register();

                }
                
                case 3 -> {
                    System.out.println("Exiting the Program...");
                    return;
                }
                
                default ->{
                    System.out.println("Invalid input, Please choose from 1-3\n");
                }
            }
        
        }
    }
    
    public static String validateLogin(String username, String password) throws IOException {
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
            // Reset for each entry
            String fileUsername = null;
            String filePassword = null;
            String fileRole = null;

            String[] lines = entry.split("\n");
            for (String line : lines) {
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
                
                if (line.startsWith("Username:")) {
                    String[] logIns = line.split("\\|");
                    fileUsername = logIns[0].split(":")[1].trim(); // Extract username
                    filePassword = logIns[1].split(":")[1].trim(); // Extract password
                    
                }if (line.startsWith("Role:")) {
                    fileRole = line.split(":")[1].trim(); // Extract role of User
                }
                
            }

            // Validate username and password
            if (fileUsername != null && filePassword != null && fileRole != null) {
                if (username.equals(fileUsername) && password.equals(filePassword)) {
                    return fileRole;
                }
            }
        }

        return null; // No match found
    }
}
