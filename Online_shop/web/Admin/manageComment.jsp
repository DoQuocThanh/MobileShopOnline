<%@include  file="temple/header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="container-fluid">


    <div class="card shadow mb-4">
        <div class="card-header py-3 d-flex justify-content-between">
            <div><h6 class="m-0 font-weight-bold text-primary">COMMENTS</h6></div>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="startDate">Start Date</label>
                                <input type="date" class="form-control" id="startDate" name="startDate">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="endDate">End Date</label>
                                <input type="date" class="form-control" id="endDate" name="endDate">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="select-filter">Select Status: </label>
                        <select class="form-control" id="select-filter">
                            <option value="">All</option>
                            <option value="Active">Active</option>
                            <option value="Baned">Baned</option>
                        </select>
                    </div>
                    <thead>
                        <tr>

                            <th>Email</th>                        
                            <th>Description</th>
                            <th>Rate</th>
                            <th>Date</th>
                            <th>Picture</th>
                            <th>ProductName</th>
                            <th>Status</th>

                        </tr>

                    <tbody>

                        <c:forEach items="${list}" var="c"> 
                            <tr>
                        <form action="comment" method="post" >

                            <td>${c.email}</td>
                            <td>${c.description}</td>
                            <td>${c.rate}</td>
                            <td>${c.date}</td>

                            <td><img src="../img/${c.picture}" alt="" style="width: 75px;"></td>
                            <td>${c.productName}</td>                     
                            <input type="hidden" name="commentID" value="${c.commentID}"/>
                            <input type="hidden" name="status" value="${c.status}"/>
                            <c:if test = "${c.status == 1}">
                                <td><button class="btn btn-success" onclick="return check()" type="submit">Active</button></td>
                            </c:if>
                            <c:if test = "${c.status != 1}">
                                <td><button class="btn btn-danger" onclick="return check()" type="submit">Baned</button></td>
                            </c:if>
                        </form>

                        </tr>
                    </c:forEach>

                    </tbody>

                </table>

            </div>
        </div>
    </div>

</div>
<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->

<!-- Footer -->
<footer class="sticky-footer bg-white">
    <div class="container my-auto">
        <div class="copyright text-center my-auto">
            <span>Copyright &copy; Your Website 2020</span>
        </div>
    </div>
</footer>
<!-- End of Footer -->

</div>
<!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<%@include file="temple/footer.jsp" %>
<!-- Bootstrap core JavaScript-->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="js/sb-admin-2.min.js"></script>
<script src="js/commentTable.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.25/js/dataTables.bootstrap4.min.js"></script>
</body>

</html>