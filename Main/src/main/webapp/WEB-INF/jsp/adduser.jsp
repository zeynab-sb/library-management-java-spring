<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<body>
<center><h2>Add User Page</h2>
    <form method="POST" action="http://localhost:8080/auth/login">
        Username : <input type="text" name="username" value="username" /><br><br>
        Password : <input type="password" name="password" value="password" /><br><br>
        <input type="submit" name="Login" />
</center>
</body>
</html>