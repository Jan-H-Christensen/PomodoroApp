package Data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 *  This class store this values, for use in other classes
 *  int empID;
 *  String name;
 *  String eMail;
 *  int phoneNo;
 *  String address;
 *  String department;
 *  String rank;
 *  StringProperty rankProperty
 *  A getter and setter is created for each value, so we can update the values
 *  All values and methods are static
 */
public class Employee {
    private static int empID;
    private static String name;
    private static String eMail;
    private static int phoneNo;
    private static String address;
    private static String department;
    private static String rank;
    private static StringProperty rankProperty = new SimpleStringProperty("");

    public static int getEmpID() {
        return empID;
    }

    public static void setEmpID(int empID1) {
        empID = empID1;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name1) {name = name1;}

    public static String getMail() {
        return eMail;
    }

    public static void setMail(String eMail1) {
        eMail = eMail1;
    }

    public static int getPhoneNo() {
        return phoneNo;
    }

    public static void setPhoneNo(int phoneNo1) {
        phoneNo = phoneNo1;
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address1) {
        address = address1;
    }

    public static String getDepartment() {
        return department;
    }

    public static void setDepartment(String department1) {
        department = department1;
    }

    public static StringProperty getRankProperty() {
        return rankProperty;
    }

    public static String getRank(){
        return rank;
    }

    public static void setRank(String rank1) {
        rank =rank1;
        rankProperty.set(rank1);
    }

}