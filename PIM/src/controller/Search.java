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
     * Search By Date Function (Public)
     * @param inputDate: the date of search
     * @param type: the type of search
     * @return: the result of search
     */
    public static String[][] searchByDate(String inputDate, String type) {
        return model.SimpleDatabase.searchByDate(inputDate, type);
    }

    /**
     * Search with Logical Connectors Function (Public)
     * @param expression: the expression of search
     * @param type: the type of search
     * @return: the result of search
     */
    public static  String [][] searchWithLogicalConnectors(String expression, String type){
        return model.SimpleDatabase.searchWithLogicalConnectors(expression, type);
    }



}
