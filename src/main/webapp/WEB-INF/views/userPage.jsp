<%--
  Created by IntelliJ IDEA.
  User: Lavrentyev.i
  Date: 11.10.2018
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<html>
<head>
    <title>User page</title>
</head>
<body>
<h2>
    Hello User ${user.name}

    your data is :

    User login - ${user.login}
    User role - <c:forEach items="${user.roles}" var="r">
                        ${r.role}
                </c:forEach>

</h2>
</body>
</html>
