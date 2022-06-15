package PomodoroApp;

import javafx.scene.Scene;

public class SceneController {

    private Scene scene;
    private ControllerName name;

    /**
     * constructor for the object
     * @param scene the scene object
     * @param name the enum name for the scene
     */
    public SceneController(Scene scene, ControllerName name) {
        this.scene = scene;
        this.name = name;
    }

    /**
     * get the scene
     * @return the scene
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * get the name
     * @return enum name of the scene
     */
    public ControllerName getName() {
        return name;
    }
}
