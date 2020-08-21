<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

<title>Création de compte - Encheres</title>
</head>
<body>
	<h1>ENI-Enchères</h1>
	<h2 ><small>Mon Profil</small></h2>
	
	<form action="/ENI-Encheres/Inscription" method="post">
		<div class="form-group">
			<label for="Pseudo">Pseudo</label>
	    	<input value="${pseudo}" name="pseudo" type="text" class="form-control" id="Pseudo" required/>
	    	<label>${erreurPseudo}</label>
	    	
	   	 	<label for="Nom">Nom</label>
	    	<input value="${nom}" name="nom" type="text" class="form-control" id="Nom" required>
	  		<label>${erreurNom}</label>
	  	</div>
	  	
	  	<div class="form-group">
			<label for="Prenom">Prénom</label>
	    	<input value="${prenom}" name="prenom" type="text" class="form-control" id="Prenom" required>
	    	<label>${erreurPrenom}</label>
	    	
	   	 	<label for="Email">Email</label>
	    	<input value="${email}" name="email" type="text" class="form-control" id="Email" required>
	    	<label>${erreurEmail}</label>
	  	</div>
	  	
	 	<div class="form-group">
			<label for="Telephone">Téléphone</label>
	    	<input value="${telephone}" name="telephone" type="text" class="form-control" id="Telephone">
	    	<label>${erreurTel}</label>
	    	
	   	 	<label for="Rue">Rue</label>
	    	<input value="${rue}" name="rue" type="text" class="form-control" id="Rue" required>
	  		<label>${erreurRue}</label>
	  	</div>
	  	
	  	<div class="form-group">
			<label for="CodePostal">Code Postal</label>
	    	<input value="${codePostal}" name="codePostal" type="text" class="form-control" id="CodePostal" required>
	    	<label>${erreurPost}</label>
	    	
	   	 	<label for="Ville">Ville</label>
	    	<input value="${ville}" name="ville" type="text" class="form-control" id="Ville" required>
	  		<label>${erreurVille}</label>
	  	</div>
	  	
	  	<div class="form-group">
			<label for="MotDePasse">Mot De Passe</label>
	    	<input name="mdp" type="password" class="form-control" id="MotDePasse" required>
	    	
	   	 	<label for="Confirmation">Confirmation</label>
	    	<input name="confirmMdp" type="password" class="form-control" id="Confirmation" required>
	  	</div>
	  	<label>${erreurConfirm} ${erreurMdp}</label>
		
		<button type="submit" class="btn btn-success">Créer</button>
		<button type="submit" class="btn btn-secondary">Annuler</button>
		
	</form>
	
	<a href="Profil?pseudo=aze">aze</a>
	
</body>
</html>