<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- 
    Document   : userlist
    Created on : May 13, 2022, 9:53:57 AM
    Author     : Minh Hieu Pham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html
    lang="en"
    class="light-style layout-menu-fixed"
    dir="ltr"
    data-theme="theme-default"
    data-assets-path="${pageContext.request.contextPath}/assets/"
    data-template="vertical-menu-template-free"
    >
    <head>
        <meta charset="utf-8" />
        <meta
            name="viewport"
            content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"
            />

        <title>Action Log List</title>

        <meta name="description" content="" />

        <!-- Favicon -->
        <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon/favicon.ico" />

        <!-- Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap"
            rel="stylesheet"
            />

        <!-- Icons. Uncomment required icon fonts -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/fonts/boxicons.css" />

        <!-- Core CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/css/core.css" class="template-customizer-core-css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/css/theme-default.css" class="template-customizer-theme-css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/demo.css" />

        <!-- Vendors CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css" />
        <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.12.0/css/jquery.dataTables.min.css">

        <!-- Page CSS -->


        <!-- Helpers -->
        <script src="${pageContext.request.contextPath}/assets/vendor/js/helpers.js"></script>

        <!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
        <!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
        <script src="${pageContext.request.contextPath}/assets/js/config.js"></script>
    </head>
    <body>
        <div class="layout-wrapper layout-content-navbar">
            <div class="layout-container">
                <%@include file="sidebar.jsp" %>
                <div class="layout-page">
                    <div class="card" style="margin-bottom: 10px; padding: 10px 0 10px 10px;">
                        <form action="searchAction" method="get" style="" name="">
                            <div class="row">
                                <input type="hidden" name="action" value="search"/>
                                <div class="mb-3 col-md-2">
                                    <input type="text" name="inputSearch" placeholder="Log/Người dùng"
                                           id="inputSearch" class="form-control" value="${inputSearch}"/>
                                </div>
                                <div class="mb-3 col-md-2" style="padding-top: auto; padding-bottom: ">
                                    <label class="form-label" for="" style="padding-left: 50px">Ngày Tạo: </label>
                                </div>
                                <div class="mb-3 col-md-3">
                                    <label class="form-label" for="">Từ </label>
                                    <input type="date" name="fromCreateDate" style="width: auto; display: inline-block;
                                           margin-left: 10px;"
                                           id="fromCreateDate" class="form-control" value="${fromCreateDate}"/>
                                </div>
                                <div class="mb-3 col-md-3">
                                    <label class="form-label" for="">Đến </label>
                                    <input type="date" name="toCreateDate" style="width: auto; display: inline-block;
                                           margin-left: 10px;"
                                           id="toCreateDate" class="form-control" value="${toCreateDate}"/>
                                </div>
                                <div class="mb-3 col-md-2">
                                    <input type="submit" value="Tìm kiếm" class="btn btn-primary me-2"/>
                                </div>
                                <div class="mb-3 col-md-2"></div>
                                <div class="mb-3 col-md-2"  style="text-align: right">
                                    <label class="form-label" for="basic-default-status">Kết quả</label>
                                </div>
                                <div class="mb-3 col-md-4">
                                    <input type="radio"  id="radioStatus1" name="actionResult" value="Thành Công" class="form-check-input"
                                    <c:if test="${actionResult.equals('Thành Công')}">checked=""</c:if>/>&nbsp;Thành Công&nbsp;&nbsp;
                                    <input type="radio"  id="radioStatus0" name="actionResult" value="Thất bại" class="form-check-input"
                                    <c:if test="${actionResult.equals('Thất bại')}">checked=""</c:if>/>&nbsp;Thất bại
                                </div>
                                <div class="mb-3 col-md-2">
                                <div class="mb-3 col-md-2">
                                    <button class="btn btn-secondary" type="button" onclick="resetFormSearch()">Reset</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <!-- Bootstrap Table with Header - Light -->
                    <div class="card">
                        <div class="row">

                        </div>
                        <br>

                        <h5 class="card-header">Danh sách Log</h5>
                        <div class="table-responsive text-nowrap">
                            <table class="table" id="">
                                <thead class="table-light">
                                    <tr>
                                        <th>Log</th>
                                        <th>Người Dùng</th>
                                        <th>Ngày Tạo</th>
                                        <th>Kết Quả</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody class="table-border-bottom-0">
                                    <c:forEach var="l" items="${logs}">
                                        <tr>
                                            <td>
                                                ${l.actionlogName}
                                            </td>
                                            <td>${l.userName}</td>
                                            <td>
                                                <fmt:formatDate value="${l.actionlogTime}" pattern="dd/MM/yyyy HH:mm:ss"/>
                                            </td>
                                            <td>
                                                ${l.actionlogResult}
                                            </td>
                                            <td>
                                                <a href="actionLogDetail?actionlogId=${l.actionlogId}&page=${page}" style="color: inherit; text-decoration: none">
                                                    <button type="button" class="dropdown-item" data-bs-toggle="modal" data-bs-target="#modalTop-${g.id}">
                                                        Chi tiết
                                                    </button>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <div style="float: right; margin-right: 10%">
                                <c:if test="${action.equals('list')}">
                                    <nav aria-label="Page navigation">
                                        <ul class="pagination">
                                            <li class="page-item first">
                                                <a class="page-link" href="actionLogList?page=1&action=list"
                                                   ><i class="tf-icon bx bx-chevrons-left"></i
                                                    ></a>
                                            </li>
                                            <li class="page-item prev">
                                                <a class="page-link" href="actionLogList?page=${(page==1)?1:(page-1)}&action=list"
                                                   ><i class="tf-icon bx bx-chevron-left"></i
                                                    ></a>
                                            </li>
                                            <c:forEach var="i" begin="${startDisplayPage}" end="${endDisplayPage}">
                                                <li class="page-item ${i==page?"active":""}">
                                                    <a class="page-link" href="actionLogList?page=${i}&action=list">
                                                        ${i}
                                                    </a>
                                                </li>
                                            </c:forEach>
                                            <li class="page-item next">
                                                <a class="page-link" href="actionLogList?page=${(page==endPage)?endPage:(page+1)}&action=list"
                                                   ><i class="tf-icon bx bx-chevron-right"></i
                                                    ></a>
                                            </li>
                                            <li class="page-item last">
                                                <a class="page-link" href="actionLogList?page=${endPage}&action=list"
                                                   ><i class="tf-icon bx bx-chevrons-right"></i
                                                    ></a>
                                            </li>
                                        </ul>
                                    </nav>
                                </c:if>
                                <c:if test="${action.equals('search')}">
                                    <nav aria-label="Page navigation">
                                        <ul class="pagination">
                                            <li class="page-item first">
                                                <a class="page-link" href="searchAction?page=1&inputSearch=${inputSearch}&action=search&fromCreateDate=${fromCreateDate}&toCreateDate=${toCreateDate}&actionResult=${actionResult}"
                                                   ><i class="tf-icon bx bx-chevrons-left"></i
                                                    ></a>
                                            </li>
                                            <li class="page-item prev">
                                                <a class="page-link" href="searchAction?page=${(page==1)?1:(page-1)}&inputSearch=${inputSearch}&action=search&fromCreateDate=${fromCreateDate}&toCreateDate=${toCreateDate}&actionResult=${actionResult}"
                                                   ><i class="tf-icon bx bx-chevron-left"></i
                                                    ></a>
                                            </li>
                                            <c:forEach var="i" begin="${startDisplayPage}" end="${endDisplayPage}">
                                                <li class="page-item ${i==page?"active":""}">
                                                    <a class="page-link" href="searchAction?page=${i}&inputSearch=${inputSearch}&action=search&fromCreateDate=${fromCreateDate}&toCreateDate=${toCreateDate}&actionResult=${actionResult}">
                                                        ${i}
                                                    </a>
                                                </li>
                                            </c:forEach>
                                            <li class="page-item next">
                                                <a class="page-link" href="searchAction?page=${(page==endPage)?endPage:(page+1)}&inputSearch=${inputSearch}&action=search&fromCreateDate=${fromCreateDate}&toCreateDate=${toCreateDate}&actionResult=${actionResult}"
                                                   ><i class="tf-icon bx bx-chevron-right"></i
                                                    ></a>
                                            </li>
                                            <li class="page-item last">
                                                <a class="page-link" href="searchAction?page=${endPage}&inputSearch=${inputSearch}&action=search&fromCreateDate=${fromCreateDate}&toCreateDate=${toCreateDate}&actionResult=${actionResult}"
                                                   ><i class="tf-icon bx bx-chevrons-right"></i
                                                    ></a>
                                            </li>
                                        </ul>
                                    </nav>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="layout-overlay layout-menu-toggle"></div>
            </div>
            <!-- Bootstrap Table with Header - Light -->
            <!-- Core JS -->
            <!-- build:js assets/vendor/js/core.js -->

            <script src="${pageContext.request.contextPath}/assets/vendor/libs/jquery/jquery.js"></script>
            <script src="${pageContext.request.contextPath}/assets/vendor/libs/popper/popper.js"></script>
            <script src="${pageContext.request.contextPath}/assets/vendor/js/bootstrap.js"></script>
            <script src="${pageContext.request.contextPath}/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>

            <script src="${pageContext.request.contextPath}/assets/vendor/js/menu.js"></script>
            <!-- endbuild -->

            <!-- Vendors JS -->

            <!-- Main JS -->
            <script src="${pageContext.request.contextPath}/assets/js/main.js"></script>

            <!-- Page JS -->

            <!-- Place this tag in your head or just before your close body tag. -->
            <script async defer src="https://buttons.github.io/buttons.js"></script>
            <!-- DataTables CSS -->

            <!-- jQuery -->

            <!-- DataTables -->
            <script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.12.0/js/jquery.dataTables.min.js"></script>
            <script>
                function resetFormSearch() {
                    document.getElementById("inputSearch").value = "";
                    document.getElementById("fromCreateDate").value = "";
                    document.getElementById("toCreateDate").value = "";
                    document.getElementById("radioStatus1").checked = false;
                    document.getElementById("radioStatus0").checked = false;
                };
            </script>

            <style>
                .dataTables_filter, .dataTables_info {
                    display: none;
                }
            </style>
    </body>
</html>
