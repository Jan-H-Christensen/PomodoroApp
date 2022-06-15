package Controller;

import DB.DBHandler;
import Data.Employee;
import PomodoroApp.Main;
import PomodoroApp.SceneController;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SettingsController extends Controller{

    @FXML
    public Label currentPasswordError,newPasswordError,confirmPasswordError,message;
    @FXML
    public PasswordField currentPassword,newPassword,confirmPassword;
    @FXML
    public CheckBox darkMode;

    @FXML
    public TextField username;
    /**
     *
     */
    @Override
    public void initialize() {
        super.initialize();

        darkMode.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            if (isSelected) {
                for(SceneController sceneStyle: Main.getSceneControllers()) {
                    sceneStyle.getScene().getStylesheets().remove("/AppStyle/BlueStyle.css");
                    sceneStyle.getScene().getStylesheets().add("/AppStyle/DarkMode.css");
                }
            } if(wasSelected) {
                for(SceneController sceneStyle: Main.getSceneControllers()){
                    sceneStyle.getScene().getStylesheets().remove("/AppStyle/DarkMode.css");
                    sceneStyle.getScene().getStylesheets().add("/AppStyle/BlueStyle.css");
                }
            }
        });
    }
    /**
     *
     */
    @FXML
    private void changePassword(){

        boolean userCheck = DBHandler.passwordCheck(username.getText(),currentPassword.getText());

        if(userCheck) {
            if (newPassword.getText().equals(confirmPassword.getText())) {
                DBHandler.changePassword(Employee.getEmpID(), currentPassword.getText(), newPassword.getText(), confirmPassword.getText());
                clean();
                message.setText("Password has been change");
            } else {
                confirmPasswordError.setText("confirm password matches not the new password");
                newPasswordError.setText("new password matches not the confirm password");
            }
        }else{
            currentPasswordError.setText("Wrong username or password");
        }
    }
    /**
     *
     */
    @FXML
    private void clean(){
        currentPassword.clear();
        newPassword.clear();
        confirmPassword.clear();
        confirmPasswordError.setText("");
        newPasswordError.setText("");
        currentPasswordError.setText("");
    }

    @Override
    public void logOut() {
        super.logOut();
    }

    @Override
    public void toDoListScene() {
        super.toDoListScene();
    }

    @Override
    public void adminStartScene() {
        super.adminStartScene();
    }

    @Override
    public void PomodoroStartScene() {
        super.PomodoroStartScene();
    }

    @Override
    public void projectScene() {
        super.projectScene();
    }

    @Override
    public void settingsScene() {
        super.settingsScene();
    }

    @Override
    public void minimize() {
        super.minimize();
    }
}
