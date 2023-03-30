/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAL.DBcontext;
import DAL.ProductDiscountUnitOnOrder;
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
}
