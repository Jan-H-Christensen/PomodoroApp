package ObjectTypes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pomodoro {
    private static String name;
    private static String estimateTime;
    private static String estimatePomodoro;
    private static String status;
    private static String workTime;
    private static String breakTIme;

    private static StringProperty nameProperty = new SimpleStringProperty("");;
    private static StringProperty estimateTimeProperty = new SimpleStringProperty("");;
    private static StringProperty estimatePomodoroProperty = new SimpleStringProperty("");;
    private static StringProperty statusProperty = new SimpleStringProperty("");;
    private static StringProperty workTimeProperty = new SimpleStringProperty("");;
    private static StringProperty breakTImeProperty = new SimpleStringProperty("");


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