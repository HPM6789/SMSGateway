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

        <title>Add And Update Shortcode</title>

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
                            <c:if test="${action.equals('add')}">
                                <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light"></span> Thêm Đầu Số</h4>
                            </c:if>
                            <c:if test="${action.equals('update')}">
                                <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light"></span> Câp Nhật Đầu Số</h4>
                            </c:if>
                            <!-- Basic Layout -->
                            <div class="row">
                                <div class="col-xl-3"></div>
                                <div class="col-xl-6">
                                    <div class="card mb-4">
                                        <div class="card-header d-flex justify-content-between align-items-center">
                                            <h5 class="mb-0">Thông Tin</h5>
                                            <small class="text-muted float-end"></small>
                                        </div>
                                        <div class="card-body">
                                            <c:if test="${notice!=null}">
                                                ${notice}
                                            </c:if>
                                            <c:if test="${action.equals('add')}">
                                                <form action="addShortcode" method="post">
                                                </c:if>
                                                <c:if test="${action.equals('update')}">
                                                    <form action="updateShortcode" method="post">

                                                        <input type="hidden" name="shcodeId" value="${shcodeId}"/>
                                                        <input type="hidden" name="oldshortcode" value="${oldshortcode}"/>
                                                    </c:if>
                                                    <input type="hidden" name="action" value="${action}"/>

                                                    <div class="mb-3 col-md-12">
                                                        <label for="shortcode" class="form-label">Đầu Số</label>
                                                        <p id="errorShortcode"></p>
                                                        <input
                                                            class="form-control"
                                                            type="text"
                                                            id="shortcode"
                                                            name="shortcode"
                                                            value="${shortcode}"
                                                            autofocus onchange="validateShortcode()" required=""
                                                            />
                                                    </div>
                                                    <div class="mb-3 col-md-12">
                                                        <label for="price" class="form-label">Giá</label>
                                                        <p id="errorPrice"></p>
                                                        <input
                                                            class="form-control"
                                                            type="text"
                                                            id="price"
                                                            name="price"
                                                            value="${price}"
                                                            autofocus onchange="validatePrice()" required=""
                                                            />
                                                    </div>
                                                    <div class="mb-3 col-md-12">
                                                        <label for="limitedMtNo" class="form-label">Giới hạn số lượng Mt</label>
                                                        <p id="errorLimitMtNo"></p>
                                                        <input
                                                            class="form-control"
                                                            type="text"
                                                            id="limitedMtNo"
                                                            name="limitedMtNo"
                                                            value="${limitedMtNo}"
                                                            autofocus onchange="validateLimitMtNo()" required=""
                                                            />
                                                    </div>
                                                    <c:if test="${action.equals('update')}">
                                                        <div class="mb-3 col-md-12">
                                                            <span>
                                                            <label class="form-label" for="basic-default-Status">Trạng Thái:</label>&nbsp;&nbsp;&nbsp;
                                                            
                                                                 <c:if test="${status == 1}">Active</c:if>
                                                                 <c:if test="${status == 0}">Inactive</c:if>
                                                            </span>
                                                        </div>
                                                        <div class="mb-3 col-md-12">
                                                            <span>
                                                                <label for="" class="form-label">Ngày Tạo:</label>&nbsp;&nbsp;&nbsp;
                                                                <fmt:formatDate value="${createTime}" pattern="dd/MM/yyyy HH:mm:ss"/>
                                                            </span>
                                                        </div>
                                                        <div class="mb-3 col-md-12">
                                                            <span>
                                                                <label for="" class="form-label">Ngày Cập Nhật:</label>&nbsp;&nbsp;&nbsp;
                                                                <c:if test="${updateTime != null}">
                                                                    <fmt:formatDate value="${updateTime}" pattern="dd/MM/yyyy HH:mm:ss"/>
                                                                </c:if>
                                                                <c:if test="${updateTime == null}">
                                                                    None
                                                                </c:if>
                                                            </span>
                                                        </div>
                                                        <div class="mb-3 col-md-12">
                                                            <span>
                                                                <label for="" class="form-label">Ngày Duyệt:</label>&nbsp;&nbsp;&nbsp;
                                                                <c:if test="${approveTime != null}">
                                                                    <fmt:formatDate value="${approveTime}" pattern="dd/MM/yyyy HH:mm:ss"/>
                                                                </c:if>
                                                                <c:if test="${approveTime == null}">
                                                                    None
                                                                </c:if>
                                                            </span>
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${action.equals('add')}">
                                                        <input type="hidden" name="creatorId" value="${sessionScope.user.userId}"/>
                                                    </c:if>
                                                    <div class="mb-3 col-md-12">
                                                        <span>
                                                        <label for="" class="form-label">Người Tạo:</label>&nbsp;&nbsp;&nbsp;
                                                        ${sessionScope.user.userFullname}
                                                        </span>
                                                    </div>
                                                    <c:if test="${action.equals('add')}">
                                                        <button type="submit" class="btn btn-primary">Thêm</button>
                                                    </c:if>
                                                    <c:if test="${action.equals('update')}">
                                                        <button type="submit" class="btn btn-primary">Cập Nhật</button>
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
            function validateShortcode() {
                var shortcode = document.getElementById("shortcode").value;
                var regex = "^[0-9]{1,8}$";
                if (!shortcode.match(regex) && shortcode !== null && shortcode !== "") {
                    document.getElementById("errorShortcode").innerHTML = "Invalid shortcode";
                    document.getElementById("errorShortcode").style = "color: red";
                    document.getElementById("shortcode").style = "border-color: red";
                } else {
                    document.getElementById("errorShortcode").innerHTML = "";
                    document.getElementById("errorShortcode").style = "color: ";
                    document.getElementById("shortcode").style = "border-color:";
                }
            }
            function validatePrice() {
                var shortcode = document.getElementById("price").value;
                var regex = "^[0-9]+$";
                if (!shortcode.match(regex) && shortcode != null && shortcode !== "") {
                    document.getElementById("errorPrice").innerHTML = "Invalid price";
                    document.getElementById("errorPrice").style = "color: red";
                    document.getElementById("price").style = "border-color: red";
                } else {
                    document.getElementById("errorPrice").innerHTML = "";
                    document.getElementById("errorPrice").style = "color: ";
                    document.getElementById("price").style = "border-color:";
                }
            }
            function validateLimitMtNo() {
                var shortcode = document.getElementById("limitedMtNo").value;
                var regex = "^[0-9]+$";
                if (!shortcode.match(regex) && shortcode != null && shortcode !== "") {
                    document.getElementById("errorLimitMtNo").innerHTML = "Invalid limited Mt No";
                    document.getElementById("errorLimitMtNo").style = "color: red";
                    document.getElementById("limitedMtNo").style = "border-color: red";
                } else {
                    document.getElementById("errorLimitMtNo").innerHTML = "";
                    document.getElementById("errorLimitMtNo").style = "color: ";
                    document.getElementById("limitedMtNo").style = "border-color:";
                }
            }
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
