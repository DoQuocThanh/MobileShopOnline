package Controller;

import DAL.Account;
import DAL.CustomerAccount;
import DAO.AccountDAO;
import DAO.CustomerDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SignInController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("CustomerInfor") != null) {
            resp.sendRedirect("home");
        } else {
            req.setAttribute("check", "not empty");
            req.getRequestDispatcher("./signIn.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //nhan du lieu tu login.jsp
            String email = req.getParameter("txtEmail");
            String pass = req.getParameter("txtPass");
            if (email.equals("")) {
                req.setAttribute("msgEmail", "Email is required");
            } else {
                req.setAttribute("Email", email);
            }
            if (pass.equals("")) {
                req.setAttribute("msgPass", "Pass is required");
            }
            Account acc = new AccountDAO().getAccount(email, pass);
            System.out.println(acc.getRole()+ "co cai l t");
            if (acc != null) {
                //Cap session cho account
                req.setAttribute("check", "not empty");
                if (acc.getRole() == 1) {
                    req.getSession().setAttribute("adminAccount", acc);
                } else {
                    CustomerAccount inforAccount = new CustomerDAO().getCustomerInfor(acc.getCustomerID().getCustomerID());
                    System.out.println(inforAccount.getAccount().getAccountID());
                    acc = inforAccount.getAccount();
                    acc.setPass(pass);
                    inforAccount.setAccount(acc);
                    req.getSession().setAttribute("CustomerInfor", inforAccount);
                    req.getSession().setAttribute("AccSession", acc);
                    System.out.println(acc.getAccountID() + "nhu con c");
                }
                //Dieu huong toi index.jsp
                resp.sendRedirect("home");
            } else {
                req.setAttribute("check", "not empty");
                req.setAttribute("msg", "This account does not exist");
                req.getRequestDispatcher("signIn.jsp").forward(req, resp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
