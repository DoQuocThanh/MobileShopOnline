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
            <a  class="btn btn-success" href="productHistory?productID=${product.productID}">ProductDetails History</a>
        </div>
        <div class="card-body">


            <form method="POST" action="UploadServlet" enctype="multipart/form-data">
                <input type="file" id="fileInput" name="file"/>

            </form>


            <form class="form-horizontal" action="productdetail" method="post"  onsubmit="return validateProductForm()">
                <fieldset>
                    <!-- Form Name -->
                    <legend>UPDATE PRODUCTS</legend>
                    <div class="productDetai row">
                        <!-- product left -->
                        <div class="productDetail_left col-4">
                            .
                            <div class="form-group">
                                <input type="hidden" name="employeeID" value="${Employee.employeeID}"/>

                                <img    id="productPicture"
                                        src="../img/${product.picture}"
                                        style="
                                        height: 300px;
                                        width: 300px;
                                        object-fit: cover;
                                        border: 2px solid black;
                                        "
                                        />
                                <input hidden name="srcProductDetail" id="srcProductDetail" value = "${product.picture}" />
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
                                           >PRODUCT ID</label
                                    >
                                    <div class="col">
                                        <input
                                            id="product_id"
                                            name="productid"
                                            value="${product.productID}"
                                            class="form-control input-md"

                                            type="text"

                                            readonly
                                            />
                                    </div>
                                </div>
                                <div class="form-group col">
                                    <label class="col control-label" for="product_name"
                                           >PRODUCT NAME</label
                                    >
                                    <div class="col">
                                        <input
                                            id="product-name"
                                            name="productname"
                                            value="${product.productName}"
                                            class="form-control input-md"
                                            type="text"
                                            />
                                    </div>
                                </div>
                            </div>

                            <!-- Text input-->
                            <div class="row">
                                <div class="form-group col">
                                    <label
                                        class="col control-label"
                                        for="product_categorie"
                                        >PRODUCT BRAND</label
                                    >
                                    <div class="col">
                                        <select     value="${product.brandName}"
                                                    id="productcategorie"
                                                    name="product_brand"
                                                    class="form-control"
                                                    >
                                            <c:forEach items="${brand}" var="b"> 
                                                <c:if test="${product.brandID == b.brandId}">
                                                    <option selected="selected" value="${b.brandId}">${b.brandName}</option>
                                                </c:if>
                                                <c:if test="${product.brandID != b.brandId}">
                                                    <option  value="${b.brandId}">${b.brandName}</option>
                                                </c:if>

                                            </c:forEach>


                                        </select>

                                    </div>
                                </div>
                                <div class="form-group col">
                                    <label
                                        class="col control-label"
                                        for="available_quantity"
                                        >RAM</label
                                    >
                                    <div class="col">
                                        <input
                                            id="ram"
                                            name="ram"
                                            value="${product.ram}"
                                            class="form-control input-md"
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
                                        >CHIP</label
                                    >
                                    <div>
                                        <input
                                            id="chip"
                                            name="chip"
                                            value="${product.chip}"

                                            class="form-control input-md"

                                            type="text"

                                            />
                                    </div>
                                </div>
                                <div class="form-group col">
                                    <label class="control-label" for="stock_alert"
                                           >PHONE SCREEN</label
                                    >
                                    <div>
                                        <input
                                            id="phone-screen"
                                            name="phonescreen"
                                            value="${product.phoneScreen}"

                                            class="form-control input-md"

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
                                        >AMOUNT RATE</label
                                    >
                                    <div>
                                        <input
                                            id="percentage_discount"
                                            name="amountrate"
                                            value="${product.amountRate}"
                                            placeholder=""
                                            class="form-control input-md"
                                            required=""
                                            type="text"
                                            readonly
                                            />
                                    </div>
                                </div>
                                <div class="form-group col">
                                    <label class="control-label" for="stock_alert"
                                           >RATE</label
                                    >
                                    <div>
                                        <input
                                            id="stock_alert"
                                            name="rate"
                                            value="${product.rate}"

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
                                        >UNIT ON ORDER</label
                                    >
                                    <div>
                                        <input
                                            id="percentage_discount"
                                            value="${product.unitOnOrder}"
                                            name="unitonorder"
                                            placeholder=""
                                            class="form-control input-md"
                                            readonly
                                            type="text"
                                            />
                                    </div>
                                </div>
                                <div class="form-group col">
                                    <label class="control-label" for="stock_critical"
                                           >UNIT ON STOCK</label
                                    >
                                    <div>
                                        <input
                                            id="stock_critical"
                                            name="unitonstock"
                                            value="${product.unitOnStock}"
                                            placeholder=""
                                            class="form-control input-md"

                                            type="search"
                                            readonly
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
                                        >OPERATING SYSTEM</label
                                    >
                                    <div>
                                        <input
                                            id="operatingSystemID"
                                            value="${product.operatingSystem}"
                                            name="operatingSystem"

                                            class="form-control input-md"

                                            type="text"
                                            />
                                    </div>
                                </div>
                                <div class="form-group col">
                                    <label class="control-label" for="stock_critical"
                                           >PIN</label
                                    >
                                    <div>
                                        <input
                                            id="pinID"
                                            name="pin"
                                            value="${product.pin}"
                                            placeholder=""
                                            class="form-control input-md"

                                            type="text"

                                            />
                                    </div>
                                </div>

                            </div>
                            <!-- Search input-->
                            <div class="row p-2">

                                <div class="form-group col">
                                    <label class="control-label" for="enable_display"
                                           >PRICE</label
                                    >
                                    <div>
                                        <input
                                            id="priceID"
                                            name="price"
                                            value="<fmt:formatNumber groupingUsed="false" maxFractionDigits="0" value="${product.price}"/>"

                                            class="form-control input-md"

                                            type="text"
                                            />
                                    </div>
                                </div>
                            </div>

                            <!-- Text input-->
                        </div>
                    </div>

                    <!-- File Button -->

                    <!-- File Button -->

                    <!-- Button -->
                    <div class="row">
                        <div class="col"></div>
                        <div class="form-group col ml-2">
                            <!-- <label class="col-md-4 control-label" for="singlebutton">ADD Button</label> -->
                            <div>
                                <input type="submit" class="btn btn-primary" onclick="return check()" value ="Update Product">


                            </div>
                        </div>
                        <div class="col"></div>
                    </div>
                </fieldset>
            </form>
        </div>

        <!-- spec -->
        <div class="card-body">
            <div class="table-responsive">
                <div>
                    <a type="button" href="productdetail2?productID=${product.productID}" class="btn btn-success">Add Product</a>
                </div>
                <table class="table table-bordered" width="100%">
                    <thead>
                        <tr>
                            <th>ProductDetailID</th>
                            <th>ProductStorage </th>
                            <th>Price</th>
                            <th>Picture </th>
                            <th>Coler</th>
                            <th>UnitOnOrder</th>
                            <th>UnitOnStock </th>
                            <th class="col-2">Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${productdetail}" var="p"> 
                            <tr>
                                <td>${p.productDetailID}</td>
                                <td>${p.productStorage}</td>
                                <td><fmt:formatNumber  maxFractionDigits="0" value="${p.price}"/></td>
                                <td><img src="../img/${p.picture}" alt="" style="width: 75px;"></td>
                                <td>${p.coler}</td>
                                <td>${p.unitsOnOrder}</td>
                                <td>${p.unitsInStock}</td>
                                <td>
                                    <form action="deleteProductDetail" method="post">
                                        <input type="hidden" name="employeeID" value="${Employee.employeeID}"/>
                                        <a
                                            href="productdetail2?productDetailID=${p.productDetailID}"
                                            class="btn btn-success"
                                            >Update</a
                                        >
                                        <input hidden type="text" name="productID" value="${p.productID}"/>    
                                        <input hidden type="text" name="productDetailID" value="${p.productDetailID}"/>
                                        
                                         <c:if test="${ Employee.status == 3  }">
                                            <input type="submit" onclick="return CheckDelete()"  value="Delete" class="btn btn-danger"/>
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
<script src="js/productDetail.js"></script>
<script>

                                            function CheckDelete() {
                                                if (confirm("Are you sure to delete this ProductDetail")) {
                                                    if (confirm("When you delete the product you will delete all data include Productdetail, OrderDetails and CartDetails of customer")) {
                                                        return true;


                                                    } else {
                                                        return false;

                                                    }
                                                } else
                                                {
                                                    return false;
                                                }

                                            }
                                            function check() {
                                                const ram = parseInt(document.getElementById('ram').value);
                                                const chip = document.getElementById('chip').value.trim();
                                                const phoneScreen = document.getElementById('phone-screen').value.trim();
                                                const productName = document.getElementById('product-name').value.trim();
                                                const price = parseFloat(document.getElementById('priceID').value);
                                                const pin = document.getElementById('pinID').value.trim();
                                                const operatingSystem = document.getElementById('operatingSystemID').value.trim();

                                                if (typeof productName !== 'string' ||
                                                        isNaN(ram) || typeof chip !== 'string' ||
                                                        typeof phoneScreen !== 'string' ||
                                                        typeof pin !== 'string' ||
                                                        typeof operatingSystem !== 'string' ||
                                                        isNaN(price) || !productName || !ram || !chip || !phoneScreen || !price || !pin || !operatingSystem)
                                                {
                                                    alert('Invalid input values.');
                                                    return false;
                                                }
                                                if (price < 0) {
                                                    alert('Price must be greater than or equal to 0.');
                                                    return false;
                                                }


                                                return true;


                                            }
</script>
<!-- Page level custom scripts -->

</body>  
</html>
