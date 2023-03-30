/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Checkout;

import DAL.Account;
import DAL.Customer;
import DAL.CustomerAccount;
import DAL.cart.ProductCart;
import DAL.checkout.GuestOrder;
import DAL.checkout.Order;
import DAL.loginGoogle.GooglePojo;
import DAO.checkout.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author LEGION
 */
public class OrderController extends HttpServlet {

    OrderDAO OrderDAO = new OrderDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account acc = (Account) req.getSession().getAttribute("AccSession");
        if (req.getSession().getAttribute("ProductCart") == null) {
            req.setAttribute("msg", "Your cart had no product for order !");
            req.getRequestDispatcher("checkout.jsp").forward(req, resp);
        } else {
            List<ProductCart> listProductCarts = (List<ProductCart>) req.getSession().getAttribute("ProductCart");
            if (acc == null) {
                if (req.getSession().getAttribute("GoogleAccount") != null) {
                    GooglePojo googlePojo = (GooglePojo) req.getSession().getAttribute("GoogleAccount");
                int accountID = googlePojo.getAccountID();
                    CustomerAccount customerInfor = (CustomerAccount) req.getSession().getAttribute("CustomerInfor");
                    Customer customer = customerInfor.getCustomer();
                    Order order = new OrderDAO().addOrderCustomer(customer);
                    OrderDAO.addOrderDetail(order, listProductCarts);
                    OrderDAO.deleteCartDetail(accountID);
                } else {
                    String Name = req.getParameter("txtFirstName") + req.getParameter("txtLastName");
                    String Email = req.getParameter("txtEmail");
                    String PhoneNumber = req.getParameter("txtPhoneNumber");
                    String Address = req.getParameter("txtAddress");
                    String City = req.getParameter("txtCity");
                    String customerID = randomString(5);
                    GuestOrder guest = new GuestOrder(customerID, Name, Email, PhoneNumber, Address, City);
                    Order order = new OrderDAO().addOrderGuest(guest);
                    OrderDAO.addOrderDetail(order, listProductCarts);
                }
            } else {
                CustomerAccount customerInfor = (CustomerAccount) req.getSession().getAttribute("CustomerInfor");
                Customer customer = customerInfor.getCustomer();
                Order order = new OrderDAO().addOrderCustomer(customer);
                OrderDAO.addOrderDetail(order, listProductCarts);
                OrderDAO.deleteCartDetail(acc.getAccountID());
            }
            req.getSession().removeAttribute("ProductCart");
            req.getSession().removeAttribute("Subtotal");
            resp.sendRedirect("shop?Order='not empty'");
        }
    }

    public String randomString(int n) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }

}
