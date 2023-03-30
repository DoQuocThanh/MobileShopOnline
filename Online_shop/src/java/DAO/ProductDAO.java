/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAL.Comments;
import DAL.DBcontext;
import DAL.Product;
import DAL.ProductDetail;
import DAL.ProductInfor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LEGION
 */
public class ProductDAO extends DBcontext {
    ResultSet rs;
    PreparedStatement ps;
    public List<ProductInfor> getAllProduct() throws SQLException {
        List<ProductInfor> listProduct = new ArrayList<ProductInfor>();
        Product p = new Product();
        Comments c = new Comments();
        ProductDetail pd = new ProductDetail();
        try {

            String sql = "select p.ProductID, p.Picture, ProductName, p.Price, avg(c.Rate) as Average "
                    + "from Comments c inner join Products p on c.ProductID = p.ProductID "
                    + "group by c.ProductID, p.ProductName, p.Price, p.ProductID, p.Picture";
            ps = getConnection().prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                String Picture = rs.getString("Picture");
                double Price = rs.getDouble("Price");
                p = new Product(ProductID, ProductName, Picture, Price);
                int Rate = rs.getInt("Average");
                c = new Comments(Rate);
                int jij;
                listProduct.add(new ProductInfor(c, p));
            }
        } catch (SQLException e) {
            getConnection().rollback();
        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }
        return listProduct;
    }

    public List<ProductInfor> searchProducts(String condition) throws SQLException {
        List<ProductInfor> listProduct = new ArrayList<ProductInfor>();
        Product p = new Product();
        Comments c = new Comments();
        try {
            String sql = "select p.ProductID, p.Picture, ProductName, p.Price, avg(c.Rate) as Average \n"
                    + "from Comments c inner join Products p on c.ProductID = p.ProductID and ProductName COLLATE SQL_Latin1_General_Cp850_CI_AS like '%'+?+'%'\n"
                    + "group by c.ProductID, p.ProductName, p.Price, p.ProductID, p.Picture";
            ps = getConnection().prepareCall(sql);
            ps.setString(1, condition);
            rs = ps.executeQuery();
            while (rs.next()) {
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                String Picture = rs.getString("Picture");
                double Price = rs.getDouble("Price");
                p = new Product(ProductID, ProductName, Picture, Price);
                int Rate = rs.getInt("Average");
                c = new Comments(Rate);
                listProduct.add(new ProductInfor(c, p));
            }
        } catch (SQLException e) {
            getConnection().rollback();
        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }
        return listProduct;
    }

    public List<ProductInfor> sortProducts(String condition) throws SQLException {
        List<ProductInfor> listProduct = new ArrayList<ProductInfor>();
        Product p = new Product();
        Comments c = new Comments();
        String sql = "";
        if (condition.equals("latest")) {
            sql = "select p.ProductID, p.Picture, ProductName, p.Price, avg(c.Rate) as Average \n"
                    + "from Comments c inner join Products p on c.ProductID = p.ProductID \n"
                    + "group by c.ProductID, p.ProductName, p.Price, p.ProductID, p.Picture\n"
                    + "order by p.ProductID desc";
        } else if (condition.equals("popularity")) {
            sql = "select * from Products order by ProductID desc";
        }
        try {
            ps = getConnection().prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                String Picture = rs.getString("Picture");
                double Price = rs.getDouble("Price");
                p = new Product(ProductID, ProductName, Picture, Price);
                int Rate = rs.getInt("Average");
                c = new Comments(Rate);
                listProduct.add(new ProductInfor(c, p));
            }
        } catch (SQLException e) {
            getConnection().rollback();
        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }
        return listProduct;
    }
}
