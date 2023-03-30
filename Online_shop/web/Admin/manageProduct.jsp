<%@include  file="temple/header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>


<div class="container-fluid">

    <div class="card shadow mb-4">
        <div class="card-header py-3 d-flex justify-content-between">
            <div><h6 class="m-0 font-weight-bold text-primary">PRODUCTS</h6></div>
            <form action="productHistory" method="post">
                <div>
                    <a type="button" href="addProduct" class="btn btn-success">Add Product</a>

                    <input  type ="submit" value="Product History" class="btn btn-success" style="background-color: blue; border-color: blue" />
                </div>
            </form>

        </div>

        <div class="card-body">
            <div class="table-responsive">


                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <div class="form-group">
                        <label for="select-filter">Select Brand:</label>
                        <select class="form-control" id="select-filter">
                            <option value="">All</option>
                            <c:forEach items="${Brand}" var="b">      
                                <option value="${b.brandName}">${b.brandName}</option>
                            </c:forEach>
                        </select>
                    </div>


                    <thead>
                        <tr>
                            <th>ProductID</th>
                            <th>ProductName</th>
                            <th>UnitOnStock</th>
                            <th>UnitOnOrder</th>
                            <th>BrandName</th>

                            <th class="col-2">Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${Product}" var="p">
                            <tr>
                                <td>${p.productID}</td>
                                <td>${p.productName}</td>
                                <td>${p.unitOnStock}</td>
                                <td>${p.unitOnOrder}</td>
                                <td>${p.brandName}</td>

                                <td>
                                    <form action="deleteProduct" method="post">
                                        <input type="hidden" name="employeeID" value="${Employee.employeeID}"/>
                                        <a
                                            href="productdetail?productID=${p.productID}"
                                            class="btn btn-success"
                                            >View</a
                                        >

                                        <input hidden type="text" name="productID" value="${p.productID}"/>
                                        <c:if test="${ Employee.status == 3}">
                                            <input type="submit" onclick="return check()" value="Delete" class="btn btn-danger"/>
                                        </c:if>
                                        

                                    </form>     
                                </td>

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



<!-- Page level custom scripts -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.24/js/dataTables.bootstrap4.min.js"></script>

<script>
                                            function check() {
                                                if (confirm("Are you sure to delete this Product")) {
                                                    if (confirm("When you delete the product you will delete all data include Productdetail,Comment, OrderDetails and CartDetails of customer")) {
                                                        return true;


                                                    } else {
                                                        return false;

                                                    }
                                                } else
                                                {
                                                    return false;
                                                }

                                            }
                                            $(document).ready(function () {

                                                var x = $('#dataTable').DataTable({
                                                    "columnDefs": [{
                                                            "targets": [0, 2, 3], // Replace with an array of indices for columns to disable searching
                                                            "searchable": false
                                                        }]
                                                });
                                                $('#select-filter').on('change', function () {
                                                    var filterValue = $(this).val();
                                                    x.column(4).search(filterValue).draw();
                                                });
                                            });
</script>
</body>
</html>