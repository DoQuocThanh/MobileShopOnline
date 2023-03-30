/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.cart;

/**
 *
 * @author LEGION
 */
public class ProductCart {
    private int ProductDetailID;
    private String ProductName;
    private int Quantity;
    private double Price;
    private String Picture;
    private String Color;

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public ProductCart(int ProductDetailID, String ProductName, int Quantity, double Price, String Picture, String Color) {
        this.ProductDetailID = ProductDetailID;
        this.ProductName = ProductName;
        this.Quantity = Quantity;
        this.Price = Price;
        this.Picture = Picture;
        this.Color = Color;
    }

    public ProductCart(int ProductDetailID, String ProductName, double Price, String Picture, String Color) {
        this.ProductDetailID = ProductDetailID;
        this.ProductName = ProductName;
        this.Price = Price;
        this.Picture = Picture;
        this.Color = Color;
    }

    @Override
    public String toString() {
        return "GuestProductCart{" + "ProductDetailID=" + ProductDetailID + ", ProductName=" + ProductName + ", Quantity=" + Quantity + ", Price=" + Price + ", Picture=" + Picture + ", Color=" + Color + '}';
    }

    

    public double getPrice() {
        return Price;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String Picture) {
        this.Picture = Picture;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }
    public ProductCart() {
    }

    public int getProductDetailID() {
        return ProductDetailID;
    }

    public void setProductDetailID(int ProductDetailID) {
        this.ProductDetailID = ProductDetailID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }
    
}
