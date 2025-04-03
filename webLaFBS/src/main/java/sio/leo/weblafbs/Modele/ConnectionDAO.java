/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sio.leo.weblafbs.Modele;

import static com.mysql.cj.conf.PropertyKey.logger;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author sasag
 */
public class ConnectionDAO {
    private Connection getConnection() throws SQLException { 
        InitialContext initialcontext = null; 
        DataSource datasource = null; 
        Connection connect = null; 

        try { 
            // Récupérer le contexte JNDI 
                initialcontext = new InitialContext(); 
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
}
