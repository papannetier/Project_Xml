package serveurRestlet;

import bean.*;
import connexionBD.*;
import dao.*;
import metiers.*;

import java.io.IOException;
import java.sql.SQLException;
import org.restlet.data.ChallengeRequest;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.CharacterSet;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.ext.xml.DomRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Ressource commande. Son URI est /commandes/{idCommande}, où idCommande est
 * l'identifiant de la commande. Seul l'utilisateur de login/mot de passe correspondant
 * à la ressource a le droit de la lire (GET).
 *
 * @author plasse
 */
public class CommandeResource extends ServerResource {
  /** Id de l'association */
  int idAssociation;

  /** Representation retournee */
  Representation resultat;
  
  /** Association sous-jacente */
  WineFood association;

  /** Verifie que le idCommande est entier */
  protected void init() {
    String idAttribute = getRequest().getAttributes().get("idAsssociation").toString();
    System.out.println("idCommande : " + idAttribute);
    try {
      idAssociation = Integer.parseInt(idAttribute);
    }
    catch (NumberFormatException exc) {
      // Indiquer que la requete est mal formee
      throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "idNotInteger");
    }
  }

  /**
   * L'utilisateur est-il autorise a acceder a la ressource ? Vrai quand il est
   * le client de la commande. <br/>Requiert isAuthenticated()et commande !=
   * null.
   *
   * @return
   */
  
  /*protected boolean isAuthorized() {
    assert isAuthenticated();
    assert association != null;
    String login = getRequest().getChallengeResponse().getIdentifier();
    String password = new String(getRequest().getChallengeResponse().getSecret());
    try {
      user = User.getByLoginPassword(login, password);
    }
    catch (SQLException exc) {
      throw new ResourceException(Status.SERVER_ERROR_INTERNAL, exc);
    }
    return (user != null && user.getId() == commande.getNoClient());
  }*/

  /** Le client est-il authentifié ? */
  /*public boolean isAuthenticated() {
    return getRequest().getChallengeResponse() != null;
  }*/

  /**
   * Indiquer dans la réponse qu'il faut s'authentifier pour le domaine "TP
   * Restlet". Dans un navigateur, ceci lancerait une popup demandant login et
   * mot de passe.
   */
  /*public void askAuthenticate() {
    ChallengeRequest auth = new ChallengeRequest(ChallengeScheme.HTTP_BASIC, "TP Restlet");
    getChallengeRequests().add(auth);
  }*/

  /** Représentation XML (en UTF-8) en réponse à une requête GET */
  @Get
  public Representation doGet() throws SQLException, IOException {
    init();
    /*if (!isAuthenticated()) {
      askAuthenticate();
      throw new ResourceException(Status.CLIENT_ERROR_UNAUTHORIZED);
    }*/
    //else {
      // Recuperer la commande
      AssociationBO associationBO=new AssociationBO();
      //association = associationBO.getById(idAssociation);
      if (association == null) {
        throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND);
      }
      // Le user est-il le client de la commande ?
      /*if (!isAuthorized()) {
        throw new ResourceException(Status.CLIENT_ERROR_UNAUTHORIZED);
      }*/
      // Produire le resultat
      DomRepresentation dom = new DomRepresentation(MediaType.TEXT_XML);
      // Encodage en UTF-8
      dom.setCharacterSet(CharacterSet.UTF_8);
      // Generer un DOM representant la ressource
      Document doc = dom.getDocument();
      Element root = doc.createElement("association");
      doc.appendChild(root);
      root.setAttribute("id", idAssociation + "");
      //root.setAttribute("date", commande.getDateValidation().toString());
      //root.setAttribute("idClient", commande.getNoClient() + "");
      resultat = dom;
    //}
    return resultat;
  }
}
