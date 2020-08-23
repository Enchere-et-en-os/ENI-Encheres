<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<title>Modification de Profil</title>
</head>
<body>
	<h1>ENI-Enchères</h1>
	<h2 ><small>Mon Profil</small></h2>
	
	<form action="/ENI-Encheres/ModificationProfil" method="post">
	
		<div class="form-group">
			<label for="Pseudo">Pseudo</label>
	    	<input value="${pseudo}" name="pseudo" type="text" class="form-control" id="Pseudo"/>
	    	
	   	 	<label for="Nom">Nom</label>
	    	<input value="${nom}" name="nom" type="text" class="form-control" id="Nom">
	  	</div>
	  	
	  	<div class="form-group">
			<label for="Prenom">Prénom</label>
	    	<input value="${prenom}" name="prenom" type="text" class="form-control" id="Prenom">
	    	
	   	 	<label for="Email">Email</label>
	    	<input value="${email}" name="email" type="text" class="form-control" id="Email">
	  	</div>
	  	
	 	<div class="form-group">
			<label for="Telephone">Téléphone</label>
	    	<input value="${telephone}" name="telephone" type="text" class="form-control" id="Telephone">
	    	
	   	 	<label for="Rue">Rue</label>
	    	<input value="${rue}" name="rue" type="text" class="form-control" id="Rue">
	  	</div>
	  	
	  	<div class="form-group">
			<label for="CodePostal">Code Postal</label>
	    	<input value="${codePostal}" name="codePostal" type="text" class="form-control" id="CodePostal">
	    	
	   	 	<label for="Ville">Ville</label>
	    	<input value="${ville}" type="text" class="form-control" id="Ville">
	  	</div>
	  	
	  	<div class="form-group">
			<label for="AncienMotDePasse">Ancien Mot De Passe</label>
	    	<input name="acienMdp" type="password" class="form-control" id="AncienMotDePasse">
	    	
	    	<label for="NouveauMotDePasse">Nouveau Mot De Passe</label>
	    	<input name="NouveauMdp" type="password" class="form-control" id="NouveauMotDePasse">
	    	
	   	 	<label for="Confirmation">Confirmation</label>
	    	<input name="confirm" type="password" class="form-control" id="Confirmation">
	  	</div> 		
		
		<button name="enregistrer" type="submit" class="btn btn-success" >Enregistrer</button>
		<button name="annuler" type="submit" class="btn btn-secondary">Annuler</button>
		<button name="supprimer" type="submit" class="btn btn-danger">Supprimer mon Compte</button>
		
	</form>
</body>
</html>