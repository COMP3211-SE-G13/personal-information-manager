package controller;

import model.SimpleDatabase;
public class Auth {
    private static int userId;
    private static String userName;

    /**
     * Auth Contract
     * @param userName: the username of account
     * @param password: the password of account
     * @param mode: the login or signup mode
     */
    public Auth(String userName, String password, String mode) {
        this.userName = userName;

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
            String[][] data = SimpleDatabase.get("user.csv");
            for (int i = 0; i < data.length; i++) {
                if (data[i][1].equals(userName) && data[i][2].equals(password)) {
                    System.out.println("Login Success!");
                    System.out.println("ID: " + data[i][0]);
                    userId = Integer.parseInt(data[i][0]);
                    return;
                }
            }
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


    /**
     * Get User ID Function
     * @return userId: the user id of account
     */
    public static int getUserId() {
        return userId;
    }

    public static String getUserName() {
        return userName;
    }
}