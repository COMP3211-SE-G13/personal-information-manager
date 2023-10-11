package controller;

import model.SimpleDatabase;

public class Auth {
    private int userId;
    private String userName;
    private String password;

    /**
     * Auth Contract
     * @param userId: the user id of account
     * @param userName: the username of account
     * @param password: the password of account
     * @param mode: the login or signup mode
     */
    public Auth(int userId, String userName, String password, String mode) {
        this.userName = userName;
        this.password = password;
        if (mode.equals("login")) {
            verifyAccount(userName, password);
        }
        if (mode.equals("signup")) {
            createAccount(userName, password);
        }
    }

    /**
     * Verify Account Function - Login
     * @param userName: the username of account
     * @param password: the password of account
     */
    private void verifyAccount(String userName, String password) {
        try {

        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
        }
    }

    /**
     * Create Account Function - Signup
     * @param userName: the username of account
     * @param password: the password of account
     */
    private void createAccount(String userName, String password) {
        try {
            int userId = SimpleDatabase.getNewID("user.csv");

            String[][] newUserData = {
                    {String.valueOf(userId), userName, password}
            };

            new SimpleDatabase("insert", "user.csv", newUserData);

        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
        }
    }

}