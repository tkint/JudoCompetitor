<table id="listeArticles">
	<tr>
		<td>Titre</td>
		<td>Auteur</td>
		<td>Posté le</td>
		<td>Modifié le</td>
	</tr>
<?php
foreach ( getListTable ( "article", "id_article DESC" ) as $article ) {
	$level = getTableByData ( "level", "id_level", $article ["id_level"] );
	if ((! isSessionStarted () && ($level ["level"] == 0)) || (isSessionStarted () && getSessionVariable ( "l.level" ) >= $level ["level"])) {
		$auteur = getTableByData ( "user", "id_user", $article ["id_user"] );
		$contact = getTableByData ( "contact", "id_contact", $auteur ["id_contact"] );
		echo "<tr>";
		echo "<td><a href='../article/" . $article ["id_article"] . "'>" . $article ["title"] . "</a></td>";
		echo "<td><a href='../membre/" . $article ["id_user"] . "'>" . $contact ["firstName"] . " " . $contact ["lastName"] . "</a></td>";
		echo "<td>" . cleanDate ( $article ["dateCreate"] ) . "</td>";
		echo "<td>" . cleanDate ( $article ["dateUpdate"] ) . "</td>";
		echo "</tr>";
	}
}
?>
</table>