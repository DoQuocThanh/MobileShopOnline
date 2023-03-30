/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Admin;

import DAL.Admin.Product;
import DAL.Home.Brands;
import DAO.Admin.AdminDAO;
import DAO.Home.BrandDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author blabl
 */
public class AdminManageProduct extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        try {
            ArrayList<Product> list = new AdminDAO().getProduct();
            List<Brands> brandlist = new BrandDAO().getAllBrands();
            req.setAttribute("Product", list);
            req.setAttribute("Brand", brandlist);
            req.getRequestDispatcher("manageProduct.jsp").forward(req, resp);
        }   catch (SQLException ex) {
            Logger.getLogger(AdminManageProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
