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
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import sio.leo.weblafbs.Modele.UserDAO;

/**
 *
 * @author sasag
 */
public class UserConnection extends HttpServlet {

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
        
        request.setAttribute("mainContent", "/WEB-INF/vues/ConnectionUser.jsp");
        request.setAttribute("pageTitle", "Connection utilisateur");
        
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
        request.setAttribute("mainContent", "/WEB-INF/vues/ConnectionUser.jsp");
        request.setAttribute("pageTitle", "Connection utilisateur");
        
        String user = request.getParameter("user");
        String pass = request.getParameter("password");
        
        UserDAO userDAO = new UserDAO();
        try {
            if(userDAO.verifUser(user, pass) == false) {
                request.setAttribute("error", "Erreur de connexion. Veuillez réessayer.");
                request.getRequestDispatcher("WEB-INF/Template/Header.jsp").forward(request, response);
            }
            else {
                HttpSession laSession = request.getSession();
                laSession.setAttribute("role_user",userDAO.verifRole(user));
                // System.out.println(userDAO.verifRole(user)); //logger vérif
                response.sendRedirect("Abonne");
            }
        } catch (Exception ex) {
            Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

