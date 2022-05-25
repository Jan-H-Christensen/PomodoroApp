package Controller;

import DB.DBHandler;
import Data.DataHub;
import ObjectTypes.Project;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ToDoListController extends Controller{


    @FXML
    public TableView<Project> projectView;
    @FXML
    public Label errorProject;

    @Override
    public void initialize() {
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
        super.initialize();
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

    @Override
    public void remove() {
        super.remove();
    }

    @Override
    public void logOut() {
        clean();
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
