/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Checkout;

import DAL.Account;
import DAL.cart.ProductCart;
import DAL.loginGoogle.GooglePojo;
import DAO.cart.CartDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LEGION
 */
public class CheckoutController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("check","not empty");
            Account acc = (Account) req.getSession().getAttribute("AccSession");
            double totalPrice = 0;
            if (acc != null) {
                    int accountID = acc.getAccountID();
                    List<ProductCart> listCustomerProductCarts = new CartDAO().getCustomerCart(accountID);
                    for (ProductCart customerProduct : listCustomerProductCarts) {
                        totalPrice += customerProduct.getPrice() * customerProduct.getQuantity();
                    }
                    req.getSession().setAttribute("ProductCart", listCustomerProductCarts);
                    req.getSession().setAttribute("Subtotal", totalPrice);
            } else if (req.getSession().getAttribute("GoogleAccount") != null) {
                GooglePojo googlePojo = (GooglePojo) req.getSession().getAttribute("GoogleAccount");
                int accountID = googlePojo.getAccountID();
                List<ProductCart> listCustomerProductCarts = new CartDAO().getCustomerCart(accountID);
                for (ProductCart customerProduct : listCustomerProductCarts) {
                    totalPrice += customerProduct.getPrice() * customerProduct.getQuantity();
                }
                req.getSession().setAttribute("ProductCart", listCustomerProductCarts);
                req.getSession().setAttribute("Subtotal", totalPrice);
            }
            req.getRequestDispatcher("checkout.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(CheckoutController.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e.getMessage());
            resp.sendRedirect("Error");
        }
    }

}
