<%-- 
    Document   : home
    Created on : 10 janv. 2013, 14:33:15
    Author     : Victor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file='../template/header.jsp'%>
<p>
    Une erreur est survenue
</p>
<script type="text/javascript">
function showHideAttributes(spanId){
     if(document.getElementById(spanId).style.display=='block'){
         document.getElementById(spanId).style.display='none';
     }else if(document.getElementById(spanId).style.display=='none'){
            document.getElementById(spanId).style.display='block';
           
     }
 }
</script>
<a href="#" onclick="showHideAttributes('debugSpan')"> Attributes</a>
<span id='debugSpan' style='display:none'>
Session Attributes:
<% java.util.Enumeration salist = session.getAttributeNames();
   while(salist.hasMoreElements()){
       String name=(String)salist.nextElement();
       Object value = session.getAttribute(name);%>      
      <%=" "+name+"="+value %>
<% }%>
Request Attributes:
<% java.util.Enumeration ralist = request.getAttributeNames();
   while(ralist.hasMoreElements()){
       String name=(String)ralist.nextElement();
       Object value = request.getAttribute(name);%>      
      <%=" "+name+"="+value %>
<% }%>
Request Parametres
<% java.util.Enumeration rplist = request.getParameterNames();
   while(rplist.hasMoreElements()){
       String name=(String)rplist.nextElement();
       Object value = request.getParameter(name);%>      
      <%=" "+name+"="+value %>
<% }%>
</span>
<%@include file='../template/footer.jsp'%>