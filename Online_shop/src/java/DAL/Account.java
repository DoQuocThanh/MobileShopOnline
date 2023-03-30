/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 *
 * @author LEGION
 */
public class Account {
    private int AccountID;
    private String Email;
    private String Pass;
    private Customer CustomerID;
    private String EmployeeID;
    private int Role;
    private int status;

    public Account() {
    }

    public Account(int Role) {
        this.Role = Role;
    }

    public Account(String Email) {
        this.Email = Email;
    }

    public Account(int AccountID, String Email, String Pass, Customer CustomerID, String EmployeeID, int Role) {
        this.AccountID = AccountID;
        this.Email = Email;
        this.Pass = Pass;
        this.CustomerID = CustomerID;
        this.EmployeeID = EmployeeID;
        this.Role = Role;
    }

    public Account(String Email, String Pass, Customer CustomerID,String EmployeeID) {
        this.Email = Email;
        this.Pass = Pass;
        this.CustomerID = CustomerID;
        this.Role = 2;
        this.EmployeeID = EmployeeID;
    }

    public Account(String Email, String Pass, Customer CustomerID, int Role, String EmployeeID) {
        this.Email = Email;
        this.Pass = Pass;
        this.CustomerID = CustomerID;
        this.Role = Role;
        this.EmployeeID = EmployeeID;
    }

    public int getAccountID() {
        return AccountID;
    }

    public void setAccountID(int AccountID) {
        this.AccountID = AccountID;
    }
    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRole() {
        return Role;
    }

    public void setRole(int Role) {
        this.Role = Role;
    }

    public Customer getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(Customer CustomerID) {
        this.CustomerID = CustomerID;
    }

    public void setEmployeeID(String EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }

    @Override
    public String toString() {
        return "Account{" + "Email=" + Email + ", Pass=" + Pass + ", CustomerID=" + CustomerID + '}';
    }

    /**
     * @return the EmployeeID
     */
    public String getEmployeeID() {
        return EmployeeID;
    }

}
