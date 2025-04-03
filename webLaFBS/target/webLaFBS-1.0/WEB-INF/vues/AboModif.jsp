<%-- 
    Document   : AboModif
    Created on : 26 févr. 2025, 16:23:07
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
        <h2>Modification d'un abonné</h2>
        <form action="AboModif" method="post">
            
            <input type="hidden" name="matricule" value="${unAbonne.matricule}"/>
        
            <label for="nom">Nom:</label>
            <input type="text" id="nom" name="nom" value="${unAbonne.nom}" required/>
            <br/>

            <label for="prenom">Prénom:</label>
            <input type="text" id="prenom" name="prenom" value="${unAbonne.prenom}" required/>
            <br/>
            
            <label for="numero">Numéro de téléphone :</label>
            <input type="text" id="numero" name="numero" value="${unAbonne.telephone}" required/>
            <br/>
            
            <label for="mail">Adresse Mail :</label>
            <input type="text" id="mail" name="mail" value="${unAbonne.mail}" required/>
            <br/>
            
            <label for="ddn"> Date De Naissance :</label>
            <input type="date" id="ddn" name="ddn" value="${unAbonne.ddn}" required/>
        <br/>

        <button type="submit">Modifier</button>
    </form>