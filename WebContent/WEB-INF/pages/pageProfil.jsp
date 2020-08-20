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
	<h1>Profil</h1>
	
	<div class="form-group">
			<label for="Pseudo">Pseudo</label>
	    	<input value="${utilisateur.pseudo}" name="pseudo" type="text" class="form-control" id="Pseudo"/>
	    	
	   	 	<label for="Nom">Nom</label>
	    	<input value="${utilisateur.nom}" name="nom" type="text" class="form-control" id="Nom">
	  	</div>
	  	
	  	<div class="form-group">
			<label for="Prenom">Prénom</label>
	    	<input value="${utilisateur.prenom}" name="prenom" type="text" class="form-control" id="Prenom">
	    	
	   	 	<label for="Email">Email</label>
	    	<input value="${utilisateur.email}" name="email" type="text" class="form-control" id="Email">
	  	</div>
	  	
	 	<div class="form-group">
			<label for="Telephone">Téléphone</label>
	    	<input value="${utilisateur.telephone}" name="telephone" type="text" class="form-control" id="Telephone">
	    	
	   	 	<label for="Rue">Rue</label>
	    	<input value="${utilisateur.rue}" name="rue" type="text" class="form-control" id="Rue">
	  	</div>
	  	
	  	<div class="form-group">
			<label for="CodePostal">Code Postal</label>
	    	<input value="${utilisateur.codePostal}" name="codePostal" type="text" class="form-control" id="CodePostal">
	    	
	   	 	<label for="Ville">Ville</label>
	    	<input value="${utilisateur.ville}" name="ville" type="text" class="form-control" id="Ville">
	  	</div>
	  	
	  	<!--  -->
	  	<div class="form-group">
			<label for="MotDePasse">Mot De Passe</label>
	    	<input name="mdp" type="password" class="form-control" id="MotDePasse">
	    	
	   	 	<label for="Confirmation">Confirmation</label>
	    	<input name="confirm" type="password" class="form-control" id="Confirmation">
	  	</div>
		
		<!-- hidden si l'id de l'utilisateur affihcé n'est pas 
		le même que celui consultant la page -->
		<button type="submit" class="btn btn-secondary">Modifier</button>
		
</body>
</html>