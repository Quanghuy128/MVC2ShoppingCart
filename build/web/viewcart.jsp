<%-- 
    Document   : shopping
    Created on : Jun 14, 2022, 12:37:17 PM
    Author     : huy
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="huynq.product.ProductDTO"%>
<%@page import="huynq.product.ProductDAO"%>
<%@page import="java.util.Map"%>
<%@page import="huynq.cart.CartObject"%>
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
        <h1>Your Cart includes</h1>
        <%
            if(application.getAttribute("CART_TEMP") != null) {
                session.setAttribute("CART", application.getAttribute("CART_TEMP"));
                application.removeAttribute("CART_TEMP");
            }
            //1.customer goes to cart place
            if(session != null) {
                //2. Customer takes his/her cart
                CartObject cart = (CartObject)session.getAttribute("CART");
                if(cart != null) {
                    //3. check item existed
                    Map<String,Integer> items = (Map<String,Integer>)cart.getItems();
                    if(items != null) {
                        //4. show items
                        %>
                
                <form action="DispatchController" method="POST">
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
                        <tbody>
                            <%
                                int count = 0;
                                List<Double> total_price = new ArrayList<>();
                                for (String cartKey : items.keySet()) {
                                    ProductDAO dao = new ProductDAO();
                                    ProductDTO dto = dao.getProduct(cartKey);
                                    if(dto != null) {
                                        double total = items.get(cartKey)* dto.getPrice();
                                    
                                    %>
                                    <tr>
                                        <td>
                                            <%= ++count %>
                                        .</td>
                                        <td>
                                            <%= cartKey %>
                                        </td>
                                        <td>
                                            <%=dto.getPrice()%>$
                                        </td>
                                        <td>
                                            <%= items.get(cartKey) %>
                                        </td>
                                        <td>
                                            <%= total %>$
                                            <%
                                                total_price.add(total);
                                            %>
                                        </td>
                                        <td>
                                            <input type="checkbox" name="chkItem" value="<%= cartKey%>" />
                                        </td>
                                        <td>
                                            <a href="DispatchController?btAction=ViewDetail&pk=<%= cartKey %>">Detail</a>
                                        </td>
                                    </tr>
                            <%
                                    }//end check dto
                                }//end for
                                int sum = 0;
                                for (Double price : total_price) {
                                        sum += price;
                                }
                            %>
                            <tr>
                                <td colspan="3">
                                    <a href="shopping.jsp">Add More Books to Cart</a>
                                </td>
                                <td>
                                    <input type="submit" value="Remove" name="btAction" />
                                </td>
                                <td colspan="2">
                                    Total: <%= sum %>$
                                </td>
                                <td>
                                    <input type="submit" value="CheckOut" name="btAction" />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
        <%
            return;
                    }
                }//cart existed
            }//end session
        %>
        <h2>No cart is existed</h2>
        <br/>
        <a href="shopping.jsp">Buy More Books </a>
    </body>
</html