package Controller;

import DB.DBHandler;
import Data.DataHub;
import ObjectTypes.Project;
import PomodoroApp.ControllerName;
import PomodoroApp.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {

    @FXML
    public TableView<Project> toDoList;

    public void initialize(){
        TableColumn<Project, String> toDo1 = new TableColumn<>("TaskName");
        toDo1.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        toDo1.setPrefWidth(129);

        TableColumn<Project, String> toDo2 = new TableColumn<>("Time");
        toDo2.setCellValueFactory(new PropertyValueFactory<>("estimatedTime"));
        toDo2.setPrefWidth(69);

        toDoList.getColumns().addAll(toDo1,toDo2);
        toDoList.itemsProperty().bind(DataHub.getToDoListProperty());
    }
    public void remove(){
        if (!toDoList.getSelectionModel().isEmpty()) {
            DBHandler.freeTask(toDoList.getSelectionModel().getSelectedItem().getTaskId());
            toDoList.getItems().remove(toDoList.getSelectionModel().getSelectedItem());
        }
    }
    public void logOut(){
        for (Project p : DataHub.getToDoList()){
            DBHandler.freeTask(p.getTaskId());
        }
        DataHub.getToDoList().clear();
        DBHandler.disconnect();
        logoutScene();
    }
    @FXML
    public  void toDoListScene(){
        Main.changeScene(ControllerName.ToDoList);
    }
    @FXML
    public  void adminStartScene(){
        Main.changeScene(ControllerName.AdminStart);
    }
    @FXML
    public  void PomodoroStartScene(){
        Main.changeScene(ControllerName.PomodoroStart);
    }
    @FXML
    public  void projectScene(){
        Main.changeScene(ControllerName.Project);
    }
    @FXML
    public  void adminCreateScene(){
        Main.changeScene(ControllerName.AdminCreate);
    }
    @FXML
    public  void adminDeleteScene(){
        Main.changeScene(ControllerName.AdminDelete);
    }
    @FXML
    public  void adminEditScene(){
        Main.changeScene(ControllerName.AdminEdit);
    }
    @FXML
    public  void pomodoroProgressScene(){
        Main.changeScene(ControllerName.PomodoroProgress);
    }
    @FXML
    private void logoutScene(){
        Main.changeScene(ControllerName.Login);
    }
}
