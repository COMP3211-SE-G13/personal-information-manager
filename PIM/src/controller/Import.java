package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Import {
    public static File importPIMFile(String fileName) {
        try {
            File file = new File("./src/input/" + fileName);
            return file;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
            return null;
        }
    }

    private static void load(File file) throws FileNotFoundException {
        ArrayList<String[]> userData = new ArrayList<>();
        ArrayList<String[]> contactsArray = new ArrayList<>();
        ArrayList<String[]> notesArray = new ArrayList<>();
        ArrayList<String[]> tasksArray = new ArrayList<>();
        ArrayList<String[]> eventsArray = new ArrayList<>();

        Scanner loadFile = new Scanner(file);
        while (loadFile.hasNextLine()) {
            String data = loadFile.nextLine();
            String[] lineDataArray = data.split(",");
            userData.add(lineDataArray);
        }
        loadFile.close();

        boolean contactsSection = false;
        boolean notesSection = false;
        boolean tasksSection = false;
        boolean eventsSection = false;

        for (String[] tempArray : userData) {
            if (tempArray[0].equals("Contacts")) {
                contactsSection = true;
                continue;
            }
            if (contactsSection && !tempArray[0].equals("End of Contacts")) {
                contactsArray.add(tempArray);
            }
            if (tempArray[0].equals("End of Contacts")) {
                contactsSection = false;
            }

            if (tempArray[0].equals("Notes")) {
                notesSection = true;
                continue;
            }
            if (notesSection && !tempArray[0].equals("End of Notes")) {
                notesArray.add(tempArray);
            }
            if (tempArray[0].equals("End of Notes")) {
                notesSection = false;
            }

            if (tempArray[0].equals("Tasks")) {
                tasksSection = true;
                continue;
            }
            if (tasksSection && !tempArray[0].equals("End of Tasks")) {
                tasksArray.add(tempArray);
            }
            if (tempArray[0].equals("End of Tasks")) {
                tasksSection = false;
            }

            if (tempArray[0].equals("Events")) {
                eventsSection = true;
                continue;
            }
            if (eventsSection && !tempArray[0].equals("End of Events")) {
                eventsArray.add(tempArray);
            }
            if (tempArray[0].equals("End of Events")) {
                eventsSection = false;
            }
        }

        for (String[] tempArray : contactsArray) {
            if (tempArray == null) {
                continue;
            }
            Contact contactData = new Contact(tempArray[2], tempArray[3], tempArray[4], tempArray[5]);
            Contact.createContact(contactData);
        }

        for (String[] tempArray : notesArray) {
            if (tempArray == null) {
                continue;
            }
            Note noteData = new Note(tempArray[2], tempArray[3], tempArray[4]);
            Note.createNote(noteData);
        }

        for (String[] tempArray : tasksArray) {
            if (tempArray == null) {
                continue;
            }
            Todo todoData = new Todo(tempArray[2], tempArray[3], tempArray[4]);
            Todo.createTask(todoData);
        }

        for (String[] tempArray : eventsArray) {
            if (tempArray == null) {
                continue;
            }
            Event eventData = new Event(tempArray[2], tempArray[3], tempArray[4], tempArray[5]);
            Event.createEvent(eventData);
        }
    }
}
