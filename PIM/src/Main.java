import controller.*;
import model.SimpleDatabase;
import java.io.*;
import java.util.Objects;

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
        while (true) {
            view.Pages.notePage(loginSignupInput[0]);
            Input.setInput();
            String noteChoice = Input.getInput();
            if (noteChoice.equals("1")) {
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

            } else if (noteChoice.equals("2")) {
                while (true){
                    view.Pages.readPage();
                    Input.setInput();
                    String readChoice = Input.getInput();
                    if (readChoice.equals("1")) {
                        view.Pages.noteList();
                        for (int i = 0; i < Objects.requireNonNull(Note.getAllNotes()).length - 1; i++) {
                            if (Auth.getUserId() == Integer.parseInt(Note.getAllNotes()[i][1])) {
                                System.out.printf("%-15s%-30s\n", Note.getAllNotes()[i][0], Note.getAllNotes()[i][2]);
                            }
                        }
                        view.Pages.readOne();
                        Input.setInput();
                        String noteID = Input.getInput();
                        String[] note = Note.getOneNote(noteID);
                        view.Pages.noteTitle();
                        System.out.println(Objects.requireNonNull(note)[2]);
                        view.Pages.noteContent();
                        System.out.println(note[3]);
                        view.Pages.noteLastModifyTime();
                        System.out.println(note[4]);
                    } else if (readChoice.equals("2")) {
                        String[][] allNote = Note.getAllNotes();
                        for (int i = 0; i < Objects.requireNonNull(allNote).length - 1; i++) {
                            view.Pages.noteTitle();
                            System.out.println(Objects.requireNonNull(allNote)[i][2]);
                            view.Pages.noteContent();
                            System.out.println(allNote[i][3]);
                            view.Pages.noteLastModifyTime();
                            System.out.println(allNote[i][4]);
                            System.out.println();
                        }
                    } else if (readChoice.equals("-1")) {
                        break;
                    }
                }
            } else if (noteChoice.equals("3")) {
                view.Pages.searchPage();
                Input.setInput();
                String keyword = Input.getInput();
                String[][] searchResult = Search.search(keyword, "notes");
                System.out.println("Search Result:");
                System.out.println("----------------------------------------------------------------");
                for (String[] x : searchResult) {
                    for (int i = 2; i < x.length; i++) {
                        System.out.print(x[i] + " ");
                    }
                    System.out.println();
                }
                System.out.println();
            } else if (noteChoice.equals("4")) {

            } else if (noteChoice.equals("5")) {
                while(true) {
                    view.Pages.removePage();
                    Input.setInput();
                    String removeChoice = Input.getInput();
                    if (removeChoice.equals("1")) {
                        view.Pages.noteList();
                        for (int i = 0; i < Objects.requireNonNull(Note.getAllNotes()).length - 1; i++) {
                            if (Auth.getUserId() == Integer.parseInt(Note.getAllNotes()[i][1])) {
                                System.out.printf("%-15s%-30s\n", Note.getAllNotes()[i][0], Note.getAllNotes()[i][2]);
                            }
                        }
                        view.Pages.removePage();
                        Input.setInput();
                        String removeId = Input.getInput();
                        Note.removeNote(removeId);
                    } else if (removeChoice.equals("-1")) {
                        break;
                    }
                }
            } else if (noteChoice.equals("-1")) {
                break;
            } else {
                System.out.println("Please input the correct number!");
            }
        }
    }

    public static void ContactConnector(String[] loginSignupInput) {
        while(true) {
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
                while (true){
                    view.Pages.readPage();
                    Input.setInput();
                    String readChoice = Input.getInput();
                    if (readChoice.equals("1")) {
                        view.Pages.contactList();
                        for (int i = 0; i < Objects.requireNonNull(Note.getAllNotes()).length - 1; i++) {
                            if (Auth.getUserId() == Integer.parseInt(Note.getAllNotes()[i][1])) {
                                System.out.printf("%-15s%-30s\n", Note.getAllNotes()[i][0], Note.getAllNotes()[i][2]);
                            }
                        }
                        view.Pages.readOne();
                        Input.setInput();
                        String contactID = Input.getInput();
                        String[] contact = Contact.getOneContact(contactID);
                        view.Pages.noteTitle();
                        System.out.println(Objects.requireNonNull(contact)[2]);
                        view.Pages.noteContent();
                        System.out.println(contact[3]);
                        view.Pages.noteLastModifyTime();
                        System.out.println(contact[4]);
                    } else if (readChoice.equals("2")) {
                        String[][] allNote = Note.getAllNotes();
                        for (int i = 0; i < Objects.requireNonNull(allNote).length - 1; i++) {
                            view.Pages.noteTitle();
                            System.out.println(Objects.requireNonNull(allNote)[i][2]);
                            view.Pages.noteContent();
                            System.out.println(allNote[i][3]);
                            view.Pages.noteLastModifyTime();
                            System.out.println(allNote[i][4]);
                            System.out.println();
                        }
                    } else if (readChoice.equals("-1")) {
                        break;
                    }
                }
            } else if (Input.getInput().equals("3")) {
                view.Pages.searchPage();
                Input.setInput();
                String keyword = Input.getInput();
                String[][] searchResult = Search.search(keyword, "contacts");
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
                while(true) {
                    view.Pages.removePage();
                    Input.setInput();
                    String removeChoice = Input.getInput();
                    if (removeChoice.equals("1")) {
                        view.Pages.contactList();
                        for (int i = 0; i < Objects.requireNonNull(Contact.getAllContacts()).length - 1; i++) {
                            if (Auth.getUserId() == Integer.parseInt(Contact.getAllContacts()[i][1])) {
                                System.out.printf("%-15s%-30s\n", Contact.getAllContacts()[i][0], Contact.getAllContacts()[i][2]);
                            }
                        }
                        view.Pages.removePage();
                        Input.setInput();
                        String removeId = Input.getInput();
                        Contact.removeContact(removeId);
                    } else if (removeChoice.equals("-1")) {
                        break;
                    }
                }
            } else if (Input.getInput().equals("-1")) {
                break;
            } else {
                System.out.println("Please input the correct number!");
            }
        }
    }

    public static void TodoConnector(String[] loginSignupInput) {
        while(true) {
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
                view.Pages.searchPage();
                Input.setInput();
                String keyword = Input.getInput();
                String[][] searchResult = Search.search(keyword, "tasks");
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
                while(true) {
                    view.Pages.removePage();
                    Input.setInput();
                    String removeChoice = Input.getInput();
                    if (removeChoice.equals("1")) {
                        view.Pages.todoList();
                        for (int i = 0; i < Objects.requireNonNull(Todo.getAllTasks()).length - 1; i++) {
                            if (Auth.getUserId() == Integer.parseInt(Todo.getAllTasks()[i][1])) {
                                System.out.printf("%-15s%-30s\n", Todo.getAllTasks()[i][0], Todo.getAllTasks()[i][2]);
                            }
                        }
                        view.Pages.removePage();
                        Input.setInput();
                        String removeId = Input.getInput();
                        Todo.removeTask(removeId);
                    } else if (removeChoice.equals("-1")) {
                        break;
                    }
                }
            } else if (Input.getInput().equals("-1")) {
                break;
            } else {
                System.out.println("Please input the correct number!");
            }
        }
    }


    public static void EventConnector(String[] loginSignupInput) {
        while(true) {
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
                view.Pages.searchPage();
                Input.setInput();
                String keyword = Input.getInput();
                String[][] searchResult = Search.search(keyword, "events");
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
                while(true) {
                    view.Pages.removePage();
                    Input.setInput();
                    String removeChoice = Input.getInput();
                    if (removeChoice.equals("1")) {
                        view.Pages.eventList();
                        for (int i = 0; i < Objects.requireNonNull(Event.getAllEvents()).length - 1; i++) {
                            if (Auth.getUserId() == Integer.parseInt(Event.getAllEvents()[i][1])) {
                                System.out.printf("%-15s%-30s\n", Event.getAllEvents()[i][0], Event.getAllEvents()[i][2]);
                            }
                        }
                        view.Pages.removePage();
                        Input.setInput();
                        String removeId = Input.getInput();
                        Event.removeEvent(removeId);
                    } else if (removeChoice.equals("-1")) {
                        break;
                    }
                }
            } else if (Input.getInput().equals("-1")) {
                break;
            } else {
                System.out.println("Please input the correct number!");
            }
        }
    }

    public static void exportConnector() {
        while(true) {
            view.Pages.exportPage();
            Input.setInput();
            String exportChoice = Input.getInput();
            if (exportChoice.equals("1")) {
                Export.export();
                System.out.println("Export Successfully!");
                break;
            } else if (exportChoice.equals("-1")) {
                break;
            }
        }
    }

    public static void MenuConnector(String[] loginSignupInput) {
        while(true) {
            view.Pages.mainPage(loginSignupInput[0]);
            Input.setInput();
            String menuChoice = Input.getInput();

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
                    exportConnector();
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

            else {
                System.out.println("Please input the correct number!");
            }
        }
    }

    public static void mainConnector() {
        view.Pages.loginPage();
        String[] loginSignupInput = loginSignupConnector();
        Auth.login(loginSignupInput[0], loginSignupInput[1]);
        MenuConnector(loginSignupInput);
    }


}