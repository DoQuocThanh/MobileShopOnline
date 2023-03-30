/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.Account;
import DAL.Customer;
import DAL.CustomerAccount;
import DAO.AccountDAO;
import DAO.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hieuh
 */
@WebServlet(name = "UpdateProfile", urlPatterns = {"/UpdateProfile"})
public class UpdateProfile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String pass = request.getParameter("pass");

        Account account;
        try {
            account = new AccountDAO().getAccount(email, pass);
            CustomerAccount customerAccount = new CustomerDAO().getCustomerInfor(account.getCustomerID().getCustomerID());
            Customer customer = customerAccount.getCustomer();
            customer.setContactName(name);
            customer.setAddress(address);
            new CustomerDAO().updateCustomer(customer);
            customerAccount = new CustomerDAO().getCustomerInfor(account.getCustomerID().getCustomerID());
            customerAccount.getAccount().setPass(pass);
            request.getSession().setAttribute("CustomerInfor", customerAccount);
            response.sendRedirect("ProfileController?req=profile");
        } catch (SQLException ex) {
            Logger.getLogger(UpdateProfile.class.getName()).log(Level.SEVERE, null, ex);
        }

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
