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
<label>Username: ${requestScope.user.getPassword()}</label>
${requestScope}
</body>
</html>
