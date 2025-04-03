<%-- 
    Document   : StatFreqActSalle
    Created on : 4 mars 2025, 16:36:00
    Author     : sasag
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- Important pour le c:foreach ect.. -->
<%
  String role = (String) session.getAttribute("role_user");
  
  if(role == null || role.equals("ABONNE")) {
    response.sendRedirect("UserConnection");
    return;
  }
%>

        <h1>Résultat Statistique 1 : Fréquentation des activités par salle</h1>
        
        <table border="1">
            
            <thead>
                <tr> <!-- Entête du tableau -->
                    <th>Nom Salle :</th> 
                    <th>Nom de l'activité :</th>
                    <th>Fréquentation de l'activité :</th>
                </tr>
            </thead>
            
            <tbody> <!-- contenue dans le tableau -->
                <c:forEach var="row" items="${ListStat}">
                    <tr>
                        <td>${row[0]}</td>
                        <td>${row[1]}</td>
                        <td>${row[2]}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

