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

    public static void NoteConnector(String[] loginSignupInput) {
        view.Pages.notePage(loginSignupInput[0]);
        Input.setInput();
        if (Input.getInput().equals("1")) {
            view.Pages.noteTitle();
            Input.setInput();
            String noteTitle = Input.getInput();
            view.Pages.noteContent();
            Input.setInput();
            String noteContent = Input.getInput();
            view.Pages.newNotePage();
            Input.setInput();
            if (Input.getInput().equals("1")) {
                Note noteInfo = new Note(noteTitle, noteContent);
                Note.createNote(noteInfo);
            } else if (Input.getInput().equals("2")) {
                view.Pages.notePage(loginSignupInput[0]);
            }

        } else if (Input.getInput().equals("2")) {
            view.Pages.readNotePage();
        } else if (Input.getInput().equals("3")) {
            String[][] searchResult= Search.search("Note", "notes");
            System.out.println("Search Result:");
            System.out.println("----------------------------------------------------------------");
            for (String[] x : searchResult) {
                for (int i = 2; i < x.length; i++) {
                    System.out.print(x[i] + " ");
                }
                System.out.println();
            }
            System.out.println();
        } else if (Input.getInput().equals("4")) {

        } else if (Input.getInput().equals("5")) {

        }
    }

    public static void ContactConnector(String[] loginSignupInput) {
        view.Pages.contactPage(loginSignupInput[0]);
        Input.setInput();
        if (Input.getInput().equals("1")) {
            view.Pages.contactFirstName();
            Input.setInput();
            String contactFirstName = Input.getInput();
            view.Pages.contactLastName();
            Input.setInput();
            String contactLastName = Input.getInput();
            String contactName = Input.getInput();
            view.Pages.contactPhoneNumber();
            Input.setInput();
            String contactPhoneNumber = Input.getInput();
            view.Pages.contactAddress();
            Input.setInput();
            String contactAddress = Input.getInput();
            view.Pages.newContactPage();
            Input.setInput();
            if (Input.getInput().equals("1")) {
                Contact contactInfo = new Contact(contactFirstName, contactLastName, contactPhoneNumber, contactAddress);
                Contact.createContact(contactInfo);
            } else if (Input.getInput().equals("2")) {
                view.Pages.contactPage(loginSignupInput[0]);
            }
        } else if (Input.getInput().equals("2")) {

        } else if (Input.getInput().equals("3")) {

        } else if (Input.getInput().equals("4")) {

        } else if (Input.getInput().equals("5")) {

        }
    }

    public static void TodoConnector(String[] loginSignupInput) {
        view.Pages.todoPage(loginSignupInput[0]);
        Input.setInput();
        if (Input.getInput().equals("1")) {
            view.Pages.todoName();
            Input.setInput();
            String todoName = Input.getInput();
            view.Pages.todoDDL();
            Input.setInput();
            String todoDDL = Input.getInput();
            view.Pages.todoDescription();
            Input.setInput();
            String todoDescription = Input.getInput();
            view.Pages.newTodoPage();
            Input.setInput();
            if (Input.getInput().equals("1")) {
                Todo taskInfo = new Todo(todoName, todoDDL, todoDescription);
                Todo.createTask(taskInfo);
            } else if (Input.getInput().equals("2")) {
                view.Pages.todoPage(loginSignupInput[0]);
            }
        } else if (Input.getInput().equals("2")) {

        } else if (Input.getInput().equals("3")) {

        } else if (Input.getInput().equals("4")) {

        } else if (Input.getInput().equals("5")) {

        }
    }


    public static void EventConnector(String[] loginSignupInput) {
        view.Pages.eventPage(loginSignupInput[0]);
        Input.setInput();
        if (Input.getInput().equals("1")) {
            view.Pages.eventName();
            Input.setInput();
            String eventName = Input.getInput();
            view.Pages.eventStartTime();
            Input.setInput();
            String eventStartTime = Input.getInput();
            view.Pages.eventAlarm();
            Input.setInput();
            String eventAlarm = Input.getInput();
            view.Pages.eventDescription();
            Input.setInput();
            String eventDescription = Input.getInput();
            view.Pages.newEventPage();
            Input.setInput();
            if (Input.getInput().equals("1")) {
                Event eventInfo = new Event(eventName, eventStartTime, eventAlarm, eventDescription);
                Event.createEvent(eventInfo);
            } else if (Input.getInput().equals("2")) {
                view.Pages.eventPage(loginSignupInput[0]);
            }
        } else if (Input.getInput().equals("2")) {

        } else if (Input.getInput().equals("3")) {

        } else if (Input.getInput().equals("4")) {

        } else if (Input.getInput().equals("5")) {

        }
    }


    public static void mainConnector() {
        view.Pages.loginPage();
        String[] loginSignupInput = loginSignupConnector();
        Auth.login(loginSignupInput[0], loginSignupInput[1]);
        view.Pages.mainPage(loginSignupInput[0]);
        Input.setInput();
        String menuChoice = Input.getInput();

        while(true) {

            // Notes
            if (menuChoice.equals("1")) {
                NoteConnector(loginSignupInput);
            }

            // Contacts
            else if (menuChoice.equals("2")) {
                ContactConnector(loginSignupInput);
            }

            // To-do Lists
            else if (menuChoice.equals("3")) {
                TodoConnector(loginSignupInput);
            }

            // Events
            else if (menuChoice.equals("4")) {
                EventConnector(loginSignupInput);
            }

            // Search
            else if (menuChoice.equals("5")) {

            }

            // Load .PIM File
            else if (menuChoice.equals("6")) {

            }

            // Export .PIM File
            else if (menuChoice.equals("7")) {
                try {
                    Export.export();
                    System.out.println("Export Successfully!");
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                    e.printStackTrace();
                }
            }

            // Exit System
            else if (menuChoice.equals("-1")) {
                System.out.println("Exit System!");
                break;
            }
        }
    }



}