
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
                    <a class="nav-link" href="product">
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
                        <form action="employeeUpdate" method="post" >
                            <label for="email">Email:</label><br>
                            <input  type="text" id="email" name="email" minlength="5" value="${employeeId.email}" required><br>
                            <input  hidden id="role" value="1" required><br>
                            <label for="password">Password:</label><br>
                            <input type="text" id="password" name="password" minlength="5" value="${employeeId.pass}" required><br>

                            <label for="firstname">Name:</label><br>
                            <input type="text" id="firstname" value="${employeeId.name}" name="firstname" required><br>


                            <label for="birthdate">Birth Date:</label><br>
                            <input type="date"  name="birthdate" value="${employeeId.birthDate}" required><br>

                            <label for="address">Address:</label><br>
                            <input  hidden name="type" value="Update" value="${employeeId.address} required><br>
                                    <input type="text" id="address" name="address" required><br>
                            <c:if test="${ type == 'Update'  }">
                                <input hidden name="typeP" value="update">
                                <input hidden name="employeeID" value="${employeeId.employeeID}">
                                <button class="btn btn-success"  type="submit">Update</button>
                            </c:if>
                            <c:if test="${ type != 'Update'  }">
                                <button class="btn btn-success"  type="submit">Create</button>
                            </c:if>


                        </form>



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
            const dateInput = document.getElementById("myDateInput");
            const dateValue = new Date(dateInput.value);
            const day = dateValue.getDate().toString().padStart(2, "0");
            const month = (dateValue.getMonth() + 1).toString().padStart(2, "0");
            const year = dateValue.getFullYear().toString();
            const formattedDate = `${day}/${month}/${year}`;
                dateInput.value = formattedDate;

        </script>

    </body>

</html>