package Controller;


import DB.DBHandler;
import Data.DataHub;
import ObjectTypes.Project;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ProjectController extends Controller{

    @FXML
    public TextField name,time;

    @FXML
    public Label nameError,timeError;


    public void createProject(){
        final int createdProjectCheck = 0;
        final int standardNumberWorkOn = 1;
        final String standardStatus = "Free";

        if(name.getText().isEmpty()){
            name.clear();
            nameError.setText("Insert Name");
        }
        else if (DBHandler.checkTaskName(name.getText()))
        {
            name.clear();
            nameError.setText("Already Taken");
        }
        else
        {
            nameError.setText("");

            if (time.getText().matches("\\d*") && !time.getText().isEmpty()){
                DataHub.getToDoList().add(
                        new Project(createdProjectCheck,Integer.parseInt(time.getText()),standardNumberWorkOn,name.getText(),standardStatus));

            }
            else {
                time.clear();
                timeError.setText("Full numbers on minutes only");
            }
        }
    }

    public void clear(){
        name.clear();
        time.clear();
        nameError.setText("");
        timeError.setText("");
    }

    @Override
    public void initialize() {
        super.initialize();
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
    public void projectScene() {
        super.projectScene();
    }

    @Override
    public void minimize() {
        super.minimize();
    }
}