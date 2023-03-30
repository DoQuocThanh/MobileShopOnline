/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Admin;

import DAL.Admin.MonthRevenue;
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
public class AdminManageDashBoard extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        float RevenueThisMonthOrdered = new AdminDAO().getRevenueThisMonthOrdered();
        float RevenueThisMonthInOrder = new AdminDAO().getRevenueThisMonthInOrder();
        int Order = new AdminDAO().getAmountOrderThisMonth();
        int Customer = new AdminDAO().getAmountCustomerInShop();
        ArrayList<MonthRevenue> list = new AdminDAO().getListRevenueOrdered();
        req.setAttribute("ordered", RevenueThisMonthOrdered);
        req.setAttribute("inorder", RevenueThisMonthInOrder);
        req.setAttribute("order", Order);
        req.setAttribute("cus", Customer);
        req.setAttribute("list", list);
        req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
    }
    
}
