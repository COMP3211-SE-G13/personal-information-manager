package controller;

import model.SimpleDatabase;

import static controller.Auth.getUserId;

public class Todo {
    private String taskName;
    private String taskDDL;
    private String taskDescription;
    private int userId;

    /**
     * Todo Contract
     *
     * @param taskName:        the name of task
     * @param taskDDL:         the deadline of task
     * @param taskDescription: the description of task
     */
    public Todo(String taskName, String taskDDL, String taskDescription) {
        this.taskName = taskName;
        this.taskDDL = taskDDL;
        this.taskDescription = taskDescription;
        this.userId = getUserId();
    }

    public static void createTask(Todo taskInfo) {
        createTask(taskInfo.taskName, taskInfo.taskDDL, taskInfo.taskDescription, taskInfo.userId);
    }

    /**
     * Create Task Function
     */
    private static void createTask(String taskName, String taskDDL, String taskDescription, int userId) {
        try {
            if (taskName.isEmpty() || taskDDL.isEmpty() || taskDescription.isEmpty()) {
                System.out.println("Please enter all information!");
                return;
            }

            int taskId = SimpleDatabase.getNewID("tasks.csv");

            String[][] newTaskData = {
                    {String.valueOf(taskId), String.valueOf(userId), taskName, taskDDL, taskDescription}
            };

            new SimpleDatabase("insert", "tasks.csv", newTaskData);

        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
        }
    }

    /**
     * Get All Tasks Function
     */
    public static String[][] getAllTasks() {
        try {
            String[][] data = SimpleDatabase.get("tasks.csv");
            return data;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
        }
        return null;
    }

    /**
     * Get One Task Function
     *
     * @param contactId: the id of task
     * @return String[]: the task data
     */
    public static String[] getOneTask(String contactId) {
        try {
            String[][] data = SimpleDatabase.get("tasks.csv");

            for (int i = 0; i < data.length; i++) {
                if (data[i][0].equals(contactId) & data[i][1].equals(String.valueOf(Auth.getUserId()))) {
                    return data[i];
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
            return null;
        }

        return null;
    }

    /**
     * Modify Task Function
     */
    private void modifyTask(String taskName, String taskDDL, String taskDescription, int userId, int taskId) {
        try {
            String[] dataWantUpdate = {String.valueOf(taskId), String.valueOf(userId), taskName, taskDDL, taskDescription};

            new SimpleDatabase("update", "tasks.csv", taskId, dataWantUpdate);
            System.out.println("Update Successfully!");

        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
        }
    }


    public static void removeTask(String taskId) {
        removeTask(Integer.parseInt(taskId));
    }

    /**
     * Remove Task Function
     */
    private static void removeTask(int taskId) {
        try {
            new SimpleDatabase("remove", "todo.csv", Auth.getUserId(), taskId);
            System.out.println("Remove Successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again!");
        }
    }

}