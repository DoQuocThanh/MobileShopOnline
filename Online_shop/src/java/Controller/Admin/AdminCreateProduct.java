/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Admin;

import DAL.Home.BrandAndQuantity;
import DAO.Home.BrandDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author blabl
 */
public class AdminCreateProduct extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ArrayList<BrandAndQuantity> brandList = new BrandDAO().getBrands();
            req.setAttribute("brand", brandList);
            req.getRequestDispatcher("addProduct.jsp").forward(req, resp);
        } catch (SQLException ex) {
            Logger.getLogger(AdminCreateProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
