
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${ Admin == null  }">
    <c:redirect url = "/Admin/login"/>
</c:if>

<html lang="en">

    <head>


        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Admin</title>

        <!-- Custom fonts for this template -->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">

        <!-- Custom styles for this page -->
        <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

    </head>

    <body id="page-top">

        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Sidebar -->
            <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

                <!-- Sidebar - Brand -->
                <a class="sidebar-brand d-flex align-items-center justify-content-center" href="product">
                    <div class="sidebar-brand-icon rotate-n-15">
                        <i class="fas fa-laugh-wink"></i>
                    </div>
                    <div class="sidebar-brand-text mx-3">Admin<sup></sup></div>
                </a>

                <!-- Divider -->
                <hr class="sidebar-divider my-0">



                <!-- Heading -->
                <div class="sidebar-heading">
                    ManageShopOnline
                </div>

                <li class="nav-item">
                    <a class="nav-link" href="employee">
                        <i class="fas fa-fw fa-table"></i>
                        <span>Manage Employee</span></a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="dashboard">
                        <i class='fa fa-mobile'></i>
                        <span>Dashbord</span></a>
                </li>


                <!-- Divider -->
                <hr class="sidebar-divider d-none d-md-block">

                <!-- Sidebar Toggler (Sidebar) -->
                <div class="text-center d-none d-md-inline">
                    <button class="rounded-circle border-0" id="sidebarToggle"></button>
                </div>

            </ul>
            <!-- End of Sidebar -->

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">

                    <!-- Topbar -->
                    <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                        <!-- Sidebar Toggle (Topbar) -->
                        <form class="form-inline">
                            <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                                <i class="fa fa-bars"></i>
                            </button>
                        </form>



                        <ul class="navbar-nav ml-auto">

                            <div class="topbar-divider d-none d-sm-block"></div>

                            <!-- Nav Item - User Information -->


                        </ul>
                        <a href="logout">Logout </a>          
                    </nav>



                    <div class="container-fluid">


                        <div class="card shadow mb-4">
                            <div class="card-header py-3 d-flex justify-content-between">
                                <div><h6 class="m-0 font-weight-bold text-primary">Employee</h6></div>
                                <a type="button" href="employeeUpdate" class="btn btn-success">Add Employee</a>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <div class="form-group">
                                         
                                            
                                        </div>
                                        <thead>
                                            <tr>
                                                <th>AccountID</th>
                                                <th>Email</th>
                                                <th>Pass</th>
                                                <th>EmployeeID</th>
                                                <th>Name</th>
                                                <th>BirthDate</th>
                                                <th>Address</th>
                                                <th>Status</th>
                                                <th></th>
                                            </tr>

                                        <tbody>

                                            <c:forEach items="${list}" var="a"> 
                                                <tr>
                                            <form action="employee" method="post" >
                                                <td>${a.accountID}</td>
                                                <td>${a.email}</td>
                                                <td>${a.pass}</td>
                                                <td>${a.employeeID}</td>
                                                <td>${a.name}</td>
                                                <td>${a.birthDate}</td>
                                                <td>${a.address}</td>
                                                <input type="hidden" name="accountID" value="${a.accountID}"/>
                                                <input type="hidden" name="status" value="${a.status}"/>
                                                
                                                </form>   

                                            <c:if test = "${a.status == 1}">
                                                <td><button class="btn btn-success" onclick="changeStatus(${a.accountID},${a.status})" type="submit">Active</button></td>
                                            </c:if>
                                            <c:if test = "${a.status == 2}">
                                                <td><button class="btn btn-danger" onclick="changeStatus(${a.accountID},${a.status})" type="submit">Baned</button></td>

                                            </c:if>
                                            <c:if test = "${a.status == 3}">
                                                <td><button class="btn btn-danger"  style="background-color: yellowgreen; border-color: yellow " onclick="changeStatus(${a.accountID},${a.status})" type="submit">Permission</button></td>

                                            </c:if>

                                                <td><a type="button" href="employeeUpdate?employeeID=${a.employeeID}" class="btn btn-success">Update Employee</a></td>
                                                
                                                
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

        <script>
                                  
                                                        function changeStatus(accountId, status) {

                                                            if (status === 1) {
                                                                change(accountId, "Baned", "Permission", status);
                                                            } else if (status === 2) {
                                                                change(accountId, "Active", "Permission", status);

                                                            } else if (status === 3) {
                                                                change(accountId, "Active", "Baned", status);
                                                            }

                                                        }


                                                        function change(accountID, status1, status2, currentStatus) {


                                                            var newStatus = prompt("Enter the new status (" + status1 + ", " + status2 + "):");
                                                            if (newStatus === status1 || newStatus === status2) {


                                                                const data = {accountID: accountID, status: newStatus, currentStatus: currentStatus};
                                                                $.ajax({
                                                                    type: 'POST',
                                                                    url: "employee",
                                                                    data: data,
                                                                    success: function (response) {

                                                                        window.location.href = "employee";
                                                                        Console.log("DoThanh");
                                                                    },
                                                                    error: function (error) {
                                                                        console.error('Error:', error);
                                                                    }
                                                                });
                                                            } else {
                                                                alert("Fail, please enter " + status1 + ", " + status2);
                                                            }
                                                        }

        </script>

    </body>

</html>