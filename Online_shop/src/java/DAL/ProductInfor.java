/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 *
 * @author LEGION
 */
public class ProductInfor {
    private Comments comments;
    private Product product;
    private ProductDetail productDetail;
    public ProductInfor() {
    }

    public ProductInfor(Comments comments, Product product, ProductDetail productDetail) {
        this.comments = comments;
        this.product = product;
        this.productDetail = productDetail;
    }

    public ProductInfor(Comments comments, Product product) {
        this.comments = comments;
        this.product = product;
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
