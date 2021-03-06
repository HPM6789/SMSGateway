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

        <title>User Detail</title>

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

        <!-- Page CSS -->

        <!-- Helpers -->
        <script src="${pageContext.request.contextPath}/assets/vendor/js/helpers.js"></script>

        <!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
        <!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
        <script src="${pageContext.request.contextPath}/assets/js/config.js"></script>
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
                            <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">Th??ng Tin T??i Kho???n /</span> T??i Kho???n</h4>

                            <div class="row">
                                <div class="col-md-12">
                                    <ul class="nav nav-pills flex-column flex-md-row mb-3">
                                        <li class="nav-item">
                                            <a class="nav-link active" href="userList?action=list&page=${page}"><i class="bx bx-user me-1"></i> Quay L???i</a>
                                        </li>

                                    </ul>
                                    <div class="card mb-4">
                                        <h5 class="card-header">Chi Ti???t Ng?????i D??ng</h5>
                                        <!-- Account -->
                                        <div class="card-body">
                                            <c:if test="${success != null}">
                                                ${success}
                                            </c:if>
                                        </div>
                                        <hr class="my-0" />
                                        <div class="card-body">
                                            <form id="formAccountSettings" action="userDetail" method="POST">
                                                <div class="d-flex align-items-start align-items-sm-center gap-4">

                                                    <input type="hidden" name="oldemail" value="${email}"/>
                                                    <input type="hidden" name="userId" value="${userId}"/>
                                                    <input type="hidden" name="page" value="${page}"/>
                                                    <div class="button-wrapper">

                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="mb-3 col-md-6">
                                                        <label for="username" class="form-label">T??n Ng?????i D??ng</label>
                                                        <input
                                                            class="form-control"
                                                            type="text"
                                                            id="username"
                                                            name="username"
                                                            value="${username}"
                                                            autofocus readonly=""
                                                            />
                                                    </div>
                                                    <div class="mb-3 col-md-6">
                                                        <label for="fullname" class="form-label">H??? T??n</label>
                                                        <input
                                                            class="form-control"
                                                            type="text"
                                                            id="firstName"
                                                            name="fullname"
                                                            value="${fullname}"
                                                            autofocus required=""
                                                            />
                                                    </div>
                                                    <div class="mb-3 col-md-6">
                                                        <c:if test="${errorEmail != null}">
                                                            ${errorEmail}
                                                        </c:if>
                                                        <label for="email" class="form-label">E-mail</label>
                                                        <input
                                                            class="form-control"
                                                            type="text"
                                                            id="email"
                                                            name="email"
                                                            value="${email}"
                                                            placeholder="john.doe@example.com"
                                                            />
                                                    </div>
                                                    <div class="mb-3 col-md-6">
                                                        <label class="form-label" for="basic-default-status">Tr???ng Th??i</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                        <input type="radio"  id="basic-default-active" name="status" value="1" required=""
                                                               <c:if test="${status == 1}">checked=""</c:if>/>&nbsp;Active&nbsp;&nbsp;
                                                               <input type="radio"  id="basic-default-inactive" name="status" value="0" 
                                                               <c:if test="${status == 0}">checked=""</c:if>/>&nbsp;Inactive
                                                        </div>
                                                        <div class="mb-3 col-md-6">
                                                            <label class="form-label" for="basic-default-isSuper">Si??u Ng?????i D??ng</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                            <input type="radio"  id="basic-default-rootUser" name="isSuper" value="1" required=""
                                                            <c:if test="${isSuper == 1}">checked=""</c:if>/>&nbsp;Root User&nbsp;&nbsp;
                                                            <input type="radio"  id="basic-default-normalUser" name="isSuper" value="0" 
                                                            <c:if test="${isSuper == 0}">checked=""</c:if>/>&nbsp;Normal User

                                                        </div>
                                                        <div class="mb-3 col-md-6">
                                                            <label class="form-label" for="basic-default-userType">Lo???i Ng?????i D??ng</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                            <input type="radio"  id="basic-default-cp" name="userType" value="1" 
                                                            <c:if test="${userType == 1}">checked=""</c:if>/>&nbsp;CP&nbsp;&nbsp;
                                                            <input type="radio"  id="basic-default-vnp" name="userType" value="0" 
                                                            <c:if test="${userType == 2}">checked=""</c:if>/>&nbsp;VNP

                                                        </div>
                                                        <div class="mb-3 col-md-6">
                                                            <label for="address" class="form-label">?????a ch???</label>
                                                            <input
                                                                class="form-control"
                                                                type="text"
                                                                id="address"
                                                                name="address"
                                                                value="${address}"
                                                            autofocus required=""
                                                            />
                                                    </div>
                                                    <div class="mb-3 col-md-6">
                                                        <label class="form-label" for="phoneNumber">??i???n Tho???i</label>
                                                        <div class="input-group input-group-merge">
                                                            <input
                                                                type="text"
                                                                id="phoneNumber"
                                                                name="phone"
                                                                class="form-control" value="${phone}"
                                                                placeholder="202 555 0111"
                                                                />
                                                        </div>
                                                    </div>
                                                    <div class="mb-3 col-md-6">
                                                        <label for="createdTime" class="form-label">Ng??y T???o</label>
                                                        <input
                                                            class="form-control"
                                                            type="text"
                                                            id="address"
                                                            name="createdTime"
                                                            value="${createdTime}"
                                                            autofocus readonly=""
                                                            />
                                                    </div>
                                                    <div class="mb-3 col-md-6">
                                                        <label for="updatedTime" class="form-label">Ng??y C???p Nh???t</label>
                                                        <input
                                                            class="form-control"
                                                            type="text"
                                                            id="updatedTime"
                                                            name="updatedTime"
                                                            value="${updatedTime}"
                                                            autofocus readonly=""
                                                            />
                                                    </div>
                                                    <div class="mb-3 col-md-6">
                                                        <label for="lastTimeLogin" class="form-label">L???n cu???i ????ng nh???p</label>
                                                        <input
                                                            class="form-control"
                                                            type="text"
                                                            id="lastTimeLogin"
                                                            name="lastTimeLogin"
                                                            value="${lastTimeLogin}"
                                                            autofocus readonly=""
                                                            />
                                                    </div>
                                                    <div class="mb-3 col-md-6">
                                                        <label class="form-label" for="basic-default-optFlag">OTP Flag</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                        <input type="radio"  id="basic-default-otp" name="optFlag" value="1" 
                                                               <c:if test="${optFlag == 1}">checked=""</c:if>/>&nbsp;Need OTP&nbsp;&nbsp;
                                                               <input type="radio"  id="basic-default-noOtp" name="optFlag" value="0" 
                                                               <c:if test="${optFlag == 0}">checked=""</c:if>/>&nbsp;No need OTP

                                                    </div>
                                                    <div class="mb-3 col-md-6">
                                                        <label for="cp" class="form-label">?????i T??c</label>
                                                        <input type="hidden" name="cpId" value="${cpId}"/>
                                                    <input
                                                        class="form-control"
                                                        type="text"
                                                        id="cp"
                                                        name="cpName"
                                                        value="${cpName}"
                                                        autofocus readonly=""
                                                        />
                                                    </div>
                                                </div>

                                                <div class="mb-3 col-md-6">
                                                    <label class="form-label" for="description">M?? T???</label>

                                                    <input type="text" name="description" value="${description}" class="form-control"/>
                                                </div>
                                                <div class="mb-3 col-md-6">
                                                    <div class="table-responsive text-nowrap">
                                                        <table class="table" id="table1">
                                                            <thead class="table-light">
                                                                <tr>
                                                                    <th>Nh??m</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody class="table-border-bottom-0">
                                                                <c:forEach var="g" items="${groups}">
                                                                    <tr>
                                                                        <td>
                                                                            ${g.name}
                                                                        </td>
                                                                    </tr>
                                                                </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                                <div class="mb-3 col-md-6">

                                                </div>

                                                <div class="mt-2">
                                                    <button type="submit" class="btn btn-primary me-2">L??u thay ?????i</button>

                                                </div>
                                            </form>
                                        </div>
                                        <!-- /Account -->
                                    </div>

                                </div>
                            </div>
                        </div>
                        <!-- / Content -->

                        <!-- Footer -->
                        <footer class="content-footer footer bg-footer-theme">
                            <div class="container-xxl d-flex flex-wrap justify-content-between py-2 flex-md-row flex-column">
                                <div class="mb-2 mb-md-0">
                                    ??
                                    <script>
                                        document.write(new Date().getFullYear());
                                    </script>
                                    <script>
                                        function RemoveTextAreaWhiteSpace() {
                                            var myTxtArea = document.getElementById("description");
                                            myTxtArea.value = myTxtArea.value.replace(/^\s*|\s*$/g, "");
                                        }
                                        }
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
        <script src="${pageContext.request.contextPath}/assets/js/pages-account-settings-account.js"></script>

        <!-- Place this tag in your head or just before your close body tag. -->
        <script async defer src="https://buttons.github.io/buttons.js"></script>
    </body>
</html>
