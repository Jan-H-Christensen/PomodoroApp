package PomodoroApp;

import DB.DBHandler;
import Data.DataHub;
import ObjectTypes.Project;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class Main extends Application {

    private static Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    private static double screenX = screenBounds.getMaxX();
    private static double screenY = screenBounds.getMaxY();

    private static ArrayList<SceneController> sceneControllers = new ArrayList<>();

    private static Stage stageHandler;
    @Override
    public void start(Stage stage) throws Exception {

        stageHandler = stage;

        FXMLLoader loginSearchLoader = new FXMLLoader(getClass().getResource("/GUI/FXLogin.fxml"));
        Parent loginSearchPane = loginSearchLoader.load();
        Scene loginSearchScene = new Scene(loginSearchPane,450,400);
        loginSearchScene.getStylesheets().add("/AppStyle/BlueStyle.css");


        FXMLLoader toDoListLoader = new FXMLLoader(getClass().getResource("/GUI/FXToDoList.fxml"));
        Parent toDoListPane = toDoListLoader.load();
        Scene toDoListScene = new Scene(toDoListPane,1200,700);
        toDoListScene.getStylesheets().add("/AppStyle/BlueStyle.css");

        FXMLLoader projectLoader = new FXMLLoader(getClass().getResource("/GUI/FXProject.fxml"));
        Parent projectPane = projectLoader.load();
        Scene projectScene = new Scene(projectPane,1200,700);
        projectScene.getStylesheets().add("/AppStyle/BlueStyle.css");

        FXMLLoader pomodoroStartLoader = new FXMLLoader(getClass().getResource("/GUI/FXPomodoroStart.fxml"));
        Parent pomodoroStartPane = pomodoroStartLoader.load();
        Scene pomodoroStartScene = new Scene(pomodoroStartPane,1200,700);
        pomodoroStartScene.getStylesheets().add("/AppStyle/BlueStyle.css");

        FXMLLoader pomodoroProLoader = new FXMLLoader(getClass().getResource("/GUI/FXPomodoroProgress.fxml"));
        Parent pomodoroProPane = pomodoroProLoader.load();
        Scene pomodoroProScene = new Scene(pomodoroProPane,1200,700);
        pomodoroProScene.getStylesheets().add("/AppStyle/BlueStyle.css");

        FXMLLoader adminStartLoader = new FXMLLoader(getClass().getResource("/GUI/FXAdminStart.fxml"));
        Parent adminStartPane = adminStartLoader.load();
        Scene adminStartScene = new Scene(adminStartPane,1200,700);
        adminStartScene.getStylesheets().add("/AppStyle/BlueStyle.css");

        FXMLLoader adminCreateLoader = new FXMLLoader(getClass().getResource("/GUI/FXAdminCreate.fxml"));
        Parent adminCreatePane = adminCreateLoader.load();
        Scene adminCreateScene = new Scene(adminCreatePane,1200,700);
        adminCreateScene.getStylesheets().add("/AppStyle/BlueStyle.css");

        FXMLLoader adminDeleteLoader = new FXMLLoader(getClass().getResource("/GUI/FXAdminDelete.fxml"));
        Parent adminDeletePane = adminDeleteLoader.load();
        Scene adminDeleteScene = new Scene(adminDeletePane,1200,700);
        adminDeleteScene.getStylesheets().add("/AppStyle/BlueStyle.css");

        FXMLLoader adminEditLoader = new FXMLLoader(getClass().getResource("/GUI/FXAdminEdit.fxml"));
        Parent adminEditPane = adminEditLoader.load();
        Scene adminEditScene = new Scene(adminEditPane,1200,700);
        adminEditScene.getStylesheets().add("/AppStyle/BlueStyle.css");

        FXMLLoader settingLoader = new FXMLLoader(getClass().getResource("/GUI/FXSettings.fxml"));
        Parent settingPane = settingLoader.load();
        Scene settingsScene = new Scene(settingPane,1200,700);
        settingsScene.getStylesheets().add("/AppStyle/BlueStyle.css");

        sceneControllers.add(new SceneController(loginSearchScene,ControllerName.Login));
        sceneControllers.add(new SceneController(toDoListScene,ControllerName.ToDoList));
        sceneControllers.add(new SceneController(projectScene,ControllerName.Project));
        sceneControllers.add(new SceneController(adminStartScene,ControllerName.AdminStart));
        sceneControllers.add(new SceneController(adminCreateScene,ControllerName.AdminCreate));
        sceneControllers.add(new SceneController(adminDeleteScene,ControllerName.AdminDelete));
        sceneControllers.add(new SceneController(adminEditScene,ControllerName.AdminEdit));
        sceneControllers.add(new SceneController(pomodoroStartScene,ControllerName.PomodoroStart));
        sceneControllers.add(new SceneController(pomodoroProScene,ControllerName.PomodoroProgress));
        sceneControllers.add(new SceneController(settingsScene,ControllerName.Settings));

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (!DataHub.getToDoList().isEmpty()){
                for (Project p : DataHub.getToDoList()){
                    DBHandler.freeTask(p.getTaskId());
                }
                DataHub.getToDoList().clear();
            }
            DBHandler.disconnect();
        }));

        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(loginSearchScene);
        setSceneLocation();
        stage.setTitle("Login");
        stage.show();
    }

    public static void changeScene(ControllerName sceneName){

        stageHandler.setTitle(sceneName.toString());

        for(SceneController sceneConPairName : sceneControllers){
            if(sceneConPairName.getName().equals(sceneName)){
                stageHandler.setScene(sceneConPairName.getScene());
                stageHandler.setTitle(sceneConPairName.getName().toString());
            }
        }
    }
    public static void setSceneLocation(){
        setStageLocation((screenX/2)-(stageHandler.getScene().getWidth()/2),(screenY/2)-(stageHandler.getScene().getHeight()/2));
    }

    public static void setStageLocation(double x, double y){
        stageHandler.setX(x);
        stageHandler.setY(y);
    }

    public static ArrayList<SceneController> getSceneControllers() {
        return sceneControllers;
    }
}
