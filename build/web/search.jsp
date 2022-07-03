<%-- 
    Document   : search
    Created on : Jun 6, 2022, 2:31:55 PM
    Author     : huy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body style="text-align: center">
        <div style="text-align: left">
            <font color="red">
            Welcome ${sessionScope.USER.fullName}
            </font>
            <form action="DispatchController">
                <input type="submit" value="Log Out" name="btAction" />
            </form>
        </div>

        <h1 style="color:blue">SEARCH PAGE</h1>
        <form action="DispatchController">
            <input type="text" name="txtSearchValue" value="${param.txtSearchValue}"/>
            <input type="submit" value="Search" name="btAction" />
        </form><br/>

        <c:set var="searchValue" value="${param.txtSearchValue}"/>  
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <c:if test="${empty result}">
                <h2>No record is matched</h2>
            </c:if>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>FullName</th>
                            <th>Role</th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${result}" varStatus="counter">
                        <form action="DispatchController"> <!--form per row-->
                            <tr>
                                <td>${counter.count}.</td>
                                <td>
                                    ${user.username}
                                    <input type="hidden" name="txtUsername" value="${user.username}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" value="${user.password}" />
                                </td>
                                <td>
                                    ${user.fullName}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkRole" value="ON" 
                                           <c:if test="user.role">
                                               checked = "checked"
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <c:url var="deleleURL" value="DispatchController">
                                        <c:param name="btAction" value="delete"/>
                                        <c:param name="pk" value="${user.username}"/>
                                        <c:param name="lastSearchValue" value="searchValue"/>
                                    </c:url>
                                    <a href="${deleteURL}">Delete</a>
                                </td>
                                <td>
                                    <input type="hidden" name="lastSearchValue" 
                                           value="${searchValue}" />
                                    <input type="submit" value="Update" name="btAction" />
                                </td>
                            </tr>
                        </form>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </c:if>
        </body>
    </html>