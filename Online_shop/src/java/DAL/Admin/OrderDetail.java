/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Admin;

/**
 *
 * @author blabl
 */
public class OrderDetail {
    private int orderID;
    private String productName;
    private String picture;
    private int quantity;
    private float discount;
    private float unitprice;
    private int productDetailID;

    public int getProductDetailID() {
        return productDetailID;
    }

    public void setProductDetailID(int productDetailID) {
        this.productDetailID = productDetailID;
    }

    public OrderDetail(int orderID, String productName, String picture, int quantity, float discount, float unitprice, int productDetailID) {
        this.orderID = orderID;
        this.productName = productName;
        this.picture = picture;
        this.quantity = quantity;
        this.discount = discount;
        this.unitprice = unitprice;
        this.productDetailID = productDetailID;
    }

    public OrderDetail() {
    }

    public OrderDetail(int orderID, String productName, String picture, int quantity, float discount, float unitprice) {
        this.orderID = orderID;
        this.productName = productName;
        this.picture = picture;
        this.quantity = quantity;
        this.discount = discount;
        this.unitprice = unitprice;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(float unitprice) {
        this.unitprice = unitprice;
    }
    
    
}
