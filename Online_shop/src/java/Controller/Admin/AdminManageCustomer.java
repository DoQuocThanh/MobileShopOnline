/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Admin;

import DAL.Admin.AccountCustomer;
import DAL.Admin.Product;
import DAL.Home.Brands;
import DAO.Admin.AdminDAO;
import DAO.Home.BrandDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author blabl
 */

public class AdminManageCustomer extends HttpServlet{
  

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
            ArrayList<AccountCustomer> list = new AdminDAO().getAccountCustomer();
            req.setAttribute("list", list);
            req.getRequestDispatcher("manageAccount.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id =  req.getParameter("accountID");
        String status =  req.getParameter("status");
        if(status.equals("1")){
            status = "2";
        }else{
            status = "1";
        }
        new AdminDAO().changeStatusCustomer(id, status,"update Accounts Set Status = ? where accountid = ?");
        resp.sendRedirect("customer");
    }
    
}
