<%-- 
    Document   : viewCart
    Created on : Nov 5, 2020, 10:44:20 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Market</title>
        <link rel="stylesheet" href="css/search.css" />
    </head>
    <body>
        <c:import url="header.jsp"></c:import>
        <h1>Your cart</h1>
        <c:set var="cart" value="${sessionScope.CUSTCART}"/>
        <c:if test="${not empty cart}">
            <c:set var="result" value="${cart.getItems()}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>                        
                        <tr>
                            <th>No.</th>
                            <th>Title</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Total Price</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <form action="DispatcherServlet">
                        <tbody>
                            <c:set var="totalbill" value="${0}"></c:set>
                            <c:forEach var="itemKey" items="${result}" varStatus="counter">
                                <tr>
                                    <td>
                                        ${counter.count}
                                        .</td>
                                    <td>
                                        ${itemKey.key.name}
                                    </td>
                                    <td>
                                        ${itemKey.key.price}
                                       
                                    </td>
                                    <td>
                                         
                                        
                                        ${itemKey.value}
                                    </td>
                                    <td>
                                        ${itemKey.value*itemKey.key.price}

                                    </td>
                                    <c:set var="totalbill" value="${totalbill= totalbill+itemKey.value*itemKey.key.price}"></c:set>

                                        <td>
                                            <input type="checkbox" name="chkItem" value="${itemKey.key.id}" />
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="6">Total bill: ${totalbill} </td>
                            </tr>
                            <tr>
                                <td colspan="5">
                                    <a href="search.jsp">Add More Books to Your Cart</a>
                                </td>
                                <td>
                                    <input type="submit" name="btAction" value="Remove Selected Item" onclick="return confirm('Are you sure you want to delete these items?');"/>
                                </td>

                            </tr>


                        </tbody>
                    </form>
                    <form action="DispatcherServlet">   <input type="submit" name="btAction" value="Check out" />
                    </form>
                </table>

            </c:if>
            <c:if test="${sessionScope.BUYERROR != null}">
                <font color="red">${sessionScope.BUYERROR} </font>
            </c:if>
            <c:if test="${empty result}">
                <h2>
                    No cart is existed <br/>
                    <a href="search.jsp">Click here to continue shopping</a>

                </h2>    
            </c:if>
        </c:if>
        <c:if test="${empty cart}">
            <h2>
                No cart is existed <br/>
                <a href="search.jsp">Click here to continue shopping</a>

            </h2>    
        </c:if>


    </body>
</html>
