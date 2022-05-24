package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdminController implements ControllerInterface{

    @FXML
    public Button btnToDoList, btnProject, btnPomodoro, btnAdmin, btnSettings, btnCreate, btnDelete, btnEdit;

    @Override
    public void toDoListScene() {
        ControllerInterface.super.toDoListScene();
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
    public void adminCreateScene() {
        ControllerInterface.super.adminCreateScene();
    }

    @Override
    public void adminDeleteScene() {
        ControllerInterface.super.adminDeleteScene();
    }

    @Override
    public void adminEditScene() {
        ControllerInterface.super.adminEditScene();
    }

    @Override
    public void pomodoroProgressScene() {
        ControllerInterface.super.pomodoroProgressScene();
    }

    @Override
    public void logoutScene() {
        ControllerInterface.super.logoutScene();
    }
}
