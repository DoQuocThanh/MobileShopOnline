/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Home;

/**
 *
 * @author blabl
 */
public class ProductDiscountUnitOnOrder {

    
    private int productID;
    private String productName;
    private int brandID;
    private String chip;
    private int ram;
    private String pin;
    private String PhoneScreen;
    private String picture;
    private float discount;
    private int unitOnStock;

    public int getUnitOnStock() {
        return unitOnStock;
    }

    public void setUnitOnStock(int unitOnStock) {
        this.unitOnStock = unitOnStock;
    }
    private int unitOnOrder;
    private float price;
    private int rate;
    private int amountRate;
    
    public int getRate() {
        return rate;
    }

    public ProductDiscountUnitOnOrder(int productID, String productName, String picture, int unitOnStock, int unitOnOrder, float price, int rate, int amountRate) {
        this.productID = productID;
        this.productName = productName;
        this.picture = picture;
        this.unitOnStock = unitOnStock;
        this.unitOnOrder = unitOnOrder;
        this.price = price;
        this.rate = rate;
        this.amountRate = amountRate;
    }

    public ProductDiscountUnitOnOrder(int productID, String productName, String picture, float discount, int unitOnStock, int unitOnOrder, float price, int rate, int amountRate) {
        this.productID = productID;
        this.productName = productName;
        this.picture = picture;
        this.discount = discount;
        this.unitOnStock = unitOnStock;
        this.unitOnOrder = unitOnOrder;
        this.price = price;
        this.rate = rate;
        this.amountRate = amountRate;
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

    public ProductDiscountUnitOnOrder(int productID, String productName, float discount, int unitOnStock, int unitOnOrder, float price, int rate, int amountRate) {
        this.productID = productID;
        this.productName = productName;
        this.discount = discount;
        this.unitOnStock = unitOnStock;
        this.unitOnOrder = unitOnOrder;
        this.price = price;
        this.rate = rate;
        this.amountRate = amountRate;
    }

    public ProductDiscountUnitOnOrder(int productID, String productName, String picture, float discount, float price, int rate, int amountRate) {
        this.productID = productID;
        this.productName = productName;
        this.picture = picture;
        this.discount = discount;
        this.price = price;
        this.rate = rate;
        this.amountRate = amountRate;
    }
 

    public ProductDiscountUnitOnOrder(int productID, String productName, String picture, float discount, float price) {
        this.productID = productID;
        this.productName = productName;
        this.picture = picture;
        this.discount = discount;
        this.price = price;
    }

    public ProductDiscountUnitOnOrder(int productID, String productName, String picture, int unitOnOrder) {
        this.productID = productID;
        this.productName = productName;
        this.picture = picture;
        this.unitOnOrder = unitOnOrder;
    }

    public ProductDiscountUnitOnOrder(int productID, String productName) {
        this.productID = productID;
        this.productName = productName;
    }

   

    public ProductDiscountUnitOnOrder(int productID, String productName, String picture, float discount) {
        this.productID = productID;
        this.productName = productName;
        this.picture = picture;
        this.discount = discount;
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
        return PhoneScreen;
    }

    public void setPhoneScreen(String PhoneScreen) {
        this.PhoneScreen = PhoneScreen;
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

    public ProductDiscountUnitOnOrder() {
    }
    
    
    
    
    
}
