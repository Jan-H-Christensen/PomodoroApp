package ObjectTypes;

import DB.DBHandler;

public class Employee {
    private int empID;
    private String name;
    private String eMail;
    private int phoneNo;
    private String address;
    private String department;
    private String rank;

    public Employee(int empID, String name, String eMail, int phoneNo, String address, String department, String rank) {
        setEmpID(empID);
        setName(name);
        seteMail(eMail);
        setPhoneNo(phoneNo);
        setAddress(address);
        setDepartment(department);
        setRank(rank);
    }

    public int getEmpID() {
        return empID;
    }

    private void setEmpID(int empID) {
        this.empID = empID;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String geteMail() {
        return eMail;
    }

    private void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    private void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    private void setAddress(String address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    private void setDepartment(String department) {
        this.department = department;
    }

    public String getRank() {
        return rank;
    }

    private void setRank(String rank) {
        this.rank = rank;
        //DBHandler.controllerInterface.checkRank();
    }

}
