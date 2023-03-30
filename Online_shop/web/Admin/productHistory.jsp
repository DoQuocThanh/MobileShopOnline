<%@include  file="temple/header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="container-fluid">


    <div class="card shadow mb-4">
        <div class="card-header py-3 d-flex justify-content-between">
            <div><h6 class="m-0 font-weight-bold text-primary">Product History</h6></div>

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
                            <option value="CREATE">CREATE</option>
                            <option value="DELETE">DELETE</option>
                            <option value="UPDATE">UPDATE</option>
                          
                        </select>
                    </div>
                    <thead>

                        <tr>
                            <th>HistoryID</th>   
                            <th>EmployeeName</th>   
                            <th>Status</th>   
                            <th>Time</th>   
                            <th>ProductName</th>   
                            <th>BrandID</th>   
                            <th>Chip</th>   
                            <th>Ram</th>   
                            <th>Pin</th>   
                            <th>PhoneScreen</th>
                            <th>Picture</th>
                        </tr>

                    <tbody>

                        <c:forEach items="${list}" var="h"> 
                            
                            <tr>



                                <td>${h.historyID}</td>  
                                <td>${h.employeeName}</td>  
                                <td>${h.status}</td>  
                                <td>${h.time}</td>  
                                <td>${h.productName}</td>  
                                <td>${h.brandID}</td>  
                                <td>${h.chip}</td>  
                                <td>${h.ram}</td>  
                                <td>${h.pin}</td>  
                                <td>${h.phoneScreen}</td>  
                                <td><img src="../img/${h.picture}" alt="" style="width: 75px;"></td>
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

<!-- Custom scripts for all pages-->
<script src="js/sb-admin-2.min.js"></script>
<script>
$(document).ready(function () {
  var table = $('#dataTable').DataTable();
  var savedSearchValue = localStorage.getItem('searchValueProductH');
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
    localStorage.setItem('filterValuesProductH', JSON.stringify(filterValues));

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
      localStorage.removeItem('searchValueProductH');
    } else {
      localStorage.setItem('searchValueProductH', searchValue);
    }
  });

  // Add event listener for the select filter
  $('#select-filter').on('change', function () {
    var filterValue = $(this).val();

    // Save the filter value in local storage
    localStorage.setItem('filterValueProductH', filterValue);
    table.column(2).search(filterValue).draw();
  });

  // Retrieve and apply saved filter values on page load
  var savedFilterValues = localStorage.getItem('filterValuesProductH');
  if (savedFilterValues !== null) {
    savedFilterValues = JSON.parse(savedFilterValues);
    $('#startDate').val(savedFilterValues.startDate);
    $('#endDate').val(savedFilterValues.endDate);
    table.draw();
  }

  var savedFilterValue = localStorage.getItem('filterValueProductH');
  if (savedFilterValue !== null) {
    $('#select-filter').val(savedFilterValue);
    table.column(2).search(savedFilterValue).draw();
  }
});    
</script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.25/js/dataTables.bootstrap4.min.js"></script>
</body>
</html>