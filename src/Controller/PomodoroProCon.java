package Controller;

import ObjectTypes.Pomodoro;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PomodoroProCon extends Controller{

    @FXML
    public Label name,estimateTime,EstimatedPomodoro,status,workTime,breakTime,currentPomodoro;

    @Override
    public void initialize() {
        super.initialize();
        name.textProperty().bindBidirectional(Pomodoro.namePropertyProperty());
        estimateTime.textProperty().bindBidirectional(Pomodoro.estimateTimePropertyProperty());
        EstimatedPomodoro.textProperty().bindBidirectional(Pomodoro.estimatePomodoroPropertyProperty());
        status.textProperty().bindBidirectional(Pomodoro.statusPropertyProperty());
        workTime.textProperty().bindBidirectional(Pomodoro.workTimePropertyProperty());
        breakTime.textProperty().bindBidirectional(Pomodoro.breakTImePropertyProperty());
        currentPomodoro.setText("0");
    }

    @Override
    public void remove() {
        super.remove();
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
    public void settingsScene() {
        super.settingsScene();
    }

    @Override
    public void minimize() {
        super.minimize();
    }
}
