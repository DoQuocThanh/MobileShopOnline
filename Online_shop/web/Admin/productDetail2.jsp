<%@include file="temple/header.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<!-- Begin Page Content -->
<div class="container-fluid">
    <div class="card shadow mb-4">
        <div class="card-header py-3 d-flex justify-content-between">
            <div>
                <h6 class="m-0 font-weight-bold text-primary">
                    Product Detail
                </h6>
            </div>
        </div>
        <div class="card-body">


            <form method="POST" action="UploadServlet" enctype="multipart/form-data">
                <input type="file" id="fileInput" name="file"/>

            </form>


            <form class="form-horizontal" action="productdetail2" method="post"  >
                <fieldset>
                    <!-- Form Name -->
                    <legend>UPDATE PRODUCT DETAIL</legend>
                    <div class="productDetai row">
                        <!-- product left -->
                        <div class="productDetail_left col-4">
                            .
                            <div class="form-group">
                                <input type="hidden" name="employeeID" value="${Employee.employeeID}"/>
                                <input type="hidden" name="productID" value="${p.productID}"/>
                                <img    id="productPicture"
                                        src="../img/${p.picture}"
                                        style="
                                        height: 300px;
                                        width: 350px;
                                        object-fit: cover;
                                        border: 2px solid black;
                                        "
                                        />
                                <input hidden name="srcProductDetail" id="srcProductDetail" value = "${p.picture}" />
                                <c:if test="${Employee == null  }">

                                </c:if>
                                <label class="col-6 control-label" for="filebutton"
                                       >ImageProduct</label
                                >

                            </div>
                        </div>

                        <!-- product right -->
                        <div class="productDetail_right col-8">
                            <!-- Text input-->
                            <div class="row">
                                <div class="form-group col">
                                    <label class="col control-label" for="product_id"
                                           >ProductDetailID</label
                                    >
                                    <div class="col">
                                        <input
                                            id="productDetailID"
                                            name="productDetailID"
                                            value="${p.productDetailID}"
                                            class="form-control input-md"

                                            type="text"

                                            readonly
                                            />
                                    </div>
                                </div>
                                <div class="form-group col">
                                    <label class="col control-label" for="product_name"
                                           >ProductStorage</label
                                    >
                                    <div class="col">
                                        <input
                                            id="productStorage"
                                            name="productStorage"
                                            value="${p.productStorage}"
                                            class="form-control input-md"
                                            type="text"
                                            />
                                    </div>
                                </div>
                            </div>

                            <!-- Text input-->


                            <!-- Text input-->
                            <div class="row p-2">
                                <div class="form-group col">
                                    <label
                                        class="control-label"
                                        for="percentage_discount"
                                        >UnitsInStock</label
                                    >
                                    <div>
                                        <input
                                            id="UnitsInStock"
                                            name="unitsInStock"
                                            value="${p.unitsInStock}"

                                            class="form-control input-md"

                                            type="text"

                                            />
                                    </div>
                                </div>
                                <div class="form-group col">
                                    <label class="control-label" for="stock_alert"
                                           >UnitsOnOrder</label
                                    >
                                    <div>
                                        <input
                                            id="UnitsOnOrder"
                                            name="unitsOnOrder"
                                            value="${p.unitsOnOrder}"

                                            class="form-control input-md"
                                            readonly
                                            type="text"

                                            />
                                    </div>
                                </div>
                            </div>


                            <!-- Text input-->
                            <div class="row p-2">
                                <div class="form-group col">
                                    <label
                                        class="control-label"
                                        for="percentage_discount"
                                        >Coler</label
                                    >
                                    <div>
                                        <input
                                            id="coler"
                                            name="coler"
                                            value="${p.coler}"

                                            class="form-control input-md"

                                            type="text"

                                            />
                                    </div>
                                </div>
                                <div class="form-group col">
                                    <label class="control-label" for="stock_alert"
                                           >Price</label
                                    >
                                    <div>
                                        <input
                                            id="price"
                                            name="price"
                                            value="<fmt:formatNumber groupingUsed="false" maxFractionDigits="0" value="${p.price}"/>"

                                            class="form-control input-md"

                                            type="text"
                                            />
                                    </div>
                                </div>
                            </div>


                            <!-- Text input-->

                            <!-- Text input-->

                            <!-- Search input-->


                            <!-- Text input-->
                        </div>
                    </div>

                    <!-- File Button -->

                    <!-- File Button -->

                    <!-- Button -->
                    <div class="row">
                        <div class="col"></div>

                        <c:if test="${type == 'Create'  }">
                            <input hidden name="typeP" value="Create">
                            <div class="form-group col ml-2">
                                <!-- <label class="col-md-4 control-label" for="singlebutton">ADD Button</label> -->
                                <div>
                                    <input type="submit" class="btn btn-primary" onclick="return check()" value ="Create Product">


                                </div>
                            </div>
                        </c:if>
                        <c:if test="${type != 'Create'  }">
                            <div class="form-group col ml-2">
                                <!-- <label class="col-md-4 control-label" for="singlebutton">ADD Button</label> -->
                                <div>
                                    <input type="submit" class="btn btn-primary" onclick="return check()" value ="Update Product">


                                </div>
                            </div>
                        </c:if>   
                        <div class="col"></div>
                    </div>
                </fieldset>
            </form>
        </div>

        <!-- spec -->

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
<script src="js/productDetail.js"></script>
<script>
                                    function check() {
                                        const productStorage = document.getElementById('productStorage').value;
                                        const color = document.getElementById('coler').value;
                                        const price = document.getElementById('price').value;
                                        const unitsInStock = document.getElementById('UnitsInStock').value;

                                        if (!productStorage || isNaN(productStorage) || productStorage <= 0) {
                                            alert('Please enter a valid product storage value.');
                                            return false;
                                        }

                                        if (!color) {
                                            alert('Please enter a color.');
                                            return false;
                                        }

                                        if (!price || isNaN(price) || price <= 0) {
                                            alert('Please enter a valid price.');
                                            return false;
                                        }

                                        if (!unitsInStock || isNaN(unitsInStock) || unitsInStock <= 0) {
                                            alert('Please enter a valid units in stock value.');
                                            return false;
                                        }

                                        return true;
                                    }
</script>
<!-- Page level custom scripts -->

</body>  
</html>
