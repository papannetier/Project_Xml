<%-- 
    Document   : vueJson
    Created on : 1 juin 2012, 01:55:13
    Author     : papou
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
            String key = request.getParameter("key") ;
            String q = request.getParameter("q") ;
            String count = request.getParameter("count") ;
            String urlFile = "http://api.punchfork.com/recipes?key=" +key+"&q="+ q +"&count="+count;
        %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %><%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
        <c:import url="http://www.t.com" />

    </body>
</html>
