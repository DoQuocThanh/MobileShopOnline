<%@include  file="temple/header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="container-fluid">


    <div class="card shadow mb-4">
        <div class="card-header py-3 d-flex justify-content-between">
            <div><h6 class="m-0 font-weight-bold text-primary">ORDERS</h6></div>
            <div>
                <a  class="btn btn-success" href="history">Order History</a>
            </div>
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
                            <option value="Accepted">Accepted</option>
                            <option value="Admin Cancelled">Admin Cancelled</option>
                            <option value="Customer">Customer Cancelled</option>
                            <option value="Pending">Pending</option>
                            <option value="Done">Done</option>
                        </select>
                    </div>

                    <thead>
                        <tr>

                            <th>CustomerID</th>   
                            <th>Name</th>
                            <th>OrderID</th>
                            <th>OrderDate</th>

                            <th>ShippedDate</th>
                            <th>ShipAddress</th>
                            <th class="col-3">Status</th>

                        </tr>

                    <tbody>

                        <c:forEach items="${list}" var="o"> 
                            <tr>



                                <td>${o.customerID}</td>
                                <td>${o.contactName}</td>
                                <td>${o.orderID}</td><!-- comment -->
                                <td>${o.orderDate}</td>

                                <td>${o.shippedDate}</td>
                                <td>${o.shipAddress}</td>

                        <input type="hidden" name="orderID" value="${o.orderID}"/>
                        <input type="hidden" name="status" value="${o.status}"/>
                        <input type="hidden" name="employeeID" value="${Employee.employeeID}"/>
                        <td>
                            <a
                                href="orderdetail?orderID=${o.orderID}"
                                class="btn btn-success"
                                >View</a
                            >

                            <c:if test = "${o.status == 0}">
                                <button class="btn btn-success" style="background-color: yellowgreen; border-color: yellowgreen " onclick="changeStatus(${Employee.employeeID},${o.status},${o.orderID})" type="submit">Pending</button>
                            </c:if>
                            <c:if test = "${o.status == 1}">
                                <button class="btn btn-success" onclick="changeStatus(${Employee.employeeID},${o.status},${o.orderID})" type="submit">Accepted</button>
                            </c:if>
                            <c:if test = "${o.status == 2}">
                                <button class="btn btn-danger" onclick="changeStatus(${Employee.employeeID},${o.status},${o.orderID})" type="submit">Admin Cancelled</button>
                            </c:if>
                            <c:if test = "${o.status == 4}">
                                <button class="btn btn-danger" >Customer Cancelled</button>
                            </c:if>
                            <c:if test = "${o.status == 3}">
                                <text class="btn btn-danger"  onclick="changeStatus(${Employee.employeeID},${o.status},${o.orderID})" style="background-color: blue; border-color: blue">Done  </text>
                            </c:if>
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
<!-- Bootstrap core JavaScript-->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>
<script src="js/orderTable.js"></script>
<script>
                                    function changeStatus(employeeId, status, orderID) {

                                        if (status === 1) {
                                            change(employeeId, "Pending", "Cancelled", "Done", orderID, status);
                                        } else if (status === 2) {
                                            change(employeeId, "Pending", "Accepted", "Done", orderID, status);

                                        } else if (status === 0) {
                                            change(employeeId, "Accepted", "Cancelled", "Done", orderID, status);
                                        } else if (status === 3) {
                                            change(employeeId, "Accepted", "Cancelled", "Pending", orderID, status);
                                        }

                                    }


                                    function change(employeeId, status1, status2, status3, orderID, currentStatus) {


                                        var newStatus = prompt("Enter the new status (" + status1 + ", " + status2 + "  or " + status3 + "):");
                                        if (newStatus === status1 || newStatus === status2 || newStatus === status3) {


                                            const data = {id: employeeId, status: newStatus, orderID: orderID, currentStatus: currentStatus};
                                            $.ajax({
                                                type: 'POST',
                                                url: "order",
                                                data: data,
                                                success: function (response) {

                                                    window.location.href = "order";
                                                },
                                                error: function (error) {
                                                    console.error('Error:', error);
                                                }
                                            });
                                        } else {
                                            alert("Fail, please enter " + status1 + ", " + status2 + "  or " + status3);
                                        }
                                    }
                                    $(document).ready(function () {

                                        var table = $('#dataTable').DataTable();
                                        var savedSearchValue = localStorage.getItem('searchValueOrder');
                                        if (savedSearchValue !== null) {
                                            table.search(savedSearchValue).draw();
                                        }

                                        // Add event listener for the date range filter
                                        $('#startDate, #endDate').on('change', function () {
                                            var startDate = $('#startDate').val();
                                            var endDate = $('#endDate').val();

                                            // Save the filter values in local storage
                                            var filterValues = {
                                                startDate: startDate,
                                                endDate: endDate
                                            };
                                            localStorage.setItem('filterValuesOrder', JSON.stringify(filterValues));

                                            // Perform date range filtering
                                            table.draw();
                                        });

                                        // Add custom date range filtering function
                                        $.fn.dataTable.ext.search.push(
                                                function (settings, data, dataIndex) {
                                                    var startDate = $('#startDate').val();
                                                    var endDate = $('#endDate').val();
                                                    var currentDate = new Date(data[3]);

                                                    if (startDate && endDate) {
                                                        var start = new Date(startDate);
                                                        var end = new Date(endDate);
                                                        return (currentDate >= start && currentDate <= end);
                                                    } else if (startDate && !endDate) {
                                                        var start = new Date(startDate);
                                                        return (currentDate >= start);
                                                    } else if (!startDate && endDate) {
                                                        var end = new Date(endDate);
                                                        return (currentDate <= end);
                                                    }
                                                    return true;
                                                }
                                        );

                                        // Add event listener for the search box
                                        $('#dataTable_filter input').on('keyup', function () {
                                            var searchValue = $(this).val();
                                            if (searchValue === '') {
                                                localStorage.removeItem('searchValueOrder');
                                            } else {
                                                localStorage.setItem('searchValueOrder', searchValue);
                                            }
                                        });

                                        // Add event listener for the select filter
                                        $('#select-filter').on('change', function () {
                                            var filterValue = $(this).val();

                                            // Save the filter value in local storage
                                            localStorage.setItem('filterValueOrder', filterValue);
                                            table.column(6).search(filterValue).draw();
                                        });

                                        // Retrieve and apply saved filter values on page load
                                        var savedFilterValues = localStorage.getItem('filterValuesOrder');
                                        if (savedFilterValues !== null) {
                                            savedFilterValues = JSON.parse(savedFilterValues);
                                            $('#startDate').val(savedFilterValues.startDate);
                                            $('#endDate').val(savedFilterValues.endDate);
                                            table.draw();
                                        }

                                        var savedFilterValue = localStorage.getItem('filterValueOrder');
                                        if (savedFilterValue !== null) {
                                            $('#select-filter').val(savedFilterValue);
                                            table.column(6).search(savedFilterValue).draw();
                                        }
                                    });

</script>
<!-- Custom scripts for all pages-->
<script src="js/sb-admin-2.min.js"></script>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.25/js/dataTables.bootstrap4.min.js"></script>
</body>

</html>