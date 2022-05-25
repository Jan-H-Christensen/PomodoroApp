package ObjectTypes;

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