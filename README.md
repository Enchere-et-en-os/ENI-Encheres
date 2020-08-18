# ENI-Encheres

#créer un fichier context.xml dans le fichier META-INF pour gérer la connexion à la base de donnée
<?xml version="1.0" encoding="UTF-8"?>
	<Context>
		<Ressource
			name="jdbc/pool_cnx"
			
			driverClassName="com.microsoft.sqlserver.jdbc.SQLServerDriver"
			type="javax.sql.DataSource"
			
			url="jdbc:sqlserver://localhost;databasename=XXXXXXX"
			username="XXXXXXX"
			password="XXXXXXX"
			
			maxTotal="100"
			maxIdle="30"
			maxWaitMillis="5000"		
			/>
	</Context>

remplir les logins et base de données avec ses identifiants
