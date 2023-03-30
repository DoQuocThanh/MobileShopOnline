/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.shop;

import DAL.PaginationObject;
import DAL.shop.ProductDetail;
import DAL.shop.ProductInfor;
import DAO.shop.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LEGION
 */
public class FilterController extends HttpServlet {

    private final PaginationObject pcp = new PaginationObject();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int price;
            String color;
            int storage;
            String brand;
            //get Brands for filter
            if (req.getSession().getAttribute("BrandFilter") == null) {
                brand = null;
            } else {
                brand = req.getSession().getAttribute("BrandFilter").toString();
            }
            //get Price for filter
            if (req.getParameter("txtCheckedFilterPrice") != null && req.getParameter("txtFilterPrice") != null) {
                price = Integer.parseInt(req.getParameter("txtFilterPrice"));
            } else {
                if (req.getParameter("txtCheckedFilterPrice") == null) {
                    price = 0;
                } else {
                    price = Integer.parseInt(req.getParameter("txtCheckedFilterPrice"));
                }
            }

            //get Color for filter
            if (req.getParameter("txtCheckedFilterColor") != null && req.getParameter("txtFilterColor") != null) {
                color = req.getParameter("txtFilterColor");
            } else {
                if (req.getParameter("txtCheckedFilterColor") == null) {
                    color = "All";
                } else {
                    color = req.getParameter("txtCheckedFilterColor");
                }
            }

            //get Storage for filter
            if (req.getParameter("txtCheckedFilterStorage") != null && req.getParameter("txtFilterStorage") != null) {
                storage = Integer.parseInt(req.getParameter("txtFilterStorage"));
            } else {
                if (req.getParameter("txtCheckedFilterStorage") == null) {
                    storage = 0;
                } else {
                    storage = Integer.parseInt(req.getParameter("txtCheckedFilterStorage"));
                }
            }

            int currentPage;
            int numberOfPage;
            String a = req.getParameter("currentPage");
            if ("".equals(a) || a == null) {
                currentPage = 1;
            } else {
                currentPage = Integer.parseInt(req.getParameter("currentPage"));
            }
            req.getSession().removeAttribute("searching");
            req.getSession().removeAttribute("sortSession");
            //Paging
            List<ProductInfor> listFilterProduct = new ProductDAO().getFilterProduct(price, color, storage, brand, "");
            List<ProductInfor> getFilterProduct = pcp.getPageOfResult(listFilterProduct, currentPage, PaginationObject.getNumberOfRowEachPage());
            numberOfPage = pcp.getTotalPageOfResult(listFilterProduct, PaginationObject.getNumberOfRowEachPage());
            req.getSession().setAttribute("currentPage", currentPage);
            req.setAttribute("numberOfPage", numberOfPage);

            //getColor
            List<ProductDetail> getColor = new ProductDAO().getAllColor();
            req.getSession().setAttribute("listColor", getColor);
            //getProductStorage
            List<ProductDetail> getProductStorage = new ProductDAO().getAllProductStorage();
            req.getSession().setAttribute("listProductStorage", getProductStorage);
            //getTotalProduct
            List<ProductInfor> listProduct = new ProductDAO().getAllProduct();
            req.getSession().setAttribute("totalListProduct", listProduct.size());

            //Pull attribute on shop
            req.setAttribute("listFilterProduct", getFilterProduct);
            req.getSession().setAttribute("PriceFilter", price);
            req.getSession().setAttribute("ColorFilter", color);
            req.getSession().setAttribute("StorageFilter", storage);
            req.getSession().setAttribute("BrandFilter", brand);
            req.getSession().setAttribute("filter", "not null");
            req.setAttribute("check", "not empty");
            req.getRequestDispatcher("shop.jsp").forward(req, resp);
        } catch (SQLException ex) {
            
            Logger.getLogger(FilterController.class.getName()).log(Level.SEVERE, null, ex);
        }catch (NumberFormatException exception){
            resp.sendRedirect("Error");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int price;
            String color;
            int storage;
            String brand;
            String sort;
            //get Brands for filter
            if (req.getParameter("txtBrandName") == null || "AllBrands".equals(req.getParameter("txtBrandName")) || req.getParameter("txtBrandName").isEmpty()) {
                if(req.getSession().getAttribute("BrandFilter") != null && req.getParameter("txtBrandName") == null){
                    brand = req.getSession().getAttribute("BrandFilter").toString();
                }else{
                    brand = null;
                }    
            } else {
                brand = req.getParameter("txtBrandName");
            }
            //get Price for filter
            if (req.getSession().getAttribute("PriceFilter") == null) {
                price = 0;
            } else {
                price = Integer.parseInt(req.getSession().getAttribute("PriceFilter").toString());
            }

            //get Color for filter
            if (req.getSession().getAttribute("ColorFilter") == null) {
                color = "All";
            } else {
                color = req.getSession().getAttribute("ColorFilter").toString();
            }

            //get Storage for filter
            if (req.getSession().getAttribute("StorageFilter") == null) {
                storage = 0;
            } else {
                storage = Integer.parseInt(req.getSession().getAttribute("StorageFilter").toString());
            }
            
            //sorting Filter Product
            if(req.getParameter("sort") == null){
                sort = "";
            }else{
                sort = req.getParameter("sort");
            }
            int currentPage;
            int numberOfPage;
            String a = req.getParameter("currentPage");
            if ("".equals(a) || a == null) {
                currentPage = 1;
            } else {
                currentPage = Integer.parseInt(req.getParameter("currentPage"));
            }
            req.getSession().removeAttribute("searching");
            req.getSession().removeAttribute("sortSession");
            //Paging
            List<ProductInfor> listFilterProduct = new ProductDAO().getFilterProduct(price, color, storage, brand, sort);
            List<ProductInfor> getFilterProduct = pcp.getPageOfResult(listFilterProduct, currentPage, PaginationObject.getNumberOfRowEachPage());
            numberOfPage = pcp.getTotalPageOfResult(listFilterProduct, PaginationObject.getNumberOfRowEachPage());
            req.getSession().setAttribute("currentPage", currentPage);
            req.setAttribute("numberOfPage", numberOfPage);

            //getColor
            List<ProductDetail> getColor = new ProductDAO().getAllColor();
            req.getSession().setAttribute("listColor", getColor);
            //getProductStorage
            List<ProductDetail> getProductStorage = new ProductDAO().getAllProductStorage();
            req.getSession().setAttribute("listProductStorage", getProductStorage);
            //getTotalProduct
            List<ProductInfor> listProduct = new ProductDAO().getAllProduct();
            req.getSession().setAttribute("totalListProduct", listProduct.size());

            //Pull attribute on shop
            req.setAttribute("listFilterProduct", getFilterProduct);
            req.getSession().setAttribute("PriceFilter", price);
            req.getSession().setAttribute("ColorFilter", color);
            req.getSession().setAttribute("StorageFilter", storage);
            req.getSession().setAttribute("BrandFilter", brand);
            req.getSession().setAttribute("filter", "not null");
            req.getSession().setAttribute("SortingFilter", sort);
            req.setAttribute("check", "not empty");
            req.getRequestDispatcher("shop.jsp").forward(req, resp);
        } catch (SQLException ex) {
            
            Logger.getLogger(FilterController.class.getName()).log(Level.SEVERE, null, ex);
            
        }catch (NumberFormatException exception){
            resp.sendRedirect("Error");
        }
    }
}