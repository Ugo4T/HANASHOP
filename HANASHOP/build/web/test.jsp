<%-- 
    Document   : test
    Created on : Jan 20, 2021, 10:23:01 PM
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
        <h1>Hello World!</h1>
        <select name="cboCategory">
                <option value="0">  All</option>
                
                <c:forEach var="Category_DTO" items="${sessionScope.CATEGORYLIST}">
                    <option value="${Category_DTO.getCategoryID()}"> ${Category_DTO.name} </option>
                </c:forEach>
    </body>
</html>
