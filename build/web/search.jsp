<%-- 
    Document   : search
    Created on : Jun 6, 2022, 2:31:55 PM
    Author     : huy
--%>

<%@page import="huynq.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
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
        
        <h1 style="color: blue">SEARCH PAGE</h1>
        <form action="DispatchController">
            <input name="txtSearchValue" 
                   value="<%= request.getParameter("txtSearchValue")==null? "" : request.getParameter("txtSearchValue")%>">
            <input type="submit" value="Search" name="btAction" />
        </form><br>
        <% 
            String searchValue = request.getParameter("txtSearchValue");
            if(searchValue != null) {
                List<RegistrationDTO> result = (List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT");
                if (result != null) {
                    %>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Username</th>
                                <th>Password</th>
                                <th>Full Name</th>
                                <th>Role</th>
                                <th>Delete</th>
                                <th>Update</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <% 
                                    int count = 0;
                                    for(RegistrationDTO dto : result) {
                                        String urlRewriting = "DispatchController"
                                                                + "?btAction=delete"
                                                                + "&pk=" + dto.getUsername()
                                                                + "&lastSearchValue=" + searchValue; 
                                        %>
                        <form action="DispatchController" method="POST">
                                    <tr>
                                    <td>
                                        <%= ++count %>
                                    .</td>
                                    <td>
                                        <%= dto.getUsername()%>
                                        <input type="hidden" name="txtUsername" value="<%= dto.getUsername()%>" />
                                    </td>
                                    <td>
                                        <input type="text" name="txtPassword" value="<%= dto.getPassword()%>" />
                                    </td>
                                    <td><%= dto.getFullName() %></td>
                                    <td>
                                        <input type="checkbox" name="chkRole" value="ON" 
                                               <%
                                                   if(dto.isRole()){
                                                       %>
                                                    checked="checked"
                                               <%
                                                   }//end role is admin
                                               %>
                                               />
                                    </td>
                                    <td><a href="<%= urlRewriting %>">Delete</a></td>
                                    <td><input type="submit" value="Update" name="btAction">
                                        <input type="hidden" value="<%= searchValue %>" name="lastSearchValue">
                                    </td>
                                 </tr>
                        </form>
         
                                <%
                                    }//end traverse search result
                                %>
                            </tr>
                        </tbody>
                    </table>

        <%
                }else { //no result
                    %>
                    <h2> No result matched</h2>    
        <%
                }
            }//end search value is existed
        %>
    </body>
</html>
