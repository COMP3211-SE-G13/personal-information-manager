package model;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * SimpleDatabase Class
 * API:
 *  - insert
 *      - new SimpleDatabase("insert", FILE_NAME, DATA_STRING_2D_ARRAY[][]);
 *  - update
 *      - new SimpleDatabase("update", FILE_NAME, USER_ID, DATA_STRING_ARRAY[]);
 *  - remove
 *      - new SimpleDatabase("remove", FILE_NAME, USER_ID, CLASS_ID);
 *  - getNewID(String fileName)
 *  - get(String fileName)
 */
public class SimpleDatabase {
    String[] contactsData = {"contactID", "userID", "firstName", "lastName", "phoneNumber", "address"};
    String[] notesData = {"noteID", "userID", "noteTitle", "noteContent", "lastModifyTime"};
    String[] tasksData = {"taskID", "userID", "taskTitle", "taskDescription", "taskDDL"};
    String[] eventsData = {"eventID", "userID", "eventTitle", "eventDescription", "eventStartTime", "eventAlarm"};

    /**
     * SimpleDatabase Contract
     * Operations:
     * - insert
     *
     * @param mode: the mode of the database
     * @param fileName: the file name of the data file
     * @param data: the data
     */
    public SimpleDatabase(String mode, String fileName, String[][] data) throws IOException {
        String fileBasePath = "./src/data/";

        //String[] eventsData = {"eventID", "userID", "eventTitle", "eventDescription", "eventStartTime", "eventAlarm"};

        if (mode.equals("insert")) {
            isDatabaseExist();
            File file = new File(fileBasePath + fileName);
            if (fileName.contains("user")) {
                FileWriter fileWriter = new FileWriter(file, true);
                insert(fileWriter, data);
            } else {
                String[][] tempFile = get(fileName);
                FileWriter fileWriter = new FileWriter(file);
                insertSorting(fileWriter, data, tempFile, fileName);
            }
        }
    }

    /**
     * SimpleDatabase Contract
     * Operations:
     * - update
     *
     * @param mode: the mode of the database
     * @param fileName: the file name of the data file
     * @param classID: the ID of those classes
     * @param data: The updated data
     */
    public SimpleDatabase(String mode, String fileName, int classID, String[] data) throws IOException {
        String fileBasePath = "./src/data/";

//        String[] contactsData = {"contactID", "userID", "firstName", "lastName", "phoneNumber", "address"};
//        String[] notesData = {"noteID", "userID", "noteTitle", "noteContent", "lastModifyTime"};
//        String[] tasksData = {"taskID", "userID", "taskTitle", "taskDescription", "taskDDL"};
//        String[] eventsData = {"eventID", "userID", "eventTitle", "eventDescription", "eventStartTime", "eventAlarm"};

        if (mode.equals("update")) {
            File file = new File(fileBasePath + fileName);
            updateSorting(file, classID, data, fileName);
        }
    }

    /**
     * SimpleDatabase Contract
     * Operations:
     * - remove
     *
     * @param mode: the mode of the database
     * @param fileName: the file name of the data file
     * @exception IOException: The exception for the File Operation
     */
    public SimpleDatabase(String mode, String fileName, int userID, int classID) throws IOException {
        String fileBasePath = "./src/data/";

        if (mode.equals("remove")) {
            File file = new File(fileBasePath + fileName);
            remove(file, userID, classID);
        }
    }


