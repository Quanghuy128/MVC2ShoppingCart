<%-- 
    Document   : error
    Created on : Jun 21, 2022, 8:48:32 AM
    Author     : huy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        <h1 color="red" text-align="center">Error May Be Existed</h1> <br/>
        <%
            request.getAttribute("ERROR");
        %>
    </body>
</html>
