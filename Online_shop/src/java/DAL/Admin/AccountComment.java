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
public class AccountComment {
    private int accountID;
    private String email;
    private int commentID;
    private int status;
    private int ProductID;
    private String productName;
    private int rate;
    private Date date;
    private String description;
    private String picture;

    public AccountComment() {
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public AccountComment(int accountID, String email, int commentID, int status, int ProductID, String productName, int rate, Date date, String description, String picture) {
        this.accountID = accountID;
        this.email = email;
        this.commentID = commentID;
        this.status = status;
        this.ProductID = ProductID;
        this.productName = productName;
        this.rate = rate;
        this.date = date;
        this.description = description;
        this.picture = picture;
    }
    
    
    
    
}
