package Data;

import ObjectTypes.Employee;
import ObjectTypes.Project;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataHub {

    private static ObservableList<Project> projectList = FXCollections.observableArrayList();
    private static Property<ObservableList<Project>> listProperty = new SimpleListProperty<>(projectList);

    public static Property<ObservableList<Project>> getListProperty() {
        return listProperty;
    }
    public static ObservableList<Project> getProjectList() {
        return projectList;
    }


    private static ObservableList<Project> toDoList = FXCollections.observableArrayList();
    private static Property<ObservableList<Project>> toDoListProperty = new SimpleListProperty<>(toDoList);

    public static ObservableList<Project> getToDoList() {
        return toDoList;
    }
    public static Property<ObservableList<Project>> getToDoListProperty() {
        return toDoListProperty;
    }

    private static Employee employee = null;
    public static Employee getEmployee() {
        return employee;
    }
    public static void setEmployee(Employee employee) {
        DataHub.employee = employee;
    }
}