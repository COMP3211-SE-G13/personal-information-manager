package model;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONObject;

public class SimpleDatabase {
    private String mode;
    private String fileBasePath;
    private String fileName;
    private String key;
    private String value;


    /**
     * SimpleDatabase Contract
     * Operations:
     * - create (return true || false)
     * - deleteall (return true || false)
     * - getall (return data)
     *
     * @param mode: the mode of the database
     * @param fileBasePath: the file bath path of the data
     * @param fileName: the file name of the data file
     */
    public SimpleDatabase(String mode, String fileBasePath, String fileName) {
        this.mode = mode;
        this.fileBasePath = fileBasePath;
        this.fileName = fileName;

        if (mode.equals("create")) {
            create(fileBasePath, fileName);
        }

        if (mode.equals("deleteall")) {
            deleteall(fileBasePath, fileName);
        }

        if (mode.equals("getall")) {
            getall(fileBasePath, fileName);
        }
    }


    /**
     * SimpleDatabase Contract
     * Operations:
     * - remove (return true || false)
     * - get (return data)
     * - insert (return true || false)
     * - update (return true || false)
     *
     * @param mode: the mode of the database
     * @param fileBasePath: the file bath path of the data
     * @param fileName: the file name of the data file
     * @param key: the key of the data
     * @param value: the value of the data
     */
    public SimpleDatabase(String mode, String fileBasePath, String fileName, String key, String value) {
        this.mode = mode;
        this.fileBasePath = fileBasePath;
        this.fileName = fileName;
        this.key = key;
        this.value = value;

        if (mode.equals("get")) {

        }

        if (mode.equals("insert")) {

        }

        if (mode.equals("update")) {

        }

        if (mode.equals("remove")) {

        }
    }


    /**
     * Create Data File Operation Function
     * @param fileBasePath: the file bath path of the data
     * @param fileName: the file name of the data file
     * @return true || false
     */
    private Boolean create(String fileBasePath, String fileName) {
        try {
            FileWriter file = new FileWriter("./data" + fileBasePath + fileName + ".json");
            file.close();
            System.out.println("Successfully created the data file");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Delete the User all data File Operation Function
     * @param fileBasePath: the file bath path of the data
     * @param fileName: the file name of the data file
     * @return true || false
     */
    private Boolean deleteall(String fileBasePath, String fileName) {
        try {
            File dataFile = new File("./data" + fileBasePath + fileName + ".json");
            if (dataFile.delete()) {
                System.out.println("Deleted the data file");
                return true;
            } else {
                System.out.println("Failed to delete the data file");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Get all data File Operation Function
     * @param fileBasePath: the file bath path of the data
     * @param fileName: the file name of the data file
     * @return data
     */
    private String getall(String fileBasePath, String fileName) {
        try {
            FileReader file = new FileReader("./data" + fileBasePath + fileName + ".json");
            String data = (char) file.read() + "";

            System.out.println(data);
            file.close();

            return data;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private String get(String fileBasePath, String fileName, String key) {
        return null;
    }


    private Boolean insert(String fileBasePath, String fileName, String key, String value) {
        return true;
    }


    private Boolean update(String fileBasePath, String fileName, String key, String value) {
        return true;
    }


    private Boolean remove(String fileBasePath, String fileName, String key) {
        return true;
    }

}

