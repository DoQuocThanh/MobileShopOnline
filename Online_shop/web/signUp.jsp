<%-- 
    Document   : signUp
    Created on : Jan 30, 2023, 8:16:53 PM
    Author     : LEGION
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Section: Design Block -->
<%@include file="template/header.jsp" %>
<c:if test="${not empty AccSession || not empty GoogleAccount}">
    <c:redirect url="/home">
    </c:redirect>
</c:if>
<section class="" ">
    <!-- Jumbotron -->
    <div class="px-4 py-5 px-md-5 text-center text-lg-start" style="background-color: hsl(0, 0%, 96%); background-color: #3e464d;">
        <div class="container">
            <div class="row gx-lg-5 align-items-center" >
                <div class="col-lg-6 mb-5 mb-lg-0">
                    <img src="img/thiet-ke-shop-dien-thoai-huy-dung-mobile.jpg" width="100%" height="676px">
                </div>

                <div class="col-lg-6 mb-5 mb-lg-0">
                    <div class="card">
                        <div class="card-body py-5 px-md-5">
                            <form action="SignUp" method="post">
                                <h3 style="color: red">${ERROR}</h3>
                                <!-- 2 column grid layout with text inputs for the first and last names -->
                                <div class="row">
                                    <div class="col-md-6 mb-4">
                                        <div class="form-outline">
                                            <input type="text" name="txtFirstName" value="${requestScope.txtFirstName}" id="form3Example1" class="form-control" required pattern="([a-zA-Z',.-]{2,8}[ ]{0,1}){1,3}"/>
                                            <label class="form-label"  for="form3Example1">First name</label>
                                        </div>
                                    </div>
                                    <div class="col-md-6 mb-4">
                                        <div class="form-outline">
                                            <input type="text" name="txtLastName" value="${requestScope.txtLastName}" id="form3Example2" class="form-control" required pattern="([a-zA-Z',.-]{2,8}[ ]{0,1}){1,3}"/>
                                            <label class="form-label" for="form3Example2">Last name</label>
                                        </div>
                                    </div>  

                                </div>

                                <!-- Email input -->
                                <div class="form-outline mb-4">
                                    <input type="email" name="txtEmail" id="form3Example3" class="form-control" 
                                           required pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}"/>
                                    <label class="form-label" for="form3Example3">Email address</label>
                                </div>

                                <!-- Password input -->
                                <div class="form-outline mb-4">
                                    <input type="password" name="txtPass" id="form3Example4" class="form-control" 
                                           required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$"/>
                                    <label class="form-label" for="form3Example4">Password</label>
                                </div>
                                <!-- Password input -->
                                <div class="form-outline mb-4">
                                    <input type="text" name="txtAddress" value="${requestScope.txtAddress}" id="txtAddress" 
                                           class="form-control" required pattern="[0-9a-zA-Z #,-]{3,100}"/>
                                    <label class="form-label" for="txtAddress">Address</label>
                                </div>
                                <div class="form-outline mb-4">
                                    <input type="radio" name="gender"
                                           <c:if test="${gender}">
                                               checked
                                           </c:if> value="1" id="male"  required=""/>
                                    <label class="form-label" for="male">Male</label>
                                    <input type="radio" name="gender"
                                           <c:if test="${!gender}">
                                               checked
                                           </c:if> 
                                           value="0" id="female"  required=""/>
                                    <label class="form-label" for="female">FeMale</label>
                                </div>
                                <!-- Submit button -->
                                <button type="submit" class="btn btn-primary btn-block mb-4">
                                    Sign up
                                </button>
                            </form>
                            <!-- Register buttons -->
                            <div class="text-center">
                                <p>or sign up with:</p>
                                <button type="button" class="btn btn-link btn-floating mx-1">
                                    <i class="fab fa-facebook-f"></i>
                                </button>

                                <button type="button" class="btn btn-link btn-floating mx-1">
                                    <i class="fab fa-google"></i>
                                </button>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Jumbotron -->
</section>
<!-- Section: Design Block -->
<%@include file="template/footer.jsp" %>
