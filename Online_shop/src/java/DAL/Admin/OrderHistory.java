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
public class OrderHistory {
    private String employeeID;
    private String employeeName;
    private Date time;
    private String description;
    private int orderID;

    public OrderHistory() {
    }

    public OrderHistory(String employeeID, String employeeName, Date time, String description, int orderID) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.time = time;
        this.description = description;
        this.orderID = orderID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
    
    
}
