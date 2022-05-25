package Controller;


import PomodoroApp.ControllerName;
import PomodoroApp.Main;
import javafx.fxml.FXML;


public interface ControllerInterface {
    @FXML
    public default void toDoListScene(){
        Main.changeScene(ControllerName.ToDoList);
    }
    @FXML
    public default void adminStartScene(){
        Main.changeScene(ControllerName.AdminStart);
    }
    @FXML
    public default void PomodoroStartScene(){
        Main.changeScene(ControllerName.PomodoroStart);
    }
    @FXML
    public default void projectScene(){
        Main.changeScene(ControllerName.Project);
    }
    @FXML
    public default void adminCreateScene(){
        Main.changeScene(ControllerName.AdminCreate);
    }
    @FXML
    public default void adminDeleteScene(){
        Main.changeScene(ControllerName.AdminDelete);
    }
    @FXML
    public default void adminEditScene(){
        Main.changeScene(ControllerName.AdminEdit);
    }
    @FXML
    public default void pomodoroProgressScene(){
        Main.changeScene(ControllerName.PomodoroProgress);
    }
    @FXML
    public default void logoutScene(){
        Main.changeScene(ControllerName.Login);
    }

}
