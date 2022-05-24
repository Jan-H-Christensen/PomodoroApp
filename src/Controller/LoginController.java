package Controller;

import PomodoroApp.ControllerName;
import PomodoroApp.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LoginController implements ControllerInterface{

   @FXML
   public Button login, cancel;

    @Override
    public void toDoListScene() {
        ControllerInterface.super.toDoListScene();
    }
    @FXML
    public void huhu(){
        Main.changeScene(ControllerName.ToDoList);
    }
}
