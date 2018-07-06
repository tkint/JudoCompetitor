<?php
$id_user = getPostVariable ( "id" );
$action = getPostVariable ( "action" );
if ($action != false && $id_user != false && existData ( "user", "id_user", $id_user )) {
	echo "<div align='center'><div id='avert'>";
	$user = getTableByData ( "user", "id_user", $id_user );
	$contact = getTableByData ( "contact", "id_contact", $user ["id_contact"] );
	if ($action == "delete") {
		echo "Vous allez supprimer le membre " . $contact ["firstName"] . " " . $contact ["lastName"] . " ainsi que toutes les données associées.<br />Confirmer?<br />";
		echo "<form method='post' style='margin: 0px; padding: 0px;display:inline;'>";
		echo "<input type='submit' value='Oui' />";
		echo "<input type='hidden' name='action' value='deleteok' />";
		echo "<input type='hidden' name='id' value='" . $id_user . "' />";
		echo "</form>";
		echo "<form method='post' style='margin: 0px; padding: 0px;display:inline;'>";
		echo "<input type='submit' value='Non' />";
		;
		echo "</form>";
	} elseif ($action == "deleteok") {
		deleteTableByData ( "message", "id_auteur", $user ["id_user"] );
		deleteTableByData ( "message", "id_destinataire", $user ["id_user"] );
		deleteTableByData ( "article", "id_auteur", $user ["id_user"] );
		deleteTableByData ( "judoka", "id_contact", $user ["id_contact"] );
		deleteTableByData ( "user", "id_user", $id_user );
		deleteTableByData ( "contact", "id_contact", $user ["id_contact"] );
		echo "Le membre " . $contact ["firstName"] . " " . $contact ["lastName"] . " ainsi que les toutes les données associées ont bien été supprimés.";
	}
	echo "</div></div>";
}
?>
<table id="liste">
	<tr>
		<td></td>
		<td>Prénom</td>
		<td>Nom</td>
		<td>Rang</td>
		<td>Club</td>
		<td>Action</td>
	</tr>
<?php
foreach ( getListTable ( "user" ) as $user ) {
	$contact = getTableByData ( "contact", "id_contact", $user ["id_contact"] );
	$judoka = getTableByData ( "judoka", "id_contact", $user ["id_contact"] );
	$level = getTableByData ( "level", "id_level", $user ["id_level"] );
	$rank = getTableByData ( "rank", "id_rank", $judoka ["id_rank"] );
	$club = getTableByData ( "club", "id_club", $judoka ["id_club"] );
	if (! $rank) {
		$rank = getTableByData ( "rank", "level", 1 );
	}
	echo "<tr>";
	echo "<td><img src='../img/belt/belt_" . $rank ["level"] . ".png' /></td>";
	echo "<td><a href='../membre/" . $user ["id_user"] . "'>" . $contact ["firstName"] . "</a></td>";
	echo "<td>" . $contact ["lastName"] . "</td>";
	echo "<td>" . $level ["name"] . "</td>";
	echo "<td>" . $club ["name"] . "</td>";
	
	echo "<td>";
	echo "<form method='post' style='margin: 0px; padding: 0px;display:inline;'>";
	echo "<input type='submit' class='sublink' value='Supprimer'>";
	echo "<input type='hidden' name='action' value='delete' />";
	echo "<input type='hidden' name='id' value='" . $user ["id_user"] . "' />";
	echo "</form>";
	echo "</td>";
	
	echo "</tr>";
}
?>
</table>