<%-- 
    Document   : category
    Created on : 10 janv. 2013, 14:33:15
    Author     : Victor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file='../template/header.jsp'%>
<%
    if(request.getAttribute("flash") != null) {
        out.println(request.getAttribute("flash"));
    }    
%>

<br/>
<form action="?action=register" method="post">
    <fieldset>
        <legend>Enregistrement</legend>
        <label for="username">Username : </label>
        <input type="text" name="username" id="username"/>
        <br/>
        <label for="password">Password : </label>
        <input type="password" name="password" id="password"/>
        <br/>        
    </fieldset>
    <input type="submit" value="Enregistrement"/>
</form>

<%@include file='../template/footer.jsp'%>
