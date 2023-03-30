/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAL.CustomerAccount;
import DAL.ListProductOrder;
import DAL.checkout.Order;
import DAO.CustomerOrder.CustomerOrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LEGION
 */
public class UpdateOrder extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("checkPath","not empty");
            String choice = req.getParameter("type");
        int orderID = Integer.parseInt(req.getParameter("orderID"));
        CustomerAccount customerAccount = (CustomerAccount) req.getSession().getAttribute("CustomerInfor");
        List<Order> showCustomerOrders = new ArrayList<>();
        switch (choice) {
            case "cancel":
                new CustomerOrderDAO().cancelCustomerOrder(orderID);
                showCustomerOrders = new CustomerOrderDAO().getAllCustomerOrders(customerAccount.getCustomer().getCustomerID());
                req.getSession().setAttribute("CustomerOrder", showCustomerOrders);
                break;
            case "view":
                List<ListProductOrder> showDetailCustomerOrders = new CustomerOrderDAO().getAllDetailCustomerOrders(orderID,customerAccount.getCustomer().getCustomerID());
                req.setAttribute("DetailCustomerOrder", showDetailCustomerOrders);
                req.setAttribute("ViewDetail", "not empty");
                break;
            default:
                showCustomerOrders = new CustomerOrderDAO().getAllCustomerOrders(customerAccount.getCustomer().getCustomerID());
                req.getSession().setAttribute("CustomerOrder", showCustomerOrders);
                break;
        }
        req.getRequestDispatcher("Profile.jsp").forward(req, resp);
        } catch (Exception e) {
            resp.sendRedirect("Error");
        }
    }

}
