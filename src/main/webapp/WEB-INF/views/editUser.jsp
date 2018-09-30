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
<html>
<head>
    <title>Edit User</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/submitEditedUser", method="post">
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

            <div class="row">
                <input type="hidden" name="hiddenID" value="${user.id}">
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
</form>
</body>
</html>
