/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Admin;

import DAL.Admin.HistoryProduct;
import DAL.Admin.HistoryProductDetail;
import DAO.Admin.AdminDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author blabl
 */
public class AdminProductHistory extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productID = req.getParameter("productID");
        ArrayList<HistoryProductDetail> list = new AdminDAO().getProductDetailHistory(productID);
        req.setAttribute("list", list);
        req.getRequestDispatcher("productHistoryDetail.jsp").forward(req, resp);
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<HistoryProduct> list = new AdminDAO().getProductHistory();
        req.setAttribute("list", list);
        req.getRequestDispatcher("productHistory.jsp").forward(req, resp);
    }
    
    
}
