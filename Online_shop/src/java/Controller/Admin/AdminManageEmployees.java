/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Admin;

import DAL.Admin.Employee;
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
public class AdminManageEmployees extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                ArrayList<Employee> list = new AdminDAO().getAccountEmployee();
                req.setAttribute("list", list);
                req.getRequestDispatcher("manageEmployee.jsp").forward(req, resp);
                

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id =  req.getParameter("accountID");
        String status =  req.getParameter("status");
        if(status.equals("Baned")){
            status = "2";
            req.getSession().removeAttribute("Employee");
        }else
        if(status.equals("Permission"))
        {
            status = "3";
        }else{
            status = "1";
        }
        new AdminDAO().changeStatusCustomer(id, status,"update Accounts Set Status = ? where accountid = ?");
        resp.sendRedirect("employee");
    
    }
    
}
