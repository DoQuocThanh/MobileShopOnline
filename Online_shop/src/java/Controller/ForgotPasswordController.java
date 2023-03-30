/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAL.Customer;
import DAO.AccountDAO;
import MyUntils.RandomString;
import MyUntils.SendMail;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Random;

/**
 *
 * @author NCC
 */
@WebServlet(name = "ForgotPasswordController", urlPatterns = {"/ForgotPasswordController"})
public class ForgotPasswordController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("forgotPass.jsp").forward(request, response);
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
        String txtFirstName = (String) request.getParameter("txtFirstName");
        String txtLastName = (String) request.getParameter("txtLastName");
        String txtEmail = (String) request.getParameter("txtEmail");
        Customer cus = new Customer(txtEmail.substring(0, 3) + txtFirstName.substring(0, 2), txtFirstName + " " + txtLastName);
        AccountDAO acd = new AccountDAO();
        if (acd.checkAccount(txtEmail, cus.getContactName()) != null) {
            AccountDAO accountDAO = new AccountDAO();
            String newPass = RandomString.RandomStringg(12);
            accountDAO.changedPassword(txtEmail, newPass);
            SendMail.SendMailFunction(txtEmail, "Welcome to us ", "<!DOCTYPE html>\n"
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
                    + "    <h3>We received a request to retrieve the password\n"
                    + "<br>\n"
                    + "Beware of your password!!!</h3><br>\n"
                    + "    <table>\n"
                    + "        <tr>\n"
                    + "            <td>Email:</td>\n"
                    + "            <td>" + txtEmail + "</td>\n"
                    + "        </tr>\n"
                    + "        <tr>\n"
                    + "            <td>Password:</td>\n"
                    + "            <td>" + newPass + "</td>\n"
                    + "        </tr>\n"
                    + "    </table>\n"
                    + "</body>\n"
                    + "\n"
                    + "</html>");
            request.setAttribute("succ", "Password reset successful,pls check email");
            request.getRequestDispatcher("forgotPass.jsp").forward(request, response);
        }
        request.setAttribute("error", "Incorrect Email Or Name");
        request.getRequestDispatcher("forgotPass.jsp").forward(request, response);
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
