<%-- 
    Document   : shopping
    Created on : Jun 14, 2022, 12:37:17 PM
    Author     : huy
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Book Store</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>

        <font color="red">Welcome ${sessionScope.USER.fullName}</font>
        </br>
        <form action="DispatchController">
            <input type="submit" value="Log Out" name="btAction" />
        </form>

        <h1>Book Store</h1>
        <c:set var="listItems" value="${requestScope.PRODUCTS}"/>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Title</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${listItems}" varStatus="counter">
                <form action="DispatchController">
                    <tr>
                        <td>${counter.count}</td>
                        <td>
                            ${item.name}
                            <input type="hidden" name="cboBook" value="${item.name}" />    
                        </td>
                        <td>${item.price}</td>
                        <td>${item.quantity}</td>
                        <td>
                            <input type="submit" value="Add Book to your Cart" name="btAction" />
                        </td>
                    </tr>
                </form>
            </c:forEach>
        </tbody>
    </table>

    <a href="DispatchController?btAction=ViewCart">View your Cart</a>
</body>
</html
