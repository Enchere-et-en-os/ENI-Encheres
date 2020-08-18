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

	<h1>ENI-Ench�res</h1>

	<form class="col-3" method="POST" action="Connexion">
	
		<div class="form-group ">
			<label for="identifiant">Identifiant :</label> 
			<input type="text" class="form-control" name="identifiantInput" id="utilisateur" aria-describedby="emailHelp">
		</div>
		
		<div class="form-group">
			<label for="motDePasse">Mot de passe :</label> 
		    <input type="password" class="form-control" name="motDePasseInput" id="motDePasse">
		</div>
		
		<a href="#">Mot de passe oubli�</a>
		
		<div class="form-group form-check">
			<input type="checkbox" class="form-check-input" id="exampleCheck1">
			<label class="form-check-label" for="exampleCheck1">Se souvenir de moi</label>
		</div>
		
		<button type="submit" class="btn btn-primary">Connexion</button>
		
	</form>

	<button type="button" class="btn btn-primary">Cr�er un compte</button>
	
	<c:out value="${identifiant }"></c:out>
	<c:out value="${motDePasse }"></c:out>

</body>
</html>