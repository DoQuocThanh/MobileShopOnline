/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAL.AccCusCom;
import DAL.Account;
import DAL.Home.Brands;
import DAL.loginGoogle.GooglePojo;
import DAL.shop.Comments;
import DAL.shop.ProductDetail;
import DAO.ProductDetails;
import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DetailProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String IDProduct = req.getParameter("productID");
        String productIDAjax = req.getParameter("productID");
        String storageProductAjax = req.getParameter("productStorage");
        String colorProductAjax = req.getParameter("productColor");
        String paramCheck = req.getParameter("paramCheck");
        if (IDProduct == null || productIDAjax == null) {
            resp.sendRedirect("index.jsp");
        } else {
            //get product by product name
            ProductDetail product = new DAO.ProductDetails().getProductDetail(IDProduct);
            //get name product & storage
            String nameProduct = product.getProductName();
            int storageProduct = product.getProductStorage();
            //get smallest price of products
            double price = product.getUnitPrice();
            DecimalFormat decimalFormat = new DecimalFormat("0.0");
            String formattedNum = decimalFormat.format(price);
            String formattedNumDisCount = decimalFormat.format(price - 0.15 * price);
            // get brand id by product and get brand name;
            int idProduct = product.getProductID();
            Brands brand = new DAO.ProductDetails().getBrandThrowBrandID(idProduct);
            //get list product detail own picture and color
            ArrayList<ProductDetail> listProductDetail = new DAO.ProductDetails().
                    getListAllAttributeProductThrowID(product.getProductID());
            ArrayList<ProductDetail> listStorage = new DAO.ProductDetails().getListStorageProductThrowID(product.getProductDetail());
            ArrayList<ProductDetail> listColor = new DAO.ProductDetails().getListColerProductThrowID(product.getProductDetail());
            ProductDetail spec = new DAO.ProductDetails().getSpecifiByIDAndStorage(IDProduct, storageProduct);

            //----------------------------------------------------------------------------------
            //RATING - PRICE BY STORAGE
            if ("storage".equals(paramCheck)) {
                ProductDetail productDetail
                        = new DAO.ProductDetails().getProductDetailByIDAndStorage(productIDAjax, storageProductAjax);
                //Get price product and format
                String formattedNum1 = decimalFormat.format(productDetail.getUnitPrice());
                // discount price
                String formattedNumDisCount1 = decimalFormat.format(productDetail.getUnitPrice() * 0.85);
                //get product name
                nameProduct = productDetail.getProductName();
                // Check input Storage 
                String formattedStorage = "";
                if ("1000".equals(storageProductAjax)) {
                    formattedStorage = "1TB";
                } else {
                    formattedStorage = storageProductAjax + "GB";
                }
                req.setAttribute("formattedNumNew", formattedNum1);
                req.setAttribute("formattedNumDisCountNew", formattedNumDisCount1);

            } else if ("color".equals(paramCheck)) {
                ArrayList<ProductDetail> pdt = new DAO.ProductDetails().getListPictureByIDAndColor(productIDAjax, colorProductAjax);
                req.setAttribute("pdt", pdt);
            } else {
                formattedNum = decimalFormat.format(price);
                formattedNumDisCount = decimalFormat.format(price - 0.15 * price);
            }
            //------------------------------------------------------------------
            ArrayList<ProductDetail> mayAlsoYouLike = new DAO.ProductDetails().mayAlsoYouLike(Integer.parseInt(IDProduct));
            Comments rateProduct = new DAO.ProductDetails().getRateByProductID(Integer.parseInt(IDProduct));
            //------------------------------------------------------------------
            //REVIEW PART
            String review = req.getParameter("review");
            boolean onclickSubmit = false;
            if ("review".equals(review)) {
                onclickSubmit = true;
            }
            if (onclickSubmit == true) {
                String message = req.getParameter("message");
                String rating = req.getParameter("rating");
                Account accSession = (Account) req.getSession().getAttribute("AccSession");
                GooglePojo gg = (GooglePojo) req.getSession().getAttribute("GoogleAccount");
                if (accSession == null && gg == null) {
                    req.setAttribute("AccNull", "AccNull");
                    req.getRequestDispatcher("signIn.jsp").forward(req, resp);
                } else {
                    if (message == null || message.isEmpty()) {
                        req.setAttribute("messageError", "Please enter a message.");
                    } else {
                        int accountID = 0, status = 1;
                        Calendar calendar = Calendar.getInstance();
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String commentDate = dateFormat.format(calendar.getTime());
                        String picture = null;
                        if (accSession != null) {
                            accountID = accSession.getAccountID();
                        }
                        if (gg != null) {
                            accountID = gg.getAccountID();
                        }
                        ProductDetails commentDAO = new ProductDetails();
                        try {
                            commentDAO.insertComments(accountID, status, Integer.parseInt(rating), Integer.parseInt(IDProduct), commentDate, message);
                        } catch (SQLException ex) {
                            Logger.getLogger(DetailProductController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        req.setAttribute("successMessage", "Your comment has been added successfully.");
                        // Set attribute values for error messages
                        req.setAttribute("message", message);
                    }
                }
            }
            ArrayList<AccCusCom> accCusCom = new DAO.ProductDetails().listCommentOfAProduct(Integer.parseInt(IDProduct));
            req.setAttribute("accCusCom", accCusCom);
            //------------------------------------------------------------------
            req.setAttribute("check", "not empty check");
            req.setAttribute("nameProduct", nameProduct);
            req.setAttribute("product", product);
            req.setAttribute("brandName", brand.getBrandName());
            req.setAttribute("storageProduct", storageProduct);
            req.setAttribute("priceProduct", formattedNum);
            req.setAttribute("priceProductDisCount", formattedNumDisCount);
            req.setAttribute("listColor", listColor);
            req.setAttribute("listStorage", listStorage);
            req.setAttribute("listProductDetail", listProductDetail);
            req.setAttribute("spec", spec);
            req.setAttribute("mayAlsoYouLike", mayAlsoYouLike);
            req.setAttribute("rateProduct", rateProduct);
            //----------------------------------------------------------------------
            req.setAttribute("ID", IDProduct);
            System.out.println(req.getParameter("numberQuantity"));
            if (req.getParameter("numberQuantity") == null) {
                req.setAttribute("Quanity", 1);
            } else {
                req.setAttribute("Quanity", req.getParameter("numberQuantity"));
            }

            req.getRequestDispatcher("detail.jsp").forward(req, resp);
        }
        } catch (Exception e) {
            printStackTrace();
            resp.sendRedirect("Error");
        }
    }

}
