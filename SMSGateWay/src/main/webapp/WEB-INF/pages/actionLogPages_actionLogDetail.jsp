<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

        <title>My Profile</title>

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
        <style>
            .form-label{
                font-weight: bolder;
                font-size: medium;
            }
        </style>
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
                            <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">Th??ng Tin Log /</span> Log</h4>

                            <div class="row">
                                <div class="col-md-12">
                                    <ul class="nav nav-pills flex-column flex-md-row mb-3">
                                        <li class="nav-item">
                                            <a class="nav-link active" href="actionLogList?action=list&page=${page}"><i class="bx bx-user me-1"></i> Quay L???i</a>
                                        </li>

                                    </ul>
                                    <div class="card mb-4">
                                        <h5 class="card-header">Chi Ti???t Log</h5>
                                        <hr class="my-0" />
                                        <div class="card-body">
                                                <div class="d-flex align-items-start align-items-sm-center gap-4">
                                                </div>
                                                <div class="row">
                                                    <div class="mb-3 col-md-6">
                                                        <label for="userName" class="form-label">Ng?????i D??ng</label>
                                                        <p>${log.userName}</p>
                                                    </div>
                                                    <div class="mb-3 col-md-6">
                                                        <label for="actionlogName" class="form-label">T??n Log</label>
                                                        <p>${log.actionlogName}</p>
                                                    </div>
                                                    <div class="mb-3 col-md-6">
                                                        <label for="actionlogObjectType" class="form-label">?????i T?????ng</label>
                                                        <p>${log.actionlogObjectType}</p>
                                                    </div>
                                                    <div class="mb-3 col-md-6">
                                                        <label for="actionlogObjectId" class="form-label">ID c???a ?????i T?????ng</label>
                                                        <p>${log.actionlogObjectId}</p>
                                                    </div>
                                                    <div class="mb-3 col-md-6">
                                                        <label for="actionlogIp" class="form-label">IP Ng?????i D??ng</label>
                                                        <p>${log.actionlogIp}</p>
                                                    </div>
                                                    <div class="mb-3 col-md-6">
                                                        <label for="actionlogOs" class="form-label">H??? ??i???u H??nh</label>
                                                        <p>${log.actionlogOs}</p>
                                                    </div>
                                                    <div class="mb-3 col-md-6">
                                                        <label for="actionlogApp" class="form-label">Tr??nh Duy???t</label>
                                                        <p>${log.actionlogApp}</p>
                                                    </div>
                                                    <div class="mb-3 col-md-6">
                                                        <label for="actionlogDevice" class="form-label">Thi???t b???</label>
                                                        <p>${log.actionlogDevice}</p>
                                                    </div>
                                                    <div class="mb-3 col-md-6">
                                                        <label for="actionlogTime" class="form-label">Th???i Gian Log</label>
                                                        <p><fmt:formatDate value="${log.actionlogTime}" pattern="dd/MM/yyyy HH:mm:ss"/></p>
                                                    </div>
                                                    <div class="mb-3 col-md-6">
                                                        <label for="actionlogResult" class="form-label">K???t Qu???</label>
                                                        <p>${log.actionlogResult}</p>
                                                    </div>
                                                    <div class="mb-3 col-md-6">
                                                        <label for="actionlogDesc" class="form-label">M?? T???</label>
                                                        <p>${log.actionlogDesc}</p>
                                                    </div>
                                                    <div class="mb-3 col-md-6">
                                                        <label for="actionlogData" class="form-label">D??? Li???u Log</label>
                                                        <p>${log.actionlogData}</p>
                                                    </div>
                                                    <div class="mb-3 col-md-6">
                                                        <label for="actionlogMsisdn" class="form-label">MSISDN</label>
                                                        <p>${log.actionlogMsisdn}</p>
                                                    </div>
                                                </div>


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
