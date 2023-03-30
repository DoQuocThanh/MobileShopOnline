/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.shop;

import java.sql.Date;

/**
 *
 * @author LEGION
 */
public class Comments {
    private int CommentID;
    private int AccountID;
    private int Rate;
    private int ProductID;
    private Date Time;
    private String Description;
    private String Picture;
    private int TotalRate;
    private int TotalComment;

    public Comments() {
    }

    public Comments(int Rate) {
        this.Rate = Rate;
    }

    public Comments(int TotalRate, int TotalComment) {
        this.TotalRate = TotalRate;
        this.TotalComment = TotalComment;
    }

    

    public int getTotalRate() {
        return TotalRate;
    }

    public void setTotalRate(int TotalRate) {
        this.TotalRate = TotalRate;
    }
    
    public int getTotalComment() {
        return TotalComment;
    }

    public void setTotalComment(int TotalComment) {
        this.TotalComment = TotalComment;
    }
     
    public int getCommentID() {
        return CommentID;
    }

    public void setCommentID(int CommentID) {
        this.CommentID = CommentID;
    }

    public int getAccountID() {
        return AccountID;
    }

    public void setAccountID(int AccountID) {
        this.AccountID = AccountID;
    }

    public int getRate() {
        return Rate;
    }

    public void setRate(int Rate) {
        this.Rate = Rate;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public Date getTime() {
        return Time;
    }

    public void setTime(Date Time) {
        this.Time = Time;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String Picture) {
        this.Picture = Picture;
    }
    
    
}
