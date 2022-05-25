package Controller;

import DB.DBHandler;
import Data.DataHub;
import ObjectTypes.Project;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ToDoListController implements ControllerInterface{

    @FXML
    public TableView<Project> toDoList;
    @FXML
    public TableView<Project> projectView;
    @FXML
    public Label errorProject;

    public void initialize(){

        TableColumn<Project, String> nameCol1 = new TableColumn<>("TaskName");
        nameCol1.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        nameCol1.setPrefWidth(223);

        TableColumn<Project, Integer> nameCol2 = new TableColumn<>("EstimatedTime");
        nameCol2.setCellValueFactory(new PropertyValueFactory<>("estimatedTime"));
        nameCol2.setPrefWidth(223);

        TableColumn<Project, Integer> nameCol3 = new TableColumn<>("NumberWorkOn");
        nameCol3.setCellValueFactory(new PropertyValueFactory<>("numberWorkOn"));
        nameCol3.setPrefWidth(223);

        projectView.getColumns().addAll(nameCol1,nameCol2,nameCol3);
        projectView.itemsProperty().bind(DataHub.getListProperty());


        TableColumn<Project, String> toDo1 = new TableColumn<>("TaskName");
        toDo1.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        toDo1.setPrefWidth(50);

        TableColumn<Project, String> toDo2 = new TableColumn<>("Time");
        toDo2.setCellValueFactory(new PropertyValueFactory<>("estimatedTime"));
        toDo2.setPrefWidth(50);

        toDoList.getColumns().addAll(toDo1,toDo2);
        toDoList.itemsProperty().bind(DataHub.getToDoListProperty());
    }

    public void remove(){
        if (!toDoList.getSelectionModel().isEmpty()) {
            DBHandler.freeTask(toDoList.getSelectionModel().getSelectedItem().getTaskId());
            toDoList.getItems().remove(toDoList.getSelectionModel().getSelectedItem());
        }
    }

    public void update(){
        DBHandler.setProjects();
    }

    public void add() {
        errorProject.setText("");
        if (!projectView.getSelectionModel().isEmpty()) {
            if (DBHandler.checkTask(projectView.getSelectionModel().getSelectedItem().getTaskId())){

                DataHub.getToDoList().add(projectView.getSelectionModel().getSelectedItem());
                DBHandler.reserveTask(projectView.getSelectionModel().getSelectedItem().getTaskId());
                projectView.getItems().remove(projectView.getSelectionModel().getSelectedItem());

            }
            else
            {
                errorProject.setText("Project has already bin reserved");
            }
        }
        else
        {
            errorProject.setText("No project selected");
        }
    }

    public void clean(){
        DataHub.getProjectList().clear();
        errorProject.setText("");
    }

    public void logOut(){
        clean();
        for (Project p : DataHub.getToDoList()){
            DBHandler.freeTask(p.getTaskId());
        }
        DataHub.getToDoList().clear();
        DBHandler.disconnect();
        logoutScene();
    }
    @Override
    public void adminStartScene() {
        ControllerInterface.super.adminStartScene();
    }

    @Override
    public void PomodoroStartScene() {
        ControllerInterface.super.PomodoroStartScene();
    }

    @Override
    public void projectScene() {
        ControllerInterface.super.projectScene();
    }

    @Override
    public void logoutScene() {
        ControllerInterface.super.logoutScene();
    }
}
