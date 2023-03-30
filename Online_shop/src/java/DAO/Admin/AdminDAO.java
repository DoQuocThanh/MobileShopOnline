/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Admin;

import DAL.Admin.Account;
import DAL.Admin.AccountComment;
import DAL.Admin.Event;
import DAL.DBcontext;
import DAL.Admin.Product;
import DAL.Admin.ProductDetails;
import DAL.Admin.AccountCustomer;
import DAL.Admin.CustomerOrder;
import DAL.Admin.Employee;
import DAL.Admin.HistoryProduct;
import DAL.Admin.HistoryProductDetail;
import DAL.Admin.MonthRevenue;
import DAL.Admin.OrderDetail;
import DAL.Admin.OrderHistory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.Date;
import org.apache.jasper.tagplugins.jstl.core.ForEach;

/**
 *
 * @author blabl
 */
public class AdminDAO extends DBcontext {

    ResultSet rs;
    PreparedStatement ps;

    public ArrayList<Product> getProduct() throws SQLException {
        ArrayList<Product> list = new ArrayList<>();
        try {
            Date date = java.util.Calendar.getInstance().getTime();
            String sql = "select c.Price,g.BrandName, c.ProductID,c.ProductName,e.UnitsInStock,e.UnitsOnOrder,c.Picture,b.Discount,d.Rate,d.AmountRate,a.EventName,a.StartEvent,a.EndEvent from [Events] as a inner join Discounts \n"
                    + "                     as b on a.EventID=b.EventID right join Products as c on b.ProductID=c.ProductID\n"
                    + "                    left join (SELECT ProductID,AVG(Rate) as Rate,Count(Rate) as AmountRate FROM Comments GROUP BY ProductID) as d \n"
                    + "                    on c.ProductID = d.ProductID\n"
                    + "                    left join(select ProductID,SUM(UnitsInStock) as UnitsInStock , SUM(UnitsOnOrder) as UnitsOnOrder from ProductDetails group by ProductID) as e\n"
                    + "                    on c.ProductID = e.ProductID\n"
                    + "                    inner join Brands as g On c.BrandID = g.BrandID";

            ps = getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                Product product = new Product();

                if (rs.getFloat("Discount") == 0) {
                    int productID = rs.getInt("ProductID");
                    String productName = rs.getString("ProductName");
                    String picture = rs.getString("Picture");
                    float price = rs.getFloat("Price");
                    int rate = rs.getInt("Rate");
                    int amountRate = rs.getInt("AmountRate");
                    int unitInStock = rs.getInt("UnitsInStock");
                    int unitOnOrder = rs.getInt("UnitsOnOrder");
                    String brandName = rs.getString("BrandName");
                    String EventName = rs.getString("EventName");
                    product = new Product(productID, productName, unitInStock, unitOnOrder, brandName);
                    list.add(product);

                } else {
                    Date dateS = rs.getDate("StartEvent");
                    Date dateE = rs.getDate("EndEvent");

                    if (dateS.before(date) && date.before(dateE)) {
                        int productID = rs.getInt("ProductID");
                        String productName = rs.getString("ProductName");
                        String picture = rs.getString("Picture");
                        float discount = rs.getFloat("Discount");
                        float price = rs.getFloat("Price");
                        int rate = rs.getInt("Rate");
                        int amountRate = rs.getInt("AmountRate");
                        int unitInStock = rs.getInt("UnitsInStock");
                        int unitOnOrder = rs.getInt("UnitsOnOrder");
                        String brandName = rs.getString("BrandName");
                        String EventName = rs.getString("EventName");
                        product = new Product(productID, productName, unitInStock, unitOnOrder, brandName);
                        list.add(product);

                    }
                }
            }
        } catch (SQLException e) {
            getConnection().rollback();
        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }
        return list;
    }

    public ArrayList<Event> getEventById(String id) {
        ArrayList<Event> list = new ArrayList<>();
        Event event = new Event();
        try {
            String sql = "select * from Events as a inner join Discounts as b on a.EventID=b.EventID where ProductID = ?";
            ps = getConnection().prepareStatement(sql);
            ps.setString(1, id);

            rs = ps.executeQuery();

            while (rs.next()) {

                String eventID = rs.getString("EventID");
                String eventName = rs.getString("EventName");
                String picture = rs.getString("picture");
                Date startEvent = rs.getDate("startEvent");
                Date endEvent = rs.getDate("endEvent");
                float discount = rs.getFloat("discount");
                int discountID = rs.getInt("discountID");
                event = new Event(eventID, eventName, picture, startEvent, endEvent, discount, discountID);
                list.add(event);

            }

        } catch (SQLException e) {

        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }
        return list;
    }

    public ArrayList<AccountCustomer> getAccountCustomer() {
        ArrayList<AccountCustomer> list = new ArrayList<>();
        AccountCustomer acc = new AccountCustomer();
        try {

            String sql = "select * from Accounts inner join Customers on Accounts.CustomerID = Customers.CustomerID";
            ps = getConnection().prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int accountID = rs.getInt("accountID");
                String email = rs.getString("email");
                String companyName = rs.getString("companyName");
                String contactName = rs.getString("contactName");
                String contactTitle = rs.getString("contactTitle");
                String address = rs.getString("address");
                int status = rs.getInt("status");
                int gender = rs.getInt("gender");
                acc = new AccountCustomer(accountID, email, companyName, contactName, contactTitle, address, status, gender);
                list.add(acc);

            }

        } catch (SQLException e) {

        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }
        return list;
    }

    public void changeStatusCustomer(String id, String status, String sql) {

        try {
            System.out.println("DoThanh" + status);
            ps = getConnection().prepareStatement(sql);
            ps.setString(1, status);
            ps.setString(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }

    }

    public ArrayList<AccountComment> getAccountComment() {
        ArrayList<AccountComment> list = new ArrayList<>();
        AccountComment acc = new AccountComment();
        try {

            String sql = "Select a.*,b.Email,c.ProductID,c.ProductName from Comments as a inner join Accounts as b on a.AccountID = b.AccountID\n"
                    + "inner join Products as c on a.ProductID = c.ProductID";
            ps = getConnection().prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                int accountID = rs.getInt("accountID");
                String email = rs.getString("email");
                int commentID = rs.getInt("commentID");
                int status = rs.getInt("status");

                int ProductID = rs.getInt("ProductID");
                String productName = rs.getString("productName");
                int rate = rs.getInt("rate");
                Date date = rs.getDate("time");
                String description = rs.getString("description");
                String picture = rs.getString("picture");
                acc = new AccountComment(accountID, email, commentID, status, ProductID, productName, rate, date, description, picture);
                list.add(acc);

            }

        } catch (SQLException e) {

        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }
        return list;
    }

    public ArrayList<CustomerOrder> getCustomerOrder() {
        ArrayList<CustomerOrder> list = new ArrayList<>();
        CustomerOrder acc = new CustomerOrder();
        try {

            String sql = "select * from Orders as a inner join Customers  as b on a.CustomerID = b.CustomerID";
            ps = getConnection().prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                String customerID = rs.getString("customerID");
                int orderID = rs.getInt("orderID");
                String contactName = rs.getString("contactName");
                Date requiredDate = rs.getDate("requiredDate");
                Date shippedDate = rs.getDate("shippedDate");
                Date orderDate = rs.getDate("orderDate");
                String shipAddress = rs.getString("shipAddress");
                int status = rs.getInt("status");

                acc = new CustomerOrder(customerID, orderID, contactName, requiredDate, shippedDate, orderDate, shipAddress, status);
                list.add(acc);

            }

        } catch (SQLException e) {

        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }
        return list;
    }

    public void manageOrder(String orderID, String EmployeeID, String status, boolean add, boolean minus) {

        String pattern = "yyyy/MM/dd HH:mm:ss";

        DateFormat df = new SimpleDateFormat(pattern);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 7);

        Date today = Calendar.getInstance().getTime();
        Date required = calendar.getTime();

        String todayAsString = df.format(today);
        String requiredString = df.format(required);

        ArrayList<OrderDetail> list = new AdminDAO().getListOrderDetailsbyOrderId(orderID);
        try {
            String sql = "";
            String unit = "";
            if (status.equals("1")) {
                sql = "update Orders Set ShippedDate = ?,Status = ?  where orderID = ?";
                ps = getConnection().prepareStatement(sql);
                ps.setString(1, null);
                ps.setString(2, status);
                ps.setString(3, orderID);
                ps.executeUpdate();

                sql = "INSERT [dbo].[OrderHistory] ( [EmployeeID], [Description], [Time], [OrderID])"
                        + " VALUES ( ?, ?, CAST(? AS DateTime), ?)";
                PreparedStatement ps1 = getConnection().prepareStatement(sql);
                ps1.setString(1, EmployeeID);
                ps1.setString(2, "Accepted");
                ps1.setString(3, todayAsString);
                ps1.setString(4, orderID);
                ps1.executeUpdate();

                if (minus) {
                    for (OrderDetail orderDetail : list) {
                        sql = "update ProductDetails  Set"
                                + " UnitsOnOrder = UnitsOnOrder + ?   "
                                + "where ProductDetailID = ?";
                        ps = getConnection().prepareStatement(sql);

                        ps.setString(1, Integer.toString(orderDetail.getQuantity()));
                        ps.setString(2, Integer.toString(orderDetail.getProductDetailID()));
                        ps.executeUpdate();
                    }
                } else {
                    for (OrderDetail orderDetail : list) {
                        sql = "update ProductDetails Set UnitsInStock = UnitsInStock - ? "
                                + ", UnitsOnOrder = UnitsOnOrder + ?   "
                                + "where ProductDetailID = ?";
                        ps = getConnection().prepareStatement(sql);
                        ps.setString(1, Integer.toString(orderDetail.getQuantity()));
                        ps.setString(2, Integer.toString(orderDetail.getQuantity()));
                        ps.setString(3, Integer.toString(orderDetail.getProductDetailID()));
                        ps.executeUpdate();
                    }
                }

            } else if (status.equals("2")) {
                sql = "update Orders Set ShippedDate = ?,Status = ?  where orderID = ?";
                ps = getConnection().prepareStatement(sql);
                ps.setString(1, null);
                ps.setString(2, status);
                ps.setString(3, orderID);
                ps.executeUpdate();
                sql = "INSERT [dbo].[OrderHistory] ( [EmployeeID], [Description], [Time], [OrderID])"
                        + " VALUES ( ?, ?, CAST(? AS DateTime), ?)";
                ps = getConnection().prepareStatement(sql);
                ps.setString(1, EmployeeID);
                ps.setString(2, "Cancelled");
                ps.setString(3, todayAsString);
                ps.setString(4, orderID);
                ps.executeUpdate();
                if (add) {
                    for (OrderDetail orderDetail : list) {
                        sql = "update ProductDetails Set UnitsInStock = UnitsInStock + ? "
                                + ", UnitsOnOrder = UnitsOnOrder - ?   "
                                + "where ProductDetailID = ?";
                        ps = getConnection().prepareStatement(sql);
                        ps.setString(1, Integer.toString(orderDetail.getQuantity()));
                        ps.setString(2, Integer.toString(orderDetail.getQuantity()));
                        ps.setString(3, Integer.toString(orderDetail.getProductDetailID()));
                        ps.executeUpdate();
                    }
                    if (minus) {
                        for (OrderDetail orderDetail : list) {
                            sql = "update ProductDetails Set UnitsInStock = UnitsInStock + ? "
                                    + "where ProductDetailID = ?";
                            ps = getConnection().prepareStatement(sql);
                            ps.setString(1, Integer.toString(orderDetail.getQuantity()));

                            ps.setString(2, Integer.toString(orderDetail.getProductDetailID()));
                            ps.executeUpdate();
                        }
                    }
                }

            } else if (status.equals("0")) {
                sql = "update Orders Set ShippedDate = ?,Status = ?  where orderID = ?";
                ps = getConnection().prepareStatement(sql);
                ps.setString(1, null);
                ps.setString(2, status);
                ps.setString(3, orderID);
                ps.executeUpdate();

                sql = "INSERT [dbo].[OrderHistory] ( [EmployeeID], [Description], [Time], [OrderID])"
                        + " VALUES ( ?, ?, CAST(? AS DateTime), ?)";

                ps = getConnection().prepareStatement(sql);
                ps.setString(1, EmployeeID);
                ps.setString(2, "Pending");
                ps.setString(3, todayAsString);
                ps.setString(4, orderID);
                ps.executeUpdate();
                if (add) {
                    for (OrderDetail orderDetail : list) {
                        sql = "update ProductDetails Set UnitsInStock = UnitsInStock + ? "
                                + ", UnitsOnOrder = UnitsOnOrder - ?   "
                                + "where ProductDetailID = ?";
                        ps = getConnection().prepareStatement(sql);
                        ps.setString(1, Integer.toString(orderDetail.getQuantity()));
                        ps.setString(2, Integer.toString(orderDetail.getQuantity()));
                        ps.setString(3, Integer.toString(orderDetail.getProductDetailID()));
                        ps.executeUpdate();
                    }
                }
                if (minus) {
                    for (OrderDetail orderDetail : list) {
                        sql = "update ProductDetails Set UnitsInStock = UnitsInStock + ? "
                                + "where ProductDetailID = ?";
                        ps = getConnection().prepareStatement(sql);
                        ps.setString(1, Integer.toString(orderDetail.getQuantity()));

                        ps.setString(2, Integer.toString(orderDetail.getProductDetailID()));
                        ps.executeUpdate();
                    }
                }
            } else if (status.equals("3")) {
                sql = "update Orders Set ShippedDate = CAST( ? AS DateTime),Status = ?  where orderID = ?";
                ps = getConnection().prepareStatement(sql);
                ps.setString(1, todayAsString);
                ps.setString(2, status);
                ps.setString(3, orderID);
                ps.executeUpdate();
                sql = "INSERT [dbo].[OrderHistory] ( [EmployeeID], [Description], [Time], [OrderID])"
                        + " VALUES ( ?, ?, CAST(? AS DateTime), ?)";
                ps = getConnection().prepareStatement(sql);
                ps.setString(1, EmployeeID);
                ps.setString(2, "Done");
                ps.setString(3, todayAsString);
                ps.setString(4, orderID);
                ps.executeUpdate();
                if (!add) {
                    for (OrderDetail orderDetail : list) {
                        sql = "update ProductDetails Set UnitsInStock = UnitsInStock - ? "
                                + "where ProductDetailID = ?";
                        ps = getConnection().prepareStatement(sql);
                        ps.setString(1, Integer.toString(orderDetail.getQuantity()));

                        ps.setString(2, Integer.toString(orderDetail.getProductDetailID()));
                        ps.executeUpdate();
                    }
                } else {
                    for (OrderDetail orderDetail : list) {
                        sql = "update ProductDetails Set UnitsInStock = UnitsInStock"
                                + ", UnitsOnOrder = UnitsOnOrder - ?   "
                                + "where ProductDetailID = ?";
                        ps = getConnection().prepareStatement(sql);

                        ps.setString(1, Integer.toString(orderDetail.getQuantity()));
                        ps.setString(2, Integer.toString(orderDetail.getProductDetailID()));
                        ps.executeUpdate();
                    }

                }
            }

        } catch (SQLException e) {
        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }

    }

    public ArrayList<OrderHistory> getOrderHistory() {
        ArrayList<OrderHistory> list = new ArrayList<>();
        OrderHistory acc = new OrderHistory();
        try {

            String sql = "select * from orderhistory inner join Employees on OrderHistory.EmployeeID=Employees.EmployeeID";
            ps = getConnection().prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                String employeeID = rs.getString("employeeID");
                String employeeName = rs.getString("firstname") + rs.getString("lastname");
                Date time = rs.getDate("time");
                String description = rs.getString("description");
                int orderID = rs.getInt("orderID");

                acc = new OrderHistory(employeeID, employeeName, time, description, orderID);
                list.add(acc);

            }

        } catch (SQLException e) {

        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }
        return list;
    }

    public ArrayList<OrderDetail> getListOrderDetailsbyOrderId(String orderId) {
        ArrayList<OrderDetail> list = new ArrayList<>();
        OrderDetail acc = new OrderDetail();
        try {

            String sql = "select a.*,b.*,c.*,d.ProductName from OrderDetails as a \n"
                    + "inner join ProductDetails as b on a.ProductDetailID=b.ProductDetailID \n"
                    + "inner join Discounts as c on a.DiscountID=c.DiscountID \n"
                    + "inner join Products as d on b.ProductID = d.ProductID\n"
                    + "where a.OrderID = ?";
            ps = getConnection().prepareStatement(sql);
            ps.setString(1, orderId);
            rs = ps.executeQuery();

            while (rs.next()) {

                int orderID = rs.getInt("orderID");
                String productName = rs.getString("productName");
                String picture = rs.getString("picture");
                int quantity = rs.getInt("quantity");
                float discount = rs.getFloat("discount");
                float unitprice = rs.getFloat("unitprice");
                int productdetailid = rs.getInt("productdetailid");

                acc = new OrderDetail(orderID, productName, picture, quantity, discount, unitprice, productdetailid);
                list.add(acc);

            }

        } catch (SQLException e) {

        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }
        return list;
    }

    public Product getProductByProductID(String id) {
        Product product = new Product();
        try {

            String sql = "select c.pin, c.BrandID,c.PhoneScreen,c.OperatingSystem,c.Ram,c.Chip,c.Price,g.BrandName, c.ProductID,c.ProductName,e.UnitsInStock,e.UnitsOnOrder,c.Picture,d.Rate,d.AmountRate from  Products as c\n"
                    + "                                     left join (SELECT ProductID,AVG(Rate) as Rate,Count(Rate) as AmountRate FROM Comments GROUP BY ProductID) as d \n"
                    + "                                       on c.ProductID = d.ProductID\n"
                    + "                                       left join(select ProductID,SUM(UnitsInStock) as UnitsInStock , SUM(UnitsOnOrder) as UnitsOnOrder from ProductDetails group by ProductID) as e\n"
                    + "                                        on c.ProductID = e.ProductID\n"
                    + "                                       inner join Brands as g On c.BrandID = g.BrandID where c.ProductID = ?";

            ps = getConnection().prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {

                int productID = rs.getInt("ProductID");
                String productName = rs.getString("ProductName");

                String picture = rs.getString("Picture");

                float price = rs.getFloat("Price");

                int rate = rs.getInt("Rate");
                int amountRate = rs.getInt("AmountRate");

                String chip = rs.getString("Chip");

                String phoneScreen = rs.getString("phoneScreen");

                String pin = rs.getString("pin");

                int ram = rs.getInt("ram");

                int brandID = rs.getInt("brandID");

                int unitInStock = rs.getInt("UnitsInStock");
                int unitOnOrder = rs.getInt("UnitsOnOrder");
                String brandName = rs.getString("BrandName");
                String operatingSystem = rs.getString("OperatingSystem");
                product = new Product(productID, productName, brandID, chip, ram, pin, phoneScreen, picture, price, unitInStock, brandName, unitOnOrder, price, rate, amountRate, operatingSystem, brandName);
                return product;
            }

        } catch (SQLException e) {
        } finally {

        }
        return product;
    }

    public ArrayList<ProductDetails> getProductDetailById(String id) {
        ArrayList<ProductDetails> list = new ArrayList<>();
        ProductDetails product = new ProductDetails();
        try {

            String sql = "select * from ProductDetails where ProductID = ?";
            ps = getConnection().prepareStatement(sql);
            ps.setString(1, id);

            rs = ps.executeQuery();

            while (rs.next()) {

                int productDetailID = rs.getInt("productDetailID");
                int productID = rs.getInt("productID");
                int productStorage = rs.getInt("productStorage");
                float price = rs.getFloat("Unitprice");
                String picture = rs.getString("Picture");
                String coler = rs.getString("coler");
                int unitInStock = rs.getInt("UnitsInStock");
                int unitOnOrder = rs.getInt("UnitsOnOrder");
                product = new ProductDetails(productDetailID, productID, productStorage, price, picture, coler, unitInStock, unitOnOrder);
                list.add(product);

            }

        } catch (SQLException e) {

        } finally {
        }
        return list;
    }

    public void updateProduct(String productId, String name, String brandID, String ram, String chip, String phoneScreen, String price, String operatingSystem, String picture, String pin, String employeeID) {

        try {
            String pattern = "yyyy/MM/dd HH:mm:ss";

            DateFormat df = new SimpleDateFormat(pattern);

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, 7);

            Date today = Calendar.getInstance().getTime();
            Date required = calendar.getTime();

            String todayAsString = df.format(today);
            String requiredString = df.format(required);

            String sql = "UPDATE products SET ProductName = ? , brandID = ?, ram = ?,chip = ?, phoneScreen = ?, price = ?, picture = ?, OperatingSystem = ? ,Pin = ? WHERE productId = ?";
            ps = getConnection().prepareStatement(sql);
            ps.setString(1, name);
            System.out.println(price);
            ps.setString(2, brandID);
            ps.setString(3, ram);
            ps.setString(4, chip);
            ps.setString(5, phoneScreen);
            ps.setString(6, price);
            ps.setString(7, picture);
            ps.setString(8, operatingSystem);
            ps.setString(9, pin);
            ps.setString(10, productId);
            ps.executeUpdate();
            sql = "INSERT [dbo].[HistoryProducts] ( [EmployeeID], [ProductID], [ProductName], "
                    + "[BrandID], [Chip], [Ram], [Pin], [OperatingSystem], [PhoneScreen], [Picture], "
                    + "[Price], [Status], [Time]) "
                    + "VALUES ( ?, ?, ?, ?, ?, ?,?,?, "
                    + "?, ?,?,?,CAST(? AS DateTime))";
            ps = getConnection().prepareStatement(sql);
            ps.setString(1, employeeID);
            ps.setString(2, productId);
            ps.setString(3, name);
            ps.setString(4, brandID);
            ps.setString(5, chip);
            ps.setString(6, ram);
            ps.setString(7, pin);
            ps.setString(8, operatingSystem);
            ps.setString(9, phoneScreen);
            ps.setString(10, picture);
            ps.setString(11, price);
            ps.setString(12, "UPDATE");
            ps.setString(13, todayAsString);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }

    }

    public void addProduct(String productId, String name, String brandID, String ram, String chip, String phoneScreen, String price, String operatingSystem, String picture, String pin, String employeeID) {

        try {
            String pattern = "yyyy/MM/dd HH:mm:ss";

            DateFormat df = new SimpleDateFormat(pattern);

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, 7);

            Date today = Calendar.getInstance().getTime();
            Date required = calendar.getTime();

            String todayAsString = df.format(today);
            String requiredString = df.format(required);

            String sql = "INSERT [dbo].[Products] ( [ProductName], [BrandID], [Chip], [Ram], [Pin], [OperatingSystem], [PhoneScreen], [Picture], [Price]) VALUES ( ?, ?, ?, ?, ?, ?,?,?,?)";
            ps = getConnection().prepareStatement(sql);
            ps.setString(1, name);

            ps.setString(2, brandID);
            ps.setString(3, chip);
            ps.setString(4, ram);
            ps.setString(5, pin);
            ps.setString(6, operatingSystem);
            ps.setString(7, phoneScreen);
            ps.setString(8, picture);
            ps.setString(9, price);

            ps.executeUpdate();
            sql = "INSERT [dbo].[HistoryProducts] ( [EmployeeID], [ProductID], [ProductName], "
                    + "[BrandID], [Chip], [Ram], [Pin], [OperatingSystem], [PhoneScreen], [Picture], "
                    + "[Price], [Status], [Time]) "
                    + "VALUES ( ?, ?, ?, ?, ?, ?,?,?, "
                    + "?, ?,?,?,CAST(? AS DateTime))";
            ps = getConnection().prepareStatement(sql);
            ps.setString(1, employeeID);
            ps.setString(2, productId);
            ps.setString(3, name);
            ps.setString(4, brandID);
            ps.setString(5, chip);
            ps.setString(6, ram);
            ps.setString(7, pin);
            ps.setString(8, operatingSystem);
            ps.setString(9, phoneScreen);
            ps.setString(10, picture);
            ps.setString(11, price);
            ps.setString(12, "CREATE");
            ps.setString(13, todayAsString);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }

    }

    public String deleteProduct(String productID, String employeeID) {
        try {
            String pattern = "yyyy/MM/dd HH:mm:ss";

            DateFormat df = new SimpleDateFormat(pattern);

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, 7);

            Date today = Calendar.getInstance().getTime();
            Date required = calendar.getTime();

            String todayAsString = df.format(today);
            String requiredString = df.format(required);

            Product p = getProductByProductID(productID);

            String sql = "INSERT [dbo].[HistoryProducts] ( [EmployeeID], [ProductID], [ProductName], "
                    + "[BrandID], [Chip], [Ram], [Pin], [OperatingSystem], [PhoneScreen], [Picture], "
                    + "[Price], [Status], [Time]) "
                    + "VALUES ( ?, ?, ?, ?, ?, ?,?,?, "
                    + "?, ?,?,?,CAST(? AS DateTime))";
            ps = getConnection().prepareStatement(sql);
            ps.setString(1, employeeID);
            ps.setString(2, productID);
            ps.setString(3, p.getProductName());
            ps.setInt(4, p.getBrandID());
            ps.setString(5, p.getChip());
            ps.setInt(6, p.getRam());
            ps.setString(7, p.getPin());
            ps.setString(8, p.getOperatingSystem());
            ps.setString(9, p.getPhoneScreen());
            ps.setString(10, p.getPicture());
            ps.setFloat(11, p.getPrice());
            ps.setString(12, "DELETE");
            ps.setString(13, todayAsString);
            ps.executeUpdate();

            sql = "select * from ProductDetails where ProductID = ?";
            ps = getConnection().prepareStatement(sql);
            ps.setString(1, productID);

            rs = ps.executeQuery();

            while (rs.next()) {

                String productDetailID = rs.getString("productDetailID");

                String sql1 = "DELETE FROM OrderDetails\n"
                        + "WHERE ProductDetailID =?\n"
                        + "DELETE FROM CartDetails\n"
                        + "WHERE ProductDetailID =?\n"
                        + "DELETE FROM ProductDetails \n"
                        + "WHERE ProductDetailID =?";
                PreparedStatement ps1 = getConnection().prepareStatement(sql1);
                ps1.setString(1, productDetailID);
                ps1.setString(2, productDetailID);
                ps1.setString(3, productDetailID);
                ps1.executeUpdate();
            }

            sql = "DELETE FROM Comments\n"
                    + "WHERE ProductID = ?;\n"
                    + "DELETE FROM Discounts\n"
                    + "WHERE ProductID = ?;\n"
                    + "DELETE FROM Products\n"
                    + "WHERE ProductID = ?;";
            ps = getConnection().prepareStatement(sql);
            ps.setString(1, productID);
            ps.setString(2, productID);
            ps.setString(3, productID);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }
        return "Delete Success";

    }

    public ProductDetails getProductDetailByProductDetailID(String id) {

        ProductDetails product = new ProductDetails();
        try {

            String sql = "select * from ProductDetails where ProductDetailID = ?";
            ps = getConnection().prepareStatement(sql);
            ps.setString(1, id);
            System.err.println(id);
            rs = ps.executeQuery();

            while (rs.next()) {
                int productDetailID = rs.getInt("productDetailID");
                int productID = rs.getInt("productID");
                int productStorage = rs.getInt("productStorage");
                float price = rs.getFloat("Unitprice");
                String picture = rs.getString("Picture");
                System.err.println(picture);
                String coler = rs.getString("coler");
                int unitInStock = rs.getInt("UnitsInStock");
                int unitOnOrder = rs.getInt("UnitsOnOrder");
                product = new ProductDetails(productDetailID, productID, productStorage, price, picture, coler, unitInStock, unitOnOrder);
            }

        } catch (SQLException e) {
            System.err.println(e);
        } finally {
        }
        return product;
    }

    public void updateProductDeteail(String productStorage, String coler, String price, String UnitsInStock, String Picture, String ProductDetailID, String unitsOnOrder, String productID, String employeeID) {
        try {
            String pattern = "yyyy/MM/dd HH:mm:ss";

            DateFormat df = new SimpleDateFormat(pattern);

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, 7);

            Date today = Calendar.getInstance().getTime();
            Date required = calendar.getTime();

            String todayAsString = df.format(today);
            String requiredString = df.format(required);

            String sql = "UPDATE ProductDetails\n"
                    + "SET productStorage = ?, \n"
                    + "    Coler = ?, \n"
                    + "    UnitPrice = ?, \n"
                    + "    UnitsInStock =?,\n"
                    + "	Picture = ?\n"
                    + "WHERE ProductDetailID = ?;";
            ps = getConnection().prepareStatement(sql);
            ps.setString(1, productStorage);
            ps.setString(2, coler);
            ps.setString(3, price);
            ps.setString(4, UnitsInStock);
            ps.setString(5, Picture);
            System.out.println("picture Dothanh: " + Picture);
            ps.setString(6, ProductDetailID);
            ps.executeUpdate();

            sql = "INSERT [dbo].[HistoryProductDetails] ( [EmployeeID], [ProductDetailID], "
                    + "[ProductID], [ProductStorage], [UnitPrice], [Picture], [Coler], [UnitsInStock], "
                    + "[UnitsOnOrder], [Status], [Time]) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
                    + "CAST(? AS DateTime))";
            ps = getConnection().prepareStatement(sql);
            ps.setString(1, employeeID);
            ps.setString(2, ProductDetailID);
            ps.setString(3, productID);
            ps.setString(4, productStorage);
            ps.setString(5, price);
            ps.setString(6, Picture);
            ps.setString(7, coler);
            ps.setString(8, UnitsInStock);
            ps.setString(9, unitsOnOrder);
            ps.setString(10, "UPDATE");
            ps.setString(11, todayAsString);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }

    }

    public void createProductDetail(String productStorage, String coler, String price, String UnitsInStock, String Picture, String ProductDetailID, String unitsOnOrder, String productID, String employeeID) {
        try {
            String pattern = "yyyy/MM/dd HH:mm:ss";

            DateFormat df = new SimpleDateFormat(pattern);

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, 7);

            Date today = Calendar.getInstance().getTime();
            Date required = calendar.getTime();

            String todayAsString = df.format(today);
            String requiredString = df.format(required);

            String sql = "INSERT [dbo].[ProductDetails] ( [ProductID], [ProductStorage], "
                    + "[UnitPrice], [Picture], [Coler], [UnitsInStock], [UnitsOnOrder]) VALUES ( ?, ?, ?,?, ?, ?, ?)";
            ps = getConnection().prepareStatement(sql);
            ps.setString(1, productID);
            ps.setString(2, productStorage);
            ps.setString(3, price);
            ps.setString(4, Picture);
            ps.setString(5, coler);
            ps.setString(6, UnitsInStock);
            ps.setString(7, unitsOnOrder);
            ps.executeUpdate();

            sql = "INSERT [dbo].[HistoryProductDetails] ( [EmployeeID], [ProductDetailID], "
                    + "[ProductID], [ProductStorage], [UnitPrice], [Picture], [Coler], [UnitsInStock], "
                    + "[UnitsOnOrder], [Status], [Time]) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
                    + "CAST(? AS DateTime))";
            ps = getConnection().prepareStatement(sql);
            ps.setString(1, employeeID);
            ps.setString(2, ProductDetailID);
            ps.setString(3, productID);
            ps.setString(4, productStorage);
            ps.setString(5, price);
            ps.setString(6, Picture);
            ps.setString(7, coler);
            ps.setString(8, UnitsInStock);
            ps.setString(9, unitsOnOrder);
            ps.setString(10, "CREATE");
            ps.setString(11, todayAsString);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }

    }

    public String deleteProductDetail(String productDetailID, String employeeID) {
        try {
            String pattern = "yyyy/MM/dd HH:mm:ss";

            DateFormat df = new SimpleDateFormat(pattern);

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, 7);

            Date today = Calendar.getInstance().getTime();
            Date required = calendar.getTime();

            String todayAsString = df.format(today);
            String requiredString = df.format(required);

            ProductDetails p = getProductDetailByProductDetailID(productDetailID);

            String sql = "INSERT [dbo].[HistoryProductDetails] ( [EmployeeID], [ProductDetailID], "
                    + "[ProductID], [ProductStorage], [UnitPrice], [Picture], [Coler], [UnitsInStock], "
                    + "[UnitsOnOrder], [Status], [Time]) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
                    + "CAST(? AS DateTime))";
            ps = getConnection().prepareStatement(sql);
            ps.setString(1, employeeID);
            ps.setInt(2, p.getProductDetailID());
            ps.setInt(3, p.getProductID());
            ps.setInt(4, p.getProductStorage());
            ps.setFloat(5, p.getPrice());
            ps.setString(6, p.getPicture());
            ps.setString(7, p.getColer());
            ps.setInt(8, p.getUnitsInStock());
            ps.setInt(9, p.getUnitsOnOrder());
            ps.setString(10, "DELETE");
            ps.setString(11, todayAsString);
            ps.executeUpdate();

            String sql1 = "DELETE FROM OrderDetails\n"
                    + "WHERE ProductDetailID =?\n"
                    + "DELETE FROM CartDetails\n"
                    + "WHERE ProductDetailID =?\n"
                    + "DELETE FROM ProductDetails \n"
                    + "WHERE ProductDetailID =?";
            PreparedStatement ps1 = getConnection().prepareStatement(sql1);
            ps1.setString(1, productDetailID);
            ps1.setString(2, productDetailID);
            ps1.setString(3, productDetailID);
            ps1.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }
        return "Delete Success";

    }

    public ArrayList<HistoryProduct> getProductHistory() {
        ArrayList<HistoryProduct> list = new ArrayList<>();
        HistoryProduct his = new HistoryProduct();
        try {

            String sql = "select * from HistoryProducts inner join Employees on HistoryProducts.EmployeeID = Employees.EmployeeID;";
            ps = getConnection().prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                String employeeID = rs.getString("employeeID");
                String employeeName = rs.getString("firstname") + rs.getString("lastname");
                Date time = rs.getDate("time");
                int historyID = rs.getInt("historyID");

                String status = rs.getString("status");
                int productID = rs.getInt("productID");
                String productName = rs.getString("productName");
                int brandID = rs.getInt("brandID");
                String chip = rs.getString("chip");
                int ram = rs.getInt("ram");
                String pin = rs.getString("pin");
                String phoneScreen = rs.getString("phoneScreen");
                String picture = rs.getString("picture");
                his = new HistoryProduct(historyID, employeeName, employeeID, status, time, productID, productName, brandID, chip, ram, pin, phoneScreen, picture);
                list.add(his);

            }

        } catch (SQLException e) {

        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }
        return list;
    }

    public ArrayList<HistoryProductDetail> getProductDetailHistory(String productId) {
        ArrayList<HistoryProductDetail> list = new ArrayList<>();
        HistoryProductDetail his = new HistoryProductDetail();
        try {

            String sql = "select * from HistoryProductDetails as a inner join Employees  as b on a.EmployeeID=b.EmployeeID where ProductID = ?";
            ps = getConnection().prepareStatement(sql);
            ps.setString(1, productId);
            rs = ps.executeQuery();

            while (rs.next()) {

                String employeeID = rs.getString("employeeID");
                String employeeName = rs.getString("firstname") + rs.getString("lastname");
                Date time = rs.getDate("time");
                int historyID = rs.getInt("HistoryProductDetailID");
                String status = rs.getString("status");
                int productID = rs.getInt("productID");

                String picture = rs.getString("picture");
                int productDetailID = rs.getInt("productDetailID");

                int productStorage = rs.getInt("productStorage");
                float price = rs.getInt("unitprice");

                String coler = rs.getString("coler");

                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                his = new HistoryProductDetail(productDetailID, employeeName, employeeID, status, time, productDetailID, productID, productStorage, price, picture, coler, UnitsInStock, UnitsOnOrder);
                list.add(his);
            }

        } catch (SQLException e) {

        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }
        return list;
    }

    public ArrayList<Employee> getAccountEmployee() {
        ArrayList<Employee> list = new ArrayList<>();
        Employee acc = new Employee();
        try {
            String sql = "select * from Accounts as a inner join Employees\n"
                    + "             as b on a.EmployeeID = b.EmployeeID ";
            ps = getConnection().prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String email = rs.getString("email");
                String pass = rs.getString("password");
                int accountID = rs.getInt("accountID");
                int employeeID = rs.getInt("employeeID");
                int role = rs.getInt("role");

                String name = rs.getString("firstname");
                int Status = rs.getInt("status");

                Date birthDate = rs.getDate("birthDate");
                String address = rs.getString("address");
                acc = new Employee(accountID, email, pass, employeeID, name, Status, birthDate, address);
                list.add(acc);

            }

        } catch (SQLException e) {

        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }
        return list;
    }

    public void CreateEmployee(String email, String pass, String firstName, String birthDate, String address
    ) {
        ArrayList<Employee> list = new ArrayList<>();
        Employee acc = new Employee();
        try {
            String sql = "INSERT [dbo].[Employees] (  [FirstName],  [BirthDate], [Address],[LastName]) VALUES (?,CAST(? AS DateTime),?,'')\n"
                    + "INSERT [dbo].[Accounts] ([Email], [Password], [EmployeeID], [Role], [Status]) VALUES (?,?,(Select top(1)EmployeeID from Employees order by EmployeeID DESC),1,1)";
            ps = getConnection().prepareStatement(sql);

            ps.setString(1, firstName);
            ps.setString(2, birthDate);
            ps.setString(3, address);
            ps.setString(4, email);
            ps.setString(5, pass);

            ps.executeUpdate();
            System.err.println("Create");
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }

    }

    public void UpdateEmployee(String email, String pass, String firstName, String birthDate, String address, String employeeID
    ) {
        ArrayList<Employee> list = new ArrayList<>();
        Employee acc = new Employee();
        try {
            String sql = "UPDATE Employees SET FirstName = ?,BirthDate=CAST(? AS DateTime),[Address]=? WHERE EmployeeID = ?;\n"
                    + "UPDATE Accounts SET Email = ?,[Password]=? WHERE EmployeeID = ?;";
            ps = getConnection().prepareStatement(sql);

            ps.setString(1, firstName);
            ps.setString(2, birthDate);
            ps.setString(3, address);
            ps.setString(4, employeeID);
            ps.setString(5, email);
            ps.setString(6, pass);
            ps.setString(7, employeeID);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }

    }

    public Employee getEmployeebyEmployeeId(String employeeID) {

        Employee acc = new Employee();
        try {
            String sql = "select * from Accounts as a inner join Employees\n"
                    + "                            as b on a.EmployeeID = b.EmployeeID  where a.EmployeeID=? ";

            ps = getConnection().prepareStatement(sql);
            ps.setString(1, employeeID);
            rs = ps.executeQuery();

            while (rs.next()) {
                String email = rs.getString("email");
                String pass = rs.getString("password");
                int accountID = rs.getInt("accountID");
                int employeeid = rs.getInt("employeeID");
                int role = rs.getInt("role");

                String name = rs.getString("firstname");
                int Status = rs.getInt("status");

                Date birthDate = rs.getDate("birthDate");
                String address = rs.getString("address");
                acc = new Employee(accountID, email, pass, employeeid, name, Status, birthDate, address);

            }

        } catch (SQLException e) {

        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }
        return acc;
    }

    public float getRevenueThisMonthOrdered() {
        float revenue = 0;
        try {
            String sql = "SELECT CONVERT(varchar(7), a.ShippedDate, 120) AS Month,SUM(a.Price) as Earning \n"
                    + "From (select a.ShippedDate,b.*,(c.UnitPrice*b.Quantity*(1-d.Discount)) as Price from Orders as a inner join OrderDetails as b on a.OrderID=b.OrderID\n"
                    + "										  inner join ProductDetails as c on c.ProductDetailID = b.ProductDetailID\n"
                    + "										  inner join Discounts as d on b.DiscountID=d.DiscountID\n"
                    + ") as a\n"
                    + "WHERE CONVERT(varchar(7), a.ShippedDate, 120) = (SELECT FORMAT(GETDATE(), 'yyyy-MM') AS CurrentMonthYear)\n"
                    + "GROUP BY CONVERT(varchar(7), a.ShippedDate, 120);";
            ps = getConnection().prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                revenue = rs.getFloat("Earning");
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }
        return revenue;
    }

    public ArrayList<MonthRevenue> getListRevenueOrdered() {
        ArrayList<MonthRevenue> list = new ArrayList<>();
        MonthRevenue m = new MonthRevenue();
        try {
            String sql = "SELECT CONVERT(varchar(7), a.ShippedDate, 120) AS Month,SUM(a.Price) as Earning \n"
                    + "From (select a.ShippedDate,b.*,(c.UnitPrice*b.Quantity*(1-d.Discount)) as Price from Orders as a inner join OrderDetails as b on a.OrderID=b.OrderID\n"
                    + "										  inner join ProductDetails as c on c.ProductDetailID = b.ProductDetailID\n"
                    + "										  inner join Discounts as d on b.DiscountID=d.DiscountID\n"
                    + ") as a\n"
                    + "\n"
                    + "GROUP BY CONVERT(varchar(7), a.ShippedDate, 120);";
            ps = getConnection().prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                String month = rs.getString("Month");
                float revenue = rs.getFloat("Earning");
                m = new MonthRevenue(revenue, month);
                list.add(m);
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }
        return list;
    }

    public float getRevenueThisMonthInOrder() {
        float revenue = 0;
        try {
            String sql = "SELECT CONVERT(varchar(7), a.OrderDate, 120) AS Month,SUM(a.Price) as Earning \n"
                    + "                    From (select  a.OrderDate,b.*,(c.UnitPrice*b.Quantity*(1-d.Discount)) as Price from Orders as a inner join OrderDetails as b on a.OrderID=b.OrderID\n"
                    + "                   									  inner join ProductDetails as c on c.ProductDetailID = b.ProductDetailID\n"
                    + "                   									  inner join Discounts as d on b.DiscountID=d.DiscountID\n"
                    + "                    ) as a\n"
                    + " WHERE CONVERT(varchar(7), a.OrderDate, 120) = (SELECT FORMAT(GETDATE(), 'yyyy-MM') AS CurrentMonthYear)\n"
                    + " GROUP BY CONVERT(varchar(7), a.OrderDate, 120);";
            ps = getConnection().prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                revenue = rs.getFloat("Earning");
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }
        return revenue;
    }

    public int getAmountOrderThisMonth() {
        int revenue = 0;
        try {
            String sql = "SELECT CONVERT(varchar(7), a.OrderDate, 120) AS Month,Sum(OrderID) as [Order] \n"
                    + "From (select a.OrderDate,b.*,(c.UnitPrice*b.Quantity*(1-d.Discount)) as Price from Orders as a inner join OrderDetails as b on a.OrderID=b.OrderID\n"
                    + "										  inner join ProductDetails as c on c.ProductDetailID = b.ProductDetailID\n"
                    + "										  inner join Discounts as d on b.DiscountID=d.DiscountID\n"
                    + ") as a\n"
                    + "WHERE CONVERT(varchar(7), a.OrderDate, 120) = (SELECT FORMAT(GETDATE(), 'yyyy-MM') AS CurrentMonthYear)\n"
                    + "GROUP BY CONVERT(varchar(7), a.OrderDate, 120);";
            ps = getConnection().prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                revenue = rs.getInt("Order");
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }
        return revenue;
    }

    public int getAmountCustomerInShop() {
        int revenue = 0;
        try {
            String sql = "select COunt(CustomerID) as a from Customers";
            ps = getConnection().prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                revenue = rs.getInt("a");
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBcontext.releaseJBDCObject(rs, ps, getConnection());
        }
        return revenue;
    }

    public static void main(String[] args) {
        System.out.println(new AdminDAO().getAmountOrderThisMonth());
    }

}
