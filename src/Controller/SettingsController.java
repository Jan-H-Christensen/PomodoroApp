package Controller;

import PomodoroApp.Main;
import PomodoroApp.SceneController;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

public class SettingsController extends Controller{

    @FXML
    public CheckBox darkMode;

    @Override
    public void initialize() {
        super.initialize();


        darkMode.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            if (isSelected) {
                for(SceneController sceneStyle: Main.getSceneControllers()) {
                    sceneStyle.getScene().getStylesheets().remove("/AppStyle/LightMode.css");
                    sceneStyle.getScene().getStylesheets().add("/AppStyle/DarkMode.css");
                }
            } else {
                for(SceneController sceneStyle: Main.getSceneControllers()){
                    sceneStyle.getScene().getStylesheets().remove("/AppStyle/DarkMode.css");
                    sceneStyle.getScene().getStylesheets().add("/AppStyle/LightMode.css");
                }
            }
        });
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
}
