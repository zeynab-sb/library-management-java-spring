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
        Edit Book Info
    </title>
</head>
<body>
<% String id=request.getParameter("id");  %>
<% String title=request.getParameter("title");  %>
<% String writer=request.getParameter("writer");  %>

<% String issn=request.getParameter("issn");  %>
<% String publisher=request.getParameter("publisher");  %>
<% String image=request.getParameter("image");  %>
<% String keywords=request.getParameter("keywords");  %>



<form method="POST" action="http://localhost:7070/books/<%=id%>">
    <table>
        <tr>
            <td>User ID</td>
            <td><input type="text" name="Id" readonly="readonly"
                       value="<%=id%>"></td>
        </tr>
        <tr>
            <td>UserName</td>
            <td><input type="text" name="title"
                       value="<%=title%>"/></td>
        </tr>
        <tr>
            <td>Authority</td>
            <td><input type="text" name="writers"
                       value="<%=writer%>"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="text" name="issn"
                       value="<%=issn%>"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="text" name="publisher"
                       value="<%=publisher%>"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="text" name="image"
                       value="<%=image%>"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="text" name="keywords"
                       value="<%=keywords%>"/></td>
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