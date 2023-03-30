/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Admin;

import DAL.Admin.Product;
import DAL.Admin.ProductDetails;
import DAO.Admin.AdminDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author blabl
 */
public class AdminProductDetails2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("productDetailID");
        ProductDetails product = new AdminDAO().getProductDetailByProductDetailID(id);
        if(id == null){
            req.setAttribute("type", "Create");
            String productID = req.getParameter("productID");
             product.setProductID(Integer.parseInt(productID));
            
        }
        req.setAttribute("p", product);
        req.getRequestDispatcher("productDetail2.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         String productDetailID = req.getParameter("productDetailID");
         String productStorage = req.getParameter("productStorage");
         String price = req.getParameter("price");
         String picture = req.getParameter("srcProductDetail");
         String coler = req.getParameter("coler");
         String UnitsInStock = req.getParameter("unitsInStock");
         String unitsOnOrder = req.getParameter("unitsOnOrder");
         String productID = req.getParameter("productID");
         String employeeID =req.getParameter("employeeID");
         if(req.getParameter("typeP")!=null){
             new AdminDAO().createProductDetail(productStorage, coler, price, UnitsInStock, picture, productDetailID, unitsOnOrder, productID, employeeID);
            resp.sendRedirect("productdetail?productID=" + productID);
             
         }else{
            new AdminDAO().updateProductDeteail(productStorage, coler, price, UnitsInStock, picture, productDetailID,unitsOnOrder,productID,employeeID);
            resp.sendRedirect("productdetail2?productDetailID=" + productDetailID);
         }
         
    }
    


}
