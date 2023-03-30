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
public class HistoryProductDetail {
    private int historyProductDetailID;
    private String employeeName;
    private String employeeID;
    private String status;
    private Date time;
    private int productDetailID;
    private int productID;
    private int productStorage;
    private float price;
    private String picture;
    private String coler;
    private int UnitsInStock;
    private int UnitsOnOrder;

    public HistoryProductDetail(int historyProductDetailID, String employeeName, String employeeID, String status, Date time, int productDetailID, int productID, int productStorage, float price, String picture, String coler, int UnitsInStock, int UnitsOnOrder) {
        this.historyProductDetailID = historyProductDetailID;
        this.employeeName = employeeName;
        this.employeeID = employeeID;
        this.status = status;
        this.time = time;
        this.productDetailID = productDetailID;
        this.productID = productID;
        this.productStorage = productStorage;
        this.price = price;
        this.picture = picture;
        this.coler = coler;
        this.UnitsInStock = UnitsInStock;
        this.UnitsOnOrder = UnitsOnOrder;
    }

    public HistoryProductDetail() {
    }

    public int getHistoryProductDetailID() {
        return historyProductDetailID;
    }

    public void setHistoryProductDetailID(int historyProductDetailID) {
        this.historyProductDetailID = historyProductDetailID;
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

    public int getProductDetailID() {
        return productDetailID;
    }

    public void setProductDetailID(int productDetailID) {
        this.productDetailID = productDetailID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getProductStorage() {
        return productStorage;
    }

    public void setProductStorage(int productStorage) {
        this.productStorage = productStorage;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getColer() {
        return coler;
    }

    public void setColer(String coler) {
        this.coler = coler;
    }

    public int getUnitsInStock() {
        return UnitsInStock;
    }

    public void setUnitsInStock(int UnitsInStock) {
        this.UnitsInStock = UnitsInStock;
    }

    public int getUnitsOnOrder() {
        return UnitsOnOrder;
    }

    public void setUnitsOnOrder(int UnitsOnOrder) {
        this.UnitsOnOrder = UnitsOnOrder;
    }
    
}
