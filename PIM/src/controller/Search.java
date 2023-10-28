package controller;

import model.SimpleDatabase;

public class Search {
    private String keyword;
    //public Search(String keyword) {
//        SimpleDatabase.get();
//        this.keyword = keyword;

//      }
//    public Search(String keyword) {
//        if (keyword == null) {
//            throw new IllegalArgumentException("Keyword cannot be null");
//        }
//        this.keyword = keyword;
//    }

    public String[][] search(String keyword, String type){
        String[][] results = new String[0][];   //store the search result

        if (keyword == null || type == null) {
            System.out.println("Invalid keyword provided.");
            return results;
        }

        try{
            String[] types = {"events.csv", "contacts.csv", "notes.csv", "tasks.csv"};
            //String[][] results = new String[0][];  //store the search result

            for (int i = 0; i < types.length; i++){
                //String type = types[i];
                String[][] data = SimpleDatabase.get(type);
                int index = 0;
                for (int j = 0; j < data.length; j++){
                    for(int k = 0; k < data[j].length; k++){
                        if (data[j][k].contains(keyword)){
                            results = appendToResults(results, data[j]);
                            break;
                        }
                    }
                }
            }
            return results;
        }catch (Exception e){
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
            return new String[0][];
        }
    }

    private String[][] appendToResults(String[][] results, String[] newRow) {
        String[][] newResult = new String[results.length+1][];
        System.arraycopy(results, 0, newResult, 0, results.length);
        newResult[results.length] = newRow;
        return newResult;
    }
}
