import controller.*;
import model.SimpleDatabase;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        SimpleDatabase.isDatabaseExist();
        view.Pages.startPage();
        Input.setInput();
        System.out.println(Input.getInput());
        if (Input.getInput().equals("1")) {
            mainConnector();
        } else if (Input.getInput().equals("2")) {
            view.Pages.signupPage();
            String[] loginSignupInput = loginSignupConnector();
            Auth.signup(loginSignupInput[0], loginSignupInput[1]);
            mainConnector();
        } else {
            System.out.println("Exit System!");
        }
    }

    public static String[] loginSignupConnector() {
        view.Pages.userNameInput();
        Input.setInput();
        String userName = Input.getInput();
        view.Pages.userPasswordInput();
        Input.setInput();
        String password = Input.getInput();

        return new String[]{
                userName, password
        };
    }

    public static void mainConnector() {
        view.Pages.loginPage();
        String[] loginSignupInput = loginSignupConnector();
        Auth.login(loginSignupInput[0], loginSignupInput[1]);
        view.Pages.mainPage(loginSignupInput[0]);
        Input.setInput();
        if (Input.getInput().equals("1")) {
            view.Pages.notePage(loginSignupInput[0]);
            Input.setInput();
            if (Input.getInput().equals("1")) {
//                view.Pages.createNotePage();
//                String[] noteInput = noteConnector();
//                new Note(noteInput[0], noteInput[1], noteInput[2], noteInput[3]);
            } else if (Input.getInput().equals("2")) {
//                view.Pages.getAllNotesPage();
//                String[][] allNotes = new Note().getAllNotes();
//                for (int i = 0; i < allNotes.length; i++) {
//                    System.out.println("Note ID: " + allNotes[i][0]);
//                    System.out.println("Note Title: " + allNotes[i][2]);
//                    System.out.println("Note Content: " + allNotes[i][3]);
//                    System.out.println("Note Create Time: " + allNotes[i][4]);
//                    System.out.println("Note Last Modify Time: " + allNotes[i][5]);
//                    System.out.println();
//                }
            } else if (Input.getInput().equals("3")) {
//                view.Pages.getOneNotePage();
//                String[] oneNote = new Note().getOneNote();
//                System.out.println("Note ID: " + oneNote[0]);
//                System.out.println("Note Title: " + oneNote[2]);
//                System.out.println("Note Content: " + oneNote[3]);
//                System.out.println("Note Create Time: " + oneNote[4]);
//                System.out.println("Note Last Modify Time: " + oneNote[5]);
//                System.out.println();
            } else if (Input.getInput().equals("4")) {
//                view.Pages.updateNotePage();
//                String[] noteInput = noteConnector();
//                new Note().updateNote
            } else if (Input.getInput().equals("5")) {

            }
        }
    }



}