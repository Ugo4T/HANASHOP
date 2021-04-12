<%-- 
    Document   : header
    Created on : Jan 17, 2021, 9:01:44 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>        
        <c:if test="${sessionScope.FULLNAME ne null}">
            <font color="red">Welcome, ${sessionScope.FULLNAME}</font>
            <form action="DispatcherServlet">
                <input type="submit" name="btAction" value="Logout" />
            </form>
        </c:if>
        <c:if test="${sessionScope.FULLNAME eq null}">
            <c:url var="url" value="DispatcherServlet">
                <c:param name="btAction" value="Login Now"></c:param>
            </c:url>
            <a href="${url}">Login</a>
        </c:if>
        <c:if test="${sessionScope.ISADMIN==false}">
            <c:url value="DispatcherServlet" var="urlview">
                <c:param name="btAction" value="View your card"></c:param>
            </c:url>
            <a href="${urlview}">

                View Card
            </a>
            <br/>

            <c:url value="DispatcherServlet" var="url">
                <c:param name="btAction" value="history"></c:param>
            </c:url>
            <a href="${url}">

                View History
            </a>

        </c:if>
    </body>
</html>
