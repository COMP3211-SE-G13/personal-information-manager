package controller;

import model.SimpleDatabase;
import static controller.Auth.getUserId;

import java.time.LocalDate;


public class Note {
    private String noteTitle;
    private String noteContent;
    private String createTime;
    private String lastModifyTime;
    private int userId;

    /**
     * Note Contract
     * @param noteTitle: the title of note
     * @param noteContent: the content of note
     */
    public Note(String noteTitle, String noteContent) {
        LocalDate date = LocalDate.now();

        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
        this.createTime = String.valueOf(date);
        this.lastModifyTime = String.valueOf(date);
        this.userId = getUserId();
    }

    public static void createNote(Note noteInfo) {
        createNote(noteInfo.noteTitle, noteInfo.noteContent, noteInfo.createTime, noteInfo.lastModifyTime, noteInfo.userId);
    }

    /**
     * Create Note Function
     */
    private static void createNote(String noteTitle, String noteContent, String createTime, String lastModifyTime, int userId) {
        try {
            if (noteTitle.isEmpty() || noteContent.isEmpty() || createTime.isEmpty() || lastModifyTime.isEmpty()) {
                System.out.println("Please enter all information!");
                return;
            }

            int noteId = SimpleDatabase.getNewID("notes.csv");

            String[][] newNoteData = {
                    {String.valueOf(noteId), String.valueOf(userId), noteTitle, noteContent, createTime, lastModifyTime}
            };

            new SimpleDatabase("insert", "notes.csv", newNoteData);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
        }
    }

    /**
     * Get All Notes Function
     */
    public static String[][] getAllNotes() {
        try{
            String[][] data = SimpleDatabase.get("notes.csv");
            return data;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
        }
        return null;
    }

    /**
     * Get One Note Function
     * @return String[]: the data of one note
     */
    public static String[] getOneNote(String noteId) {
        try {
            String[][] data = SimpleDatabase.get("notes.csv");

            for (int i = 0; i < data.length; i++) {
                if (data[i][0].equals(noteId) && data[i][1].equals(String.valueOf(Auth.getUserId()))) {
                    return data[i];
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
            return null;
        }

        return null;
    }

    /**
     * Modify Note Function
     */
    private void modifyNote(String noteTitle, String noteContent, String createTime, String lastModifyTime, int userId, int noteId) {
        try {
            String[] dataWantUpdate = {String.valueOf(noteId), String.valueOf(userId), noteTitle, noteContent, createTime, lastModifyTime};
            new SimpleDatabase("update", "notes.csv", noteId, dataWantUpdate);
            System.out.println("Update Successfully!");

        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
        }
    }


    public static void removeNote(String noteId) {
        removeNote(Integer.parseInt(noteId));
    }

    /**
     * Remove Note Function
     */
    private static void removeNote(int noteId) {
        try {
            new SimpleDatabase("remove", "notes.csv", Auth.getUserId(), noteId);
            System.out.println("Remove Successfully!");

        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
        }
    }
}
