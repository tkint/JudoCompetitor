<?php
$id_article = getPostVariable ( "id" );
$action = getPostVariable ( "action" );
if ($action != false && $id_article != false && existData ( "article", "id_article", $id_article )) {
	echo "<div align='center'><div id='avert'>";
	$article = getTableByData ( "article", "id_article", $id_article );
	if ($action == "delete") {
		echo "Vous allez supprimer l'article " . $article ["title"] . ".<br />Confirmer?<br />";
		echo "<form method='post' style='margin: 0px; padding: 0px;display:inline;'>";
		echo "<input type='submit' value='Oui' />";
		echo "<input type='hidden' name='action' value='deleteok' />";
		echo "<input type='hidden' name='id' value='" . $id_article . "' />";
		echo "</form>";
		echo "<form method='post' style='margin: 0px; padding: 0px;display:inline;'>";
		echo "<input type='submit' value='Non' />";
		;
		echo "</form>";
	} elseif ($action == "deleteok") {
		deleteTableByData ( "article", "id_article", $id_article );
		echo "L'article " . $article ["title"] . " a bien été supprimé.";
	}
	echo "</div></div>";
}
?>
<form method='post' action='../article'
	style='margin: 0px; padding: 0px; display: inline;'>
	<input type='submit' value='Nouveau' /> <input type='hidden'
		name='action' value='new' />
</form>
<table id="listeArticles">
	<tr>
		<td>Titre</td>
		<td>Auteur</td>
		<td>Posté le</td>
		<td>Modifié le</td>
		<td width='15%'>Action</td>
	</tr>
<?php
foreach ( getListTable ( "article" ) as $article ) {
	$auteur = getTableByData ( "user", "id_user", $article ["id_user"] );
	$contact = getTableByData ( "contact", "id_contact", $auteur ["id_contact"] );
	echo "<tr>";
	echo "<td><a href='../article/" . $article ["id_article"] . "'>" . $article ["title"] . "</a></td>";
	echo "<td><a href='../membre/" . $article ["id_user"] . "'>" . $contact ["firstName"] . " " . $contact ["lastName"] . "</a></td>";
	echo "<td>" . cleanDate ( $article ["dateCreate"] ) . "</td>";
	echo "<td>" . cleanDate ( $article ["dateUpdate"] ) . "</td>";
	echo "<td>";
	echo "<form method='post' action='../article' style='margin: 0px; padding: 0px;display:inline;'>";
	echo "<input type='submit' class='sublink' value='Modifier'>";
	echo "<input type='hidden' name='action' value='update' />";
	echo "<input type='hidden' name='id' value='" . $article ["id_article"] . "' />";
	echo "</form>";
	echo "<form method='post' style='margin: 0px; padding: 0px;display:inline;'>";
	echo "<input type='submit' class='sublink' value='Supprimer'>";
	echo "<input type='hidden' name='action' value='delete' />";
	echo "<input type='hidden' name='id' value='" . $article ["id_article"] . "' />";
	echo "</form>";
	echo "</td>";
	echo "</tr>";
}
?>
</table>