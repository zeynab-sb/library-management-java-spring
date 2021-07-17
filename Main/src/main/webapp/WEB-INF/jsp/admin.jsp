<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <title>Users</title>
    <style>
        table, td {
            border: 1px solid rgb(194, 18, 194);
            border-collapse: collapse;
            background-color:#afc1d662; color:rgb(17, 14, 14);
            text-align: center;
        }
        th{
            color: rgb(145, 8, 104);
            background:#afc1d662;
        }
    </style>
</head>
<body>
<h1 style="text-align:center; color: rgb(145, 8, 104)">Users</h1>
<table style="width:100%">
    <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Authority</th>
        <th>Delete</th>
        <th>Edit</th>
    </tr>


    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.authority}</td>
            <td>
                <form method="GET" action="${pageContext.request.contextPath}/admin/users/${user.id}">
                    <input type=submit value="Delete" style="width:100%">
                </form>
            </td>
            <td><form><input type=submit value="Delete" style="width:100%"></form></td>

        </tr>
    </c:forEach>

    <td align="center">
        <input type="button" name="Add New User"
               onClientClick="window.open('adduser.jsp','popuppage','width=100%,toolbar=1,
        resizable=1,scrollbars=yes,height=700,top=100,left=100');" value="Add New User">
    </td>

</table>
</body>
</html>