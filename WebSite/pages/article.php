<?php
$action = getPostVariable ( "action" );
if ($action == "new") {
	echo "<div align='center'>Nouvel article</div>";
	displayEditor ();
} elseif ($action == "newok") {
	$categories = getPostVariable ( "id_category" );
	unset ( $_POST ["id_category"] );
	createData ( "article", $_POST );
	$article = getLastData ( "article", "id_article" );
	if ($categories != false) {
		foreach ( $categories as $category ) {
			createData ( "belongTo", array (
					"id_article" => $article ["id_article"],
					"id_category" => $category 
			) );
		}
	}
	echo "Article posté";
	echo "<meta http-equiv='refresh' content='1;URL=article/" . $article ["id_article"] . "'>";
} elseif ($action == "update") {
	$id_article = getPostVariable ( "id" );
	displayEditor ( $id_article );
} elseif ($action == "updateok") {
	$id_article = getPostVariable ( "id" );
	$categories = getPostVariable ( "id_category" );
	unset ( $_POST ["id_category"] );
	updateData ( "article", "id_article", $id_article, $_POST );
	if (existData ( "belongTo", "id_article", $id_article ))
		deleteTableByData ( "belongTo", "id_article", $id_article );
	if ($categories != false) {
		foreach ( $categories as $category ) {
			createData ( "belongTo", array (
					"id_article" => $id_article,
					"id_category" => $category 
			) );
		}
	}
	echo "Article modifié";
	echo "<meta http-equiv='refresh' content='1;URL=article/" . $id_article . "'>";
} elseif ($action == "comment") {
	$e = array ();
	if (! getPostVariable ( "comment" ))
		$e [] = "Erreur dans le commentaire, veuillez réessayer";
	if (! getPostVariable ( "id_article" ))
		$e [] = "L'article spécifié n'existe pas ou a été déplacé";
	if (getSessionVariable ( "l.level" ) < VAR_LVL_MEMBRE)
		$e [] = "Vous devez être connecté pour poster un commentaire";
	
	if (sizeof ( $e ) > 0) {
		echo "Commentaire non valide, il y a " . sizeof ( $e ) . " erreurs: <br />";
		foreach ( $e as $erreur ) {
			echo $erreur . "<br />";
		}
	} else {
		createData ( "comment", $_POST );
		$id_article = getPostVariable ( "id_article" );
		echo "Commentaire posté";
		echo "<meta http-equiv='refresh' content='0;URL=../article/" . $id_article . "'>";
	}
} else {
	$id_article = readNextArgument ();
	if (existData ( "article", "id_article", $id_article )) {
		displayArticle ( $id_article );
		if (getSessionVariable ( "l.level" ) >= VAR_LVL_ADMIN) {
			echo "<form method='post'>";
			echo "<input type='submit' value='Modifier'>";
			echo "<input type='hidden' name='action' value='update' />";
			echo "<input type='hidden' name='id' value='" . $id_article . "' />";
			echo "</form>";
		}
		if (getSessionVariable ( "l.level" ) >= VAR_LVL_MEMBRE)
			displayCommentEditor ( $id_article );
		if (countTableByData ( "comment", "id_article", $id_article ) > 0)
			displayComments ( $id_article );
	} else {
		getContent ( "404" );
	}
}
?>