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
    HashMap<String, String []> winesTotal = null;
    String foodList= null;
    winesTotal = (HashMap<String, String []>) session.getAttribute("winesTotal");
    foodList=(String) request.getAttribute("foodList");


%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/Projet_xml/css/creation.css" type="text/css" />
        <script src="/Projet_xml/js/prototype.js" type="text/javascript" ></script>

        <script type="text/javascript">
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

            
        
        
            function getSearchWine(id,name,nameRegion){
                                
                var id=id;
                var name=name;
                var nameRegion=nameRegion;

                //var url="http://services.wine.com/api/beta/service.svc/xml/catalog?offset=0&size=1&apikey=2b5f9a2b3c37bafcc6247efccab5030b&search="+searchWine;
                var url="ServletReponseAJAX?id=" + escape(id) +"&name=" + escape(name)+"&nameRegion="+ escape(nameRegion);            
                requete.open("GET",url,true);
                requete.onreadystatechange=actualiserPage;
                requete.send(null);
            }
            function actualiserPage(){
                //alert(requete.readyState);
                //alert(requete.status);
                if (requete.readyState == 4){
                    var attributs= requete.responseText;
                    var tabAttributs=attributs.split(",");

                    document.getElementById("name").value=tabAttributs[0];
                    document.getElementById("nameRegion").value=tabAttributs[1];
                    
                }
            }

        </script>
        <script type="text/javascript" >
            function getSearchFood(data) {
              
                
                var sfo = data.recipes;
                var tab = new Array();
                for (var i=0, j=data.count;i<j;i++){
                    console.log(sfo[i]);
                    tab.push(sfo[i].title,sfo[i].source_img);
                } 
                console.log(tab);
                return tab;
            }
            
        </script>
   
        <script type="text/javascript" src="http://api.punchfork.com/recipes?key=3f748f947e84fcda&q=<%=foodList%>&count=5&jsonp=getSearchFood">

        </script>

        <script  type="text/javascript">
                var getSearchFoodd = function(){
                    var searchFood = document.getElementById("rechercheFood").value;
                    alert(searchFood);
                    var url = "http://api.punchfork.com/recipes?key=3f748f947e84fcda&q="+searchFood+"&count=5&jsonp=parseResponse";
                    alert(url);
                    var myAjax = new Ajax.Request(url,{
                        on401: function() {
                        },
                        on404: function() {
                        },
                        onComplete: function(request) {
                            
                           console.log("ok");
                            alert("ok");
                            //alert(this.header('Content-type'));
                            //if(this.header('Content-type') == "application/json"){
                                alert("json");
                            
                            var foods = request.responseText.evalJSON();
                            console.log(foods);
                            alert(foods);
                        //}
                        } , // onSuccess
                        onFailure: function(){
                        }
                    }
                );
                };
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
                    if (!(winesTotal == null)) {
                %>
                <table>
                    <tr>
                        <th>Nom des vins </th>
                    </tr>

                    <%for (String mapKey : winesTotal.keySet()) {
                            out.println("<tr><td><input type=\"button\" id=\"buttonWineResponse\"  onclick=\"getSearchWine('"+ mapKey +"','"+ winesTotal.get(mapKey)[0]+ "','"+ winesTotal.get(mapKey)[1]+ "');\" value=\""+winesTotal.get(mapKey)[0]+"\" /></td></tr>");
                        }%>

                </table>
                <%}%>  

            </div>
            <div id="gd">
                <table>
                    <tr><td>Nom du vin</td></tr>
                    <tr><td><input type="text" id="name" value="" disabled="disabled" size="35"></td></tr>
                    <tr><td>Region</td></tr>
                    <tr><td><input type="text" id="nameRegion" value="" disabled="disabled" size="35"></td></tr>
                    
                    
                </table>  
            </div>

        </div>

        <div id="associer">
            <form action="ServletWineFood" method="post">
                <input type="submit" value="Associer"/>
                <input type="hidden" name="action" value="associer"/>
            </form>
        </div>

        <div id="food">

            <h1>FOOD</h1>
            <div id="search">
                <form action="ServletWineFood" method="post">
                <h2>recherche</h2>
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
                <div >
                    <ul id="field_champ"><li><%= foodList %></li></ul>
                </div>
            </div>
        </div>

    </body>
</html>