    /**
     * Check the database is existed or not Function
     */
    public static void isDatabaseExist() {
        String dirPath = "./src/data/";
        String[] dataFilePaths = {dirPath + "user.csv", dirPath + "contacts.csv", dirPath + "notes.csv", dirPath + "tasks.csv", dirPath + "events.csv"};

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
    private static void createDatabase() {
        try {
            Path path = Paths.get("./src/data");
            Files.createDirectories(path);

            String fileBasePath = "./src/data/";

            File userCSVFile = new File(fileBasePath + "user.csv");
            File contactsCSVFile = new File(fileBasePath + "contacts.csv");
            File notesCSVFile = new File(fileBasePath + "notes.csv");
            File tasksCSVFile = new File(fileBasePath + "tasks.csv");
            File eventsCSVFile = new File(fileBasePath + "events.csv");

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
                    {"noteID", "userID", "noteTitle", "noteContent", "lastModifyTime"}
            };
            insert(notesWriter, notesData);

            FileWriter tasksWriter = new FileWriter(tasksCSVFile);
            String[][] tasksData = {
                    {"taskID", "userID", "taskTitle", "taskDescription", "taskDDL"}
            };
            insert(tasksWriter, tasksData);

            FileWriter eventsWriter = new FileWriter(eventsCSVFile);
            String[][] eventsData = {
                    {"eventID", "userID", "eventTitle", "eventDescription", "eventStartTime", "eventAlarm"}
            };
            insert(eventsWriter, eventsData);

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
            int largestID = 0;

            // Find the number of rows of the file
            while (bufferedReader1.readLine() != null) {
                rowNum++;
            }

            // Initiate class ID
            if (rowNum == 1) {
                newID = 1;
            } else {
                bufferedReader1.close();

                try (BufferedReader bufferedReader2 = new BufferedReader(new FileReader(fileBasePath + fileName))) {
                    int i = 0;
                    while ((line = bufferedReader2.readLine()) != null) {
                        // Find the largest class ID of the file
                        if (i != 0) {
                            if (i == 1)
                                largestID = Integer.parseInt(line.split(",")[0]);
                            if (Integer.parseInt(line.split(",")[0]) > largestID)
                                largestID = Integer.parseInt(line.split(",")[0]);
                        }
                        i++;
                    }
                }
                // Construct the new class ID by adding one to the last class ID
                newID = largestID + 1;
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
     * @return data: the data
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

            // Find the size of String[][]
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

            // Load the csv data into a String[][]
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
     * @param csvData: The data of the user want to insert
     * @exception Exception: The Exception for File operation
     */
    private static void insert(FileWriter fileWriter, String[][] csvData) {
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
     * Insert Event Data Function in sequence of user ID and event start time
     * @param fileWriter: The filewrite pointer of the file
     * @param newData: The data of the user want to insert
     * @param tempFile: The data get from the file by using the get function
     * @exception Exception: The Exception for File operation
     */
    private void insertSorting(FileWriter fileWriter, String[][] newData, String[][] tempFile, String fileName) {
        try{
            // If the csv file is null, we write the new data into the csv file directly.
            if (tempFile.length <= 1) {
                String[][] tempData = new String[1+newData.length][];
                if (fileName.contains("contacts")) {
                    tempData[0] = contactsData;
                } else if (fileName.contains("notes")) {
                    tempData[0] = notesData;
                } else if (fileName.contains("tasks")) {
                    tempData[0] = tasksData;
                } else if (fileName.contains("events")) {
                    tempData[0] = eventsData;
                }

                System.arraycopy(newData, 0, tempData, 1, newData.length);
                for (String[] data : tempData) {
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
            } else {
                int newIndex = 0;
                // Find the index of the new data (which position in the csv file it should be inserted)
                if (fileName.contains("contacts")) {
                    for (int i = 0; i < tempFile.length - 1; i++) {
                        if (Integer.parseInt(newData[0][1]) < Integer.parseInt(tempFile[i][1])) {
                            break;
                        } else {
                            newIndex++;
                        }
                    }
                } else {
                    for (int i = 0; i < tempFile.length - 1; i++) {
                        if (Integer.parseInt(newData[0][1]) < Integer.parseInt(tempFile[i][1])) {
                            break;
                        } else if (Integer.parseInt(newData[0][1]) == Integer.parseInt(tempFile[i][1])) {
                            if (fileName.contains("notes")) {
                                if (newData[0][4].compareTo(tempFile[i][4]) > 0) {
                                    break;
                                } else {
                                    newIndex++;
                                }
                            } else {
                                if (newData[0][4].compareTo(tempFile[i][4]) < 0) {
                                    break;
                                } else {
                                    newIndex++;
                                }
                            }
                        } else {
                            newIndex++;
                        }
                    }
                }

                // Insert the new data into the read 2D string array
                String[][] temp1 = new String[1+newIndex][];
                if (fileName.contains("contacts")) {
                    temp1[0] = contactsData;
                } else if (fileName.contains("notes")) {
                    temp1[0] = notesData;
                } else if (fileName.contains("tasks")) {
                    temp1[0] = tasksData;
                } else if (fileName.contains("events")) {
                    temp1[0] = eventsData;
                }
                if (newIndex > 0) {
                    System.arraycopy(tempFile, 0, temp1, 1, newIndex);
                }
                String[][] temp2 = new String[2+newIndex][];
                System.arraycopy(temp1, 0, temp2, 0, newIndex + 1);
                System.arraycopy(newData, 0, temp2, newIndex + 1, 1);
                String[][] csvData = new String[2+tempFile.length][];
                System.arraycopy(temp2, 0, csvData, 0, newIndex + 2);
                if (newIndex < (tempFile.length - 1)) {
                    System.arraycopy(tempFile, newIndex, csvData, newIndex + 2, tempFile.length - newIndex - 1);
                }

                // Write the integrated 2D string array into the csv file
                for (int i = 0; i < csvData.length - 1; i++) {
                    StringBuilder line = new StringBuilder();
                    for (int j = 0; j < csvData[i].length; j++) {
                        line.append(csvData[i][j]);
                        if (j != csvData[i].length - 1) {
                            line.append(',');
                        }
                    }
                    line.append("\n");
                    fileWriter.append(line.toString());
                }
                fileWriter.close();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

//    /**
//     * Update Data Function
//     * @param file: The file that want to update
//     * @param classDataAttribute: The first row (class data attribute name) of the file
//     * @param contactID: The class ID
//     * @param newData: The new data that needs to replace the original data
//     * @exception IOException: The IOException for File operation
//     */
//    private void update(File file, String[] classDataAttribute, int contactID, String[] newData) throws IOException {
//        if (!file.exists() || !file.isFile() || !file.canWrite()) {
//            System.out.println("Cannot modify the file.");
//            return;
//        }
//        String[][] data = new String[1+get(file.getName()).length][];
//        data[0] = classDataAttribute;
//
//        System.arraycopy(get(file.getName()), 0, data, 1, get(file.getName()).length - 1);
//        String id = Integer.toString(contactID);
//
//        // Find the row with the matching ID
//        int rowIndex = -1;
//        for (int i = 1; i < data.length; i++) {
//            if (data[i][0].equals(id)) {
//                rowIndex = i;
//                break;
//            }
//        }
//
//        // If a matching row is found, update the data
//        if (rowIndex != -1) {
//            String[] values;
//            values = newData;
//            data[rowIndex] = values;
//        }
//
//        // Write the updated data back to the file
//        try (FileWriter fileWriter = new FileWriter(file)) {
//
//            for (int i = 0; i < data.length - 1; i++) {
//                StringBuilder line = new StringBuilder();
//                for (int j = 0; j < data[i].length; j++) {
//                    line.append(data[i][j]);
//                    if (j != data[i].length - 1) {
//                        line.append(',');
//                    }
//                }
//                line.append("\n");
//                fileWriter.append(line.toString());
//            }
//            fileWriter.close();
//        } catch (IOException ex) {
//            System.out.println("Error reading or writing the file.");
//        }
//    }

    private void updateSorting(File file, int contactID, String[] newData, String fileName) throws IOException {
        if (!file.exists() || !file.isFile() || !file.canWrite()) {
            System.out.println("Cannot modify the file.");
            return;
        }
        String id = Integer.toString(contactID);

        // Find the row with the matching ID
        int rowIndex = -1;
        for (int i = 0; i < get(file.getName()).length - 1; i++) {
            if (get(file.getName())[i][0].equals(id)) {
                rowIndex = i;
                break;
            }
        }

        String[][] tempFile = new String[get(file.getName()).length-1][];
        if (rowIndex > 0) {
            System.arraycopy(get(file.getName()), 0, tempFile, 0, rowIndex);
        }
        if (get(file.getName()).length-rowIndex-1 > 0) {
            System.arraycopy(get(file.getName()), rowIndex + 1, tempFile, rowIndex, get(file.getName()).length - rowIndex - 1);
        }

        int newIndex = 0;
        if (fileName.contains("contacts")) {
            for (int i = 0; i < tempFile.length - 1; i++) {
                if (Integer.parseInt(newData[1]) < Integer.parseInt(tempFile[i][1])) {
                    break;
                } else {
                    newIndex++;
                }
            }
        } else {
            for (int i = 0; i < tempFile.length - 1; i++) {
                if (Integer.parseInt(newData[1]) < Integer.parseInt(tempFile[i][1])) {
                    break;
                } else if (Integer.parseInt(newData[1]) == Integer.parseInt(tempFile[i][1])) {
                    if (fileName.contains("notes")) {
                        if (newData[4].compareTo(tempFile[i][4]) > 0) {
                            break;
                        } else {
                            newIndex++;
                        }
                    } else {
                        if (newData[4].compareTo(tempFile[i][4]) < 0) {
                            break;
                        } else {
                            newIndex++;
                        }
                    }
                } else {
                    newIndex++;
                }
            }
        }

        // Insert the new data into the read 2D string array
        String[][] temp1 = new String[1+newIndex][];
        if (fileName.contains("contacts")) {
            temp1[0] = contactsData;
        } else if (fileName.contains("notes")) {
            temp1[0] = notesData;
        } else if (fileName.contains("tasks")) {
            temp1[0] = tasksData;
        } else if (fileName.contains("events")) {
            temp1[0] = eventsData;
        }
        if (newIndex > 0) {
            System.arraycopy(tempFile, 0, temp1, 1, newIndex);
        }
        String[][] temp2 = new String[2+newIndex][];
        System.arraycopy(temp1, 0, temp2, 0, newIndex + 1);
        temp2[newIndex+1] = newData;
        //System.arraycopy(newData, 0, temp2, newIndex + 1, 1);
        String[][] csvData = new String[2+tempFile.length][];
        System.arraycopy(temp2, 0, csvData, 0, newIndex + 2);
        if (newIndex < (tempFile.length - 1)) {
            System.arraycopy(tempFile, newIndex, csvData, newIndex + 2, tempFile.length - newIndex - 1);
        }

        // Write the updated data back to the file
        try (FileWriter fileWriter = new FileWriter(file)) {

            for (int i = 0; i < csvData.length - 1; i++) {
                StringBuilder line = new StringBuilder();
                for (int j = 0; j < csvData[i].length; j++) {
                    line.append(csvData[i][j]);
                    if (j != csvData[i].length - 1) {
                        line.append(',');
                    }
                }
                line.append("\n");
                fileWriter.append(line.toString());
            }
            fileWriter.close();
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

    public static String[][] search(String keyword, String fileType) {
        String[][] results = new String[0][];
        if (keyword == null || fileType == null) {
            //System.out.println("Invalid keyword or file type provided.");
            return results;
        }

        try {
            String[][] data = SimpleDatabase.get(fileType);
            //System.out.println("Searching in data of length: " + data.length);
            for (int j = 0; j < data.length; j++) {
                for (int k = 0; k < data[j].length; k++) {
                    if (data[j][k] != null && data[j][k].toLowerCase().contains(keyword.toLowerCase())) {
                        //System.out.println("Match found in row: " + j + ", col: " + k + ", value: " + data[j][k]);
                        results = appendToResults(results, data[j]);
                        break;
                    }
                }
            }
            //System.out.println("Search results length: " + results.length);
//            for (String[] row : results) {
//                System.out.println(Arrays.toString(row));
//            }
        } catch (Exception e) {
            System.out.println("Error occurred during searching.");
            return new String[0][];
        }

        return results;
    }

    /**
     * Append the new row to the results
     * @param results: the current results
     * @param newRow: the new row to append
     * @return the new results
     */
    private static String[][] appendToResults(String[][] results, String[] newRow) {
        String[][] newResult = new String[results.length+1][];
        System.arraycopy(results, 0, newResult, 0, results.length);
        newResult[results.length] = newRow;
        return newResult;
    }


    /**
     * Search by date in a specific file type with given comparison operator.
     *
     * @param inputDate: the input date string with format "operator yyyy-mm-dd".
     * @param fileType:  the type of file to search in (notes, tasks, events).
     * @return the search result as a String[][].
     */
    public static String[][] searchByDate(String inputDate, String fileType) {
        String[][] results = new String[0][];

        if (inputDate == null || (!inputDate.startsWith("> ") && !inputDate.startsWith("< ") && !inputDate.startsWith("= "))) {
            System.out.println("Invalid date format or operator.");
            return results;
        }

        char operator = inputDate.charAt(0);
        String dateString = inputDate.substring(2);

        try {
            String[][] data = SimpleDatabase.get(fileType);
            for (int i = 0; i < data.length; i++) {
                if (data[i] == null || data[i][4] == null) {
                    continue;
                }

                int comparison = data[i][4].compareTo(dateString);
                if ((operator == '>' && comparison > 0) ||
                        (operator == '<' && comparison < 0) ||
                        (operator == '=' && comparison == 0)) {
                    results = appendToResults(results, data[i]);
                }
            }
//            if (results.length == 0) {
//                System.out.println("No matching files found for the query.");
//            }
//            return results;
        } catch (Exception e) {
            System.out.println("Error occurred during searching: " + e.getMessage());
            return new String[0][];
        }
        return results;
    }

    public static String[][] searchWithLogicalConnectors(String expression, String type) {
        String fileType = determineFileType(type);
        if (fileType == null) {
            return new String[0][];
        }

        String[][] combinedResults = new String[0][];
        String[] parts;
        if (expression.contains("&&")) {
            parts = expression.split("&&");
            String[][] results1 = performSearch(parts[0].trim(), fileType);
            String[][] results2 = performSearch(parts[1].trim(), fileType);
//            System.out.println("Results from first part: \n" + Arrays.deepToString(results1));
//            System.out.println("Results from second part: \n" + Arrays.deepToString(results2));
            combinedResults = intersectResults(results1, results2);
            //System.out.println("Combined results: \n" + Arrays.deepToString(combinedResults));
        } else if (expression.contains("||")) {
            parts = expression.split("\\|\\|");
            String[][] results1 = performSearch(parts[0].trim(), fileType);
            String[][] results2 = performSearch(parts[1].trim(), fileType);
            combinedResults = unionResults(results1, results2);
        } else if (expression.contains("!")) {
            parts = new String[]{expression.replace("!", "").trim()};
            combinedResults = negateResults(performSearch(parts[0], fileType), fileType);
        } else {
            combinedResults = performSearch(expression, fileType);
        }
        // 打印搜索结果
        //System.out.println("Search results for '" + expression + "': \n" + Arrays.deepToString(combinedResults));
//        if (combinedResults.length == 0 && !expression.isEmpty()) {
//            System.out.println("No matching files found for the query.");
//        }
        return combinedResults;
    }

    private static String[][] performSearch(String query, String fileType) {
        //System.out.println("Performing search with query: " + query + " on fileType: " + fileType);
        if (query.matches("([<>]=?|=)\\s\\d{4}-\\d{2}-\\d{2}")) {
            int spaceIndex = query.indexOf(' ');
            String operator = query.substring(0, spaceIndex).trim();
            String date = query.substring(spaceIndex).trim();
            //System.out.println("Detected date query. Operator: " + operator + ", Date: " + date);
            return searchByDate(operator + " " + date, fileType);
        } else {
            // 其他类型的查询
            //System.out.println("Performing keyword search.");
            return search(query, fileType);
        }
    }

    private static String determineFileType(String type) {
        switch (type.toLowerCase()) {
            case "contacts":
                return "contacts.csv";
            case "notes":
                return "notes.csv";
            case "tasks":
                return "tasks.csv";
            case "events":
                return "events.csv";
            default:
                return null;
        }
    }

    private static String[][] negateResults(String[][] results, String fileType) {
        try {
            Set<List<String>> resultSet = Arrays.stream(results)
                    .map(Arrays::asList)
                    .collect(Collectors.toSet());
            String[][] fullData = SimpleDatabase.get(fileType);

            return Arrays.stream(fullData)
                    .filter(row -> {
                        if (row == null || Arrays.stream(row).allMatch(Objects::isNull)) {
                            // 跳过全 null 行
                            return false;
                        }
                        return !resultSet.contains(Arrays.asList(row));
                    })
                    .toArray(String[][]::new);
        } catch (Exception e) {
            return new String[0][];
        }
    }


    // 找到两个结果集的交集
    private static String[][] intersectResults(String[][] results1, String[][] results2) {
        Set<String[]> resultSet = new HashSet<>();
        Set<Set<String>> set1 = Arrays.stream(results1)
                .map(Arrays::asList)
                .map(HashSet::new)
                .collect(Collectors.toSet());

        for (String[] row : results2) {
            Set<String> rowSet = new HashSet<>(Arrays.asList(row));
            if (set1.contains(rowSet)) {
                resultSet.add(row);
            }
        }
        return resultSet.toArray(new String[0][]);
    }

    // 合并两个结果集
    private static String[][] unionResults(String[][] results1, String[][] results2) {
        Set<List<String>> resultSet = new LinkedHashSet<>();
        Stream.concat(Arrays.stream(results1), Arrays.stream(results2))
                .map(Arrays::asList)
                .forEach(resultSet::add);

        return resultSet.stream()
                .map(list -> list.toArray(new String[0]))
                .toArray(String[][]::new);
    }
}

