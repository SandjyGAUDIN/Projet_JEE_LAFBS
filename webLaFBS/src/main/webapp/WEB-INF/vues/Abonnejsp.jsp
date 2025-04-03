<%-- 
    Document   : Abonnejsp.jsp
    Created on : 9 janv. 2025, 15:48:28
    Author     : sasag
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %> 

<%
  String role = (String) session.getAttribute("role_user");
  
  if(role == null || role.equals("ABONNE")) {
    request.getRequestDispatcher("WEB-INF/vues/ConnectionUser").forward(request, response);
    return;
  }
%>


        <h2>Liste des abonnées</h2>
        <p>Rôle en session : ${sessionScope.role_user}</p>
        <table border="1">
            <thead>
                <tr>
                    <th>Matricule</th>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>Action</th>
                </tr>
            </thead>
            
            <tbody>
                <c:forEach var="abonnes" items="${abonnes}">
                    <tr>
                        <td>${abonnes.matricule}</td>
                        <td>${abonnes.nom}</td>
                        <td>${abonnes.prenom}</td>
                        <td> <a href="AboInfo?matricule=${abonnes.matricule}">Détails</a> </td>
                        <c:if test="${sessionScope.role_user eq 'ADMIN'}"> <!-- on ne met pas == mais eq en JSTL --> <!-- SessionScope.role_user pour récupérer le role de l'utilisateur -->
                        <td>
                            <a href="AboModif?matricule=${abonnes.matricule}">Modifier</a>
                        </td>
                        <td>
                            <a href="AboSuppr?matricule=${abonnes.matricule}"> Supprimer </a>
                        </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        nombre d'abonnés : ${abonnes.size()}
        <br><br>
        
        <form method="post" action="AboInfo" id="unAbo">
        <select id="abo" form="unAbo" name="abo">
            <c:forEach var="abonnes" items="${abonnes}">
            <option value="${abonnes.matricule}">${abonnes.nom}  ${abonnes.prenom}</option>
            <p> ${abonnes.matricule} </p>
            </c:forEach>
        </select>
        <button type="details">Details</button>
        </form>
        <br/>
        <a href="AboAjout">
            <button>Ajouter un abonné (abonnez-vous svp lol)</button>
        </a>
        <br/> 
        <a href="Statistique">
            <button>Accéder à la page de statistique</button>
        </a>

