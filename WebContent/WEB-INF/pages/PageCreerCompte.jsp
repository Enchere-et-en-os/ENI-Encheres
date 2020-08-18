<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	
	<form action="/Encheres/Inscription" method="post">
	<div class="form-group">
		<label for="Pseudo">Pseudo</label>
    	<input name="Pseudo" type="text" class="form-control" id="Pseudo"/>
    	
   	 	<label for="Nom">Nom</label>
    	<input name="nom" type="text" class="form-control" id="Nom">
  	</div>
  	
  	<div class="form-group">
		<label for="Prenom">Prénom</label>
    	<input name="Prenom" type="text" class="form-control" id="Prenom">
    	
   	 	<label for="Email">Email</label>
    	<input name="Email" type="text" class="form-control" id="Email">
  	</div>
  	
 	<div class="form-group">
		<label for="Telephone">Téléphone</label>
    	<input name="Telephone" type="text" class="form-control" id="Telephone">
    	
   	 	<label for="Rue">Rue</label>
    	<input name="Rue" type="text" class="form-control" id="Rue">
  	</div>
  	
  	<div class="form-group">
		<label for="CodePostal">Code Postal</label>
    	<input name="CodePostal" type="text" class="form-control" id="CodePostal">
    	
   	 	<label for="Rue">Rue</label>
    	<input name="Rue" type="text" class="form-control" id="Rue">
  	</div>
  	
  	<div class="form-group">
		<label for="MotDePasse">Mot De Passe</label>
    	<input name="Mdp" type="password" class="form-control" id="MotDePasse">
    	
   	 	<label for="Confirmation">Confirmation</label>
    	<input name="Confirm" type="password" class="form-control" id="Confirmation">
  	</div>
	
	<button type="submit" class="btn btn-success">Créer</button>
	<button type="submit" class="btn btn-secondary">Annuler</button>
	
	</form>
</body>
</html>