<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>JSP - Hello World</title>
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
<h1><%= "Welcome" %>
</h1>
<br/>
<a href="Home">Home page</a>
<br>
<br>
<label>${requestScope.NOT_FOUND}</label>
<form action="Home" class="" method="get">
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