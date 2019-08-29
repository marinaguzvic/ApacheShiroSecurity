<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${cookie.lang == 'en'}">
    <c:set var="lang" value="en"/>
    <c:set var="langFlag" value="us"/>
    <c:set var="langName" value="English"/>
</c:if>
<c:if test="${cookie.lang == 'es_ES'}">
    <c:set var="lang" value="es_ES"/>
    <c:set var="langFlag" value="es"/>
    <c:set var="langName" value="Spanish"/>
</c:if>
<c:if test="${cookie.lang == 'sr_SR'}">
    <c:set var="lang" value="sr_SR"/>
    <c:set var="langFlag" value="rs"/>
    <c:set var="langName" value="Serbian"/>
</c:if>
<c:if test="${cookie.lang == null}">
    <c:set var="lang" value="en"/>
    <c:set var="langFlag" value="us"/>
    <c:set var="langName" value="English"/>
</c:if>
<nav class="navbar navbar-expand navbar-dark bg-dark static-top">

    <a class="navbar-brand mr-1" href="index.html">Start Bootstrap</a>

    <button class="btn btn-link btn-sm text-white order-1 order-sm-0" id="sidebarToggle" href="#">
        <i class="fas fa-bars"></i>
    </button>

    
    <!-- Navbar Search -->
    <form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
        <div class="input-group">
            <input type="text" class="form-control" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
            <div class="input-group-append">
                <button class="btn btn-primary" type="button">
                    <i class="fas fa-search"></i>
                </button>
            </div>
            <div class="nav-item dropdown">
                <!--<li class="nav-item dropdown">-->

                <a class="nav-link dropdown-toggle" href="?lang=${param.lang}" id="dropdown09" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="flag-icon flag-icon-${langFlag}"> </span>${cookie['lang'].value} ${langName}</a>
                <div class="dropdown-menu" aria-labelledby="dropdown09">
                    <a class="dropdown-item" href="?lang=en"><span class="flag-icon flag-icon-us"> </span>  English</a>
                    <a class="dropdown-item" href="?lang=es_ES"><span class="flag-icon flag-icon-es"> </span>  Spanish</a>
                    <a class="dropdown-item" href="?lang=sr_SR"><span class="flag-icon flag-icon-rs"> </span>  Serbian</a>
                </div>
                <!--</li>-->
            </div>

        </div>
    </form>

    <!-- Navbar -->
    <ul class="navbar-nav ml-auto ml-md-0">
        <li class="nav-item dropdown no-arrow mx-1">
            <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-bell fa-fw"></i>
                <span class="badge badge-danger">9+</span>
            </a>
            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="alertsDropdown">
                <a class="dropdown-item" href="#">Action</a>
                <a class="dropdown-item" href="#">Another action</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">Something else here</a>
            </div>
        </li>
        <li class="nav-item dropdown no-arrow mx-1">
            <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-envelope fa-fw"></i>
                <span class="badge badge-danger">7</span>
            </a>
            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="messagesDropdown">
                <a class="dropdown-item" href="#">Action</a>
                <a class="dropdown-item" href="#">Another action</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">Something else here</a>
            </div>
        </li>
        <li class="nav-item dropdown no-arrow">
            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-user-circle fa-fw"></i>
            </a>
            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
                <a class="dropdown-item" href="#">Settings</a>
                <a class="dropdown-item" href="#">Activity Log</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">Logout</a>
            </div>
        </li>
    </ul>

</nav>