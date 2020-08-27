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
<p class="h3 display-3 text-center">Connexion</p>
	<div class="row ">
		<div class="col-4"></div>
		<div class="col-4 text-center p-3 mb-2 bg-dark text-white rounded mt-5">
		<form  method="POST" action="/ENI-Encheres/Connexion">

			<div class="form-group ">
				<label for="pseudo">Identifiant :${pseudo}</label> <input
					type="text" class="form-control" name="pseudo" id="pseudo"
					aria-describedby="emailHelp" value='${login}' required autofocus>
				<input type="hidden" name="id" value='${id}'>
			</div>

			<div class="form-group">
	
				<label for="motDePasse">Mot de passe :</label> 
			    <input type="password" class="form-control" 
					   name="mdp" id="motDePasse"
					    required
			    >
			</div>
			<% if(request.getAttribute("erreur") != null) {%>
			    <div class="text-danger">Une erreur a été rencontrée: <%=request.getAttribute("erreur")%></div>
			<%}%>

			<a href="#">Mot de passe oublié</a>

			<div class="form-group form-check">
				<input type="checkbox" class="form-check-input" id="exampleCheck1">
				<label class="form-check-label" for="exampleCheck1"> Se
					souvenir de moi </label>
			</div>

			<button type="submit" class="btn btn-success mr-3">Connexion</button>
			<a href="Inscription"><button type="button"
					class="btn btn-danger">Créer un compte</button></a>
		</form>
	</div>
			<div class="col-4"></div>
	
	
	</div>

</body>
</html>