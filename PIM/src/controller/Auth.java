package controller;

public class Auth {
    private String userName;
    private String password;

    /**
     * Auth Contract
     * @param userName: the username of account
     * @param password: the password of account
     * @param mode: the login or signup mode
     */
    public Auth(String userName, String password, String mode) {
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

    }

    /**
     * Create Account Function - Signup
     * @param userName: the username of account
     * @param password: the password of account
     */
    private void createAccount(String userName, String password) {

    }

}