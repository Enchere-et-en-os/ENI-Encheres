<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

<title>Cr�ation de compte - Encheres</title>
</head>
<body>
	<h1>ENI-Ench�res</h1>
	<h2 ><small>Mon Profil</small></h2>
	
	<form action="/ENI-Encheres/Inscription" method="post">
		<div class="form-group">
			<label for="Pseudo">Pseudo</label>
	    	<input value="${pseudo}" name="pseudo" type="text" class="form-control" id="Pseudo"/>
	    	<label>${erreurPseudo}</label>
	    	
	   	 	<label for="Nom">Nom</label>
	    	<input value="${nom}" name="nom" type="text" class="form-control" id="Nom">
	  	</div>
	  	
	  	<div class="form-group">
			<label for="Prenom">Pr�nom</label>
	    	<input value="${prenom}" name="prenom" type="text" class="form-control" id="Prenom">
	    	
	   	 	<label for="Email">Email</label>
	    	<input value="${email}" name="email" type="text" class="form-control" id="Email">
	    	<label>${erreurEmail}</label>
	  	</div>
	  	
	 	<div class="form-group">
			<label for="Telephone">T�l�phone</label>
	    	<input value="${telephone}" name="telephone" type="text" class="form-control" id="Telephone">
	    	
	   	 	<label for="Rue">Rue</label>
	    	<input value="${rue}" name="rue" type="text" class="form-control" id="Rue">
	  	</div>
	  	
	  	<div class="form-group">
			<label for="CodePostal">Code Postal</label>
	    	<input value="${codePostal}" name="codePostal" type="text" class="form-control" id="CodePostal">
	    	
	   	 	<label for="Ville">Ville</label>
	    	<input value="${ville}" name="ville" type="text" class="form-control" id="Ville">
	  	</div>
	  	
	  	<div class="form-group">
			<label for="MotDePasse">Mot De Passe</label>
	    	<input name="mdp" type="password" class="form-control" id="MotDePasse">
	    	
	   	 	<label for="Confirmation">Confirmation</label>
	    	<input name="confirmMdp" type="password" class="form-control" id="Confirmation">
	  	</div>
	  	<label>${erreurConfirm} ${erreurMdp}</label>
		
		<button type="submit" class="btn btn-success">Cr�er</button>
		<button type="submit" class="btn btn-secondary">Annuler</button>
		
	</form>
	
</body>
</html>