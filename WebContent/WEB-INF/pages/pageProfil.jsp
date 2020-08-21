<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<title>Profil de ${pseudo}</title>
</head>
<body>
	<h1>ENI-Enchères</h1>
	<h2>Profil</h2>
	
	  	
	  	<div class="form-group">
			<label for="Prenom">Prénom</label>
	    	<label class="form-control">${prenom}</label> 	
	  	</div>
	  	
	  	
	  	<div class="form-group">
			<label for="CodePostal">Code Postal</label>
	    	<label class="form-control">${codePostal}</label>
	    	
	   	 	<label>Ville</label>
	    	<label class="form-control">${ville}</label>
	  	</div>
	  	
	  
		
		<!-- Si le pseudo en session et le pseudo affiché sont les mêmes -->
		<c:if test="${memeProfil == 1 }">
		<div class="form-group">
			<label for="Pseudo">Pseudo</label>
	    	<label class="form-control">${pseudo}</label>
	    	
	   	 	<label for="Nom">Nom</label>
	    	<label class="form-control">${nom}</label>
		
			<label for="Email">Email</label>
	    	<label class="form-control">${email}</label>
			
			<label for="Telephone">Téléphone</label>
	    	<label class="form-control">${telephone}</label>
	    	
	   	 	<label for="Rue">Rue</label>
	    	<label class="form-control">${rue}</label>
		</div>
		<form action="/ENI-Encheres/ModificationProfil" method="post">
			<button type="submit" class="btn btn-secondary">Modifier</button>
		</form>
		</c:if>

		
</body>
</html>