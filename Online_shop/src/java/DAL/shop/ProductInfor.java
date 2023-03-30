/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.shop;

/**
 *
 * @author LEGION
 */
public class ProductInfor {
    private Comments comments;
    private Product product;
    private int TotalComments;
    
    public ProductInfor() {
    }

    public ProductInfor(Comments comments, Product product, int TotalComments) {
        this.comments = comments;
        this.product = product;
        this.TotalComments = TotalComments;
    }

    public int getTotalComments() {
        return TotalComments;
    }

    public void setTotalComments(int TotalComments) {
        this.TotalComments = TotalComments;
    }

    

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
}
