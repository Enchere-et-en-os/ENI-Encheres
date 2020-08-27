<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

<title>Création de compte - Encheres</title>
</head>
<body>

	
	
		<p class="h3 display-3 text-center">Création de compte</p>
		
		<div class="container text-center p-3 mb-2 bg-dark text-white rounded mt-5">
		<form action="/ENI-Encheres/Inscription" method="post">
		
		
			<div class="row">
					<div class="form-group col-6">
						<label for="Pseudo">Pseudo</label>
				    	<input value="${pseudo}" name="pseudo" type="text" class="form-control" id="Pseudo" />
				    	<label>${erreurPseudo}</label>
		    	  	</div>
				    	
				    <div class="form-group col-6">
				   	 	<label for="Nom">Nom</label>
				    	<input value="${nom}" name="nom" type="text" class="form-control" id="Nom" >
				  		<label>${erreurNom}</label>
				  	</div>
		  	</div>
		  	
		  	
		  	
			<div class="row">
				  	<div class="form-group col-6">
						<label for="Prenom">Prénom</label>
				    	<input value="${prenom}" name="prenom" type="text" class="form-control" id="Prenom">
				    	<label>${erreurPrenom}</label>
		   			</div>
				    	
				    	
			    	<div class="form-group col-6">
				   	 	<label for="Email">Email</label>
				    	<input value="${email}" name="email" type="text" class="form-control" id="Email" >
				    	<label class="is-invalid">${erreurEmail}</label>
				  	</div>
	    	</div>
	    	
	    	
	    		<div class="row">
	    		
				 	<div class="form-group col-6">
						<label for="Telephone">Téléphone</label>
				    	<input value="${telephone}" name="telephone" type="text" class="form-control" id="Telephone">
				    	<label>${erreurTelephone}</label>
			    	</div>
	    	
	    	
				    	<div class="form-group col-6">
					   	 	<label for="Rue">Rue</label>
					    	<input value="${rue}" name="rue" type="text" class="form-control" id="Rue" >
					  		<label>${erreurRue}</label>
					  	</div>
					  	
				    	</div>
					  	
				  	<div class="row">
				  		
						  	<div class="form-group col-6">
								<label for="CodePostal">Code Postal</label>
						    	<input value="${codePostal}" name="codePostal" type="text" class="form-control" id="CodePostal" >
						    	<label>${erreurPost}</label>
				    		</div>
			    		
					    	
					    	
			    		<div class="form-group col-6">
					   	 	<label for="Ville">Ville</label>
					    	<input value="${ville}" name="ville" type="text" class="form-control" id="Ville" >
					  		<label>${erreurVille}</label>
					   	</div>
					   				    		</div>
					   	
				  	
				  	
				  	<div class=" form-group row">
				  	
					  	<div class="col-6">
							<label for="MotDePasse">Mot De Passe</label>
					    	<input name="mdp" type="password" class="form-control" id="MotDePasse" >
			   			</div>
			   			
			   			<div class="col-6">
					   	 	<label for="Confirmation">Confirmation</label>
					    	<input name="confirmMdp" type="password" class="form-control" id="Confirmation" >
					    	<label>${erreurConfirm} ${erreurMdp}</label>
					  	</div>
		  		</div>
		  		
		  	
			<button name="envoyer" type="submit" class="btn btn-success mr-3">Créer</button>
			<button name="annuler" type="submit" class="btn btn-danger">Annuler</button>
			
		</form>
	  			
	  				</div>
	
	
		
</body>
</html>