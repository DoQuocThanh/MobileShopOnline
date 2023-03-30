/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.Account;
import DAL.CustomerAccount;
import DAL.ListProductOrder;
import DAL.checkout.Order;
import DAO.AccountDAO;
import DAO.CustomerOrder.CustomerOrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author NCC
 */
@WebServlet(name = "ProfileController", urlPatterns = {"/ProfileController"})
public class ProfileController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("checkPath","not empty");
        if (request.getSession().getAttribute("CustomerInfor") != null || request.getSession().getAttribute("GoogleAccount") != null) {
            CustomerAccount inforAccount = (CustomerAccount) request.getSession().getAttribute("CustomerInfor");
            List<Order> showCustomerOrders = new CustomerOrderDAO().getAllCustomerOrders(inforAccount.getCustomer().getCustomerID());
            request.getSession().setAttribute("CustomerOrder", showCustomerOrders);
            request.getRequestDispatcher("Profile.jsp").forward(request, response);
        } else {
            response.sendRedirect("home");
        }
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
