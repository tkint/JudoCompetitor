<?php
/** 
 * Fonction de tri de tableau
 * @param unknown $array
 * @param unknown $on
 * @param string $order
 * @return multitype:unknown
 */
function array_sort($array, $on, $order = SORT_ASC) {
	$new_array = array ();
	$sortable_array = array ();
	
	if (count ( $array ) > 0) {
		foreach ( $array as $k => $v ) {
			if (is_array ( $v )) {
				foreach ( $v as $k2 => $v2 ) {
					if ($k2 == $on) {
						$sortable_array [$k] = $v2;
					}
				}
			} else {
				$sortable_array [$k] = $v;
			}
		}
		
		switch ($order) {
			case SORT_ASC :
				asort ( $sortable_array );
				break;
			case SORT_DESC :
				arsort ( $sortable_array );
				break;
		}
		
		foreach ( $sortable_array as $k => $v ) {
			$new_array [$k] = $array [$k];
		}
	}
	
	return $new_array;
}
/**
 * Intégration de fichier HTML
 *
 * @param file $htmlFile        	
 */
function includeHtml($htmlFile) {
	$content = file_get_contents ( PATH_HTML . "/" . $htmlFile );
	echo $content;
}
/**
 * Retourne la valeur d'un GET
 *
 * @param string $name        	
 * @return La valeur de la variable si elle existe, FALSE sinon
 */
function getGetVariable($name) {
	if (isset ( $_GET ) && ! empty ( $_GET [$name] )) {
		return $_GET [$name];
	}
	return false;
}
/**
 * Retourne la valeur d'un POST
 *
 * @param string $name        	
 * @return La valeur de la variable si elle existe, FALSE sinon
 */
function getPostVariable($name) {
	if (isset ( $_POST ) && ! empty ( $_POST [$name] )) {
		return $_POST [$name];
	}
	return false;
}
/**
 * Retourne la valeur d'une variable de session
 *
 * @param string $name        	
 * @return La valeur si la variable si elle existe, FALSE sinon
 */
function getSessionVariable($name) {
	if (isSessionStarted () && isset ( $_SESSION [$name] )) {
		return $_SESSION [$name];
	}
	return false;
}
/**
 * Retourne le menu sous forme de liens
 *
 * @return string
 */
function afficheMenuButton() {
	global $menu_nav;
	global $menu_def;
	global $menu_on;
	global $menu_off;
	global $level;
	$menu = array ();
	$res = "<ul id='navbar'>";
	if (isSessionStarted ()) {
		$menu = array_merge ( $menu_def, $menu_on );
	} else {
		$menu = array_merge ( $menu_def, $menu_off );
	}
	foreach ( $menu as $key => $value ) {
		if ((! isSessionStarted () && (! isset ( $level [$value] ) || $level [$value] == 0)) || (isSessionStarted () && getSessionVariable ( "l.level" ) >= $level [$value])) {
			$res .= "<li><a href='../../" . $value . "'>" . $key . "</a></li>";
			$menu_nav [$key] = $value;
		}
	}
	foreach ( getListTable ( "article" ) as $article ) {
		$level = getTableByData ( "level", "id_level", $article ["id_level"] );
		if ((! isSessionStarted () && ($level ["level"] == 0)) || (isSessionStarted () && getSessionVariable ( "l.level" ) >= $level ["level"])) {
			$menu_nav [] = "article/" . $article ["id_article"];
		}
	}
	$res .= "</ul>";
	return $res;
}
/**
 * Affiche l'aperçu rapide de l'utilisateur connecté
 */
function afficheUserData() {
	if (isSessionStarted ()) {
		$grade = getSessionVariable ( "g.level" );
		if (! $grade) {
			$grade = 0;
		}
		echo "<script>userdata.style.backgroundImage=\"url('../../img/belt/belt_" . $grade . ".png')\"</script>";
		echo "<a href='../../membre/" . getSessionVariable ( "u.id_user" ) . "'>" . getSessionVariable ( "c.firstName" ) . " " . getSessionVariable ( "c.lastName" ) . "</a><br />";
		echo getSessionVariable ( "l.name" );
		echo "<br /><a href='../../disconnect'>Déconnexion</a>";
	} else {
		echo "<script>userdata.style.backgroundImage=\"url('../../img/belt/belt_0.png')\"</script>";
		echo "Visiteur";
		echo "<br /><a href='../../connect'>Connexion</a>";
		echo "<br /><a href='../../register'>Inscription</a>";
	}
}
/**
 * Créer la session de l'utilisateur qui se connecte
 *
 * @param string $user
 *        	id_user de l'utilisateur
 */
