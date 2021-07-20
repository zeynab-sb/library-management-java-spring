<%@ page import="org.springframework.web.client.RestTemplate" %>
<%@ page import="org.springframework.http.ResponseEntity" %>
<%@ page import="org.springframework.http.HttpStatus" %>
<%@ page import="org.apache.tomcat.util.json.JSONParser" %>
<%@ page import="net.minidev.json.JSONObject" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page import="com.fasterxml.jackson.databind.JsonNode" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%--<% Boolean haveCookie = false; %>--%>
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
//                }

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

        <h1 style="text-align:center; color: rgb(145, 8, 104)">Books</h1>
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
    </c:when>

    <c:otherwise>
        <h1>Please login</h1>
        <input type="button" value="Login" onclick="window.location='/login.jsp'" >
    </c:otherwise>
</c:choose>