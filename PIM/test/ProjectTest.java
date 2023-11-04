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
    public void test1() {
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
    public void test2() {
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
    public void test3() {
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

    /**
     * Test Login in Auth Controller
     */
    @Test
    public void test4() {
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

    /**
     * Test Create And GetAll in Contact Controller
     */
    @Test
    public void test5() {
        try {
            Auth.login("david", "1234");
            Contact contactInfo1 = new Contact("Tom", "Smith", "31283425", "HK Street");
            Contact contactInfo2 = new Contact("Sandy", "Disney", "62481823", "SH Street");

            Contact.createContact(contactInfo1);
            Contact.createContact(contactInfo2);

            String dataGetAllString = "";
            String dataGetOneString = "";
            String resultGetAllString = "";
            String resultGetOneString = "";

            String[][] dataGetAll = Contact.getAllContacts();
            String[] dataGetOne = Contact.getOneContact("2");
            String[][] resultGetAll = new String[][] {{"1", "2", "Tom", "Smith", "31283425", "HK Street"}, {"2", "2", "Sandy", "Disney", "62481823", "SH Street"}};
            String[][] resultGetOne = new String[][] {{"2", "2", "Sandy", "Disney", "62481823", "SH Street"}};

            StringBuilder dataGetAllTempStr = new StringBuilder();
            for (int i = 0; i < dataGetAll.length - 1; i++) {
                for (int j = 0; j < dataGetAll[i].length; j++){
                    dataGetAllTempStr.append(dataGetAll[i][j]);
                }
            }
            dataGetAllString = dataGetAllTempStr.toString();

            StringBuilder dataGetOneTempStr = new StringBuilder();
            for (int i = 0; i < dataGetOne.length; i++) {
                dataGetOneTempStr.append(dataGetOne[i]);
            }
            dataGetOneString = dataGetOneTempStr.toString();

            StringBuilder resultGetAllTempStr = new StringBuilder();
            for (int i = 0; i < resultGetAll.length; i++) {
                for (int j = 0; j < resultGetAll[i].length; j++){
                    resultGetAllTempStr.append(resultGetAll[i][j]);
                }
            }
            resultGetAllString = resultGetAllTempStr.toString();

            StringBuilder resultGetOneTempStr = new StringBuilder();
            for (int i = 0; i < resultGetOne.length; i++) {
                for (int j = 0; j < resultGetOne[i].length; j++){
                    resultGetOneTempStr.append(resultGetOne[i][j]);
                }
            }
            resultGetOneString = resultGetOneTempStr.toString();


            assertEquals(resultGetAllString, dataGetAllString);
            assertEquals(resultGetOneString, dataGetOneString);

        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
            fail();
        }
    }

    @Test
    public void test6() {
        try {

        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
            fail();
        }
    }

}