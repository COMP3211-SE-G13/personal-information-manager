package controller;

import model.SimpleDatabase;

import java.io.FileWriter;
import java.io.File;

public class Export {
    /**
     * Export Function
     */
    public static void export() {
        String contacts = "contacts.csv";
        String notes = "notes.csv";
        String events = "events.csv";
        String tasks = "tasks.csv";

        String userName = Auth.getUserName();

        try {
            String[][] contactsData = SimpleDatabase.get(contacts);
            String[][] notesData = SimpleDatabase.get(notes);
            String[][] eventsData = SimpleDatabase.get(events);
            String[][] tasksData = SimpleDatabase.get(tasks);

            String exportBaseUrl = "./src/output/";
            File file = new File(exportBaseUrl + userName + "_export.pim");

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter exportFile = new FileWriter(file);
            exportFile.write("Contacts\n");
            for (int i = 0; i < contactsData.length - 1; i++) {
                for (int j = 0; j < contactsData[i].length; j++) {
                    if (j == 0)
                        exportFile.write(contactsData[i][j]);
                    else if (j == contactsData[i].length - 1)
                        exportFile.write("," + contactsData[i][j]);
                    else
                        exportFile.write("," + contactsData[i][j]);
                }
            }
            exportFile.write("\nEnd of Contacts");
            exportFile.write("\n");
            exportFile.write("Notes\n");
            for (int i = 0; i < notesData.length - 1; i++) {
                for (int j = 0; j < notesData[i].length; j++) {
                    if (j == 0)
                        exportFile.write(notesData[i][j]);
                    else if (j == notesData[i].length - 1)
                        exportFile.write("," + notesData[i][j]);
                    else
                        exportFile.write("," + notesData[i][j]);
                }
            }
            exportFile.write("\nEnd of Notes");
            exportFile.write("\n");
            exportFile.write("Events\n");
            for (int i = 0; i < eventsData.length - 1; i++) {
                for (int j = 0; j < eventsData[i].length; j++) {
                    if (j == 0)
                        exportFile.write(eventsData[i][j]);
                    else if (j == eventsData[i].length - 1)
                        exportFile.write("," + eventsData[i][j]);
                    else
                        exportFile.write("," + eventsData[i][j]);
                }

            }
            exportFile.write("\nEnd of Events");
            exportFile.write("\n");
            exportFile.write("Tasks\n");
            for (int i = 0; i < tasksData.length - 1; i++) {
                for (int j = 0; j < tasksData[i].length; j++) {
                    if (j == 0)
                        exportFile.write(tasksData[i][j]);
                    else if (j == tasksData[i].length - 1)
                        exportFile.write("," + tasksData[i][j]);
                    else
                        exportFile.write("," + tasksData[i][j]);
                }
            }
            exportFile.write("\nEnd of Tasks");
            exportFile.write("\n");
            exportFile.close();


        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
        }
    }
}
