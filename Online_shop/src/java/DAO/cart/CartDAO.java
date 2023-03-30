/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.cart;

import DAL.DBcontext;
import DAL.cart.ProductCart;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LEGION
 */
public class CartDAO extends DBcontext {

    ResultSet rs;
    PreparedStatement ps;

    public ProductCart getGProductCart(int ProductDetailID) throws SQLException {
        ProductCart gProduct = new ProductCart();
        try {
            String sql = "select pd.ProductDetailID, pd.ProductID, p.ProductName, pd.Picture, pd.UnitPrice, pd.Coler \n"
                    + "from Products p inner join ProductDetails pd  \n"
                    + "on  p.ProductID = pd.ProductID and pd.ProductDetailID = ?";
            ps = getConnection().prepareCall(sql);
            ps.setInt(1, ProductDetailID);
            rs = ps.executeQuery();
            while (rs.next()) {
                String Color = rs.getString("Coler");
                String ProductName = rs.getString("ProductName");
                String Picture = rs.getString("Picture");
                int productDetailID = rs.getInt("ProductDetailID");
                Double Price = rs.getDouble("UnitPrice");
                gProduct = new ProductCart(productDetailID, ProductName, Price, Picture, Color);
            }
        } catch (SQLException e) {
            getConnection().rollback();
        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }
        return gProduct;
    }

    public List<ProductCart> getCustomerCart(int AccountID) throws SQLException {
        ProductCart customerProduct = new ProductCart();
        List<ProductCart> getListCustomerProduct = new ArrayList<>();
        try {
            String sql = "select c.Quantity, pd.ProductDetailID, pd.ProductID, p.ProductName, pd.Picture, pd.UnitPrice, pd.Coler from Accounts acc\n"
                    + "inner join (CartDetails c inner join (ProductDetails pd inner join Products p on p.ProductID = pd.ProductID) \n"
                    + "on c.ProductDetailID = pd.ProductDetailID) \n"
                    + "on c.AccountID = acc.AccountID and c.AccountID = ?";
            ps = getConnection().prepareCall(sql);
            ps.setInt(1, AccountID);
            rs = ps.executeQuery();
            while (rs.next()) {
                int Quantity = rs.getInt("Quantity");
                String Color = rs.getString("Coler");
                String ProductName = rs.getString("ProductName");
                String Picture = rs.getString("Picture");
                int productDetailID = rs.getInt("ProductDetailID");
                Double Price = rs.getDouble("UnitPrice");
                customerProduct = new ProductCart(productDetailID, ProductName, Quantity, Price, Picture, Color);
                getListCustomerProduct.add(customerProduct);
            }
        } catch (SQLException e) {
            getConnection().rollback();
        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }
        return getListCustomerProduct;
    }

    public void updateCustomerProductCart(int accountID, int productDetailID, int quantity) throws SQLException {
        try {
            String sql = "UPDATE CartDetails \n"
                    + "SET Quantity = ?\n"
                    + "WHERE ProductDetailID = ? and AccountID=?;";
            ps = getConnection().prepareCall(sql);
            ps.setInt(1, quantity);
            ps.setInt(2, productDetailID);
            ps.setInt(3, accountID);
            ps.executeUpdate();
        } catch (SQLException e) {
            getConnection().rollback();
        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }
    }

    public void addCustomerProductCart(int accountID, int productDetailID) throws SQLException {
        try {
            String sql = "INSERT INTO CartDetails (AccountID, ProductDetailID, Quantity)\n"
                    + "VALUES (?, ?, 1);";
            ps = getConnection().prepareCall(sql);
            ps.setInt(1, accountID);
            ps.setInt(2, productDetailID);
            ps.executeUpdate();
        } catch (SQLException e) {
            getConnection().rollback();
        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }
    }
    
    public void DeleteCustomerProductCart(int accountID, int productDetailID) throws SQLException {
        try {
            String sql = "DELETE FROM CartDetails WHERE AccountID = ? and ProductDetailID = ?;";
            ps = getConnection().prepareCall(sql);
            ps.setInt(1, accountID);
            ps.setInt(2, productDetailID);
            ps.executeUpdate();
        } catch (SQLException e) {
            getConnection().rollback();
        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }
    }
}
