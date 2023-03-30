/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.util.Date;

/**
 *
 * @author blabl
 */
public class Event {
    private String saleID;
    private String nameSale;
    private String picture;
    private Date startSale;
    private Date endSale;

    public Event(String saleID, String nameSale, String picture, Date startSale, Date endSale) {
        this.saleID = saleID;
        this.nameSale = nameSale;
        this.picture = picture;
        this.startSale = startSale;
        this.endSale = endSale;
    }

    public String getSaleID() {
        return saleID;
    }

    public void setSaleID(String saleID) {
        this.saleID = saleID;
    }

    public String getNameSale() {
        return nameSale;
    }

    public void setNameSale(String nameSale) {
        this.nameSale = nameSale;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Date getStartSale() {
        return startSale;
    }

    public void setStartSale(Date startSale) {
        this.startSale = startSale;
    }

    public Date getEndSale() {
        return endSale;
    }

    public void setEndSale(Date endSale) {
        this.endSale = endSale;
    }
    
    
    
}