function sessionStart($user) {
	// session_start ();
	foreach ( $user as $key => $value ) {
		$_SESSION ["u." . $key] = $value;
	}
	foreach ( getTableByData ( "contact", "id_contact", $user ["id_contact"] ) as $key => $value ) {
		$_SESSION ["c." . $key] = $value;
	}
	foreach ( getTableByData ( "level", "id_level", $user ["id_level"] ) as $key => $value ) {
		$_SESSION ["l." . $key] = $value;
	}
	if (existData ( "judoka", "id_contact", $user ["id_contact"] )) {
		foreach ( getTableByData ( "judoka", "id_contact", $user ["id_contact"] ) as $key => $value ) {
			$_SESSION ["j." . $key] = $value;
		}
		foreach ( getTableByData ( "rank", "id_rank", $_SESSION ["j.id_rank"] ) as $key => $value ) {
			$_SESSION ["g." . $key] = $value;
		}
	}
}
/**
 * Met à jour la session de l'utilisateur ciblé
 *
 * @param string $user
 *        	id_user de l'utilisateur
 */
function sessionUpdate($user) {
	foreach ( $user as $key => $value ) {
		if ($_SESSION ["u." . $key] != $value) {
			$_SESSION ["u." . $key] = $value;
		}
	}
	foreach ( getTableByData ( "contact", "id_contact", $user ["id_contact"] ) as $key => $value ) {
		if ($_SESSION ["c." . $key] != $value) {
			$_SESSION ["c." . $key] = $value;
		}
	}
	foreach ( getTableByData ( "level", "id_level", $user ["id_level"] ) as $key => $value ) {
		if ($_SESSION ["l." . $key] != $value) {
			$_SESSION ["l." . $key] = $value;
		}
	}
	if (existData ( "judoka", "id_contact", $user ["id_contact"] )) {
		foreach ( getTableByData ( "judoka", "id_contact", $user ["id_contact"] ) as $key => $value ) {
			if ($_SESSION ["j." . $key] != $value) {
				$_SESSION ["j." . $key] = $value;
			}
		}
		foreach ( getTableByData ( "rank", "id_rank", $_SESSION ["j.id_rank"] ) as $key => $value ) {
			if ($_SESSION ["g." . $key] != $value) {
				$_SESSION ["g." . $key] = $value;
			}
		}
	}
}
/**
 * Retourne true si une session est en cours
 *
 * @return boolean
 */
function isSessionStarted() {
	$result = isset ( $_SESSION ) && ! empty ( $_SESSION );
	// if (! $result) {
	// session_start ();
	// $result = isset ( $_SESSION ) && ! empty ( $_SESSION );
	// if (! $result) {
	// session_destroy ();
	// }
	// }
	return $result;
}
/**
 * Retourne la page passée en argument dans l'url
 *
 * @return string
 */
function getMenu() {
	global $arguments;
	$menu = $arguments [0];
	return $menu;
}
/**
 * Récupère le contenu de la page indiquée
 *
 * @param string $menu        	
 * @return boolean
 */
function getContent($menu = null) {
	if (! isset ( $menu )) {
		$menu = getMenu ();
	}
	if ($menu == "admin") {
		$menu = readNextArgument ();
		$path = PATH_ADMIN;
	} else {
		$path = PATH_PAGE;
	}
	if (! $menu) {
		$menu = PAGE_DEFAULT;
	}
	$filename = $path . "/" . $menu . ".php";
	if (! is_file ( $filename )) {
		$menu = PAGE_ERROR;
		$filename = PATH_PAGE . "/" . $menu . ".php";
		include_once $filename;
		return false;
	}
	include_once $filename;
	return true;
}
/**
 * Fonction qui rafraîchi la page
 */
function refresh() {
	echo "<META HTTP-EQUIV=\"Refresh\" CONTENT=\"0\" />";
}
/**
 * Fonction qui génère une clef qui servira à valider l'utilisateur
 *
 * @param unknown $usr        	
 * @return string
 */
