package serveurRestlet;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

/**
 *
 * @author plasse
 */
public class RestletApplication extends Application {
  @Override
  public synchronized Restlet createInboundRoot() {
      
    Router router = new Router(getContext());
     System.out.println("suce");
    //router.attach("/produits/{id}", ProduitResource.class);
    router.attach("/associations/{idAssociation}", CommandeResource.class);
    // Gerer les exceptions a notre facon
    //router.getApplication().setStatusService(new MyStatusService());
   
    
    
    
    
    return router;
  }
}
