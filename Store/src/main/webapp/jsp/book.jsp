<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>

<%--<% Boolean haveCookie = false; %>--%>
<c:set var="haveCookie" value="false" />
<%
    Cookie[] cookies = null;
    cookies = request.getCookies();
    if( cookies != null ) {
        for (int i=0; i<cookies.length; i++){
            if (cookies[i].getName().equals("persist")){
                pageContext.setAttribute("haveCookie", "true");
                break;
            }
        }
    }
%>

<c:choose>
    <c:when test="${haveCookie.equals('true')}">
        <html lang="en">
        <head>
            <meta charset="ISO-8859-1">
            <title>Books</title>
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
                <th>Title</th>
                <th>Writer</th>
                <th>Date</th>
                <th>ISSN</th>
                <th>Image</th>
                <th>Keywords</th>
                <th>Delete</th>
                <th>Edit</th>
            </tr>


            <c:forEach items="${books}" var="book">
                <tr>
                    <td>${book.id}</td>
                    <td>${book.title}</td>
                    <td>${book.writers}</td>
                    <td>${book.date}</td>
                    <td>${book.ISSN}</td>
                    <td>${book.photo}</td>
                    <td>${book.keywords}</td>
                    <td>
                        <form method="GET" action="${pageContext.request.contextPath}/books/${book.id}/${id}">
                            <input type=submit value="Delete" style="width:100%;color:darkred">
                        </form>
                    </td>
                    <td>
                        <form action="/jsp/editbook.jsp" method="post">
                            <input  type="hidden" name="id" value="${book.id}" />
                            <input  type="hidden" name="title" value="${book.title}" />
                            <input  type="hidden" name="writer" value="${book.writers}" />
                            <input  type="hidden" name="issn" value="${book.ISSN}" />
                            <input  type="hidden" name="publisher" value="${id}" />
                            <input  type="hidden" name="image" value="${book.photo}" />
                            <input  type="hidden" name="keywords" value="${book.keywords}" />
                            <input  type="hidden" name="date" value="${book.date}" />

                            <input  type="submit" value="Edit"  style="width:100%"/>
                        </form>
                    </td>

                </tr>
            </c:forEach>
<%--            <input type="button" value="Add Book" onclick="window.location='/jsp/addbook.jsp'" >--%>
            <form method ="post" action="/jsp/addbook.jsp">
                <input type="hidden" name="publisher" value="${id}">
                <input type="submit" value="Add Book" ><br/>
            </form>
        </table>
        </body>
        </html>
    </c:when>

    <c:otherwise>
        <h1>Please login</h1>
        <input type="button" value="Login" onclick="window.location='/login.jsp'" >
    </c:otherwise>
</c:choose>