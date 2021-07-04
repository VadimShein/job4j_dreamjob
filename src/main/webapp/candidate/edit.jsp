<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="ru.job4j.dream.model.Candidate" %>
<%@ page import="java.util.Date" %>
<%@ page import="ru.job4j.dream.store.PsqlStore" %>
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <title>Работа мечты</title>

    <style>
    input.form-control {
        display: inline-block;
        width:90%;
    }
    select.cityList {
        height:38px;
        width:20px;
    }
    </style>
</head>

<body onload="getCity()">
<%
    String id = request.getParameter("id");
    Candidate can = new Candidate(0, "", "", new Date());
    if (id != null) {
        can = PsqlStore.instOf().findByIdCandidate(Integer.parseInt(id));
    }
%>
<div class="container pt-3">
    <div class="row">
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
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <% if (id == null) { %>
                Новый кандидат
                <% } else { %>
                Редактирование вакансии.
                <% } %>
            </div>
            <div class="card-body">
                <form action="<%=request.getContextPath()%>/candidates.do?id=<%=can.getId()%>" method="post">
                    <div class="form-group">
                        <div>
                            <label>Имя</label><br>
                            <input type="text" class="form-control" name="name" title="Enter name" value="<%=can.getName()%>">
                        </div>
                        <div>
                            <label>Описание</label><br>
                            <input type="text" class="form-control" name="description" title="Enter description" value="<%=can.getDescription()%>">
                        </div>
                        <div>
                            <label>Город</label><br>
                            <input id="city" list="cityList" type="text" class="form-control" name="city"  title="Enter city">
                            <select id="cityList" class="cityList" onchange="document.querySelector('#city').value=value">
                                <option selected disabled hidden>-</option>
                            </select>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary" onclick="return validate()">Сохранить</button>
                </form>
            </div>
        </div>
    </div>
</div>
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

    function getCity() {
        $.ajax({
            type: 'GET',
            crossDomain : true,
            url: 'http://localhost:8080/dreamjob/city',
            dataType: 'text',
        }).done(function(data) {
            let dt = JSON.parse(data)
            for (const [key, value] of Object.entries(dt)) {
                if (key === "<%=can.getCityId()%>") {
                    document.querySelector('#city').value = value
                }
                let parent = document.querySelector('#cityList')
                let el = document.createElement('option')
                el.innerHTML = `${value}`
                parent.appendChild(el)
            }
        }).fail(function(err) {
            alert(err);
        });
    }
</script>
</body>
</html>