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

        <title>Danh Sách Đối Tác</title>

        <meta name="description" content="" />
        <!--Bootstrap for select-->

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
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
                        <form action="searchCP" method="get" style="" name="" id="formSearch">
                            <div class="row">
                                <div class="mb-3 col-md-2">
                                    <input type="hidden" name="action" value="search"/>
                                    <input type="text" name="inputSearch" placeholder="Tên/Mã Đối Tác"
                                           id="inputSearch" class="form-control" value="${inputSearch}"/>
                                </div>

                                <div class="mb-3 col-md-2">
                                    <input type="submit" value="Tìm kiếm" class="btn btn-primary me-2"/>
                                </div>

                                <div class="mb-3 col-md-2">
                                    <button class="btn btn-secondary" type="button" onclick="resetFormSearch()">Reset</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <!-- Bootstrap Table with Header - Light -->
                    <div class="card">
                        <div class="row">
                            <div class="col-md-2" style="margin-top: 10px; margin-left: 10px">
                                <a href="addCP?action=add" style="color: inherit; text-decoration: none">
                                    <button class="btn btn-primary me-2">
                                        Thêm Đối Tác
                                    </button>
                                </a>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-md-3" style="text-align: center">
                                <c:if test="${notice!=null}">
                                    <div class="alert alert-success" role="alert">${notice}</div>
                                </c:if>
                            </div>
                        </div>
                        <h5 class="card-header">Danh sách đối tác</h5>
                        <div class="table-responsive text-nowrap">
                            <table class="table" id="">
                                <thead class="table-light">
                                    <tr>
                                        <th>STT</th>
                                        <th>Tên</th>
                                        <th>Mã</th>
                                        <th>Ngày Khởi Tạo</th>
                                        <th>Cập nhật đầu số</th>
                                        <th>Đại Diện</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody class="table-border-bottom-0">
                                    <c:forEach var="i" begin="1" end="${cpLists.size()}">
                                        <tr>
                                            <td>${i+start}</td>
                                            <td>
                                                ${cpLists.get(i-1).cpName}
                                            </td>
                                            <td>${cpLists.get(i-1).cpCode}</td>
                                            <td>
                                                <fmt:formatDate value="${cpLists.get(i-1).createdTime}" pattern="dd/MM/yyyy"/>
                                            </td>
                                            <td>
<!--                                                <a href="updateShortcodeForCp?cpId=${cpLists.get(i-1).cpId}&page=${page}" style="color: inherit; text-decoration: none">
                                                    <button class="btn btn-primary me-2">
                                                        Cập Nhật Đầu Số
                                                    </button>
                                                </a>-->
                                                
                                                <form action="updateShortcodeForCp" method="post">
                                                    <input type="hidden" name="cpId" value="${cpLists.get(i-1).cpId}"/>
                                                    <input type="hidden" name="page" value="${page}"/>
                                                    <select id="" name="shcodeId" multiple class="selectpicker"
                                                            data-live-search="true" data-actions-box="true">
                                                        <c:forEach var="sc" items="${shortcodes}">
                                                            <option <c:if test="${listOfListShcode.get(i-1).contains(sc.shortcode)}">selected=""</c:if> value="${sc.shcodeId}">
                                                                ${sc.shortcode}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                    <button type="submit" class="btn btn-primary">Cập Nhật</button>
                                                </form>
                                            </td>
                                            <td>${cpLists.get(i-1).representer}</td>

                                            <td>
                                                <div class="dropdown">
                                                    <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">
                                                        <i class="bx bx-dots-vertical-rounded"></i>
                                                    </button>
                                                    <div class="dropdown-menu">
                                                        <a class="dropdown-item"
                                                           href="updateCP?action=update&cpId=${cpLists.get(i-1).cpId}"
                                                           ><i class="bx bx-edit-alt me-1"></i> Sửa</a
                                                        >
                                                        <a class="dropdown-item" href="#"
                                                           ><i class="bx bx-trash me-1"></i> Xóa</a
                                                        >
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
                                                <a class="page-link" href="cpList?page=1&action=list"
                                                   ><i class="tf-icon bx bx-chevrons-left"></i
                                                    ></a>
                                            </li>
                                            <li class="page-item prev">
                                                <a class="page-link" href="cpList?page=${(page==1)?1:(page-1)}&action=list"
                                                   ><i class="tf-icon bx bx-chevron-left"></i
                                                    ></a>
                                            </li>
                                            <c:forEach var="i" begin="${startDisplayPage}" end="${endDisplayPage}">
                                                <li class="page-item ${i==page?"active":""}">
                                                    <a class="page-link" href="cpList?page=${i}&action=list">
                                                        ${i}
                                                    </a>
                                                </li>
                                            </c:forEach>
                                            <li class="page-item next">
                                                <a class="page-link" href="cpList?page=${(page==endPage)?endPage:(page+1)}&action=list"
                                                   ><i class="tf-icon bx bx-chevron-right"></i
                                                    ></a>
                                            </li>
                                            <li class="page-item last">
                                                <a class="page-link" href="cpList?page=${endPage}&action=list"
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
                                                <a class="page-link" href="searchCP?page=1&inputSearch=${inputSearch}&action=search"
                                                   ><i class="tf-icon bx bx-chevrons-left"></i
                                                    ></a>
                                            </li>
                                            <li class="page-item prev">
                                                <a class="page-link" href="searchCP?page=${(page==1)?1:(page-1)}&inputSearch=${inputSearch}&action=search"
                                                   ><i class="tf-icon bx bx-chevron-left"></i
                                                    ></a>
                                            </li>
                                            <c:forEach var="i" begin="${startDisplayPage}" end="${endDisplayPage}">
                                                <li class="page-item ${i==page?"active":""}">
                                                    <a class="page-link" href="searchCP?page=${i}&inputSearch=${inputSearch}&action=search">
                                                        ${i}
                                                    </a>
                                                </li>
                                            </c:forEach>
                                            <li class="page-item next">
                                                <a class="page-link" href="searchCP?page=${(page==endPage)?endPage:(page+1)}&inputSearch=${inputSearch}&action=search"
                                                   ><i class="tf-icon bx bx-chevron-right"></i
                                                    ></a>
                                            </li>
                                            <li class="page-item last">
                                                <a class="page-link" href="searchCP?page=${endPage}&inputSearch=${inputSearch}&action=search"
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
                }
                ;
                $(document).ready(function () {
                    $('#selectMemberMultiple').multiselect({
                        includeSelectAllOption: true,
                    });
                });
                const selects = document.document.querySelectorAll(".selectpicker");
                for (const select of selects) {
                    select.multiselect({
                        includeSelectAllOption: true,
                    });
                }
            </script>
            <style>
                .dataTables_filter, .dataTables_info {
                    display: none;
                }
            </style>
    </body>
</html>
