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
<p>Coucou</p>
<%@include file='../template/footer.jsp'%>
