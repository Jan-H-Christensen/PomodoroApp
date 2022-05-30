package DB;

import Data.DataHub;
import ObjectTypes.Employee;
import ObjectTypes.Project;
import java.sql.*;

public class DBHandler {

    private static Connection con;

    public static boolean connect(String name, String code) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=JensenAndJensen","sa","1234");

            try {
                PreparedStatement ps = con.prepareStatement("exec CheckUser "+name + ", " + code);
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
                    DataHub.setEmployee(new Employee(no1,no2,no3,no4,no5,no6,no7));
                    check++;
                }

                ps.close();
                rs.close();

                if (check <= 0){
                    disconnect();
                    return false;
                }
                else{
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

    public static void disconnect() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

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

    public static void reserveTask(int taskID){
        try {
            PreparedStatement ps = con.prepareStatement("exec reservadProject "+taskID);
            ps.executeUpdate();
            ps.close();
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void freeTask(int taskID){
        try {
            PreparedStatement ps = con.prepareStatement("exec freeProject "+taskID);
            ps.executeUpdate();
            ps.close();
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static boolean checkTaskName(String name){
        try {
            PreparedStatement ps = con.prepareStatement("exec CheckTaskName "+name);
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

    public static void changePassword(int employeeID,String oldPassword,String newPassword,String confirmPassword){
        try{
            PreparedStatement ps = con.prepareStatement("exec changePassword "+employeeID+","+oldPassword+","+newPassword+","+confirmPassword);
            ps.executeUpdate();
            ps.close();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

}