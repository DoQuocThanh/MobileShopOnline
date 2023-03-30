/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Admin;

import DAL.Admin.Account;
import DAO.Admin.AdminAccountDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author blabl
 */
public class LoginAdmin extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("Employee") != null ){
            resp.sendRedirect("product");
        }else if(req.getSession().getAttribute("Admin") != null){
            resp.sendRedirect("employee");
        }
        
        else
        {    
        req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
   }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String pass = req.getParameter("password");
        
        Account acc = new AdminAccountDAO().getAccount(email, pass);
        
        if(acc.getRole() == 0){
            req.setAttribute("msg", "Email or Password fail");
            req.getRequestDispatcher("login.jsp").forward(req, resp);  
        }else if(acc.getRole() == 1 && acc.getRole() != 2){
            req.getSession().setAttribute("Employee", acc);
            resp.sendRedirect("product");
        }else if(acc.getRole() == 3){
            req.getSession().setAttribute("Admin", "Admin");
            resp.sendRedirect("employee");
        }
        
    }

}
