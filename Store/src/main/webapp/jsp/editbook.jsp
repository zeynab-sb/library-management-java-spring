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
<% String date=request.getParameter("date");  %>




<form method="POST" action="http://localhost:9091/books/<%=id%>">
    <table>
        <tr>
            <td>Book ID</td>
            <td><input type="text" name="Id" readonly="readonly"
                       value="<%=id%>"></td>
        </tr>
        <tr>
            <td>Title</td>
            <td><input type="text" name="Title"
                       value="<%=title%>"/></td>
        </tr>
        <tr>
            <td>Writers</td>
            <td><input type="text" name="Writers"
                       value="<%=writer%>"/></td>
        </tr>
        <tr>
            <td>Keywords</td>
            <td><input type="text" name="Keywords"
                       value="<%=keywords%>"/></td>
        </tr>
        <tr>
            <td>ISSN</td>
            <td><input type="text" name="ISSN"
                       value="<%=issn%>"/></td>
        </tr>

        <tr>
            <td>Date</td>
            <td><input type="text" name="Date"
                       value="<%=date%>"/></td>
        </tr>

        <tr>
            <td>Image</td>
            <td><input type="text" name="Image"
                       value="<%=image%>"/></td>
        </tr>

        <tr>
            <td>Publisher</td>
            <td><input type="text" name="Publisher"
                       value="<%=publisher%>"/></td>
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