package Controller;

import Data.DataHub;
import ObjectTypes.Pomodoro;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PomodoroController extends Controller{

    @FXML
    public Label name,estimateTime,EstimatedPomodoro,status,workTimeError,breakTimeError,mainError;

    @FXML
    public TextField workTime,breakTime;

    @Override
    public void initialize() {

        super.initialize();
        workTimeError.setText("");
        breakTimeError.setText("");
        mainError.setText("");

        toDoList.setOnMouseClicked(mouseEvent -> updateLabel());

        workTime.setOnKeyReleased(keyEvent -> updateLabel());
    }

    private void updateLabel(){
        final int standardPomodoro = 25;
        if (DataHub.getListenerChecker() == 0 && !toDoList.getSelectionModel().isEmpty()) {
            name.setText(toDoList.getSelectionModel().getSelectedItem().getTaskName());
            estimateTime.setText("" + toDoList.getSelectionModel().getSelectedItem().getEstimatedTime());
            if (workTime.getText().isEmpty()) {
                int estimated = toDoList.getSelectionModel().getSelectedItem().getEstimatedTime();
                EstimatedPomodoro.setText("" + (int) Math.ceil((double) estimated / standardPomodoro));
            } else {
                int estimated = toDoList.getSelectionModel().getSelectedItem().getEstimatedTime();
                int workTimeNum = Integer.parseInt(workTime.getText());
                EstimatedPomodoro.setText("" + (int) Math.ceil((double) estimated / workTimeNum));
            }
            status.setText(toDoList.getSelectionModel().getSelectedItem().getStatus());
        }
    }

    @FXML
    public void start(){
        if (workTime.getText().isEmpty()){
            workTimeError.setText("Enter number");
        }
        else if (!workTime.getText().matches("\\d*"))
        {
            workTimeError.setText("Number only");
        }
        else
        {
            if (breakTime.getText().isEmpty()) {
                breakTimeError.setText("Enter Number");
            }
            else if (!breakTime.getText().matches("\\d*")) {
                breakTimeError.setText("NumberOnly");
            }
            else
            {
                if (toDoList.getSelectionModel().isEmpty()){
                    mainError.setText("Select a project");
                }
                else
                {
                    Pomodoro.setName(name.getText());
                    Pomodoro.setEstimateTime(estimateTime.getText());
                    Pomodoro.setEstimatePomodoro(EstimatedPomodoro.getText());
                    Pomodoro.setStatus(status.getText());
                    Pomodoro.setWorkTime(workTime.getText());
                    Pomodoro.setBreakTIme(breakTime.getText());
                    Pomodoro.setProject(toDoList.getSelectionModel().getSelectedItem());
                    Pomodoro.setCurrentPomodoro("0");

                    workTime.clear();
                    breakTime.clear();
                    name.setText("");
                    estimateTime.setText("");
                    EstimatedPomodoro.setText("");
                    status.setText("");
                    workTimeError.setText("");
                    breakTimeError.setText("");
                    mainError.setText("");

                    toDoList.getSelectionModel().clearSelection();

                    this.pomodoroProgressScene();
                }
            }
        }
    }

    private void setListener(){
        DataHub.setListenerChecker(1);
    }

    @Override
    public void remove() {
        setListener();
        name.setText("");
        estimateTime.setText("");
        EstimatedPomodoro.setText("");
        status.setText("");
        super.remove();
    }

    @Override
    public void logOut() {
        setListener();
        super.logOut();
    }

    @Override
    public void toDoListScene() {
        setListener();
        super.toDoListScene();
    }

    @Override
    public void adminStartScene() {
        setListener();
        super.adminStartScene();
    }

    @Override
    public void PomodoroStartScene() {
        setListener();
        remove();
        super.PomodoroStartScene();
    }

    @Override
    public void projectScene() {
        setListener();
        super.projectScene();
    }

    @Override
    public void pomodoroProgressScene() {
        setListener();
        super.pomodoroProgressScene();
    }
}
