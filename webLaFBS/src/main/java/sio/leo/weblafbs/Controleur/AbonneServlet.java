/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sio.leo.weblafbs.Controleur;

import java.io.IOException;
// import java.io.PrintWriter;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import sio.leo.weblafbs.Modele.Abonne;
import sio.leo.weblafbs.Modele.AbonneDAO;
import java.util.logging.Logger;

/**
 *
 * @author sasag
 */
public class AbonneServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AbonneDAO.class.getName());

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
                request.setAttribute("mainContent", "/WEB-INF/vues/Abonnejsp.jsp");
                request.setAttribute("pageTitle", "Tout les abonnées");
                AbonneDAO abonneDAO = new AbonneDAO();
                List<Abonne> abonnes = abonneDAO.getAllAbonnes();
                
                abonnes.forEach(e->{
                    logger.info(e.toString());
                });
                request.setAttribute("abonnes", abonnes);
                request.getRequestDispatcher("WEB-INF/Template/Header.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                request.setAttribute("mainContent", "/WEB-INF/vues/AboInfo.jsp");
                request.setAttribute("pageTitle", "Les informations de l'abonné");
                
                Integer id = Integer.valueOf(request.getParameter("abo"));
                logger.info("AbonneServlet doPost method called.");
                AbonneDAO abonneDAO = new AbonneDAO();
                Abonne unAbonne = abonneDAO.getUnAbonne(id);
                
                logger.info(unAbonne.toString());
                
                request.setAttribute("unAbo", unAbonne);
                request.getRequestDispatcher("WEB-INF/Template/Header.jsp").forward(request, response);
        
    }
    
}
