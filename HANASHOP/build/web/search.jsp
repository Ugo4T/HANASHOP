<%-- 
    Document   : search
    Created on : Jan 17, 2021, 9:44:23 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
        <link rel="stylesheet" href="css/search.css" />
    </head>
    <body>
        <c:import url="header.jsp"></c:import>
            <h1>HANA SHOP</h1> 
            <br/>

            <form action="DispatcherServlet">
                <p>Search Value <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" /></p> <br/>



            Category: <select name="cboCategory">
                <option value="0">  All</option>
                <c:forEach var="CategoryDTO" items="${sessionScope.CATEGORYLIST}">

                    <c:if test="${CategoryDTO.getCategoryID() eq param.cboCategory}">
                        <option selected="selected" value="${CategoryDTO.getCategoryID()}"> ${CategoryDTO.name} </option>
                    </c:if>
                    <c:if  test="${CategoryDTO.getCategoryID() ne param.cboCategory}">
                        <option value="${CategoryDTO.getCategoryID()}"> ${CategoryDTO.name} </option>
                    </c:if>
                </c:forEach>

            </select> <br/>
            Min: <input type="text" name="txtPricemin" value="${requestScope.TXTMIN}" onkeypress='return event.charCode >= 48 && event.charCode <= 57'></input> 
            Max: <input type="text" name="txtPricemax" value="${requestScope.TXTMAX}" onkeypress='return event.charCode >= 48 && event.charCode <= 57'></input>



            <input type="submit" value="Search" name="btAction" /><br/>
            <c:if test="${sessionScope.ISADMIN}">
                <input type="submit" value="AddNew" name="btAction" />
            </c:if>

        </form> <br/>

        <c:set var="searchValue" value="${param.txtSearchValue}"/>

        <c:set var="result" value="${requestScope.SEARCHRESULT}"/>

        <c:if test="${not empty result}">
            <table border="1" style="width: 100%">
                <thead>
                    <tr>
                        <th>ID.</th>
                        <th>Image</th>
                        <th>Name</th>
                        <th>price</th>
                        <th>Category</th>
                        <th>quantity</th>
                        <th>description</th>
                        <th>create Date</th>


                        <c:if test="${sessionScope.ISADMIN}">
                            <th>status</th>
                            <th>Delete</th>
                            <th>Update</th>
                            </c:if>
                            <c:if test="${sessionScope.ISADMIN == false}">
                            <th>Add card</th>

                        </c:if>

                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${result}" >
                    <form action="DispatcherServlet">
                        <tr>
                            <td>${dto.id}</td>
                            <td> <img src="image/${dto.image}" style="width: 120px; height: 120px"></img>


                            </td>
                            <td>${dto.name}

                            </td>
                            <td>${dto.price}

                            </td>
                            <td>
                                <c:forEach var="CategoryDTO" items="${sessionScope.CATEGORYLIST}">
                                    <c:if  test="${CategoryDTO.getCategoryID() eq dto.categoryID}">
                                        ${CategoryDTO.name}
                                    </c:if>
                                </c:forEach>

                            </td>
                            <td>${dto.quantity}

                            </td>
                            <td>${dto.description}

                            </td>
                            <td>${dto.createDate}

                            </td>
                            <c:if test="${sessionScope.ISADMIN}">
                                <td>${dto.status}

                                </td>
                                <td>


                                    <input type="hidden" name="pk" value="${dto.id}" />
                                    <input type="hidden" name="lastSearchValue" value="${param.txtSearchValue}" />
                                    <input type="hidden" name="lastCatagoryValue" value="${param.cboCatagory}" />
                                    <input type="hidden" name="lastPriceMin" value="${param.txtPricemin}" />
                                    <input type="hidden" name="lastPriceMax" value="${param.txtPricemax}" />
                                    <input type="submit" value="Delete" name="btAction" onclick="return confirm('Are you sure you want to delete this item?');" />




                                </td>
                                <td>
                                    <input type="hidden" name="pk" value="${dto.id}" />
                                    <input type="hidden" name="lastSearchValue" value="${param.txtSearchValue}" />
                                    <input type="hidden" name="lastCatagoryValue" value="${param.cboCatagory}" />
                                    <input type="hidden" name="lastPriceMin" value="${param.txtPricemin}" />
                                    <input type="hidden" name="lastPriceMax" value="${param.txtPricemax}" />
                                    <input type="submit" value="Update" name="btAction"/>


                                </td>

                            </c:if>
                            <c:if test="${sessionScope.ISADMIN==false}">
                                <td><input type="hidden" name="pk" value="${dto.id}" />
                                    <input type="hidden" name="txtSearchValue" value="${searchValue}" />
                                    <input type="hidden" name="cboCategory" value="${param.cboCategory}" />
                                    <input type="hidden" name="txtPricemin" value="${param.txtPricemin}" />
                                    <input type="hidden" name="txtPricemax" value="${param.txtPricemax}" />
                                    <input type="hidden" name="txtPage" value="${requestScope.PAGE}" />
                                    <input type="hidden" name="txtPageSize" value="${requestScope.PAGESIZE}" />
                                    <input type="submit" value="Add to cart" name="btAction" />

                                </td>


                            </c:if>

                        </tr>
                    </form>
                </c:forEach>

            </tbody>
        </table>
        <form action="DispatcherServlet">
            <c:if test="${requestScope.PAGE ne 1}">
                <input type="submit" value="BACK" name="btAction" /> 
            </c:if>


            <input type="text" name="txtPage" value="${requestScope.PAGE}" readonly="readonly" />
            <c:if test="${requestScope.PAGE ne requestScope.PAGESIZE}">
                <input type="submit" value="NEXT" name="btAction" />   
            </c:if>

            <input type="hidden" name="txtSearchValue" value="${searchValue}" />
            <input type="hidden" name="cboCategory" value="${param.cboCategory}" />
            <input type="hidden" name="txtPricemin" value="${param.txtPricemin}" />
            <input type="hidden" name="txtPricemax" value="${param.txtPricemax}" />
        </form>

    </c:if>
    <c:if test="${param.cboCategory != null}">
        <c:if test="${empty result}">
            <h2>no record is matched!!! 
            </h2>
        </c:if>

    </c:if>  


</body>
</html>