function generateKey($usr) {
	$string = date ( "Y-m-d" ) . $usr ["firstName"] . $usr ["lastName"];
	return md5 ( $string );
}
/**
 * Fonction qui vérifie la validité de la clef
 *
 * @param unknown $usr        	
 * @param unknown $key        	
 */
function checkKey($usr, $key) {
}
/**
 * Fonction qui extrait les arguments de l'url
 */
function processUrlVariables() {
	global $arguments;
	$url = "";
	if (($url = getSessionVariable ( VAR_MENU )) === false) {
		$url = getGetVariable ( VAR_MENU );
	} else {
		unset ( $_SESSION [VAR_MENU] );
	}
	if (! empty ( $url )) {
		global $menu;
		if (strpos ( $url, "/" ) > 0) {
			$arguments = explode ( "/", $url );
			$menu = $arguments [0];
			unset ( $arguments [0] );
		} else {
			$menu = $url;
		}
	}
}
/**
 * Fonction qui retourne l'argument suivant
 *
 * @return unknown
 */
function readNextArgument() {
	global $arguments;
	if (! empty ( $arguments [1] )) {
		$arg = $arguments [1];
		foreach ( $arguments as $key => $value ) {
			$arguments [$key - 1] = $value;
		}
		// unset ( $arguments [1] );
	} else {
		$arg = false;
	}
	return $arg;
}
/**
 * Vérification de concordance de données
 *
 * @param string $table
 *        	nom de la table
 * @param string $key
 *        	champ clef
 * @param string $value
 *        	valeur du champ clef
 * @param string $data_key
 *        	champ cible
 * @param string $data_value
 *        	valeur du champ cible
 * @return TRUE si les données concordent, FALSE sinon
 */
function matchTableData($table, $key, $value, $data_key, $data_value) {
	$t = getTableByData ( $table, $key, $value );
	if ($t [$data_key] == $data_value) {
		return true;
	}
	return false;
}
/**
 * Vérifie l'existence d'un valeur dans une table
 *
 * @param string $table        	
 * @param string $key
 *        	champ clef
 * @param string $value
 *        	valeur du champ clef
 * @return TRUE si la valeur existe, FALSE sinon
 */
function existData($table, $key, $value) {
	foreach ( getListTable ( $table ) as $row ) {
		if ($row [$key] == $value) {
			return true;
		}
	}
	return false;
}
/**
 * Vérifie que le champ existe dans la table
 *
 * @param string $table        	
 * @param string $column        	
 * @return TRUE si le champ existe, FALSE sinon
 */
function existColumn($table, $column) {
	foreach ( getTableColumns ( $table ) as $k => $v ) {
		if ($v [0] == $column) {
			return true;
		}
	}
	return false;
}
/**
 * Nettoie les valeurs d'un array
 *
 * @param array $array        	
 * @return le tableau nettoyé
 */
function cleanArray($array) {
	foreach ( $array as $key => $value ) {
		if (is_string ( $value ))
			$array [$key] = htmlspecialchars ( $value );
	}
	return $array;
}
/**
 * Formate une date dans le format JJ/MM/AAAA
 *
 * @param string $date        	
 * @param boolean $heure
 *        	TRUE si on veut garder l'heure, FALSE sinon
 * @return string
 */
function cleanDate($date, $heure = false) {
	$t = explode ( " ", $date );
	$j = explode ( "-", $t [0] );
	$r = $j [2] . "/" . $j [1] . "/" . $j [0];
	if ($heure && sizeof ( $t ) == 2) {
		$r .= " " . $t [1];
	}
	return $r;
}
/**
 * Convertit une date ou un timestamp en date compatible avec l'input date de HTML
 *
 * @param string $date        	
 * @return string
 */
function convertHTMLDate($date) {
	if ($date != time ())
		return date ( "Y-m-d", strtotime ( $date ) );
	else
		return date ( "Y-m-d", $date );
}
/**
 * Affiche l'editeur d'article
 *
 * @param string $id_article
 *        	si non null, l'editeur affiche les données de l'article à modifier
 */
