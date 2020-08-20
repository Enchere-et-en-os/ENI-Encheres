<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%-- <%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>ENI-Enchères</h1>
	<h2 ><small>Mon Profil</small></h2>
	
	<form action="/ENI-Encheres/ModificationProfil" method="post">
		<div class="form-group">
			<label for="Pseudo">Pseudo</label>
	    	<input name="pseudo" type="text" class="form-control" id="Pseudo"/>
	    	
	   	 	<label for="Nom">Nom</label>
	    	<input name="nom" type="text" class="form-control" id="Nom">
	  	</div>
	  	
	  	<div class="form-group">
			<label for="Prenom">Prénom</label>
	    	<input name="prenom" type="text" class="form-control" id="Prenom">
	    	
	   	 	<label for="Email">Email</label>
	    	<input name="email" type="text" class="form-control" id="Email">
	  	</div>
	  	
	 	<div class="form-group">
			<label for="Telephone">Téléphone</label>
	    	<input name="telephone" type="text" class="form-control" id="Telephone">
	    	
	   	 	<label for="Rue">Rue</label>
	    	<input name="rue" type="text" class="form-control" id="Rue">
	  	</div>
	  	
	  	<div class="form-group">
			<label for="CodePostal">Code Postal</label>
	    	<input name="codePostal" type="text" class="form-control" id="CodePostal">
	    	
	   	 	<label for="Ville">Ville</label>
	    	<input name="ville" type="text" class="form-control" id="Ville">
	  	</div>
	  	
	  	<div class="form-group">
			<label for="AncienMotDePasse">Ancien Mot De Passe</label>
	    	<input name="acienMdp" type="password" class="form-control" id="AncienMotDePasse">
	    	
	    	<label for="NouveauMotDePasse">Nouveau Mot De Passe</label>
	    	<input name="NouveauMdp" type="password" class="form-control" id="NouveauMotDePasse">
	    	
	   	 	<label for="Confirmation">Confirmation</label>
	    	<input name="confirm" type="password" class="form-control" id="Confirmation">
	  	</div>
	  	
	  	<div>
	  		<label for="credit">Crédit</label>
	  		<p id="credit">${utilisateur.credit}</p>
	  	</div>
		
		<button type="submit" class="btn btn-success">Enregistrer</button>
		<button type="submit" class="btn btn-secondary">Annuler</button>
		<button type="button" class="btn btn-danger">Supprimer mon Compte</button>
		
	</form>
</body>
</html>