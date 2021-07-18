<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<body>
<center><h2>Add User Page</h2>
    <form method="POST" action="http://localhost:7070/books">
        title : <input type="text" name="Title"/><br><br>
        writer : <input type="text" name="Writer"/><br><br>
        keywords : <input type="text" name="Keywords"/><br><br>
        ISBN : <input type="text" name="ISSN"/><br><br>
        Date : <input type="text" name="Date"/><br><br>
        Image : <input type="text" name="Image"/><br><br>
        Publisher : <input type="text" name="Publisher"/><br><br>


        <input type="submit" name="Add" />
</center>
</body>
</html>