<div align='center'>
<?php
if (isSessionStarted ()) {
	getContent ( "404" );
} else {
	$action = getPostVariable ( "action" );
	if ($action == "ok") {
		$post = cleanArray ( $_POST );
		$e = array ();
		
		if (empty ( $post ["login"] )) {
			$e [] = "Le champ identifiant est vide";
		} elseif (existData ( "user", "login", $post ["login"] )) {
			$e [] = "L'identifiant existe déjà";
		}
		if (empty ( $post ["password"] )) {
			$e [] = "Le champ mot de passe est vide";
		} elseif (empty ( $post ["password_verif"] )) {
			$e [] = "Le mot de passe doit être vérifié";
		} elseif ($post ["password"] != $post ["password_verif"]) {
			$e [] = "La vérifiation de mot de passe ne correspond pas";
		}
		if (empty ( $post ["eMail"] )) {
			$e [] = "Le champ e-mail est vide";
		} elseif (existData ( "contact", "eMail", $post ["eMail"] )) {
			$e [] = "Cette address e-mail est déjà utilisée";
		} elseif (filter_var ( $post ["eMail"], FILTER_VALIDATE_EMAIL ) == false) {
			$e [] = "L'address e-mail n'est pas valide";
		}
		if (empty ( $post ["lastName"] )) {
			$e [] = "Le champ nom est vide";
		}
		if (empty ( $post ["firstName"] )) {
			$e [] = "Le champ prénom est vide";
		}
		if (empty ( $post ["phone"] )) {
			$e [] = "Le champ téléphone est vide";
		}
		if (empty ( $post ["address"] )) {
			$e [] = "Le champ adresse est vide";
		}
		if (empty ( $post ["city"] )) {
			$e [] = "Le champ ville est vide";
		}
		if (empty ( $post ["postalCode"] )) {
			$e [] = "Le champ code postal est vide";
		}
		if (empty ( $post ["country"] )) {
			$e [] = "Le champ pays est vide";
		}
		if (empty ( $post ["g-recaptcha-response"] )) {
			$e [] = "Le captcha n'est pas valide";
		}
		
		if (sizeof ( $e ) > 0) {
			echo "<table><tr><td colspan='2' align='center'>L'inscription a échoué, il y a " . sizeof ( $e ) . " erreurs:</td></tr>";
			$i = 0;
			foreach ( $e as $erreur ) {
				if ($i % 2 == 0) {
					echo "</tr><tr>";
				}
				echo "<td>" . $erreur . "</td>";
				$i ++;
			}
			echo "</td></tr></table>";
		} else {
			createData ( "contact", $post );
			$c = getLastData ( "contact", "id_contact" );
			$post ["activeKey"] = generateKey ( $c );
			$post ["active"] = 0;
			$post ["password"] = md5 ( $_POST ["password"] );
			$post ["id_contact"] = $c ["id_contact"];
			createData ( "user", $post );
			sessionStart ( getLastData ( "user", "id_user" ) );
			echo "Inscription réussie";
			
			$destinataire = $c ["eMail"];
			$titre = "Inscription au site Judo Competitor";
			$message = "Cher " . $c ["lastName"] . " " . $c ["firstName"] . ",\n\n";
			$message .= "Bienvenue sur le site Judo Competitor. Afin d'activer votre compte, veuillez cliquer sur le lien ci-dessous:\n";
			$message .= "<a href='http://judo-competitor.fr/register/" . $post ["activeKey"] . "'>Cliquez ici</a>\n\n";
			$message .= "A bientôt sur Judo Competitor.";
			
			mail ( $destinataire, $titre, $message );
			echo "<meta http-equiv='refresh' content='0;URL=accueil'>";
		}
	}
	?>
	<form method='post'>
		<table>
			<tr>
				<td>Identifiant</td>
				<td><input type='text' name='login'
					value='<?php echo empty($post["login"]) ? "" : $post["login"]; ?>' /></td>
			</tr>
			<tr>
				<td>Mot de passe</td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
				<td>Vérification</td>
				<td><input type='password' name='password_verif' /></td>
			</tr>
			<tr>
				<td>Adresse Mail</td>
				<td><input type='text' name='eMail'
					value='<?php echo empty($post["eMail"]) ? "" : $post["eMail"]; ?>' /></td>
			</tr>
			<tr>
				<td>Nom</td>
				<td><input type='text' name='lastName'
					value='<?php echo empty($post["lastName"]) ? "" : $post["lastName"]; ?>' /></td>
			</tr>
			<tr>
				<td>Prénom</td>
				<td><input type='text' name='firstName'
					value='<?php echo empty($post["firstName"]) ? "" : $post["firstName"]; ?>' /></td>
			</tr>
			<tr>
				<td>Téléphone</td>
				<td><input type='text' name='phone'
					value='<?php echo empty($post["phone"]) ? "" : $post["phone"]; ?>' /></td>
			</tr>
			<tr>
				<td>Adresse</td>
				<td><input type='text' name='address'
					value='<?php echo empty($post["address"]) ? "" : $post["address"]; ?>' /></td>
			</tr>
			<tr>
				<td>Ville</td>
				<td><input type='text' name='city'
					value='<?php echo empty($post["city"]) ? "" : $post["city"]; ?>' /></td>
			</tr>
			<tr>
				<td>Code Postal</td>
				<td><input type='text' name='postalCode'
					value='<?php echo empty($post["postalCode"]) ? "" : $post["postalCode"]; ?>' /></td>
			</tr>
			<tr>
				<td>Pays</td>
				<td><input type='text' name='country'
					value='<?php echo empty($post["country"]) ? "" : $post["country"]; ?>' /></td>
			</tr>
			<tr>
				<td colspan='2'><div class="g-recaptcha"
						data-sitekey="6Ld0XhsTAAAAAOwtzDXHT3PVesxF4ZIBti032cWw"></div></td>
			</tr>
			<tr>
				<td colspan='2' align='center'><input type='hidden' name='action'
					value='ok' /> <input type='hidden' name='id_level' value='2' /> <input
					type='submit' /></td>
			</tr>
		</table>
	</form>
</div>
<?php } ?>