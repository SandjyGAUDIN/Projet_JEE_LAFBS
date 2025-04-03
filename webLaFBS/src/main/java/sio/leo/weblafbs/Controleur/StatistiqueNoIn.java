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
public class StatistiqueNoIn extends HttpServlet {

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
        
        request.setAttribute("mainContent", "/WEB-INF/vues/StatNoIn.jsp");
        request.setAttribute("pageTitle", "Liste des statistiques");
        
        StatistiqueDAO statDAO = new StatistiqueDAO();
        String result = statDAO.Statistique1();
        ArrayList<String[]> ListStat = statDAO.Statistique1Array();
        
        System.out.println("Données envoyés à la JSP :" + ListStat.size());
        
        request.setAttribute("result", result);
        request.setAttribute("ListStat", ListStat);
        
        request.getRequestDispatcher("WEB-INF/Template/Header.jsp").forward(request, response);
    }

}
