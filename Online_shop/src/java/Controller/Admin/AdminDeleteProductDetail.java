/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Admin;

import DAO.Admin.AdminDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author blabl
 */
public class AdminDeleteProductDetail extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productDetailID = req.getParameter("productDetailID");
        String productID = req.getParameter("productID");
        String employeeID = req.getParameter("employeeID");
        String mess = new AdminDAO().deleteProductDetail(productDetailID, employeeID);
        resp.sendRedirect("productdetail?productID="+productID);
    }
    
}
