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

<form action="" method="POST">
    <fieldset>
        <legend>Recherche</legend>
        <label for="terme">Terme : </label>
        <input type="text" name="terme" id="terme"/>
        <br/>
        <label for="categorie">Catégorie :</label>
        <select name="categorie" id="categorie">
            <option value="-1">Toutes</option>
            <%
                for(String[] categorie : (String[][]) request.getAttribute("listeCategories")) {
                    %>
                    <option value="<%= categorie[0] %>"><%= categorie[1] %></option>
                    <%
                }
            %>
        </select>
    </fieldset>
    <input type="submit" value="Recherche"/>
</form>
<%@include file='../template/footer.jsp'%>
