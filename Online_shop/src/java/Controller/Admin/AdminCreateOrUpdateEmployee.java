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
import java.util.Date;

/**
 *
 * @author blabl
 */
public class AdminCreateOrUpdateEmployee extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String employeeId = req.getParameter("employeeID");
        Employee em = new AdminDAO().getEmployeebyEmployeeId(employeeId);
        req.setAttribute("employeeId", em);
        if(employeeId != null){
            req.setAttribute("type", "Update");
        }
        req.getRequestDispatcher("updateEmployee.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String email = req.getParameter("email");
        String pass = req.getParameter("password");
        String firstName = req.getParameter("firstname");
        String birthDate = req.getParameter("birthdate");
        String address = req.getParameter("address");
        System.out.println(req.getParameter("type"));
        if(req.getParameter("typeP") != null ){
            String employeeId = req.getParameter("employeeID");
            new AdminDAO().UpdateEmployee(email, pass, firstName, birthDate, address, employeeId);
            resp.sendRedirect("employee?employeeID="+employeeId);
            
        }else{
            new AdminDAO().CreateEmployee(email, pass, firstName, birthDate, address);
            resp.sendRedirect("employee");
        }
        
         
    }

}
