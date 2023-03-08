<%--
  Created by IntelliJ IDEA.
  User: magnu
  Date: 07/03/2023
  Time: 17.36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Admin Home</title>
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
<c:if test="${requestScope.user != null}">
    <c:if test="${requestScope.user.getRole() != 'admin'}">
        <h1>Access denied</h1>
    </c:if>
</c:if>
<div class="container">
<h1>Welcome to the administrative page ${requestScope.user.getName()}!</h1>

    <div class="col-sm-2"></div>
    <div class="col-sm-8">
        <table class="table-responsive-sm table-bordered">
            <tr class="table-bordered">
                <th>Users</th>
                <th>Delete</th>
            </tr>
            <c:forEach var="user" items="${applicationScope.users}">
                <tr>
                    <td>${user.value.getName()}</td>
                    <td><a href="ServletDeleteUser?username=${user.value.getName()}">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="col-sm-2"></div>
    <br>
    <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-2">
            <h3>${requestScope.USER_ADD_ATTEMPTED}</h3>
            <form action="ServletAddUser" method="get">
                <div class="mb-3">
                    <label for="name" class="form-label">Username</label>
                    <input type="text" class="form-control" id="name" name="name">
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" name="password">
                </div>
                <div class="mb-3">
                    <label for="permission" class="form-label">Role</label>
                    <select class="form-select" aria-label="Default select example" name="permission" id="permission">
                        <option value="USER">User</option>
                        <option value="ADMIN">Admin</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
        <div class="col-sm-8"></div>
    </div>
</div>
</body>
</html>
