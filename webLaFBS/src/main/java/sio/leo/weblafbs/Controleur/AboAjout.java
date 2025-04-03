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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sio.leo.weblafbs.Modele.AbonneDAO;

/**
 *
 * @author sasag
 */
public class AboAjout extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

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
        
            request.setAttribute("mainContent", "/WEB-INF/vues/AboAjoutJSP.jsp");
            request.setAttribute("pageTitle", "Ajout d'un abonné");
            
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
            request.setAttribute("mainContent", "/WEB-INF/vues/Abonnejsp.jsp");
            request.setAttribute("pageTitle", "Liste des abonnées");
            

        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String numero = request.getParameter("numero");
        String mail = request.getParameter("mail");
        String ddn = request.getParameter("ddn");
            
            AbonneDAO abonneDao = new AbonneDAO();
        try {
            abonneDao.AjouterAbonner(nom, prenom, numero, mail, ddn);
        } catch (SQLException ex) {
            Logger.getLogger(AboSuppr.class.getName()).log(Level.SEVERE, null, ex);
        }
            // Rediriger vers une page JSP pour afficher les informations détaillées
            request.getRequestDispatcher("WEB-INF/Template/Header.jsp").forward(request, response);
    }
}

