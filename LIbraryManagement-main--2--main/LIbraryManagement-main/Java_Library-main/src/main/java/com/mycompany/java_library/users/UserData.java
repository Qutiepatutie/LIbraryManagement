package com.mycompany.java_library.users;


import java.util.Scanner;
import java.io.*;
import com.mycompany.java_library.library_function.*;
import com.mycompany.java_library.screen_functions.*;

public class UserData {
    static Scanner scan = new Scanner(System.in);
    static libraryFunctions libraryFuncs = new libraryFunctions();
    static Functions screen = new Functions();

    protected String first_name = "",
                     last_name = "",
                     user_ID = "",
                     user_name = "", 
                     password = "";

    public void register_prompt() throws IOException, InterruptedException{
        System.out.println("=====================");
        System.out.println("||     REGISTER    ||");
        System.out.println("=====================");

        System.out.print("Enter Username: ");
        user_name = scan.nextLine();

        System.out.print("Enter Password: ");
        password = scan.nextLine();

        Functions.clear_screen(200);

        saveDataToFile(user_name, password);
    }

    public void login_prompt() throws IOException, InterruptedException{
        Admin admin_class = new Admin();
        Students student_class = new Students();

        while(true){
            System.out.println("=====================");
            System.out.println("||      LOGIN      ||");
            System.out.println("=====================");

            System.out.print("Enter Username: ");
            user_name = scan.nextLine();

            System.out.print("Enter Password: ");
            password = scan.nextLine();

            if (readDataFromFile(user_name, password)) {
                if(roleValidation(user_name, password).equals("Admin")){
                    Functions.clear_screen(2000);
                    System.out.println("Login Successful");
                    
                    Functions.clear_screen(2000);
                    System.out.println("Welcome Admin!");

                    admin_class.admin(); 
                }else{
                    Functions.clear_screen(2000);
                    System.out.println("Login Successful");

                    Functions.clear_screen(2000);
                    System.out.println("Welcome Student!");

                    student_class.student();
                }
                break; // Exit the loop on successful login
            } else {
                System.out.println("\nWrong Username or Password!!");
                Functions.clear_screen(2000);
            }            
        }
    }

    private void saveDataToFile(String user_name, String password){
        try (FileWriter fw = new FileWriter("UserData_Log_File.txt", true);
                PrintWriter pw = new PrintWriter(fw)) {
                    pw.println(user_name + ":" + password);
                    pw.println("Role: Student");
                    pw.println("=====================");
                } catch (IOException e) {
            e.printStackTrace();
        }       
    }

    private boolean readDataFromFile(String username, String password) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("UserData_Log_File.txt"))) {
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
        try (BufferedReader br = new BufferedReader(new FileReader("UserData_Log_File.txt"))) {
            String line;
            //String fileRole;
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


