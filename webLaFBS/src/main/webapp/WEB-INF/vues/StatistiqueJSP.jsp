<%-- 
    Document   : StatistiqueJSP
    Created on : 28 févr. 2025, 15:39:05
    Author     : sasag
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
  String role = (String) session.getAttribute("role_user");
  
  if(role == null || role.equals("ABONNE")) {
    response.sendRedirect("UserConnection");
    return;
  }
%>
        <h1>Page pour choisir la statistique voulu</h1>
        
        <h3>Fréquentation des activités par salle</h3>
        <br/>
        <form action="StatsFreqActivSalle" method="post"> <!-- action = nom de la servlet souhaiter -->
            <label for="dateDebut1"> Date De Début pour la statistique :</label>
            <input type="date" id="dateDebut1" name="dateDebut1" required/> <!-- name sera utilisé pour la Servlet StatFreqActivSalle et pas l'ID -->
            <label for="dateFin1"> Date de Fin pour la statistique :</label>
            <input type="date" id="dateFin1" name="dateFin1" required/>
        <br/>
            <button type="submit">Résultat statistique 1</button>
        </form>
        <br/>
        
        <h3>Statistique pour les inscriptions par activités (toutes salles)</h3>
        <br/>
        <form action="Statistique_deux" method="post">
            <label for="dateDebut2"> Date De Début pour la statistique :</label>
            <input type="date" id="dateDebut2" name="dateDebut2" required/>
            <label for="dateFin2"> Date de fin pour la statistique : </label>
            <input type="date" id="dateFin2" name="dateFin2" required/>
        <br/>

            <button type="submit">Résultat statistique 2</button>
        </form>
        <br/>
        
        <h3>Statistique sans renseignement particulier</h3>
        <p>Nombre d'abonner inscrit par activité</p>
        
        <a href="StatistiqueNoIn">
            <button>Resultat sans date</button>
        </a>
        
        <a href="AbonneServlet">
            <button>Retour à la liste d'abonner</button>
        </a>
