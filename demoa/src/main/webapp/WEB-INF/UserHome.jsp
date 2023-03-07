
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
    <title>Title</title>
</head>
<body>
<%--Get the current users info from context--%>
<label>Username: ${sessionScope.user.getName()}</label>
<form name="logout" action="ServletLogout" method="get">
    <input type="submit" value="Logout" name="logout">
</form>
<br>
<form name="changePassword" action="ServletChangePassword" method="get">
    <input type="submit" value="Change password" name="changePassword">
</form>
<br>
<label>${requestScope.PERMISSION_DENIED}</label>
<form name="overview" action="ServletOverview" method="get">
    <input type="submit" value="Go to overview" name="gotoOverview">
</form>
</body>
</html>
