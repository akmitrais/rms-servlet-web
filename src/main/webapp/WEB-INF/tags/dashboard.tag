<%@tag description="Dashboard layout" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@attribute name="css" fragment="true" %>
<%@attribute name="js" fragment="true" %>
<%@attribute name="headingTitle" fragment="true" %>
<%@attribute name="headingButton" fragment="true" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:layout>
    <jsp:attribute name="css">
        <link href="${pageContext.request.contextPath}/css/dashboard.css" rel="stylesheet">
        <jsp:invoke fragment="css" />
    </jsp:attribute>
    <jsp:attribute name="js">
        <!-- Icons -->
        <script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
        <script>feather.replace()</script>
        <jsp:invoke fragment="js" />
    </jsp:attribute>
    <jsp:body>
        <nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
            <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="${pageContext.request.contextPath}">MITRAIS</a>
            <input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search">
            <ul class="navbar-nav px-3">
                <li class="nav-item text-nowrap">
                    <a class="nav-link" href="${pageContext.request.contextPath}/logout">Sign out</a>
                </li>
            </ul>
        </nav>
        <div class="container-fluid">
            <div class="row">
                <nav class="col-md-2 d-none d-md-block bg-light sidebar">
                    <div class="sidebar-sticky">
                        <ul class="nav flex-column">
                            <li class="nav-item">
                                <a class="nav-link active" href="">
                                  <span data-feather="home"></span>
                                  Dashboard <span class="sr-only">(current)</span>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">
                                  <span data-feather="shopping-cart"></span>
                                  Orders
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">
                                  <span data-feather="box"></span>
                                  Products
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">
                                  <span data-feather="database"></span>
                                  Customers
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">
                                  <span data-feather="bar-chart-2"></span>
                                  Reports
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">
                                  <span data-feather="minimize-2"></span>
                                  Integrations
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/users">
                                  <span data-feather="users"></span>
                                  Users
                                </a>
                            </li>
                        </ul>
                        <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                            <span>Saved reports</span>
                            <a class="d-flex align-items-center text-muted" href="#">
                                <span data-feather="plus-circle"></span>
                            </a>
                        </h6>
                         <ul class="nav flex-column mb-2">
                            <li class="nav-item">
                                <a class="nav-link" href="#">
                                  <span data-feather="file-text"></span>
                                  Current month
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">
                                  <span data-feather="file-text"></span>
                                  Last quarter
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">
                                  <span data-feather="file-text"></span>
                                  Social engagement
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">
                                  <span data-feather="file-text"></span>
                                  Year-end sale
                                </a>
                            </li>
                        </ul>
                    </div>
                </nav>
                <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
                    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
                        <h1 class="h2"><jsp:invoke fragment="headingTitle" /></h1>
                        <div class="btn-toolbar mb-2 mb-md-0">
                            <jsp:invoke fragment="headingButton" />
                        </div>
                    </div>
                    <jsp:doBody />
                </main>
            </div>
        </div>
    </jsp:body>
</t:layout>