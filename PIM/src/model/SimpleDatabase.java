package model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;


public class SimpleDatabase {
    private String mode = "";
    private String fileName = "";
    private String[][] data = new String[0][0];

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
     *
     *
     * @param mode: the mode of the database
     * @param fileName: the file name of the data file
     */
    public SimpleDatabase(String mode, String fileName, int userID, int classID) throws IOException {
        this.mode = mode;
        this.fileName = fileName;

        String fileBasePath = "./src/data/";

        if (mode.equals("remove")) {
            File file = new File(fileBasePath + fileName);
            remove(file, userID, classID);
        }
    }


    /**
     * Check the database is existed or not Function
     */
    private void isDatabaseExist() {
        String dirPath = "./src/data/";
        String[] dataFilePaths = {dirPath + "user.csv", dirPath + "contacts.csv", dirPath + "notes.csv", dirPath + "tasks.csv", dirPath + "events.csv", dirPath + "notifications.csv"};

        for (String filePath : dataFilePaths) {
            if (!Files.exists(Path.of(filePath))) {
                createDatabase();
                return; // Exit the method after creating the database
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
//            File notificationsCSVFile = new File(fileBasePath + "notifications.csv");

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
//                    {"taskID", "userID", "taskTitle", "taskDescription", "taskDDL", "isTaskComplete"}
                    {"taskID", "userID", "taskTitle", "taskDescription", "taskDDL"}
            };
            insert(tasksWriter, tasksData);


            FileWriter eventsWriter = new FileWriter(eventsCSVFile);
            String[][] eventsData = {
//                    {"eventID", "userID", "eventTitle", "eventDescription", "eventStartTime", "eventAlarm", "isEventSendNotify"}
                    {"eventID", "userID", "eventTitle", "eventDescription", "eventStartTime", "eventAlarm"}
            };
            insert(eventsWriter, eventsData);


//            FileWriter notificationsWriter = new FileWriter(notificationsCSVFile);
//            String[][] notificationsData = {
//                    {"notificationID", "userID", "notification", "notificationTime", "isNotificationRead"}
//            };
//            insert(notificationsWriter, notificationsData);

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
        String fileBasePath = "./src/data/";

        try (BufferedReader bufferedReader1 = new BufferedReader(new FileReader(fileBasePath + fileName))) {
            int rowNum = 0;
            int newID = 0;
            String line;
            int ID = 0;

            while (bufferedReader1.readLine() != null) {
                rowNum++;
            }

            if (rowNum == 1) {
                newID = 1;
            } else {
                bufferedReader1.close();

                try (BufferedReader bufferedReader2 = new BufferedReader(new FileReader(fileBasePath + fileName))) {
                    int i = 0;
                    while ((line = bufferedReader2.readLine()) != null) {
                        if (i == rowNum - 1)
                            ID = Integer.parseInt(line.split(",")[0]);
                        i++;
                    }
                }
                newID = ID + 1;
            }

            return newID;

        } catch (IOException e) {
            // Handle any IO exceptions and re-throw them.
            throw e;
        }
    }


    /**
     * Get Data Function
     * @param fileName: The file name of the data file
     * @return: the data
     * @throws IOException: IOException throw the I/O error
     */
    public static String[][] get(String fileName) throws IOException {
        String fileBasePath = "./src/data/";
        File file = new File(fileBasePath + fileName);

        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            int rowNum = 0;
            int colNum = 0;
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                colNum = Math.max(colNum, values.length);
                rowNum++;
            }

            String[][] data = new String[rowNum][colNum];
            int i = 0;

            // Reset the BufferedReader to read from the beginning of the file again.
            bufferedReader.close();
            fileReader.close();

            try (FileReader newFileReader = new FileReader(file);
                 BufferedReader newBufferedReader = new BufferedReader(newFileReader)) {

                newBufferedReader.readLine();
                while ((line = newBufferedReader.readLine()) != null) {
                    String[] values = line.split(",");
                    data[i] = values;
                    i++;
                }
            }

            return data;
        } catch (IOException e) {
            // Handle any IO exceptions and re-throw them.
            throw e;
        }
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
                fileWriter.append(line.toString());
            }
            fileWriter.close();

        } catch (Exception e) {
            System.err.println(e);
        }
    }


    /**
     * Update Data Function
     * @param file: The file that want to update
     * @param userID: The user ID
     */
    private void update(File file, int userID, String[] newData) throws IOException {
        if (!file.exists() || !file.isFile() || !file.canWrite()) {
            System.out.println("Cannot modify the file.");
            return;
        }
        String[][] data = get(file.getName());
        String id = Integer.toString(userID);

        // Find the row with the matching ID
        int rowIndex = -1;
        for (int i = 0; i < data.length; i++) {
            if (data[i][1].equals(id)) {
                rowIndex = i;
                break;
            }
        }

        // If a matching row is found, update the data
        if (rowIndex != -1) {
            for (int j = 0; j < newData.length; j++) {
                data[rowIndex][j] = newData[j];
            }
        }

        // Write the updated data back to the file
        try (FileWriter fileWriter = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            for (String[] row : data) {
                bufferedWriter.write(String.join(",", row));
                bufferedWriter.newLine();
            }
        } catch (IOException ex) {
            System.out.println("Error reading or writing the file.");
        }
    }





        /**
             * Remove Data Function
             * @param file: The file that want to remove
             * @param userID: The user ID
             * @param classID: The class ID
             * @throws IOException: IOException throw the I/O error
         */
    private void remove(File file, int userID, int classID) throws IOException {
        try {
            List<String> lines = Files.readAllLines(file.toPath());

            for (int i = 1; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(",");

                try {
                    int storedUserID = Integer.parseInt(parts[1].trim());
                    int storedClassID = Integer.parseInt(parts[0].trim());

                    if (storedUserID == userID && storedClassID == classID) {
                        lines.remove(i);
                        i--; // Decrement the loop counter after removing a line
                        Files.write(file.toPath(), lines); // Load from memory to file
                        System.out.println("Data removed successfully!");
                        return;
                    }
                } catch (NumberFormatException e) {
                    // Handle parsing error
                    System.err.println("Invalid data format in line " + (i + 1) + ": " + lines.get(i));
                }
            }
            System.out.println("No matching data found for removal.");
        } catch (Exception e) {
            System.err.println(e);
        }
    }


}

