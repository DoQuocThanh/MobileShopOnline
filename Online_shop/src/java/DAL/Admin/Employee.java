/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Admin;

import java.util.Date;


/**
 *
 * @author blabl
 */
public class Employee {
    private int accountID;
    private String email;
    private String pass;  
    private int employeeID;
    private String name;
    private int Status;
    private Date birthDate;
    private String address;

    public Employee(int accountID, String email, String pass, int employeeID, String name, int Status, Date birthDate, String address) {
        this.accountID = accountID;
        this.email = email;
        this.pass = pass;
        this.employeeID = employeeID;
        this.name = name;
        this.Status = Status;
        this.birthDate = birthDate;
        this.address = address;
    }

    public Employee() {
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
