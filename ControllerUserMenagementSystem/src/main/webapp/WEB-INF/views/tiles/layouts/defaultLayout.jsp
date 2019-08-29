<%-- 
    Document   : defaultLayout
    Created on : May 29, 2019, 10:40:43 AM
    Author     : MARINA
--%>
<%-- 
    Document   : defaultLayout
    Created on : Jan 10, 2019, 1:58:59 PM
    Author     : Dusan
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    


    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <!--<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">-->
        <title><tiles:getAsString name="title" /></title>
        <link href="/usermgmt/webjars/bootstrap/4.3.0/css/bootstrap.min.css" rel="stylesheet">
        <link href="/usermgmt/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css"/>
        <link href="/usermgmt/resources/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet" type="text/css"/>
        <link href="/usermgmt/resources/css/sb-admin.css" rel="stylesheet" type="text/css"/>
        <link href="/usermgmt/resources/css/bootstrap-datepicker.min.css" rel="stylesheet" type="text/css"/>
        <link href="/usermgmt/resources/css/style.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/3.1.0/css/flag-icon.min.css" rel="stylesheet">

    </head>

    <body id="page-top">
        <!--<header id="header">-->
        <tiles:insertAttribute name="header" />
        <!--</header>-->
        <div id="wrapper">
            <!--<section id="sidemenu">-->
            <tiles:insertAttribute name="menu" />
            <!--</section>-->
            <div id="content-wrapper">
                <!--<section id="site-content">-->
                <tiles:insertAttribute name="body" />
                <!--</section>-->
                <footer class="sticky-footer">
                    <tiles:insertAttribute name="footer" />
                </footer>
            </div>
        </div>

        <!-- Scroll to Top Button-->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>

        <!-- Logout Modal-->
        <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                    <div class="modal-footer">
                        <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                        <a class="btn btn-primary" href="/usermgmt/perform_logout">Logout</a>
                    </div>
                </div>
            </div>
        </div>



        <script src="/usermgmt/resources/vendor/jquery/jquery.min.js"></script>
        <script src="/usermgmt/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="/usermgmt/resources/vendor/jquery-easing/jquery.easing.min.js"></script>


        <!-- Custom scripts for all pages-->
        <script src="/usermgmt/resources/js/sb-admin.min.js"></script>
        <script src="/usermgmt/resources/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
        <script src="/usermgmt/resources/js/script.js" type="text/javascript"></script>

    </body>

</html>
