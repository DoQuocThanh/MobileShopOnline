/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Admin;

import DAL.Admin.OrderDetail;
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
public class AdminOrderDetails extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderID = req.getParameter("orderID");
        ArrayList<OrderDetail> list = new AdminDAO().getListOrderDetailsbyOrderId(orderID);
        req.setAttribute("list", list);
        req.getRequestDispatcher("orderDetail.jsp").forward(req, resp);
    }
    
}
