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

        <title>Add And Update CP</title>

        <meta name="description" content="" />
        <!--select searchbox-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
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
                                <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light"></span> Th??m ?????i T??c</h4>
                            </c:if>
                            <c:if test="${action.equals('update')}">
                                <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light"></span> C???p Nh???t ?????i T??c</h4>
                            </c:if>
                            <!-- Basic Layout -->
                            <div class="row">
                                <div class="col-xl-12">
                                    <div class="card mb-4">
                                        <div class="card-header d-flex justify-content-between align-items-center">
                                            <h5 class="mb-0">Th??ng Tin</h5>
                                            <small class="text-muted float-end"></small>
                                        </div>
                                        
                                        <div class="card-body">
                                            <c:if test="${notice!=null}">
                                                ${notice}
                                            </c:if>
                                            <c:if test="${action.equals('add')}">
                                                <form action="addCP" method="post">
                                                </c:if>
                                                <c:if test="${action.equals('update')}">
                                                    <form action="updateCP" method="post">

                                                        <input type="hidden" name="cpId" value="${cpId}"/>
                                                        <input type="hidden" name="oldcode" value="${oldcode}"/>
                                                    </c:if>
                                                    <input type="hidden" name="action" value="${action}"/>
                                                    <div class="row">
                                                        <div class="mb-3 col-md-6">
                                                            <label for="cpName" class="form-label">T??n ?????i T??c</label>
                                                            <input
                                                                class="form-control"
                                                                type="text"
                                                                id="cpName"
                                                                name="cpName"
                                                                value="${cpName}"
                                                                autofocus 
                                                                />
                                                        </div>
                                                        <div class="mb-3 col-md-6">
                                                            <label for="cpCode" class="form-label">M?? ?????i T??c</label>
                                                            <input
                                                                class="form-control"
                                                                type="text"
                                                                id="cpCode"
                                                                name="cpCode"
                                                                value="${cpCode}"
                                                                autofocus 
                                                                />
                                                        </div>
                                                        <div class="mb-3 col-md-6">
                                                            <label for="contact" class="form-label">Li??n h???</label>
                                                            <input
                                                                class="form-control"
                                                                type="text"
                                                                id="cpCode"
                                                                name="contact"
                                                                value="${contact}"
                                                                autofocus 
                                                                />
                                                        </div>

                                                        <div class="mb-3 col-md-6">
                                                            <label for="usernameMt" class="form-label">Username MT</label>
                                                            <input
                                                                class="form-control"
                                                                type="text"
                                                                id="usernameMt"
                                                                name="usernameMt"
                                                                value="${usernameMt}"
                                                                autofocus 
                                                                />
                                                        </div>
                                                        <div class="mb-3 col-md-6">
                                                            <div class="form-password-toggle">
                                                                <label for="passwordMt" class="form-label">M???t kh???u MT</label>
                                                                <div class="input-group">
                                                                    <input
                                                                        class="form-control"
                                                                        type="password"
                                                                        id="passwordMt"
                                                                        name="passwordMt"
                                                                        value="${passwordMt}"
                                                                        autofocus 
                                                                        />
                                                                    <span id="passwordMt" class="input-group-text cursor-pointer"
                                                                          ><i class="bx bx-hide"></i
                                                                        ></span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="mb-3 col-md-6">
                                                            <label for="listipMt" class="form-label">Danh s??ch IP MT</label>
                                                            <input
                                                                class="form-control"
                                                                type="text"
                                                                id="listipMt"
                                                                name="listipMt"
                                                                value="${listipMt}"
                                                                autofocus 
                                                                />
                                                        </div>
                                                        <div class="mb-3 col-md-6">
                                                            <label for="usernameCharge" class="form-label">Username tr??? c?????c</label>
                                                            <input
                                                                class="form-control"
                                                                type="text"
                                                                id="usernameCharge"
                                                                name="usernameCharge"
                                                                value="${usernameCharge}"
                                                                autofocus 
                                                                />
                                                        </div>
                                                        <div class="mb-3 col-md-6">
                                                            <div class="form-password-toggle">
                                                                <label for="passwordCharge" class="form-label">M???t kh???u tr??? c?????c</label>
                                                                <div class="input-group">
                                                                    <input
                                                                        class="form-control"
                                                                        type="password"

                                                                        name="passwordCharge"
                                                                        value="${passwordCharge}"
                                                                        autofocus aria-describedby="password"
                                                                        />
                                                                    <span id="passwordCharge" class="input-group-text cursor-pointer"
                                                                          ><i class="bx bx-hide"></i
                                                                        ></span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="mb-3 col-md-6">
                                                            <label for="taxCode" class="form-label">M?? S??? Thu???</label>
                                                            <input
                                                                class="form-control"
                                                                type="text"
                                                                id="taxCode"
                                                                name="taxCode"
                                                                value="${taxCode}"
                                                                autofocus 
                                                                />
                                                        </div>
                                                        <div class="mb-3 col-md-6">
                                                            <label for="representer" class="form-label">?????i di???n</label>
                                                            <input
                                                                class="form-control"
                                                                type="text"
                                                                id="representer"
                                                                name="representer"
                                                                value="${representer}"
                                                                autofocus 
                                                                />
                                                        </div>
                                                        <div class="mb-3 col-md-6">
                                                            <label for="address" class="form-label">?????a ch???</label>
                                                            <input
                                                                class="form-control"
                                                                type="text"
                                                                id="address"
                                                                name="address"
                                                                value="${address}"
                                                                autofocus 
                                                                />
                                                        </div>
                                                        <div class="mb-3 col-md-6">
                                                            <label for="emailCp" class="form-label">Email ?????i T??c</label>
                                                            <input
                                                                class="form-control"
                                                                type="text"
                                                                id="emailCp"
                                                                name="emailCp"
                                                                value="${emailCp}"
                                                                autofocus 
                                                                />
                                                        </div>
                                                    </div>
                                                    <c:if test="${action.equals('add')}">

                                                        <button type="submit" class="btn btn-primary">Th??m</button>
                                                    </c:if>
                                                    <c:if test="${action.equals('update')}">

                                                        <button type="submit" class="btn btn-primary">C???p Nh???t</button>
                                                    </c:if>
                                                </form>
                                        </div>
                                        <div class="card-body">

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
                                    ??
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
                $('#searchShortcode').multiselect({
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

        <!-- Place this tag in your head or just before your close body tag. -->
        <script async defer src="https://buttons.github.io/buttons.js"></script>
    </body>
</html>
