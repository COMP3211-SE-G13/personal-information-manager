package controller;

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

    }

    /**
     * Get All Notes Function
     */
    private void getAllNotes() {

    }

    /**
     * Get One Note Function
     * @return String[]: the data of one note
     */
    private String[] getOneNote() {
        String[] data = new String[4];
        return data;
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
