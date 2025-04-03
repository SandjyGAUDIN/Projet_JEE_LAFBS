/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sio.leo.weblafbs.Modele;

import java.sql.*; 
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
public class AbonneDAO {
    private static final Logger logger = Logger.getLogger(AbonneDAO.class.getName());
    
    public AbonneDAO(){ 
        
    }
    
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
    
    public Abonne getUnAbonne(Integer id) {
        String requeteSql = "select * from abonne where ABO_MATRICULE = " + id + ";";
        Abonne unAbonne = null;
        Integer matricule;
        String nom;
        String prenom;
        Date ddn;
        String telephone;
        String mail;
        Connection cnx=null;
        Statement stmt=null;
        ResultSet rs=null;
        
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
            rs = stmt.executeQuery(requeteSql); 
        } 
        catch (SQLException ex) { 
            Logger.getLogger(AbonneDAO.class.getName()).log(Level.SEVERE, null, ex); 
            System.out.println("pbm resultset"); 
        }
        
        try { 
            if (rs.next()){
                    matricule = rs.getInt("ABO_MATRICULE");
                    nom = rs.getString("ABO_NOM");
                    prenom = rs.getString("ABO_PRENOM");
                    ddn = rs.getDate("ABO_DDN");
                    telephone = rs.getString("ABO_TELEPHONE");
                    mail = rs.getString("ABO_MAIL");
                    unAbonne = new Abonne(matricule, nom, prenom, ddn, telephone, mail);
            }
        }
        catch (SQLException ex) { 
            Logger.getLogger(AbonneDAO.class.getName()).log(Level.SEVERE, null, ex); 
            System.out.println("pbm sur rs"); 
        }
        return unAbonne;
    }
    
    public void supprimeAbonne(int id) throws SQLException {
    String sql1 = "DELETE FROM abonne WHERE ABO_MATRICULE = ?";

    try (Connection cnx = getConnection();
         PreparedStatement pstmt1 = cnx.prepareStatement(sql1)) {

        pstmt1.setInt(1, id);
        int rowsAffected = pstmt1.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Utilisateur supprimé avec succès.");
        } else {
            System.out.println("Aucun utilisateur trouvé avec ce matricule.");
        }
    } catch (SQLException e) {
        System.err.println("Erreur lors de la suppression de l'abonné : " + e.getMessage());
        throw e; // Propagation de l'exception
    }
}

public void modifierAbonner(String nom, String prenom, String numero, String mail, String ddn, int id) throws SQLException {
    String sql1 = "UPDATE abonne SET ABO_NOM = ?, ABO_PRENOM = ?, ABO_TELEPHONE = ?, ABO_MAIL = ?, ABO_DDN = ? WHERE ABO_MATRICULE = ?;";
    LocalDate date = LocalDate.parse(ddn, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    try (Connection cnx = getConnection();
         PreparedStatement pstmt1 = cnx.prepareStatement(sql1)) {

        pstmt1.setString(1, nom);
        pstmt1.setString(2, prenom);
        pstmt1.setString(3, numero);
        pstmt1.setString(4, mail);
        pstmt1.setDate(5, java.sql.Date.valueOf(date));
        pstmt1.setInt(6, id);
        int rowsAffected = pstmt1.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Utilisateur modifié avec succès.");
        } else {
            System.out.println("Aucun utilisateur trouvé avec ce matricule.");
        }
    } catch (SQLException e) {
        System.err.println("Erreur lors de la modification de l'abonné : " + e.getMessage());
        throw e; // Propagation de l'exception
    }
}

public void AjouterAbonner (String nom, String prenom, String numero, String mail, String ddn) throws SQLException {
    String sql = "INSERT INTO abonne (ABO_NOM, ABO_PRENOM, ABO_TELEPHONE, ABO_MAIL, ABO_DDN) VALUES (?, ?,?,?,?);";
    LocalDate date = LocalDate.parse(ddn, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    
    try (Connection cnx = getConnection();
         PreparedStatement pstmt1 = cnx.prepareStatement(sql)) {

        pstmt1.setString(1, nom);
        pstmt1.setString(2, prenom);
        pstmt1.setString(3, numero);
        pstmt1.setString(4, mail);
        pstmt1.setDate(5, java.sql.Date.valueOf(date));
        int rowsAffected = pstmt1.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Utilisateur ajouté avec succès.");
        } else {
            System.out.println("Erreur");
        }
    } catch (SQLException e) {
        System.err.println("Erreur lors de l'ajout de l'abonné : " + e.getMessage());
        throw e; // Propagation de l'exception
    }
}

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public ArrayList<Abonne> getAllAbonnes() { 
        ArrayList<Abonne> lesAbonnes = new ArrayList<>(); 
        String requeteSql = "select * from abonne"; 
        Integer matricule; 
        String nom ; 
        String prenom ; 
        Date ddn ; 
        String telephone ; 
        String mail; 
        Connection cnx=null; 
        Statement stmt=null;
        ResultSet rs=null; 

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
            rs = stmt.executeQuery(requeteSql); 
        } 
        catch (SQLException ex) { 
            Logger.getLogger(AbonneDAO.class.getName()).log(Level.SEVERE, null, ex); 
            System.out.println("pbm resultset"); 
        }                   
        try { 
            while (rs.next()) { 
                matricule = rs.getInt("ABO_MATRICULE"); 
                nom = rs.getString("ABO_NOM"); 
                prenom = rs.getString("ABO_PRENOM"); 
                ddn = rs.getDate("ABO_DDN"); 
                telephone = rs.getString("ABO_TELEPHONE"); 
                mail = rs.getString("ABO_MAIL"); 
                Abonne abonne = new Abonne(matricule, nom, prenom, ddn, telephone, mail); 
                lesAbonnes.add(abonne); 
            } 
        }
        catch (SQLException ex) { 
            Logger.getLogger(AbonneDAO.class.getName()).log(Level.SEVERE, null, ex); 
            System.out.println("pbm sur rs"); 
        } 
        return lesAbonnes; 
    } 
}
            

