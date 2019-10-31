<%-- 
    Document   : home
    Created on : 30-Oct-2019, 1:51:04 PM
    Author     : awarsyle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body> 
        <h1>Home</h1>
        <h2>Hi ${email}</h2>
        <div>
            <a href="users">Manage Users</a> | 
            <a href="login">Logout</a>
        </div>
    </body>
</html>
