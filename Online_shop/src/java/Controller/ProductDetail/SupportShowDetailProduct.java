/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.ProductDetail;

import DAL.shop.ProductDetail;
import DAO.ProductDetails;
import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author LEGION
 */
public class SupportShowDetailProduct extends HttpServlet {
    
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getParameter("ProductID") != null) {
                if(req.getParameter("type") != null){
                    int ProductID = Integer.parseInt(req.getParameter("ProductID"));
                    ProductDetail product = new ProductDetails().getProductDetailbysupporting(ProductID);
                    resp.sendRedirect("cart?productDetailID=" + product.getProductDetail() + "&type=buyNow");
                }else{
                    int ProductID = Integer.parseInt(req.getParameter("ProductID"));
                    ProductDetail product = new ProductDetails().getProductDetailbysupporting(ProductID);
                    resp.sendRedirect("detail?productID=" + product.getProductDetail() + "");
                }
            } else {
                resp.sendRedirect("home");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("Error");
        }
        
    }
    
}
