package controllers;

import misc.OTPAuthenticator;
import model.User;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserController {

    private User currentUser;

    public boolean handleRegister() {
        Scanner scanner = new Scanner(System.in);

        //Prompt for input
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter email address: ");
        String email = scanner.nextLine();

        // Validate inputs
        if (!isValidUsername(username)) {
            System.out.println("Invalid username! Username should be at least 4 characters long.");
        } else if (!isValidPassword(password)) {
            System.out.println("Invalid password! Password should be at least 8 characters long.");
        } else if (!isValidEmail(email)) {
            System.out.println("Invalid email address!");
        } else {
            return registerUser(username, password, email);
        }
        return false;
    }

    // Validate username (at least 4 characters)
    private boolean isValidUsername(String username) {
        return username.length() >= 4;
    }

    // Validate password (at least 8 characters)
    private boolean isValidPassword(String password) {
        return password.length() >= 8;
    }

    // Validate email address using regex
    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    public boolean handleLogin() {
        Scanner scanner = new Scanner(System.in);

        //Prompt for input
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Validate inputs
        if (!isValidUsername(username)) {
            System.out.println("Invalid username! Username should be at least 4 characters long.");
        } else if (!isValidPassword(password)) {
            System.out.println("Invalid password! Password should be at least 8 characters long.");
        } else {
            return loginUser(username, password);
        }
        return false;
    }

    public boolean registerUser(String username, String password, String email) {
        for (User user : Database.getUsers()) {
            if (user.getUsername().equals(username)) {
                System.out.println("User already exists.\n");
                return false;
            }
        }
        if (!OTPAuthenticator.sendOTP(email))
            return false;
        User newUser = new User(username, password, email);
        Database.addUser(newUser);
        return true;
    }

    public boolean loginUser(String username, String password) {
        for (User user : Database.getUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                System.out.println("Logged in successfully.\n");
                return true;
            }
        }
        System.out.println("Failed log-in, please try again.\n");
        return false;
    }

    public void logout() {
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}