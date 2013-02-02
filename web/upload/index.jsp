<%-- 
    Document   : index
    Created on : 25 janv. 2013, 15:27:33
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
<br/>
<form action="?action=upload" method="post" enctype="multipart/form-data" name="fuckujava">
    <fieldset>
        <legend>Ajout d'image</legend>
        <label for="label">Titre : </label>
        <input type="text" name="label" id="label"/>
        <br/>
        <label for="image">Image : </label>
        <input type="file" name="image" id="image"/>
        <br/>
        <label for="categorie">Catégorie</label>
        <select name="categorie" id="categorie">
            <%
                for(String[] categorie : (String[][]) request.getAttribute("listeCategories")) {
                    %>
                    <option value="<%= categorie[0] %>"><%= categorie[1] %></option>
                    <%
                }
            %>
        </select>
        <input type="hidden" name="user_id" value="<%= request.getSession().getAttribute("id") %>"/>
    </fieldset>
    <input type="submit" value="Uploader"/>
</form>
<%@include file='../template/footer.jsp'%>

