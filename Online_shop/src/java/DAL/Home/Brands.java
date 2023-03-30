/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.Home;

/**
 *
 * @author acer
 */
public class Brands {
    private int BrandID;
    private String BrandName;
    private String Picture;

    public Brands() {
    }

    public Brands(int BrandID, String BrandName, String Picture) {
        this.BrandID = BrandID;
        this.BrandName = BrandName;
        this.Picture= Picture;
    }

    public Brands(String BrandName) {
        this.BrandName = BrandName;
    }
    
    
    public int getBrandID() {
        return BrandID;
    }

    public void setBrandID(int BrandID) {
        this.BrandID = BrandID;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String BrandName) {
        this.BrandName = BrandName;
    }

    public String getPictureString() {
        return Picture;
    }

    public void setPictureString(String PictureString) {
        this.Picture = PictureString;
    }
    
    public String getPicture() {
        return Picture;
    }

    public void setPicture(String Picture) {
        this.Picture = Picture;
    }
}
