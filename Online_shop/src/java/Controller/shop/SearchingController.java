/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.shop;

import DAL.PaginationObject;
import DAL.shop.Product;
import DAL.shop.ProductDetail;
import DAL.shop.ProductInfor;
import DAO.shop.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LEGION
 */
public class SearchingController extends HttpServlet {

    private final PaginationObject pcp = new PaginationObject();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("text/html;charset=UTF-8");
            req.getSession().removeAttribute("sortSession");
            req.getSession().removeAttribute("filter");
            String searchingInf = req.getParameter("txt");
            if (searchingInf == null) {
                searchingInf = "";
            }
            List<ProductInfor> searchingList = new ProductDAO().searchProducts(searchingInf);
            int currentPage;
            String a = req.getParameter("currentPage");
            if ("".equals(a) || a == null) {
                currentPage = 1;
            } else {
                currentPage = Integer.parseInt(req.getParameter("currentPage"));
            }
            int numberOfPage;
            List<Product> getProduct = pcp.getPageOfResult(searchingList, currentPage, PaginationObject.getNumberOfRowEachPage());
            numberOfPage = pcp.getTotalPageOfResult(searchingList, PaginationObject.getNumberOfRowEachPage());
            req.getSession().setAttribute("currentPage", currentPage);
            req.getSession().setAttribute("searching", currentPage);
            req.setAttribute("numberOfPage", numberOfPage);
            req.setAttribute("searchingListProduct", getProduct);
            req.setAttribute("txtValue", searchingInf);
            req.setAttribute("check", "not empty");
            //getColor
            List<ProductDetail> getColor = new ProductDAO().getAllColor();
            req.getSession().setAttribute("listColor", getColor);
            //getProductStorage
            List<ProductDetail> getProductStorage = new ProductDAO().getAllProductStorage();
            req.getSession().setAttribute("listProductStorage", getProductStorage);
            //getTotal Product
            List<ProductInfor> listProduct = new ProductDAO().getAllProduct();
            req.getSession().setAttribute("totalListProduct", listProduct.size());
            //reset Filter
            req.getSession().setAttribute("PriceFilter", 0);
            req.getSession().setAttribute("ColorFilter", "All");
            req.getSession().setAttribute("StorageFilter", 0);
            
            req.getRequestDispatcher("shop.jsp").forward(req, resp);
        } catch (SQLException ex) {
            
            Logger.getLogger(SearchingController.class.getName()).log(Level.SEVERE, null, ex);
        }catch (NumberFormatException exception){
            resp.sendRedirect("Error");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("text/html;charset=UTF-8");
            req.getSession().removeAttribute("sortSession");
            req.getSession().removeAttribute("filter");
            String searchingInf = req.getParameter("txt");
            if (searchingInf == null) {
                searchingInf = "";
            }
            List<ProductInfor> searchingList = new ProductDAO().searchProducts(searchingInf);
            int currentPage;
            String a = req.getParameter("currentPage");
            if ("".equals(a) || a == null) {
                currentPage = 1;
            } else {
                currentPage = Integer.parseInt(req.getParameter("currentPage"));
            }
            int numberOfPage;
            List<Product> getProduct = pcp.getPageOfResult(searchingList, currentPage, PaginationObject.getNumberOfRowEachPage());
            numberOfPage = pcp.getTotalPageOfResult(searchingList, PaginationObject.getNumberOfRowEachPage());
            req.getSession().setAttribute("currentPage", currentPage);
            req.getSession().setAttribute("searching", currentPage);
            req.setAttribute("numberOfPage", numberOfPage);
            req.setAttribute("searchingListProduct", getProduct);
            req.setAttribute("txtValue", searchingInf);
            req.setAttribute("check", "not empty");
            //getColor
            List<ProductDetail> getColor = new ProductDAO().getAllColor();
            req.getSession().setAttribute("listColor", getColor);
            //getProductStorage
            List<ProductDetail> getProductStorage = new ProductDAO().getAllProductStorage();
            req.getSession().setAttribute("listProductStorage", getProductStorage);
            //getTotal Product
            List<ProductInfor> listProduct = new ProductDAO().getAllProduct();
            req.getSession().setAttribute("totalListProduct", listProduct.size());
            //reset Filter
            req.getSession().setAttribute("PriceFilter", 0);
            req.getSession().setAttribute("ColorFilter", "All");
            req.getSession().setAttribute("StorageFilter", 0);
            
            req.getRequestDispatcher("shop.jsp").forward(req, resp);
        } catch (SQLException ex) {
            
            Logger.getLogger(SearchingController.class.getName()).log(Level.SEVERE, null, ex);
        }catch (NumberFormatException exception){
            resp.sendRedirect("Error");
        }
    }

}
