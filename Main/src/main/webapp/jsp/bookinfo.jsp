<%@ page import="org.springframework.web.client.RestTemplate" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page import="com.fasterxml.jackson.databind.JsonNode" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="haveCookie" value="false" />
<c:set var="isReader" value="false" />
<c:set var="id" value="1" />

<%
    Cookie[] cookies = null;
    cookies = request.getCookies();
    if( cookies != null ) {
        String sessionId = null;
        for (int i=0; i<cookies.length; i++){
            if (cookies[i].getName().equals("persist")){
                pageContext.setAttribute("haveCookie", "true");
                sessionId = cookies[i].getValue();

                RestTemplate restTemplate = new RestTemplate();
                String URL = "http://localhost:8080/auth/authenticate";
                String res = restTemplate.getForObject(URL + '/' + sessionId, String.class);

                ObjectMapper jacksonObjMapper = new ObjectMapper();
                JsonNode jsonObj = jacksonObjMapper.readTree(res);

                pageContext.setAttribute("id", jsonObj.get("id"));
                pageContext.setAttribute("isReader", "true");
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
            <title>${book.title}</title>
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

        <br>Title: ${book.title}<br>
        <br>Writers: ${book.writers}<br>
        <br>Date: ${book.date}<br>
        <br>ISSN: ${book.ISSN}<br>
        <br>Image: <img alt="Book" src=${book.photo}
                width=150" height="70"><br>
        <br>Keywords: ${book.keywords}<br>
        <br>Create Date Time: ${book.createDateTime}<br>

        <div  align="center" style="max-width:380px; border:2px solid #ccc;">
            <form method="POST" action="${pageContext.request.contextPath}/reader/addcomment">
                Comment : <input type="text" name="text" /><br><br>
                <input type="hidden" name="userid" value=${userid} /><br><br>
                <input type="hidden" name="bookid" value=${book.id} /><br><br>
                <input type=submit value="Send" style="width:50%;color:darkred">
            </form>
        </div>


        <table style="width:100%">
            <tr>
                <th>Username</th>
                <th>Comment</th>
                <th>Date</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>

            <c:forEach items="${comments}" var="comment">
                <tr>
                    <td>${comment.user.username}</td>
                    <td>${comment.text}</td>
                    <td>${comment.createDateTime}</td>
                    <c:if test="${comment.user.id == userid}">
                        <td>
                        <form method="POST" action="/jsp/editcomment.jsp">
                            <input  type="hidden" name="username" value="${comment.user.username}" />
                            <input  type="hidden" name="text" value="${comment.text}" />
                            <input  type="hidden" name="createtime" value="${comment.createDateTime}" />
                            <input  type="hidden" name="userid" value="${userid}" />
                            <input  type="hidden" name="bookid" value="${book.id}" />
                            <input  type="hidden" name="commentid" value="${comment.id}" />

                            <input type=submit value="Edit" style="width:100%;color:darkred">
                        </form></td>
                        <td>
                        <form method="POST" action="${pageContext.request.contextPath}/reader/deletecomment">
                            <input type="hidden" name="bookid" value=${book.id} />
                            <input type="hidden" name="userid" value=${userid} />
                            <input type="hidden" name="id" value=${comment.id} />
                            <input type=submit value="Delete" style="width:100%;color:darkred">
                        </form>
                        </td>
                    </c:if>
                </tr>
                </c:forEach>
        </table>
        </body>
        </html>
    </c:when>

    <c:otherwise>
        <h1>Please login</h1>
        <input type="button" value="Login" onclick="window.location='/login.jsp'" >
    </c:otherwise>
</c:choose>