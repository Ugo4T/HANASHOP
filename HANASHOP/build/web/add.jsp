<%-- 
    Document   : add
    Created on : Jan 31, 2021, 1:16:40 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Page</title>
        <link rel="stylesheet" href="css/search.css" />
    </head>
    <body>
        <c:import url="header.jsp"></c:import>
        <c:set var="dto" value="${requestScope.UPDATEFOOD}">           
        </c:set><br/>
        <h1>FOOD INFORMATION</h1>



        <form action="DispatcherServlet">

            <c:set var="err" value="${requestScope.ERROR}"></c:set>
            id:   &nbsp;&nbsp;&emsp;&emsp;&emsp;    <input type="text" name="txtid" value="${dto.getId()}" /><br/>
            <c:if test="${err != null}">
                
                <p> <font color="red">${err[0]}</font></p>
                </c:if>
                
                 <c:if test="${(err[6] != null)}">
                
                <p> <font color="red">${err[6]}</font></p>
                </c:if>
            name: &emsp;&emsp;    <input type="text" name="txtname" value="${dto.name}"  /><br/>
            <c:if test="${err != null}">
                
                <p> <font color="red">${err[1]}</font></p>
                </c:if>
            price:  &nbsp;&nbsp;&emsp;&emsp;
            <c:if test="${err[2] == null}">
                <input type="text" name="txtprice" value="${dto.price}"  /><br/>
            </c:if>
            <c:if test="${err[2] != null}">
                <input type="text" name="txtprice" value=""  /><br/>
                <p> <font color="red">${err[2]}</font></p>
                </c:if>

            image:  &emsp;&emsp;<input type="file" name="txtimage" value=""  /><br/>
            <c:if test="${err != null}">
                
                <p> <font color="red">${err[3]}</font></p>
                </c:if>
            <input type="hidden" name="txtimagehidden" value="${dto.image}" />
            categoryID:  
            <select name="cboCategory">

                <c:forEach var="CategoryDTO" items="${sessionScope.CATEGORYLIST}">

                    <c:if test="${CategoryDTO.getCategoryID() eq dto.categoryID}">
                        <option selected="selected" value="${CategoryDTO.getCategoryID()}"> ${CategoryDTO.name} </option>
                    </c:if>
                    <c:if  test="${CategoryDTO.getCategoryID() ne dto.categoryID}">
                        <option value="${CategoryDTO.getCategoryID()}"> ${CategoryDTO.name} </option>
                    </c:if>
                </c:forEach>

            </select> <br/>
            quantity: &emsp; <c:if test="${err[4] == null}"> <input type="text" name="txtquantity" value="${dto.quantity}" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/><br/></c:if> 
            <c:if test="${err[4] != null}">
                <input type="text" name="txtquantity" value="" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/><br/>
                <p> <font color="red">${err[4]}</font></p>
            </c:if>
            description:&nbsp;<input type="text" name="txtdescription" value="${dto.description}" /><br/>
            <c:if test="${err != null}">
                
                <p> <font color="red">${err[5]}</font></p>
                </c:if>

            
            <input type="submit" value="Create"  name="btAction" />

        </form>

    </body>
</html>
