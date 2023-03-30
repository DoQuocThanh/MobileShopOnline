/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Shopingcart;

import DAL.Account;
import DAL.cart.ProductCart;
import DAL.loginGoogle.GooglePojo;
import DAO.cart.CartDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LEGION
 */
public class CartController extends HttpServlet {

    List<ProductCart> listGuestProductCart = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String choice = req.getParameter("type");
        if (choice == null) {
            choice = "showCart";
        }
        req.setAttribute("check","not empty");
        try {
            int productDetailID;
            if (req.getParameter("productDetailID") == null) {
                productDetailID = 0;
            } else {
                productDetailID = Integer.parseInt(req.getParameter("productDetailID"));
            }
            String code = req.getParameter("txtCouponCode");
            double CouponCode;
            if (code == null || code.isEmpty()) {
                CouponCode = 0;
            } else {
                CouponCode = 0.1;
            }
            Account acc = (Account) req.getSession().getAttribute("AccSession");
            System.out.println(acc.getAccountID() + "adkasjid");
            double totalPrice = 0;
            switch (choice) {
                case "+":
                    if (acc == null && productDetailID != 0) {
                        if (req.getSession().getAttribute("GoogleAccount") != null) {//Customer using Google Account
                            GooglePojo googlePojo = (GooglePojo) req.getSession().getAttribute("GoogleAccount");
                            int accountID = googlePojo.getAccountID();

                            List<ProductCart> listCustomerProductCarts = new CartDAO().getCustomerCart(accountID);

                            for (ProductCart cusProductCart : listCustomerProductCarts) {
                                if (cusProductCart.getProductDetailID() == productDetailID) {
                                    new CartDAO().updateCustomerProductCart(accountID, productDetailID, (cusProductCart.getQuantity() + 1));
                                    break;
                                }
                            }
                            listCustomerProductCarts = new CartDAO().getCustomerCart(accountID);
                            for (ProductCart guestProduct : listCustomerProductCarts) {
                                totalPrice += guestProduct.getPrice() * guestProduct.getQuantity();
                            }
                            req.getSession().setAttribute("ProductCart", listCustomerProductCarts);
                            req.getSession().setAttribute("Subtotal", totalPrice);
                        } else {
                            for (ProductCart guestProduct : listGuestProductCart) {
                                if (guestProduct.getProductDetailID() == productDetailID) {
                                    guestProduct.setQuantity(guestProduct.getQuantity() + 1);
                                    break;
                                }
                            }
                            for (ProductCart guestProduct : listGuestProductCart) {
                                totalPrice += guestProduct.getPrice() * guestProduct.getQuantity();
                            }
                            req.getSession().setAttribute("ProductCart", listGuestProductCart);
                            req.getSession().setAttribute("Subtotal", totalPrice);
                        }
                    } //Customer
                    else if (acc != null && productDetailID != 0) {
                        int accountID = acc.getAccountID();
                        List<ProductCart> listCustomerProductCarts = new CartDAO().getCustomerCart(accountID);

                        for (ProductCart cusProductCart : listCustomerProductCarts) {
                            if (cusProductCart.getProductDetailID() == productDetailID) {
                                new CartDAO().updateCustomerProductCart(accountID, productDetailID, (cusProductCart.getQuantity() + 1));
                                break;
                            }
                        }
                        listCustomerProductCarts = new CartDAO().getCustomerCart(accountID);
                        for (ProductCart guestProduct : listCustomerProductCarts) {
                            totalPrice += guestProduct.getPrice() * guestProduct.getQuantity();
                        }
                        req.getSession().setAttribute("ProductCart", listCustomerProductCarts);
                        req.getSession().setAttribute("Subtotal", totalPrice);
                    }
                    req.getSession().setAttribute("Code", code);
                    req.setAttribute("Discount", CouponCode);
                    req.getRequestDispatcher("cart.jsp").forward(req, resp);
                    break;
                case "-":
                    boolean checkZero = false;
                    int i = 0;
                    if (acc == null && productDetailID != 0) {
                        if (req.getSession().getAttribute("GoogleAccount") != null) {//Customer using Google Account
                            GooglePojo googlePojo = (GooglePojo) req.getSession().getAttribute("GoogleAccount");
                            int accountID = googlePojo.getAccountID();
                            List<ProductCart> listCustomerProductCarts = new CartDAO().getCustomerCart(accountID);
                            for (ProductCart cusProductCart : listCustomerProductCarts) {
                                if (cusProductCart.getProductDetailID() == productDetailID) {
                                    if (cusProductCart.getQuantity() == 1) {
                                        checkZero = true;
                                        break;
                                    } else {
                                        new CartDAO().updateCustomerProductCart(accountID, productDetailID, (cusProductCart.getQuantity() - 1));
                                        break;
                                    }
                                }
                                i++;
                            }
                            if (checkZero) {
                                new CartDAO().DeleteCustomerProductCart(accountID, productDetailID);
                            }
                            listCustomerProductCarts = new CartDAO().getCustomerCart(accountID);
                            for (ProductCart customerProduct : listCustomerProductCarts) {
                                totalPrice += customerProduct.getPrice() * customerProduct.getQuantity();
                            }
                            req.getSession().setAttribute("ProductCart", listCustomerProductCarts);
                            req.getSession().setAttribute("Subtotal", totalPrice);
                        } else {
                            for (ProductCart guestProduct : listGuestProductCart) {
                                if (guestProduct.getProductDetailID() == productDetailID) {
                                    if (guestProduct.getQuantity() == 1) {
                                        checkZero = true;
                                        break;
                                    } else {
                                        guestProduct.setQuantity(guestProduct.getQuantity() - 1);
                                        break;
                                    }
                                }
                                i++;
                            }
                            if (checkZero) {
                                listGuestProductCart.remove(i);
                            }
                            for (ProductCart guestProduct : listGuestProductCart) {
                                totalPrice += guestProduct.getPrice() * guestProduct.getQuantity();
                            }
                            req.getSession().setAttribute("ProductCart", listGuestProductCart);
                            req.getSession().setAttribute("Subtotal", totalPrice);
                        }

                    } //Customer
                    else if (acc != null && productDetailID != 0) {
                        int accountID = acc.getAccountID();
                        List<ProductCart> listCustomerProductCarts = new CartDAO().getCustomerCart(accountID);
                        for (ProductCart cusProductCart : listCustomerProductCarts) {
                            if (cusProductCart.getProductDetailID() == productDetailID) {
                                if (cusProductCart.getQuantity() == 1) {
                                    checkZero = true;
                                    break;
                                } else {
                                    new CartDAO().updateCustomerProductCart(accountID, productDetailID, (cusProductCart.getQuantity() - 1));
                                    break;
                                }
                            }
                            i++;
                        }
                        if (checkZero) {
                            new CartDAO().DeleteCustomerProductCart(accountID, productDetailID);
                        }
                        listCustomerProductCarts = new CartDAO().getCustomerCart(accountID);
                        for (ProductCart customerProduct : listCustomerProductCarts) {
                            totalPrice += customerProduct.getPrice() * customerProduct.getQuantity();
                        }
                        req.getSession().setAttribute("ProductCart", listCustomerProductCarts);
                        req.getSession().setAttribute("Subtotal", totalPrice);
                    }
                    req.getSession().setAttribute("Code", code);
                    req.setAttribute("Discount", CouponCode);
                    req.getRequestDispatcher("cart.jsp").forward(req, resp);
                    break;
                case "remove": {
                    i = 0;
                    if (acc == null && productDetailID != 0) {
                        if (req.getSession().getAttribute("GoogleAccount") != null) {//Customer using Google Account
                            GooglePojo googlePojo = (GooglePojo) req.getSession().getAttribute("GoogleAccount");
                            int accountID = googlePojo.getAccountID();

                            new CartDAO().DeleteCustomerProductCart(accountID, productDetailID);
                            List<ProductCart> listCustomerProductCarts = new CartDAO().getCustomerCart(accountID);
                            for (ProductCart customerProduct : listCustomerProductCarts) {
                                totalPrice += customerProduct.getPrice() * customerProduct.getQuantity();
                            }
                            req.getSession().setAttribute("ProductCart", listCustomerProductCarts);
                            req.getSession().setAttribute("Subtotal", totalPrice);
                        } else {
                            for (ProductCart guestProduct : listGuestProductCart) {
                                if (guestProduct.getProductDetailID() == productDetailID) {
                                    break;
                                }
                                i++;
                            }
                            listGuestProductCart.remove(i);
                            for (ProductCart guestProduct : listGuestProductCart) {
                                totalPrice += guestProduct.getPrice() * guestProduct.getQuantity();
                            }
                            req.getSession().setAttribute("ProductCart", listGuestProductCart);
                            req.getSession().setAttribute("Subtotal", totalPrice);
                            req.setAttribute("Code", code);
                            req.setAttribute("Discount", CouponCode);
                        }

                    } //Customer
                    else if (acc != null && productDetailID != 0) {
                        int accountID = acc.getAccountID();
                        new CartDAO().DeleteCustomerProductCart(accountID, productDetailID);
                        List<ProductCart> listCustomerProductCarts = new CartDAO().getCustomerCart(accountID);
                        for (ProductCart customerProduct : listCustomerProductCarts) {
                            totalPrice += customerProduct.getPrice() * customerProduct.getQuantity();
                        }
                        req.getSession().setAttribute("ProductCart", listCustomerProductCarts);
                        req.getSession().setAttribute("Subtotal", totalPrice);
                    }
                    req.getRequestDispatcher("cart.jsp").forward(req, resp);
                    break;
                }
                case "buyNow":
                    if (acc == null && productDetailID != 0) {
                        if (req.getSession().getAttribute("GoogleAccount") != null) {//Customer using Google Account
                            GooglePojo googlePojo = (GooglePojo) req.getSession().getAttribute("GoogleAccount");
                            int accountID = googlePojo.getAccountID();
                            boolean exist = false;
                            List<ProductCart> listCustomerProductCarts = new CartDAO().getCustomerCart(accountID);
                            for (ProductCart customerProduct : listCustomerProductCarts) {
                                if (customerProduct.getProductDetailID() == productDetailID) {
                                    new CartDAO().updateCustomerProductCart(accountID, productDetailID, (customerProduct.getQuantity() + 1));
                                    exist = true;
                                    break;
                                }
                            }
                            if (!exist) {
                                new CartDAO().addCustomerProductCart(accountID, productDetailID);
                            }
                            listCustomerProductCarts = new CartDAO().getCustomerCart(accountID);
                            for (ProductCart customerProduct : listCustomerProductCarts) {
                                totalPrice += customerProduct.getPrice() * customerProduct.getQuantity();
                            }
                            req.getSession().setAttribute("ProductCart", listCustomerProductCarts);
                            req.getSession().setAttribute("Subtotal", totalPrice);
                        } else {
                            ProductCart gProduct = new CartDAO().getGProductCart(productDetailID);
                            boolean exist = false;
                            for (ProductCart guestProduct : listGuestProductCart) {
                                if (guestProduct.getProductDetailID() == productDetailID) {
                                    guestProduct.setQuantity(guestProduct.getQuantity() + 1);
                                    exist = true;
                                    break;
                                }
                            }
                            if (!exist) {
                                gProduct.setQuantity(1);
                                listGuestProductCart.add(gProduct);
                            }
                            for (ProductCart guestProduct : listGuestProductCart) {
                                totalPrice += guestProduct.getPrice() * guestProduct.getQuantity();
                            }
                            req.getSession().setAttribute("ProductCart", listGuestProductCart);
                            req.getSession().setAttribute("Subtotal", totalPrice);
                        }

                    } //Customer
                    else if (acc != null && productDetailID != 0) {
                        boolean exist = false;
                        int accountID = acc.getAccountID();
                        List<ProductCart> listCustomerProductCarts = new CartDAO().getCustomerCart(accountID);
                        for (ProductCart customerProduct : listCustomerProductCarts) {
                            if (customerProduct.getProductDetailID() == productDetailID) {
                                new CartDAO().updateCustomerProductCart(accountID, productDetailID, (customerProduct.getQuantity() + 1));
                                exist = true;
                                break;
                            }
                        }
                        if (!exist) {
                            new CartDAO().addCustomerProductCart(accountID, productDetailID);
                        }
                        listCustomerProductCarts = new CartDAO().getCustomerCart(accountID);
                        for (ProductCart customerProduct : listCustomerProductCarts) {
                            totalPrice += customerProduct.getPrice() * customerProduct.getQuantity();
                        }
                        req.getSession().setAttribute("ProductCart", listCustomerProductCarts);
                        req.getSession().setAttribute("Subtotal", totalPrice);
                    }
                    req.getSession().setAttribute("Code", code);
                    req.setAttribute("Discount", CouponCode);
                    req.getRequestDispatcher("cart.jsp").forward(req, resp);
                    break;
                case "addToCart":
                    int quantity = Integer.parseInt(req.getParameter("txtQuantity"));
                    if (acc == null && productDetailID != 0) {
                        if (req.getSession().getAttribute("GoogleAccount") != null) {//Customer using Google Account
                            GooglePojo googlePojo = (GooglePojo) req.getSession().getAttribute("GoogleAccount");
                            int accountID = googlePojo.getAccountID();
                            boolean exist = false;
                            List<ProductCart> listCustomerProductCarts = new CartDAO().getCustomerCart(accountID);
                            for (ProductCart customerProduct : listCustomerProductCarts) {
                                if (customerProduct.getProductDetailID() == productDetailID) {
                                    new CartDAO().updateCustomerProductCart(accountID, productDetailID, (customerProduct.getQuantity() + 1));
                                    exist = true;
                                    break;
                                }
                            }
                            if (!exist) {
                                new CartDAO().addCustomerProductCart(accountID, productDetailID);
                            }
                            listCustomerProductCarts = new CartDAO().getCustomerCart(accountID);
                            for (ProductCart customerProduct : listCustomerProductCarts) {
                                totalPrice += customerProduct.getPrice() * customerProduct.getQuantity();
                            }
                            req.getSession().setAttribute("ProductCart", listCustomerProductCarts);
                            req.getSession().setAttribute("Subtotal", totalPrice);
                        } else {
                            ProductCart gProduct = new CartDAO().getGProductCart(productDetailID);
                            boolean exist = false;
                            for (ProductCart guestProduct : listGuestProductCart) {
                                if (guestProduct.getProductDetailID() == productDetailID) {
                                    if (guestProduct.getQuantity() != 1) {
                                        guestProduct.setQuantity(guestProduct.getQuantity() + quantity);
                                    } else {
                                        guestProduct.setQuantity(guestProduct.getQuantity() + 1);
                                    }
                                    exist = true;
                                    break;
                                }
                            }
                            if (!exist) {
                                gProduct.setQuantity(quantity);
                                listGuestProductCart.add(gProduct);
                            }
                            for (ProductCart guestProduct : listGuestProductCart) {
                                totalPrice += guestProduct.getPrice() * guestProduct.getQuantity();
                            }
                            req.getSession().setAttribute("ProductCart", listGuestProductCart);
                            req.getSession().setAttribute("Subtotal", totalPrice);
                        }

                    } //Customer
                    else if (acc != null && productDetailID != 0) {
                        boolean exist = false;
                        int accountID = acc.getAccountID();
                        List<ProductCart> listCustomerProductCarts = new CartDAO().getCustomerCart(accountID);
                        for (ProductCart customerProduct : listCustomerProductCarts) {
                            if (customerProduct.getProductDetailID() == productDetailID) {
                                new CartDAO().updateCustomerProductCart(accountID, productDetailID, (customerProduct.getQuantity() + 1));
                                exist = true;
                                break;
                            }
                        }
                        if (!exist) {
                            System.out.println(accountID + " " + productDetailID);
                            new CartDAO().addCustomerProductCart(accountID, productDetailID);
                        }
                        listCustomerProductCarts = new CartDAO().getCustomerCart(accountID);
                        for (ProductCart customerProduct : listCustomerProductCarts) {
                            totalPrice += customerProduct.getPrice() * customerProduct.getQuantity();
                        }
                        req.getSession().setAttribute("ProductCart", listCustomerProductCarts);
                        req.getSession().setAttribute("Subtotal", totalPrice);
                    }
                    resp.sendRedirect("detail?productID=" + productDetailID + "&?numberQuantity=" + quantity + "");
                    break;
                default: {
                    //Customer    
                    if (acc != null) {
                        int accountID = acc.getAccountID();
                        List<ProductCart> listCustomerProductCarts = new CartDAO().getCustomerCart(accountID);
                        for (ProductCart customerProduct : listCustomerProductCarts) {
                            totalPrice += customerProduct.getPrice() * customerProduct.getQuantity();
                        }
                        req.getSession().setAttribute("ProductCart", listCustomerProductCarts);
                        req.getSession().setAttribute("Subtotal", totalPrice);
                    //Customer Using Account Google        
                    } else if (req.getSession().getAttribute("GoogleAccount") != null) {
                        GooglePojo googlePojo = (GooglePojo) req.getSession().getAttribute("GoogleAccount");
                        int accountID = googlePojo.getAccountID();
                        List<ProductCart> listCustomerProductCarts = new CartDAO().getCustomerCart(accountID);
                        for (ProductCart customerProduct : listCustomerProductCarts) {
                            totalPrice += customerProduct.getPrice() * customerProduct.getQuantity();
                        }
                        req.getSession().setAttribute("ProductCart", listCustomerProductCarts);
                        req.getSession().setAttribute("Subtotal", totalPrice);
                    }
                    req.getSession().setAttribute("Code", code);
                    req.setAttribute("Discount", CouponCode);
                    req.getRequestDispatcher("cart.jsp").forward(req, resp);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            resp.sendRedirect("Error");
        }

    }

}
