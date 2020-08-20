<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Connexion</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
</head>
<body>

	<h1>ENI-Enchères</h1>

	<form class="col-3" method="POST" action="Connexion">
		
		<div class="form-group ">
			<label for="identifiant">Identifiant :${identifiant}</label> 
			<input type="text" class="form-control" name="identifiant" 
				id="utilisateur" aria-describedby="emailHelp"
				 value="<c:out value="${utilisateur.identifiant}"/>"
			>
		</div>
		
		<div class="form-group">
			<label for="motDePasse">Mot de passe :</label> 
		    <input type="password" class="form-control" name="motDePasse" id="motDePasse">
		</div>
		
		<a href="#">Mot de passe oublié</a>
		
		<div class="form-group form-check">
			<input type="checkbox" class="form-check-input" id="exampleCheck1">
			<label class="form-check-label" for="exampleCheck1">Se souvenir de moi</label>
		</div>
		
		<button type="submit" class="btn btn-primary">Connexion</button>
		
	</form>

	<button type="button" class="btn btn-primary">Créer un compte</button>
	
	<c:out value="${utilisateur }"></c:out>
	<c:out value="${sessionScope.motDePasseS }"></c:out>

</body>
</html>