<div align='center'>
<?php
$e = array ();
$login = getPostVariable ( "login" );
$pass = getPostVariable ( "password" );
if (getPostVariable ( "connect" ) != false) {
	if (! $login)
		$e [] = "login vide";
	else
		$usr_tmp = getTableByData ( "user", "login", $login );
	
	if (! $pass)
		$e [] = "Mot de passe vide";
	
	if (! existData ( "user", "login", $login ) || ! matchTableData ( "user", "login", $login, "password", md5 ( $pass ) ))
		$e [] = "L'utilisateur " . $login . " ou le mot de passe n'est pas correct";
	
	if (sizeof ( $e ) > 0) {
		echo "La connexion a échoué, il y a " . sizeof ( $e ) . " erreurs: <br />";
		foreach ( $e as $erreur ) {
			echo $erreur . "<br />";
		}
	} else {
		sessionStart ( $usr_tmp );
		echo "Vous vous êtes correctement connecté";
		echo "<meta http-equiv='refresh' content='0;URL=accueil'>";
		// header('Location: accueil');
	}
}
?>
	<form method="post">
		<table>
			<tr>
				<td>Identifiant</td>
				<td><input type="text" name="login" /></td>
			</tr>
			<tr>
				<td>Mot de passe</td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td colspan="2" align='center'><input type="hidden" name="connect"
					value="1" /><input type="submit" /></td>
			</tr>
		</table>
	</form>
</div>