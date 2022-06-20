<%-- 
    Document   : create_account
    Created on : Jun 19, 2022, 10:02:37 AM
    Author     : huy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign up</title>
    </head>
    <body>
        <h1>Create Account</h1>
        <form action="DispatchController" method="POST">
            Username* <input type="text" name="txtUsername" value="" />(e.g) 6-20 char <br/>
            Password* <input type="password" name="txtPassword" value="" />(e.g) 6-30 char <br/>
            Confirm* <input type="password" name="txtConfirm" value="" /> <br/>
            Full Name* <input type="text" name="txtFullname" value="" />(e.g) 6-50 char <br/>
            <input type="submit" value="Create New Account" name="btAction" />
            <input type="reset" value="Reset"/>
        </form>
    </body>
</html>
