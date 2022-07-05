<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!-- =========================================================
* Sneat - Bootstrap 5 HTML Admin Template - Pro | v1.0.0
==============================================================

* Product Page: https://themeselection.com/products/sneat-bootstrap-html-admin-template/
* Created by: ThemeSelection
* License: You must have a valid license purchased in order to legally use the theme for your project.
* Copyright ThemeSelection (https://themeselection.com)

=========================================================
-->
<!-- beautify ignore:start -->
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

        <title>Add And Update Group</title>

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

        <!-- Page CSS -->

        <!-- Helpers -->
        <script src="${pageContext.request.contextPath}/assets/vendor/js/helpers.js"></script>

        <!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
        <!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
        <script src="${pageContext.request.contextPath}/assets/js/config.js"></script>

        <!-- Select Multiple -->


    </head>

    <body>
        <!-- Layout wrapper -->
        <div class="layout-wrapper layout-content-navbar">
            <div class="layout-container">
                <!-- Menu -->

                <%@include file="sidebar.jsp" %>
                <!-- / Menu -->

                <!-- Layout container -->
                <div class="layout-page">
                    <!-- Navbar -->



                    <!-- / Navbar -->

                    <!-- Content wrapper -->
                    <div class="content-wrapper">
                        <!-- Content -->

                        <div class="container-xxl flex-grow-1 container-p-y">
                            <c:if test="${action.equals('add')}">
                                <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light"></span> Thêm Nhóm</h4>
                            </c:if>
                            <c:if test="${action.equals('update')}">
                                <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light"></span> Cập Nhật Nhóm</h4>
                            </c:if>
                            <!-- Basic Layout -->
                            <div class="row">

                                <div class="col-xl-12">
                                    <ul class="nav nav-pills flex-column flex-md-row mb-3">
                                        <li class="nav-item">
                                            <a class="nav-link active" href="groupList?action=list">Quay lại dang sách nhóm</a>
                                        </li>

                                    </ul>
                                    <div class="card mb-4">
                                        <div class="card-header d-flex justify-content-between align-items-center">
                                            <h5 class="mb-0">Thông tin</h5>
                                            <small class="text-muted float-end"></small>
                                        </div>
                                        <div class="card-body">
                                            <div class="row">

                                                <div class="col-md-6">
                                                    <c:if test="${notice!=null}">
                                                        ${notice}
                                                    </c:if>
                                                    <c:if test="${action.equals('add')}">
                                                        <form action="addGroup" method="post" enctype="multipart/form-data">
                                                        </c:if>
                                                        <c:if test="${action.equals('update')}">
                                                            <form action="updateGroup" method="post" enctype="multipart/form-data">

                                                                <input type="hidden" name="id" value="${id}"/>
                                                                <input type="hidden" name="oldname" value="${oldname}"/>
                                                            </c:if>
                                                            <input type="hidden" name="action" value="${action}"/>
                                                            <div class="mb-3">
                                                                <label class="form-label" for="basic-default-name">Tên</label>
                                                                <input type="text" class="form-control" id="basic-default-name"
                                                                       name="name" placeholder="" required="" value="${name}"/>
                                                            </div>

                                                            <div class="mb-3">
                                                                <label class="form-label" for="exampleFormControlTextarea1">Mô Tả</label>
                                                                <input type="text" class="form-control" id="description"
                                                                       name="description" placeholder=""  value="${description}"/>
                                                            </div>

                                                            <c:if test="${action.equals('add')}">

                                                                <button type="submit" class="btn btn-primary">Thêm</button>
                                                            </c:if>
                                                            <c:if test="${action.equals('update')}">

                                                                <button type="submit" class="btn btn-primary">Cập Nhật</button>
                                                            </c:if>
                                                        </form>
                                                        <c:if test="${action.equals('update')}">
                                                            <div class="mb-3"></div>
                                                            <div class="col-lg-6 mb-4 mb-xl-0">
                                                                <h5 class="text-light fw-semibold">Thành viên</h5>
                                                                <div class="demo-inline-spacing mt-3">
                                                                    <div class="list-group">
                                                                        <c:forEach var="u" items="${usersInGr}">
                                                                            <a href="javascript:void(0);" class="list-group-item list-group-item-action "
                                                                               >${u.userFullname}</a
                                                                            >
                                                                        </c:forEach>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </c:if>
                                                </div>

                                                <div class="col-md-6">
                                                    <div class="card-body">
                                                        <c:if test="${action.equals('update')}">
                                                            <div class="mb-3">
                                                                <h5>Cập Nhật Thành Viên</h5>
                                                                <form action="updateMemberForGroup" method="post">
                                                                    <input type="hidden" name="groupId" value="${id}" />
                                                                    <select id="selectMemberMultiple" name="membersId" multiple class="selectpicker"
                                                                            data-live-search="true" data-actions-box="true">
                                                                            <c:forEach var="u" items="${users}">
                                                                                    <option <c:if test="${usernames.contains(u.userName)}">selected="</c:if>" value="${u.userId}">
                                                                                        ${u.userFullname}
                                                                                    </option>
                                                                            </c:forEach>
                                                                    </select>
                                                                    <button type="submit" class="btn btn-primary">Lưu thay đổi thành viên</button>
                                                                </form>
                                                            </div>
                                                            <div class="mb-3">
                                                                <h5>Cập Nhật Quyền</h5>
                                                                <form action="updateRoleForGroup" method="post">
                                                                    <input type="hidden" name="groupId" value="${id}" />
                                                                    <select id="selectRoleMultiple" name="rolesId" multiple class="selectpicker"
                                                                            data-live-search="true" data-actions-box="true">
                                                                        <c:forEach var="r" items="${roles}">
                                                                            <option <c:if test="${rolenames.contains(r.name)}">selected</c:if> value="${r.id}">
                                                                                ${r.name}
                                                                            </option>
                                                                        </c:forEach>
                                                                    </select>
                                                                    <button type="submit" class="btn btn-primary">Lưu thay đổi quyền</button>
                                                                </form>
                                                            </div>
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- / Content -->

                            <!-- Footer -->
                            <footer class="content-footer footer bg-footer-theme">
                                <div class="container-xxl d-flex flex-wrap justify-content-between py-2 flex-md-row flex-column">
                                    <div class="mb-2 mb-md-0">
                                        ©
                                        <script>
                                            document.write(new Date().getFullYear());
                                        </script>
                                        , made with ?? by
                                        <a href="https://themeselection.com" target="_blank" class="footer-link fw-bolder">ThemeSelection</a>
                                    </div>
                                    <div>
                                        <a href="https://themeselection.com/license/" class="footer-link me-4" target="_blank">License</a>
                                        <a href="https://themeselection.com/" target="_blank" class="footer-link me-4">More Themes</a>

                                        <a
                                            href="https://themeselection.com/demo/sneat-bootstrap-html-admin-template/documentation/"
                                            target="_blank"
                                            class="footer-link me-4"
                                            >Documentation</a
                                        >

                                        <a
                                            href="https://github.com/themeselection/sneat-html-admin-template-free/issues"
                                            target="_blank"
                                            class="footer-link me-4"
                                            >Support</a
                                        >
                                    </div>
                                </div>
                            </footer>
                            <!-- / Footer -->

                            <div class="content-backdrop fade"></div>
                        </div>
                        <!-- Content wrapper -->
                    </div>
                    <!-- / Layout page -->
                </div>

                <!-- Overlay -->
                <div class="layout-overlay layout-menu-toggle"></div>
            </div>
            <!-- / Layout wrapper -->



            <!-- Core JS -->
            <!-- build:js assets/vendor/js/core.js -->
            <script>
                $(document).ready(function () {
                    $('#selectMemberMultiple').multiselect({
                        includeSelectAllOption: true,
                    });
                });
                $(document).ready(function () {
                    $('#selectRoleMultiple').multiselect({
                        includeSelectAllOption: true
                    });
                });
            </script>


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
            <!--JS for bootstrap selectpicker-->

            <!-- Place this tag in your head or just before your close body tag. -->
            <script async defer src="https://buttons.github.io/buttons.js"></script>
    </body>
</html>
