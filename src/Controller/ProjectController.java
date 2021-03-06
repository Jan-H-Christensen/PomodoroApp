package Controller;


import DB.DBHandler;
import Data.DataHub;
import ObjectTypes.Project;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * @author NIels Rogengell
 * @version 1.0
 * @since 30-5-2022
 */

public class ProjectController extends Controller{

    @FXML
    public TextField name,time;

    @FXML
    public Label nameError,timeError;

    /**
     * Here in this method we create a project object, if the textFields are not empty, and not already taken
     */
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
                clear();
            }
            else {
                time.clear();
                timeError.setText("Full numbers on minutes only");
            }
        }
    }
    /**
     * used to clean the stage
     */
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

}