import controller.*;
import model.SimpleDatabase;
import org.junit.Test;

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

    /**
     * Test of getNewID method in Model
     */
    @Test
    public void test1_3() {
        try {
            int id = SimpleDatabase.getNewID("users");
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
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
            if (Auth.login("david", "1234")) {
                String userId = String.valueOf(Auth.getUserId());

                assertEquals("2", userId);
            } else {
                fail();
            }
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
            if (Auth.login("david", "1234")) {
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
                String[][] resultGetAll = new String[][]{{"1", "2", "Tom", "Smith", "31283425", "HK Street"}, {"2", "2", "Sandy", "Disney", "62481823", "SH Street"}};
                String[][] resultGetOne = new String[][]{{"2", "2", "Sandy", "Disney", "62481823", "SH Street"}};

                StringBuilder dataGetAllTempStr = new StringBuilder();
                for (int i = 0; i < dataGetAll.length - 1; i++) {
                    for (int j = 0; j < dataGetAll[i].length; j++) {
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
                    for (int j = 0; j < resultGetAll[i].length; j++) {
                        resultGetAllTempStr.append(resultGetAll[i][j]);
                    }
                }
                resultGetAllString = resultGetAllTempStr.toString();

                StringBuilder resultGetOneTempStr = new StringBuilder();
                for (int i = 0; i < resultGetOne.length; i++) {
                    for (int j = 0; j < resultGetOne[i].length; j++) {
                        resultGetOneTempStr.append(resultGetOne[i][j]);
                    }
                }
                resultGetOneString = resultGetOneTempStr.toString();


                assertEquals(resultGetAllString, dataGetAllString);
                assertEquals(resultGetOneString, dataGetOneString);
            } else {
                fail();
            }
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
            if (Auth.login("david", "1234")) {
                Contact contactInfo = new Contact("Sandy", "Disney", "62481823", "128 SH Street");
                Contact.modifyContact(contactInfo, "2");

                String dataString = "";
                String resultString = "";

                String[] data = Contact.getOneContact("2");

                String[] result = new String[]{"2", "2", "Sandy", "Disney", "62481823", "128 SH Street"};

                StringBuilder dataTempStr = new StringBuilder();
                for (int i = 0; i < data.length; i++) {
                    dataTempStr.append(data[i]);
                }
                dataString = dataTempStr.toString();

                StringBuilder resultTempStr = new StringBuilder();
                for (int i = 0; i < result.length; i++) {
                    resultTempStr.append(result[i]);
                }
                resultString = resultTempStr.toString();

                assertEquals(resultString, dataString);
            } else {
                fail();
            }
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
            if (Auth.login("david", "1234")) {
                Contact.removeContact("2");

                String dataString = "";
                String resultString = "";

                String[][] data = Contact.getAllContacts();
                String[][] result = new String[][]{{"1", "2", "Tom", "Smith", "31283425", "HK Street"}};

                StringBuilder dataTempStr = new StringBuilder();
                for (int i = 0; i < data.length - 1; i++) {
                    for (int j = 0; j < data[i].length; j++) {
                        dataTempStr.append(data[i][j]);
                    }
                }
                dataString = dataTempStr.toString();

                StringBuilder resultTempStr = new StringBuilder();
                for (int i = 0; i < result.length; i++) {
                    for (int j = 0; j < result[i].length; j++) {
                        resultTempStr.append(result[i][j]);
                    }
                }
                resultString = resultTempStr.toString();

                assertEquals(resultString, dataString);
            } else {
                fail();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
            fail();
        }
    }

    /**
     * Test Search contact from Contact Controller
     */
    @Test
    public void test3_4() {
        try {
            if (Auth.login("david", "1234")) {
                Search.search("Tom", "contacts");

                String dataString = "";
                String resultString = "";

                String[][] data = Contact.getAllContacts();

                String[][] result = new String[][]{{"1", "2", "Tom", "Smith", "31283425", "HK Street"}};

                StringBuilder dataTempStr = new StringBuilder();
                for (int i = 0; i < data.length - 1; i++) {
                    for (int j = 0; j < data[i].length; j++) {
                        dataTempStr.append(data[i][j]);
                    }
                }
                dataString = dataTempStr.toString();

                StringBuilder resultTempStr = new StringBuilder();
                for (int i = 0; i < result.length; i++) {
                    for (int j = 0; j < result[i].length; j++) {
                        resultTempStr.append(result[i][j]);
                    }
                }
                resultString = resultTempStr.toString();

                assertEquals(resultString, dataString);
            } else {
                fail();
            }
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
            if (Auth.login("david", "1234")) {
                Note noteInfo1 = new Note("Note 1", "Here is Note 1. Hello World!");
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
                String[][] resultGetAll = new String[][]{{"1", "2", "Note 1", "Here is Note 1. Hello World!", date}, {"2", "2", "Note 2", "Here is Note 2", date}};
                String[][] resultGetOne = new String[][]{{"2", "2", "Note 2", "Here is Note 2", date}};

                StringBuilder dataGetAllTempStr = new StringBuilder();
                for (int i = 0; i < dataGetAll.length - 1; i++) {
                    for (int j = 0; j < dataGetAll[i].length; j++) {
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
                    for (int j = 0; j < resultGetAll[i].length; j++) {
                        resultGetAllTempStr.append(resultGetAll[i][j]);
                    }
                }
                resultGetAllString = resultGetAllTempStr.toString();

                StringBuilder resultGetOneTempStr = new StringBuilder();
                for (int i = 0; i < resultGetOne.length; i++) {
                    for (int j = 0; j < resultGetOne[i].length; j++) {
                        resultGetOneTempStr.append(resultGetOne[i][j]);
                    }
                }
                resultGetOneString = resultGetOneTempStr.toString();


                assertEquals(resultGetAllString, dataGetAllString);
                assertEquals(resultGetOneString, dataGetOneString);
            } else {
                fail();
            }
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
            if (Auth.login("david", "1234")) {
                Note noteInfo = new Note("Note 2", "Hello World for Note 2");
                Note.modifyNote(noteInfo, "2");

                String dataString = "";
                String resultString = "";

                String[][] data = Note.getAllNotes();

                String date = String.valueOf(LocalDate.now());
                String[][] result = new String[][]{{"1", "2", "Note 1", "Here is Note 1. Hello World!", date}, {"2", "2", "Note 2", "Hello World for Note 2", date}};

                StringBuilder dataTempStr = new StringBuilder();
                for (int i = 0; i < data.length - 1; i++) {
                    for (int j = 0; j < data[i].length; j++) {
                        dataTempStr.append(data[i][j]);
                    }
                }
                dataString = dataTempStr.toString();

                StringBuilder resultTempStr = new StringBuilder();
                for (int i = 0; i < result.length; i++) {
                    for (int j = 0; j < result[i].length; j++) {
                        resultTempStr.append(result[i][j]);
                    }
                }
                resultString = resultTempStr.toString();

                assertEquals(resultString, dataString);
            } else {
                fail();
            }
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
            if (Auth.login("david", "1234")) {
                Note.removeNote("2");

                String dataString = "";
                String resultString = "";

                String[][] data = Note.getAllNotes();

                String date = String.valueOf(LocalDate.now());
                String[][] result = new String[][]{{"1", "2", "Note 1", "Here is Note 1. Hello World!", date}};

                StringBuilder dataTempStr = new StringBuilder();
                for (int i = 0; i < data.length - 1; i++) {
                    for (int j = 0; j < data[i].length; j++) {
                        dataTempStr.append(data[i][j]);
                    }
                }
                dataString = dataTempStr.toString();

                StringBuilder resultTempStr = new StringBuilder();
                for (int i = 0; i < result.length; i++) {
                    for (int j = 0; j < result[i].length; j++) {
                        resultTempStr.append(result[i][j]);
                    }
                }
                resultString = resultTempStr.toString();

                assertEquals(resultString, dataString);
            } else {
                fail();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
            fail();
        }
    }

    /**
     * Test Search note from Note Controller
     */
    @Test
    public void test4_4() {
        try {
            if (Auth.login("david", "1234")) {
                Search.search("hello world", "notes");

                String dataString = "";
                String resultString = "";

                String[][] data = Note.getAllNotes();

                String date = String.valueOf(LocalDate.now());
                String[][] result = new String[][]{{"1", "2", "Note 1", "Here is Note 1. Hello World!", date}};

                StringBuilder dataTempStr = new StringBuilder();
                for (int i = 0; i < data.length - 1; i++) {
                    for (int j = 0; j < data[i].length; j++) {
                        dataTempStr.append(data[i][j]);
                    }
                }
                dataString = dataTempStr.toString();

                StringBuilder resultTempStr = new StringBuilder();
                for (int i = 0; i < result.length; i++) {
                    for (int j = 0; j < result[i].length; j++) {
                        resultTempStr.append(result[i][j]);
                    }
                }
                resultString = resultTempStr.toString();

                assertEquals(resultString, dataString);
            } else {
                fail();
            }
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
            if (Auth.login("david", "1234")) {
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
                String[][] resultGetAll = new String[][]{{"1", "2", "Task 1", "2023-11-25", "To finish task 1"}, {"2", "2", "Task 2", "2023-12-01", "To finish task 2"}};
                String[][] resultGetOne = new String[][]{{"2", "2", "Task 2", "2023-12-01", "To finish task 2"}};

                StringBuilder dataGetAllTempStr = new StringBuilder();
                for (int i = 0; i < dataGetAll.length - 1; i++) {
                    for (int j = 0; j < dataGetAll[i].length; j++) {
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
                    for (int j = 0; j < resultGetAll[i].length; j++) {
                        resultGetAllTempStr.append(resultGetAll[i][j]);
                    }
                }
                resultGetAllString = resultGetAllTempStr.toString();

                StringBuilder resultGetOneTempStr = new StringBuilder();
                for (int i = 0; i < resultGetOne.length; i++) {
                    for (int j = 0; j < resultGetOne[i].length; j++) {
                        resultGetOneTempStr.append(resultGetOne[i][j]);
                    }
                }
                resultGetOneString = resultGetOneTempStr.toString();


                assertEquals(resultGetAllString, dataGetAllString);
                assertEquals(resultGetOneString, dataGetOneString);
            } else {
                fail();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
            fail();
        }
    }

    /**
     * Test Update task from Todo Controller
     */
    @Test
    public void test5_2() {
        try {
            if (Auth.login("david", "1234")) {
                Todo taskInfo = new Todo("Task 2", "2023-12-01", "To finish task 2 and also update the all the task info");
                Todo.modifyTask(taskInfo, "2");

                String dataString = "";
                String resultString = "";

                String[][] data = Todo.getAllTasks();

                String[][] result = new String[][]{{"1", "2", "Task 1", "2023-11-25", "To finish task 1"}, {"2", "2", "Task 2", "2023-12-01", "To finish task 2 and also update the all the task info"}};

                StringBuilder dataTempStr = new StringBuilder();
                for (int i = 0; i < data.length - 1; i++) {
                    for (int j = 0; j < data[i].length; j++) {
                        dataTempStr.append(data[i][j]);
                    }
                }
                dataString = dataTempStr.toString();

                StringBuilder resultTempStr = new StringBuilder();
                for (int i = 0; i < result.length; i++) {
                    for (int j = 0; j < result[i].length; j++) {
                        resultTempStr.append(result[i][j]);
                    }
                }
                resultString = resultTempStr.toString();

                assertEquals(resultString, dataString);
            } else {
                fail();
            }
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
            if (Auth.login("david", "1234")) {
                Todo.removeTask("2");

                String dataString = "";
                String resultString = "";

                String[][] data = Todo.getAllTasks();
                String[][] result = new String[][]{{"1", "2", "Task 1", "2023-11-25", "To finish task 1"}};

                StringBuilder dataTempStr = new StringBuilder();
                for (int i = 0; i < data.length - 1; i++) {
                    for (int j = 0; j < data[i].length; j++) {
                        dataTempStr.append(data[i][j]);
                    }
                }
                dataString = dataTempStr.toString();

                StringBuilder resultTempStr = new StringBuilder();
                for (int i = 0; i < result.length; i++) {
                    for (int j = 0; j < result[i].length; j++) {
                        resultTempStr.append(result[i][j]);
                    }
                }
                resultString = resultTempStr.toString();

                assertEquals(resultString, dataString);
            } else {
                fail();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
            fail();
        }
    }

    /**
     * Test Search task from Todo Controller
     */
    @Test
    public void test5_4() {
        try {
            if (Auth.login("david", "1234")) {
                Search.search("task 1", "tasks");

                String dataString = "";
                String resultString = "";

                String[][] data = Todo.getAllTasks();

                String[][] result = new String[][]{{"1", "2", "Task 1", "2023-11-25", "To finish task 1"}};

                StringBuilder dataTempStr = new StringBuilder();
                for (int i = 0; i < data.length - 1; i++) {
                    for (int j = 0; j < data[i].length; j++) {
                        dataTempStr.append(data[i][j]);
                    }
                }
                dataString = dataTempStr.toString();

                StringBuilder resultTempStr = new StringBuilder();
                for (int i = 0; i < result.length; i++) {
                    for (int j = 0; j < result[i].length; j++) {
                        resultTempStr.append(result[i][j]);
                    }
                }
                resultString = resultTempStr.toString();

                assertEquals(resultString, dataString);
            } else {
                fail();
            }
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
            if (Auth.login("david", "1234")) {
                Event eventInfo1 = new Event("Event 1", "2023-11-20", "1", "Join the event 1");
                Event eventInfo2 = new Event("Event 3", "2023-11-30", "2", "Join the event 3");
                Event eventInfo3 = new Event("Event 2", "2023-11-25", "1", "Join the event 2");

                Event.createEvent(eventInfo1);
                Event.createEvent(eventInfo2);
                Event.createEvent(eventInfo3);

                String dataGetAllString = "";
                String dataGetOneString = "";
                String resultGetAllString = "";
                String resultGetOneString = "";

                String[][] dataGetAll = Event.getAllEvents();
                String[] dataGetOne = Event.getOneEvent("3");
                String[][] resultGetAll = new String[][]{
                        {"1", "2", "Event 1", "Join the event 1", "2023-11-20", "1"},
                        {"3", "2", "Event 2", "Join the event 2", "2023-11-25", "1"},
                        {"2", "2", "Event 3", "Join the event 3", "2023-11-30", "2"}
                };
                String[][] resultGetOne = new String[][]{{"3", "2", "Event 2", "Join the event 2", "2023-11-25", "1"}};

                StringBuilder dataGetAllTempStr = new StringBuilder();
                for (int i = 0; i < dataGetAll.length - 1; i++) {
                    for (int j = 0; j < dataGetAll[i].length; j++) {
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
                    for (int j = 0; j < resultGetAll[i].length; j++) {
                        resultGetAllTempStr.append(resultGetAll[i][j]);
                    }
                }
                resultGetAllString = resultGetAllTempStr.toString();

                StringBuilder resultGetOneTempStr = new StringBuilder();
                for (int i = 0; i < resultGetOne.length; i++) {
                    for (int j = 0; j < resultGetOne[i].length; j++) {
                        resultGetOneTempStr.append(resultGetOne[i][j]);
                    }
                }
                resultGetOneString = resultGetOneTempStr.toString();


                assertEquals(resultGetAllString, dataGetAllString);
                assertEquals(resultGetOneString, dataGetOneString);
            } else {
                fail();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
            fail();
        }
    }

    /**
     * Test Update Event from Event Controller
     */
    @Test
    public void test6_2() {
        try {
            if (Auth.login("david", "1234")) {

                Event eventInfo = new Event("Event 1", "2023-11-27", "1", "Join the event 1");
                Event.modifyEvent(eventInfo, "1");

                String dataString = "";
                String resultString = "";

                String[] data = Event.getOneEvent("1");

                String[] result = new String[]{"1", "2", "Event 1", "Join the event 1", "2023-11-27", "1"};

                StringBuilder dataTempStr = new StringBuilder();
                for (int i = 0; i < data.length; i++) {
                    dataTempStr.append(data[i]);
                }
                dataString = dataTempStr.toString();

                StringBuilder resultTempStr = new StringBuilder();
                for (int i = 0; i < result.length; i++) {
                    resultTempStr.append(result[i]);
                }
                resultString = resultTempStr.toString();

                assertEquals(resultString, dataString);
            } else {
                fail();
            }
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
            if (Auth.login("david", "1234")) {
                Event.removeEvent("2");
                Event.removeEvent("3");

                String dataString = "";
                String resultString = "";

                String[][] data = Event.getAllEvents();
                String[][] result = new String[][]{{"1", "2", "Event 1", "Join the event 1", "2023-11-27", "1"}};

                StringBuilder dataTempStr = new StringBuilder();
                for (int i = 0; i < data.length - 1; i++) {
                    for (int j = 0; j < data[i].length; j++) {
                        dataTempStr.append(data[i][j]);
                    }
                }
                dataString = dataTempStr.toString();

                StringBuilder resultTempStr = new StringBuilder();
                for (int i = 0; i < result.length; i++) {
                    for (int j = 0; j < result[i].length; j++) {
                        resultTempStr.append(result[i][j]);
                    }
                }
                resultString = resultTempStr.toString();

                assertEquals(resultString, dataString);
            } else {
                fail();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
            fail();
        }
    }

    /**
     * Test Search events from Event Controller
     */
    @Test
    public void test6_4() {
        try {
            if (Auth.login("david", "1234")) {
                Search.search("event 1", "events");

                String dataString = "";
                String resultString = "";

                String[][] data = Event.getAllEvents();

                String[][] result = new String[][]{{"1", "2", "Event 1", "Join the event 1", "2023-11-27", "1"}};

                StringBuilder dataTempStr = new StringBuilder();
                for (int i = 0; i < data.length - 1; i++) {
                    for (int j = 0; j < data[i].length; j++) {
                        dataTempStr.append(data[i][j]);
                    }
                }
                dataString = dataTempStr.toString();

                StringBuilder resultTempStr = new StringBuilder();
                for (int i = 0; i < result.length; i++) {
                    for (int j = 0; j < result[i].length; j++) {
                        resultTempStr.append(result[i][j]);
                    }
                }
                resultString = resultTempStr.toString();

                assertEquals(resultString, dataString);
            } else {
                fail();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
            fail();
        }
    }

    @Test
    public void test6_5() {
        try {
            if (Auth.login("david", "1234")) {
                Search.searchByDate("= 2023-11-27", "events");

                String dataString = "";
                String resultString = "";

                String[][] data = Event.getAllEvents();

                String[][] result = new String[][]{{"1", "2", "Event 1", "Join the event 1", "2023-11-27", "1"}};

                StringBuilder dataTempStr = new StringBuilder();
                for (int i = 0; i < data.length - 1; i++) {
                    for (int j = 0; j < data[i].length; j++) {
                        dataTempStr.append(data[i][j]);
                    }
                }
                dataString = dataTempStr.toString();

                StringBuilder resultTempStr = new StringBuilder();
                for (int i = 0; i < result.length; i++) {
                    for (int j = 0; j < result[i].length; j++) {
                        resultTempStr.append(result[i][j]);
                    }
                }
                resultString = resultTempStr.toString();

                assertEquals(resultString, dataString);
            } else {
                fail();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
            fail();
        }
    }

    @Test
    public void test6_6() {
        try {
            if (Auth.login("david", "1234")) {
                Event eventInfo4 = new Event("Event 4", "2023-12-01", "1", "Join the event 4");
                Event.createEvent(eventInfo4);

                Search.searchByDate("= 2023-11-27", "events");

                String dataString = "";
                String resultString = "";

                String[][] data = Event.getAllEvents();

                String[][] result = new String[][]{{"1", "2", "Event 1", "Join the event 1", "2023-11-27", "1"}};

                StringBuilder dataTempStr = new StringBuilder();
                for (int i = 0; i < data.length - 1; i++) {
                    for (int j = 0; j < data[i].length; j++) {
                        dataTempStr.append(data[i][j]);
                    }
                }
                dataString = dataTempStr.toString();

                StringBuilder resultTempStr = new StringBuilder();
                for (int i = 0; i < result.length; i++) {
                    for (int j = 0; j < result[i].length; j++) {
                        resultTempStr.append(result[i][j]);
                    }
                }
                resultString = resultTempStr.toString();

                assertEquals(resultString, dataString);
            } else {
                fail();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
            fail();
        }
    }

}