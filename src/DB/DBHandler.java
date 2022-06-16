package DB;

import Data.DataHub;
import Data.Employee;
import Data.Pomodoro;
import ObjectTypes.Project;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DBHandler {
    private static Connection con;

    /**
     * Her we connect to the database be using a username and a password
     * @param name is a username for entering the Application
     * @param code is the password to check if he is allowed to enter
     * @return a boolean so the application knows if the entered username and password was correct or not
     */
    public static boolean connect(String name, String code) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=JensenAndJensen","sa","123456");
            try {
                PreparedStatement ps = con.prepareStatement("exec CheckUser "+name + "," + code);
                ResultSet rs = ps.executeQuery();
                int check = 0;

                while (rs.next()) {
                    int no1 = rs.getInt(1);
                    String no2 = rs.getString(2);
                    String no3 = rs.getString(3);
                    int no4 = rs.getInt(4);
                    String no5 = rs.getString(5);
                    String no6 = rs.getString(6);
                    String no7 = rs.getString(7);
                    Employee.setEmpID(no1);
                    Employee.setName(no2);
                    Employee.setMail(no3);
                    Employee.setPhoneNo(no4);
                    Employee.setAddress(no5);
                    Employee.setDepartment(no6);
                    Employee.setRank(no7);
                    check++;
                }

                ps.close();
                rs.close();

                if (check <= 0){
                    disconnect();
                    return false;
                }
                else{
                    setStatus();
                    return true;
                }

            }catch (SQLException e) {
                System.err.println(e.getMessage());
                disconnect();
                return false;
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            disconnect();
            return false;
        }
    }
    /**
     * disconnect from the database
     */
    public static void disconnect() {
        try {
            if (con != null) {
                if(Employee.getEmpID()!=0) {
                    setStatus();
                }
                con.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    /**
     * gets all available projects in the database
     */
    public static void setProjects(){
        DataHub.getProjectList().clear();
        try {
            PreparedStatement ps = con.prepareStatement("exec getProject");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                int no1 = rs.getInt(1);
                int no2 = rs.getInt(2);
                int no3 = rs.getInt(3);
                String no4 = rs.getString(4);
                String no5 = rs.getString(5);

                Project project = new Project(no1,no2,no3,no4,no5);
                DataHub.getProjectList().add(project);
            }

            ps.close();
            rs.close();


        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * checks for if a project from the list is free or in use
     * @param taskID to find the number of work on
     * @return a boolean so the application know if ti is in use
     */
    public static boolean checkTask(int taskID){
        try {
            PreparedStatement ps = con.prepareStatement("exec CheckProject "+taskID);
            ResultSet rs = ps.executeQuery();
            int check = 0;
            while (rs.next()) {
                check = rs.getInt(1);
            }

            ps.close();
            rs.close();
            if (check <= 0){
                return false;
            }
            else{
                return true;
            }

        }catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    /**
     * update the number of workOn in the database, to be reserved
     * @param taskID is needed to find the right task and update the workOn
     */
    public static void reserveTask(int taskID){
        try {
            PreparedStatement ps = con.prepareStatement("exec reservadProject "+taskID);
            ps.executeUpdate();
            ps.close();
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    /**
     * update the numberOfWorkOn back is the current project has been reset / interrupted
     * @param taskID is needed the reset project
     */
    public static void freeTask(int taskID){
        try {
            PreparedStatement ps = con.prepareStatement("exec freeProject "+taskID);
            ps.executeUpdate();
            ps.close();
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    /**
     * checks for the task name if it already exists
     * @param name is needed so the stored procedure is look in the database if the one exists
     * @return a boolean so the application notes if the there is one with the same name
     */
    public static boolean checkTaskName(String name){
        try {
            PreparedStatement ps = con.prepareStatement("exec CheckTaskName '"+name+"'");
            ResultSet rs = ps.executeQuery();
            int check = 0;
            while (rs.next()) {
                check++;
            }

            ps.close();
            rs.close();
            if (check <= 0){
                return false;
            }
            else{
                return true;
            }

        }catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
    /**
     * her we can delete a consultant by only deleting Login
     * @param username is needed to find the user we want to delete
     * @return the consultant that are stored at the username
     */
    public static Employee findUser(String username){
        try {
            PreparedStatement ps = con.prepareStatement("exec deleteUser "+"'"+username+"'");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){

                int no1 = rs.getInt(1);
                if(no1 != 0){
                    String no2 = rs.getString(2);
                    String no3 = rs.getString(3);
                    int no4 = rs.getInt(4);
                    String no5 = rs.getString(5);
                    String no6 = rs.getString(6);
                    String no7 = rs.getString(7);
                    Employee employee = new Employee();
                    employee.setEmpID(no1);
                    employee.setName(no2);
                    employee.setMail(no3);
                    employee.setPhoneNo(no4);
                    employee.setAddress(no5);
                    employee.setDepartment(no6);
                    employee.setRank(no7);
                    return employee;
                }
            }
            return null;
        }catch (SQLException e){
            System.err.println(e.getMessage());
            return null;
        }
    }
    /**
     * checks if the entered username and password is correct
     * @param username is the name of the consultant for the application
     * @param password is corresponding code to the username
     * @return a boolean so the application notes if the entered username and password was correct
     */
    public static boolean passwordCheck(String username, String password){
        try{
            PreparedStatement ps = con.prepareStatement("exec passwordCheck '"+username+"','"+password+"'");
            ResultSet rs = ps.executeQuery();

            int check = 0;
            while (rs.next()){
                check = rs.getInt(1);
            }
            ps.close();
            rs.close();

            if(check <=0 ){
                return false;
            }else {
                return true;
            }

        }catch (SQLException e){
            System.err.println(e.getMessage());
            return  false;
        }
    }
    /**
     * her the user can change the password
     * @param employeeID her he is checking for which user is logged in, to make be sure no other can change password
     * @param oldPassword is the current password that the user wants to change
     * @param newPassword is the new password
     * @param confirmPassword ist the new password again to be sure he did not make a typing mistake
     */
    public static void changePassword(int employeeID,String oldPassword,String newPassword,String confirmPassword){
        try{
            PreparedStatement ps = con.prepareStatement("exec changePassword "+employeeID+","+oldPassword+","+newPassword+","+confirmPassword);
            ps.executeUpdate();

           ps.close();
        }catch (SQLException e){
            System.err.println(e.getMessage());

        }
    }

    /**
     * sets the status for the user to be online or not
     */
    private static void setStatus(){
        DataHub.getProjectList().clear();
        try {
            PreparedStatement ps = con.prepareStatement("exec setStatus "+Employee.getEmpID());
            ps.executeUpdate();
            ps.close();
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * her we're adding a new consultant and a Login for the application to the database
     * @param name Consultant name
     * @param userEmail Consultant E-Mail
     * @param userPhone Consultant phone number
     * @param userAddress Consultant address
     * @param userDepartment Consultant department where will work
     * @param username Consultant username for the application
     * @param password Consultant password to enter the application
     * @return a boolean if the username already exists
     */
    public static boolean createConsultant(String name, String userEmail, int userPhone,String userAddress ,  String userDepartment, String username, String password) {

        try {
            int check = 0;
            PreparedStatement ps = con.prepareStatement("exec checkUsername '"+username+"'");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                check++;
            }
            if(check <= 0){
                try{
                    PreparedStatement pst = con.prepareStatement("exec createConsultent '"+name+"','"+userEmail+"',"+userPhone+",'"+userAddress+"','"+userDepartment+"','"+username+"','"+password+"'");
                    pst.executeUpdate();
                    return true;
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                    return false;
                }
            }else {
                return false;
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }

    /**
     * her we update the progress of the current pomodoro time to the database
     * @param currentProgress is the progress at the update time
     */
    public static void updateToDoList(double currentProgress){
        try {
            PreparedStatement ps = con.prepareStatement("exec updateToDoList "+Pomodoro.getToDoListID()+","+currentProgress+"");
            ps.executeUpdate();
            ps.close();
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * her we update the progress of the current pomodoro time and number to the database
     * @param currentProgress is the progress at the update time
     * @param currentPomodoro is the number of the current pomodoro
     */
    public static void updateToDoList(double currentProgress,int currentPomodoro){
        try {
            PreparedStatement ps = con.prepareStatement("exec updateToDoListAndCurrentPomodoro "+ Pomodoro.getToDoListID()+","+currentProgress+","+currentPomodoro+"");
            ps.executeUpdate();
            ps.close();
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * update the status to finish when done
     */
    public static void finishPomodoro(){
        try {
            PreparedStatement ps = con.prepareStatement("exec finishPomodoro "+Pomodoro.getToDoListID()+","+Pomodoro.getProject().getTaskId()+"");
            ps.executeUpdate();
            ps.close();
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }
    /**
     * creates a new to-do-list to the database and when created it selects the to-do-list
     */
    public static void createToDoList(){
        Date date = new java.util.Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy");
        if (Pomodoro.getToDoListID() == 0) {
            try{
                PreparedStatement ps2 = con.prepareStatement("exec createToDoList " + Employee.getEmpID() + "," + Integer.parseInt(Pomodoro.getWorkTime()) + "," + Integer.parseInt(Pomodoro.getBreakTIme()) + "," + Pomodoro.getProject().getTaskId() + ",'" + dateFormat.format(date) + "','" + timeFormat.format(date) + "','" + Pomodoro.getProject().getTaskName() + "'," + Pomodoro.getProject().getEstimatedTime() + "");
                ps2.executeUpdate();
                ps2.close();
            }catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        getToDoList(dateFormat.format(date));
    }

    /**
     * get the listID and taskID of the current pomodoro
     * @param date is needed to be sure we have the right to-do-list
     *
     */
    public static void getToDoList(String date){
        try {
            PreparedStatement ps1 = con.prepareStatement("exec checkToDoList '" + Pomodoro.getProject().getTaskName() + "'," + Employee.getEmpID() + ",'" + date + "'," + Integer.parseInt(Pomodoro.getWorkTime()) + "," + Integer.parseInt(Pomodoro.getBreakTIme()));
            ResultSet rs = ps1.executeQuery();

            while (rs.next()) {
                Pomodoro.setToDoListID(rs.getInt(1));
                Pomodoro.getProject().setTaskId(rs.getInt(2));
            }
            rs.close();
            ps1.close();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
    /**
     * updater task status in the database
     */
    public static void cancelChecker(){
        try {
            PreparedStatement ps = con.prepareStatement("exec cancelChecker "+Pomodoro.getToDoListID()+"");
            ps.executeUpdate();
            ps.close();
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}