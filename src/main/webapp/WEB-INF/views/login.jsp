<%--
  Created by IntelliJ IDEA.
  User: Lavrentyev.i
  Date: 12.10.2018
  Time: 0:18
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>Login Page</title>
</head>
<body style='margin:50px;'>
<h2></h2>
<form action="${pageContext.request.contextPath}/login", method="post">
    <div class="form-group">
        <label for="InputLogin">Login</label>
        <input type="text" class="form-control" name="userLogin" value="${user.login}" id="InputLogin" aria-describedby="emailHelp" placeholder="Enter Login">
    </div>
    <div class="form-group">
        <label for="InputPassword">Password</label>
        <input type="password" class="form-control" name="userPassword" id="InputPassword" placeholder="Password">
    </div>
    <button type="submit" class="btn btn-primary">LOGIN</button>

    <input id="remember-me" name="remember-me" type="checkbox"/>
    <label for="remember-me" class="inline">Remember me</label>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</body>
</html>
