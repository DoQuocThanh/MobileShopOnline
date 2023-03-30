/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Admin;

import java.util.Date;

/**
 *
 * @author blabl
 */
public class Event {
  private String eventID;
    private String eventName;
    private String picture;
    private Date startEvent;
    private Date endEvent;
    private float discount;
    private int discountID;

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Date getStartEvent() {
        return startEvent;
    }

    public void setStartEvent(Date startEvent) {
        this.startEvent = startEvent;
    }

    public Date getEndEvent() {
        return endEvent;
    }

    public void setEndEvent(Date endEvent) {
        this.endEvent = endEvent;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public int getDiscountID() {
        return discountID;
    }

    public void setDiscountID(int discountID) {
        this.discountID = discountID;
    }

    public Event(String eventID, String eventName, String picture, Date startEvent, Date endEvent, float discount, int discountID) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.picture = picture;
        this.startEvent = startEvent;
        this.endEvent = endEvent;
        this.discount = discount;
        this.discountID = discountID;
    }

    public Event() {
    }

    
    
}
