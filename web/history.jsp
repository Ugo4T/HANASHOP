<%-- 
    Document   : history
    Created on : Feb 1, 2021, 12:53:21 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/search.css" />
    </head>
    <body>
        <c:import url="header.jsp"></c:import>
            <h1>View History</h1>
            <form action="DispatcherServlet" style="text-align: center">
                Search name <input type="text" name="txtSearchHistory" value="" />
                <input type="submit" value="Search history" name="btAction" />
            </form><br/>
        <c:set var="history" value="${sessionScope.SHOPPINGHISTORY}" />
        <c:if test="${not empty history}" >
            <table id="keywords" border="1" style="width: 100%">
                <thead>
                    <tr>
                        <th>User ID</th>
                        <th>User Name</th>
                        <th>Food Name</th>
                        <th>Quantity</th>
                        <th>Total</th>
                        <th>Date</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${history}" >
                        <tr>
                            <td>
                                ${dto.userid}
                            </td>
                            <td>
                                ${dto.username}
                            </td>
                            <td>
                                ${dto.foodname}
                            </td>
                            <td>
                                ${dto.quantity}
                            </td>
                            <td>
                                ${dto.total}
                            </td>
                            <td>
                                ${dto.date}
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
        <c:if test="${empty history}" >
            <h2>No history to show</h2>
        </c:if>
    </body>
</html>