function displayEditor($id_article = null) {
	if ($id_article == null) {
		$title = "";
		$text = "";
		$level = 1;
		$dateCreate = time ();
	} else {
		$article = getTableByData ( "article", "id_article", $id_article );
		$title = $article ["title"];
		$text = $article ["article"];
		$level = $article ["id_level"];
		$dateCreate = $article ["dateCreate"];
	}
	echo "<form method='post' action='../article'>";
	echo "<table style='width: 100%'>";
	echo "<tr>";
	echo "<td>Date de création</td>";
	echo "<td>" . cleanDate ( convertHTMLDate ( $dateCreate ) ) . "</td>";
	echo "</tr>";
	// Titre
	echo "<tr>";
	echo "<td>Titre</td>";
	echo "<td><input type='text' name='title' value='" . $title . "' /></td>";
	echo "</tr>";
	// Article
	echo "<tr>";
	echo "<td colspan='2'><textarea name='article' style='width: 100%; height: 300px'>" . $text . "</textarea></td>";
	echo "</tr>";
	// Niveau minimum
	echo "<tr>";
	echo "<td>Niveau minimum requis</td>";
	echo "<td><select name='id_level'>";
	foreach ( getListTable ( "level", "level" ) as $lvl ) {
		echo "<option value='" . $lvl ["id_level"] . "'";
		if ($lvl ["id_level"] == $level) {
			echo " selected ";
		}
		echo ">" . $lvl ["level"] . " - " . $lvl ["name"] . "</option>";
	}
	echo "</select></td>";
	echo "</tr>";
	// Catégories
	echo "<tr>";
	echo "<td>Catégorie</td>";
	echo "<td>";
	echo "<table id='listeCheck'><tr>";
	foreach ( getListTable ( "category" ) as $i => $category ) {
		echo "<td><input type='checkbox' name='id_category[]' value='" . $category ["id_category"] . "'";
		foreach ( getListTableByData ( "belongTo", "id_article", $id_article ) as $belongTo ) {
			if ($category ["id_category"] == $belongTo ["id_category"]) {
				echo " checked";
			}
		}
		echo "/>" . utf8_encode ( $category ["name"] ) . "</td>";
		if ($i % 5 == 1) {
			echo "</tr><tr>";
		}
	}
	echo "</tr></table>";
	echo "</tr>";
	// Validation
	echo "<tr>";
	echo "<td align='center' colspan='2'>";
	echo "<input type='hidden' name='dateUpdate' value='" . date ( "Y/m/d - h:i:s" ) . "' />";
	if ($id_article != null) {
		echo "<input type='hidden' name='action' value='updateok' />";
		echo "<input type='hidden' name='id' value='" . $id_article . "' />";
	} else {
		echo "<input type='hidden' name='id_user' value='" . getSessionVariable ( "u.id_user" ) . "' />";
		echo "<input type='hidden' name='dateCreate' value='" . date ( "Y/m/d - h:i:s" ) . "' />";
		echo "<input type='hidden' name='action' value='newok' />";
	}
	echo "<input type='submit' value='Poster' /></td>";
	echo "</tr>";
	echo "</table>";
	echo "</form>";
}
/**
 * Affiche l'article définit
 *
 * @param string $id_article        	
 */
function displayArticle($id_article) {
	$article = getTableByData ( "article", "id_article", $id_article );
	$user = getTableByData ( "user", "id_user", $article ["id_user"] );
	$auteur = getTableByData ( "contact", "id_contact", $user ["id_contact"] );
	echo "<table id='article'>";
	echo "<tr><td>" . $article ["title"] . "</td></tr>";
	echo "<tr><td>Ecrit par " . $auteur ["firstName"] . " " . $auteur ["lastName"] . "</td></tr>";
	echo "<tr><td>Le " . cleanDate ( $article ["dateCreate"], true ) . "</td></tr>";
	echo "<tr><td><pre>" . $article ["article"] . "</pre></td></tr>";
	echo "<tr><td>";
	$belong = getListTableByData ( "belongTo", "id_article", $id_article );
	foreach ( $belong as $i => $belongTo ) {
		$category = getTableByData ( "category", "id_category", $belongTo ["id_category"] );
		echo utf8_encode ( $category ["name"] );
		if ($i < sizeof ( $belong ) - 1)
			echo " | ";
	}
	echo "</table>";
}
/**
 * Affiche l'editeur de commentaire sur un article
 *
 * @param integer $id_article        	
 */
