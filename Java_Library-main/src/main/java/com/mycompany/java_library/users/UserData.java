package com.mycompany.java_library.users;

//import java.util.HashMap;
//import java.util.Map;
import java.util.Scanner;
import java.io.*;
import com.mycompany.java_library.library_function.*;
import com.mycompany.java_library.screen_functions.*;

    public class UserData {
        static Scanner scan = new Scanner(System.in);
        //static HashMap<String, String> userDataBase = new HashMap<>();
        
        static libraryFunctions libraryFuncs = new libraryFunctions();
        static Functions screen = new Functions();

        protected String first_name = "",
                        last_name = "",
                        user_ID = "",
                        user_name = "", 
                        password = "";

        protected void Data(String user_name, String password){
            this.user_name = user_name;
            this.password = password;
        }
        
                        
        public void register_prompt() throws IOException, InterruptedException{

            System.out.print("Enter Given Name: ");
            first_name = scan.nextLine();

            System.out.print("Enter Last Name: ");
            last_name = scan.nextLine();

            System.out.print("Enter Username: ");
            user_name = scan.nextLine();

            System.out.print("Enter Password: ");
            password = scan.nextLine();

            //userDataBase.put(user_name, password);
            Functions.clear_screen(200);

            saveDataToFile(user_name, password);
            
        }

        public void login_prompt() throws IOException{
            while(true){
                System.out.print("Enter Username: ");
                user_name = scan.nextLine();

                System.out.print("Enter Password: ");
                password = scan.nextLine();

                if (readDataFromFile(user_name, password)) {
                    if(roleValidation(user_name, password).equals("Admin")){
                        System.out.println("Login Successful");
                        System.out.println("Welcome Admin!");
                        
                    }else{
                        System.out.println("Login Successful");
                        System.out.println("Welcome Student!");
                    }
                    
                    break; // Exit the loop on successful login
                } else {
                    System.out.println("Wrong Username or Password");
                }            
            }
        }

        private void saveDataToFile(String user_name, String password){
            try (FileWriter fw = new FileWriter("userdata.txt", true);
                    PrintWriter pw = new PrintWriter(fw)) {
                        pw.println(user_name + ":" + password);
                        pw.println("Role: Student");
                    } catch (IOException e) {
                e.printStackTrace();
            }       
        }

        private boolean readDataFromFile(String username, String password) throws IOException {
            try (BufferedReader br = new BufferedReader(new FileReader("userdata.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (parts[0].equals(username) && parts[1].equals(password)) {
                        return true;
                    }
                }
                return false; // Login failed
            }
        }
        
        private String roleValidation(String username, String password) throws IOException {
            try (BufferedReader br = new BufferedReader(new FileReader("userdata.txt"))) {
                String line;
                String fileRole;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(":");
                    
                    if (parts[0].equals(username) && parts[1].equals(password)) {
                        String roleLine = br.readLine();
                        if (roleLine.startsWith("Role:")) {
                            return roleLine.split(":")[1].trim(); // Extract role of User
                        }
                    }
                }
            }
            return null;
        }
    }


