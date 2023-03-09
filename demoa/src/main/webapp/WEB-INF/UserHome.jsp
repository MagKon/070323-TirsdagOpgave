<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: magnu
  Date: 07/03/2023
  Time: 15.31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Home</title>
</head>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Navbar</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                    <a class="nav-link active" aria-current="page" href="Home">Home</a>
                    <a class="nav-link" href="ServletOverview">Admin</a>
                    <a class="nav-link" href="ServletLogout">Logout</a>
                </div>
            </div>
        </div>
    </nav>
</header>
<body>
    <div class="container">
        <h1 class="alert-danger">${requestScope.INTERNAL_SERVER_ERROR}</h1>
        <%--Get the current users info from context--%>
        <h2>Username: ${sessionScope.user.getName()}</h2>
<%--        <form name="logout" action="ServletLogout" method="get">--%>
<%--            <input type="submit" value="Logout" name="logout">--%>
<%--        </form>--%>
        <br>
        <br>
        <label class="alert-danger">${requestScope.PASSWORD_ATTEMPTED_CHANGE}</label>
        <form name="changePassword" action="ServletChangePassword" method="get">
            <label for="newPassword">Change password: </label>
            <br>
            <input type="password" id="newPassword" name="newPassword">
            <br>
            <br>
            <input type="submit" value="Change password" name="changePassword">
        </form>
        <br>
        <br>
        <div class="row">
            <label class="alert-danger">${requestScope.USER_ITEM_ADD_ATTEMPTED}</label>
            <label class="fst-italic text-sm-start">*to remove an item, simply delete it and update the list</label>
            <form action="ServletAddItemToUser" method="get">
                <c:forEach var="item" items="${sessionScope.user.getTodoList()}">
                    <input type="text" name="${sessionScope.user.getTodoList().indexOf(item)}" id="${sessionScope.user.getTodoList().indexOf(item)}" value="${item}"><br>
                </c:forEach>
                <input type="text" name="newItem" id="-1" placeholder="New Item">
                <br>
                <br>
                <input type="submit" name="submit" id="submit" value="Update list">
            </form>
        </div>
<%--        <label>${requestScope.PERMISSION_DENIED}</label>--%>
<%--        <form name="overview" action="ServletOverview" method="get">--%>
<%--            <input type="submit" value="Go to overview" name="gotoOverview">--%>
<%--        </form>--%>
    </div>
</body>
</html>
