
<%-- 
    Document   : checkout
    Created on : Jan 28, 2023, 8:10:19 AM
    Author     : LEGION
--%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="template/header.jsp" %>
<c:if test="${empty check}">
    <c:redirect url="/shop">
        <c:param name="choice" value="shop"/>
    </c:redirect>
</c:if>
<!-- Breadcrumb Start -->
<div class="container-fluid">
    <div class="row px-xl-5">
        <div class="col-12">
            <nav class="breadcrumb bg-light mb-30">
                <a class="breadcrumb-item text-dark" href="<c:url value="/home"/>">Home</a>
                <a class="breadcrumb-item text-dark" href="<c:url value="/cart"/>">Cart</a>
                <span class="breadcrumb-item active">Checkout</span>
            </nav>
        </div>
    </div>
</div>
<!-- Breadcrumb End -->


<!-- Checkout Start -->
<form id="myForm" action="<c:url value="/order"/>" method="get">
    <div class="container-fluid">
    <div class="row px-xl-5">
        <c:if test="${empty AccSession && empty GoogleAccount}">
            <div class="col-lg-8 table-responsive mb-5">
                <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Cart</span></h5>    
                <table class="table table-light table-borderless table-hover text-center mb-0">
                    <thead>
                        <tr>
                            <th>Products</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <c:forEach items="${ProductCart}" var="gProduct">
                        <tbody class="align-middle">
                            <tr>
                                <td style="text-align: left; padding-left: 7%"><img src="img/${gProduct.picture}" alt="" style="width: 50px;">${gProduct.productName} - ${gProduct.color}</td>
                                <td class="align-middle">$<fmt:formatNumber maxFractionDigits="0" value="${gProduct.price}"/></td>
                                <td class="align-middle">
                                    <div class="quantity mx-auto" style="width: 100px;">
                                        <input type="text" readonly class="form-control form-control-sm bg-secondary1 border-0 text-center"  value="${gProduct.quantity}">
                                    </div>
                                </td>
                                <td class="align-middle">$<fmt:formatNumber maxFractionDigits="0" value="${gProduct.quantity * gProduct.price}"/></td>
                            </tr>

                        </tbody>
                    </c:forEach>
                </table>
                <c:if test="${empty ProductCart}">
                    <p>Your cart have no Products. Please choose the product for order <a href="<c:url value="/shop"/>">Here!</a></p>
                </c:if>
            </div>
            <div class="col-lg-8">
                    <h5 class="section-title position-relative text-uppercase mb-3">
                        <span class="bg-secondary pr-3">Billing Address</span>
                    </h5>
                    <div class="bg-light p-30 mb-5">
                        <div class="row">
                            <div class="col-md-6 form-group">
                                <label>First Name</label>
                                <input class="form-control" type="text" placeholder="John" name="txtFirstName" id="txtFirstName">
                            </div>
                            <div class="col-md-6 form-group">
                                <label>Last Name</label>
                                <input class="form-control" type="text" placeholder="Doe" name="txtLastName" id="txtLastName">
                            </div>
                            <div class="col-md-6 form-group">
                                <label>E-mail</label>
                                <input class="form-control" type="text" placeholder="example@gmail.com" name="txtEmail" id="txtEmail">
                            </div>
                            <div class="col-md-6 form-group">
                                <label>Mobile No</label>
                                <input class="form-control" type="text" placeholder="+84 12345678" name="txtPhoneNumber" id="txtPhoneNumber">
                            </div>
                            <div class="col-md-6 form-group">
                                <label>Address</label>
                                <input class="form-control" type="text" placeholder="123 Street" name="txtAddress" id="txtAddress">
                            </div>
                            <div class="col-md-6 form-group">
                                <label>City</label>
                                <input class="form-control" type="text" placeholder="New York" name="txtCity" id="txtCity">
                            </div>
                        </div>
                    </div>
                <h5 style="color: red"><c:out value="${msg}"/></h5>
            </div>
        </c:if>
        <c:if test="${not empty AccSession || not empty GoogleAccount}">
            <div class="col-lg-8 table-responsive mb-5">
                <table class="table table-light table-borderless table-hover text-center mb-0">
                    <thead>
                        <tr>
                            <th>Products</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <c:forEach items="${ProductCart}" var="gProduct">
                        <tbody class="align-middle">
                            <tr>
                                <td style="text-align: left; padding-left: 7%"><img src="img/${gProduct.picture}" alt="" style="width: 50px;">${gProduct.productName} - ${gProduct.color}</td>
                                <td class="align-middle">$<fmt:formatNumber maxFractionDigits="0" value="${gProduct.price}"/></td>
                                <td class="align-middle">
                                    <div class="quantity mx-auto" style="width: 100px;">
                                        <input type="text" readonly class="form-control form-control-sm bg-secondary1 border-0 text-center"  value="${gProduct.quantity}">
                                    </div>
                                </td>
                                <td class="align-middle">$<fmt:formatNumber maxFractionDigits="0" value="${gProduct.quantity * gProduct.price}"/></td>
                            </tr>

                        </tbody>
                    </c:forEach>
                </table>
                <c:if test="${empty ProductCart}">
                    <p>Your cart have no Products. Please choose the product for order <a href="<c:url value="/shop"/>">Here!</a></p>
                </c:if>
            </div>
        </c:if>
        <div class="col-lg-4">
            <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Order Total</span></h5>
            <div class="bg-light p-30 mb-5">
                <div class="border-bottom">
                    <h6 class="mb-3">Products</h6>
                    <c:forEach items="${ProductCart}" var="gProduct">
                        <div class="d-flex justify-content-between">
                            <p>${gProduct.productName} - ${gProduct.color} x${gProduct.quantity}</p>
                            <p>$<fmt:formatNumber maxFractionDigits="0" value="${gProduct.price}"/></p>
                        </div>
                    </c:forEach>
                </div>
                <div class="border-bottom pt-3 pb-2">
                    <div class="d-flex justify-content-between mb-3">
                        <h6>Subtotal</h6>
                        <h6>$<fmt:formatNumber maxFractionDigits="0" value="${Subtotal}"/></h6>
                    </div>
                    <div class="d-flex justify-content-between mb-3">
                        <h6>Coupon Code</h6>
                        <c:choose>
                            <c:when test="${not empty Code}">
                                <h6>${Code}</h6>
                            </c:when>
                            <c:otherwise>
                                <h6>None</h6>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="d-flex justify-content-between">
                        <h6 class="font-weight-medium">Shipping</h6>
                        <h6 class="font-weight-medium">Free</h6>
                    </div>
                </div>
                <div class="pt-2">
                    <div class="d-flex justify-content-between mt-2">
                        <h5>Total</h5>
                        <c:if test="${not empty Code}">
                            <h5>$<fmt:formatNumber maxFractionDigits="0" value="${Subtotal -(Subtotal * 0.1)}"/></h5>
                        </c:if>
                        <c:if test="${empty Code}">
                            <h5>$<fmt:formatNumber maxFractionDigits="0" value="${Subtotal -(Subtotal * 0)}"/></h5>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="mb-5">
                <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-secondary pr-3">Payment</span></h5>
                <div class="bg-light p-30">
                    <div class="form-group">
                        <div class="custom-control custom-radio">
                            <input type="radio" class="custom-control-input" name="payment" id="paypal">
                            <label class="custom-control-label" for="paypal">Paypal</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="custom-control custom-radio">
                            <input type="radio" class="custom-control-input" name="payment" id="directcheck" checked>
                            <label class="custom-control-label" for="directcheck">Direct Check</label>
                        </div>
                    </div>
                    <div class="form-group mb-4">
                        <div class="custom-control custom-radio">
                            <input type="radio" class="custom-control-input" name="payment" id="banktransfer">
                            <label class="custom-control-label" for="banktransfer">Bank Transfer</label>
                        </div>
                    </div>
                    <c:if test="${empty AccSession && empty GoogleAccount}">
                        <button class="btn btn-block btn-primary font-weight-bold py-3" onclick="return check()">Place Order</button>
                    </c:if>
                    <c:if test="${not empty AccSession || not empty GoogleAccount}">
                        <a href="<c:url value="/order"/>" class="btn btn-block btn-primary font-weight-bold py-3" onclick="return check()">Place Order</a>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
