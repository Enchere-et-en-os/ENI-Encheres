
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="fr.eni.encheres.bo.Article"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">

<title>ENI-Enchere</title>
</head>
<body>
	<nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="Accueil">ENI-Enchere</a>
		<ul class="navbar-nav ml-auto">
			<li class="nav-item"><a class="nav-link" href="Connexion">S'inscrire
					- Se connecter</a></li>
		</ul>
	</nav>
	<br>
	<br>

	<p class="h3 display-3">Liste des enchères</p>


	<div class="alert alert-info " role="alert">
		Vous n'êtes pas connecté ! <a href="/ENI-Encheres/Connexion"
			class="alert-link">Connectez-vous</a> pour profiter de milliers
		d'articles à bas prix !
	</div>
	<form method="POST" action="/ENI-Encheres/Accueil">

		<div class="container-fluid text-center">
			<div class="row mr-3 ml-3">
				<div class="col-6 input-group mb-3">
					<div class="input-group flex-nowrap">
						<div class="input-group-prepend">
							<span class="input-group-text " id="addon-wrapping">Recherche
								par article :</span>
						</div>
						<input type="text" class="form-control"
							placeholder="Le nom de l'article contient ..."
							value="${barreRechercheArticle}" aria-label="Username"
							aria-describedby="addon-wrapping" name="barreRechercheArticle">
							
					</div>
				</div>


				<div class=" col-4 input-group mb-3">
					<div class="input-group-prepend">
						<label class="input-group-text" for="inputGroupSelect01">Catégorie</label>
					</div>
					<select class="custom-select" id="inputGroupSelect01"
						name="selectCategorie">
						<option selected value="-1">Toutes</option>
						<c:forEach var="categorie" items="${listeCategorie}">
							<option value="${categorie.id}">${categorie.libelle}</option>
						</c:forEach>
					</select>

				</div>
				<div class="col-2">
					<button type="SUBMIT" class="btn btn-primary rounded-lg">Rechercher</button>
				</div>
			</div>
		</div>


	</form>

			<% if(request.getAttribute("erreur") != null) {%>
			    <div class="text-danger">Une erreur a été rencontrée: <%=request.getAttribute("erreur")%></div>
			<%}%>

	<div class="container-fluid">
		<div class="row">
			<c:set var="nbArticle" value="0" scope="page" />
			<c:forEach var="article" items="${listeArticle}">
				<c:set var="nbArticle" value="${nbArticle + 1}" scope="page" />
				<div class="card mb-3 shadow p-3 mb-5 bg-white rounded col-6 "
					style="max-width: 540px;">
					<div class="row no-gutters">
						<div class="col-md-4">
							<img src="/ENI-Encheres/images/chapeau_paille.jpg"
								class="card-img" alt="...">
						</div>

						<div class="col-md-8">
							<div class="card-body">
								<h5 class="card-title">${article.nom}</h5>
								<p class="card-text">Description : ${article.description}</p>
								<p class="card-text">Prix : ${article.prixVente}€</p>
								<p class="card-text">Fin de l'enchère :
									${article.dateFinEncheres}</p>
								<p class="card-text">Vendeur : ${article.utilisateurPseudo}</p>
							</div>
						</div>
					</div>
					<a href="/ENI-Encheres/Connexion" class="stretched-link"></a>
				</div>
			</c:forEach>

				<h4><c:out value="Nous avons ${nbArticle} article"/><c:out value="${nbArticle > 1 ? 's' : '' } à vous proposer"/></h4>	



		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
		integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
		integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
		crossorigin="anonymous"></script>
</body>
</html>
