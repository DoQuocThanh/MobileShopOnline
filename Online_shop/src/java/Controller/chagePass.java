/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

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

/**
 *
 * @author hieuh
 */
@WebServlet(name = "chagePass", urlPatterns = {"/chagePass"})
public class chagePass extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("check", true);
        request.setAttribute("checkPath", "not empty");
        request.getRequestDispatcher("Profile.jsp").forward(request, response);
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
        String pass = request.getParameter("pass");
        String newpass = request.getParameter("newpass");
        if (request.getSession().getAttribute("CustomerInfor") != null) {
            CustomerAccount inforAccount = (CustomerAccount) request.getSession().getAttribute("CustomerInfor");
            if (inforAccount.getAccount().getPass().equals(pass)) {
                new AccountDAO().update(inforAccount.getAccount().getEmail(), newpass);
                inforAccount.getAccount().setPass(newpass);
                request.getSession().setAttribute("CustomerInfor", inforAccount);
                request.setAttribute("check", true);
                request.setAttribute("mess", true);
                request.getRequestDispatcher("Profile.jsp").forward(request, response);
            } else {
                request.setAttribute("check", true);
                request.setAttribute("mess", false);
                request.getRequestDispatcher("Profile.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("check", true);
            request.setAttribute("mess", false);
            request.getRequestDispatcher("Profile.jsp").forward(request, response);
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