function displayCommentEditor($id_article) {
	echo "<div  id='comments'><form method='post'><table><tr><td>";
	echo getSessionVariable ( "c.firstName" ) . " " . getSessionVariable ( "c.lastName" );
	echo "</td><td>";
	echo "<textarea name='comment' style='width:90%; height: 70px;float:left;'></textarea>";
	echo "<input type='hidden' name='id_user' value='" . getSessionVariable ( "u.id_user" ) . "'/>";
	echo "<input type='hidden' name='id_article' value='" . $id_article . "'/>";
	echo "<input type='hidden' name='dateCreate' value='" . date ( "Y/m/d - h:i:s" ) . "' />";
	echo "<input type='hidden' name='action' value='comment'/>";
	echo "<input type='submit' style='width: 10%;height: 70px;' value='Poster'/>";
	echo "</td></tr></table></form>";
}
/**
 * Affiche la liste des commentaires d'un article
 *
 * @param integer $id_article        	
 */
function displayComments($id_article) {
	echo "<div  id='comments'><table border='1'>";
	foreach ( getListTableByData ( "comment", "id_article", $id_article, "dateCreate DESC" ) as $comment ) {
		$user = getTableByData ( "user", "id_user", $comment ["id_user"] );
		$contact = getTableByData ( "contact", "id_contact", $user ["id_contact"] );
		echo "<tr>";
		echo "<td rowspan='2'>" . $contact ["firstName"] . " " . $contact ["lastName"] . "</td>";
		echo "<td>" . cleanDate ( $comment ["dateCreate"], true ) . "</td>";
		echo "</tr>";
		echo "<tr><td><pre>" . $comment ["comment"] . "</pre></td></tr>";
	}
	echo "</table></div>";
}
/**
 * Affiche le club définit
 *
 * @param string $id_club        	
 */
function displayClub($id_club) {
	$club = getTableByData ( "club", "id_club", $id_club );
	echo "<table id='club' border='1'>";
	echo "<tr><td colspan='3'>" . $club ["name"] . "</td></tr>";
	echo "<tr><td>Adresse</td><td>" . $club ["address"] . "</td><td>Judokas</td></tr>";
	echo "<tr><td>Code Postal</td><td>" . $club ["postalCode"] . "</td>";
	echo "<td rowspan='2'>";
	echo "<table>";
	foreach ( getListTableByData ( "judoka", "id_club", $id_club ) as $judoka ) {
		$contact = getTableByData ( "contact", "id_contact", $judoka ["id_contact"] );
		$user = getTableByData ( "user", "id_contact", $contact ["id_contact"] );
		echo "<tr><td><a href='../membre/" . $user ["id_user"] . "'>" . $contact ["firstName"] . " " . $contact ["lastName"] . "</a></td></tr>";
	}
	echo "</table>";
	echo "</td></tr>";
	echo "<tr><td>Ville</td><td>" . $club ["city"] . "</td></tr>";
	echo "</table>";
}
/**
 * Affiche l'editeur de club
 *
 * @param string $id_club
 *        	si non null, affiche les données du club à modifier
 */
function displayClubEditor($id_club = null) {
	$club = getTableByData ( "club", "id_club", $id_club );
	if ($club !== false) {
		$name = $club ["name"];
	} else {
		$name = "";
	}
	echo "<form method='post'>";
	echo "<table>";
	echo "<tr><td>";
	if ($club !== false) {
		echo "Modifier club";
	} else {
		echo "Nouveau club";
	}
	echo "</td></tr>";
	echo "<tr><td>Nom</td><td><input type='text' name='name' value='" . $club ["name"] . "'/>";
	echo "<tr><td>Adresse</td><td><input type='text' name='address' value='" . $club ["address"] . "'/>";
	echo "<tr><td>Code Postal</td><td><input type='text' name='postalCode' value='" . $club ["postalCode"] . "'/>";
	echo "<tr><td>Ville</td><td><input type='text' name='city' value='" . $club ["city"] . "'/>";
	echo "<tr><td>Region</td><td><select name='id_region'/>";
	foreach ( getListTable ( "region" ) as $region ) {
		echo "<option value='" . $region ["id_region"] . "'";
		if ($region ["id_region"] == $club ["id_region"]) {
			echo " selected";
		}
		echo "/>" . $region ["name"] . "</option>";
	}
	echo "</select></td></tr>";
	echo "<tr><td>";
	if ($club !== false) {
		echo "<input type='hidden' name='action' value='updateok' />";
		echo "<input type='hidden' name='id' value='" . $club ["id_club"] . "'/>";
	} else {
		echo "<input type='hidden' name='action' value='newok' />";
	}
	echo "</td></tr>";
	echo "<tr><td><input type='submit'/></td></tr>";
	echo "</table>";
	echo "</form>";
}
/**
 * Affiche le message ciblé
 *
 * @param string $id_message        	
 */
