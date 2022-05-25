package Controller;

import DB.DBHandler;
import PomodoroApp.ControllerName;
import PomodoroApp.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController{
    @FXML
    public TextField username,password;

    @FXML
    public Label errorText;

    public void login(){

        boolean loginCheck = DBHandler.connect(username.getText(),password.getText());

        username.clear();
        password.clear();
        if (loginCheck){
            errorText.setText("");
            DBHandler.setProjects();
            toDoListScene();
        }
        else
        {
            errorText.setText("Wrong username or password");
        }
    }
    
    public void toDoListScene() {
        Main.changeScene(ControllerName.ToDoList);
    }
}
