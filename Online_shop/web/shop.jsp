<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="template/header.jsp" %>
<c:if test="${empty check}">
    <c:redirect url="/shop">
        <c:param name="choice" value="shop"/>
    </c:redirect>
</c:if>
<!-- Shop Start -->
<c:if test="${not empty OrderSuccessful}">
    <div classs="container p-5">
	<div class="row no-gutters">
		<div class="col-lg-3 col-md-12 ml-auto">
			<div class="alert alert-success fade show" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    	<span aria-hidden="True">&times;</span>
			  	</button>
			 	<h4 class="alert-heading">Order successful!</h4>
			  	<p>Thanks for your support.</p>
			</div>
		</div>
	</div>
</div>
</c:if>
<div class="container-fluid">
    <div class="row px-xl-5">
        <!-- Shop Sidebar Start -->
        <div class="col-lg-3 col-md-4">
            <form action="<c:url value="/filter"/>" method="post">
                <!-- Price Start -->
                <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-white pr-3">Price</span></h5>
                <div class="bg-light p-4 mb-30">
                    <c:choose>
                        <c:when test="${PriceFilter == 0}">
                            <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                <input type="checkbox" class="custom-control-input" checked id="price-all" name="txtCheckedFilterPrice" value="${PriceFilter}">
                                <label class="custom-control-label" for="price-all">All Price</label>
                                <span class="badge border font-weight-normal">${totalListProduct}</span>
                            </div>
                        </c:when>
                        <c:when test="${PriceFilter != 0}">
                            <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                <input type="submit" class="custom-control-input" id="price-all" name="txtFilterPrice" value="0">
                                <label class="custom-control-label" for="price-all">All Price</label>
                                <span class="badge border font-weight-normal">${totalListProduct}</span>
                            </div>
                        </c:when>
                    </c:choose>
                    <c:set var="i" value="${1}"/>
                    <c:forEach begin="${i}" end="5">
                        <c:choose>
                            <c:when test="${i == PriceFilter}">
                                <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                    <input type="checkbox" class="custom-control-input" checked id="price-${i}" name="txtCheckedFilterPrice" value="${PriceFilter}">
                                    <label class="custom-control-label" for="price-${i}">
                                        $<fmt:formatNumber maxFractionDigits="0" value="${(i-1)*10000000}"/>
                                        - $<fmt:formatNumber maxFractionDigits="0" value="${i*10000000}"/>
                                    </label>
                                    <span class="badge border font-weight-normal">150</span>
                                </div>
                            </c:when>
                            <c:when test="${i != PriceFilter}">
                                <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                    <input type="submit" class="custom-control-input" id="price-${i}" name="txtFilterPrice" value="${i}">
                                    <label class="custom-control-label" for="price-${i}">
                                        $<fmt:formatNumber maxFractionDigits="0" value="${(i-1)*10000000}"/>
                                        - $<fmt:formatNumber maxFractionDigits="0" value="${i*10000000}"/>
                                    </label>
                                    <span class="badge border font-weight-normal">150</span>
                                </div>
                            </c:when>
                        </c:choose>
                        <c:set var="i" value="${i+1}"/>
                    </c:forEach>
                </div>
                <!-- Price End -->

                <!-- Color Start -->
                <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-white pr-3">Color</span></h5>
                <div class="bg-light p-4 mb-30">
                    <c:set var="total" value="${0}"/>
                    <c:forEach var="c" items="${listColor}">
                        <c:set var="total" value="${total + c.count}"/>
                    </c:forEach>
                    <c:set var="i" value="${0}"/>
                    <c:forEach var="c" items="${listColor}">
                        <c:set var="i" value="${i + 1}"/>
                        <c:choose>
                            <c:when test="${i != listColor.size()}">
                                <c:if test="${i == 1}">
                                    <c:choose>
                                        <c:when test="${ColorFilter == 'All'}">
                                            <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                                <input type="checkbox" class="custom-control-input" checked id="color-all" name="txtCheckedFilterColor" value="${ColorFilter}">
                                                <label class="custom-control-label" for="color-all">All Color</label>
                                                <span class="badge border font-weight-normal">${total}</span>
                                            </div>
                                        </c:when>
                                        <c:when test="${ColorFilter != 'All'}">
                                            <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                                <input type="submit" class="custom-control-input" id="color-all" name="txtFilterColor" value="All">
                                                <label class="custom-control-label" for="color-all">All Color</label>
                                                <span class="badge border font-weight-normal">${total}</span>
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </c:if>
                                <c:choose>
                                    <c:when test="${c.color == ColorFilter}">
                                        <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                            <input type="checkbox" class="custom-control-input" checked id="color-${i}" name="txtCheckedFilterColor" value="${ColorFilter}">
                                            <label class="custom-control-label" for="color-${i}">${c.color}</label>
                                            <span class="badge border font-weight-normal">${c.count}</span>
                                        </div>
                                    </c:when>
                                    <c:when test="${c.color != ColorFilter}">
                                        <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                            <input type="submit" class="custom-control-input" id="color-${i}" name="txtFilterColor" value="${c.color}">
                                            <label class="custom-control-label" for="color-${i}">${c.color}</label>
                                            <span class="badge border font-weight-normal">${c.count}</span>
                                        </div>
                                    </c:when>
                                </c:choose>
                            </c:when>
                            <c:when test="${i == listColor.size()}">
                                <c:choose>
                                    <c:when test="${c.color == ColorFilter}">
                                        <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between">
                                            <input type="checkbox" class="custom-control-input" id="color-${i}" checked name="txtCheckedFilterColor" value="${ColorFilter}">
                                            <label class="custom-control-label" for="color-${i}">${c.color}</label>
                                            <span class="badge border font-weight-normal">${c.count}</span>
                                        </div>
                                    </c:when>
                                    <c:when test="${c.color != ColorFilter}">
                                        <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between">
                                            <input type="submit" class="custom-control-input" id="color-${i}" name="txtFilterColor" value="${c.color}">
                                            <label class="custom-control-label" for="color-${i}">${c.color}</label>
                                            <span class="badge border font-weight-normal">${c.count}</span>
                                        </div>
                                    </c:when>
                                </c:choose>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </div>
                <!-- Color End -->


                <!-- Storage Start -->
                <h5 class="section-title position-relative text-uppercase mb-3"><span class="bg-white pr-3">Storage</span></h5>
                <div class="bg-light p-4 mb-30">
                    <c:set var="total" value="${0}"/>
                    <c:forEach var="s" items="${listProductStorage}">
                        <c:set var="total" value="${total + s.count}"/>
                    </c:forEach>
                    <c:set var="i" value="${0}"/>
                    <c:forEach var="s" items="${listProductStorage}">
                        <c:set var="i" value="${i + 1}"/>
                        <c:choose>
                            <c:when test="${i != listProductStorage.size()}">
                                <c:if test="${i == 1}">
                                    <c:choose>
                                        <c:when test="${StorageFilter != 0}">
                                            <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                                <input type="submit" class="custom-control-input" id="storage-all" name="txtFilterStorage" value="0">
                                                <label class="custom-control-label" for="storage-all">All Storage</label>
                                                <span class="badge border font-weight-normal">${total}</span>
                                            </div>
                                        </c:when>
                                        <c:when test="${StorageFilter == 0}">
                                            <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                                <input type="checkbox" class="custom-control-input" checked id="storage-all" name="txtCheckedFilterStorage" value="${StorageFilter}">
                                                <label class="custom-control-label" for="storage-all">All Storage</label>
                                                <span class="badge border font-weight-normal">${total}</span>
                                            </div>
                                        </c:when>
                                    </c:choose>    
                                </c:if>
                                <c:choose>
                                    <c:when test="${s.productStorage != StorageFilter}">
                                        <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                            <input type="submit" class="custom-control-input"  id="storage-${i}" name="txtFilterStorage" value="${s.productStorage}">
                                            <c:if test="${s.productStorage == 1000}">
                                                <label class="custom-control-label" for="storage-${i}">1 TB</label>
                                            </c:if>
                                            <c:if test="${s.productStorage != 1000}">
                                                <label class="custom-control-label" for="storage-${i}">${s.productStorage} GB</label>
                                            </c:if>
                                            <span class="badge border font-weight-normal">${s.count}</span>
                                        </div>
                                    </c:when>
                                    <c:when test="${s.productStorage == StorageFilter}">
                                        <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                            <input type="checkbox" class="custom-control-input"  id="storage-${i}" checked name="txtCheckedFilterStorage" value="${StorageFilter}">
                                            <c:if test="${s.productStorage == 1000}">
                                                <label class="custom-control-label" for="storage-${i}">1 TB</label>
                                            </c:if>
                                            <c:if test="${s.productStorage != 1000}">
                                                <label class="custom-control-label" for="storage-${i}">${s.productStorage} GB</label>
                                            </c:if>
                                            <span class="badge border font-weight-normal">${s.count}</span>
                                        </div>
                                    </c:when>
                                </c:choose>
                            </c:when>
                            <c:when test="${i == listProductStorage.size()}">
                                <c:choose>
                                    <c:when test="${s.productStorage != StorageFilter}">
                                        <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between">
                                            <input type="submit" class="custom-control-input"  id="storage-${i}" name="txtFilterStorage" value="${s.productStorage}">
                                            <c:if test="${s.productStorage == 1000}">
                                                <label class="custom-control-label" for="storage-${i}">1 TB</label>
                                            </c:if>
                                            <c:if test="${s.productStorage != 1000}">
                                                <label class="custom-control-label" for="storage-${i}">${s.productStorage} GB</label>
                                            </c:if>
                                            <span class="badge border font-weight-normal">${s.count}</span>
                                        </div>
                                    </c:when>
                                    <c:when test="${s.productStorage == StorageFilter}">
                                        <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between">
                                            <input type="checkbox" class="custom-control-input"  id="storage-${i}" checked name="txtCheckedFilterStorage" value="${StorageFilter}">
                                            <c:if test="${s.productStorage == 1000}">
                                                <label class="custom-control-label" for="storage-${i}">1 TB</label>
                                            </c:if>
                                            <c:if test="${s.productStorage != 1000}">
                                                <label class="custom-control-label" for="storage-${i}">${s.productStorage} GB</label>
                                            </c:if>
                                            <span class="badge border font-weight-normal">${s.count}</span>
                                        </div>
                                    </c:when>
                                </c:choose>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </div>
            </form>
            <!-- Size End -->
        </div> 


        <!-- Shop Sidebar End -->


        <!-- Shop Product Start -->
        <div class="col-lg-9 col-md-8">
            <div class="row pb-3">
                <div class="col-12 pb-1">
                    <div class="d-flex align-items-center justify-content-between mb-4">
                        <div>
                        </div>
                        <div class="ml-2">
                            <div class="btn-group">
                                <button type="button" class="btn btn-sm btn-light dropdown-toggle" data-toggle="dropdown">Sorting</button>
                                <c:choose>
                                    <c:when test="${empty filter}">
                                        <div class="dropdown-menu dropdown-menu-right">
                                            <a class="dropdown-item" href="<c:url value="/shop">
                                                   <c:param name="choice" value="sort"/>
                                                   <c:param name="sort" value="latest"/></c:url>">Latest</a>
                                            <a class="dropdown-item" href="<c:url value="/shop">
                                                   <c:param name="choice" value="sort"/>
                                                   <c:param name="sort" value="popularity"/></c:url>">Popularity</a>
                                            <a class="dropdown-item" href="<c:url value="/shop">
                                                   <c:param name="choice" value="sort"/>
                                                   <c:param name="sort" value="bestRating"/></c:url>">Best Rating</a>
                                            </div>
                                    </c:when>
                                    <c:when test="${not empty filter}">
                                        <div class="dropdown-menu dropdown-menu-right">
                                            <a class="dropdown-item" href="<c:url value="/filter">
                                                   <c:param name="sort" value="desc"/></c:url>">Price Desc</a>
                                            <a class="dropdown-item" href="<c:url value="/filter">
                                                   <c:param name="sort" value="asc"/></c:url>">Price Asc</a>
                                            </div>
                                    </c:when>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
                <!--FILTER-->
                <c:if test="${not empty filter}">
                    <c:forEach var="ListProduct" items="${listFilterProduct}" >
                        <c:set var="p" value="${ListProduct.product}"></c:set>
                        <c:set var="c" value="${ListProduct.comments}"></c:set>
                            <div id="content" class="col-lg-4 col-md-6 col-sm-6 pb-1">
                                <div >
                                    <div  class="product-item bg-light mb-4">
                                        <div class="product-img position-relative overflow-hidden">
                                            <img class="img-fluid w-100" src="img/${p.picture}" alt="">
                                        <div class="product-action">
                                            <a class="btn btn-outline-dark btn-square" href="<c:url value="/cart">
                                                   <c:param name="productDetailID" value="${p.productID}"/>
                                                   <c:param name="type" value="buyNow"/>
                                               </c:url>"><i class="fa fa-shopping-cart"></i></a>
                                            <a class="btn btn-outline-dark btn-square" href="<c:url value="/detail">
                                                   <c:param name="productID" value="${p.productID}" />
                                            </c:url>"><i class="fa fa-search"></i></a>
                                        </div>
                                    </div>
                                    <div class="text-center py-4">
                                        <a class="h6 text-decoration-none text-truncate" href="<c:url value="/detail">
                                                   <c:param name="productID" value="${p.productID}" />
                                            </c:url>">${p.productName}</a>
                                        <div class="d-flex align-items-center justify-content-center mt-2">
                                            <h5>$<fmt:formatNumber maxFractionDigits="0" value="${p.price-(0.15*p.price)}"/></h5>
                                            <h6 class="text-muted ml-2"><del>$<fmt:formatNumber maxFractionDigits="0" value="${p.price}"/></del></h6>
                                        </div>
                                        <div class="d-flex align-items-center justify-content-center mb-1">
                                            <div class="rate-star-class">
                                                <c:forEach begin="1" end="${c.rate}">
                                                    <small class="fa fa-star fasize checked"></small>
                                                </c:forEach>

                                                <c:forEach begin="1" end="${5-c.rate}">
                                                    <small class="fa fa-star fasize "></small>
                                                </c:forEach>
                                            </div>
                                            <small>(${ListProduct.totalComments})</small>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="col-12">
                        <nav>
                            <ul class="pagination justify-content-center">
                                <c:if test="${currentPage>1}">
                                    <c:url value="/filter" var="paginationPrevious">
                                        <c:param name="currentPage" value="${currentPage-1}" />
                                        <c:param name="txtFilterPrice" value="${PriceFilter}"/>
                                        <c:param name="txtFilterColor" value="${ColorFilter}"/>
                                        <c:param name="txtFilterStorage" value="${StorageFilter}"/>
                                        <c:param name="txtBrandName" value="${BrandFilter}"/>
                                        <c:param name="sort" value="${SortingFilter}"/>
                                    </c:url>
                                    <li class="page-item"><a class="page-link" href="${paginationPrevious}">Previous</span></a></li>
                                    </c:if>
                                    <c:forEach begin="1" end="${numberOfPage}" step="1" var="stepValue">
                                        <c:choose>
                                            <c:when test="${stepValue == currentPage}">
                                            <li class="page-item active"><a class="page-link" href="#">${stepValue}</a></li>
                                            </c:when>
                                            <c:otherwise>
                                                <c:url value="/filter" var="pagination">
                                                    <c:param name="currentPage" value="${stepValue}" />
                                                    <c:param name="txtFilterPrice" value="${PriceFilter}"/>
                                                    <c:param name="txtFilterColor" value="${ColorFilter}"/>
                                                    <c:param name="txtFilterStorage" value="${StorageFilter}"/>
                                                    <c:param name="txtBrandName" value="${BrandFilter}"/>
                                                    <c:param name="sort" value="${SortingFilter}"/>
                                                </c:url>
                                            <li class="page-item"><a class="page-link" href="${pagination}">${stepValue}</a></li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                    <c:if test="${currentPage<numberOfPage}">
                                        <c:url value="/filter" var="paginationNext">
                                            <c:param name="currentPage" value="${currentPage+1}" />
                                            <c:param name="txtFilterPrice" value="${PriceFilter}"/>
                                            <c:param name="txtFilterColor" value="${ColorFilter}"/>
                                            <c:param name="txtFilterStorage" value="${StorageFilter}"/>
                                            <c:param name="txtBrandName" value="${BrandFilter}"/>
                                            <c:param name="sort" value="${SortingFilter}"/>
                                        </c:url>
                                    <li class="page-item"><a class="page-link" href="${paginationNext}">Next</a></li>
                                    </c:if>
                            </ul>
                        </nav>
                    </div>
                </c:if>
                <!--EMPTY FILTER-->
                <c:if test="${empty filter}">
                    <!--EMPTY SEARCHING-->
                    <c:if test="${empty searching}">
                        <!--EMPTY SORTING-->
                        <c:if test="${empty sortSession}">
                            <c:forEach var="ListProduct" items="${shopListProduct}" >
                                <c:set var="p" value="${ListProduct.product}"></c:set>
                                <c:set var="c" value="${ListProduct.comments}"></c:set>
                                    <div id="content" class="col-lg-4 col-md-6 col-sm-6 pb-1">
                                        <div >
                                            <div  class="product-item bg-light mb-4">
                                                <div class="product-img position-relative overflow-hidden">
                                                    <img class="img-fluid w-100" src="img/${p.picture}" alt="">
                                                <div class="product-action">
                                                    <a class="btn btn-outline-dark btn-square" href="<c:url value="/cart">
                                                   <c:param name="productDetailID" value="${p.productID}"/>
                                                   <c:param name="type" value="buyNow"/>
                                               </c:url>"><i class="fa fa-shopping-cart"></i></a>
                                                    <a class="btn btn-outline-dark btn-square" href="<c:url value="/detail">
                                                   <c:param name="productID" value="${p.productID}" />
                                            </c:url>"><i class="fa fa-search"></i></a>
                                                </div>
                                            </div>
                                            <div class="text-center py-4">
                                                <a class="h6 text-decoration-none text-truncate" href="<c:url value="/detail">
                                                   <c:param name="productID" value="${p.productID}" />
                                            </c:url>">${p.productName}</a>
                                                <div class="d-flex align-items-center justify-content-center mt-2">
                                                    <h5>$<fmt:formatNumber maxFractionDigits="0" value="${p.price-(0.15*p.price)}"/></h5>
                                                    <h6 class="text-muted ml-2"><del>$<fmt:formatNumber maxFractionDigits="0" value="${p.price}"/></del></h6>
                                                </div>
                                                <div class="d-flex align-items-center justify-content-center mb-1">
                                                    <div class="rate-star-class">
                                                        <c:forEach begin="1" end="${c.rate}">
                                                            <small class="fa fa-star fasize checked"></small>
                                                        </c:forEach>

                                                        <c:forEach begin="1" end="${5-c.rate}">
                                                            <small class="fa fa-star fasize "></small>
                                                        </c:forEach>
                                                    </div>
                                                    <small>(${ListProduct.totalComments})</small>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            <div class="col-12">
                                <nav>
                                    <ul class="pagination justify-content-center">
                                        <c:if test="${currentPage>1}">
                                            <c:url value="/shop" var="paginationPrevious">
                                                <c:param name="currentPage" value="${currentPage-1}" />
                                                <c:param name="choice" value="shop"/>
                                            </c:url>
                                            <li class="page-item"><a class="page-link" href="${paginationPrevious}">Previous</span></a></li>
                                            </c:if>
                                            <c:forEach begin="1" end="${numberOfPage}" step="1" var="stepValue">
                                                <c:choose>
                                                    <c:when test="${stepValue == currentPage}">
                                                    <li class="page-item active"><a class="page-link" href="#">${stepValue}</a></li>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:url value="/shop" var="pagination">
                                                            <c:param name="currentPage" value="${stepValue}" />
                                                            <c:param name="choice" value="shop"/>
                                                        </c:url>
                                                    <li class="page-item"><a class="page-link" href="${pagination}">${stepValue}</a></li>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                            <c:if test="${currentPage<numberOfPage}">
                                                <c:url value="/shop" var="paginationNext">
                                                    <c:param name="currentPage" value="${currentPage+1}" />
                                                    <c:param name="choice" value="shop"/>
                                                </c:url>
                                            <li class="page-item"><a class="page-link" href="${paginationNext}">Next</a></li>
                                            </c:if>
                                    </ul>
                                </nav>
                            </div>
                        </c:if>
                        <!--SORTING-->
                        <c:if test="${not empty sortSession}">
                            <c:forEach var="ListProduct" items="${shopListProduct}" >
                                <c:set var="p" value="${ListProduct.product}"></c:set>
                                <c:set var="c" value="${ListProduct.comments}"></c:set>
                                    <div id="content" class="col-lg-4 col-md-6 col-sm-6 pb-1">
                                        <div >
                                            <div  class="product-item bg-light mb-4">
                                                <div class="product-img position-relative overflow-hidden">
                                                    <img class="img-fluid w-100" src="img/${p.picture}" alt="">
                                                <div class="product-action">
                                                    <a class="btn btn-outline-dark btn-square" href="<c:url value="/cart">
                                                   <c:param name="productDetailID" value="${p.productID}"/>
                                                   <c:param name="type" value="buyNow"/>
                                               </c:url>"><i class="fa fa-shopping-cart"></i></a>
                                                    <a class="btn btn-outline-dark btn-square" href="<c:url value="/detail">
                                                   <c:param name="productID" value="${p.productID}" />
                                            </c:url>"><i class="fa fa-search"></i></a>
                                                </div>
                                            </div>
                                            <div class="text-center py-4">
                                                <a class="h6 text-decoration-none text-truncate" href="">${p.productName}</a>
                                                <div class="d-flex align-items-center justify-content-center mt-2">
                                                    <h5>$<fmt:formatNumber maxFractionDigits="0" value="${p.price-(0.15*p.price)}"/></h5>
                                                    <h6 class="text-muted ml-2"><del>$<fmt:formatNumber maxFractionDigits="0" value="${p.price}"/></del></h6>
                                                </div>
                                                <div class="d-flex align-items-center justify-content-center mb-1">
                                                    <div class="rate-star-class">
                                                        <c:forEach begin="1" end="${c.rate}">
                                                            <small class="fa fa-star fasize checked"></small>
                                                        </c:forEach>

                                                        <c:forEach begin="1" end="${5-c.rate}">
                                                            <small class="fa fa-star fasize "></small>
                                                        </c:forEach>
                                                    </div>
                                                    <small>(${ListProduct.totalComments})</small>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            <div class="col-12">
                                <nav>
                                    <ul class="pagination justify-content-center">
                                        <c:if test="${currentPage>1}">
                                            <c:url value="/shop" var="paginationPrevious">
                                                <c:param name="currentPage" value="${currentPage-1}" />
                                                <c:param name="choice" value="sort"/>
                                                <c:param name="sort" value="${sortCondition}"/>
                                            </c:url>
                                            <li class="page-item"><a class="page-link" href="${paginationPrevious}">Previous</span></a></li>
                                            </c:if>
                                            <c:forEach begin="1" end="${numberOfPage}" step="1" var="stepValue">
                                                <c:choose>
                                                    <c:when test="${stepValue == currentPage}">
                                                    <li class="page-item active"><a class="page-link" href="#">${stepValue}</a></li>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:url value="/shop" var="pagination">
                                                            <c:param name="currentPage" value="${stepValue}" />
                                                            <c:param name="choice" value="sort"/>
                                                            <c:param name="sort" value="${sortCondition}"/>
                                                        </c:url>
                                                    <li class="page-item"><a class="page-link" href="${pagination}">${stepValue}</a></li>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                            <c:if test="${currentPage<numberOfPage}">
                                                <c:url value="/shop" var="paginationNext">
                                                    <c:param name="currentPage" value="${currentPage+1}" />
                                                    <c:param name="choice" value="sort"/>
                                                    <c:param name="sort" value="${sortCondition}"/>
                                                </c:url>
                                            <li class="page-item"><a class="page-link" href="${paginationNext}">Next</a></li>
                                            </c:if>
                                    </ul>
                                </nav>
                            </div>
                        </c:if>
                    </c:if>
                    <!--SEARCHING-->
                    <c:if test="${not empty searching}">
                        <c:forEach var="ListProduct" items="${searchingListProduct}" >
                            <c:set var="p" value="${ListProduct.product}"></c:set>
                            <c:set var="c" value="${ListProduct.comments}"></c:set>
                                <div id="content" class="col-lg-4 col-md-6 col-sm-6 pb-1">
                                    <div>
                                        <div  class="product-item bg-light mb-4">
                                            <div class="product-img position-relative overflow-hidden">
                                                <img class="img-fluid w-100" src="img/${p.picture}" alt="">
                                            <div class="product-action">
                                                <a class="btn btn-outline-dark btn-square" href="<c:url value="/cart">
                                                   <c:param name="productDetailID" value="${p.productID}"/>
                                                   <c:param name="type" value="buyNow"/>
                                               </c:url>"><i class="fa fa-shopping-cart"></i></a>
                                                <a class="btn btn-outline-dark btn-square" href="<c:url value="/detail">
                                                   <c:param name="productID" value="${p.productID}" />
                                            </c:url>"><i class="fa fa-search"></i></a>
                                            </div>
                                        </div>
                                        <div class="text-center py-4">
                                            <a class="h6 text-decoration-none text-truncate" href="">${p.productName}</a>
                                            <div class="d-flex align-items-center justify-content-center mt-2">
                                                <h5>$<fmt:formatNumber maxFractionDigits="0" value="${p.price-(0.15*p.price)}"/></h5>
                                                <h6 class="text-muted ml-2"><del>$<fmt:formatNumber maxFractionDigits="0" value="${p.price}"/></del></h6>
                                            </div>
                                            <div class="d-flex align-items-center justify-content-center mb-1">
                                                <div class="rate-star-class">
                                                    <c:forEach begin="1" end="${c.rate}">
                                                        <small class="fa fa-star fasize checked"></small>
                                                    </c:forEach>

                                                    <c:forEach begin="1" end="${5-c.rate}">
                                                        <small class="fa fa-star fasize "></small>
                                                    </c:forEach>
                                                </div>
                                                <small>(${ListProduct.totalComments})</small>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        <div class="col-12">
                            <nav>
                                <ul class="pagination justify-content-center">
                                    <c:if test="${currentPage>1}">
                                        <c:url value="/searching" var="paginationPrevious">
                                            <c:param name="currentPage" value="${currentPage-1}" />
                                            <c:param name="txt" value="${txtValue}"/>
                                        </c:url>
                                        <li class="page-item"><a class="page-link" href="${paginationPrevious}">Previous</span></a></li>
                                        </c:if>
                                        <c:forEach begin="1" end="${numberOfPage}" step="1" var="stepValue">
                                            <c:choose>
                                                <c:when test="${stepValue == currentPage}">
                                                <li class="page-item active"><a class="page-link" href="#">${stepValue}</a></li>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:url value="/searching" var="pagination">
                                                        <c:param name="currentPage" value="${stepValue}" />
                                                        <c:param name="txt" value="${txtValue}"/>
                                                    </c:url>
                                                <li class="page-item"><a class="page-link" href="${pagination}">${stepValue}</a></li>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                        <c:if test="${currentPage<numberOfPage}">
                                            <c:url value="/searching" var="paginationNext">
                                                <c:param name="currentPage" value="${currentPage+1}" />
                                                <c:param name="txt" value="${txtValue}"/>
                                            </c:url>
                                        <li class="page-item"><a class="page-link" href="${paginationNext}">Next</a></li>
                                        </c:if>
                                </ul>
                            </nav>
                        </div>
                    </c:if>
                </c:if>
            </div>
        </div>
        <!-- Shop Product End -->
    </div>

</div>
<!-- Shop End -->
<%@include file="template/footer.jsp" %>