import model.SimpleDatabase;
import controller.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ProjectTest {

    /**
     * Test of getNewID method in Model
     */
    @Test
    public void testGetNewID() {
        try {
//            new SimpleDatabase("insert", "user.csv", new String[][]{{"1", "admin", "1234"}});
            SimpleDatabase.isDatabaseExist();
            Auth.signup("admin", "1234");
            int id = SimpleDatabase.getNewID("user.csv");
            assertEquals(2, id);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
            fail();
        }
    }

    /**
     * Test of get method in Model
     */
    @Test
    public void testGet() {
        try {
            String dataString = "";
            String resultString = "";

            String[][] data = SimpleDatabase.get("user.csv");
            String[][] result = new String[][] {{"1", "admin", "1234"}};

            StringBuilder dataTempStr = new StringBuilder();
            for (int i = 0; i < data.length - 1; i++) {
                for (int j = 0; j < data[i].length; j++){
                    dataTempStr.append(data[i][j]);
                }
            }
            dataString = dataTempStr.toString();

            StringBuilder resultTempStr = new StringBuilder();
            for(String[] s1 : result){
                for(String s2 : s1){
                    resultTempStr.append(s2);
                }
            }
            resultString = resultTempStr.toString();

            assertEquals(resultString, dataString);

        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
            fail();
        }
    }

    /**
     * Test of Create User method in Controller.Auth
     */
    @Test
    public void testCreateUser() {
        try {
            Auth.signup("david", "1234");

            String dataString = "";
            String resultString = "";

            String[][] data = SimpleDatabase.get("user.csv");
            String[][] result = new String[][] {{"1", "admin", "1234"}, {"2", "david", "1234"}};

            StringBuilder dataTempStr = new StringBuilder();
            for (int i = 0; i < data.length - 1; i++) {
                for (int j = 0; j < data[i].length; j++){
                    dataTempStr.append(data[i][j]);
                }
            }
            dataString = dataTempStr.toString();

            StringBuilder resultTempStr = new StringBuilder();
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result[i].length; j++){
                    resultTempStr.append(result[i][j]);
                }
            }
            resultString = resultTempStr.toString();

            assertEquals(resultString, dataString);

        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
            fail();
        }
    }

    @Test
    public void testSignin() {
        try {
            Auth.login("david", "1234");
            String userId = String.valueOf(Auth.getUserId());

            assertEquals("2", userId);

        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
            fail();
        }
    }



//
//    @Test
//    public void testUpdate() {
//        String[][] data = {{"1", "1", "test", "test", "test", "test"}};
//        SimpleDatabase.update("events.csv", data);
//        String[][] newData = SimpleDatabase.get("events.csv");
//        Assert.assertEquals(1, newData.length);
//    }
//
//    @Test
//    public void testDelete() {
//        String[][] data = {{"1", "1", "test", "test", "test", "test"}};
//        SimpleDatabase.delete("events.csv", data);
//        String[][] newData = SimpleDatabase.get("events.csv");
//        Assert.assertEquals(0, newData.length);
//    }
}