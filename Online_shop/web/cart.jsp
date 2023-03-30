<%-- 
    Document   : cảt
    Created on : Jan 28, 2023, 8:05:22 AM
    Author     : LEGION
--%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="template/header.jsp" %>
<!-- Cart Start -->
<c:if test="${empty check}">
    <c:redirect url="/shop">
        <c:param name="choice" value="shop"/>
    </c:redirect>
</c:if>
<div class="container-fluid">
    <div class="row px-xl-5">
        <div class="col-lg-8 table-responsive mb-5">
            <table class="table table-light table-borderless table-hover text-center mb-0" >
                <thead class="thead-dark border-1">
                    <tr>
                        <th>Products</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Total</th>
                        <th>Remove</th>
                    </tr>
                </thead>
                <c:forEach items="${ProductCart}" var="gProduct">
                    <tbody class="align-middle">
                        <tr>
                            <td style="text-align: left; padding-left: 7%"><img src="img/${gProduct.picture}" alt="" style="width: 50px;">
                            <a href="<c:url value="/detail"><c:param name="productID" value="${gProduct.productDetailID}"/></c:url>" 
                               style="color: black">${gProduct.productName} - ${gProduct.color}</a>
                            </td>
                            <td class="align-middle">$<fmt:formatNumber maxFractionDigits="0" value="${gProduct.price}"/></td>
                            <td class="align-middle">
                                <div class="input-group quantity mx-auto" style="width: 100px;">
                                    <div class="input-group-btn">
                                        <c:choose>
                                            <c:when test="${gProduct.quantity == 1}">
                                                <a class="btn btn-sm btn-primary btn-minus" href="<c:url value="/cart">
                                                       <c:param name="type" value="-"/>
                                                       <c:param name="productDetailID" value="${gProduct.productDetailID}"/>
                                                   </c:url>" onclick="return check()">
                                                    <i class="fa fa-minus"></i>
                                                </a>
                                            </c:when>
                                            <c:otherwise>
                                                <a class="btn btn-sm btn-primary btn-minus" href="<c:url value="/cart">
                                                       <c:param name="type" value="-"/>
                                                       <c:param name="productDetailID" value="${gProduct.productDetailID}"/>
                                                   </c:url>">
                                                    <i class="fa fa-minus"></i>
                                                </a>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <input type="text" class="form-control form-control-sm bg-secondary border-0 text-center"  value="${gProduct.quantity}" >
                                    <div class="input-group-btn">
                                        <a class="btn btn-sm btn-primary btn-plus" href="<c:url value="/cart">
                                               <c:param name="type" value="+"/>
                                               <c:param name="productDetailID" value="${gProduct.productDetailID}"/>
                                           </c:url>">
                                            <i class="fa fa-plus"></i>
                                        </a>
                                    </div>
                                </div>
                            </td>
                            <td class="align-middle">$<fmt:formatNumber maxFractionDigits="0" value="${gProduct.quantity * gProduct.price}"/></td>
                            <td class="align-middle"><a class="btn btn-sm btn-danger" href="<c:url value="/cart">
                                                            <c:param name="type" value="remove"/>
                                                            <c:param name="productDetailID" value="${gProduct.productDetailID}"/>
                                                        </c:url>" onclick="return check()"><i class="fa fa-times"></i></a></td>
                        </tr>

                    </tbody>
                </c:forEach>
            </table>
            <c:if test="${empty ProductCart}">
                <p>Your cart have no Products. Please choose the product for order <a href="<c:url value="/shop"/>">Here!</a></p>
            </c:if>
        </div>
        <div class="col-lg-4">
            <form class="mb-30" action="<c:url value="/cart"></c:url>" method="get">
                    <div class="input-group" style="gap: 5px">
                        <input type="text" class="form-control border-0 p-4" style="background-color: rgb(190, 197, 199, 1); border-radius: 5px;" name="txtCouponCode" placeholder="Coupon Code">
                        <div class="input-group-append">
                            <button class="btn btn-primary" style="border-radius: 5px">Apply Coupon</button>
                        </div>
                    </div>
                </form>
                <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-white pr-3">Cart Summary</span></h5>
                <div class="bg-light p-30 mb-5">
                    <div class="border-bottom pb-2">
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
                    <div class="pt-2">
                        <div class="d-flex justify-content-between mt-2">
                            <h5>Total</h5>
                            <h5>$<fmt:formatNumber maxFractionDigits="0" value="${Subtotal - (Subtotal * Discount)}"/></h5>
                        </div>
                            <form action="<c:url value="/checkout"/>" method="get">
                                <button class="btn btn-block btn-primary font-weight-bold my-3 py-3" style="border-radius: 5px;">Proceed To Checkout</button>
                            </form>
                    </div>
                </div>
        </div>
    </div>
</div>
<!-- Cart End -->
<%@include file="template/footer.jsp" %>
<script>
    function check() {
        let text;
        if (confirm("Are you sure to delete this Product") === false) {
            return false;
        } else {
            return true;
        }
    }
</script>