function displayMessage($id_message) {
	$message = getTableByData ( "message", "id_message", $id_message );
	$u_auteur = getTableByData ( "user", "id_user", $message ["id_author"] );
	$u_destinataire = getTableByData ( "user", "id_user", $message ["id_target"] );
	if ($u_auteur ["id_user"] == getSessionVariable ( "u.id_user" ) || $u_destinataire ["id_user"] == getSessionVariable ( "u.id_user" )) {
		$auteur = getTableByData ( "contact", "id_contact", $u_auteur ["id_contact"] );
		$destinataire = getTableByData ( "contact", "id_contact", $u_destinataire ["id_contact"] );
		echo "<table id='corpsmessage'>";
		echo "<tr><td>De:</td><td>" . $auteur ["firstName"] . " " . $auteur ["lastName"] . "</td></tr>";
		echo "<tr><td>A:</td><td>" . $destinataire ["firstName"] . " " . $destinataire ["lastName"] . "</td></tr>";
		echo "<tr><td>Titre:</td><td>" . $message ["title"] . "</td></tr>";
		echo "<tr><td colspan='2'><hr/>" . $message ["message"] . "</td></tr>";
		echo "</table>";
	} elseif (is_numeric ( $id_message )) {
		echo "Message inexistant";
	}
}
/**
 * Affiche l'editeur de message
 */
function displayMessageEditor() {
	echo "<form method='post'>";
	echo "<table id='corpsmessage'>";
	echo "<tr><td>Destinataire</td><td><select name='id_target'>";
	foreach ( getListTable ( "contact WHERE id_contact != '" . getSessionVariable ( "c.id_contact" ) . "'", "firstName" ) as $contact ) {
		$user = getTableByData ( "user", "id_contact", $contact ["id_contact"] );
		echo "<option value='" . $user ["id_user"] . "'>" . $contact ["firstName"] . " " . $contact ["lastName"] . "</option>";
	}
	echo "</select></td></tr>";
	echo "<tr><td>Titre</td><td><input type='text' name='title'/></td></tr>";
	echo "<tr><td colspan='2'><hr/><textarea name='message'></textarea></td></tr>";
	echo "<tr><td align='center' colspan='2'>";
	echo "<input type='hidden' name='id_author' value='" . getSessionVariable ( "u.id_user" ) . "'/>";
	echo "<input type='hidden' name='action' value='newok'/>";
	echo "<input type='submit' value='Envoyer le message'></td></tr>";
	echo "</table>";
	echo "</form>";
}
/**
 * Affiche l'editeur de competition
 *
 * @param string $id_competition
 *        	si non null, affiche les données de la compétition à modifier
 */
