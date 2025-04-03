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
import java.util.ArrayList;
import sio.leo.weblafbs.Modele.StatistiqueDAO;

/**
 *
 * @author sasag
 */
public class StatsFreqActivSalle extends HttpServlet {

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
        request.setAttribute("mainContent", "/WEB-INF/vues/StatFreqActSalle.jsp");
        request.setAttribute("pageTitle", "Résultat statistique fréquence salle");
        
        StatistiqueDAO statDAO = new StatistiqueDAO(); //appel du DAO Statistique
        
        String datedebut = request.getParameter("dateDebut1"); //récupére la donnée de la JSP StatistiqueJSP, il utilise l'information "name = "dateDebut1"
        String datefin = request.getParameter("dateFin1");
        ArrayList<String[]> ListStat = statDAO.Statistique2Array(datedebut, datefin);
        
        System.out.println("Données envoyés à la JSP :" + ListStat.size()); //log pour voir si des informations sont envoyées à la Servlet
        
        request.setAttribute("ListStat", ListStat);//permet d'attribuer les informations reçu par la servet à un truc de la JSP
        request.getRequestDispatcher("WEB-INF/Template/Header.jsp").forward(request, response); //fais appel à la JSP
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
        
        request.setAttribute("mainContent", "/WEB-INF/vues/StatFreqActSalle.jsp");
        request.setAttribute("pageTitle", "Résultat statistique fréquence salle");
        
        StatistiqueDAO statDAO = new StatistiqueDAO(); //appel du DAO Statistique
        
        String datedebut = request.getParameter("dateDebut1"); //récupére la donnée de la JSP StatistiqueJSP, il utilise l'information "name = "dateDebut1"
        String datefin = request.getParameter("dateFin1");
        ArrayList<String[]> ListStat = statDAO.Statistique2Array(datedebut, datefin);
        
        System.out.println("Données envoyés à la JSP :" + ListStat.size()); //log pour voir si des informations sont envoyées à la Servlet
        
        request.setAttribute("ListStat", ListStat);//permet d'attribuer les informations reçu par la servet à un truc de la JSP
        request.getRequestDispatcher("WEB-INF/Template/Header.jsp").forward(request, response); //fais appel à la JSP
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
