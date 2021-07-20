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
<% String username=request.getParameter("username");  %>
<% String text=request.getParameter("text");  %>
<% String createtime=request.getParameter("createtime");  %>

<% String bookid=request.getParameter("bookid");  %>
<% String userid=request.getParameter("userid");  %>
<% String commentid=request.getParameter("commentid");  %>

<form method="POST" action="http://localhost:9090/reader/editcomment/<%=commentid%>">
  <table>
    <tr>
      <td>User ID</td>
      <td><input type="text" name="userid" readonly="readonly"
                 value="<%=userid%>"/></td>
    </tr>
    <tr>
      <td>Book ID</td>
      <td><input type="text" name="bookid" readonly="readonly"
                 value="<%=bookid%>"/></td>
    </tr>
    <tr>
      <td>User Name</td>
      <td><input type="text" name="username" readonly="readonly"
                 value="<%=username%>"></td>
    </tr>
    <tr>
      <td>Text</td>
      <td><input type="text" name="text"
                 value="<%=text%>"/></td>
    </tr>
    <tr>
      <td>Created At</td>
      <td><input type="text" name="createtime" readonly="readonly"
                 value="<%=createtime%>"/></td>
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
