/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sio.leo.weblafbs.Controleur;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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
@WebServlet(name = "AboSuppr", urlPatterns = {"/AboSuppr"})
public class AboSuppr extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

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
        Integer matricule = Integer.valueOf(request.getParameter("matricule"));
            
            AbonneDAO abonneDao = new AbonneDAO();
        try {
            abonneDao.supprimeAbonne(matricule);
        } catch (SQLException ex) {
            Logger.getLogger(AboSuppr.class.getName()).log(Level.SEVERE, null, ex);
        }
            // Rediriger vers une page JSP pour afficher les informations détaillées
            response.sendRedirect("Abonne");
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
