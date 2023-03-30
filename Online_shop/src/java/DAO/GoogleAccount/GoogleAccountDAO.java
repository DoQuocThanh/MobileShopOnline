/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.GoogleAccount;

import DAL.DBcontext;
import DAL.loginGoogle.GooglePojo;
import jakarta.servlet.jsp.jstl.sql.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author LEGION
 */
public class GoogleAccountDAO extends DBcontext{
    
    public boolean checkExistAccount(String email){
        boolean check = false;
        try {
            String sql = "select * from Accounts where Email = ?";
            PreparedStatement ps = getConnection().prepareCall(sql);
            ps.setString(1, email);
            ResultSet rs  = ps.executeQuery();
            if(rs.next()){
                check = true;
            }
        } catch (Exception e) {
        }
        return check;
    }
    
    public int getExistAccount(String email){
        int accountID = 0;
        try {
            String sql = "select * from Accounts where Email = ?";
            PreparedStatement ps = getConnection().prepareCall(sql);
            ps.setString(1, email);
            ResultSet rs  = ps.executeQuery();
            while(rs.next()){
                accountID = rs.getInt("AccountID");
            }
        } catch (Exception e) {
        }
        return accountID;
    }
    
    public String getExistCustomerIDAccount(String email){
        String CustomerID = "";
        try {
            String sql = "select * from Accounts where Email = ?";
            PreparedStatement ps = getConnection().prepareCall(sql);
            ps.setString(1, email);
            ResultSet rs  = ps.executeQuery();
            while(rs.next()){
                CustomerID = rs.getString("CustomerID");
            }
        } catch (Exception e) {
        }
        return CustomerID;
    }
    
    public void addAccountGoogle(GooglePojo google){
        String CustomerID = randomString(5);
        String Password = randomString(10);
        try {
            //account
            String sqlAccount = "INSERT INTO Accounts(Email, [Password], CustomerID, EmployeeID, [Role], [Status])\n" +
"VALUES (?, ?, ?, NULL, 2, 1);";
            PreparedStatement ps = getConnection().prepareCall(sqlAccount);
            ps.setString(1, google.getEmail());
            ps.setString(2, Password);
            ps.setString(3, CustomerID);
            //customer
            String sqlCustomer = "Insert into Customers(CustomerID,CompanyName,ContactName,ContactTitle,[Address])\n" +
"values(?,?,?,?,?);";
            PreparedStatement ps2 = getConnection().prepareCall(sqlCustomer);
            ps2.setString(1, CustomerID);
            ps2.setString(2, "");
            ps2.setString(3, google.getName());
            ps2.setString(4, "");
            ps2.setString(5, "");
            ps2.executeUpdate();
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public String randomString(int n) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }
}
