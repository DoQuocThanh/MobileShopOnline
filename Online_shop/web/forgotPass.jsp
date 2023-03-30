 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="template/header.jsp" %>
<section class="vh-100" style="background-color: #3e464d;">
    <div class="container py-4.5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card shadow-2-strong" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">
                        <form action="<c:url value="/ForgotPasswordController"/>" method="post">
                            <h3 class="mb-5">Forgot password</h3>
                            <h3 class="form-label" style="color: red" >${error}</h3>
                            <h3 class="form-label" style="color: graytext" >${succ}</h3>
                            <div class="form-outline mb-4">
                                <input type="text" id="typeEmailX-2" name="txtEmail" class="form-control form-control-lg" required pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}"/>
                                <label class="form-label" for="typeEmailX-2">Email</label>
                            </div>
                             <div class="form-outline mb-4">
                                <div class="form-outline">
                                    <input type="text" name="txtFirstName" value="${requestScope.txtFirstName}" id="form3Example1" class="form-control form-control-lg" required pattern="([a-zA-Z',.-]{2,8}[ ]{0,1}){1,3}"/>
                                    <label class="form-label"  for="form3Example1">First name</label>
                                </div>
                            </div>
                            <div class="form-outline mb-4">
                                <div class="form-outline">
                                    <input type="text" name="txtLastName" value="${requestScope.txtLastName}" id="form3Example2" class="form-control form-control-lg" required pattern="([a-zA-Z',.-]{2,8}[ ]{0,1}){1,3}"/>
                                    <label class="form-label" for="form3Example2">Last name</label>
                                </div>
                            </div>  
                            <div>
                                <a class="form-check d-flex justify-content-end mb-4" href="./SignIn"> Sign In</a>
                            </div>


                            <button class="btn btn-primary btn-lg btn-block" type="submit" >Submit</button>


                            <hr class="my-4">

                            <button class="btn btn-lg btn-block btn-primary" style="background-color: #dd4b39;"
                                    type="submit"><i class="fab fa-google me-2"></i> Sign in with google</button>
                            <button class="btn btn-lg btn-block btn-primary mb-2" style="background-color: #3b5998;"
                                    type="submit"><i class="fab fa-facebook-f me-2"></i> Sign in with facebook</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<%@include file="template/footer.jsp" %>
