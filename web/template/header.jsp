<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="template/TastyChanStyle.css" rel="stylesheet" type="text/css">
		<link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700' rel='stylesheet' type='text/css'>
        <title>JSP Page</title>
    </head>
    <body>
	<header>
		<h1>TastyChan</h1>
		<h3>A FreeArt project !</h3>
	</header>
	<nav>
		<ul>
			<li><a href="/Tastychan/">Accueil</a></li>
			<li><a href="#">Recherche</a></li>
			<li>Catégories
				<ul>
					<li><a href="/Tastychan/category/index?id=1">BD & Manga</a></li>
					<li><a href="#">Dessins</a></li>
					<li><a href="#">Fan Art</a></li>
					<li><a href="#">Photographies</a></li>
					<li><a href="#">Sculptures</a></li>
				</ul>
			</li>
                        <% if(request.getSession().getAttribute("username") == null) { %>
                            <li><a href="/Tastychan/login">Connexion</a></li>
                        <% } else { %>
                            <li><a href="/Tastychan/logout">Déconnexion</a></li>
                        <% } %>
		</ul>
	</nav>
	<div id="content">