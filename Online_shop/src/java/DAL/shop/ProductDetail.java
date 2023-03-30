/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.shop;

/**
 *
 * @author LEGION
 */
public class ProductDetail extends Product{
    private int ProductDetail;
    private int ProductStorage;
    private double UnitPrice;
    private String Color;
    private int UnitsInStock;
    private int UnitsOnOrder;
    private int Count;
    private int totalComment;
    private int totalRate;
    public ProductDetail() {
    }

    public ProductDetail(String Color, int Count) {
        this.Color = Color;
        this.Count = Count;
    }

    public ProductDetail(int ProductStorage, int Count) {
        this.ProductStorage = ProductStorage;
        this.Count = Count;
    }

    public ProductDetail(int ProductDetail, int ProductStorage, double UnitPrice, String Color, int UnitsInStock, int UnitsOnOrder, int ProductID, String ProductName, String Picture) {
        super(ProductID, ProductName, Picture);
        this.ProductDetail = ProductDetail;
        this.ProductStorage = ProductStorage;
        this.UnitPrice = UnitPrice;
        this.Color = Color;
        this.UnitsInStock = UnitsInStock;
        this.UnitsOnOrder = UnitsOnOrder;
    }

    public ProductDetail(int ProductDetail, int ProductStorage, double UnitPrice, String Color, int UnitsInStock, int UnitsOnOrder, int totalComment, int totalRate, int ProductID, String ProductName, String Picture) {
        super(ProductID, ProductName, Picture);
        this.ProductDetail = ProductDetail;
        this.ProductStorage = ProductStorage;
        this.UnitPrice = UnitPrice;
        this.Color = Color;
        this.UnitsInStock = UnitsInStock;
        this.UnitsOnOrder = UnitsOnOrder;
        this.totalComment = totalComment;
        this.totalRate = totalRate;
    }
    
    

    public ProductDetail(int ProductID, int ProductDetail, int ProductStorage, double UnitPrice, String Color, int UnitsInStock, int UnitsOnOrder, String Picture) {
        super(ProductID, Picture);
        this.ProductDetail = ProductDetail;
        this.ProductStorage = ProductStorage;
        this.UnitPrice = UnitPrice;
        this.Color = Color;
        this.UnitsInStock = UnitsInStock;
        this.UnitsOnOrder = UnitsOnOrder;
    }

    public ProductDetail(int ProductDetail, int ProductStorage, double UnitPrice, String Color, int UnitsInStock, 
            int UnitsOnOrder, int ProductID, String ProductName, int BrandID, String Chip, int Ram, String Pin, 
            String OperatingSystem, String PhoneScreen, String Picture, double Price) {
        super(ProductID, ProductName, BrandID, Chip, Ram, Pin, OperatingSystem, PhoneScreen, Picture, Price);
        this.ProductDetail = ProductDetail;
        this.ProductStorage = ProductStorage;
        this.UnitPrice = UnitPrice;
        this.Color = Color;
        this.UnitsInStock = UnitsInStock;
        this.UnitsOnOrder = UnitsOnOrder;
    }

    public int getTotalComment() {
        return totalComment;
    }

    public void setTotalComment(int totalComment) {
        this.totalComment = totalComment;
    }

    public int getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(int totalRate) {
        this.totalRate = totalRate;
    }
    
    

    public ProductDetail(int ProductStorage) {
        this.ProductStorage = ProductStorage;
    }

    public ProductDetail(String Color) {
        this.Color = Color;
    }
    
    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        this.Count = count;
    }

    public ProductDetail(int UnitsInStock, int ProductID, String ProductName, String Picture, double Price) {
        super(ProductID, ProductName, Picture, Price);
        this.UnitsInStock = UnitsInStock;
    }
    
    public int getProductDetail() {
        return ProductDetail;
    }
    
    public void setProductDetail(int ProductDetail) {
        this.ProductDetail = ProductDetail;
    }


    public int getProductStorage() {
        return ProductStorage;
    }

    public void setProductStorage(int ProductStorage) {
        this.ProductStorage = ProductStorage;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double UnitPrice) {
        this.UnitPrice = UnitPrice;
    }


    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
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

    @Override
    public String toString() {
        return super.toString(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
}