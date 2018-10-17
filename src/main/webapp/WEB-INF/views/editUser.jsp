<%--
  Created by IntelliJ IDEA.
  User: Lavrentyev.i
  Date: 30.09.2018
  Time: 23:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <%--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>--%>

    <%--<!-- Bootstrap CSS -->--%>
    <%--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">--%>
        <title>Edit User</title>
</head>
<body>
 <form action="${pageContext.request.contextPath}/submitEditedUser", method="post"> >
    <div>
        <div class="row">
            <div class="col">
                <div class="collapse multi-collapse">
                    <div class="row">
                        <input type="text" class="form-control" name="nameToEdit" value="${user.name}" placeholder="Edit User name">
                    </div>
                </div>
            </div>

            <div class="col">
                <div class="collapse multi-collapse">
                    <div class="row">
                        <input type="text" class="form-control" name="loginToEdit" value="${user.login}" placeholder="Edit User login">
                    </div>
                </div>
            </div>

            <div class="col">
                <div class="collapse multi-collapse">
                    <div class="row">
                        <input type="text" class="form-control" name="passwordToEdit" value="${user.password}" placeholder="Edit User password">
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


            <div class="row">
                <input type="hidden" name="hiddenID" value="${user.id}">
                <input type="hidden" name="oldPassword" value="${oldPassword}">
            </div>

            <div class="col">
                <div class="collapse multi-collapse">
                    <div>
                        <button type="submit" class="btn btn-secondary butn">Submit Edited User</button>
                    </div>
                </div>
            </div>

        </div>
    </div>
     <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
 </form>
</body>
</html>
