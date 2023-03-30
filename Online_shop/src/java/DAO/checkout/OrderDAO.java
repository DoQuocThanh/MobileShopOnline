/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.checkout;

import DAL.Account;
import DAL.Customer;
import DAL.CustomerAccount;
import DAL.DBcontext;
import DAL.cart.ProductCart;
import DAL.checkout.GuestOrder;
import DAL.checkout.Order;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author LEGION
 */
public class OrderDAO extends DBcontext{
    public Order addOrderGuest(GuestOrder guest) {
        Order order = new Order();
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String sqlCustomer = "insert into Customers (CustomerID,CompanyName,ContactName,ContactTitle,[Address]) values(?,?,?,?,?);";
            PreparedStatement ps = getConnection().prepareStatement(sqlCustomer);
            ps.setString(1, guest.getCustomerID());
            ps.setString(2, "");
            ps.setString(3, guest.getName());
            ps.setString(4, "");
            ps.setString(5, guest.getAddress());
            ps.executeUpdate();
            //
            String sql = "insert into Orders (CustomerID,OrderDate,RequiredDate,ShipAddress,ShipCity,PhoneNumber,ShipCountry,[Status]) values(?,?,?,?,?,?,?,?);";
            ps = getConnection().prepareStatement(sql);
            String oderDate = dateFormat.format(calendar.getTime());
            calendar.add(Calendar.DATE, 7);
            String requiredDate = dateFormat.format(calendar.getTime());
            ps.setString(1, guest.getCustomerID());
            ps.setDate(2, Date.valueOf(oderDate));
            ps.setDate(3, Date.valueOf(requiredDate));
            ps.setString(4, guest.getAddress());
            ps.setString(5, guest.getCity());
            ps.setString(6, guest.getPhoneNumber());
            ps.setString(7, "VietNam");
            ps.setInt(8, 0);
            ps.executeUpdate();
            //
            String sqlgetOrder = "select top(1)* from Orders order by OrderID desc";
            ps = getConnection().prepareStatement(sqlgetOrder);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int OrderID = rs.getInt("OrderID");
                order = new Order(OrderID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }

    public Order addOrderCustomer(Customer customer) {
        Order order = new Order();
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String sql = "insert into Orders (CustomerID,OrderDate,RequiredDate,ShipAddress,ShipCity,PhoneNumber,ShipCountry,[Status]) values(?,?,?,?,?,?,?,?);";
            PreparedStatement ps = getConnection().prepareStatement(sql);
            String oderDate = dateFormat.format(calendar.getTime());
            calendar.add(Calendar.DATE, 7);
            String requiredDate = dateFormat.format(calendar.getTime());
            ps.setString(1, customer.getCustomerID());
            ps.setDate(2, Date.valueOf(oderDate));
            ps.setDate(3, Date.valueOf(requiredDate));
            ps.setString(4, customer.getAddress());
            ps.setString(5, "Ha noi");
            ps.setString(6, "1234567890");
            ps.setString(7, "VietNam");
            ps.setInt(8, 0);
            ps.executeUpdate();
            //
            String sqlgetOrder = "select top(1)* from Orders order by OrderID desc";
            ps = getConnection().prepareStatement(sqlgetOrder);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int OrderID = rs.getInt("OrderID");
                order = new Order(OrderID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }
    
    public void addOrderDetail(Order order, List<ProductCart> listProductOrders) {
        try {
            for (ProductCart listProductOrder : listProductOrders) {
                String sql = "insert into [OrderDetails] (OrderID,ProductDetailID,Quantity) values(?,?,?);";
                PreparedStatement ps = getConnection().prepareStatement(sql);
                ps.setInt(1, order.getOrderID());
                ps.setInt(2, listProductOrder.getProductDetailID());
                ps.setInt(3, listProductOrder.getQuantity());
                ps.executeUpdate();
            }
        } catch (Exception e) {
        }
    }
    
    public void deleteCartDetail(int accountID){
        try {
            String sql;
                sql = "DELETE FROM CartDetails WHERE AccountID = ?;";
                PreparedStatement ps = getConnection().prepareCall(sql);
                ps.setInt(1, accountID);
                ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
