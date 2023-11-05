import model.SimpleDatabase;
import controller.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ProjectTest {

    // Model

    /**
     * Test of getNewID method in Model
     */
    @Test
    public void test1_1() {
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
    public void test1_2() {
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


    // Controller - Auth

    /**
     * Test of Create User method in Controller.Auth
     */
    @Test
    public void test2_1() {
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
    public void test2_2() {
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


    // Controller - Contact

    /**
     * Test Create And GetAll in Contact Controller
     */
    @Test
    public void test3_1() {
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

    /**
     * Test Update contact from Contact Controller
     */
    @Test
    public void test3_2() {
        try {

        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
            fail();
        }
    }

    /**
     * Test Remove contact from Contact Controller
     */
    @Test
    public void test3_3() {
        try {
            Auth.login("david", "1234");
            Contact.removeContact("2");

            String dataString = "";
            String resultString = "";

            String[][] data = Contact.getAllContacts();
            String[][] result = new String[][] {{"1", "2", "Tom", "Smith", "31283425", "HK Street"}};

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


    // Controller - Note

    /**
     * Test Create And GetAll in Note Controller
     */
    @Test
    public void test4_1() {
        try {
            Auth.login("david", "1234");
            Note noteInfo1 = new Note("Note 1", "Here is Note 1");
            Note noteInfo2 = new Note("Note 2", "Here is Note 2");

            Note.createNote(noteInfo1);
            Note.createNote(noteInfo2);

            String dataGetAllString = "";
            String dataGetOneString = "";
            String resultGetAllString = "";
            String resultGetOneString = "";

            String date = String.valueOf(LocalDate.now());

            String[][] dataGetAll = Note.getAllNotes();
            String[] dataGetOne = Note.getOneNote("2");
            String[][] resultGetAll = new String[][] {{"1", "2", "Note 1", "Here is Note 1", date, date}, {"2", "2", "Note 2", "Here is Note 2", date, date}};
            String[][] resultGetOne = new String[][] {{"2", "2", "Note 2", "Here is Note 2", date, date}};

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

    /**
     * Test Update note from Note Controller
     */
    @Test
    public void test4_2() {
        try {

        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
            fail();
        }
    }

    /**
     * Test Remove note from Note Controller
     */
    @Test
    public void test4_3() {
        try {
            Auth.login("david", "1234");
            Note.removeNote("2");

            String dataString = "";
            String resultString = "";

            String[][] data = Note.getAllNotes();

            String date = String.valueOf(LocalDate.now());
            String[][] result = new String[][] {{"1", "2", "Note 1", "Here is Note 1", date, date}};

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


    // Controller - task to-do list

    /**
     * Test Create And GetAll in Task Controller
     */
    @Test
    public void test5_1() {
        try {
            Auth.login("david", "1234");
            Todo taskInfo1 = new Todo("Task 1", "2023-11-25", "To finish task 1");
            Todo taskInfo2 = new Todo("Task 2", "2023-12-01", "To finish task 2");

            Todo.createTask(taskInfo1);
            Todo.createTask(taskInfo2);

            String dataGetAllString = "";
            String dataGetOneString = "";
            String resultGetAllString = "";
            String resultGetOneString = "";

            String[][] dataGetAll = Todo.getAllTasks();
            String[] dataGetOne = Todo.getOneTask("2");
            String[][] resultGetAll = new String[][] {{"1", "2", "Task 1", "2023-11-25", "To finish task 1"}, {"2", "2", "Task 2", "2023-12-01", "To finish task 2"}};
            String[][] resultGetOne = new String[][] {{"2", "2", "Task 2", "2023-12-01", "To finish task 2"}};

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

    /**
     * Test Update task from Task Controller
     */
    @Test
    public void test5_2() {
        try {

        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
            fail();
        }
    }

    /**
     * Test Remove task from Task Controller
     */
    @Test
    public void test5_3() {
        try {
            Auth.login("david", "1234");
            Todo.removeTask("2");

            String dataString = "";
            String resultString = "";

            String[][] data = Todo.getAllTasks();
            String[][] result = new String[][] {{"1", "2", "Task 1", "2023-11-25", "To finish task 1"}};

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


    // Controller - Event

    /**
     * Test Create And GetAll in Event Controller
     */
    @Test
    public void test6_1() {
        try {
            Auth.login("david", "1234");
            Event eventInfo1 = new Event("Event 1", "2023-11-20 19:30:00", "5h", "Join the event 1");
            Event eventInfo2 = new Event("Event 2", "2023-11-30 10:00:00", "24h", "Join the event 2");

            Event.createEvent(eventInfo1);
            Event.createEvent(eventInfo2);

            String dataGetAllString = "";
            String dataGetOneString = "";
            String resultGetAllString = "";
            String resultGetOneString = "";

            String[][] dataGetAll = Event.getAllEvents();
            String[] dataGetOne = Event.getOneEvent("2");
            String[][] resultGetAll = new String[][] {{"1", "2", "Event 1", "2023-11-20 19:30:00", "5h", "Join the event 1"}, {"2", "2", "Event 2", "2023-11-30 10:00:00", "24h", "Join the event 2"}};
            String[][] resultGetOne = new String[][] {{"2", "2", "Event 2", "2023-11-30 10:00:00", "24h", "Join the event 2"}};

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

    /**
     * Test Update event from Event Controller
     */
    @Test
    public void test6_2() {
        try {

        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
            fail();
        }
    }

    /**
     * Test Remove event from Event Controller
     */
    @Test
    public void test6_3() {
        try {
            Auth.login("david", "1234");
            Event.removeEvent("2");

            String dataString = "";
            String resultString = "";

            String[][] data = Event.getAllEvents();
            String[][] result = new String[][] {{"1", "2", "Event 1", "2023-11-20 19:30:00", "5h", "Join the event 1"}};

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


    // Controller - Search

    /**
     * Test the searching in Search Controller
     */
    @Test
    public void test7() {
        try {

        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
            fail();
        }
    }


    // Controller - Export





    // Controller - Import





    // View





}