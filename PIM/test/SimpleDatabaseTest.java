import org.junit.*;
import org.junit.jupiter.api.Test;

public class SimpleDatabaseTest {

    @Test
    public void testGetNewID() {
        int id = SimpleDatabase.getNewID("events.csv");
        Assert.assertEquals(1, id);
    }

    @Test
    public void testGet() {
        String[][] data = SimpleDatabase.get("events.csv");
        Assert.assertEquals(1, data.length);
    }

    @Test
    public void testInsert() {
        String[][] data = {{"1", "1", "test", "test", "test", "test"}};
        SimpleDatabase.insert("events.csv", data);
        String[][] newData = SimpleDatabase.get("events.csv");
        Assert.assertEquals(2, newData.length);
    }

    @Test
    public void testUpdate() {
        String[][] data = {{"1", "1", "test", "test", "test", "test"}};
        SimpleDatabase.update("events.csv", data);
        String[][] newData = SimpleDatabase.get("events.csv");
        Assert.assertEquals(1, newData.length);
    }

    @Test
    public void testDelete() {
        String[][] data = {{"1", "1", "test", "test", "test", "test"}};
        SimpleDatabase.delete("events.csv", data);
        String[][] newData = SimpleDatabase.get("events.csv");
        Assert.assertEquals(0, newData.length);
    }
}