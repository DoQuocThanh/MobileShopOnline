 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="template/header.jsp" %>
<c:if test="${empty checkPath}">
    <c:redirect url="/ProfileController">
        <c:param name="req" value="profile"/>
    </c:redirect>
</c:if>
<!-- Carousel Start --> 
<section style="background-color: #eee;">
    <div class="container py-5">
        <div class="row">
            <div class="col-lg-4">
                <div class="card mb-4">
                    <div class="card-body text-center">
                        <c:choose>
                            <c:when test="${not empty AccSession}">
                                <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp" alt="avatar"
                                     class="rounded-circle img-fluid" style="width: 150px;">
                                <h5 class="my-3">${CustomerInfor.customer.getContactName()}</h5> 
                                <c:if test="${check == null}">
                                    <button id="s5" class="btn btn-primary " onclick="update()">Update Profile</button>
                                </c:if>
                                <c:if test="${check != null}">
                                    <a href="./ProfileController?req=profile"> <button id="s5" class="btn btn-primary" >Profile </button></a>
                                </c:if>
                                <a href="./chagePass"> <button id="s5" class="btn btn-primary" >Change Password</button></a>
                            </c:when>
                            <c:otherwise>
                                <img src="${GoogleAccount.getPicture()}" alt="avatar"
                                     class="rounded-circle img-fluid" style="width: 150px;">
                                <h5 class="my-3">${CustomerInfor.customer.getContactName()}</h5> 
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>

            </div>
            <c:if test="${check == null}">
                <form id="updateUser" action="./UpdateProfile" method="post"></form>
                <input type="text" form="updateUser" hidden name="pass"  value="${CustomerInfor.account.getPass()}"/>
                <div class="col-lg-8">
                    <div class="card mb-4">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0">Full Name</p>
                                </div>
                                <div class="col-sm-9">

                                    <c:choose>
                                        <c:when test="${not empty AccSession}">
                                            <p id="s1" class="text-muted mb-0">${CustomerInfor.customer.getContactName()}</p>
                                            <p><input pattern="^[\p{L}\s]+$" title="Tên không được chữa ký tự đặc biệt" name="name" form="updateUser" id="a1" style="display: none" 
                                                      type="text" class="form-control" required value="${CustomerInfor.customer.getContactName()}"/></p>
                                            </c:when>
                                            <c:otherwise>
                                            <p class="text-muted mb-0">${GoogleAccount.getName()}</p>
                                        </c:otherwise>
                                    </c:choose>    
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0">Email</p>
                                </div>
                                <div class="col-sm-9">

                                    <c:choose>
                                        <c:when test="${not empty AccSession}">
                                            <p id="s2" class="text-muted mb-0">${CustomerInfor.account.getEmail()}</p>
                                            <p><input id="a2" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" form="updateUser" style="display: none"  type="text" class="form-control" 
                                                      name="email"    readonly value="${CustomerInfor.account.getEmail()}"/></p>
                                            </c:when>
                                            <c:otherwise>
                                            <p class="text-muted mb-0">${GoogleAccount.getEmail()}</p>
                                        </c:otherwise>
                                    </c:choose>         
                                </div>
                            </div>

                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0">Address</p>
                                </div>
                                <div class="col-sm-9">

                                    <c:choose>
                                        <c:when test="${not empty AccSession}">
                                            <p id="s3" class="text-muted mb-0">${CustomerInfor.customer.getAddress()}</p>
                                            <p><input name="address" id="a3" pattern="[a-zA-ZÀ-ỹ0-9\s,'-]{3,}" form="updateUser" style="display: none" type="text" 
                                                      class="form-control" required value="${CustomerInfor.customer.getAddress()}"/></p>
                                            </c:when>
                                            <c:otherwise>
                                            <p class="text-muted mb-0">Ha noi</p>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div> <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                </div>
                                <div class="col-sm-9">
                                    <button id="a5" form="updateUser" style="display: none" class="btn btn-primary" type="submit">Update</button>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </c:if>
            <c:if test="${check}">
                <form id="updatePass" action="./chagePass" method="post"></form>
                <div class="col-lg-8">
                    <div class="card mb-4">
                        <div class="card-body">
                            <div class="row">
                                <div class="container text-center">
                                    <h3>Change Password</h3>
                                    <br/>
                                </div> 
                            </div> 
                            <c:if test="${!mess && mess != null}">
                                <span style="color: red">Mat khau ko dung</span>
                            </c:if>
                            <c:if test="${mess}">
                                <span style="color: green">Doi mk thanh cong</span>
                            </c:if>
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0">Old Pass</p>
                                </div>
                                <div class="col-sm-9">
                                    <p><input name="pass"  form="updatePass" type="text" 
                                              class="form-control" required value=""/></p> 
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-3">
                                    <p class="mb-0">New Pass</p>
                                </div>
                                <div class="col-sm-9">
                                    <p><input name="newpass"  form="updatePass" type="text" 
                                              class="form-control" required value=""/></p> 
                                </div>
                            </div> 
                            <div class="row">
                                <div class="col-sm-3">
                                </div>
                                <div class="col-sm-9">
                                    <button id="a5" form="updatePass" class="btn btn-primary" type="submit">Update</button>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </c:if>
        </div>

        <div class="col-lg-12 table-responsive mb-5">
            <c:if test="${not empty ViewDetail}">
                <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Ordered Detail</span></h5>
                <table class="table table-light table-borderless table-hover text-center mb-0">
                <thead>
                    <tr class="thead-dark border-1">
                        <th>Order ID</th>
                        <th>Products</th>
                        <th>Date Creation</th>
                        <th>Quantity</th>
                        <th>Total Price</th>
                    </tr>
                </thead>
                <c:forEach items="${DetailCustomerOrder}" var="cOrder">
                    <tbody class="align-middle">
                        <tr>
                            <td class="align-middle">#${cOrder.orderID}</td>
                            <td style="text-align: left; padding-left: 5%"><img src="img/${cOrder.picture}" alt="" style="width: 50px;">${cOrder.productName} - ${cOrder.color}</td>
                            <td class="align-middle">${cOrder.orderDate}</td>
                            <td class="align-middle">
                                <div class="quantity mx-auto" style="width: 100px;">
                                    <input type="text" readonly class="form-control form-control-sm bg-secondary1 border-0 text-center" value="${cOrder.quantity}">
                                </div>
                            </td>
                            <td class="align-middle">$<fmt:formatNumber maxFractionDigits="0" value="${cOrder.quantity * cOrder.price}"/></td>
                        </tr>
                    </tbody>    
                </c:forEach>
            </table>
            </c:if>
                <c:if test="${empty ViewDetail}">
                  <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Ordered</span></h5>
                <table class="table table-light table-borderless table-hover text-center mb-0">
                <thead>
                    <tr class="thead-dark border-1">
                        <th>Order ID</th>
                        <th>Date Creation</th>
                        <th>Address</th>
                        <th>Country</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <c:forEach items="${CustomerOrder}" var="cOrder">
                    <tbody class="align-middle">
                        <tr>
                            <td class="align-middle">#${cOrder.orderID}</td>
                            <td class="align-middle">${cOrder.orderDate}</td>
                            <td class="align-middle">${cOrder.shipAddress}</td>
                            <td class="align-middle">${cOrder.shipCountry}</td>
                            <td class="align-middle">
                                <c:if test="${cOrder.status == 0}">
                                    <a class="btn btn-success" href="<c:url value="/updateOrder">
                                           <c:param name="orderID" value="${cOrder.orderID}"/>
                                           <c:param name="type" value="view"/>
                                    </c:url>" style="background-color: yellowgreen; border-color: yellowgreen " type="submit">View</a>
                                    <a class="btn btn-danger" href="<c:url value="/updateOrder">
                                           <c:param name="orderID" value="${cOrder.orderID}"/>
                                           <c:param name="type" value="cancel"/>
                                       </c:url>" type="submit" onclick="return check()">Cancel</a>
                                </c:if>
                                <c:if test="${cOrder.status == 1}">
                                    <a class="btn btn-success" href="<c:url value="/updateOrder">
                                           <c:param name="orderID" value="${cOrder.orderID}"/>
                                           <c:param name="type" value="view"/>
                                    </c:url>" style="background-color: yellowgreen; border-color: yellowgreen " type="submit">View</a>
                                    <text class="btn btn-success"  type="submit">Accepted</text>
                                </c:if>
                                <c:if test="${cOrder.status == 4}">
                                    <a class="btn btn-success" href="<c:url value="/updateOrder">
                                           <c:param name="orderID" value="${cOrder.orderID}"/>
                                           <c:param name="type" value="view"/>
                                    </c:url>" style="background-color: yellowgreen; border-color: yellowgreen " type="submit">View</a>
                                    <text class="btn btn-danger" style="background-color: #eb345f; border-color: #eb345f "  type="submit">Cancelled</text>
                                </c:if>
                                <c:if test="${cOrder.status == 3}">
                                    <a class="btn btn-success" href="<c:url value="/updateOrder">
                                           <c:param name="orderID" value="${cOrder.orderID}"/>
                                           <c:param name="type" value="view"/>
                                    </c:url>" style="background-color: yellowgreen; border-color: yellowgreen " type="submit">View</a>
                                    <text class="btn btn-danger" style="background-color: blue; border-color: blue">Completed</text>
                                </c:if>
                            </td>
                        </tr>
                    </tbody>    
                </c:forEach>
            </table>  
            </c:if>
            <c:if test="${empty CustomerOrder}">
                <p>You have no order here. Please choose the product for order <a href="<c:url value="/shop"/>">Here!</a></p>
            </c:if>
        </div>
    </div>
</section>
<!-- Vendor End -->
<%@include file="template/footer.jsp" %>
<script>
    function update() {
        // Lấy các phần tử cần thay đổi thuộc tính bằng ID
        document.getElementById('s1').style.display = 'none';
        document.getElementById('s2').style.display = 'none';
        document.getElementById('s3').style.display = 'none';
//        document.getElementById('s4').style.display = 'none';
        document.getElementById('s5').style.display = 'none';

        document.getElementById('a1').style.display = 'block';
        document.getElementById('a2').style.display = 'block';
        document.getElementById('a3').style.display = 'block';
//        document.getElementById('a4').style.display = 'block';
        document.getElementById('a5').style.display = 'block';
    }
    function check() {
        let text;
        if (confirm("Are you sure to cancel this order. All of your product In") === false) {
            return false;
        } else {
            return true;
        }
    }
</script>