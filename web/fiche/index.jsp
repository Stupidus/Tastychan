<%-- 
    Document   : category
    Created on : 10 janv. 2013, 14:33:15
    Author     : Victor
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file='../template/header.jsp'%>
<%
    if(request.getAttribute("flash") != null) {
        out.println(request.getAttribute("flash"));
    }    
%>

<%
    if(request.getAttribute("ficheImage") != null) {
        String[] ficheImage = (String[]) request.getAttribute("ficheImage");
        %>
        <p>
            <b><%= ficheImage[3] %> : <%= ficheImage[1] %></b><br/>
            <img src="image?id=<%= ficheImage[0] %>" style="max-width:200px;max-height:200px"/>
            <pre><%= ficheImage[2] %></pre>
            <br/>
            <%
                String url = request.getRequestURL().toString();
                url += "?";
                url += request.getQueryString();
                url = url.replaceAll("/index.jsp", "");
                if(url.indexOf("&") > 0)
                    url = url.substring(0, url.indexOf("&"));
                if(request.getSession().getAttribute("Panier") != null) {
                    ArrayList panier = (ArrayList) request.getSession().getAttribute("Panier");
                    if(!panier.contains(Integer.parseInt(request.getParameter("id")))) {
                        %><a href="<%= url %>&ajout=true">Ajouter au panier</a><%
                    } else {
                        %><a href="<%= url %>&ajout=remove">Retirer du panier</a><%
                    }
                }
               else {
                    %><a href="<%= url %>&ajout=true">Ajouter au panier</a><%
               }
                %>
        </p>
        <%
    }    
%>

<%@include file='../template/footer.jsp'%>
