<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Run servlet test</a>
<br>
<br>
<label>${requestScope.NOT_FOUND}</label>
<form action="hello-servlet" class="" method="get">
    <label for="name">Name:</label>
    <br>
    <input type="text" id="name" name="name" placeholder="Enter your name">
    <br>
    <label for="password">Password:</label>
    <br>
    <input type="text" id="password" name="password" placeholder="Enter your password">
    <br>
    <br>
    <input type="submit" value="Submit">
</form>
</body>
</html>