</form>
<!-- Checkout End -->
<%@include file="template/footer.jsp" %>
<script>
    const nameRegex = /^[a-zA-Z\s]*$/; // Only letters and spaces
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/; // Email format
    const phoneRegex = /^(84|\+84|0)(3[2-9]|5[2689]|7[0|6-9]|8[1-9]|9[0-9])([0-9]{7})$/; // Phone number format
    const addressRegex = /^[a-zA-Z0-9\s,'-]*$/; // Letters, digits, spaces, and some punctuation
    const form = document.getElementById('myForm');

// Add an event listener to the form's submit event
    form.addEventListener('submit', function (event) {
        // Prevent the default form submission
        event.preventDefault();

        // Get the form field values
        const firstName = document.getElementById('txtFirstName').value;
        const lastName = document.getElementById('txtLastName').value;
        const email = document.getElementById('txtEmail').value;
        const phoneNumber = document.getElementById('txtPhoneNumber').value;
        const address = document.getElementById('txtAddress').value;
        const city = document.getElementById('txtCity').value;
        // Validate the form fields

        if (firstName === '') {
            alert('Please enter your first name');
            document.getElementById('txtFirstName').focus();
            return false;
        }
        if (lastName === '') {
            alert('Please enter your last name');
            document.getElementById('txtLastName').focus();
            return false;
        }
        if (email === '') {
            alert('Please enter your email');
            document.getElementById('txtEmail').focus();
            return false;
        }
        if (phoneNumber === '') {
            alert('Please enter your phone number');
            document.getElementById('txtPhoneNumber').focus();
            return false;
        }
        if (address === '') {
            alert('Please enter your address');
            document.getElementById('txtAddress').focus();
            return false;
        }
        if (city === '') {
            alert('Please enter your city');
            document.getElementById('txtCity').focus();
            return false;
        }

        if (!firstName.match(nameRegex)) {
            alert("Please enter a valid first name.");
            return false;
        }

        if (!lastName.match(nameRegex)) {
            alert("Please enter a valid last name.");
            return false;
        }

        if (!email.match(emailRegex)) {
            alert("Please enter a valid email address.");
            return false;
        }

        if (!phoneNumber.match(phoneRegex)) {
            alert("Please enter a valid phone number.");
            return false;
        }

        if (!address.match(addressRegex)) {
            alert("Please enter a valid address.");
            return false;
        }

        if (!city.match(nameRegex)) {
            alert("Please enter a valid city.");
            return false;
        }
        // Submit the form if all fields are valid
        form.submit();
    });
    
    function check() {
        let text;
        if (confirm("Are you sure to order") === false) {
            return false;
        } else {
            return true;
        }
    }
</script>;
