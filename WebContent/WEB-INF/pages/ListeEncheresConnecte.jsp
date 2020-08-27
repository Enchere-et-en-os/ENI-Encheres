
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
	               <a class="nav-link" href="MiseEnVente"> 
	                Vendre un article
	               </a> 
	           </li> 
	           <li class="nav-item"> 
	               <a class="nav-link" href="Profil"> 
	                 Mon profil
	               </a> 
	           </li>

	            <li class="nav-item" >
	            <form action="/ENI-Encheres/ListeEncheres" method="POST">
	           		 <button type="submit" class="btn btn-link"  name="deconnexion">Déconnexion</button>
	            </form>
	            </li>

	       </ul> 
	   </nav> 
	<br><br>

	
	<p class="h3 display-3 text-center">Liste des enchères</p>
	
	<div class="alert alert-success text-center " role="alert">
		Bienvenue parmi nous <span style="font-weight:bold">${pseudo}</span> ! <br> Vous nous avez manqué ! &#128519;
	</div>


<!--  
	<label for="basic-url">Filtre :</label>
	<div class="input-group mb-3">
		<div class="input-group flex-nowrap">
			<input type="text" class="form-control"
				placeholder="Le nom de l'article contient" aria-label="Username"
				aria-describedby="addon-wrapping">
		</div>
	</div>
	
	<form action="/ENI-Encheres/ListeEncheres" method="post" >
	<div class="container ">
		<div class="row " >
			<div class="form-check col-sm">
			  <input class="form-check-input" type="radio" name="radio" id="achat" value="achat" checked>
			  <label class="form-check-label" for="achat"> Achats</label>
				  <div>
					  <input name="enchereOuverte" class="form-check-input" type="checkbox" value="enchereOuverte" id="defaultCheck1">
					  <label  class="form-check-label" for="defaultCheck1">Enchères ouvertes</label>
				  </div>
				   <div>
					  <input name="enchereUtilisateur" class="form-check-input" type="checkbox" value="enchereUtilisateur" id="defaultCheck1">
					  <label class="form-check-label" for="defaultCheck1">Mes enchères</label>
				  </div>
				   <div>
					  <input name="enchereGagne" class="form-check-input" type="checkbox" value="enchereGagne" id="defaultCheck1">
					  <label class="form-check-label" for="defaultCheck1">Mes enchères remportées</label>
				  </div>
			</div>
			
			
			
			<div class="form-check col-sm">

			  <input class="form-check-input" type="radio" name="radio" id="vente" value="vente">

			  <label class="form-check-label" for="exampleRadios2">Mes ventes</label>
			  <div  >
				  <input name="venteEnCours" class="form-check-input" type="checkbox" value="venteEnCours" id="defaultCheck1">
				  <label class="form-check-label" for="defaultCheck1">Mes ventes en cours</label>
			  </div>
			   <div>
				  <input name="venteNonDebute" class="form-check-input" type="checkbox" value="venteNonDebut" id="defaultCheck1">
				  <label class="form-check-label" for="defaultCheck1">Ventes non débutées</label>
			  </div>
			   <div>
				  <input name="venteTermine" class="form-check-input" type="checkbox" value="venteTermine" id="defaultCheck1">
				  <label class="form-check-label" for="defaultCheck1">Ventes terminées</label>
			  </div>
			</div>
		</div>


		<div class="input-group mb-3">
 			    
          </div>
		
		<%-- <div class="input-group">
			<div class="input-group-prepend">
   			   	     <label class="input-group-text" for="inputGroupSelect01">Catégorie</label>
 			    </div>
  				<select class="custom-select" id="inputGroupSelect01" name="selectCategorie">
  					<option selected>Toutes</option>
  					 <c:forEach var="categorie" items="${listeCategorie}"> 
  					<c:set var="cat" value="${categorie.getLibelle()}"/>		NOT WORKING Code pour mettre la premiere lettre du libelle en MAJ
  					<c:set var="libelle" value="${cat.substring(0, 1).toUpperCase() + cat.substring(1)}" />
				    	 <option value="${categorie.libelle}"><c:out value="${categorie.libelle}"/></option>
					</c:forEach>
               </select>
		</div>
	</div> --%>
	<button name="recherche" type="submit" class="btn btn-success" >Recherche</button>
	</form>
	-->


	<c:forEach var="article" items="${listeArticle}">

		<div class="card mb-3" style="max-width: 540px;">
			<div class="row no-gutters">
				<div class="col-md-4">

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