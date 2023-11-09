package view;

import controller.Auth;
import controller.Note;

import java.util.Objects;

public class Pages {

    /**
     * The Start Page
     */
    public static void startPage() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("        Welcome to the Personal Information Manager (PIM)!");
        System.out.println("             COMP3211 - Software Engineering");
        System.out.println("                       Project Group 13");
        System.out.println();
        System.out.println("- [1] Login");
        System.out.println("- [2] Signup");
        System.out.println("- [-1] Exit System");
        System.out.println();
        System.out.print(">>> Please select the above options x in [x]: ");
    }

    public static void loginPage() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("        Login");
        System.out.println();
    }

    public static void signupPage() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("        Signup");
        System.out.println();
    }

    public static void userNameInput() {
        System.out.print("Username: ");
    }

    public static void userPasswordInput() {
        System.out.print("Password: ");
    }


    /**
     * The Main Function Page
     * @param userName: The username of the account
     */
    public static void mainPage(String userName) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("        Welcome " + userName);
        System.out.println();
        System.out.println("----------------------");
        System.out.println();
        System.out.println("- [1] Notes");
        System.out.println("- [2] Contacts");
        System.out.println("- [3] To-do Lists");
        System.out.println("- [4] Events");
        System.out.println("- [5] Search");
        System.out.println("- [6] Load .PIM File");
        System.out.println("- [7] Export .PIM File");
        System.out.println("- [-1] Exit System");
        System.out.println();
        System.out.print(">>> Please select the above options x in [x]: ");
    }


    /**
     * The Note Page
     * @param userName: The username of the account
     */
    public static void notePage(String userName) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("        Notes - " + userName);
        System.out.println();
        System.out.println("- [1] New Notes");
        System.out.println("- [2] Read Note");
        System.out.println("- [3] Search Note");
        System.out.println("- [4] Modify Note");
        System.out.println("- [5] Remove Note");
        System.out.println("- [-1] Back to last page");
        System.out.println();
        System.out.print(">>> Please select the above options x in [x]: ");
    }

    public static void noteTitle() {
        System.out.print("Note Title: ");
    }

    public static void noteContent() {
        System.out.println("Note Content: ");
        System.out.println("----------------------------------------------------------------");
    }

    public static void noteLastModifyTime() {
        System.out.println("----------------------------------------------------------------");
        System.out.print("Note last modify time: ");
    }

    public static void newNotePage() {
        System.out.println("----------------------------------------------------------------");
        System.out.println();
        System.out.println("- [1] Save Note");
        System.out.println("- [2] Discard Note");
        System.out.print(">>> Please select the above options x in [x]: ");
    }

    public static void noteList() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Your List of Notes: ");
        System.out.println("Note ID        Note Title");
    }

    public static void readPage() {
        System.out.println("----------------------------------------------------------------");
        System.out.println();
        System.out.println("- [1] Choose which one to read");
        System.out.println("- [2] Read All");
        System.out.println("- [-1] Back to last page");
        System.out.print(">>> Please select the above options x in [x]: ");
    }

    public static void readOne() {
        System.out.print(">>> Please input the note ID you want to read: ");
    }



    /**
     * The Contact Page
     * @param userName: The username of the account
     */
    public static void contactPage(String userName) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("        Contacts - " + userName);
        System.out.println();
        System.out.println("- [1] Create New Contact");
        System.out.println("- [2] All Contacts");
        System.out.println("- [3] Search Contact");
        System.out.println("- [4] Modify Contact");
        System.out.println("- [5] Remove Contact");
        System.out.println("- [-1] Back to last page");
        System.out.println();
        System.out.print(">>> Please select the above options x in [x]: ");
    }

    public static void contactFirstName() {
        System.out.print("First Name: ");
    }

    public static void contactLastName() {
        System.out.print("Last Name: ");
    }

    public static void contactPhoneNumber() {
        System.out.print("Phone Number: ");
    }

    public static void contactAddress() {
        System.out.print("Address: ");
    }

    public static void newContactPage() {
        System.out.println("----------------------------------------------------------------");
        System.out.println();
        System.out.println("- [1] Save Contact");
        System.out.println("- [2] Discard Contact");
        System.out.print(">>> Please select the above options x in [x]: ");
    }

    public static void contactList() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Your List of Contacts: ");
        System.out.println("Contact ID     Contact Name");
    }


    /**
     * The To-do List Page
     * @param userName: The username of the account
     */
    public static void todoPage(String userName) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("        To-do Lists - " + userName);
        System.out.println();
        System.out.println("- [1] Create New Task");
        System.out.println("- [2] All Tasks");
        System.out.println("- [3] Search Task");
        System.out.println("- [4] Modify Task");
        System.out.println("- [5] Remove Task");
        System.out.println("- [-1] Back to last page");
        System.out.println();
        System.out.print(">>> Please select the above options x in [x]: ");
    }

    public static void todoName() {
        System.out.print("Task Name: ");
    }

    public static void todoDDL() {
        System.out.print("Task Deadline: ");
    }

    public static void todoDescription() {
        System.out.print("Task Description: ");
    }

    public static void newTodoPage() {
        System.out.println("----------------------------------------------------------------");
        System.out.println();
        System.out.println("- [1] Save Task");
        System.out.println("- [2] Discard Task");
        System.out.print(">>> Please select the above options x in [x]: ");
    }

    public static void todoList() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Your List of Tasks: ");
        System.out.println("Task ID        Task Title");
    }


    /**
     * The Event Page
     * @param userName: The username of the account
     */
    public static void eventPage(String userName) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("        Events - " + userName);
        System.out.println();
        System.out.println("- [1] Create New Event");
        System.out.println("- [2] All Events");
        System.out.println("- [3] Search Event");
        System.out.println("- [4] Modify Event");
        System.out.println("- [5] Remove Event");
        System.out.println("- [-1] Back to last page");
        System.out.println();
        System.out.print(">>> Please select the above options x in [x]: ");
    }

    public static void eventName() {
        System.out.print("Event Name: ");
    }

    public static void eventStartTime() {
        System.out.print("Event Start Time: ");
    }

    public static void eventAlarm() {
        System.out.print("Event Alarm: ");
    }

    public static void eventDescription() {
        System.out.println("Event Description: ");
        System.out.println("----------------------------------------------------------------");
    }

    public static void newEventPage() {
        System.out.println("----------------------------------------------------------------");
        System.out.println();
        System.out.println("- [1] Save Event");
        System.out.println("- [2] Discard Event");
        System.out.print(">>> Please select the above options x in [x]: ");
    }

    public static void eventList() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Your List of Events: ");
        System.out.println("Event ID       Event Title");
    }


    public static void searchPage() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("                           Search");
        System.out.print("Searching Keyword(s): ");
    }

    public static void removePage() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("                            Remove");
        System.out.println();
        System.out.println("- [1] Choose which one to remove");
        System.out.println("- [-1] Back to last page");
        System.out.print(">>> Please select the above options x in [x]: ");
    }

    public static void exportPage() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("                           Export");
        System.out.println();
        System.out.println("- [1] Confirm export to .PIM File");
        System.out.println("- [-1] Back to last page");
        System.out.print(">>> Please select the above options x in [x]: ");
    }


}
