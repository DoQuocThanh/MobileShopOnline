package DAO;

import DAL.Account;
import DAL.Customer;
import DAL.DBcontext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {

    ResultSet rs;
    PreparedStatement ps;
    Connection connection;

    public AccountDAO() {
        try {
            connection = new DBcontext().getConnection();
        } catch (Exception e) {
            System.out.println("loi ket noi DB cua AccountDAO ");
        }
    }

    public Account getAccount(String email, String pass) throws SQLException {
        Account acc = null;
        try {
            String sql = "select * from Accounts where Email = ? and Password = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                int AccountID = rs.getInt("AccountID");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");
                String CusID = rs.getString("CustomerID");
                int role = rs.getInt("Role");
                String EmpID = rs.getString("EmployeeID");

                acc = new Account(AccountID, Email, pass, new Customer(CusID), EmpID, role);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

        }
        return acc;
    }

    public void update(String email, String pass) {
        Account acc = null;
        try {
            String sql = "UPDATE [Accounts]\n"
                    + "   SET \n"
                    + "   [Password] = ?\n"
                    + " WHERE Email = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, pass);
            ps.setString(2, email);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Loi : " + e.getMessage());
        }
    }

    public int addAccount(Customer customer, Account acc) throws SQLException {
        int result1 = 0, result2 = 0;
        try {
            String sql1 = "insert into Customers(CustomerID, CompanyName, ContactName, ContactTitle, Address,Gender) values(?,?,?,?,?,?)";
            String sql2 = "insert into Accounts(Email,Password,CustomerID,EmployeeID,Role, [Status]) values(?,?,?,?,2, 1)";
            boolean fl = true;
            if (acc.getEmployeeID() == null) {
                sql2 = "insert into Accounts(Email,Password,CustomerID,EmployeeID,Role, [Status]) values(?,?,?,NULL,2,1)";
                fl = false;
            }
            PreparedStatement ps1 = connection.prepareStatement(sql1);
            PreparedStatement ps2 = connection.prepareStatement(sql2);
            ps1.setString(1, customer.getCustomerID());
            ps1.setString(2, customer.getCompanyName() == null ? "Null" : customer.getCompanyName());
            ps1.setString(3, customer.getContactName() == null ? "NULL" : customer.getContactName());
            ps1.setString(4, customer.getContactTitle() == null ? "NULL" : customer.getContactTitle());
            ps1.setString(5, customer.getAddress());
            ps1.setBoolean(6, customer.isGender());
            ps2.setString(1, acc.getEmail());
            ps2.setString(2, acc.getPass());
            ps2.setString(3, customer.getCustomerID());
            if (fl) {
                ps2.setInt(4, Integer.parseInt(acc.getEmployeeID()));
            }
            result1 = ps1.executeUpdate();
            result2 = ps2.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DBcontext.releaseJBDCObject(rs, ps, connection);
        }
        if (result1 > 0 && result2 > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public void changedPassword(String email, String newPassword) {
        try {
            String sql = "Update Accounts Set Password = ? where Email = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, newPassword);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (Exception e) {

        } finally {
        }
    }

    public Account checkAccount(String email, String contact) {
        Account acc = null;
        try {
            String sql = "SELECT a.[AccountID]\n"
                    + "      ,a.[Email]\n"
                    + "      ,a.[Password]\n"
                    + "      ,a.[CustomerID]\n"
                    + "	  ,c.ContactName\n"
                    + "      ,a.[EmployeeID]\n"
                    + "      ,a.[Role]\n"
                    + "      ,a.[Status]\n"
                    + "  FROM [Accounts] a JOIN Customers c ON a.CustomerID = c.CustomerID"
                    + " where a.Email = ? ";
            if (!contact.equals("")) {
                sql += " AND c.ContactName = '" + contact + "'";
            }
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");
                String CusID = rs.getString("CustomerID");
                int role = rs.getInt("Role");
                String EmpID = rs.getString("EmployeeID");
                acc = new Account(Email, Password, new Customer(CusID), role, EmpID);
            }
        } catch (Exception e) {
        } finally {
        }
        return acc;
    }

    

}
