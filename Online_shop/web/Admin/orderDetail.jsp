<%@include file="temple/header.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<div class="card-header py-3 d-flex justify-content-between">
    <div><h6 class="m-0 font-weight-bold text-primary">ORDER DETAILS</h6></div>
    
</div>
<!-- spec -->
<div class="card-body">
    <div class="table-responsive">
        <table class="table table-bordered" width="100%">
            <thead>
                <tr>

                    <th>OrderID</th>
                    <th>ProductName</th>
                    <th>Picture</th>
                    <th>Quantity</th>
                    <th>Discount</th>
                    <th>Unitprice</th>
                    <th>$</th>
                </tr>
            </thead>
            <tbody>
                
                <c:forEach items="${list}" var="o"> 
                    <tr>
                        <td>${o.orderID}</td>
                        <td>${o.productName}</td>
                        <td><img src="../img/${o.picture}" alt="" style="width: 75px;"></td>
                        <td>${o.quantity}</td>
                        <td>${o.discount}</td>
                        <td><fmt:formatNumber maxFractionDigits="0" value="${o.unitprice}"/>$</td>
                        <td><fmt:formatNumber maxFractionDigits="0" value="${o.unitprice * o.quantity}"/>$</td>
                        <c:set var = "total"  value = "${o.unitprice * o.quantity}"/>
                    </tr>
                </c:forEach>

            </tbody>
            
        </table>
      
                <h5 style="color: red">TOTAL:  <fmt:formatNumber maxFractionDigits="0" value="${total}"/>$</h5>
            
    </div>
</div>

<!-- History -->

<!-- End -->
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
<div
    class="modal fade"
    id="logoutModal"
    tabindex="-1"
    role="dialog"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true"
    >
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                <button
                    class="close"
                    type="button"
                    data-dismiss="modal"
                    aria-label="Close"
                    >
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">
                Select "Logout" below if you are ready to end your current session.
            </div>
            <div class="modal-footer">
                <button
                    class="btn btn-secondary"
                    type="button"
                    data-dismiss="modal"
                    >
                    Cancel
                </button>
                <a class="btn btn-primary" href="login.html">Logout</a>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript-->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="vendor/datatables/jquery.dataTables.min.js"></script>
<script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

<!-- Page level custom scripts -->
<script src="js/demo/datatables-demo.js"></script>
</body>
</html>
