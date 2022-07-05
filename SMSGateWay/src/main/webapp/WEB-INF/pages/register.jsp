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

        <title>Register</title>

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
                            <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light"></span> Mẫu Đăng Ký</h4>

                            <!-- Basic Layout -->
                            <div class="row">
                                <div class="col-xl-3"></div>
                                <div class="col-xl-6">
                                    <div class="card mb-4">
                                        <div class="card-header d-flex justify-content-between align-items-center">
                                            <h5 class="mb-0">Thông Tin</h5>
                                            <small class="text-muted float-end"></small>
                                            <c:if test="${notice != null}">
                                                ${notice}
                                            </c:if>
                                        </div>
                                        <div class="card-body">
                                            <form action="register" method="post" enctype="multipart/form-data">
                                                <div class="mb-3">
                                                    <label class="form-label" for="basic-default-firstname">Họ Tên</label>
                                                    <input type="text" class="form-control" id="basic-default-fullname"
                                                           name="fullname" placeholder="" required="" value="${fullname}"/>
                                                </div>
                                                
                                                <div class="mb-3">
                                                    <label class="form-label" for="basic-default-username">Tên Người Dùng</label>
                                                    <input type="text" class="form-control" id="basic-default-username" 
                                                           name="username" placeholder="" required="" value="${username}"/>
                                                </div>
                                                <div class="mb-3">
                                                    <div class="form-password-toggle">
                                                        <label class="form-label" for="basic-default-password">Mật Khẩu</label>
                                                        <p id="errorPassword"></p>
                                                        <div class="input-group">
                                                            <input type="password" class="form-control" id="basic-default-password"
                                                                   name="password" placeholder="&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;" 
                                                                   required="" value="${password}" onchange="validatePassword()"
                                                                   />
                                                            <span id="basic-default-password" class="input-group-text cursor-pointer"
                                                                  ><i class="bx bx-hide"></i
                                                                ></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="mb-3">
                                                    <div class="form-password-toggle">
                                                        <label class="form-label" for="basic-default-confirmPassword">Xác Nhận Mật Khẩu</label>
                                                        <p id="errorConfirmPassword"></p>
                                                        <div class="input-group">
                                                            <input type="password" class="form-control" id="basic-default-confirmPassword" name="confirmPassword"
                                                                   placeholder="&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;"
                                                                   required="" onchange="confirmPassword()" value="${confirmPassword}"/>
                                                            <span id="basic-default-password" class="input-group-text cursor-pointer"
                                                                  ><i class="bx bx-hide"></i
                                                                ></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="mb-3">
                                                    <label class="form-label" for="basic-default-email">Email</label>
                                                    <p id="errorEmail"></p>
                                                    <div class="input-group input-group-merge">
                                                        <input
                                                            type="text"
                                                            id="basic-default-email"
                                                            class="form-control"
                                                            placeholder="john.doe"
                                                            aria-label="john.doe"
                                                            aria-describedby="basic-default-email2" value="${email}"
                                                            name="email" required="" onchange="validateEmail()"
                                                            />
                                                        <span class="input-group-text" id="basic-default-email2">@example.com</span>
                                                    </div>
                                                    <div class="form-text">You can use letters, numbers & periods</div>
                                                </div>
                                                <div class="mb-3">
                                                    <label class="form-label" for="basic-default-mobile">Điện Thoại</label>
                                                    <p id="errorMobile"></p>
                                                    <input
                                                        type="text"
                                                        id="basic-default-mobile"
                                                        class="form-control phone-mask"
                                                        placeholder="658 799 8941" value="${phone}"
                                                        name="phone" required="" onchange="validateMobile()"
                                                        />
                                                </div>
                                                <div class="mb-3">
                                                    <label class="form-label" for="basic-default-address">Địa Chỉ</label>
                                                    <input type="text" class="form-control" id="basic-default-username" 
                                                           name="address" placeholder="" required="" value="${address}"/>
                                                </div>
                                                <div class="mb-3">
                                                    <label class="form-label" for="exampleFormControlTextarea1">Mô Tả</label>
                                                    <textarea name="description" class="form-control"
                                                              id="exampleFormControlTextarea1" rows="3">
                                                        ${description}
                                                    </textarea>
                                                </div>
                                                
                                                    <button type="submit" class="btn btn-primary">Gửi</button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <!-- / Content -->

                            <!-- Footer -->
                            <footer class="content-footer footer bg-footer-theme">
                                <div class="container-xxl d-flex flex-wrap justify-content-between py-2 flex-md-row flex-column">
                                    
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
                function confirmPassword() {
                    var pw = document.getElementById("basic-default-password").value;
                    var cfpw = document.getElementById("basic-default-confirmPassword").value;
                    if (pw !== cfpw) {
                        document.getElementById("errorConfirmPassword").innerHTML = "Confirm password and password are different";
                        document.getElementById("errorConfirmPassword").style = "color: red";
                        document.getElementById("basic-default-confirmPassword").style = "border-color: red";
                    } else {
                        document.getElementById("errorConfirmPassword").innerHTML = "";
                        document.getElementById("errorConfirmPassword").style = "color:";
                        document.getElementById("basic-default-confirmPassword").style = "border-color: ";
                    }
                }
                function validatePassword() {
                    var pw = document.getElementById("basic-default-password").value;
                    var regex = "^(?=.{6,30})";
                    if (!pw.match(regex)) {
                        document.getElementById("errorPassword").style = "color: red";
                        document.getElementById("basic-default-password").style = "border-color: red";
                        document.getElementById("errorPassword").innerHTML = "Password must be between 8 and 30 characters, has at least a letter and a digit";
                    } else {
                        document.getElementById("errorPassword").style = "color: ";
                        document.getElementById("errorPassword").innerHTML = "";
                        document.getElementById("basic-default-password").style = "border-color:";
                    }
                }
                function validateMobile() {
                    var mobile = document.getElementById("basic-default-mobile").value;
                    var regex = "^[0-9]+$";
                    if (!mobile.match(regex)) {
                        document.getElementById("errorMobile").innerHTML = "Invalid mobile";
                        document.getElementById("errorMobile").style = "color: red";
                        document.getElementById("basic-default-mobile").style = "border-color: red";
                    } else {
                        document.getElementById("errorMobile").innerHTML = "";
                        document.getElementById("errorMobile").style = "color: ";
                        document.getElementById("basic-default-mobile").style = "border-color:";
                    }
                }
                function validateEmail() {
                    var regex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+.(?:\.[a-zA-Z0-9-]+)*$";
                    var email = document.getElementById("basic-default-email").value;

                    if (!email.match(regex)) {
                        document.getElementById("errorEmail").innerHTML = "Invalid email";
                        document.getElementById("errorEmail").style = "color: red";
                        document.getElementById("basic-default-email").style = "border-color: red";
                    } else {
                        document.getElementById("errorEmail").innerHTML = "";
                        document.getElementById("errorEmail").style = "color: ";
                        document.getElementById("basic-default-email").style = "border-color: ";
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
