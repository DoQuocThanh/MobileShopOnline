/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Home;

import DAL.DBcontext;
import DAL.Home.ProductDiscountUnitOnOrder;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import org.apache.jasper.tagplugins.jstl.core.ForEach;

/**
 *
 * @author blabl
 */
public class ProductDAO1 extends DBcontext {

    ResultSet rs;
    PreparedStatement ps;

    public ArrayList<ProductDiscountUnitOnOrder> getProductBestSale() throws SQLException {
        ArrayList<ProductDiscountUnitOnOrder> list = new ArrayList<>();
        try {
            Date date = java.util.Calendar.getInstance().getTime();
            String sql = "select c.Price, c.ProductID,c.ProductName,c.Picture,b.Discount,d.Rate,d.AmountRate,a.StartEvent,a.EndEvent from [Events] as a inner join Discounts \n"
                    + "as b on a.EventID=b.EventID inner join Products as c on b.ProductID=c.ProductID\n"
                    + "left join (SELECT ProductID,AVG(Rate) as Rate,Count(Rate) as AmountRate FROM Comments GROUP BY ProductID) as d \n"
                    + "on c.ProductID = d.ProductID";

            ps = getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                Date dateS = rs.getDate("StartEvent");
                Date dateE = rs.getDate("EndEvent");

                if (dateS.before(date) && date.before(dateE)) {
                    int productID = rs.getInt("ProductID");
                    String productName = rs.getString("ProductName");
                    String picture = rs.getString("Picture");
                    float discount = rs.getFloat("Discount");
                    float price = rs.getFloat("Price");
                    int rate = rs.getInt("Rate");
                    int amountRate = rs.getInt("AmountRate");
                    ProductDiscountUnitOnOrder product = new ProductDiscountUnitOnOrder(productID, productName, picture, discount, price, rate, amountRate);
                    list.add(product);
                }

                Collections.sort(list, new Comparator<ProductDiscountUnitOnOrder>() {
                    @Override
                    public int compare(ProductDiscountUnitOnOrder s1, ProductDiscountUnitOnOrder s2) {
                        return Float.compare(s2.getDiscount(), s1.getDiscount());
                    }
                });
            }

        } catch (SQLException e) {
            getConnection().rollback();
        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }
        return list;
    }

    public ArrayList<ProductDiscountUnitOnOrder> getProduct(int status) throws SQLException {
        ArrayList<ProductDiscountUnitOnOrder> list = new ArrayList<>();
        try {
            Date date = java.util.Calendar.getInstance().getTime();
            String sql = "select c.Price, c.ProductID,c.ProductName,e.UnitsInStock,e.UnitsOnOrder,c.Picture,b.Discount,d.Rate,d.AmountRate,a.StartEvent,a.EndEvent from [Events] as a inner join Discounts \n"
                    + " as b on a.EventID=b.EventID right join Products as c on b.ProductID=c.ProductID\n"
                    + "left join (SELECT ProductID,AVG(Rate) as Rate,Count(Rate) as AmountRate FROM Comments GROUP BY ProductID) as d \n"
                    + "on c.ProductID = d.ProductID\n"
                    + "left join(select ProductID,SUM(UnitsInStock) as UnitsInStock , SUM(UnitsOnOrder) as UnitsOnOrder from ProductDetails group by ProductID) as e\n"
                    + "on c.ProductID = e.ProductID";

            ps = getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (status == 1) {
                    if(rs.getFloat("Discount") == 0 ){
                        break;
                    }else{
                    Date dateS = rs.getDate("StartEvent");
                    Date dateE = rs.getDate("EndEvent");

                    if (dateS.before(date) && date.before(dateE)) {
                        int productID = rs.getInt("ProductID");
                        String productName = rs.getString("ProductName");
                        String picture = rs.getString("Picture");
                        float discount = rs.getFloat("Discount");
                        float price = rs.getFloat("Price");
                        int rate = rs.getInt("Rate");
                        int amountRate = rs.getInt("AmountRate");
                        int unitInStock = rs.getInt("UnitsInStock");
                        int unitOnOrder = rs.getInt("UnitsOnOrder");
                        ProductDiscountUnitOnOrder product = new ProductDiscountUnitOnOrder(productID, productName, picture, discount, unitInStock, unitOnOrder, price, rate, amountRate);
                        list.add(product);
                    }
                    }

                    
                } else if (status != 1) {
                    
                    
                    ProductDiscountUnitOnOrder product = new ProductDiscountUnitOnOrder();
                    
                    
                    
                    if(rs.getFloat("Discount") == 0 ){
                        int productID = rs.getInt("ProductID");
                        String productName = rs.getString("ProductName");
                        String picture = rs.getString("Picture");
                        float price = rs.getFloat("Price");
                        int rate = rs.getInt("Rate");
                        int amountRate = rs.getInt("AmountRate");
                        int unitInStock = rs.getInt("UnitsInStock");
                        int unitOnOrder = rs.getInt("UnitsOnOrder");
                        product = new ProductDiscountUnitOnOrder(productID, productName, picture, unitInStock, unitOnOrder, price, rate, amountRate);
                        list.add(product);
                    }else{
                    
                        int productID = rs.getInt("ProductID");
                        String productName = rs.getString("ProductName");
                        String picture = rs.getString("Picture");
                        float discount = rs.getFloat("Discount");
                        float price = rs.getFloat("Price");
                        int rate = rs.getInt("Rate");
                        int amountRate = rs.getInt("AmountRate");
                        int unitInStock = rs.getInt("UnitsInStock");
                        int unitOnOrder = rs.getInt("UnitsOnOrder");
                         product = new ProductDiscountUnitOnOrder(productID, productName, picture, discount, unitInStock, unitOnOrder, price, rate, amountRate);
                        list.add(product);
                }
                    if(list.size()>1 &&list.get(list.size()-1).getProductID()==list.get(list.size()-2).getProductID()){
                            list.remove(list.size()-1);
                        }
                    
            }
               
       }
             if(status == 1){
                    Collections.sort(list, new Comparator<ProductDiscountUnitOnOrder>() {
                        @Override
                        public int compare(ProductDiscountUnitOnOrder s1, ProductDiscountUnitOnOrder s2) {
                            return Float.compare(s2.getDiscount(), s1.getDiscount());
                        }
                    });
                }else if(status ==2){
                    
                    Collections.sort(list, new Comparator<ProductDiscountUnitOnOrder>() {
                        @Override
                        public int compare(ProductDiscountUnitOnOrder s1, ProductDiscountUnitOnOrder s2) {
                            return Float.compare(s2.getUnitOnOrder(), s1.getUnitOnOrder());
                        }
                    });
                }else {
                    Collections.sort(list, new Comparator<ProductDiscountUnitOnOrder>() {
                        @Override
                        public int compare(ProductDiscountUnitOnOrder s1, ProductDiscountUnitOnOrder s2) {
                            return Float.compare(s2.getUnitOnStock(), s1.getUnitOnStock());
                        }
                    });
                }
             

        } catch (SQLException e) {
            getConnection().rollback();
        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }
        return list;
    }

}
