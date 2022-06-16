package Data;

import ObjectTypes.Project;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author NIels Rogengell
 * @version 1.0
 * @since 30-5-2022
 */

/**
 * Here we store the data of the pomodoro that the user ,is currently using, so that we can create the right pomodoro.
 * String name; for the project name
 * String estimateTime; for the project estimate time
 * String estimatePomodoro; for the estimated pomodoro it shut take
 * String status; for the project current status
 * String workTime; for the pomodoro work time
 * String breakTIme; for the pomodoro break time
 * String currentPomodoro; the current pomodoro the user is at
 * Project project; the project object, to update the project in the database
 * int toDoListID; the todolist in the database id, to update the database
 * boolean sound; a boolean to confirm if the user wants to have sound
 * The values and methods are static
 * There is also the clear method, where we clear all values in the class
 */
public class Pomodoro {
    private static String name;
    private static String estimateTime;
    private static String estimatePomodoro;
    private static String status;
    private static String workTime;
    private static String breakTIme;
    private static String currentPomodoro;
    private static Project project;
    private static int toDoListID;
    private static boolean sound;

    private static StringProperty nameProperty = new SimpleStringProperty("");;
    private static StringProperty estimateTimeProperty = new SimpleStringProperty("");;
    private static StringProperty estimatePomodoroProperty = new SimpleStringProperty("");;
    private static StringProperty statusProperty = new SimpleStringProperty("");;
    private static StringProperty workTimeProperty = new SimpleStringProperty("");;
    private static StringProperty breakTImeProperty = new SimpleStringProperty("");
    private static StringProperty currentPomodoroProperty = new SimpleStringProperty("");

    public static void clear(){
        setName("");
        setEstimateTime("");
        setEstimatePomodoro("");
        setStatus("");
        setWorkTime("");
        setBreakTIme("");
        setCurrentPomodoro("0");
        setProject(null);
        setToDoListID(0);
        sound = false;
    }

    public static boolean isSound() {
        return sound;
    }

    public static void setSound(boolean sound) {
        Pomodoro.sound = sound;
    }

    public static String getCurrentPomodoro() {
        return currentPomodoro;
    }

    public static void setCurrentPomodoro(String currentPomodoro1) {
        currentPomodoroProperty.set(currentPomodoro1);
        currentPomodoro = currentPomodoro1;
    }

    public static String getCurrentPomodoroProperty() {
        return currentPomodoroProperty.get();
    }

    public static StringProperty currentPomodoroPropertyProperty() {
        return currentPomodoroProperty;
    }

    public static int getToDoListID() {
        return toDoListID;
    }

    public static void setToDoListID(int toDoListID) {
        Pomodoro.toDoListID = toDoListID;
    }

    public static Project getProject() {
        return project;
    }

    public static void setProject(Project project1) {
        project = project1;
    }

    public static String getNameProperty() {
        return nameProperty.get();
    }

    public static StringProperty namePropertyProperty() {
        return nameProperty;
    }

    public static String getEstimateTimeProperty() {
        return estimateTimeProperty.get();
    }

    public static StringProperty estimateTimePropertyProperty() {
        return estimateTimeProperty;
    }

    public static String getEstimatePomodoroProperty() {
        return estimatePomodoroProperty.get();
    }

    public static StringProperty estimatePomodoroPropertyProperty() {
        return estimatePomodoroProperty;
    }

    public static String getStatusProperty() {
        return statusProperty.get();
    }

    public static StringProperty statusPropertyProperty() {
        return statusProperty;
    }

    public static String getWorkTimeProperty() {
        return workTimeProperty.get();
    }

    public static StringProperty workTimePropertyProperty() {
        return workTimeProperty;
    }

    public static String getBreakTImeProperty() {
        return breakTImeProperty.get();
    }

    public static StringProperty breakTImePropertyProperty() {
        return breakTImeProperty;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name1) {
        name = name1;
        nameProperty.set(name1);
    }

    public static String getEstimateTime() {
        return estimateTime;
    }

    public static void setEstimateTime(String estimateTime1) {
        estimateTime = estimateTime1;
        estimateTimeProperty.set(estimateTime1);
    }

    public static String getEstimatePomodoro() {
        return estimatePomodoro;
    }

    public static void setEstimatePomodoro(String estimatePomodoro1) {
        estimatePomodoro = estimatePomodoro1;
        estimatePomodoroProperty.set(estimatePomodoro1);
    }

    public static String getStatus() {
        return status;
    }

    public static void setStatus(String status1) {
        status = status1;
        statusProperty.set(status1);
    }

    public static String getWorkTime() {
        return workTime;
    }

    public static void setWorkTime(String workTime1) {
        workTime = workTime1;
        workTimeProperty.set(workTime1);
    }

    public static String getBreakTIme() {
        return breakTIme;
    }

    public static void setBreakTIme(String breakTIme1) {
        breakTIme = breakTIme1;
        breakTImeProperty.set(breakTIme1);
    }
}
