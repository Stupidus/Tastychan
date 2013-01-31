<%-- 
    Document   : home
    Created on : 10 janv. 2013, 14:33:15
    Author     : Victor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file='../template/header.jsp'%>
<%
    if(request.getAttribute("flash") != null) {
        out.println(request.getAttribute("flash"));
    }
    
    if(request.getSession().getAttribute("username") != null) {
        out.println("Bienvenue "+request.getSession().getAttribute("username"));
    }
%>
<p>
    Bienvenue sur TastyChan !<br/><br/>
    Ici, pas de /b ou de /soc ou de /gif ou de /r [...] ou de moutons, y a que du bon !
</p>
<%@include file='../template/footer.jsp'%>
