/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 *
 * @author acer
 */
public class AccCusCom {
    private Account acc;
    private Customer customer;
    private Comments cmt;

    public AccCusCom() {
    }

    public AccCusCom(Account acc, Customer customer, Comments cmt) {
        this.acc = acc;
        this.customer = customer;
        this.cmt = cmt;
    }

    public Account getAcc() {
        return acc;
    }

    public void setAcc(Account acc) {
        this.acc = acc;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Comments getCmt() {
        return cmt;
    }

    public void setCmt(Comments cmt) {
        this.cmt = cmt;
    }

    @Override
    public String toString() {
        return customer.getContactName() +" "+ cmt.getTime() +" "+ cmt.getRate()+" "+ cmt.getDescription();
    }
    
    
}
