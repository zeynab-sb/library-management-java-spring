<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <style>
        table {
            width: auto;
            margin-left: auto;
            margin-right: auto;
        }
    </style>
    <title>
        Edit User Info
    </title>
</head>
<body>
<% String userid=request.getParameter("userid");  %>
<% String username=request.getParameter("username");  %>
<% String password=request.getParameter("password");  %>

<% String authoriry=request.getParameter("authority");  %>

<form method="POST" action="http://localhost:9090/admin/users/<%=userid%>">
<table>
    <tr>
        <td>User ID</td>
        <td><input type="text" name="userId" readonly="readonly"
                   value="<%=userid%>"></td>
    </tr>
    <tr>
        <td>UserName</td>
        <td><input type="text" name="username"
                   value="<%=username%>"/></td>
    </tr>
    <tr>
        <td>Authority</td>
        <td><input type="text" name="authority"
                   value="<%=authoriry%>"/></td>
    </tr>
    <tr>
        <td>Password</td>
        <td><input type="text" name="password"
                   value="<%=password%>"/></td>
    </tr>
    <tr>
        <td></td>
        <td>

            <input type="submit" value="Update" />

        </td>
    </tr>
</table>
</form>
</body>
</html>
