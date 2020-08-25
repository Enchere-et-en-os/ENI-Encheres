
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="fr.eni.encheres.bo.Article"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
	<style type="text/css"><%@include file="/style.css"%></style>
	
	<title>ENI-Enchere</title>
</head>
<body>
 	<nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
	  <a class="navbar-brand" href="Accueil">ENI-Enchere</a>
	  <ul class="navbar-nav ml-auto">
	    <li class="nav-item"> 
	       <a class="nav-link"  href="Connexion">S'inscrire - Se connecter</a>
	     </li>
     </ul> 
	</nav>
	<br><br>
	
	<h2>Liste des enchères</h2>

	<form method="POST" action="/ENI-Encheres/Accueil">
		<label for="basic-url">Filtre :</label>
			<div class="input-group mb-3">
				<div class="input-group flex-nowrap">
					<div class="input-group-prepend">
							<span class="input-group-text" id="addon-wrapping">Recherche par article :</span>
						</div>
						<input type="text" class="form-control"
							placeholder="Le nom de l'article contient ..." aria-label="Username"
							aria-describedby="addon-wrapping" name="barreRechercheArticle">
					</div>
				</div>
			
			
			
			<div class="input-group mb-3">
 			    <div class="input-group-prepend">
   			   	     <label class="input-group-text" for="inputGroupSelect01">Catégorie</label>
 			    </div>
  				<select class="custom-select" id="inputGroupSelect01" name="selectCategorie">
  					<option selected>Toutes</option>
  					<c:forEach var="categorie" items="${listeCategorie}"> 
				    	 <option value="${categorie.id}">${categorie.libelle}</option>
					</c:forEach>
               </select>
          </div>
			
			<button type="SUBMIT"  class="btn btn-primary btn-lg">Rechercher</button>
		
	</form>






	<c:forEach var="article" items="${listeArticle}">

		<div class="card mb-3" style="max-width: 540px;">
			<div class="row no-gutters">
				<div class="col-md-4">
					<img src="" class="card-img" alt="...">
				</div>

				<div class="col-md-8">
					<div class="card-body">
						<h5 class="card-title">${article.nom}</h5>
						<p class="card-text">Description : ${article.description}</p>
						<p class="card-text">Prix : ${article.prixVente}€</p>
						<p class="card-text">Fin de l'enchère : ${article.dateFinEncheres}</p>
						<p class="card-text">Vendeur :</p>
						<p class="card-text">
							<small class="text-muted">Last updated 3 mins ago</small>
						</p>
					</div>
				</div>
			</div>
		</div>

	</c:forEach>

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
