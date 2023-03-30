/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

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

    public ProductDetail() {
    }

    public ProductDetail(int UnitsInStock, int ProductID, String ProductName, String Picture, double Price) {
        super(ProductID, ProductName, Picture, Price);
        this.UnitsInStock = UnitsInStock;
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
    
}
