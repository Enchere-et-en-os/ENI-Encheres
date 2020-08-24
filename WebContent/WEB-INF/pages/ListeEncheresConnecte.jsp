
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
<title>ENI-Enchere</title>
</head>
<body>
	<jsp:include page="navBar.jsp" /><br><br>

	
	<h2>Liste des enchères</h2>


	<label for="basic-url">Filtre :</label>
	<div class="input-group mb-3">
		<div class="input-group flex-nowrap">
			<input type="text" class="form-control"
				placeholder="Le nom de l'article contient" aria-label="Username"
				aria-describedby="addon-wrapping">
		</div>
	</div>
	
	<p>Bonjour ${pseudo}</p>
	
	<div class="container ">
		<div class="row ">
			<div class="form-check col-sm">
		  <input class="form-check-input" type="radio" name="achat" id="achat" value="option1" checked>
		  <label class="form-check-label" for="achat">
		    Achats
		  </label>
		  <div>
			  <input class="form-check-input" type="checkbox" value="" id="defaultCheck1">
			  <label class="form-check-label" for="defaultCheck1">Enchères ouvertes</label>
		  </div>
		   <div>
			  <input class="form-check-input" type="checkbox" value="" id="defaultCheck1">
			  <label class="form-check-label" for="defaultCheck1">Mes enchères</label>
		  </div>
		   <div>
			  <input class="form-check-input" type="checkbox" value="" id="defaultCheck1">
			  <label class="form-check-label" for="defaultCheck1">Mes enchères remportées</label>
		  </div>
		  
		</div>
		<div class="form-check col-sm">
		  <input class="form-check-input" type="radio" name="vente" id="exampleRadios2" value="option2">
		  <label class="form-check-label" for="exampleRadios2">
		    Mes ventes
		  </label>
		  <div  >
			  <input class="form-check-input" type="checkbox" value="" id="defaultCheck1">
			  <label class="form-check-label" for="defaultCheck1">Mes ventes en cours</label>
		  </div>
		   <div>
			  <input class="form-check-input" type="checkbox" value="" id="defaultCheck1">
			  <label class="form-check-label" for="defaultCheck1">Ventes non débutées</label>
		  </div>
		   <div>
			  <input class="form-check-input" type="checkbox" value="" id="defaultCheck1">
			  <label class="form-check-label" for="defaultCheck1">Ventes terminées</label>
		  </div>
		  		  </div>
		  
	</div>


	<div class="input-group">
		<input type="text" class="form-control"
			aria-label="Text input with dropdown button">
		<div class="input-group-append">
			<button class="btn btn-outline-secondary dropdown-toggle"
				type="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false">Dropdown</button>
			<div class="dropdown-menu">
				<a class="dropdown-item" href="#">1</a> <a class="dropdown-item"
					href="#">2</a> <a class="dropdown-item" href="#">3</a>
			</div>
		</div>
	</div>
</div>







	<c:forEach var="article" items="${listeArticle}">

		<div class="card mb-3" style="max-width: 540px;">
			<div class="row no-gutters">
				<div class="col-md-4">
					<img
						src="C:\Users\slevy2020\Desktop\MissionProjet\images\chapeau_paille.jpg"
						class="card-img" alt="...">
				</div>

				<div class="col-md-8">
					<div class="card-body">
						<h5 class="card-title">${article.nom}</h5>
						<p class="card-text">Description : ${article.description}</p>
						<p class="card-text">Prix : ${article.prixVente}€</p>
						<p class="card-text">Fin de l'enchère :
							${article.dateFinEncheres}</p>


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