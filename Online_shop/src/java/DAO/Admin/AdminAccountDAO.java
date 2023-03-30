/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Admin;

import DAL.Admin.Account;
import DAL.Admin.AccountComment;
import DAL.Admin.Event;
import DAL.DBcontext;
import DAL.Admin.Product;
import DAL.Admin.ProductDetails;
import DAL.Admin.AccountCustomer;
import DAL.Admin.CustomerOrder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Date;

/**
 *
 * @author blabl
 */
public class AdminAccountDAO extends DBcontext {

    ResultSet rs;
    PreparedStatement ps;

    public Account getAccount(String email, String pass) {

        Account acc = new Account();
        try {
            String sql = "select * from Accounts as a left join Employees"
                    + " as b on a.EmployeeID = b.EmployeeID where  a.Role != 2 And a.Status != 2  And a.Email = ? And a.Password = ? ";
            ps = getConnection().prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, pass);
            rs = ps.executeQuery();

            while (rs.next()) {
               
                    int accountID = rs.getInt("accountID");
                    int employeeID = rs.getInt("employeeID");
                    int role = rs.getInt("role");
                    String lastname = rs.getString("lastname");
                    String firstname = rs.getString("firstname");
                    int status = rs.getInt("status");
                    acc = new Account(accountID, email, pass, employeeID, role, lastname, firstname, status);
                
            }

        } catch (SQLException e) {

        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }
        return acc;
    }
    
   
}
