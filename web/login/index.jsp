<%-- 
    Document   : home
    Created on : 10 janv. 2013, 14:33:15
    Author     : Victor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file='../template/header.jsp'%>
<form id="loginForm" action="?action=login" method="post">
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
<%@include file='../template/footer.jsp'%>