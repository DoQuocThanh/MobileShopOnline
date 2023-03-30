/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Home;

import DAL.Home.BrandAndQuantity;
import DAL.DBcontext;
import DAL.Home.Event;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author blabl
 */
public class EventDAO extends DBcontext{
    ResultSet rs;
    PreparedStatement ps;
    public ArrayList<Event> getEvents() throws SQLException {
        ArrayList<Event> list = new ArrayList<>();
        try {
            
            Date date = java.util.Calendar.getInstance().getTime();
            String sql = "select * from [Events]";
            ps = getConnection().prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Date dateS = rs.getDate("StartEvent");
                Date dateE = rs.getDate("EndEvent");
                if (dateS.before(date) && date.before(dateE)) {
                    int SaleID = rs.getInt("EventID");
                    String NameSale = rs.getString("EventName");
                    String picture= rs.getString("Picture");
                    Date StartSale = rs.getDate("StartEvent");
                    Date EndSale = rs.getDate("EndEvent");
                    list.add(new Event(sql, NameSale, picture, StartSale, EndSale));
                }
            }
        } catch (SQLException e) {
            getConnection().rollback();
        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }
        return list;
    }
    
}