function displayCompetEditor($id_competition = null) {
	$competition = getTableByData ( "competition", "id_competition", $id_competition );
	if ($competition !== false) {
		$name = $competition ["name"];
		$dateStart = $competition ["dateStart"];
		$dateEnd = $competition ["dateEnd"];
		$capacity = $competition ["capacity"];
		$address = $competition ["address"];
		$postalCode = $competition ["postalCode"];
		$city = $competition ["city"];
		$country = $competition ["country"];
	} else {
		$name = "";
		$dateStart = time ();
		$dateEnd = time ();
		$capacity = "";
		$address = "";
		$postalCode = "";
		$city = "";
		$country = "FRANCE";
	}
	echo "<form method='post'>";
	echo "<table><tr><td>";
	echo "<table>";
	echo "<tr><td>";
	if ($competition !== false) {
		echo "Modifier compétition";
	} else {
		echo "Nouvelle compétition";
	}
	echo "</td></tr>";
	echo "<tr><td>Nom</td><td><input type='text' name='name' value='" . $name . "'/>";
	echo "<tr><td>Début</td><td><input type='date' name='dateStart' value='" . convertHTMLDate ( $dateStart ) . "'/>";
	echo "<tr><td>Fin</td><td><input type='date' name='dateEnd' value='" . convertHTMLDate ( $dateEnd ) . "'/>";
	echo "<tr><td>Capacité</td><td><input type='text' name='capacity' value='" . $capacity . "'/>";
	echo "<tr><td>Adresse</td><td><input type='text' name='address' value='" . $address . "'/>";
	echo "<tr><td>Code Postal</td><td><input type='text' name='postalCode' value='" . $postalCode . "'/>";
	echo "<tr><td>Ville</td><td><input type='text' name='city' value='" . $city . "'/>";
	echo "<tr><td>Pays</td><td><input type='text' name='country' value='" . $country . "'/>";
	echo "<tr><td>Region</td><td><select name='id_region'/>";
	foreach ( getListTable ( "region" ) as $region ) {
		echo "<option value='" . $region ["id_region"] . "'";
		if ($region ["id_region"] == $competition ["id_region"]) {
			echo " selected";
		}
		echo "/>" . $region ["name"] . "</option>";
	}
	echo "</select></td></tr>";
	echo "<tr><td>";
	if ($competition !== false) {
		echo "<input type='hidden' name='action' value='updateok' />";
		echo "<input type='hidden' name='id' value='" . $competition ["id_competition"] . "'/>";
	} else {
		echo "<input type='hidden' name='action' value='newok' />";
	}
	echo "</td></tr>";
	echo "<tr><td><input type='submit'/></td></tr>";
	echo "</table></td><td>";
	echo "Concerne:";
	echo "<table id='listeCheck'><tr>";
	foreach ( getListTable ( "rank" ) as $i => $rank ) {
		echo "<td><input type='checkbox' name='id_rank[]' value='" . $rank ["id_rank"] . "'";
		foreach ( getListTableByData ( "concern", "id_competition", $id_competition ) as $concern ) {
			if ($rank ["id_rank"] == $concern ["id_rank"]) {
				echo " checked";
			}
		}
		echo ">" . $rank ["name"] . "</input></td>";
		if ($i % 2 == 1) {
			echo "</tr><tr>";
		}
	}
	echo "</tr></table>";
	echo "</td></tr></table>";
	echo "</form>";
}
/**
 * Retourne les marques d'un judoka pendant un combat
 *
 * @param integer $id_judoka        	
 * @param integer $id_fight        	
 * @return matrice {number, id_move}
 */
function getJudokaMark($id_judoka, $id_fight) {
	$return = array ();
	$marks = getListTableByData ( "mark", "id_judoka", $id_judoka );
	for($i = 0; $i < sizeof ( $marks ); $i ++) {
		if ($marks [$i] ["id_fight"] == $id_fight) {
			$return [$i] = array ();
			$move = getTableByData ( "move", "id_move", $marks [$i] ["id_move"] );
			$return [$i] ["number"] = $marks [$i] ["number"];
			$return [$i] ["id_move"] = $move ["id_move"];
		}
	}
	return $return;
}
/**
 * Retourne les combats d'un judoka pendant une compétition
 *
 * @param integer $id_judoka        	
 * @param integer $id_competition        	
 * @return matrice { id_fight, id_phase, points }
 */
function getFightJudoka($id_judoka, $id_competition) {
	$return = array ();
	$fights = array ();
	foreach ( getListTableByData ( "fight", "id_competition", $id_competition ) as $fight ) {
		$opponents = getJudokas ( $fight ["id_fight"] );
		if ($opponents [0] ["id_judoka"] == $id_judoka || $opponents [1] ["id_judoka"] == $id_judoka) {
			$fights [] = $fight;
		}
	}
	$return = array ();
	$judoka = getTableByData ( "judoka", "id_judoka", $id_judoka );
	for($i = 0; $i < sizeof ( $fights ); $i ++) {
		$points = 0;
		$return [$i] = array ();
		$return [$i] ["id_fight"] = $fights [$i] ["id_fight"];
		$return [$i] ["id_phase"] = $fights [$i] ["id_phase"];
		foreach ( getJudokaMark ( $id_judoka, $fights [$i] ["id_fight"] ) as $mark ) {
			$move = getTableByData ( "move", "id_move", $mark ["id_move"] );
			$points += $move ["points"];
		}
		$return [$i] ["points"] = $points;
	}
	return $return;
}
/**
 * Retourne les opposants d'un combat sous forme de tableau
 *
 * @param integer $id_fight        	
 * @return tableau d'id de judokas
 */
