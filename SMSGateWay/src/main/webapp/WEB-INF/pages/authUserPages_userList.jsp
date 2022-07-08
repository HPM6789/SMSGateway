<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <title>Danh sách người dùng</title>

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
                        <form action="searchUser" method="get" style="" name="" id="formSearch">
                            <div class="row">
                                <div class="mb-3 col-md-4">
                                    <input type="hidden" name="action" value="search"/>
                                    <input type="text" name="inputSearch" placeholder="username, SĐT, họ tên hoặc email"
                                           id="inputSearch" class="form-control" value="${inputSearch}"/>
                                </div>
                                <div class="mb-3 col-md-2"  style="text-align: right">
                                    <label class="form-label" for="basic-default-status">Trạng Thái</label>
                                </div>
                                <div class="mb-3 col-md-4">
                                    <input type="radio"  id="radioStatus1" name="status" value="1" class="form-check-input"
                                           <c:if test="${status.equals('1')}">checked=""</c:if>/>&nbsp;Hoạt động&nbsp;&nbsp;
                                           <input type="radio"  id="radioStatus0" name="status" value="0" class="form-check-input"
                                           <c:if test="${status.equals('0')}">checked=""</c:if>/>&nbsp;Không hoạt động
                                    </div>
                                    <div class="mb-3 col-md-2">
                                        <input type="submit" value="Tìm kiếm" class="btn btn-primary me-2"/>
                                    </div>
                                    <div class="mb-3 col-md-4"></div>
                                    <div class="mb-3 col-md-2"  style="text-align: right">
                                        <label class="form-label" for="basic-default-status">Loại</label>
                                    </div>
                                    <div class="mb-3 col-md-4">
                                        <input type="radio"  id="radioType1" name="userType" value="1" class="form-check-input"
                                        <c:if test="${userType.equals('1')}">checked=""</c:if>/>&nbsp;CP&nbsp;&nbsp;
                                        <input type="radio"  id="radioType0" name="userType" value="0" class="form-check-input"
                                        <c:if test="${userType.equals('0')}">checked=""</c:if>/>&nbsp;Chưa xác nhận&nbsp;&nbsp;
                                        <input type="radio"  id="radioType2" name="userType" value="2" class="form-check-input"
                                        <c:if test="${userType.equals('2')}">checked=""</c:if>/>&nbsp;VNP
                                    </div>
                                    <div class="mb-3 col-md-2">
                                        <button class="btn btn-secondary" type="button" onclick="resetFormSearch()">Reset</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <!-- Bootstrap Table with Header - Light -->
                        <div class="card">
                            <h5 class="card-header">Danh sách người dùng</h5>
                            <div class="table-responsive text-nowrap">
                                <table class="table" >
                                    <thead class="table-light">
                                        <tr>
                                            <th>Tên Người Dùng</th>
                                            <th>Họ Tên</th>
                                            <th>Trạng Thái</th>
                                            <th>Email</th>
                                            <th>Loại</th>
                                            <th>Điện Thoại</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody class="table-border-bottom-0">
                                    <c:forEach var="u" items="${users}">
                                        <tr>
                                            <td>
                                                ${u.userName}
                                            </td>
                                            <td>${u.userFullname}</td>
                                            <td>
                                                <c:if test="${u.userStatus == 1}">
                                                    Hoạt động
                                                </c:if>
                                                <c:if test="${u.userStatus == 0}">
                                                    Không hoạt động
                                                </c:if>
                                            </td>
                                            <td>${u.userEmail}</td>
                                            <td>
                                                <c:if test="${u.userType == 0}">
                                                    Chưa xác nhận
                                                </c:if>
                                                <c:if test="${u.userType == 1}">
                                                    CP
                                                </c:if>
                                                <c:if test="${u.userType == 2}">
                                                    VNP
                                                </c:if>
                                            </td>
                                            <td>${u.userPhone}</td>
                                            <td>
                                                <div class="dropdown">
                                                    <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">
                                                        <i class="bx bx-dots-vertical-rounded"></i>
                                                    </button>
                                                    <div class="dropdown-menu">
                                                        <a class="dropdown-item"
                                                           href="userDetail?username=${u.userName}&page=${page}"
                                                           ><i class="bx bx-edit-alt me-1"></i> Sửa</a
                                                        >

                                                        <button type="button" class="dropdown-item" data-bs-toggle="modal" data-bs-target="#modalTop-${g.id}">
                                                            <i class="bx bx-trash me-1"></i> Xóa
                                                        </button>
                                                    </div>
                                                    <!-- Modal -->
                                                    <div class="modal modal-top fade" id="modalTop-${g.id}" tabindex="-1">
                                                        <div class="modal-dialog">
                                                            <form class="modal-content">
                                                                <div class="modal-header">
                                                                    <h5 class="modal-title" id="modalTopTitle">Xác nhận xóa</h5>
                                                                    <button
                                                                        type="button"
                                                                        class="btn-close"
                                                                        data-bs-dismiss="modal"
                                                                        aria-label="Close"
                                                                        ></button>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <div class="row">
                                                                        <div class="col mb-3">
                                                                            <h2>Bạn có chắc muốn xóa ${u.userName}</h2>
                                                                        </div>
                                                                    </div>

                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">
                                                                        Đóng
                                                                    </button>
                                                                    <a href="#" style="color: inherit; text-decoration: none">
                                                                        <button type="button" class="btn btn-primary">Xóa</button>
                                                                    </a>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
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
                                                <a class="page-link" href="userList?page=1&action=list"
                                                   ><i class="tf-icon bx bx-chevrons-left"></i
                                                    ></a>
                                            </li>
                                            <li class="page-item prev">
                                                <a class="page-link" href="userList?page=${(page==1)?1:(page-1)}&action=list"
                                                   ><i class="tf-icon bx bx-chevron-left"></i
                                                    ></a>
                                            </li>
                                            <c:forEach var="i" begin="${startDisplayPage}" end="${endDisplayPage}">
                                                <li class="page-item ${i==page?"active":""}">
                                                    <a class="page-link" href="userList?page=${i}&action=list">
                                                        ${i}
                                                    </a>
                                                </li>
                                            </c:forEach>
                                            <li class="page-item next">
                                                <a class="page-link" href="userList?page=${(page==endPage)?endPage:(page+1)}&action=list"
                                                   ><i class="tf-icon bx bx-chevron-right"></i
                                                    ></a>
                                            </li>
                                            <li class="page-item last">
                                                <a class="page-link" href="userList?page=${endPage}&action=list"
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
                                                <a class="page-link" href="searchUser?page=1&inputSearch=${inputSearch}&action=search&status=${status}&userType=${userType}"
                                                   ><i class="tf-icon bx bx-chevrons-left"></i
                                                    ></a>
                                            </li>
                                            <li class="page-item prev">
                                                <a class="page-link" href="searchUser?page=${(page==1)?1:(page-1)}&inputSearch=${inputSearch}&action=search&status=${status}&userType=${userType}"
                                                   ><i class="tf-icon bx bx-chevron-left"></i
                                                    ></a>
                                            </li>
                                            <c:forEach var="i" begin="${startDisplayPage}" end="${endDisplayPage}">
                                                <li class="page-item ${i==page?"active":""}">
                                                    <a class="page-link" href="searchUser?page=${i}&inputSearch=${inputSearch}&action=search&status=${status}&userType=${userType}">
                                                        ${i}
                                                    </a>
                                                </li>
                                            </c:forEach>
                                            <li class="page-item next">
                                                <a class="page-link" href="searchUser?page=${(page==endPage)?endPage:(page+1)}&inputSearch=${inputSearch}&action=search&status=${status}&userType=${userType}"
                                                   ><i class="tf-icon bx bx-chevron-right"></i
                                                    ></a>
                                            </li>
                                            <li class="page-item last">
                                                <a class="page-link" href="searchUser?page=${endPage}&inputSearch=${inputSearch}&action=search&status=${status}&userType=${userType}"
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
            $(document).ready(function () {
                $('#table1').DataTable();
            });
        </script>
        <script>
           function resetFormSearch() {
                document.getElementById("inputSearch").value = "";
                document.getElementById("radioStatus0").checked = false;
                document.getElementById("radioStatus1").checked = false;
                document.getElementById("radioType1").checked = false;
                document.getElementById("radioType2").checked = false;
                document.getElementById("radioType0").checked = false;
           };
        </script>
        <style>
            .dataTables_filter, .dataTables_info {
                display: none;
            }
        </style>
    </body>
</html>
