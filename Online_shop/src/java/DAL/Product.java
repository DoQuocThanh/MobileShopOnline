/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 *
 * @author LEGION
 */
public class Product {
    private int ProductID;
    private String ProductName;
    private int BrandID;
    private String Chip;
    private int Ram;
    private String Pin;
    private String OperatingSystem;
    private String PhoneScreen;
    private String Picture;
    private double Price;
    
    public Product() {
    }

    public Product(int ProductID, String ProductName, String Picture, double Price) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.Picture = Picture;
        this.Price = Price;
    }

       
    public Product(int ProductID, String ProductName, String Picture) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.Picture = Picture;
    }

    public Product(int ProductID, String ProductName, int BrandID, String Chip, int Ram, String Pin, String OperatingSystem, String PhoneScreen, String Picture, double Price) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.BrandID = BrandID;
        this.Chip = Chip;
        this.Ram = Ram;
        this.Pin = Pin;
        this.OperatingSystem = OperatingSystem;
        this.PhoneScreen = PhoneScreen;
        this.Picture = Picture;
        this.Price = Price;
    }
    
    
    
    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public int getBrandID() {
        return BrandID;
    }

    public void setBrandID(int BrandID) {
        this.BrandID = BrandID;
    }

    public String getChip() {
        return Chip;
    }

    public void setChip(String Chip) {
        this.Chip = Chip;
    }

    public int getRam() {
        return Ram;
    }

    public void setRam(int Ram) {
        this.Ram = Ram;
    }

    public String getPin() {
        return Pin;
    }

    public void setPin(String Pin) {
        this.Pin = Pin;
    }

    public String getOperatingSystem() {
        return OperatingSystem;
    }

    public void setOperatingSystem(String OperatingSystem) {
        this.OperatingSystem = OperatingSystem;
    }

    public String getPhoneScreen() {
        return PhoneScreen;
    }

    public void setPhoneScreen(String PhoneScreen) {
        this.PhoneScreen = PhoneScreen;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String Picture) {
        this.Picture = Picture;
    }
    
    
}
