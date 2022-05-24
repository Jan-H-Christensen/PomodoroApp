package PomodoroApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    private static ArrayList<SceneController> sceneControllers = new ArrayList<>();

    private static Stage stageHandler;
    @Override
    public void start(Stage stage) throws Exception {

        stageHandler = stage;

        FXMLLoader loginSearchLoader = new FXMLLoader(getClass().getResource("../GUI/FXLogin.fxml"));
        Parent loginSearchPane = loginSearchLoader.load();
        Scene loginSearchScene = new Scene(loginSearchPane,480,400);

        FXMLLoader toDoListLoader = new FXMLLoader(getClass().getResource("../GUI/FXToDoList.fxml"));
        Parent toDoListPane = toDoListLoader.load();
        Scene toDoListScene = new Scene(toDoListPane,1200,700);

        FXMLLoader projectLoader = new FXMLLoader(getClass().getResource("../GUI/FXProject.fxml"));
        Parent projectPane = projectLoader.load();
        Scene projectScene = new Scene(projectPane,1200,700);

        FXMLLoader pomodoroStartLoader = new FXMLLoader(getClass().getResource("../GUI/FXPomodoroStart.fxml"));
        Parent pomodoroStartPane = pomodoroStartLoader.load();
        Scene pomodoroStartScene = new Scene(pomodoroStartPane,1200,700);

        FXMLLoader pomodoroProLoader = new FXMLLoader(getClass().getResource("../GUI/FXPomodoroProgress.fxml"));
        Parent pomodoroProPane = pomodoroProLoader.load();
        Scene pomodoroProScene = new Scene(pomodoroProPane,1200,700);

        FXMLLoader adminStartLoader = new FXMLLoader(getClass().getResource("../GUI/FXAdminStart.fxml"));
        Parent adminStartPane = adminStartLoader.load();
        Scene adminStartScene = new Scene(adminStartPane,1200,700);

        FXMLLoader adminCreateLoader = new FXMLLoader(getClass().getResource("../GUI/FXAdminCreate.fxml"));
        Parent adminCreatePane = adminCreateLoader.load();
        Scene adminCreateScene = new Scene(adminCreatePane,1200,700);

        FXMLLoader adminDeleteLoader = new FXMLLoader(getClass().getResource("../GUI/FXAdminDelete.fxml"));
        Parent adminDeletePane = adminDeleteLoader.load();
        Scene adminDeleteScene = new Scene(adminDeletePane,1200,700);

        FXMLLoader adminEditLoader = new FXMLLoader(getClass().getResource("../GUI/FXAdminEdit.fxml"));
        Parent adminEditPane = adminEditLoader.load();
        Scene adminEditScene = new Scene(adminEditPane,1200,700);

        sceneControllers.add(new SceneController(loginSearchScene,ControllerName.Login));
        sceneControllers.add(new SceneController(toDoListScene,ControllerName.ToDoList));
        sceneControllers.add(new SceneController(projectScene,ControllerName.Project));
        sceneControllers.add(new SceneController(adminStartScene,ControllerName.AdminStart));
        sceneControllers.add(new SceneController(adminCreateScene,ControllerName.AdminCreate));
        sceneControllers.add(new SceneController(adminDeleteScene,ControllerName.AdminDelete));
        sceneControllers.add(new SceneController(adminEditScene,ControllerName.AdminEdit));
        sceneControllers.add(new SceneController(pomodoroStartScene,ControllerName.PomodoroStart));
        sceneControllers.add(new SceneController(pomodoroProScene,ControllerName.PomodoroProgress));

        stage.setResizable(false);
        stage.setScene(loginSearchScene);
        stage.show();
    }

    public static void changeScene(ControllerName sceneName){

        stageHandler.setTitle(sceneName.toString());

        for(SceneController sceneConPairName : sceneControllers){

            if(sceneConPairName.getName().equals(sceneName)){
                stageHandler.setScene(sceneConPairName.getScene());
            }
        }
    }
}
