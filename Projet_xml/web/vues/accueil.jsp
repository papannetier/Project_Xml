<%-- 
    Document   : accueil
    Created on : 30 mai 2012, 15:48:36
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
        <h1>Bienvenue dans notre application pour les gourmands!</h1>
        <form action="ServletWineFood" method="post">
        <input type="submit" value="Consulter"/>
        <input type="hidden" name="action" value="redirectConsultation"/></br>
        </form>
        <form action="ServletWineFood" method="post">
        <input type="submit" value="Créer"/>
        <input type="hidden" name="action" value="redirectCreation"/>
        </form>
    </body>
</html>
