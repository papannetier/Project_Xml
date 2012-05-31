<%-- 
    Document   : index
    Created on : 30 mai 2012, 14:04:14
    Author     : papou
--%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%
HashMap<String,String> winesName=null;
winesName = (HashMap<String,String>) request.getAttribute("winesName");

%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/Projet_xml/css/creation.css" type="text/css" />
        
        
        <script language="javascript" type="text/javascript">
            // le code suivant n'est pas mis dans une fonction pour que dès le chargement de la page, il s'exécute. 
            var requete=null;
       
            try{
                requete= new XMLHttpRequest();
            }
            catch(erreurReuqueteAJAX){
                requete=null;
            }
            if(requete == null){
                alert("Erreur de création de HttpRequest");
            }
            
        
        
        function getSearchWine(id){
            var searchWine= document.getElementById("rechercheWine").value;
            
            //var url="http://services.wine.com/api/beta/service.svc/xml/catalog?offset=0&size=1&apikey=2b5f9a2b3c37bafcc6247efccab5030b&search="+searchWine;
            var url="ServletReponseAJAX?id=" + escape(id);            
            requete.open("GET",url,true);
            requete.onreadystatechange=actualiserPage;
            requete.send(null);
        }
        function actualiserPage(){
            //alert(requete.readyState);
            //alert(requete.status);
            if (requete.readyState == 4){
                alert(requete.responseText);
            }
        }
        </script>
        
        
        <title>Page d'association wine-food</title>
    </head>
    <body>

        <div id="wine">
            <h1>WINE</h1>
            <div id="search">
                <h2>recherche</h2>
                <form action="ServletWineFood" method="post">
                    <input type ="text" name="rechercheWine" id="rechercheWine" />
                    <input type="submit" value="Rechercher"/>
                    <input type="hidden" name="action" value="rechercheWine"/>
                </form>
            </div>



            <div id="gg">
                <h3>résultat</h3> 
                        <%
                if (!(winesName == null)) {
                  %><table>
                         <tr>
                             <th>Nom des vins</th>
                         </tr>
                             
                    <%for (String mapKey : winesName.keySet()) {
                        out.println("<tr><td onclick='getSearchWine("+mapKey+")'>" + winesName.get(mapKey) +"</td></tr>");
                    }%>
                    </table>
               <%}%>  
       
            </div>
            <div id="gd">
                <h3>sélection</h3>   
            </div>

        </div>

        <div id="associer">
            <form action="ServletWineFood" method="get">
                <input type="submit" value="Associer"/>
                <input type="hidden" name="action" value="associer"/>
            </form>
        </div>

        <div id="food">
            
                <h1>FOOD</h1>
                <div id="search">
                    <h2>recherche</h2>
                    <form action="ServletWineFood" method="post">
                        <input type ="text" name="rechercheFood" id="rechercheFood" value=""/>
                        <input type="submit" value="Rechercher"/>
                        <input type="hidden" name="action" value="rechercheFood"/>
                    </form>
                </div>

            
                <div id="dg">
                  <h3>sélection</h3>  
                </div>
                <div id="dd">
                    <h3>résultat</h3>
                </div>
    </div>

</body>
</html>
