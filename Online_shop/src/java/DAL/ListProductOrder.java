/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.Date;

/**
 *
 * @author LEGION
 */
public class ListProductOrder {
    private int OrderID;
    private String ProductName;
    private String Color;
    private String Picture;
    private Date OrderDate;
    private int Price;
    private int Quantity;
    private int Status;

    public ListProductOrder(int OrderID, String ProductName, String Color, String Picture, Date OrderDate, int Price, int Quantity, int Status) {
        this.OrderID = OrderID;
        this.ProductName = ProductName;
        this.Color = Color;
        this.Picture = Picture;
        this.OrderDate = OrderDate;
        this.Price = Price;
        this.Quantity = Quantity;
        this.Status = Status;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String Picture) {
        this.Picture = Picture;
    }

    

    public ListProductOrder() {
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public Date getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(Date OrderDate) {
        this.OrderDate = OrderDate;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }
    
    
}
