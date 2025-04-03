    <%-- 
    Document   : ConnectionUser
    Created on : 5 mars 2025, 16:47:36
    Author     : sasag
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <h1>Page de connection</h1>
        
        <p style="color:red;">​
            ${error}​
        </p>​
        <form action="UserConnection" method="post">
        
            <label for="user">Nom d'utilisateur :</label>
            <input type="text" id="user" name="user" required/>
            <br/>

            <label for="password">Mot de passe :</label>
            <input type="password" id="password" name="password" required/>
            <br/>
            

            <button type="submit">Connexion</button>
        </form>
