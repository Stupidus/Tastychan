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

<%
    if(request.getAttribute("listeImages") != null) {
        for(String[] image : (String[][]) request.getAttribute("listeImages")) {
            %>
            <p>
                <b><%= image[1] %></b><br/>
                <a href="fiche?id=<%= image[0] %>" target="_blank"><img src="image?id=<%= image[0] %>" style="max-width:200px;max-height:200px"/></a>
                <pre><%= image[2] %></pre>
            </p>
            <%
        }
    }
%>

<br/>
Page : 
<% 
for (int i = 1; i <= Integer.parseInt(request.getAttribute("nbPages").toString()); i++) {
    
    %>
        <a href="/Tastychan/category?id=<%= request.getParameter("id") %>&page=<%= i %>"><%= i %></a>
    <%
}
    %>
<%@include file='../template/footer.jsp'%>
