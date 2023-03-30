<!DOCTYPE html>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${ Employee == null  }">
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
                    <a class="nav-link" href="order">
                        <i class="fas fa-fw fa-table"></i>
                        <span>Manage Order</span></a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="product">
                        <i class='fa fa-mobile'></i>
                        <span>Manage Product</span></a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="customer">
                        <i class='fa fa-user'></i>
                        <span>Manage Account</span></a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="comment">
                        <i class='fa fa-user'></i>
                        <span>Manage Comment</span></a>
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
                            <li class="nav-item dropdown no-arrow">
                                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <span class="mr-2 d-none d-lg-inline text-gray-600 small">${Employee.firstname} ${ Employee.lastname}</span>
                                    <img class="img-profile rounded-circle"
                                         src="img/undraw_profile.svg">
                                    
                                </a>
                                
                                    
                                
                            </li>

                        </ul>
                          <a href="logout">Logout </a>          
                    </nav>
                    <!-- End of Topbar -->

                    <!-- Begin Page Content -->