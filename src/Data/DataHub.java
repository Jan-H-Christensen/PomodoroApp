package Data;

import ObjectTypes.Project;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataHub {
    /**
     * here we store date for the project list
     */
    private static ObservableList<Project> projectList = FXCollections.observableArrayList();
    private static Property<ObservableList<Project>> listProperty = new SimpleListProperty<>(projectList);
    /**
     * here we get data from the project list or set data into the project list
     */
    public static Property<ObservableList<Project>> getListProperty() {
        return listProperty;
    }
    public static ObservableList<Project> getProjectList() {
        return projectList;
    }


    /**
     * her we have a checker, to make sure we are inside the right scene
     */
    private static int listenerChecker = 0;
    public static int getListenerChecker() {
        return listenerChecker;
    }
    public static void setListenerChecker(int listenerChecker) {
        DataHub.listenerChecker = listenerChecker;
    }

    /**
     *  here we store the date for the toDoList, and create a SimpleListProperty
     */
    private static ObservableList<Project> toDoList = FXCollections.observableArrayList();
    private static Property<ObservableList<Project>> toDoListProperty = new SimpleListProperty<>(toDoList);
    /**
     * here we have a getter for the list and the list property
     */
    public static ObservableList<Project> getToDoList() {
        return toDoList;
    }
    public static Property<ObservableList<Project>> getToDoListProperty() {
        return toDoListProperty;
    }

}