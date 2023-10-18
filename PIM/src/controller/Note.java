package controller;

import model.SimpleDatabase;

public class Note {
    String noteTitle;
    String noteContent;
    String createTime;
    String lastModifyTime;

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
    private void createNote() {
        try {
            int noteId = SimpleDatabase.getNewID("note.csv");

            String[][] newNoteData = {
                    {String.valueOf(noteId), noteTitle, noteContent, createTime, lastModifyTime}
            };

            new SimpleDatabase("insert", "note.csv", newNoteData);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
        }
    }

    /**
     * Get All Notes Function
     */
    private void getAllNotes() {
        try {
            String[][] data = SimpleDatabase.get("note.csv");

            for (int i = 0; i < data.length; i++) {
                System.out.println("Note ID: " + data[i][0]);
                System.out.println("Note Title: " + data[i][1]);
                System.out.println("Note Content: " + data[i][2]);
                System.out.println("Create Time: " + data[i][3]);
                System.out.println("Last Modify Time: " + data[i][4]);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
        }
    }

    /**
     * Get One Note Function
     * @return String[]: the data of one note
     */
    private String[] getOneNote() {
        try {
            String[][] data = SimpleDatabase.get("note.csv");

            for (int i = 0; i < data.length; i++) {
                if (data[i][0].equals(noteTitle)) {
                    return data[i];
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
        }

        return null;
    }

    /**
     * Modify Note Function
     */
    private void modifyNote() {

    }

    /**
     * Remove Note Function
     */
    private void removeNote() {

    }
}
