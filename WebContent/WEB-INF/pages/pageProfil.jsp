<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
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
	    	<label class="form-control">${pseudo}</label>
	    	
	   	 	<label for="Nom">Nom</label>
	    	<label class="form-control">${nom}</label>
	  	</div>
	  	
	  	<div class="form-group">
			<label for="Prenom">Prénom</label>
	    	<label class="form-control">${prenom}</label>
	    	
	   	 	<label for="Email">Email</label>
	    	<label class="form-control">${email}</label>
	  	</div>
	  	
	 	<div class="form-group">
			<label for="Telephone">Téléphone</label>
	    	<label class="form-control">${telephone}</label>
	    	
	   	 	<label for="Rue">Rue</label>
	    	<label class="form-control">${rue}</label>
	  	</div>
	  	
	  	<div class="form-group">
			<label for="CodePostal">Code Postal</label>
	    	<label class="form-control">${codePostal}</label>
	    	
	   	 	<label>Ville</label>
	    	<label class="form-control">${ville}</label>
	  	</div>
	  	
	  
		
		<!-- Si le pseudo en session et le pseudo affiché sont les mêmes 
		<%-- <%if( ${profilUtilisateur} ){ %> --%>
		<form action="/ENI-Encheres/ModificationProfil" method="post">
			<button type="submit" class="btn btn-secondary">Modifier</button>
		</form>
		<%-- <% } %> --%>
		-->
</body>
</html>