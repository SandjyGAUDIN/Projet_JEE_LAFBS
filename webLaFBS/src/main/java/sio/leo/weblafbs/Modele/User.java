/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sio.leo.weblafbs.Modele;

/**
 *
 * @author sasag
 */
public class User {
    private int id; 
    private String use_nom; 
    private String use_pass; 
    private String use_role; 

 

    // Constructeur 

    public User(int idUser, String user, String password, String role) { 
        this.id = idUser; 
        this.use_nom = user; 
        this.use_pass = password; 
        this.use_role = role; 
    } 
    
    public User(String user, String password, String role) {
        this.use_nom = user; 
        this.use_pass = password; 
        this.use_role = role;
    }
    
    // Getters
    public int getId() { return id; }
    public String getUseNom() { return use_nom; }
    public String getUsePass() { return use_pass; }
    public String getUseRole() { return use_role; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setUseNom(String use_nom) { this.use_nom = use_nom; }
    public void setUsePass(String use_pass) { this.use_pass = use_pass; }
    public void setUseRole(String use_role) { this.use_role = use_role; }
}
