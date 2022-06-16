package Controller;


import DB.DBHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AdminCreateCon extends Controller{

    @FXML
    public TextField name, email,address,phone,department,username,password;

    @FXML
    public Label nameError, emailError,addressError,phoneError,departmentError,usernameError,passwordError,message;
    /**
     * Here it initializes the suer initialize and clear the scene
     */
    @Override
    public void initialize() {
        super.initialize();
        clear();
    }

    @Override
    public void remove() {
        super.remove();
    }

    @Override
    public void logOut() {
        super.logOut();
        clear();
    }

    @Override
    public void toDoListScene() {
        super.toDoListScene();
        clear();
    }

    @Override
    public void adminStartScene() {
        super.adminStartScene();
        clear();
    }

    @Override
    public void PomodoroStartScene() {
        super.PomodoroStartScene();
        clear();
    }

    @Override
    public void projectScene() {
        super.projectScene();
        clear();
    }

    @Override
    public void settingsScene() {
        super.settingsScene();
        clear();
    }
    /**
     * Here we create consultant if all, information is inserted correctly
     */
    @FXML
    public void createConsultant() {
        boolean check = true;
        if(phone.getText().isEmpty()) {
            phoneError.setText("insert phone number");
            check=false;
        }
        else if (!phone.getText().matches("\\d*")) {
            phoneError.setText("numbers only");
            phone.clear();
            check=false;
        }else{
            phoneError.setText("");
        }
        if(name.getText().isEmpty()) {
            nameError.setText("insert name");
            check=false;
        }else{
            nameError.setText("");
        }
        if(address.getText().isEmpty()) {
            addressError.setText("insert address");
            check=false;
        }else{
            addressError.setText("");
        }
        if(department.getText().isEmpty()) {
            departmentError.setText("insert department");
            check=false;
        }else{
            departmentError.setText("");
        }
        if(email.getText().isEmpty()) {
            emailError.setText("insert E-Mail");
            check=false;
        }else{
            emailError.setText("");
        }
        if(username.getText().isEmpty()) {
            usernameError.setText("insert username");
            check=false;
        }else{
            usernameError.setText("");
        }
        if(password.getText().isEmpty()) {
            passwordError.setText("insert password");
            check=false;
        }else{
            passwordError.setText("");
        }

        if(check){

            boolean passwordTest = DBHandler.createConsultant(name.getText(),email.getText(),Integer.parseInt(phone.getText()),address.getText(),department.getText(),username.getText(),password.getText());

            if(passwordTest){
                clear();
                message.setText("SUCCESSFUL");
            }
            else{
                usernameError.setText("username already exist");
            }
        }
    }
    /**
     * Here we clear the scene
     */
    @FXML
    public void clear(){
        message.setText("");
        name.clear();
        nameError.setText("");
        email.clear();
        emailError.setText("");
        address.clear();
        addressError.setText("");
        phone.clear();
        phoneError.setText("");
        department.clear();
        departmentError.setText("");
        username.clear();
        usernameError.setText("");
        password.clear();
        passwordError.setText("");
    }

    @Override
    public void minimize() {
        super.minimize();
    }
}
