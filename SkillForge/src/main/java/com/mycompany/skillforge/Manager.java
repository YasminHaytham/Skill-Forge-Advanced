package com.mycompany.skillforge;

import java.util.Random;

import javax.swing.JOptionPane;

public class Manager {

    private static User loggedInUser = null;
    private  static JsonDatabaseManager dbManager = new JsonDatabaseManager();

    public static String signup(String username, String email, String password, String role) {

        if (!InputValidator.isNotEmpty(username)
                || !InputValidator.isNotEmpty(email)
                || !InputValidator.isValidEmail(email)
                || !InputValidator.isValidPassword(password)) {
            return "Invalid Input!";
        }

        try {
            if (dbManager.userExists(email)) {
                return "Email already exists!";
            }

            Random rand = new Random();
            String userId = String.valueOf(1000 + rand.nextInt(9000));
            String hashed = Password.hashPassword(password);

            
            if (role.equalsIgnoreCase("student")) {
                Student newUser = new Student(userId, "Student", username, email, hashed);
                try {
                    dbManager.addStudent(newUser);
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                return "Signup successful!";
            } else {
                Instructor newUser = new Instructor(userId, "Instructor", username, email, hashed);
                try {
                    dbManager.addInstructor(newUser);
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                return "Signup successful!";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Database error!";
        }
    }

    public static String login(String userInput, String password, String role) {
        String hashed = Password.hashPassword(password);
        try {
            if (role.equalsIgnoreCase("student")) {
                for (Student s : dbManager.getAllStudents()) {
                    if ((s.getEmail().equalsIgnoreCase(userInput) || s.getUsername().equalsIgnoreCase(userInput))&& s.getPasswordHash().equals(hashed)) {
                        loggedInUser = s;
                        return "Login successful";
                    }
                }
            } else if (role.equalsIgnoreCase("instructor")) {
                for (Instructor i : dbManager.getAllInstructors()) {
                    if ((i.getEmail().equalsIgnoreCase(userInput) || i.getUsername().equalsIgnoreCase(userInput))
                            && i.getPasswordHash().equals(hashed)) {
                        loggedInUser = i;
                        return "Login successful";
                    }
                }
            }

            return "Invalid email or password or role";

        } catch (Exception e) {
            e.printStackTrace();
            return "Database error!";
        }
    }
    
    public static void logout() {
        loggedInUser = null;
    }

     public static User getCurrentUser() {
        return loggedInUser;
    }

}
