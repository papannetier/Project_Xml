<%-- 
    Document   : index
    Created on : 30 mai 2012, 14:04:14
    Author     : papou
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/Projet_xml/css/creation.css" type="text/css" />
        <title>Page d'association wine-food</title>
    </head>
    <body>

        <div id="wine">
            <h1>WINE</h1>
            <div id="search">
                <h2>recherche</h2>
                <form action="ServletWineFood" method="get">
                    <input type ="text" name="rechercheWine" id="rechercheWine" value=""/>
                    <input type="submit" value="Rechercher"/>
                    <input type="hidden" name="action" value="rechercheWine"/>
                </form>
            </div>



            <div id="gg">
                <h3>selection</h3>  
            </div>
            <div id="gd">
                <h3>resultat</h3>
            </div>

        </div>

        <div id="associer">
            <form action="ServletWineFood" method="get">
                <input type="submit" value="Associer"/>
                <input type="hidden" name="action" value="associer"/>
            </form>
        </div>

        <div id="food">
            <form action="ServletWineFood" method="get">
                <h1>FOOD</h1>
                <div id="search">
                    <h2>recherche</h2>
                    <input type ="text" name="rechercheFood" id="rechercheWine" value=""/>
                    <input type="submit" value="Rechercher"/>
                    <input type="hidden" name="action" value="rechercheFood"/>
            </form>
                </div>

            
                <div id="dg">
                  <h3>resultat</h3>  
                </div>
                <div id="dd">
                    <h3>selection</h3>
                </div>
    </div>
</body>
</html>
