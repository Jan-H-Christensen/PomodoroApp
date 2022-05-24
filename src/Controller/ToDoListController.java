package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ToDoListController implements ControllerInterface{

    @FXML
    public Button btnToDoList, btnProject, btnPomodoro, btnAdmin, btnSettings;

    @Override
    public void adminStartScene() {
        ControllerInterface.super.adminStartScene();
    }

    @Override
    public void PomodoroStartScene() {
        ControllerInterface.super.PomodoroStartScene();
    }

    @Override
    public void projectScene() {
        ControllerInterface.super.projectScene();
    }

    @Override
    public void logoutScene() {
        ControllerInterface.super.logoutScene();
    }
}
