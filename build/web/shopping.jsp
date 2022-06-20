<%-- 
    Document   : shopping
    Created on : Jun 14, 2022, 12:37:17 PM
    Author     : huy
--%>

<%@page import="java.util.List"%>
<%@page import="huynq.product.ProductDAO"%>
<%@page import="huynq.product.ProductDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Book Store</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <%
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (int i=cookies.length-1; i >= 0;i--) {
                    if (!cookies[i].getName().equals("JSESSIONID")) {
                        %> 
                        <font color="red">Welcome <%= cookies[i].getName()%></font>
                        </br>
                        <form action="DispatchController">
                            <input type="submit" value="Log Out" name="btAction" />
                        </form>
        <%
                    break;
                    }
                }
            }
        %>
        <h1>Book Store</h1>
        <%
            ProductDAO dao = new ProductDAO();
            dao.getItemList();
            List<ProductDTO> list = (List<ProductDTO>)dao.getItems();
            if(list != null) {
                %>
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Product</th>
                            <th>Price</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            int count = 0;
                            for (ProductDTO dto : list) {
                                    %>
                                    <tr>
                                        <td><%= ++count %></td>
                                        <td><%= dto.getName() %></td>
                                        <td><%= (double)Math.round(dto.getPrice()) %>$</td>
                                        <td>
                                            <form action="DispatchController" method="POST">
                                                <input type="hidden" name="cboBook" value="<%= dto.getName() %>" />
                                                <input type="submit" name="btAction" value="Add Book to your Cart" />
                                            </form>
                                        </td>
                                    </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>

        <%
            }

        %>
        <a href="viewcart.jsp">View your Cart</a>
    </body>
</html
