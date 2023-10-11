package view;

public class Pages {

    /**
     * The Start Page
     */
    public void startPage() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("        Welcome to the Personal Information Manager (PIM)!");
        System.out.println("             COMP3211 - Software Engineering");
        System.out.println("                       Project Group 13");
        System.out.println();
        System.out.println("- [1] Login");
        System.out.println("- [2] Signup");
        System.out.println("- [-1] Exit System");
        System.out.println();
        System.out.print(">>> Please select the above options x in [x]:");
    }


    /**
     * The Main Function Page
     * @param userName: The username of the account
     * @param notifications: The array of the Recently Notifications
     */
    public void mainPage(String userName, String[] notifications) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("        Welcome " + userName);
        System.out.println();
        System.out.println("----------------------");
        System.out.println("Recently Notifications:");
        for (int i = 0; i < notifications.length; i++) {
            System.out.println(notifications[i]);
        }
        System.out.println("----------------------");
        System.out.println();
        System.out.println("- [1] Notes");
        System.out.println("- [2] Contacts");
        System.out.println("- [3] To-do Lists");
        System.out.println("- [4] Events");
        System.out.println("- [5] All Notifications");
        System.out.println("- [6] Load .PIM File");
        System.out.println("- [7] Export .PIM File");
        System.out.println("- [-1] Exit System");
        System.out.println();
        System.out.print(">>> Please select the above options x in [x]:");
    }


    /**
     * The Note Page
     * @param userName: The username of the account
     */
    public void notePage(String userName) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("        Notes - " + userName);
        System.out.println();
        System.out.println("- [1] New Notes");
        System.out.println("- [2] Read Note");
        System.out.println("- [3] Modify Note");
        System.out.println("- [4] Remove Note");
        System.out.println("- [-1] Back to last page");
        System.out.println();
        System.out.print(">>> Please select the above options x in [x]:");
    }

    public void newNotePage() {
        System.out.println("----------------------------------------------------------------");
        System.out.println();
        System.out.print("- [1] Save Note");
        System.out.print("- [2] Discard Note");
        System.out.print(">>> Please select the above options x in [x]:");
    }


    /**
     * The Contact Page
     * @param userName: The username of the account
     */
    public void contactPage(String userName) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("        Contacts - " + userName);
        System.out.println();
        System.out.println("- [1] Create New Contact");
        System.out.println("- [2] All Contacts");
        System.out.println("- [2] Search Contact");
        System.out.println("- [3] Modify Contact");
        System.out.println("- [4] Remove Contact");
        System.out.println("- [-1] Back to last page");
        System.out.println();
        System.out.print(">>> Please select the above options x in [x]:");
    }


    /**
     * The To-do List Page
     * @param userName: The username of the account
     */
    public void todoPage(String userName) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("        To-do Lists - " + userName);
        System.out.println();
        System.out.println("- [1] Create New Task");
        System.out.println("- [2] All Tasks");
        System.out.println("- [2] Search Task");
        System.out.println("- [3] Modify Task");
        System.out.println("- [4] Remove Task");
        System.out.println("- [-1] Back to last page");
        System.out.println();
        System.out.print(">>> Please select the above options x in [x]:");
    }


    /**
     * The Event Page
     * @param userName: The username of the account
     */
    public void eventPage(String userName) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("        Events - " + userName);
        System.out.println();
        System.out.println("- [1] Create New Event");
        System.out.println("- [2] All Events");
        System.out.println("- [2] Search Event");
        System.out.println("- [3] Modify Event");
        System.out.println("- [4] Remove Event");
        System.out.println("- [-1] Back to last page");
        System.out.println();
        System.out.print(">>> Please select the above options x in [x]:");
    }


    /**
     * The All Notifications Page
     * @param userName: The username of the account
     */
    public void notificationsPage(String userName, String[] notifications) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("        Notifications - " + userName);
        System.out.println();
        System.out.println("----------------------");
        System.out.println("Recently Notifications:");
        for (int i = 0; i < notifications.length; i++) {
            System.out.println(notifications[i]);
        }
        System.out.println("----------------------");

        System.out.println("- [1] All Read");
        System.out.println("- [2] Select one Read");
        System.out.println("- [-1] Back to last page");
        System.out.println();
        System.out.print(">>> Please select the above options x in [x]:");
    }

}
