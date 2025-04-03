/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sio.leo.weblafbs.Controleur;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Logger;
import sio.leo.weblafbs.Modele.Abonne;
import sio.leo.weblafbs.Modele.AbonneDAO;

/**
 *
 * @author sasag
 */
public class AboInfo extends HttpServlet {
    
    private static final Logger logger = Logger.getLogger(AbonneDAO.class.getName());
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
            logger.info("AbonneServlet doGet method called.");
            request.setAttribute("mainContent", "/WEB-INF/vues/AboInfo.jsp");
            request.setAttribute("pageTitle", "Les informations de l'abonné");
            logger.info("****************** LISTE ABONNES ******************");
                AbonneDAO abonneDAO = new AbonneDAO();
                List<Abonne> abonnes = abonneDAO.getAllAbonnes();
                
                abonnes.forEach(e->{
                    logger.info(e.toString());
                });
                request.setAttribute("abonnes", abonnes);
            
                
                logger.info("**************** INFORMATION DE L'ABONNé *************");
                //Integer id = Integer.valueOf(request.getParameter("id"));
                Integer id2 = Integer.valueOf(request.getParameter("matricule"));
                Integer selectedid = id2;
                Abonne unAbonne = abonneDAO.getUnAbonne(id2);
                //Abonne unAbonne2 = abonneDAO.getUnAbonne(id2);
                
                logger.info(unAbonne.toString());
                
                request.setAttribute("unAbonne", unAbonne);
                request.setAttribute("selectedid", selectedid);
        
                request.getRequestDispatcher("WEB-INF/Template/Header.jsp").forward(request, response);
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

                logger.info("AbonneServlet doPost method called.");
                
                request.setAttribute("mainContent", "/WEB-INF/vues/AboInfo.jsp");
                request.setAttribute("pageTitle", "Les informations de l'abonné");
                
                
                AbonneDAO abonneDAO = new AbonneDAO();
                List<Abonne> abonnes = abonneDAO.getAllAbonnes();
                
                abonnes.forEach(e->{
                    logger.info(e.toString());
                });
                request.setAttribute("abonnes", abonnes);
                
        //Integer id = Integer.valueOf(request.getParameter("id"));
        Integer id2 = Integer.valueOf(request.getParameter("abo"));
        Integer selectedid = id2;
                Abonne unAbonne = abonneDAO.getUnAbonne(id2);
                //Abonne unAbonne2 = abonneDAO.getUnAbonne(id2);
                
                logger.info(unAbonne.toString());
                
                request.setAttribute("unAbonne", unAbonne);
                request.setAttribute("selectedid", selectedid);
        
                request.getRequestDispatcher("WEB-INF/Template/Header.jsp").forward(request, response);
    }

}
