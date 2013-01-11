<%-- 
    Document   : login
    Created on : 11 janv. 2013, 08:58:12
    Author     : Victor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="?action=login" method="post">
            <fieldset>
			<legend>Connexion</legend>
			<label for="username">Username :</label>
            <input type="text" name="username" id="username"/>
            <br/>
            <label for="password">Password :</label>
            <input type="password" name="password" id="password"/>
            <br/><br/>
			</fieldset>
            <input type="submit" value="Connexion"/>
        </form>
    </body>
</html>
