/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Admin;

/**
 *
 * @author blabl
 */
public class MonthRevenue {
    private float revenue;
    private String month;

    public MonthRevenue() {
    }

    public float getRevenue() {
        return revenue;
    }

    public void setRevenue(float revenue) {
        this.revenue = revenue;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public MonthRevenue(float revenue, String month) {
        this.revenue = revenue;
        this.month = month;
    }
    
}
