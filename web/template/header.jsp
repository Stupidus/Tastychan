<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="template/TastyChanStyle.css" rel="stylesheet" type="text/css">
		<link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700' rel='stylesheet' type='text/css'>
        <title>TastyChan by TastyCorp</title>
    </head>
    <body>
	<header>
		<h1>TastyChan</h1>
		<h3>A FreeArt project !</h3>
	</header>
	<nav>
		<ul>
                    <li>
                        <%
                        if(request.getSession().getAttribute("username") != null) {
                            out.println("Bienvenue "+request.getSession().getAttribute("username"));
                        }
                     %>
                    </li>
                    <% if(request.getSession().getAttribute("username") == null) { %>
                        <li><a href="/Tastychan/login">Connexion</a></li>
                        <li><a href="/Tastychan/register">S'enregistrer</a></li>
                    <% } else { %>
                        <li><a href="/Tastychan/logout">Déconnexion</a></li>
                    <% } %>
                    <li>&nbsp;</li>
                    <li><a href="/Tastychan/">Accueil</a></li>
                    <li><a href="#">Recherche</a></li>
                    <li>Catégories
                        <ul>
                            <%
                                if(request.getAttribute("listeCategories") != null) {
                                    for(String[] categorie : (String[][]) request.getAttribute("listeCategories")) {
                                        %>
                                        <li><a href="/Tastychan/category?id=<%= categorie[0] %>"><%= categorie[1] %></a></li>
                                        <%
                                    }
                                }
                            %>
                        </ul>
                    </li>     
                    <% if(request.getSession().getAttribute("username") != null) { %>
                        <li><a href="/Tastychan/upload">Ajouter une image</a></li>
                    <% }  %>
		</ul>
	</nav>
	<div id="content">