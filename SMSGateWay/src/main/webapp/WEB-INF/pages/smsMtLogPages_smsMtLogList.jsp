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

        <title>SMS MT LOG List</title>

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
                        <form action="searchSmsMtLog" method="get" style="" name="searchUserForm">
                            <input type="hidden" name="action" value="search"/>
                            <div class="row">
                                <div class="mb-3 col-md-2">
                                    <input type="text" name="inputSearch" placeholder="SĐT/Đầu số"
                                           id="inputSearch" class="form-control" value="${inputSearch}"
                                           />
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
                                <div class="mb-3 col-md-10"></div>
                                <div class="mb-3 col-md-2">
                                    <button class="btn btn-secondary" type="button" onclick="resetFormSearch()">Reset</button>
                                </div>
                                <div class="mb-3 col-md-2"></div>
                            </div>

                        </form>
                    </div>
                    <!-- Bootstrap Table with Header - Light -->
                    <div class="card">
                        <h5 class="card-header">Danh sách SMS MT LOG</h5>
                        <div class="table-responsive text-nowrap">
                            <div class="row">
                                <div class="col-md-2">

                                </div>
                                <div class="col-md-3" style="text-align: center">

                                </div>
                                <div class="col-md-4"></div>
                            </div>
                            <table class="table" id="">
                                <thead class="table-light">
                                    <tr>
                                        <th>Thuê bao</th>
                                        <th>Đầu số</th>
                                        <th>Giá</th>
                                        <th>Ngày tạo</th>
                                        <th>Mo Id</th>
                                        <th>Mã lỗi</th>
                                        <th>Mô tả lỗi</th>
                                    </tr>
                                </thead>
                                <tbody class="table-border-bottom-0">
                                    <c:forEach var="s" items="${smsList}">
                                        <tr>
                                            <td>${s.msisdn}</td>
                                            <td>${s.shortCode}</td>
                                            <td>${s.price}</td>
                                            <td><fmt:formatDate value="${s.createDate}" pattern="dd/MM/yyyy"/></td>
                                            <td>${s.moId}</td>
                                            <td>${k.errorCode}</td>
                                            <td>${k.errorDesc}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>

                            <div style="float: right; margin-right: 10%">
                                <c:if test="${action.equals('list')}">
                                    <nav aria-label="Page navigation">
                                        <ul class="pagination">
                                            <li class="page-item first">
                                                <a class="page-link" href="smsMtLogList?page=1&action=list"
                                                   ><i class="tf-icon bx bx-chevrons-left"></i
                                                    ></a>
                                            </li>
                                            <li class="page-item prev">
                                                <a class="page-link" href="smsMtLogList?page=${(page==1)?1:(page-1)}&action=list"
                                                   ><i class="tf-icon bx bx-chevron-left"></i
                                                    ></a>
                                            </li>
                                            <c:forEach var="i" begin="${startDisplayPage}" end="${endDisplayPage}">
                                                <li class="page-item ${i==page?"active":""}">
                                                    <a class="page-link" href="smsMtLogList?page=${i}&action=list">
                                                        ${i}
                                                    </a>
                                                </li>
                                            </c:forEach>
                                            <li class="page-item next">
                                                <a class="page-link" href="smsMtLogList?page=${(page==endPage)?endPage:(page+1)}&action=list"
                                                   ><i class="tf-icon bx bx-chevron-right"></i
                                                    ></a>
                                            </li>
                                            <li class="page-item last">
                                                <a class="page-link" href="smsMtLogList?page=${endPage}&action=list"
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
                                                <a class="page-link" href="searchSmsMtLog?page=1&action=search&inputSearch=${inputSearch}&fromCreateDate=${fromCreateDate}&toCreateDate=${toCreateDate}"
                                                   ><i class="tf-icon bx bx-chevrons-left"></i
                                                    ></a>
                                            </li>
                                            <li class="page-item prev">
                                                <a class="page-link" href="searchSmsMtLog?page=${(page==1)?1:(page-1)}&action=search&inputSearch=${inputSearch}&fromCreateDate=${fromCreateDate}&toCreateDate=${toCreateDate}"
                                                   ><i class="tf-icon bx bx-chevron-left"></i
                                                    ></a>
                                            </li>
                                            <c:forEach var="i" begin="${startDisplayPage}" end="${endDisplayPage}">
                                                <li class="page-item ${i==page?"active":""}">
                                                    <a class="page-link" href="searchSmsMtLog?page=${i}&action=search&inputSearch=${inputSearch}&fromCreateDate=${fromCreateDate}&toCreateDate=${toCreateDate}">
                                                        ${i}
                                                    </a>
                                                </li>
                                            </c:forEach>
                                            <li class="page-item next">
                                                <a class="page-link" href="searchSmsMtLog?page=${(page==endPage)?endPage:(page+1)}&action=search&inputSearch=${inputSearch}&fromCreateDate=${fromCreateDate}&toCreateDate=${toCreateDate}"
                                                   ><i class="tf-icon bx bx-chevron-right"></i
                                                    ></a>
                                            </li>
                                            <li class="page-item last">
                                                <a class="page-link" href="searchSmsMtLog?page=${endPage}&action=search&inputSearch=${inputSearch}&fromCreateDate=${fromCreateDate}&toCreateDate=${toCreateDate}"
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
                                        $(document).ready(function () {
                                            $('#table1').DataTable();
                                        });
            </script>
            <script>
                function resetFormSearch() {
                    document.getElementById("inputSearch").value = "";
                    document.getElementById("fromCreateDate").value = "";
                    document.getElementById("toCreateDate").value = "";
                }
                ;

            </script>
            <style>
                .dataTables_filter, .dataTables_info {
                    display: none;
                }
            </style>
    </body>
</html>
