/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.checkout;

/**
 *
 * @author LEGION
 */
public class GuestOrder {
    private String CustomerID;
    private String Name;
    private String Email;
    private String PhoneNumber;
    private String Address;
    private String City;

    public GuestOrder() {
    }

    public GuestOrder(String CustomerID, String Name, String Email, String PhoneNumber, String Address, String City) {
        this.CustomerID = CustomerID;
        this.Name = Name;
        this.Email = Email;
        this.PhoneNumber = PhoneNumber;
        this.Address = Address;
        this.City = City;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }


    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }
    
}
