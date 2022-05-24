package PomodoroApp;

import javafx.scene.Scene;

public class SceneController {

    private Scene scene;
    private ControllerName name;

    public SceneController(Scene scene, ControllerName name) {
        this.scene = scene;
        this.name = name;
    }

    public Scene getScene() {
        return scene;
    }

    public ControllerName getName() {
        return name;
    }
}
