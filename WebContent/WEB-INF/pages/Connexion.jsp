<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<title>Connexion</title>
		<link rel="stylesheet"
			href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
			integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
			crossorigin="anonymous">
		</head>
	<body>
		<jsp:include page="navBar.jsp" /><br><br>
	
		<form class="col-3" method="POST" action="/ENI-Encheres/Connexion">
	
			<div class="form-group ">
				<label for="pseudo">Identifiant :${pseudo}</label> 
				<input type="text" class="form-control" name="pseudo" 
					id="pseudo" aria-describedby="emailHelp"
					value='${login}' required autofocus
				>
			</div>
	
			<div class="form-group">
	
				<label for="motDePasse">Mot de passe :</label> 
			    <input type="password" class="form-control" 
					   name="motDePasse" id="motDePasse"
					   value='${password}' required
			    >
			</div>
				
			<a href="#">Mot de passe oublié</a>
	
			<div class="form-group form-check">
				<input type="checkbox" class="form-check-input" id="exampleCheck1">
				<label class="form-check-label" for="exampleCheck1">Se
					souvenir de moi</label>
			</div>
	
			<button type="submit" class="btn btn-primary">Connexion</button>
	
		</form>
	
		<a href="Inscription"><button type="button" class="btn btn-primary">Créer un compte</button></a>
	
	</body>
</html>