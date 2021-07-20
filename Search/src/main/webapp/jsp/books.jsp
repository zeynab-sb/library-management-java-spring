<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



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

        <h1 style="text-align:center; color: rgb(145, 8, 104)">Books</h1>

        <form method="GET" action="http://localhost:9090/reader/books">
            <input type=submit value="Back" style="width:100%;color:darkred">
        </form>
        <br>
        </td>
        <table style="width:100%">
            <tr>
                <th>Title</th>
                <th>Writers</th>
                <th>Date</th>
                <th>ISSN</th>
                <th>Image</th>
                <th>Keywords</th>
                <th>Create Date Time</th>
                <th>Book Info</th>
            </tr>


            <c:forEach items="${books}" var="book">
                <tr>
                    <td>${book.title}</td>
                    <td>${book.writers}</td>
                    <c:set var = "date" value = '${book.date}'/>
                    <td>${fn:substring(date, 0, 10)}</td>
                    <td>${book.ISSN}</td>
                    <td> <img alt="Book" src=${book.photo}
                            width=150" height="70"></td>
                    <td>${book.keywords}</td>
                    <td>${book.createDateTime}</td>
                    <td><form method="GET" action="${pageContext.request.contextPath}/reader/bookinfo/${book.id}/${id}">
                        <input type=submit value="Book Info" style="width:100%;color:darkred">
                    </form></td>
                </tr>
            </c:forEach>
        </table>

        </body>
        </html>