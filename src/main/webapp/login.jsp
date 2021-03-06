<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    <title>Работа мечты</title>
    <script>
        function validate() {
            let rsl = true
            let atr = $('.form-control')
            for (let node of atr) {
                if (node.value === '' || node.value === null) {
                    alert(node.getAttribute('title'));
                    rsl = false
                    break
                }
            }
            return rsl
        }
    </script>
</head>
<body>
<div class="container pt-3">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Авторизация
            </div>
            <div class="card-body">
                <%
                    String err = (String) request.getAttribute("error");
                %>
                <c:if test="<%=err != null%>">
                    <c:out value="<%=err%>"/>
                </c:if>
                <form action="<c:url value="auth.do"/>" method="post">
                    <div class="form-group">
                        <label>Почта</label>
                        <input type="text" class="form-control" name="email" title="Enter email"
                               value="<%=(request.getParameter("email")!=null)?request.getParameter("email"):""%>">
                    </div>
                    <div class="form-group">
                        <label>Пароль</label>
                        <input type="password" class="form-control" name="password" title="Enter password" >
                    </div>
                    <button type="submit" class="btn btn-primary" onclick="return validate()">Войти</button>
                </form>
                <p></p>
                <form action="<c:url value="reg.jsp"/>" method="post">
                    <button type="submit" class="btn btn-primary">Регистрация</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>