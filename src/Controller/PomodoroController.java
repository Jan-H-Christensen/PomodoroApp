package Controller;

import Data.Pomodoro;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PomodoroController extends Controller{

    @FXML
    public Label name,estimateTime,EstimatedPomodoro,status,workTimeError,breakTimeError,mainError;

    @FXML
    public TextField workTime,breakTime;

    @FXML
    public CheckBox musicChecker;
    /**
     * This initializes also does the super initialize, but it also resets the error labels and it hase 2 events
     * 1. is the toDoList.setOnMouseClicked(mouseEvent -> ) that does the updateLabel method, when toy click in the tableview
     * 2. is the  workTime.setOnKeyReleased(keyEvent -> ) that does the same but, the method is called when you press a key, when you are inside the textField
     */
    @Override
    public void initialize() {

        super.initialize();
        workTimeError.setText("");
        breakTimeError.setText("");
        mainError.setText("");

        toDoList.setOnMouseClicked(mouseEvent -> updateLabel());

        workTime.setOnKeyReleased(keyEvent -> updateLabel());
    }
    /**
     *
     */
    private void updateLabel(){
        final int standardPomodoro = 25;
        if (!toDoList.getSelectionModel().isEmpty()) {
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
    /**
     *
     */
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

                    if (musicChecker.isSelected()){
                        Pomodoro.setSound(true);
                    }

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
    /**
     *
     */
    @Override
    public void remove() {
        name.setText("");
        estimateTime.setText("");
        EstimatedPomodoro.setText("");
        status.setText("");
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
        remove();
        super.PomodoroStartScene();
    }

    @Override
    public void projectScene() {
        super.projectScene();
    }

    @Override
    public void pomodoroProgressScene() {
        super.pomodoroProgressScene();
    }
}
