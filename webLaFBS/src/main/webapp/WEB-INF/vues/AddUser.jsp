<%-- 
    Document   : AddUser
    Created on : 5 mars 2025, 15:43:30
    Author     : sasag
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
  String role = (String) session.getAttribute("role_user");
  
  if(role == null|| role.equals("ABONNE")) {
    response.sendRedirect("UserConnection");
    return;
  }
  else if(role.equals("CONSULT")) {
    response.sendRedirect("Abonne");
  }
%>
        <h1>Page d'ajout d'un utilisateur</h1>
        <form action="AddUser" method="post">
        
            <label for="user">Nom d'utilisateur :</label>
            <input type="text" id="user" name="user" required/>
            <br/>

            <label for="password">Mot de passe :</label>
            <input type="password" id="password" name="password" required/>
            <br/>
            
            <label for="role">Rôle de l'utilisateur :</label>
            <input type="text" id="role" name="role" required/>
            <br/>

            <button type="submit">Ajouter</button>
        </form>
