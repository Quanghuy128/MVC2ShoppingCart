<%-- 
    Document   : detail
    Created on : Jun 19, 2022, 6:19:04 PM
    Author     : huy
--%>

<%@page import="huynq.product.ProductDTO"%>
<%@page import="huynq.product.ProductDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            int quantity = 0;
            double price = 0;
            String title = request.getParameter("pk");
            ProductDAO dao = new ProductDAO();
            ProductDTO dto = dao.getProduct(title);
            if(dto != null) {
                quantity = dto.getQuantity();
                price = dto.getPrice();
            }
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= (dto == null) ? title : "Detail - " + title %></title>
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
        
        <h1>Detail Of <%= title%></h1>
        <font color="blue">Existed Quantity <%= quantity %> - Price: <%= price %></font> <br/>
        <a href="DispatchController?btAction=View Your Cart">Back to Your Cart</a> <br/>
        <a href="DispatchController?btAction=Add Book to your Cart">Add more Books</a>
    </body>
</html>