function getJudokas($id_fight) {
	$mark = getListTableByData ( "mark", "id_fight", $id_fight . " AND number = 0" );
	if (sizeof ( $mark ) < 2)
		return false;
	$judoka1 = getTableByData ( "judoka", "id_judoka", $mark [0] ["id_judoka"] );
	$judoka2 = getTableByData ( "judoka", "id_judoka", $mark [1] ["id_judoka"] );
	return array (
			$judoka1,
			$judoka2 
	);
}
/**
 * Retourne les points qu'a accumulé un judoka pendant un combat
 *
 * @param integer $id_judoka        	
 * @param integer $id_fight        	
 * @return integer
 */
function getJudokaPoints($id_judoka, $id_fight) {
	$points = 0;
	foreach ( getListTableByData ( "mark", "id_fight", $id_fight ) as $mark ) {
		if ($mark ["id_judoka"] == $id_judoka) {
			$move = getTableByData ( "move", "id_move", $mark ["id_move"] );
			$points += $move ["points"];
		}
	}
	return $points;
}
/**
 * Retourne l'id du judoka victorieux d'une combat
 *
 * @param unknown $id_fight        	
 * @return boolean
 */
function getWinner($id_fight) {
	$judokas = getJudokas ( $id_fight );
	if (getJudokaPoints ( $judokas [0] ["id_judoka"], $id_fight ) > getJudokaPoints ( $judokas [1] ["id_judoka"], $id_fight ))
		return $judokas [0] ["id_judoka"];
	else
		return $judokas [1] ["id_judoka"];
	return false;
}
/**
 * Génération de la matrice des scores
 *
 * @param integer $id_competition        	
 * @return matrice { id_contact, judoka, rank, fight, wins, looses, points }
 */
function generateLadder($id_competition) {
	$ladder = array ();
	$competition = getTableByData ( "competition", "id_competition", $id_competition );
	// On récupère l'id de chaque judoka participant à cette compétition
	$judokas = array ();
	foreach ( getListTableByData ( "participate", "id_competition", $id_competition ) as $participate ) {
		$judokas [] = $participate ["id_judoka"];
	}
	// Pour chaque judoka
	for($i = 0; $i < sizeof ( $judokas ); $i ++) {
		// On ajoute une ligne au ladder
		$ladder [$i] = array ();
		// On récupère les données nécessaires
		$judoka = getTableByData ( "judoka", "id_judoka", $judokas [$i] );
		$rank = getTableByData ( "rank", "id_rank", $judoka ["id_rank"] );
		$contact = getTableByData ( "contact", "id_contact", $judoka ["id_contact"] );
		$fights = getFightJudoka ( $judoka ["id_judoka"], $id_competition );
		// Initialisation des compteurs
		$points = 0;
		$wins = 0;
		foreach ( $fights as $fight ) {
			$points += $fight ["points"];
			if ($judokas [$i] == getWinner ( $fight ["id_fight"] )) {
				$wins ++;
			}
		}
		$ladder [$i] ["id_contact"] = $contact ["id_contact"];
		$ladder [$i] ["judoka"] = $contact ["firstName"] . " " . $contact ["lastName"];
		$ladder [$i] ["rank"] = $rank ["name"];
		$ladder [$i] ["fight"] = sizeof ( $fights );
		$ladder [$i] ["wins"] = $wins;
		$ladder [$i] ["looses"] = sizeof ( $fights ) - $wins;
		$ladder [$i] ["points"] = $points;
	}
	return $ladder;
}
/**
 * Vérifie si deux judokas ont déjà combattus durant la même phase d'une compétition (dans un même combat)
 *
 * @param integer $id_judoka        	
 * @param integer $id_opponent        	
 * @param integer $id_phase        	
 * @param integer $id_competition        	
 * @return l'id du combat dans lequel ils ont combattus, FALSE sinon
 */
function alreadyFight($id_judoka, $id_opponent, $id_phase, $id_competition) {
	foreach ( getListTableByData ( "fight", "id_competition", $id_competition ) as $fight ) {
		$judokas = getJudokas ( $fight ["id_fight"] );
		if ((($judokas [0] ["id_judoka"] == $id_judoka && $judokas [1] ["id_judoka"] == $id_opponent) || ($judokas [1] ["id_judoka"] == $id_judoka && $judokas [0] ["id_judoka"] == $id_opponent)) && $id_phase == $fight ["id_phase"]) {
			return $fight ["id_fight"];
		}
	}
	return false;
}