package Controller;

import DB.DBHandler;
import Data.DataHub;
import Data.Employee;
import ObjectTypes.Project;
import PomodoroApp.ControllerName;
import PomodoroApp.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * this is the main controller class where all other controller extender from
 * we have created a base GUI where only the center changes
 * @author Jan Christensen, NIels Rogengell
 * @version 1.0
 * @since 30.05.2022
 */

public class Controller {

    @FXML
    public Button btnMinimize;
    @FXML
    public Button btnAdmin;
    @FXML
    public TableView<Project> toDoList;
    /**
     * her we always loader To-Do-List listen for every Scene in the different controller classes
     */
    public void initialize(){
        TableColumn<Project, String> toDo1 = new TableColumn<>("TaskName");
        toDo1.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        toDo1.setPrefWidth(129);

        TableColumn<Project, String> toDo2 = new TableColumn<>("Time");
        toDo2.setCellValueFactory(new PropertyValueFactory<>("estimatedTime"));
        toDo2.setPrefWidth(69);

        toDoList.getColumns().addAll(toDo1,toDo2);
        toDoList.itemsProperty().bind(DataHub.getToDoListProperty());

        btnAdmin.setDisable(true);

        Employee.getRankProperty().addListener((observableValue, s, t1) -> checkRank());

    }
    /**
     * her we can remove the selected tasks from the to-do-list
     */
    public void remove(){
        if (!toDoList.getSelectionModel().isEmpty()) {
            DBHandler.freeTask(toDoList.getSelectionModel().getSelectedItem().getTaskId());
            toDoList.getItems().remove(toDoList.getSelectionModel().getSelectedItem());
        }
    }
    /**
     * her can the user logout and all tasks and to-do-list will be cleared
     */
    public void logOut(){
        for (Project p : DataHub.getToDoList()){
            DBHandler.freeTask(p.getTaskId());
        }
        DataHub.getToDoList().clear();
        DBHandler.disconnect();
        Employee.setEmpID(0);
        Employee.setName("");
        Employee.setMail("");
        Employee.setPhoneNo(0);
        Employee.setAddress("");
        Employee.setDepartment("");
        Employee.setRank("");
        btnAdmin.setDisable(true);
        logoutScene();
    }
    /**
     * her we check user if admin or consultant for disable or enable admin button
     */
    @FXML
    public void checkRank(){
        if (Employee.getRank().equalsIgnoreCase("ADMIN")) {
            this.btnAdmin.setDisable(false);
        }
        else
        {
            this.btnAdmin.setDisable(true);
        }
    }
    /**
     * her we can change scene to to-do-list load available projects
     */
    @FXML
    public  void toDoListScene(){
        DBHandler.setProjects();
        Main.changeScene(ControllerName.ToDoList);
        Main.setSceneLocation();
    }
    /**
     * her we can change scene to pomodoro start
     */
    @FXML
    public  void PomodoroStartScene(){
        Main.changeScene(ControllerName.PomodoroStart);
        Main.setSceneLocation();
    }
    /**
     * her we can change to admin start
     */
    @FXML
    public  void adminStartScene(){
            Main.changeScene(ControllerName.AdminStart);
            Main.setSceneLocation();
    }
    /**
     * her we can change scene to project
     */
    @FXML
    public  void projectScene(){
        Main.changeScene(ControllerName.Project);
        Main.setSceneLocation();
    }
    /**
     * her we can change scene to create user
     */
    @FXML
    public  void adminCreateScene(){
        Main.changeScene(ControllerName.AdminCreate);
        Main.setSceneLocation();
    }
    /**
     * her we can change scene to delete user
     */
    @FXML
    public  void adminDeleteScene(){
        Main.changeScene(ControllerName.AdminDelete);
        Main.setSceneLocation();
    }
    /**
     * her we can change scene to edit user
     */
    @FXML
    public  void adminEditScene(){
        Main.changeScene(ControllerName.AdminEdit);
        Main.setSceneLocation();
    }
    /**
     * her we can change scene to pomodoro progress
     */
    @FXML
    public  void pomodoroProgressScene(){
        Main.changeScene(ControllerName.PomodoroProgress);
        Main.setSceneLocation();
    }
    /**
     * her we can change scene to settings
     */
    @FXML
    public  void settingsScene(){
        Main.changeScene(ControllerName.Settings);
        Main.setSceneLocation();
    }
    /**
     * her we can change scene to login
     */
    @FXML
    private void logoutScene(){
        Main.changeScene(ControllerName.Login);
        Main.setSceneLocation();
    }
    /**
     * her we can minimize de scenes
     */
    public void minimize(){
        Stage stage = (Stage) btnMinimize.getScene().getWindow();
        stage.setIconified(true);
    }
}
