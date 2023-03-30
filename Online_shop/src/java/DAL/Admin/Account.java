/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Admin;



/**
 *
 * @author blabl
 */
public class Account {
    
    private int accountID;
    private String email;
    private String pass;  
    private int employeeID;
    private int role;
    private String lastname;
    private String firstname;
    private int status;

    public Account(int accountID, String email, String pass, int employeeID, int role, String lastname, String firstname, int status) {
        this.accountID = accountID;
        this.email = email;
        this.pass = pass;
        this.employeeID = employeeID;
        this.role = role;
        this.lastname = lastname;
        this.firstname = firstname;
        this.status = status;
    }

    public int getAccountID() {
        return accountID;
    }

    public int getStatus() {
        return status;
    }

    public Account(int accountID, String email, String pass, int role) {
        this.accountID = accountID;
        this.email = email;
        this.pass = pass;
        this.role = role;
    }

    public Account(int accountID, String email, String pass, int employeeID, int role, String lastname, String firstname) {
        this.accountID = accountID;
        this.email = email;
        this.pass = pass;
        this.employeeID = employeeID;
        this.role = role;
        this.lastname = lastname;
        this.firstname = firstname;
    }

    @Override
    public String toString() {
        return "Account{" + "accountID=" + accountID + ", email=" + email + ", pass=" + pass + ", employeeID=" + employeeID + ", role=" + role + ", lastname=" + lastname + ", firstname=" + firstname + '}';
    }

    

    

    public Account() {
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    
}
