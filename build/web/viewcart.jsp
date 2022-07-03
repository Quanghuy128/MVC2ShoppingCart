<%-- 
    Document   : shopping
    Created on : Jun 14, 2022, 12:37:17 PM
    Author     : huy
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

        <h1>Your Cart</h1>
        <c:set var="cart" value="${requestScope.ITEM_IN_CART}" />
        <c:if test="${not empty cart}">         
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Title</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Total</th>
                        <th>Action</th>
                        <th>View Detail</th>
                    </tr>
                </thead>
                <form action="DispatchController">
                    <tbody>
                        <c:forEach var="item" items="${cart}" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${item.key.name}</td>
                                <td>${item.key.price}</td>
                                <td>${item.value}</td>
                                <td>${item.value} * ${item.key.price}</td>
                                <td><input type="checkbox" name="chkItem" value="${item.key}" /></td>
                                <td>
                                    <a href="DispatchController?btAction=ViewDetail&pk=${item.key}>">Detail</a>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="3">
                                <a href="shopping.jsp">Add More Books to Cart</a>
                            </td>
                            <td>
                                <input type="submit" value="Remove" name="btAction" />
                            </td>

                            <td>
                                <input type="submit" value="CheckOut" name="btAction" />
                            </td>
                        </tr>
                    </tbody>
                </form>
            </table>

        </c:if>
        <c:if test="${empty cart}">
            No items in your cart!!!! 
        </c:if>
        <br/>
        <a href="shopping.jsp">Buy More Books </a>
    </body>
</html