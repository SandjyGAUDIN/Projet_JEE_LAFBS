<%-- 
    Document   : Abonnejsp.jsp
    Created on : 9 janv. 2025, 15:48:28
    Author     : sasag
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
  String role = (String) session.getAttribute("role_user");
  
  if(role == null || role.equals("ABONNE")) {
    response.sendRedirect("UserConnection");
    return;
  }
%>
        <h2>Information d'un abonné</h2>
        <form method="post" id="unAbo">
        <select id="abo" form="unAbo" name="abo">
            <c:forEach var="abonnes" items="${abonnes}">
                <option value="${abonnes.matricule}" id="id2">${abonnes.nom}  ${abonnes.prenom}</option>
            </c:forEach>
        </select>
        <button type="submit">Details</button>
        </form>

        <br><br>
        <h2>Informations de l'abonné</h2>
        <table border="1">
            
            <thead>
                <th>Matricule</th>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Date de naissance</th>
                <th>Numéro de téléphone</th>
                <th>Adresse Mail</th>
            </thead>
            
            <tbody>
                    <tr>
                        <td>${unAbonne.matricule}</td>
                        <td>${unAbonne.nom}</td>
                        <td>${unAbonne.prenom}</td>
                        <td>${unAbonne.ddn}</td>
                        <td>${unAbonne.telephone}</td>
                        <td>${unAbonne.mail}</td>
                    </tr>
            </tbody>
        </table>
        
        <a href="Abonne">retour à la page principal</a>
