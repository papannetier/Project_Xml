  
        // Reaction au bouton DOM
        document.getElementById("boutonDOM").onclick = function() {
          // Recuperer l'element HTML de id "resultat"
          var div = document.getElementById("resultat");
          // Mettre son contenu a vide
          div.innerHTML = "";
          // Creer un element HTML de type "p"
          var noeud = document.createElement("p");
          noeud.innerHTML = "Un paragraphe";
          // Le rattacher au div
          div.appendChild(noeud);
          // Creer un autre element p
          noeud = document.createElement("p");
          // Lui ajouter un attribut
          noeud.setAttribute("class", "clair");
          // Creer un noeud texte
          var noeudTexte = document.createTextNode("Un autre p de classe CSS 'clair'");
          // Le rattacher au paragraphe noeud
          noeud.appendChild(noeudTexte);
          // Rattacher noeud au div
          div.appendChild(noeud);
          // Memes traitements avec un un et des li
          var ul = document.createElement("ul");
          for (var i=0 ; i<3; i++) {
            noeud = document.createElement("li");
            noeud.innerHTML = "Puce n° " + (i+1);
            ul.appendChild(noeud);
          }
          div.appendChild(ul);
          // Suppprimer le dernier enfant (le dernier li) du ul
          ul.removeChild(ul.lastChild);
        }
 
      // Permettre new XMLHttpRequest() dans IE
      if (!window.XMLHttpRequest && window.ActiveXObject) {
        window.XMLHttpRequest = function() {
          return new ActiveXObject("Microsoft.XMLHTTP");
        }
      }

        // Reaction au bouton Ajax
        document.getElementById("boutonAjax").onclick = function() {
        // Instancier une requete HTTP en JavaScript (objet XMLHttpRequest)
        var requete = new XMLHttpRequest();
        // Ouvrir une connexion asynchrone vers l'url communes.xml
        requete.open("GET", document.getElementById("url").value, true);
        // Traitement a effectuer quand l'etat de la requete changera
        requete.onreadystatechange = function() {
          if (requete.readyState == 4) {
            if (requete.status == 200) {
              // La reponse est arrivee completement (readyState 4) et est OK
              traiterReponseAjax(requete);
            }
            else {
              document.getElementById("resultat").innerHTML = "Document pas récupéré : erreur "
                + requete.status;
            }
          }
        }
        // Montrer que la requete est en cours
        document.getElementById("resultat").innerHTML = "Attendre svp";
        // Lancer la requete avec un corps vide
        requete.send("");
      }

      // Reagir a l'arrivee du document XML en placant toutes les communes
      // trouvees dans une liste a puces.
      function traiterReponseAjax(reponseHTTP) {
        //alert(reponseHTTP.responseText);
        // Element racine du DOM de la reponse
        var racine = reponseHTTP.responseXML.documentElement
        var communes = racine.getElementsByTagName("commune");
        // Modifier le HTML
        var div = document.getElementById("resultat");
        div.innerHTML = "Il y a " + communes.length + 
          " communes de code postal " + racine.getAttribute("cp");
        var ul = document.createElement("ul");
        div.appendChild(ul);
        for (var i=0 ; i<communes.length ; i++) {
          var li = document.createElement("li");
          li.innerHTML = communes[i].firstChild.nodeValue;
          ul.appendChild(li);
        }
      }
      



