<%--Add jstl library--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>First Web Application</title>
    </head>
    <body>
        <p>
            Hello, ${checkName}!!
        </p>
        <p>
            Tasks :
        </p>
        <c:if test="${not empty tasks}">

            <ul>
                <c:forEach var="listValue" items="${tasks}">
                    <li>${listValue.taskDetails.description}</li>
                </c:forEach>
            </ul>

        </c:if>
        <p>
            <a href="/logout">logout</a>
        </p>
    </body>
</html>