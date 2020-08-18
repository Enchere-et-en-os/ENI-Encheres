<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<title>ENI-Enchere</title>
</head>
<body>
	<h1>ENI-Enchere</h1>
	<a href="">S'inscrire - Se connecter</a>
	<h2>Liste des enchères</h2>
	<--filtre-->
	<label for="basic-url">Filtre :</label>
	<div class="input-group mb-3">
		<div class="input-group flex-nowrap">
			<div class="input-group-prepend">
				<span class="input-group-text" id="addon-wrapping">search</span>
			</div>
			<input type="text" class="form-control"
				placeholder="Le nom de l'article contient" aria-label="Username"
				aria-describedby="addon-wrapping">
		</div>
	</div>
	<label for="basic-url">Categorie :</label>
	<div class="input-group">
		<div class="input-group">
			<input type="text" class="form-control"
				aria-label="Text input with dropdown button">
			<div class="input-group-append">
				<button class="btn btn-outline-secondary dropdown-toggle"
					type="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">Toutes</button>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="#">Action</a> <a
						class="dropdown-item" href="#">Another action</a> <a
						class="dropdown-item" href="#">Something else here</a>
					<div role="separator" class="dropdown-divider"></div>
					<a class="dropdown-item" href="#">Separated link</a>
				</div>
			</div>
		</div>
		<button>Rechercher</button>
		<div class="card mb-3" style="max-width: 540px;">
  <div class="row no-gutters">
    <div class="col-md-4">
      <img src="..." class="card-img" alt="...">
    </div>
    <div class="col-md-8">
      <div class="card-body">
        <h5 class="card-title">Card title</h5>
        <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
        <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
      </div>
    </div>
  </div>
</div>
</body>
</html>
 