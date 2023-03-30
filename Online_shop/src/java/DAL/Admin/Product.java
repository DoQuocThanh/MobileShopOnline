/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Admin;

/**
 *
 * @author blabl
 */
public class Product {
    
    private int productID;
    private String productName;
    private int brandID;
    private String chip;
    private int ram;
    private String pin;
    private String phoneScreen;
    private String picture;
    private float discount;
    private int unitOnStock;
    private String eventName;
    private int unitOnOrder;
    private float price;
    private int rate;
    private int amountRate;
    private String operatingSystem;
    private String brandName;
    
   

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public Product(int productID, String productName, int brandID, String chip, int ram, String pin, String phoneScreen, String picture, float price) {
        this.productID = productID;
        this.productName = productName;
        this.brandID = brandID;
        this.chip = chip;
        this.ram = ram;
        this.pin = pin;
        this.phoneScreen = phoneScreen;
        this.picture = picture;
        this.price = price;
    }
     public Product(int productID, String productName, int brandID, String chip, int ram, String pin, String phoneScreen, String picture, float discount, int unitOnStock, String eventName, int unitOnOrder, float price, int rate, int amountRate, String operatingSystem, String brandName) {
        this.productID = productID;
        this.productName = productName;
        this.brandID = brandID;
        this.chip = chip;
        this.ram = ram;
        this.pin = pin;
        this.phoneScreen = phoneScreen;
        this.picture = picture;
        this.discount = discount;
        this.unitOnStock = unitOnStock;
        this.eventName = eventName;
        this.unitOnOrder = unitOnOrder;
        this.price = price;
        this.rate = rate;
        this.amountRate = amountRate;
        this.operatingSystem = operatingSystem;
        this.brandName = brandName;
    }
    public Product(int productID, String productName, int brandID, String chip, int ram, String pin, String phoneScreen, String picture, int unitOnStock, int unitOnOrder, float price, int rate, int amountRate, String brandName) {
        this.productID = productID;
        this.productName = productName;
        this.brandID = brandID;
        this.chip = chip;
        this.ram = ram;
        this.pin = pin;
        this.phoneScreen = phoneScreen;
        this.picture = picture;
        this.unitOnStock = unitOnStock;
        this.unitOnOrder = unitOnOrder;
        this.price = price;
        this.rate = rate;
        this.amountRate = amountRate;
        this.brandName = brandName;
    }
    
    

    public Product(int productID, String productName, int unitOnStock, int unitOnOrder, String brandName) {
        this.productID = productID;
        this.productName = productName;
        this.unitOnStock = unitOnStock;
        this.unitOnOrder = unitOnOrder;
        this.brandName = brandName;
    }

    public Product() {
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

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public int getUnitOnStock() {
        return unitOnStock;
    }

    public void setUnitOnStock(int unitOnStock) {
        this.unitOnStock = unitOnStock;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getUnitOnOrder() {
        return unitOnOrder;
    }

    public void setUnitOnOrder(int unitOnOrder) {
        this.unitOnOrder = unitOnOrder;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getAmountRate() {
        return amountRate;
    }

    public void setAmountRate(int amountRate) {
        this.amountRate = amountRate;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    
    

    
}
