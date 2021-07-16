<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 7/16/2021
  Time: 8:48 PM
  To change this template use File | Settings | File Templates.
--%>



<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title></head>
<body>
<center><h2>Login Page</h2>
    <form method="POST" action="http://localhost:8080/auth/login">
        Username : <input type="text" name="username" value="username" /><br><br>
        Password : <input type="password" name="password" value="password" /><br><br>
        <input type="submit" name="Login" />
</center>
</body>
</html>

