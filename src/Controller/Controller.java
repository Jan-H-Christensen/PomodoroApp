package Controller;

import DB.DBHandler;
import Data.DataHub;
import ObjectTypes.Employee;
import ObjectTypes.Project;
import PomodoroApp.ControllerName;
import PomodoroApp.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class Controller {

    @FXML
    public Button btnMinimize;
    @FXML
    public Button btnAdmin;
    @FXML
    public TableView<Project> toDoList;
    /**
     *
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
     *
     */
    public void remove(){
        if (!toDoList.getSelectionModel().isEmpty()) {
            DBHandler.freeTask(toDoList.getSelectionModel().getSelectedItem().getTaskId());
            toDoList.getItems().remove(toDoList.getSelectionModel().getSelectedItem());
        }
    }
    /**
     *
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
     *
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
     *
     */
    @FXML
    public  void toDoListScene(){
        DBHandler.setProjects();
        Main.changeScene(ControllerName.ToDoList);
        Main.setSceneLocation();
    }
    /**
     *
     */
    @FXML
    public  void PomodoroStartScene(){
        Main.changeScene(ControllerName.PomodoroStart);
        Main.setSceneLocation();
        DataHub.setListenerChecker(0);
    }
    /**
     *
     */
    @FXML
    public  void adminStartScene(){
        if (Employee.getRank().equalsIgnoreCase("ADMIN")) {
            Main.changeScene(ControllerName.AdminStart);
            Main.setSceneLocation();
        }
        else
        {
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.WARNING);
            a.setContentText("YOU ARE NOT A ADMIN\nYOU ACCESS TRY WILL BE RECORDED");
            a.show();
        }
    }
    /**
     *
     */
    @FXML
    public  void projectScene(){
        Main.changeScene(ControllerName.Project);
        Main.setSceneLocation();
    }
    /**
     *
     */
    @FXML
    public  void adminCreateScene(){
        Main.changeScene(ControllerName.AdminCreate);
        Main.setSceneLocation();
    }
    /**
     *
     */
    @FXML
    public  void adminDeleteScene(){
        Main.changeScene(ControllerName.AdminDelete);
        Main.setSceneLocation();
    }
    /**
     *
     */
    @FXML
    public  void adminEditScene(){
        Main.changeScene(ControllerName.AdminEdit);
        Main.setSceneLocation();
    }
    /**
     *
     */
    @FXML
    public  void pomodoroProgressScene(){
        Main.changeScene(ControllerName.PomodoroProgress);
        Main.setSceneLocation();
    }
    /**
     *
     */
    @FXML
    public  void settingsScene(){
        Main.changeScene(ControllerName.Settings);
        Main.setSceneLocation();
    }
    /**
     *
     */
    @FXML
    private void logoutScene(){
        Main.changeScene(ControllerName.Login);
        Main.setSceneLocation();
    }
    /**
     *
     */
    public void minimize(){
        Stage stage = (Stage) btnMinimize.getScene().getWindow();
        stage.setIconified(true);
    }
}
