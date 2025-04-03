/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sio.leo.weblafbs.Modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author sasag
 */
public class StatistiqueDAO {
    private static final Logger logger = Logger.getLogger(StatistiqueDAO.class.getName());
    
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
    
    public String Statistique1(){
        ArrayList<String> listStat1 = new ArrayList<>();
        Connection cnx = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "CALL prc_inscritparactiviter();";
        try { 
            cnx = getConnection(); 
        } 
        catch (SQLException ex) { 
            System.out.println("pbm connexion"); 
        } 

        try { 
            stmt = cnx.createStatement(); 
        } 

        catch (SQLException e) { 
            e.getMessage(); 
            System.out.println("statement échoué"); 
        } 

        try { 
            rs = stmt.executeQuery(sql); 
        } 
        catch (SQLException ex) { 
            Logger.getLogger(AbonneDAO.class.getName()).log(Level.SEVERE, null, ex); 
            System.out.println("pbm resultset"); 
        }                   
        try { 
            while (rs.next()) { 
                String activiter = rs.getString("Nom_Activiter");
                String nombre = rs.getString("Nombre_Inscrit");
                listStat1.add(activiter + " : " + nombre);
            } 
        }
        catch (SQLException ex) { 
            Logger.getLogger(AbonneDAO.class.getName()).log(Level.SEVERE, null, ex); 
            System.out.println("pbm sur rs"); 
        }
        return String.join("\n",listStat1);
    }
    
    public ArrayList<String[]> Statistique2Array(String datedebut, String datefin){ //Fréquentation des activités par salle (nom salle/nom activité/nombre de participants)
        ArrayList<String[]> ListStat2 = new ArrayList<>();
        LocalDate date1 = LocalDate.parse(datedebut, DateTimeFormatter.ofPattern("yyyy-MM-dd")); //transforme les informations données en format date (venant de la JSP statistiques
        LocalDate date2 = LocalDate.parse(datefin, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Connection cnx = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "CALL prc_FreqActivParSalle(?, ?);"; //procédure : en bas de code
        
        try { 
            cnx = getConnection(); 
            stmt = cnx.prepareStatement(sql);
            stmt.setString(1, datedebut);
            stmt.setString(2, datefin);
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                String[] t1 = new String[3];
                t1[0] = rs.getString("Nom_Salle"); //AS de la requête dans la procédure
                t1[1] = rs.getString("Nom_Activiter");
                t1[2] = rs.getString("Fréquentation_Activiter");
                ListStat2.add(t1);
            } 
            
            System.out.println("Données récupérées :");
            for (String[] row : ListStat2) {
                System.out.println(row[0] + " - " + row[1] + " - " + row[2]);
            }
        }
        catch (SQLException ex) { 
            Logger.getLogger(AbonneDAO.class.getName()).log(Level.SEVERE, null, ex); 
            System.out.println("pbm sur rs"); 
        } finally { //fermeture de la connection pour éviter la fuite de mémoire.
            try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (cnx != null) cnx.close();
        } catch (SQLException ex) {
                System.out.println("Erreur fermeture connexion : " + ex.getMessage());
            }
        }
        return ListStat2;
    }
    
    
    
    
    
    
    public ArrayList<String[]> Statistique1Array(){ //inscrit par activité sans information demandé 
        ArrayList<String[]> listStat1 = new ArrayList<>();
        Connection cnx = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "CALL prc_inscritparactiviter();";
        try { 
            cnx = getConnection(); 
        } 
        catch (SQLException ex) { 
            System.out.println("pbm connexion"); 
        } 

        try { 
            stmt = cnx.createStatement(); 
        } 

        catch (SQLException e) { 
            e.getMessage(); 
            System.out.println("statement échoué"); 
        } 

        try { 
            rs = stmt.executeQuery(sql); 
        } 
        catch (SQLException ex) { 
            Logger.getLogger(AbonneDAO.class.getName()).log(Level.SEVERE, null, ex); 
            System.out.println("pbm resultset"); 
        }                   
        try { 
            while (rs.next()) {
                String[] t1 = new String[2];
                t1[0] = rs.getString("Nom_Activiter");
                t1[1] = rs.getString("Nombre_Inscrit");
                listStat1.add(t1);
            } 
            
            System.out.println("Données récupérées :");
            for (String[] row : listStat1) {
                System.out.println(row[0] + " - " + row[1]);
            }
        }
        catch (SQLException ex) { 
            Logger.getLogger(AbonneDAO.class.getName()).log(Level.SEVERE, null, ex); 
            System.out.println("pbm sur rs"); 
        } finally {
            try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (cnx != null) cnx.close();
        } catch (SQLException ex) {
                System.out.println("Erreur fermeture connexion : " + ex.getMessage());
            }
        }
        return listStat1;
    }
}


