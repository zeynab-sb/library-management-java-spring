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
        Title : <input type="text" name="Title"/><br><br>
        Writers : <input type="text" name="Writer"/><br><br>
        Keywords : <input type="text" name="Keywords"/><br><br>
        ISSN : <input type="text" name="ISSN"/><br><br>
        Date : <input type="text" name="Date"/><br><br>
        Image : <input type="text" name="Image"/><br><br>
        <input type="hidden" type="text" name="Publisher" readonly="readonly"
               value="<%=publisher%>"/><br><br>
        <input type="submit" name="Search" />
</center>
</body>
</html>