<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Upload</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
        <ul class="nav">
            <c:if test="${user.name == null}">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp">Войти</a>
                </li>
            </c:if>
            <c:if test="${user.name != null}">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp"> <c:out value="${user.name}"/> | Выйти</a>
                </li>
            </c:if>
        </ul>
</div>
<div class="container">
    <h2>Upload image</h2>
    <%
        String name = request.getParameter("name");
    %>
    <form action="<c:url value='/upload'/>" method="post" enctype="multipart/form-data">
        <div class="checkbox">
            <input type="file" name="<%=name%>"/>
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>
</div>
</body>
</html>