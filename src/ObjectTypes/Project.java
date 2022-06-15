package ObjectTypes;
/**
 * This class stores date for the individual project
 * int taskId;
 * int estimatedTime;
 * int numberWorkOn;
 * String taskName;
 * String status;
 * This class is non-static class, because it is intended to be used as an object
 */
public class Project {

    private int taskId;
    private int estimatedTime;
    private int numberWorkOn;
    private String taskName;
    private String status;

    public Project(int taskId, int estimatedTime, int numberWorkOn, String taskName, String status) {
        this.taskId = taskId;
        this.estimatedTime = estimatedTime;
        this.numberWorkOn = numberWorkOn;
        this.taskName = taskName;
        this.status = status;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getTaskId() {
        return taskId;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public int getNumberWorkOn() {
        return numberWorkOn;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getStatus() {
        return status;
    }
}