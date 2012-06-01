/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import java.io.*;
import java.text.Format;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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
    public static final String URL_WSF = "http://api.punchfork.com/recipes?key=3f748f947e84fcda";

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
        String action = request.getParameter("action");
        if (action == null) {
            doInit(request, response);
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
        String action = request.getParameter("action");
        System.out.println("doPost");
        System.out.println(action);
        if (action != null) {
            if (action.equals("redirectConsultation")) {
                getServletContext().getRequestDispatcher("/vues/consultation.jsp").forward(request, response);
            }
            if (action.equals("redirectCreation")) {
                getServletContext().getRequestDispatcher("/vues/creation.jsp").forward(request, response);
            }
            if (action.equals("rechercheWine")) {
                doXML(request, response);
            }
            if (action.equals("rechercheFood")) {
                doJSON(request, response);
            }
        }

    }

    public void doXML(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //PrintWriter out = response.getWriter();
        //response.setContentType("text/html; charset=utf-8");
        ClientResource resource = null;
        HashMap<String, String> winesName = new HashMap<String, String>();
        Element wine = null;
        try {// Preparer l'appel au service Web distant
            String search = request.getParameter("rechercheWine");
            resource = new ClientResource(URL_WS + "?offset=0&size=5&apikey=2b5f9a2b3c37bafcc6247efccab5030b&search=" + search);
            // Recuperer la reponse en arbre DOM
            System.out.println("test "+resource.get());
            Document xml = resource.get(Document.class);
            //out.println("<ul>");
            NodeList wines = xml.getElementsByTagName("Product");

            for (int i = 0; i < wines.getLength(); i++) {
                wine = (Element) wines.item(i);
                String name = (String) wine.getFirstChild().getNextSibling().getTextContent();
                String id = (String) wine.getFirstChild().getTextContent();

                winesName.put(id, name);
                //System.out.println(winesName.isEmpty());
                /*
                 * out.println("<li>");
                 * out.println(wine.getFirstChild().getNextSibling().getTextContent());
                 * out.println("</li>");
                 */
            }

            //out.println("</ul>");
        } catch (ResourceException exc) {
            System.out.println("Erreur : " + exc.getStatus().getCode() + " ("
                    + exc.getStatus().getDescription() + ") : "
                    + resource.getResponseEntity().getText());
            /*
             * out.println("Erreur : " + exc.getStatus().getCode() + " (" +
             * exc.getStatus().getDescription() + ") : " +
             * resource.getResponseEntity().getText());
             */
        }

        request.setAttribute("winesName", winesName);
        getServletContext().getRequestDispatcher("/vues/creation.jsp").forward(request, response);

    }

    public void doJSON(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         ClientResource resource = null;
//        HashMap<String, String> winesName = new HashMap<String, String>();
        Element food = null;
        try {// Preparer l'appel au service Web distant
            String search = request.getParameter("rechercheFood");
            resource = new ClientResource(URL_WSF + "?key=3f748f947e84fcda&q="+search+"&count=5");
            // Recuperer la reponse en arbre DOM
            System.out.println("test "+resource.get());
            Document json = resource.get(Document.class);
            System.out.println("test2 "+json);
        } catch(Exception e){}
        String searchFood = request.getParameter("rechercheFood");
//        System.out.println(searchFood);
         request.setAttribute("foodList", searchFood);
        getServletContext().getRequestDispatcher("/vues/creation.jsp").forward(request, response);

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

    public void doInit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/vues/accueil.jsp").forward(request, response);
    }
}
