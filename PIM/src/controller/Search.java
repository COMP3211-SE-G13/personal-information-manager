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
     * Search by time (Public)
     * @param timeComparison: the comparison string such as ">1"
     * @param type:           the type of search, either "tasks" or "events"
     * @return the result of search
     */
    public static String[][] searchByTime(String timeComparison, String type) {
        return model.SimpleDatabase.searchByTime(timeComparison, type);
    }

    /**
     * Search with logical connectors (Public)
     * @param conditionA: first condition result set
     * @param conditionB: second condition result set
     * @param connector:  logical connector (&&, ||, !)
     * @return the result of search
     */
    public static String[][] searchWithLogicalConnectors(String[][] conditionA, String[][] conditionB, String connector) {
        return model.SimpleDatabase.searchWithLogicalConnectors(conditionA, conditionB, connector);
    }

}
