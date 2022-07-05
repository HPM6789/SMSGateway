<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    Document   : accessDeniedPage
    Created on : Jun 1, 2022, 9:35:20 AM
    Author     : Minh Hieu Pham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Từ Chối Cập Nhật</title>
    </head>
    <body>
        <h1>${message}</h1>
        <a href="home">Trang Chủ</a><br>
        <c:if test="${sessionScope.user != null}">
            <a href="logout">Đăng Xuất</a><br>
        </c:if>
    </body>
</html>
