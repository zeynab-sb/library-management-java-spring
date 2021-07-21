<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<body>
<% String publisher=request.getParameter("publisher");  %>

<center><h2>Search Page</h2>
    <form method="POST" action="http://localhost:6060/search/books">
        Title : <input type="text" name="title"/><br><br>
        Writers : <input type="text" name="writers"/><br><br>
        Keywords : <input type="text" name="keywords"/><br><br>
        ISSN : <input type="text" name="ISSN"/><br><br>
        YearFrom : <input type="text" name="from"/><br><br>
        YearTo : <input type="text" name="to"/><br><br>
        Image : <input type="text" name="photo"/><br><br>
       Publisher: <input  type="text" name="publisher"/><br><br>
        <input type="submit" name="Search" />
</center>
</body>
</html>