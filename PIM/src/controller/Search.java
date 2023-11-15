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

    public String[][] handleSearchByTimeRequest(String fileName, String time, String operator) {
        try {
            return model.SimpleDatabase.searchByTime(fileName, time, operator);
        } catch (Exception e) {
            System.err.println(e);
            return new String[0][];
        }
    }

    public String[][] handleSearchWithLogicRequest(String fileName, String[][] conditions) {
        try {
            return model.SimpleDatabase.searchWithLogic(fileName, conditions);
        } catch (Exception e) {
            System.err.println(e);
            return new String[0][];
        }
    }
//    public static String[][] search(String type, String keyword, String timeCompare, String compareTime){
//        return model.SimpleDatabase.search(type, keyword, timeCompare, compareTime);
//
//    }

}
