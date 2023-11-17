package controller;

public class Search {
    /**
     * Search Function (Public)
     * @param keyword: the keyword of search
     * @param type:    the type of search
     * @return the result of search
     */
    public static String[][] search(String keyword, String type) {
        return model.SimpleDatabase.search(keyword, type);
    }

    /**

     */
    public static String[][] searchByDate(String inputDate, String type) {
        return model.SimpleDatabase.searchByDate(inputDate, type);
    }



}
