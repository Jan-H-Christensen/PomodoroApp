package Controller;

import DB.DBHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController extends Controller{
    @FXML
    public TextField username,password;

    @FXML
    public Button btnCloseApp,btnMinimize;
    @FXML
    public Label errorText;

    /**
     * here we override initialize, to clare the method, sins it is not use in this controller
     */
    @Override
    public void initialize() {

    }

    /**
     * here we log in to the system by confirming, if the user exists inside the database, otherwise we give the user and error
     */
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

    /**
     * here we get the stage, and close the stage to close the program
     */
    public void close(){
        Stage stage = (Stage) btnCloseApp.getScene().getWindow();
        stage.close();
    }

    /**
     * here we get the stage, so we can minimize the scene
     */
    public void minimize(){
        Stage stage = (Stage) btnMinimize.getScene().getWindow();
        stage.setIconified(true);
    }
}
