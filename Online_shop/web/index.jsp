<%-- 
    Document   : index
    Created on : Jan 28, 2023, 7:40:01 AM
    Author     : LEGION
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="template/header.jsp" %>
<!-- Carousel Start -->
<c:if test="${Check !='true'}">
    <c:redirect url = "/home" />
</c:if>
<div class="container-fluid bg-white">
    <div class="row">
        <div class="col-lg-12">
            <div id="header-carousel" class="carousel slide carousel-fade mb-30 mb-lg-0" data-ride="carousel">
                <ol class="carousel-indicators">
                    <c:forEach begin="0" end="${Events.size()-1}" var="count"> 
                        <c:if test="${count!=0}">
                            <li data-target="#header-carousel" data-slide-to="${count}" ></li>
                            </c:if>
                            <c:if test="${count==0}">
                            <li data-target="#header-carousel" data-slide-to="${count}" class="active"></li>
                            </c:if>
                        </c:forEach>
                </ol>
                <div class="carousel-inner">
                    <c:set var="count" value="${0}"/>
                    <c:forEach items="${Events}" var="event">
                        <c:if test="${count==0}">
                            <div class="carousel-item position-relative active " style="height: 450px;">
                                <img class="position-absolute w-100 h-100 rounded" src="img/${event.picture}" style="object-fit: cover;">
                                <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
                                    <div class="p-3" style="max-width: 700px;">
                                        <a class="btn btn-outline-light py-2 px-4 mt-3 animate__animated animate__fadeInUp" 
                                           href="<c:url value="/shop"><c:param name="choice" value="showList" /></c:url>">Shop Now</a>
                                        </div>
                                    </div>
                                </div>
                        </c:if>
                        <c:if test="${count==1}">
                            <div class="carousel-item position-relative" style="height:450px;">
                                <img class="position-absolute w-100 h-100 rounded" src="img/${event.picture}" style="object-fit: cover;">
                                <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
                                    <div class="p-3" style="max-width: 700px;">
                                        <a class="btn btn-outline-light py-2 px-4 mt-3 animate__animated animate__fadeInUp" 
                                           href="<c:url value="/shop"><c:param name="choice" value="showList" /></c:url>">Shop Now</a>
                                        </div>
                                    </div>
                             </div>
                            </c:if>
                            <c:set var="count" value="${1}"/>   
                        </c:forEach>        
                </div>
            </div>

        </div>
    </div>
    <!-- Carousel End -->


    <!-- Featured Start -->
    <div class="container-fluid pt-5 bg-white">
        <div class="row px-xl-5 pb-3">
            <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                <div class="d-flex align-items-center bg-white mb-4" style="padding: 30px;">
                    <h1 class="fa fa-check text-primary m-0 mr-3"></h1>
                    <h5 class="font-weight-semi-bold m-0">Quality Product</h5>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                <div class="d-flex align-items-center bg-white mb-4" style="padding: 30px;">
                    <h1 class="fa fa-shipping-fast text-primary m-0 mr-2"></h1>
                    <h5 class="font-weight-semi-bold m-0">Free Shipping</h5>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                <div class="d-flex align-items-center bg-white mb-4" style="padding: 30px;">
                    <h1 class="fas fa-exchange-alt text-primary m-0 mr-3"></h1>
                    <h5 class="font-weight-semi-bold m-0">14-Day Return</h5>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                <div class="d-flex align-items-center bg-white mb-4" style="padding: 30px;">
                    <h1 class="fa fa-phone-volume text-primary m-0 mr-3"></h1>
                    <h5 class="font-weight-semi-bold m-0">24/7 Support</h5>
                </div>
            </div>
        </div>
    </div>
    <!-- Featured End -->


    <!-- Categories Start -->
    <div class="container-fluid pt-5 bg-white">
        <h2 class="section-title position-relative text-uppercase mx-xl-5 mb-4 bg-white"><span class="bg-wite pr-3">Brands</span></h2>
        <div class="row px-xl-5 pb-3">


            <c:forEach var="brand" items="${List}">
                <div class="col-lg-3 col-md-4 col-sm-6 pb-1">
                    <a class="text-decoration-none" href="<c:url value="/filter">
                           <c:param name="txtBrandName" value="${brand.brandName}"/></c:url>">
                           <div class="cat-item d-flex align-items-center mb-4 bg-white">
                               <div class="overflow-hidden" style="width: 100px; height: 100px;">
                                   <img class="img-fluid rounded" src="img/${brand.picture}" alt="">
                           </div>
                           <div class="flex-fill pl-3">

                               <h6>${brand.brandName}</h6>
                               <small class="text-body">${brand.quantity} Products</small>

                           </div>
                       </div>
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>
    <!-- Categories End -->


    <!-- Products Start -->
    <div class="container-fluid pt-5 pb-3 bg-white">
        <h2 class="section-title position-relative text-uppercase mx-xl-5 mb-4"><span class="bg-white pr-3">Best Sale Products</span></h2>
        <div class="row px-xl-5">
            <c:forEach begin="0" end="3" items="${ListSale}" var="sale">
                <div class="col-lg-3 col-md-4 col-sm-6 pb-1">
                    <div class="product-item bg-white mb-4">
                        <div class="product-img position-relative overflow-hidden ">
                            <img class="img-fluid w-100" src="img/${sale.picture}" alt="">
                            <div class="product-action">
                                <a class="btn btn-outline-dark btn-square" href="<c:url value="/SupportDetail">
                                   <c:param name="ProductID" value="${sale.productID}"/>
                                   <c:param name="type" value="buyNow"/>
                               </c:url>"><i class="fa fa-shopping-cart"></i></a>
                                <a class="btn btn-outline-dark btn-square" href="<c:url value="/SupportDetail">
                                   <c:param name="ProductID" value="${sale.productID}"/>
                               </c:url>"><i class="fa fa-search"></i></a>
                            </div>
                        </div>
                        <div class="text-center py-4 ">
                            <a class="h6 text-decoration-none text-truncate" href="<c:url value="/SupportDetail">
                                   <c:param name="ProductID" value="${sale.productID}"/>
                               </c:url>">${sale.productName}</a>
                            <div class="d-flex align-items-center justify-content-center mt-2 ">

                                <h5>$<fmt:formatNumber maxFractionDigits="0" value="${sale.price *(1-sale.discount)}"/></h5><h6 class="text-muted ml-2"><del>$<fmt:formatNumber maxFractionDigits="2" value="${sale.price}"/></del></h6>
                            </div>
                            <div class="d-flex align-items-center justify-content-center mb-1">
                                <div class="rate-star-class">
                                    <c:forEach begin="1" end="${sale.rate}">
                                        <small class="fa fa-star fasize checked"></small>
                                    </c:forEach>
                                    <c:forEach begin="1" end="${5-sale.rate}">
                                        <small class="fa fa-star fasize "></small>
                                    </c:forEach>

                                </div>
                                <small>(${sale.amountRate})</small>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>
    </div>
    <!-- Products End -->





    <!-- Products Start -->
    <div class="container-fluid pt-5 pb-3">
        <h2 class="section-title position-relative text-uppercase mx-xl-5 mb-4"><span class="bg-white pr-3">Recent Products</span></h2>
        <div class="row px-xl-5">
            <c:forEach begin="0" end="3" items="${ListFeatured}" var="product">
                <div class="col-lg-3 col-md-4 col-sm-6 pb-1">
                    <div class="product-item bg-light mb-4">
                        <div class="product-img position-relative overflow-hidden">
                            <img class="img-fluid w-100" src="img/${product.picture}" alt="">
                            <div class="product-action">
                                <a class="btn btn-outline-dark btn-square" href="<c:url value="/SupportDetail">
                                   <c:param name="ProductID" value="${product.productID}"/>
                                   <c:param name="type" value="buyNow"/></c:url>"><i class="fa fa-shopping-cart"></i></a>
                                <a class="btn btn-outline-dark btn-square" href="<c:url value="/SupportDetail">
                                   <c:param name="ProductID" value="${product.productID}"/>
                               </c:url>"><i class="fa fa-search"></i></a>
                            </div>
                        </div>
                        <div class="text-center py-4">
                            <a class="h6 text-decoration-none text-truncate" href="<c:url value="/SupportDetail">
                                   <c:param name="ProductID" value="${product.productID}"/>
                               </c:url>">${product.productName}</a>
                            <div class="d-flex align-items-center justify-content-center mt-2">
                                <c:if test="${product.discount != 0}">
                                    <h5>$<fmt:formatNumber maxFractionDigits="0" value="${product.price *(1-product.discount)}"/></h5><h6 class="text-muted ml-2"><del>$<fmt:formatNumber maxFractionDigits="2" value="${product.price}"/></del></h6>
                                </c:if>
                                <c:if test="${product.discount == 0}">
                                    <h5>$<fmt:formatNumber maxFractionDigits="0" value="${product.price }"/></h5>
                                </c:if>
                            </div   
                            <div class="d-flex align-items-center justify-content-center mb-1">
                                <div class="rate-star-class">
                                    <c:forEach begin="1" end="${product.rate}">
                                        <small class="fa fa-star fasize checked"></small>
                                    </c:forEach>
                                    <c:forEach begin="1" end="${5-product.rate}">
                                        <small class="fa fa-star fasize "></small>
                                    </c:forEach>

                                </div>
                                <small>(${product.amountRate})</small>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>




    <!-- Products Start -->
    <div class="container-fluid pt-5 pb-3">       
        <h2 class="section-title position-relative text-uppercase mx-xl-5 mb-4"><span class="bg-white pr-3">PRODUCTS</span></h2>
        <div class="row px-xl-5">
            <c:forEach begin="0" end="3" items="${ListBigAmount}" var="product">
                <div class="col-lg-3 col-md-4 col-sm-6 pb-1">
                    <div class="product-item bg-light mb-4">
                        <div class="product-img position-relative overflow-hidden">
                            <img class="img-fluid w-100" src="img/${product.picture}" alt="">
                            <div class="product-action">
                                <a class="btn btn-outline-dark btn-square" href="<c:url value="/SupportDetail">
                                   <c:param name="ProductID" value="${product.productID}"/>
                                   <c:param name="type" value="buyNow"/></c:url>"><i class="fa fa-shopping-cart"></i></a>
                                <a class="btn btn-outline-dark btn-square" href="<c:url value="/SupportDetail">
                                   <c:param name="ProductID" value="${product.productID}"/>
                               </c:url>"><i class="fa fa-search"></i></a>
                            </div>
                        </div>
                        <div class="text-center py-4">
                            <a class="h6 text-decoration-none text-truncate" href="<c:url value="/SupportDetail">
                                   <c:param name="ProductID" value="${product.productID}"/>
                               </c:url>">${product.productName}</a>
                            <div class="d-flex align-items-center justify-content-center mt-2">
                                <c:if test="${product.discount != 0}">
                                    <h5>$<fmt:formatNumber maxFractionDigits="0" value="${product.price *(1-product.discount)}"/></h5><h6 class="text-muted ml-2"><del>$<fmt:formatNumber maxFractionDigits="2" value="${product.price}"/></del></h6>
                                </c:if>
                                <c:if test="${product.discount == 0}">
                                    <h5>$<fmt:formatNumber maxFractionDigits="0" value="${product.price }"/></h5>
                                </c:if>
                            </div>
                            <div class="d-flex align-items-center justify-content-center mb-1">
                                <div class="rate-star-class">
                                    <c:forEach begin="1" end="${product.rate}">
                                        <small class="fa fa-star fasize checked"></small>
                                    </c:forEach>
                                    <c:forEach begin="1" end="${5-product.rate}">
                                        <small class="fa fa-star fasize "></small>
                                    </c:forEach>

                                </div>
                                <small>(${product.amountRate})</small>
                            </div>


                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>
        <div class="d-flex justify-content-center ">
            <button class="btn btn-secondary rounded border p-3 pl-5 pr-5"><a href="<c:url value="/shop"/>" style="text-decoration: none; color: black"/>Show All</button>
        </div>
    </div>


    <!-- Products End -->


    <!-- Vendor Start -->
    <div class="container-fluid py-5">
        <div class="row px-xl-5">
            <div class="col">
                <div class="owl-carousel vendor-carousel">
                    <div class="bg-light p-4">
                        <img src="img/Apple.png" alt="">
                    </div>
                    <div class="bg-light p-4" >
                        <img src="img/Oppo-Logo.png" alt="" style="border-radius: 5px">
                    </div>
                    <div class="bg-light p-4">
                        <img src="img/ss.jpg" alt="">
                    </div>
                    <div class="bg-light p-4">
                        <img src="img/Xiaomi.png" alt="">
                    </div>
                    <div class="bg-light p-4">
                        <img src="img/vivo.png" alt="" style="border-radius: 5px">
                    </div>
                    <div class="bg-light p-4">
                        <img src="img/mobell.png" alt="">
                    </div>
                    <div class="bg-light p-4">
                        <img src="img/realme.png" alt="" style="border-radius: 5px">
                    </div>
                    <div class="bg-light p-4">
                        <img src="img/itel.png" alt="">
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<!-- Vendor End -->
<%@include file="template/footer.jsp" %>
