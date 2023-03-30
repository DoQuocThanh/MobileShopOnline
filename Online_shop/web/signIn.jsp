<%-- 
    Document   : signIn
    Created on : Jan 28, 2023, 8:27:56 PM
    Author     : LEGION
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="template/header.jsp" %>
<c:if test="${not empty AccNull}">
  <div class="alert alert-danger text-center my-3" role="alert">
    <c:out value="You should SIGN IN before comment!" />
  </div>
</c:if>
<c:if test="${not empty AccSession || not empty GoogleAccount}">
    <c:redirect url="/home">
    </c:redirect>
</c:if>
<section class="vh-100" style="background-color: #3e464d;">
  <div class="container py-4.5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-12 col-md-8 col-lg-6 col-xl-5">
        <div class="card shadow-2-strong" style="border-radius: 1rem;">
          <div class="card-body p-5 text-center">
              <form action="<c:url value="/SignIn"/>" method="post">
            <h3 class="mb-5">Sign in</h3>

            <div class="form-outline mb-4">
              <input type="email" id="typeEmailX-2" name="txtEmail" class="form-control form-control-lg" />
              <label class="form-label" for="typeEmailX-2">Email</label>
            </div>

            <div class="form-outline mb-4">
              <input type="password" id="typePasswordX-2" name="txtPass" class="form-control form-control-lg" />
              <label class="form-label" for="typePasswordX-2">Password</label>
            </div>
            <div>
                <a class="form-check d-flex justify-content-end mb-4" href="<c:url value="/ForgotPasswordController"/>"> Forgot Password</a>
            </div>
            <!-- Checkbox -->
            <div class="form-check d-flex justify-content-start mb-4">
              <input class="form-check-input" type="checkbox" value="" id="form1Example3" />
              <label class="form-check-label" for="form1Example3"> Remember password </label>
            </div>

            <button class="btn btn-primary btn-lg btn-block" type="submit" >Login</button>

            
            <hr class="my-4">

            <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile&redirect_uri=http://localhost:9999/Project_Swp/googleAccount&response_type=code&client_id=719822410434-haqr6pmal8ccb5aoea6881ta519ue5no.apps.googleusercontent.com&approval_prompt=force" class="btn btn-lg btn-block btn-primary" style="background-color: #dd4b39;"
              type="submit"><i class="fab fa-google me-2"></i> Sign in with google</a>
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
