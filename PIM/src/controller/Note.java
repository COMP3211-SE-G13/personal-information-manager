package controller;

import model.SimpleDatabase;
import view.Pages;

public class Note {
    private String noteTitle;
    private String noteContent;
    private String createTime;
    private String lastModifyTime;

    /**
     * Note Contract
     * @param noteTitle: the title of note
     * @param noteContent: the content of note
     * @param createTime: the creation time of note
     * @param lastModifyTime: the last modify time of note
     */
    public Note(String noteTitle, String noteContent, String createTime, String lastModifyTime) {
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
        this.createTime = createTime;
        this.lastModifyTime = lastModifyTime;
    }

    /**
     * Create Note Function
     */
    private void createNote(String noteTitle, String noteContent, String createTime, String lastModifyTime, int userId) {
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
    private String[][] getAllNotes() {
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
    private String[] getOneNote() {
        try {
            String[][] data = SimpleDatabase.get("notes.csv");

            for (int i = 0; i < data.length; i++) {
                if (data[i][0].equals(noteTitle) && data[i][1].equals(String.valueOf(Auth.getUserId()))) {
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

    /**
     * Remove Note Function
     */
    private void removeNote( String contactId) {

            try {
                new SimpleDatabase("remove", "note.csv",Auth.getUserId(), Integer.parseInt(contactId));
                System.out.println("Remove Successfully!");

            } catch (Exception e) {
                System.out.println("Error: " + e);
                System.out.println("Please try again!");
            }


    }
}
