package Controller.Admin;

import DAL.Admin.Product;
import DAL.Admin.ProductDetails;
import DAL.Home.BrandAndQuantity;

import DAO.Admin.AdminDAO;
import DAO.Home.BrandDAO;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author blabl
 */
public class AdminProductDetails extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("productID");
            if (id == null) {
                
            }
            Product product = new AdminDAO().getProductByProductID(id);
            ArrayList<ProductDetails> productdetail = new AdminDAO().getProductDetailById(id);
            ArrayList<BrandAndQuantity> brandList = new BrandDAO().getBrands();
            req.setAttribute("brand", brandList);
            req.setAttribute("product", product);
            req.setAttribute("productdetail", productdetail);
            req.getRequestDispatcher("productDetail.jsp").forward(req, resp);
        } catch (SQLException ex) {
            Logger.getLogger(AdminProductDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("productid");
        String name = req.getParameter("productname");
        String brandID = req.getParameter("product_brand");
        String ram = req.getParameter("ram");
        String chip = req.getParameter("chip");
        String phoneScreen = req.getParameter("phonescreen");
        String price = req.getParameter("price");
        String picture = req.getParameter("srcProductDetail");
        String operatingSystem = req.getParameter("operatingSystem");
        String pin = req.getParameter("pin");
        String employeeID = req.getParameter("employeeID");
        String type = req.getParameter("type");
        if (type != null) {
            new AdminDAO().addProduct(productId, name, brandID, ram, chip, phoneScreen, price, operatingSystem, picture, pin, employeeID);
            resp.sendRedirect("product");
        } else {
            new AdminDAO().updateProduct(productId, name, brandID, ram, chip, phoneScreen, price, operatingSystem, picture, pin, employeeID);
            resp.sendRedirect("productdetail?productID=" + productId);
        }

    }

}
