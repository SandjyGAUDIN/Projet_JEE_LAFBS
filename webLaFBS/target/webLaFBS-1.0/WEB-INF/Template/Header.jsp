<%-- 
    Document   : Header
    Created on : 7 mars 2025, 13:34:04
    Author     : sasag
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@page import="sio.leo.weblafbs.Modele.Abonne"%>
<%@page import="java.util.List"%>
<%@ page session="true" %> 


<!-- template.jsp --> 
<!DOCTYPE html> 
<html lang="fr"> 
<head> 
    <meta charset="UTF-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
    <title><%= request.getAttribute("pageTitle") != null ? request.getAttribute("pageTitle") : "La FsB" %></title> 
    <!-- <link rel="stylesheet" href="css/styles.css"> -->
</head> 

<body> 
    <header> 
        <nav> 
            <a href="connection">connection</a> 
            <a href="lesabonnes">Liste des abonnés</a> 
            <a href="contact">Contact</a> 
        </nav> 
    </header> 
    <!--logo de la fbs --> 
    <img src="/webLaFBS/images/laFbsLogo.jpg" alt="Logo"> 
    <!-- Contenu dynamique de la page --> 
    <jsp:include page="${mainContent}"/> 
</body> 
<!-- footer.jsp --> 
<footer> 
    <p>&copy; 2025 la Forme by sport. Tous droits réservés.</p> 
    <a href="/webLaFBS/CGVU.jsp" />cgvu</a> <a href="/webLaFBS/mentions.jsp" />mentions légales</a> 
</footer> 
</html> 
