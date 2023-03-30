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
import MyUntils.SendMail;
import java.io.IOException;
import jakarta.servlet.ServletException;
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
public class SignUpController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("check", "not empty");
        request.getRequestDispatcher("./signUp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String txtFirstName = (String) request.getParameter("txtFirstName");
            String txtLastName = (String) request.getParameter("txtLastName");
            String txtEmail = (String) request.getParameter("txtEmail");
            String txtPass = (String) request.getParameter("txtPass");
            String txtAddress = (String) request.getParameter("txtAddress");
            boolean gender = (Boolean.valueOf(request.getParameter("gender")));
            AccountDAO acd = new AccountDAO();
            if (acd.checkAccount(txtEmail, "") != null) {
                request.setAttribute("txtFirstName", txtFirstName);
                request.setAttribute("txtLastName", txtLastName);
                request.setAttribute("txtAddress", txtAddress);
                request.setAttribute("gender", gender);
                request.setAttribute("ERROR", "email already exist!");
                request.getRequestDispatcher("signUp.jsp").forward(request, response);
            } else {
                // id = 3 chu cái dau Email và 2 chu cai dau FirstName ..... ContactName = txtFirstName + " " + txtLastName
                Customer cus = new Customer(txtEmail.substring(0, 2) + txtFirstName.substring(0, 2), txtFirstName + " " + txtLastName);
                cus.setAddress(txtAddress);
                cus.setGender(gender);

                String cust = cus.getCustomerID();
                if (new CustomerDAO().getCustomerInfor(cust) != null) {
                    do {
                        try {
                            int count = Integer.parseInt(cust.substring(cust.length() - 1, cust.length()));
                            cust = cust.substring(0, cust.length() - 1) + "1";
                        } catch (Exception e) {
                            cust += "1";
                        }
                    } while (new CustomerDAO().getCustomerInfor(cust) != null);
                    
                }else{
                     cust += "1";
                }
                cus.setCustomerID(cust);
                Account acc = new Account(txtEmail, txtPass, cus, null);
//                acc.set
                if (acd.addAccount(cus, acc) != 0) {
//                    SendMail.SendMailFunction(txtEmail, "tieu de", acc.toString());
                    request.getSession().setAttribute("AccSession", acc);
                    CustomerAccount inforAccount = new CustomerDAO().getCustomerInfor(acc.getCustomerID().getCustomerID());
                    inforAccount.getAccount().setPass(txtPass);
                    request.getSession().setAttribute("CustomerInfor", inforAccount);
                    SendMail.SendMailFunction(acc.getEmail(), "Welcome to us ", "<!DOCTYPE html>\n"
                            + "<html lang=\"en\">\n"
                            + "\n"
                            + "<head>\n"
                            + "    <meta charset=\"UTF-8\">\n"
                            + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                            + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                            + "    <title>ok</title>\n"
                            + "</head>\n"
                            + "\n"
                            + "<body>\n"
                            + "    <h3>Please do not disclose information outside</h3><br>\n"
                            + "    <table>\n"
                            + "        <tr>\n"
                            + "            <td>Email:</td>\n"
                            + "            <td>" + acc.getEmail() + "</td>\n"
                            + "        </tr>\n"
                            + "        <tr>\n"
                            + "            <td>Password:</td>\n"
                            + "            <td>" + acc.getPass() + "</td>\n"
                            + "        </tr>\n"
                            + "    </table>\n"
                            + "</body>\n"
                            + "\n"
                            + "</html>");
                    response.sendRedirect("home"); 
                } else {
                    request.setAttribute("txtFirstName", txtFirstName);
                    request.setAttribute("txtLastName", txtLastName);
                    request.setAttribute("txtAddress", txtAddress);
                    request.setAttribute("gender", gender);
                    request.setAttribute("ERROR", "Thong tin đã tồn tại");
                    request.getRequestDispatcher("signUp.jsp").forward(request, response);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
