package model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class SimpleDatabase {
    private String mode;
    private String fileName;
    private String[][] data;

    /**
     * SimpleDatabase Contract
     * Operations:
     * - insert
     * - update (return true || false)
     *
     * @param mode: the mode of the database
     * @param fileName: the file name of the data file
     * @param data: the data
     */
    public SimpleDatabase(String mode, String fileName, String[][] data) throws IOException {
        this.mode = mode;
        this.fileName = fileName;
        this.data = data;

        String fileBasePath = "./src/data/";

        if (mode.equals("insert")) {
            try {
                isDatabaseExist();
                File file = new File(fileBasePath + fileName);
                FileWriter fileWriter = new FileWriter(file);
                insert(fileWriter, data);
            } catch (Exception e) {
                System.err.println(e);
            }
        }

        if (mode.equals("update")) {
            try {


            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }


    /**
     * SimpleDatabase Contract
     * Operations:
     * - remove (return true || false)
     * - get (return data - [ type -> String[][] ])
     *
     * @param mode: the mode of the database
     * @param fileName: the file name of the data file
     */
    public SimpleDatabase(String mode, String fileName) throws IOException {
        this.mode = mode;
        this.fileName = fileName;

        String fileBasePath = "./src/data/";

        if (mode.equals("get")) {
            try {
                isDatabaseExist();
                File file = new File(fileBasePath + fileName);
                FileReader fileReader = new FileReader(file);
                String[][] data = get(fileReader);
            } catch (Exception e) {
                System.err.println(e);
            }
        }

        if (mode.equals("remove")) {
            try{
                File file = new File(fileBasePath + fileName);
                List<String> lines = File
                }
            }
        }
    }


    /**
     * Check the database is existed or not Function
     */
    private void isDatabaseExist() {
        String dirPath = "./src/data/";
        String[] dataFilePaths = {dirPath + "user.csv", dirPath + "contacts.csv", dirPath + "notes.csv", dirPath + "tasks.csv", dirPath + "events.csv", "notifications.csv"};

        for (int i = 0; i < dataFilePaths.length; i++) {
            if (!Files.exists(Path.of(dataFilePaths[i]))) {
                createDatabase();
            }
        }
    }

    /**
     * Create (Initial) Database Function
     */
    private void createDatabase() {
        try {
            Path path = Paths.get("./src/data");
            Files.createDirectories(path);
            System.out.println("Data Folder is created!");

            String fileBasePath = "./src/data/";

            File userCSVFile = new File(fileBasePath + "user.csv");
            File contactsCSVFile = new File(fileBasePath + "contacts.csv");
            File notesCSVFile = new File(fileBasePath + "notes.csv");
            File tasksCSVFile = new File(fileBasePath + "tasks.csv");
            File eventsCSVFile = new File(fileBasePath + "events.csv");
            File notificationsCSVFile = new File(fileBasePath + "notifications.csv");

            FileWriter userWriter = new FileWriter(userCSVFile);
            String[][] userData = {
                    {"userID", "userName", "password"}
            };
            insert(userWriter, userData);

            FileWriter contactsWriter = new FileWriter(contactsCSVFile);
            String[][] contactsData = {
                    {"contactID", "userID", "firstName", "lastName", "phoneNumber", "address"}
            };
            insert(contactsWriter, contactsData);

            FileWriter notesWriter = new FileWriter(notesCSVFile);
            String[][] notesData = {
                    {"noteID", "userID", "noteTitle", "noteContent", "createTime", "lastModifyTime"}
            };
            insert(notesWriter, notesData);

            FileWriter tasksWriter = new FileWriter(tasksCSVFile);
            String[][] tasksData = {
                    {"taskID", "userID", "taskTitle", "taskDescription", "taskDDL", "isTaskComplete"}
            };
            insert(tasksWriter, tasksData);


            FileWriter eventsWriter = new FileWriter(eventsCSVFile);
            String[][] eventsData = {
                    {"eventID", "userID", "eventTitle", "eventDescription", "eventStartTime", "eventAlarm", "isEventSendNotify"}
            };
            insert(eventsWriter, eventsData);


            FileWriter notificationsWriter = new FileWriter(notificationsCSVFile);
            String[][] notificationsData = {
                    {"notificationID", "userID", "notification", "notificationTime", "isNotificationRead"}
            };
            insert(notificationsWriter, notificationsData);

        } catch (IOException e) {
            System.err.println("Failed to create directory!" + e.getMessage());
        }
    }


    /**
     * Get The new ID function
     * @param fileName: The file name that want to get new ID
     * @return newID: Get the new ID
     * @throws IOException: IOException throw the I/O error
     */
    public static int getNewID(String fileName) throws IOException {
        int rowCount = 0;
        int newID = 0;
        String fileBasePath = "./src/data/";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileBasePath + fileName))) {
            while (reader.readLine() != null) {
                rowCount++;
            }
        }
        newID = rowCount + 1;

        return newID;
    }


    private String[][] get(FileReader fileReader) {
        try {
            BufferedReader bufferedReader1 = new BufferedReader(fileReader);
            BufferedReader bufferedReader2 = new BufferedReader(fileReader);
            int rowNum = 0;
            while ((bufferedReader1.readLine()) != null) {
                rowNum++;
            }

            String[][] data = new String[rowNum][];
            String line;
            int i = 0;
            while ((line = bufferedReader2.readLine()) != null) {
                data[i] = line.split(",");
            }
            fileReader.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        return data;
    }


    /**
     * Insert Data Function
     * @param fileWriter: The filewrite pointer of the file
     * @param csvData: the data of the user want to insert
     */
    private void insert(FileWriter fileWriter, String[][] csvData) {
        try {
            for (String[] data : csvData) {
                StringBuilder line = new StringBuilder();
                for (int i = 0; i < data.length; i++) {
                    line.append(data[i]);
                    if (i != data.length - 1) {
                        line.append(',');
                    }
                }
                line.append("\n");
                fileWriter.write(line.toString());
            }
            fileWriter.close();

        } catch (Exception e) {
            System.err.println(e);
        }
    }


    /**
     * Update Data Function
     * @param fileWriter: The filewrite pointer of the file
     * @param csvData: the data of the user want to update
     */
    private Boolean update(FileWriter fileWriter, String[][] csvData) {
        try {
            for(String[] data : csvData) {
                StringBuilder line = new StringBuilder();
                for(int i = 0; i < data.length; i++) {

                }
            }
        } catch(Exception e) {
            return false;
        }
    }


    private Boolean remove() {

        return true;
    }

}

