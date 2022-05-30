package Controller;

import DB.DBHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController extends Controller{
    @FXML
    public TextField username,password;

    @FXML
    public Label errorText;

    @Override
    public void initialize() {

    }
    public void login(){

        boolean loginCheck = DBHandler.connect(username.getText(),password.getText());

        password.clear();
        if (loginCheck){
            username.clear();
            errorText.setText("");
            super.toDoListScene();
        }
        else
        {
            errorText.setText("Wrong username or password");
        }
    }
}
