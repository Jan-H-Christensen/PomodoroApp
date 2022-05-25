package Controller;

import DB.DBHandler;
import Data.DataHub;
import ObjectTypes.Project;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PomodoroController implements ControllerInterface{

    @FXML
    public Button btnToDoList, btnProject, btnPomodoro, btnAdmin, btnSettings;

    public void logOut(){
        //TODO clean();
        for (Project p : DataHub.getToDoList()){
            DBHandler.freeTask(p.getTaskId());
        }
        DataHub.getToDoList().clear();
        DBHandler.disconnect();
        logoutScene();
    }

    @Override
    public void toDoListScene() {
        ControllerInterface.super.toDoListScene();
    }

    @Override
    public void adminStartScene() {
        ControllerInterface.super.adminStartScene();
    }

    @Override
    public void projectScene() {
        ControllerInterface.super.projectScene();
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
