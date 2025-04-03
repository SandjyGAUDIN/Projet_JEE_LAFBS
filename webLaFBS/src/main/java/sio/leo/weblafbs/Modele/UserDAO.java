/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sio.leo.weblafbs.Modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author sasag
 */
public class UserDAO {
    private static final Logger logger = Logger.getLogger(AbonneDAO.class.getName());
    
    private Connection getConnection() throws SQLException { 
        InitialContext initialcontext = null; 
        DataSource datasource = null; 
        Connection connect = null; 

        try { 
            // Récupérer le contexte JNDI 
                initialcontext = new InitialContext(); 
                logger.info("Loading initialContext..."); 
             // Vérifier si le contexte est valide 
                if (initialcontext == null) {
                    System.out.println("Le contexte JNDI est null"); 
        }
        // Rechercher la ressource DataSource configurée dans context.xml 
        datasource = (DataSource) initialcontext.lookup("java:/comp/env/jdbc/MaSourceDeDonnees"); 
        if (datasource == null) { 
        System.out.println("Le DataSource est null"); 
        }     
        connect=datasource.getConnection(); 
        if (connect == null) { 
        System.out.println("La connexion à la base de données est null"); 
        } 
        else {
        System.out.println("Connexion à la base de données établie avec succès"); 
        }     
        // Retourner la connexion à la base de données 
        return connect; 
        } 
        catch (SQLException | NamingException e) {
             throw new SQLException("Erreur lors de la récupération de la connexion à la base de données.", e); 
        } 
    }
    
    public void AjouterUser(String user, String password, String role) throws SQLException, Exception {
        Connection cnx = null;
        PreparedStatement stmt = null;
        String sql = "INSERT INTO user (use_nom, use_pass, use_role) VALUES (?, ?, ?);";
        String hashpass = Hasher.hashPassword(password);
        try { 
            cnx = getConnection(); 
            stmt = cnx.prepareStatement(sql);
            stmt.setString(1, user);
            stmt.setString(2, hashpass);
            stmt.setString(3, role);
            
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Utilisateur ajouté avec succès.");
            } else {
                System.out.println("Erreur");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de l'Utilisateur : " + e.getMessage());
            throw e; // Propagation de l'exception
        }
    }
    
    public boolean verifUser(String user, String pass) throws SQLException, Exception{
        Connection cnx = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String pwsql = "SELECT use_pass from user where use_nom = ?";

        try {
            cnx = getConnection();
            stmt = cnx.prepareStatement(pwsql);
            stmt.setString(1, user);
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {
            String hashedPassword = rs.getString("use_pass"); // Récupération du mot de passe haché
            System.out.println("Mot de passe haché récupéré : " + hashedPassword); // Debug

            return Hasher.verifyPassword(pass, hashedPassword); // Utilisation correcte
            }
        }
        catch (SQLException ex) { 
            Logger.getLogger(AbonneDAO.class.getName()).log(Level.SEVERE, null, ex); 
            System.out.println("pbm sur rs"); 
        } 
        finally { //fermeture de la connection pour éviter la fuite de mémoire.
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (cnx != null) cnx.close();
            } catch (SQLException ex) {
                System.out.println("Erreur fermeture connexion : " + ex.getMessage());
            }
        }
        return false;
    }
    
    public String verifRole(String user) throws SQLException, Exception{
        Connection cnx = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String role = null;
        String rlsql = "SELECT use_role from user where use_nom = ?";
        
        try {
            cnx = getConnection();
            stmt = cnx.prepareStatement(rlsql);
            stmt.setString(1, user);
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                role = rs.getString("use_role"); // Récupération du rôle de l'utilisateur
                System.out.println("role récupérer : " + role); // Debug
                
                return role; // Utilisation correcte
            }
        } catch(SQLException ex) {
            Logger.getLogger(AbonneDAO.class.getName()).log(Level.SEVERE, null, ex); 
            System.out.println("pbm sur rs"); 
        }
        finally { //fermeture de la connection pour éviter la fuite de mémoire.
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (cnx != null) cnx.close();
            } catch (SQLException ex) {
                System.out.println("Erreur fermeture connexion : " + ex.getMessage());
            }
        }
        return role;
    }
}
