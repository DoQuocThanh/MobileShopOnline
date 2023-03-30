/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAL.Account;
import DAL.Customer;
import DAL.CustomerAccount;
import DAL.DBcontext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LEGION
 */
public class CustomerDAO {

    ResultSet rs;
    PreparedStatement ps;
    Connection connection;

    public CustomerDAO() {
        try {
            connection = new DBcontext().getConnection();
        } catch (Exception e) {
            System.out.println("loi ket noi DB cua AccountDAO ");
        }
    }

    public static void main(String[] args) {
        try {
            CustomerDAO customerDAO = new CustomerDAO();
            CustomerAccount customerAccount = customerDAO.getCustomerInfor("ABCDE");
        } catch (Exception ex) {
        }

    }
    

    public CustomerAccount getCustomerInfor(String CusID) {
        CustomerAccount customerAccount = null;
        try {
            String sql = ""
                    + "SELECT a.[AccountID]\n"
                    + "      ,a.[Email]\n"
                    + "      ,a.[Password]\n"
                    + "      ,a.[CustomerID]\n"
                    + "	  ,c.ContactName\n"
                    + "	  ,c.CompanyName\n"
                    + "	  ,c.Address\n "
                    + ",c.ContactTitle "
                    + ",c.[Gender]"
                    + "      ,a.[EmployeeID]\n"
                    + "      ,a.[Role]\n"
                    + "      ,a.[Status]\n"
                    + "  FROM [Accounts] a JOIN Customers c ON a.CustomerID = c.CustomerID\n"
                    + "   where c.CustomerID = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, CusID);
            rs = ps.executeQuery();
            if (rs.next()) {
                int AccountID = rs.getInt("AccountID");
                String Email = rs.getString("Email");
                String CompanyName = rs.getString("CompanyName");
                String ContactName = rs.getString("ContactName");
                String ContactTitle = rs.getString("ContactTitle");
                String Address = rs.getString("Address");
                String CustomerID = rs.getString("CustomerID");
                Customer customer = new Customer(CustomerID, CompanyName, ContactName, ContactTitle, Address, rs.getBoolean("Gender"));
                Account acc = new Account();
                acc.setAccountID(AccountID);
                acc.setCustomerID(customer);
                acc.setEmail(rs.getString("Email"));
                acc.setEmployeeID(rs.getString("EmployeeID"));
                customerAccount = new CustomerAccount(acc, customer);
                return customerAccount;
            }
        } catch (Exception e) {
            System.out.println("Loi ham getCustomerInfor " + e.getMessage());
        }
        return customerAccount;
    }

    public void updateCustomer(Customer cus) throws SQLException {
        try {
            String sql = "Update Customers set ContactName= ?, CompanyName= ?,ContactTitle= ?,[Address]= ? \n"
                    + "where CustomerID = ? ";
            ps = connection.prepareStatement(sql);
            ps.setString(1, cus.getContactName());
            ps.setString(2, cus.getCompanyName());
            ps.setString(3, cus.getContactTitle());
            ps.setString(4, cus.getAddress());
            ps.setString(5, cus.getCustomerID());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Loi: "+ e.getMessage());
        }

    }

    public ArrayList<CustomerAccount> getCustomerbyCondition(String condition, String cusName) throws SQLException {
        ArrayList<CustomerAccount> list = new ArrayList<>();
        Customer c = new Customer();
        Account ac = new Account();
        CustomerAccount customerAccount = new CustomerAccount();
        try {
            String sql;
            switch (condition) {
                case "searchByCustomerName":
                    sql = "select * from Customers c left join Accounts ac on c.CustomerID = ac.CustomerID where c.ContactName like ?";
                    ps = connection.prepareStatement(sql);
                    ps.setString(1, "%" + cusName + "%");
                    break;
                default:
                    sql = "select * from Customers c left join Accounts ac on c.CustomerID = ac.CustomerID ";
                    ps = connection.prepareStatement(sql);
                    break;
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                String CustomerID = rs.getString("CustomerID");
                String CompanyName = rs.getString("CompanyName");
                String ContactName = rs.getString("ContactName");
                String ContactTitle = rs.getString("ContactTitle");
                String Address = rs.getString("Address");
                c = new Customer(CustomerID, CompanyName, ContactName, ContactTitle, Address, rs.getBoolean("Gender"));
                String Email = rs.getString("Email");
                ac = new Account(Email);
                customerAccount = new CustomerAccount(ac, c);
                list.add(customerAccount);
            }
        } catch (Exception e) {
        }
        return list;
    }

}
