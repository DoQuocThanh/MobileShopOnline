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
public class HistoryProduct {
    private int historyID;
    private String employeeName;
    private String employeeID;
    private String status;
    private Date time;
    private int productID;
    private String productName;
    private int brandID;
    private String chip;
    private int ram;
    private String pin;
    private String phoneScreen;
    private String picture;

    public HistoryProduct() {
    }

    public HistoryProduct(int historyID, String employeeName, String employeeID, String status, Date time, int productID, String productName, int brandID, String chip, int ram, String pin, String phoneScreen, String picture) {
        this.historyID = historyID;
        this.employeeName = employeeName;
        this.employeeID = employeeID;
        this.status = status;
        this.time = time;
        this.productID = productID;
        this.productName = productName;
        this.brandID = brandID;
        this.chip = chip;
        this.ram = ram;
        this.pin = pin;
        this.phoneScreen = phoneScreen;
        this.picture = picture;
    }

    public int getHistoryID() {
        return historyID;
    }

    public void setHistoryID(int historyID) {
        this.historyID = historyID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getBrandID() {
        return brandID;
    }

    public void setBrandID(int brandID) {
        this.brandID = brandID;
    }

    public String getChip() {
        return chip;
    }

    public void setChip(String chip) {
        this.chip = chip;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getPhoneScreen() {
        return phoneScreen;
    }

    public void setPhoneScreen(String phoneScreen) {
        this.phoneScreen = phoneScreen;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
