/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Admin;

import DAL.Admin.AccountComment;
import DAL.Admin.AccountCustomer;
import DAO.Admin.AdminDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author blabl
 */
public class AdminManageComment extends HttpServlet{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            ArrayList<AccountComment> list = new AdminDAO().getAccountComment();
            req.setAttribute("list", list);
            req.getRequestDispatcher("manageComment.jsp").forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id =  req.getParameter("commentID");
        String status =  req.getParameter("status");
        if(status.equals("1")){
            status = "2";
        }else{
            status = "1";
        }
        new AdminDAO().changeStatusCustomer(id, status,"update Comments Set Status = ? where commentID = ?");
        resp.sendRedirect("comment");
        
    }
}
