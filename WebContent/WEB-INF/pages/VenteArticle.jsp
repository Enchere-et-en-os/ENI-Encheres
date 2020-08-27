<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<style type="text/css"><%@include file="/style.css"%></style>
<title>Nouvelle vente</title>
</head>
<body>

	<h1>ENI-Enchères</h1>
	
	
	<div class="container">
	
  <div class="row">
    <div class="col-sm col-lg-3">
     	<img class="img_art" src="/ENI-Encheres/images/photo-en-attente.png" 
     		alt="chapeau de paille posé délicatemment sur une jolie demoiselle à la chevelure blonde et soyeuse" 
   		>
    </div>
    <div class="col-sm col-lg-9 bg-dark text-white text-center">
     <p class="h3 display-3 text-center">Nouvelle vente</p>
		<form method="POST" action="/ENI-Encheres/MiseEnVente">

			<div class="form-group">
				<label for="nomArticle">Nom de l'article :</label>
				<input value="" name="nomArticle" type="text" class="form-control" id="nomArticle" required/>
			</div>
			<% if(request.getAttribute("erreur") != null) {%>
			    <div class="text-danger">Une erreur a été rencontrée: <%=request.getAttribute("erreur")%></div>
			<%}%>
			
			<div class="form-group">
				<label for="description">Description :</label>
				<textarea name="description" rows="5" cols="100" maxlength="500" >
					Vous pouvez écrire votre description ici.
				</textarea>
			</div>
			<% if(request.getAttribute("erreur") != null) {%>
			    <div class="text-danger">Une erreur a été rencontrée: <%=request.getAttribute("erreur")%></div>
			<%}%>
			<div class="row align-items-center">
					<div class="form-group  col-4">
						<label for="categorie">Catégorie :</label>
						<select name="categorie" id="categorie">
							<option value="1">Electroménager</option>
						    <option value="2">Electronique</option>
						    <option value="3">Véhicules</option>
						    <option value="4">Vêtements</option>
						</select>
					</div>
			
					<div class="form-group col-4">
						<label for="uploader">Photo de l'article :</label>
						<button type="button" name ="uploader" class="btn btn-secondary">Uploader</button>
					</div>
					
					<div class="form-group  col-4 mb-5">
						<label for="miseAprix">Mise à prix (en points) :</label>
						<input type="number" id="miseAprix" name="miseAprix" min="0">
					
					<% if(request.getAttribute("erreur") != null) {%>
					    <div class="text-danger">Une erreur a été rencontrée: <%=request.getAttribute("erreur")%></div>
					<%}%>
					</div>
			</div>
			<div class="row">
			<div class="form-group col-6">
				<label for="debutEnchere">Début de l'enchère :</label>
				<input type="date" id="debutEnchere" name="debutEnchere">
			</div>
			
			<div class="form-group col-6">
				<label for="finEnchere">Fin de l'enchère :</label>
				<input type="date" id="finEnchere" name="finEnchere">
			</div>
			</div>
			
			<fieldset>
		    	<legend>Retrait</legend>
		    	
		    	<div class="form-group">
					<label for="rue">Rue :</label>
					<input value="" name="rue" type="text" class="form-control" id="rue" required/>
				</div>
				<% if(request.getAttribute("erreur") != null) {%>
				    <div class="text-danger">Une erreur a été rencontrée: <%=request.getAttribute("erreur")%></div>
				<%}%>
				
				<div class="form-group">
					<label for="codePostal">Code Postal :</label>
					<input value="" name="codePostal" type="text" class="form-control" id="codePostal" required/>
				</div>
				<% if(request.getAttribute("erreur") != null) {%>
				    <div class="text-danger">Une erreur a été rencontrée: <%=request.getAttribute("erreur")%></div>
				<%}%>
				
				<div class="form-group">
					<label for="ville">Ville :</label>
					<input value="" name="ville" type="text" class="form-control" id="ville" required/>
				</div>
				<% if(request.getAttribute("erreur") != null) {%>
				    <div class="text-danger">Une erreur a été rencontrée: <%=request.getAttribute("erreur")%></div>
				<%}%>
	  		</fieldset>

				<button type="submit" class="btn btn-success mb-5">Enregistrer</button>
				<button type="button" class="btn btn-danger mb-5">Annuler</button>
				
		</form>
	    </div>
	  </div>
	</div>
	
	
	
	
	
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
		integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
		integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
		crossorigin="anonymous"></script>
</body>
</html>