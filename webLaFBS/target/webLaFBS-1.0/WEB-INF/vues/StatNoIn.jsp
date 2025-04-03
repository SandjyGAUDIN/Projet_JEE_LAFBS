<%-- 
    Document   : StatNoIn
    Created on : 4 mars 2025, 14:24:50
    Author     : sasag
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- Necessaire pour le c:foreach -->
<%
  String role = (String) session.getAttribute("role_user");
  
  if(role == null || role.equals("ABONNE")) {
    response.sendRedirect("UserConnection");
    return;
  }
%>
        <h1>Résultat Statistique le nombre d'inscrit par activité sans date demandé</h1>
        <p>${result}</p> <!-- Test récupération des données déjà dans un paragraphe -->
        
        <table border="1">
            
            <thead>
                <tr> <!-- Entête du tableau -->
                    <th>Nom Activité :</th>
                    <th>Nombre d'inscrit :</th>
                </tr>
            </thead>
            
            <tbody>
                <c:forEach var="row" items="${ListStat}"> <!-- le foreach permet de faire le tour des informations dans la list, items permet de récupérer la liste-->
                    <tr>
                        <td>${row[0]}</td> <!-- "row[0] récupére la première information inséré dans la liste -->
                        <td>${row[1]}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
