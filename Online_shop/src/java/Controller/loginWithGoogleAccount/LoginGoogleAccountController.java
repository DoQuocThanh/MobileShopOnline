/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.loginWithGoogleAccount;

import DAL.CustomerAccount;
import DAL.loginGoogle.GooglePojo;
import DAL.loginGoogle.GoogleUtils;
import DAO.CustomerDAO;
import DAO.GoogleAccount.GoogleAccountDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 *
 * @author LEGION
 */
public class LoginGoogleAccountController extends HttpServlet{
    private static final long serialVersionUID = 1L;
    GoogleAccountDAO googleaccount = new GoogleAccountDAO();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
    if (code == null || code.isEmpty()) {
      RequestDispatcher dis = req.getRequestDispatcher("login.jsp");
      dis.forward(req, resp);
    } else {
      String accessToken = GoogleUtils.getToken(code);
      GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
      req.setAttribute("id", googlePojo.getId());
      req.setAttribute("name", googlePojo.getName());
      req.setAttribute("email", googlePojo.getEmail());
        req.getSession().setAttribute("GoogleAccount", googlePojo);
        if(googleaccount.checkExistAccount(googlePojo.getEmail())){
            googlePojo.setAccountID(googleaccount.getExistAccount(googlePojo.getEmail()));
            googlePojo.setCustomerID(googleaccount.getExistCustomerIDAccount(googlePojo.getEmail()));
        }else{
            googleaccount.addAccountGoogle(googlePojo);
        }
        System.out.println(googlePojo.getEmail());
        CustomerAccount inforAccount = new CustomerDAO().getCustomerInfor(googlePojo.getCustomerID());
        req.getSession().setAttribute("CustomerInfor", inforAccount);
      RequestDispatcher dis = req.getRequestDispatcher("index.jsp");
      dis.forward(req, resp);
    }
    }
        
}
