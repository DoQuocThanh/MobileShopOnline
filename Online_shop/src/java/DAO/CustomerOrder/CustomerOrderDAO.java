/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.CustomerOrder;

import DAL.DBcontext;
import DAL.ListProductOrder;
import DAL.checkout.Order;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LEGION
 */
public class CustomerOrderDAO extends DBcontext {

    ResultSet rs;
    PreparedStatement ps;

    public List<ListProductOrder> getAllDetailCustomerOrders(int ID, String CustomerID) {
        List<ListProductOrder> customerOrders = new ArrayList<>();
        try {
            String sql = "	select o.OrderID, p.ProductName, pd.Coler,  o.OrderDate,pd.Picture, pd.UnitPrice, od.Quantity, o.[Status] from OrderDetails  od\n" +
"	inner join Orders o on od.OrderID = o.OrderID\n" +
"	inner join (ProductDetails pd inner join Products p on pd.ProductID = p.ProductID) on pd.ProductDetailID = od.ProductDetailID\n" +
"	where o.CustomerID = ? and o.OrderID = ?";
            ps = getConnection().prepareCall(sql);
            ps.setString(1, CustomerID);
            ps.setInt(2, ID);
            rs = ps.executeQuery();
            while (rs.next()) {
                int OrderID = rs.getInt("OrderID");
                String ProductName = rs.getString("ProductName");
                String Color = rs.getString("Coler");
                String Picture = rs.getString("Picture");
                Date OrderDate = rs.getDate("OrderDate");
                int Quantity = rs.getInt("Quantity");
                int Price = rs.getInt("UnitPrice");
                int Status = rs.getInt("Status");
                customerOrders.add(new ListProductOrder(OrderID, ProductName, Color, Picture, OrderDate, Price, Quantity, Status));
            }
        } catch (Exception e) {
        }
        return customerOrders;
    }

    public void cancelCustomerOrder(int OrderID) {
        try {
            String sql = "UPDATE Orders\n"
                    + "SET [Status] = 4\n"
                    + "WHERE OrderID = ?;";
            ps = getConnection().prepareCall(sql);
            ps.setInt(1, OrderID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public List<Order> getAllCustomerOrders(String customerID){
        List<Order> getAllOrders = new ArrayList<>();
        try {
            String sql = "select * from Orders where CustomerID = ?";
            ps = getConnection().prepareCall(sql);
            ps.setString(1, customerID);
            rs = ps.executeQuery();
            while (rs.next()) {                
                int OrderID = rs.getInt("OrderID");
                Date orderDate = rs.getDate("OrderDate");
                String ShipAddress = rs.getString("ShipAddress");
                String ShipCountry = rs.getString("ShipCountry");
                int Status = rs.getInt("Status");
                getAllOrders.add(new Order(OrderID, orderDate, ShipAddress, ShipCountry, Status));
            }
            
        } catch (Exception e) {
        }
        return getAllOrders;
    }
}
