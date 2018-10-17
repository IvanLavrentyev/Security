<%--
  Created by IntelliJ IDEA.
  User: Lavrentyev.i
  Date: 29.09.2018
  Time: 1:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<style>
.butn{
    margin-left: 35%;
}
</style>


<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>All Users</title>
</head>
<body>
<div class="container">
    <div class="col-md-1"></div>

    You are logged in as <security:authentication property = "principal.username"/>

    <div class="col-md-10">
    <hr>
        <table class="table" style="margin-top: 10%">
            <thead class="thead-dark">
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Name</th>
                <th scope="col">Login</th>
                <th scope="col">Password</th>
                <th scope="col">Roles</th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <th>${user.id}</th>
                            <td>${user.name}</td>
                            <td>${user.login}</td>
                            <td>${user.password}</td>

                            <td>
                                <c:forEach items="${user.roles}" var="r">
                                    ${r.role}
                                </c:forEach>

                            </td>

                           <td>
                               <div class="btn-group" role="group">
                                   <button id="btnGroupDrop1" type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                       Actions
                                   </button>
                                   <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                                       <a class="dropdown-item" href="${pageContext.request.contextPath}/editUser?id=${user.id}">Edit</a>
                                       <a class="dropdown-item" href="${pageContext.request.contextPath}/deleteUser?id=${user.id}">Delete</a>
                                       <a class="dropdown-item" href="${pageContext.request.contextPath}/viewUser?id=${user.id}">View</a>
                                   </div>
                               </div>
                           </td>
                        </tr>
                    </c:forEach>
            </tbody>
        </table>
        <hr>

        <p>
            <div class="float-md-right">
        <button class="btn btn-secondary" type="button" data-toggle="collapse" data-target=".multi-collapse" aria-expanded="false" aria-controls="multiCollapseExample1 multiCollapseExample2 multiCollapseExample3">Add User</button>
            </div>

        </p>

        <form:form action="${pageContext.request.contextPath}/addUser" method="post">
            <div>
                <div class="row">
                    <div class="col">
                        <div class="collapse multi-collapse">
                            <div class="row">
                                <input type="text" class="form-control" name="newUserName" placeholder="Enter User name">
                            </div>

                        </div>
                    </div>

                    <div class="col">
                        <div class="collapse multi-collapse">
                            <div class="row">
                                <input type="text" class="form-control" name="newUserLogin" placeholder="Enter User login">
                            </div>
                        </div>
                    </div>

                    <div class="col">
                        <div class="collapse multi-collapse">
                            <div class="row">
                                <input type="text" class="form-control" name="newUserPassword" placeholder="Enter User password">
                            </div>
                        </div>
                    </div>

                    <div>
                        <div class="collapse multi-collapse">
                                <div class="input-group mb-3">
                                    <select name="roleParam" class="custom-select" id="inputGroupSelect01">
                                        <option selected>Choose Role</option>
                                        <option value="ADMIN">ADMIN</option>
                                        <option value="USER">USER</option>
                                    </select>
                                </div>
                        </div>
                    </div>

                    <div class="col">
                        <div class="collapse multi-collapse" id="multiCollapseExample4">
                            <div>
                               <a><button class="btn btn-secondary butn" >Submit User</button></a>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </form:form>
    </div>
    <div class="col-md-1">
        <div class="float-md-right">
            <a href="${pageContext.request.contextPath}/logout">Logout</a>
        </div>
    </div>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>

</html>
