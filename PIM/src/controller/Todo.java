package controller;

public class Todo {
    private String taskName;
    private String taskDDL;
    private String taskDescription;
    private Boolean isTaskComplete;

    /**
     * Todo Contract
     * @param taskName: the name of task
     * @param taskDDL: the deadline of task
     * @param taskDescription: the description of task
     * @param isTaskComplete: the task is complete or not
     */
    public Todo(String taskName, String taskDDL, String taskDescription, Boolean isTaskComplete) {
        this.taskName = taskName;
        this.taskDDL = taskDDL;
        this.taskDescription = taskDescription;
        this.isTaskComplete = isTaskComplete;
    }

    /**
     * Create Task Function
     */
    private void createTask() {

    }

    /**
     * Get All Tasks Function
     */
    private void getAllTasks() {

    }

    /**
     * Get One Task Function
     */
    private void getOneTask() {

    }

    /**
     * Modify Task Function
     */
    private void modifyTask() {

    }

    /**
     * Remove Task Function
     */
    private void removeTask() {

    }

    /**
     * Task Complete or not Function
     * @return Boolean: the task is complete or not
     */
    private Boolean taskComplete() {
        return true;
    }

}
