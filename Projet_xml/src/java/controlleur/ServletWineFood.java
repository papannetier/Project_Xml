/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Form;
import org.restlet.representation.InputRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


/**
 *
 * @author admin
 */
public class ServletWineFood extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    public static final String URL_WS = "http://services.wine.com/api/beta/service.svc/xml/catalog";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /*
             * TODO output your page here. You may use following sample code.
             */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet servletWineFood</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet servletWineFood at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        PrintWriter out = response.getWriter();
        response.setContentType("text/html; charset=utf-8");
        ClientResource resource = null;
        try {// Preparer l'appel au service Web distant
            resource = new ClientResource(URL_WS + "?offset=0&size=5&apikey=2b5f9a2b3c37bafcc6247efccab5030b&search=merlot%202007%20oregon");
            // Recuperer la reponse en arbre DOM
            Document xml = resource.get(Document.class);
            out.println("<ul>");
            NodeList wines = xml.getElementsByTagName("Region");
            for (int i = 0; i < wines.getLength(); i++) {
            Element wine = (Element) wines.item(i);
            out.println("<li>");
            out.println(wine.getNodeValue());
            out.println("</li>");
            }
            /*for (int i = 0; i < wines.getLength(); i++) {
                out.println("<li>");
                Element wine = (Element) wines.item(i);
                out.println(wine.getAttribute("prenom"));
                out.println(" ");
                out.println(wine.getAttribute("nom"));
                out.println("</li>");
            }*/
            out.println("</ul>");
        } catch (ResourceException exc) {
            out.println("Erreur : " + exc.getStatus().getCode() + " ("
                    + exc.getStatus().getDescription() + ") : "
                    + resource.getResponseEntity().getText());
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
