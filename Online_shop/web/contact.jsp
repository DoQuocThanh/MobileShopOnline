<%-- 
    Document   : contact
    Created on : Jan 28, 2023, 8:09:19 AM
    Author     : LEGION
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="template/header.jsp" %>
<c:if test="${empty check}">
    <c:redirect url = "/contact" />
</c:if>
<!-- Breadcrumb Start -->
    <div class="container-fluid">
        <div class="row px-xl-5">
            <div class="col-12">
                <nav class="breadcrumb bg-light mb-30">
                    <a class="breadcrumb-item text-dark" href="<c:url value="/home"/>">Home</a>
                    <span class="breadcrumb-item active">Contact</span>
                </nav>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->


    <!-- Contact Start -->
    <div class="container-fluid">
        <h2 class="section-title position-relative text-uppercase mx-xl-5 mb-4"><span class="bg-white pr-3">Contact Us</span></h2>
        <div class="row px-xl-5">
            <div class="col-lg-7 mb-5">
                <div class="contact-form bg-light p-30">
                    <div id="success"></div>
                    <form action="/contact" method="POST">
                    <div class="control-group">
                        <input type="text" class="form-control rounded" name="name" placeholder="Your Name"
                               required="required" data-validation-required-message="Please enter your name" />
                        <p class="help-block text-danger"></p>
                    </div>
                    <div class="control-group">
                        <input type="email" class="form-control rounded" name="email" placeholder="Your Email"
                               required="required" data-validation-required-message="Please enter your email" />
                        <p class="help-block text-danger"></p>
                    </div>
                    <div class="control-group">
                        <textarea class="form-control " rows="8" name="message" placeholder="Message"
                                  required="required"
                                  data-validation-required-message="Please enter your message"></textarea>
                        <p class="help-block text-danger"></p>
                    </div>
                    <div>
                        <button class="btn btn-dark py-2 px-4 rounded" type="submit">Send
                            Message</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="col-lg-5 mb-5">
            <div class="bg-light p-30 mb-30 ">
                <iframe style="width: 100%; height: 250px;"
                        src="https://maps.google.com/maps?width=600&amp;height=400&amp;hl=en&amp;q=66 thái hà&amp;t=&amp;z=14&amp;ie=UTF8&amp;iwloc=B&amp;output=embed"
                        frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>
            </div>
            <div class="bg-light p-30 mb-3">
                <p class="mb-2"><i class="fa fa-map-marker-alt text-primary mr-3"></i>66 Thai Ha, Dong Da, Ha Noi</p>
                <p class="mb-2"><i class="fa fa-envelope text-primary mr-3"></i>NgocLinhMobile@fptshop.com</p>
                <p class="mb-2"><i class="fa fa-phone-alt text-primary mr-3"></i>+012 6868 868</p>
            </div>
        </div>
    </div>
</div>
    <!-- Contact End -->
<%@include file="template/footer.jsp" %>
